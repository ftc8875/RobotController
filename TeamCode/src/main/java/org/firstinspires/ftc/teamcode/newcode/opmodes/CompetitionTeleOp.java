package org.firstinspires.ftc.teamcode.newcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.newcode.components.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.Grabber;

@TeleOp(name = "Gen 3", group = "competition")
public class CompetitionTeleOp extends OpMode {

    Drivetrain drivetrain;
    RobotLift robotLift;
    Grabber grabber;

    @Override
    public void init() {
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "l");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "r");
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo grabServo = hardwareMap.get(Servo.class, "grab");
        drivetrain = new Drivetrain(leftMotor, rightMotor);
        robotLift = new RobotLift(liftMotor);
        grabber = new Grabber(grabServo);
    }

    @Override
    public void loop() {
        double drivePower = Math.pow(-gamepad1.left_stick_y, 3);
        double steerPower = Math.pow(gamepad1.right_stick_x, 3);
        boolean extendLift = gamepad2.x;
        boolean retractLift = gamepad2.a;
        boolean grab = gamepad2.right_bumper;
        boolean release = gamepad2.left_bumper;
        boolean stop = gamepad2.b;
        boolean bumpUp = gamepad2.dpad_up;
        boolean bumpDown = gamepad2.dpad_down;

        drivetrain.driveSteer(drivePower, steerPower);

        if (extendLift) {
            robotLift.extend();
        } else if (retractLift) {
            robotLift.retract();
        } else if (bumpUp) {
            robotLift.bumpUp();
        } else if (bumpDown) {
            robotLift.bumpDown();
        }

        if (stop) {
            robotLift.stop();
        }

        if (grab) {
            grabber.grab();
        } else if (release) {
            grabber.release();
        }

        telemetry.addData("Drive", "%.2f", drivePower);
        telemetry.addData("Steer", "%.2f", steerPower);
        telemetry.addData("Extend", extendLift);
        telemetry.addData("Retract", retractLift);
        telemetry.addData("Grab", grab);
        telemetry.addData("Release", release);
    }
}
