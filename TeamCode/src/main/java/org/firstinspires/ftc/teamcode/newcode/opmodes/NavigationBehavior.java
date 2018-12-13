package org.firstinspires.ftc.teamcode.newcode.opmodes;

import org.firstinspires.ftc.teamcode.newcode.components.Drivetrain;
import org.firstinspires.ftc.teamcode.newcode.components.Vuforia;

public class NavigationBehavior implements RobotBehavior {

    private Drivetrain drivetrain;
    private Vuforia vuforia;

    private double targetX;
    private double targetY;
    private double targetRot;

    private double currentX;
    private double currentY;
    private double currentRot;

    public NavigationBehavior(Drivetrain drivetrain, Vuforia vuforia) {
        this.drivetrain = drivetrain;
        this.vuforia = vuforia;
    }

    public void setTargetLocation(double x, double y, double rotation) {
        targetX = x;
        targetY = y;
        targetRot = rotation;
    }

    public void run() {
        currentX = 0;
        currentY = 0;
        currentRot = 0;

    }

    private void setNewMotorPowers() {
        double dx = targetX - currentX;
        double dy = targetY - currentY;
        double dRot = targetRot - currentRot;
    }

    @Override
    public boolean runWasSuccessful() {
        return false;
    }

    public void stop() {}
}
