package org.firstinspires.ftc.teamcode.newcode.behavior;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.newcode.components.hardware.Drivetrain;

public class BumpBehavior implements RobotBehavior {

    private static final double BUMP_INCHES = 42.0;
    private static final double BUMP_POWER = 0.25;

    private Drivetrain drivetrain;
    private LinearOpMode opMode;

    public BumpBehavior(Drivetrain drivetrain, LinearOpMode opMode) {
        this.drivetrain = drivetrain;
        this.opMode = opMode;
    }

    @Override
    public void run() {
        drivetrain.driveDistance(BUMP_INCHES, BUMP_POWER);
        while(drivetrain.isBusy() && opMode.opModeIsActive()) {}
    }

    @Override
    public boolean runWasSuccessful() {
        return true;
    }
}
