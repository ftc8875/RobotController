package org.firstinspires.ftc.teamcode.competition.vuforia;

import com.vuforia.Trackable;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.general.RobotComponent;

import java.util.ArrayList;
import java.util.List;

public class Vuforia implements RobotComponent {

    /**
     * Initializes new Vuforia instance.
     * @param cameraDirection - Which phone camera to use. FRONT is the camera on the screen.
     *                        BACK is the camera on the other side. BACK is usually a higher
     *                        resolution than FRONT.
     * @param enableCameraMonitoring - Whether or not to display the camera monitor view on the
     *                               robot controller phone. Displaying it uses more battery but is
     *                               useful for debugging.
     * @param trackables - A list of the CustomVuforiaTrackables used for location of the robot.
     *                   See CustomVuforiaTrackable class for more info.
     * @param cameraLocation - A matrix representing the location and orientation of the camera,
     *                       relative to the center of the robot. Use the following axes:
     *                       * X: +ve in the forward direction of the robot
     *                       * Y: +ve to the left of the robot
     *                       * Z: +ve upwards of the robot
     */
    public Vuforia(VuforiaLocalizer.CameraDirection cameraDirection,
                   boolean enableCameraMonitoring,
                   List<CustomVuforiaTrackable> trackables,
                   OpenGLMatrix cameraLocation) {
        // TODO implement
    }

    public boolean isVisible(String trackableName) {
        // TODO implement
        return false;
    }

    public List<CustomVuforiaTrackable> visibleTrackables() {
        // TODO implement
        return new ArrayList<CustomVuforiaTrackable>();
    }

    public OpenGLMatrix getRobotFieldPosition() {
        // TODO implement
        return new OpenGLMatrix();
    }

    public OpenGLMatrix getRobotPositionToTrackable(String trackableName) {
        // TODO implement
        return new OpenGLMatrix();
    }

    @Override
    public void init() {}

    @Override
    public void start() {}

    @Override
    public void stop() {}

    @Override
    public void emergencyStop() {
        stop();
    }
}
