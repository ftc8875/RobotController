package org.firstinspires.ftc.teamcode.santaclaus.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.santaclaus.components.Drivetrain;
import org.firstinspires.ftc.teamcode.santaclaus.components.RobotLift;
import org.firstinspires.ftc.teamcode.santaclaus.components.Grabber;

@TeleOp(name = "Gen 3", group = "bigpackage")
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
        double drivePower = -gamepad1.left_stick_y;
        double steerPower = gamepad1.right_stick_x;
        boolean extendLift = gamepad2.x;
        boolean retractLift = gamepad2.a;
        boolean grab = gamepad2.right_bumper;
        boolean release = gamepad2.left_bumper;

        drivetrain.driveSteer(drivePower, steerPower);

        if (extendLift) {
            robotLift.extend();
        } else if (retractLift) {
            robotLift.retract();
        }

        if (grab) {
            grabber.grab();
        } else if (release) {
            grabber.release();
        }

        telemetry.addData("Drive", drivePower);
        telemetry.addData("Steer", steerPower);
        telemetry.addData("Extend", extendLift);
        telemetry.addData("Retract", retractLift);
        telemetry.addData("Grab", grab);
        telemetry.addData("Release", release);
    }
}
