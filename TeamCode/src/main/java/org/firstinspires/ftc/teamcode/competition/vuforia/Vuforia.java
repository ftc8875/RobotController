package org.firstinspires.ftc.teamcode.competition.vuforia;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.general.RobotComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vuforia implements RobotComponent {

    // TODO Clean up this mess. SRP much? Loose coupling? Come on! You can do better than this!

    public static class VuforiaBuilder {

        private VuforiaLocalizer.CameraDirection cameraDirection;
        private boolean enableCameraMonitoring = false;
        private OpenGLMatrix cameraLocation;
        private String vuforiaKey;
        private String vuforiaAssetName;
        private List<String> vuforiaTrackableNames;
        private HardwareMap hardwareMap;

        public VuforiaBuilder(String vuforiaKey) {
            this.vuforiaKey = vuforiaKey;
        }

        public VuforiaBuilder withCameraDirection(VuforiaLocalizer.CameraDirection cameraDirection) {
            this.cameraDirection = cameraDirection;
            return this;
        }

        public VuforiaBuilder withCameraLocation(OpenGLMatrix cameraLocation) {
            this.cameraLocation = cameraLocation;
            return this;
        }

        public VuforiaBuilder withVuforiaAssetName(String vuforiaAssetName) {
            this.vuforiaAssetName = vuforiaAssetName;
            return this;
        }

        public VuforiaBuilder withVuforiaTrackableNames(String ... vuforiaTrackableNames) {
            Collection<String> namesCollection = Arrays.asList(vuforiaTrackableNames);
            this.vuforiaTrackableNames = new ArrayList<>();
            this.vuforiaTrackableNames.addAll(namesCollection);
            return this;
        }

        public VuforiaBuilder withVuforiaTrackableNames(List<String> vuforiaTrackableNames) {
            this.vuforiaTrackableNames = vuforiaTrackableNames;
            return this;
        }

        public VuforiaBuilder withCameraMonitoring(HardwareMap hardwareMap) {
            this.hardwareMap = hardwareMap;
            enableCameraMonitoring = true;
            return this;
        }

        public Vuforia build() {
            return new Vuforia(
                    this.cameraDirection,
                    this.enableCameraMonitoring,
                    this.cameraLocation,
                    this.vuforiaKey,
                    this.vuforiaAssetName,
                    this.vuforiaTrackableNames,
                    this.hardwareMap
            );

        }
    }

    private String vuforiaKey;

    private VuforiaLocalizer vuforiaLocalizer;
    private Map<String, VuforiaTrackable> trackablesMap;
    private VuforiaTrackables trackablesList;

    private VuforiaLocalizer.CameraDirection cameraDirection;
    private OpenGLMatrix cameraLocation;
    private String vuforiaAssetName;

    /**
     * Initializes new Vuforia instance.
     * @param cameraDirection - Which phone camera to use. FRONT is the camera on the screen.
     *                        BACK is the camera on the other side. BACK is usually a higher
     *                        resolution than FRONT.
     * @param enableCameraMonitoring - Whether or not to display the camera monitor view on the
     *                               robot controller phone. Displaying it uses more battery but is
     *                               useful for debugging.
     * @param cameraLocation - A matrix representing the location and orientation of the camera,
     *                       relative to the center of the robot. Use the following axes:
     *                       * X: +ve in the forward direction of the robot
     *                       * Y: +ve to the left of the robot
     *                       * Z: +ve upwards of the robot
     * @param vuforiaKey - the unique key generated on Vuforia's website
     * @param vuforiaAssetName - the name of the asset set for the current season's VuMarks.
     *                         Example: "RoverRuckus"
     * @param vuforiaTrackableNames - a list of the names to assign to each trackable in the asset
     *                              set, in order for the asset set
     * @param hardwareMap - the hardware map for the current op mode
     */
    private Vuforia(VuforiaLocalizer.CameraDirection cameraDirection,
                   boolean enableCameraMonitoring,
                   OpenGLMatrix cameraLocation,
                   String vuforiaKey,
                   String vuforiaAssetName,
                   List<String> vuforiaTrackableNames,
                   HardwareMap hardwareMap) {

        // TODO split into multiple methods. A LOT of this should be in init()
        // should this constructor really take such an ungodly number of args??

        this.cameraDirection = cameraDirection;
        this.cameraLocation = cameraLocation;
        this.vuforiaKey = vuforiaKey;
        this.vuforiaAssetName = vuforiaAssetName;

        VuforiaLocalizer.Parameters parameters;
        if(enableCameraMonitoring) {
            parameters = new VuforiaLocalizer.Parameters(
                    hardwareMap.appContext.getResources().getIdentifier(
                            "cameraMonitorViewId",
                            "id",
                            hardwareMap.appContext.getPackageName())
            );
        } else {
            parameters = new VuforiaLocalizer.Parameters();
        }

        parameters.vuforiaLicenseKey = vuforiaKey;
        parameters.cameraDirection = cameraDirection;

        vuforiaLocalizer = ClassFactory.getInstance().createVuforia(parameters);

        trackablesList = vuforiaLocalizer.loadTrackablesFromAsset(vuforiaAssetName);

        if (trackablesList.size() != vuforiaTrackableNames.size()) {
            throw new RuntimeException(String.format(
                    "Vuforia trackable names list is different size (%d) than Vuforia assets list" +
                            "size (%d)", trackablesList.size(), vuforiaTrackableNames.size()));
        }

        trackablesMap = new HashMap<>();

        for (int i=0; i<trackablesList.size(); i++) {
            addTrackable(trackablesList.get(i), vuforiaTrackableNames.get(i), /* TODO add actual location matrix */ new OpenGLMatrix() /* BAD CODE */);
        }

    }

    /**
     * Returns whether a given Vuforia Trackable (eg VuMark) is visible
     * @param trackableName - the name of the Trackable (assigned when building the Vuforia object)
     */
    public boolean isVisible(String trackableName) {
        VuforiaTrackable trackable = trackablesMap.get(trackableName);
        VuforiaTrackableDefaultListener listener = getTrackableListener(trackable);
        return listener.isVisible();
    }

    /**
     * Returns a list of all the names of the Vuforia Trackables (eg VuMarks) that are visible
     */
    public List<String> getVisibleTrackableNames() {
        List<String> names = new ArrayList<>();
        List<VuforiaTrackable> visibleTrackables = visibleTrackables();
        for (VuforiaTrackable trackable : visibleTrackables) {
            names.add(trackable.getName());
        }
        return names;
    }

    /**
     * Returns the robot's current position on the field, as an OpenGLMatrix., using the Vuforia
     * Trackables (eg VuMarks) that are currently visible.
     * @return - the robot's position, or null if no Trackables are visible
     */
    public OpenGLMatrix getRobotFieldPosition() {
        // TODO test this!
        List<VuforiaTrackable> visibleTrackables = visibleTrackables();
        if (visibleTrackables.size() == 0) return null;
        List<OpenGLMatrix> positions = new ArrayList<>();
        for (VuforiaTrackable trackable : visibleTrackables) {
            positions.add(getRobotPositionFromTrackable(trackable.getName()));
        }
        return VuforiaHelper.averagePosition(positions);
    }

    /**
     * Returns the robot's current position on the field, considering only one Trackable.
     * @param trackableName - the name of the Trackable (assigned when building the Vuforia object)
     * @return
     */
    public OpenGLMatrix getRobotPositionFromTrackable(String trackableName) {
        VuforiaTrackable trackable = trackablesMap.get(trackableName);
        VuforiaTrackableDefaultListener listener = getTrackableListener(trackable);
        return listener.getUpdatedRobotLocation();

    }


    @Override
    public void init() {
        // TODO all the init stuff
    }

    @Override
    public void start() {
        trackablesList.activate();
    }

    @Override
    public void stop() {}

    @Override
    public void emergencyStop() {
        stop();
    }

    /**
     * Add a trackable and handle its name and location
     * @param trackable
     * @param name
     * @param location - location of Trackable relative to the field
     */
    private void addTrackable(VuforiaTrackable trackable, String name, OpenGLMatrix location) {
        trackable.setName(name);
        trackable.setLocation(location);
        addPhoneInfoToTrackable(trackable);
        trackablesMap.put(name, trackable);
    }

    /**
     * Returns whether a Trackable is visible, given the trackable itself
     */
    private boolean isVisible(VuforiaTrackable trackable) {
        return isVisible(trackable.getName());
    }

    /**
     * Returns a list of the Trackables that are visible, as themselves, not their names
     */
    private List<VuforiaTrackable> visibleTrackables() {
        List<VuforiaTrackable> visibleTrackables = new ArrayList<>();
        for (VuforiaTrackable trackable : trackablesMap.values()) {
            if (isVisible(trackable)) {
                visibleTrackables.add(trackable);
            }
        }
        return visibleTrackables;
    }

    /**
     * Adds phone camera location and camera direction to the Trackable
     * @param trackable
     */
    private void addPhoneInfoToTrackable(VuforiaTrackable trackable) {
        ((VuforiaTrackableDefaultListener)(trackable.getListener()))
                .setPhoneInformation(cameraLocation, cameraDirection);
    }

    /**
     * Get the Listener from a Trackable
     * @param trackable
     * @return - the Trackable's listener
     */
    private VuforiaTrackableDefaultListener getTrackableListener(VuforiaTrackable trackable) {
        return (VuforiaTrackableDefaultListener)trackable.getListener();
    }

}
