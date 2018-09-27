package org.firstinspires.ftc.teamcode.competition.vuforia;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;

public class CustomVuforiaTrackable {

    private VuforiaTrackable trackable;
    private OpenGLMatrix position;
    private String trackableName;

    public CustomVuforiaTrackable(VuforiaTrackable trackable, OpenGLMatrix position) {
        this.trackable = trackable;
        this.position = position;
        trackableName = this.trackable.getName();
    }

    public VuforiaTrackable getTrackable() {
        return trackable;
    }

    public OpenGLMatrix getPosition() {
        return position;
    }

    public static OpenGLMatrix createPositionMatrix(
            float x, float y, float z,
            float xRot, float yRot, float zRot) {
        OpenGLMatrix matrix = new OpenGLMatrix()
                .translated(x, y, z)
                .rotated();
    }

    @Override
    public String toString() {
        return trackableName;
    }
}
