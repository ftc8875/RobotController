package org.firstinspires.ftc.teamcode.competition.vuforia;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;

public class CustomVuforiaTrackable {

    private VuforiaTrackable trackable;
    private OpenGLMatrix location;
    private String trackableName;

    public CustomVuforiaTrackable(VuforiaTrackable trackable, OpenGLMatrix location) {
        trackable.setLocation(location);
        trackableName = this.trackable.getName();
        this.trackable = trackable;
        this.location = location;
    }

    public VuforiaTrackable getTrackable() {
        return trackable;
    }

    public OpenGLMatrix getLocation() {
        return location;
    }

    public String getTrackableName() {
        return trackableName;
    }

    public static OpenGLMatrix createPositionMatrix(
            float x, float y, float z,
            float xRot, float yRot, float zRot) {
        return new OpenGLMatrix()
                .translated(x, y, z)
                .rotated(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES,
                        xRot, yRot, zRot);
    }

    @Override
    public String toString() {
        return trackableName;
    }
}
