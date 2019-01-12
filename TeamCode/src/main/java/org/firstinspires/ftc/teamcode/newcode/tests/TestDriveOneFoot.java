package org.firstinspires.ftc.teamcode.newcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.newcode.components.hardware.Drivetrain;

@TeleOp(name="Drive One Foot", group="test")
public class TestDriveOneFoot extends LinearOpMode {

    private Drivetrain drivetrain;

    public void runOpMode() {
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "l");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "r");
        drivetrain = new Drivetrain(leftMotor, rightMotor);
        telemetry.addLine("Initialized Drivetrain");
        telemetry.addLine("Waiting for start...");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("Press A to drive one foot.");
            telemetry.update();
            if (gamepad1.a) {
                telemetry.addLine("Driving one foot...");
                telemetry.update();
                driveOneFoot();
                while (drivetrain.isBusy() && opModeIsActive()) {}
                telemetry.addLine("Done!");
            }
        }

    }

    private void driveOneFoot() {
        drivetrain.driveDistance(12, 0.2);
    }

}
