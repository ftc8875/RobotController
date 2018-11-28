package org.firstinspires.ftc.teamcode.santaclaus.reindeer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.santaclaus.hooves.SleighLift;
import org.firstinspires.ftc.teamcode.santaclaus.hooves.Sleigh;
import org.firstinspires.ftc.teamcode.santaclaus.hooves.Mouth;

@TeleOp(name = "Gen 3", group = "bigpackage")
public class ChristmasDay extends OpMode {

    Sleigh sleigh;
    SleighLift sleighLift;
    Mouth mouth;

    @Override
    public void init() {
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "l");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "r");
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo grabServo = hardwareMap.get(Servo.class, "grab");
        sleigh = new Sleigh(leftMotor, rightMotor);
        sleighLift = new SleighLift(liftMotor);
        mouth = new Mouth(grabServo);
    }

    @Override
    public void loop() {
        double drivePower = -gamepad1.left_stick_y;
        double steerPower = gamepad1.right_stick_x;
        boolean extendLift = gamepad2.x;
        boolean retractLift = gamepad2.a;
        boolean grab = gamepad2.right_bumper;
        boolean release = gamepad2.left_bumper;

        sleigh.driveSteer(drivePower, steerPower);

        if (extendLift) {
            sleighLift.extend();
        } else if (retractLift) {
            sleighLift.retract();
        }

        if (grab) {
            mouth.grab();
        } else if (release) {
            mouth.release();
        }

        telemetry.addData("Drive", drivePower);
        telemetry.addData("Steer", steerPower);
        telemetry.addData("Extend", extendLift);
        telemetry.addData("Retract", retractLift);
        telemetry.addData("Grab", grab);
        telemetry.addData("Release", release);
    }
}
