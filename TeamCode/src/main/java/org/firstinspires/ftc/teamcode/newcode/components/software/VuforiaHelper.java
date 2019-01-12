package org.firstinspires.ftc.teamcode.newcode.components.software;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.List;

public class VuforiaHelper {

    private VuforiaHelper() {}

    /**
     * Creates a location matrix from position and rotation values. Uses degrees and XYZ axis order
     * @param x - x-coordinate
     * @param y - y-coordinate
     * @param z - z-coordinate
     * @param xRot - x-axis rotation
     * @param yRot - y-axis rotation
     * @param zRot - z-axis rotation
     * @return - the location matrix
     */
    public static OpenGLMatrix createLocationMatrix(
            float x, float y, float z,
            float xRot, float yRot, float zRot) {
        return new OpenGLMatrix()
                .translated(x, y, z)
                .rotated(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES,
                        xRot, yRot, zRot);
    }

    /**
     * Finds the "average" position and rotation of a list of location matrices. Needs to be tested.
     */
    public static OpenGLMatrix averagePosition(List<OpenGLMatrix> positions) {
        // TODO test this method.
        if (positions.size() == 1) return positions.get(0);
        float Ex=0;
        float Ey=0;
        float Ez=0;
        float ExRot=0;
        float EyRot=0;
        float EzRot=0;
        float n = positions.size();
        for (OpenGLMatrix position : positions) {
            VectorF translation = position.getTranslation();
            Orientation rotation = Orientation.getOrientation(position,
                    AxesReference.EXTRINSIC, AxesOrder.XYZ,  AngleUnit.DEGREES);
            Ex += translation.get(0);
            Ey += translation.get(1);
            Ez += translation.get(2);
            ExRot += rotation.firstAngle;
            EyRot += rotation.secondAngle;
            EzRot += rotation.thirdAngle;
        }

        Ex /= n;
        Ey /= n;
        Ez /= n;
        ExRot /= n;
        EyRot /= n;
        EzRot /= n;

        return createLocationMatrix(Ex, Ey, Ez, ExRot, EyRot, EzRot);
    }
}
