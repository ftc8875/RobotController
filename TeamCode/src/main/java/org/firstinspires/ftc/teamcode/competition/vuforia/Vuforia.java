package org.firstinspires.ftc.teamcode.competition.vuforia;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Trackable;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.teamcode.general.RobotComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vuforia implements RobotComponent {

    private String vuforiaKey;

    private VuforiaLocalizer vuforiaLocalizer;
    private Map<String, Trackable> trackables;

    private VuforiaLocalizer.CameraDirection cameraDirection;
    private OpenGLMatrix cameraLocation;
    private String vuforiaAssetName;

    private class Trackable {
        protected VuforiaTrackable trackable;
        protected OpenGLMatrix location;
    }

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
     * @param hardwareMap - the hardware map for the current op mode
     */
    public Vuforia(VuforiaLocalizer.CameraDirection cameraDirection,
                   boolean enableCameraMonitoring,
                   OpenGLMatrix cameraLocation,
                   String vuforiaKey,
                   String vuforiaAssetName,
                   HardwareMap hardwareMap) {

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

    }

    public void addTrackable(VuforiaTrackable trackable, OpenGLMatrix location) {
        Trackable t = new Trackable();
        t.trackable = trackable;
        t.location = location;
        addPhoneInfoToTrackable(t);
        trackables.put(t.trackable.getName(), t);
    }

    public void addTrackable(VuforiaTrackable trackable, float xPos, float yPos, float zPos,
                             float xRot, float yRot, float zRot) {
        addTrackable(trackable, createLocationMatrix(
                xPos, yPos, zPos, xRot, yRot, zRot));
    }

    private OpenGLMatrix createLocationMatrix(
            float x, float y, float z,
            float xRot, float yRot, float zRot) {
        return new OpenGLMatrix()
                .translated(x, y, z)
                .rotated(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES,
                        xRot, yRot, zRot);
    }

    public boolean isVisible(String trackableName) {
        // TODO implement
        return false;
    }

    public List<VuforiaTrackable> visibleTrackables() {
        // TODO implement
        return new ArrayList<VuforiaTrackable>();
    }

    public OpenGLMatrix getRobotFieldPosition() {
        // TODO implement
        return new OpenGLMatrix();
    }

    public OpenGLMatrix getRobotPositionToTrackable(String trackableName) {
        // TODO implement
        return new OpenGLMatrix();
    }

    private List<VuforiaTrackable> generateTrackableList(List<String> names) {
        List<VuforiaTrackable> list = new ArrayList<>();

        for (String name : names) {
            list.add(this.trackables.get(name).trackable);
        }

        return list;
    }

    private void addPhoneInfoToTrackable(Trackable trackable) {
        ((VuforiaTrackableDefaultListener)(trackable.trackable.getListener()))
                .setPhoneInformation(cameraLocation, cameraDirection);
    }

    @Override
    public void init() {
        for(Trackable trackable : this.trackables.values()) {
            addPhoneInfoToTrackable(trackable);
        }

        // Whoops. I messed up. // TODO FIX. READ ConceptVuforiaNavRoverRuckus.java
        this.vuforiaAssetName.activate();

        // TODO all the init stuff
    }

    @Override
    public void start() {}

    @Override
    public void stop() {}

    @Override
    public void emergencyStop() {
        stop();
    }
}
