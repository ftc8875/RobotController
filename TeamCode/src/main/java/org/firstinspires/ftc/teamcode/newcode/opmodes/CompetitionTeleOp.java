package org.firstinspires.ftc.teamcode.newcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.newcode.behavior.SwivelBehavior;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.PhoneSwivel;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.Grabber;

@TeleOp(name = "Gen 3", group = "competition")
public class CompetitionTeleOp extends OpMode {

    private Drivetrain drivetrain;
    private RobotLift robotLift;
    private PhoneSwivel phoneSwivel;

    @Override
    public void init() {
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "l");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "r");
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo swivelServo = hardwareMap.get(Servo.class, "swivel");
        drivetrain = new Drivetrain(leftMotor, rightMotor);
        robotLift = new RobotLift(liftMotor);
        phoneSwivel = new PhoneSwivel(swivelServo);
    }

    public void start() {
        phoneSwivel.swivel(SwivelBehavior.Position.RIGHT);
    }

    @Override
    public void loop() {
        double drivePower = Math.pow(gamepad1.left_stick_y, 1);
        double steerPower = Math.pow(gamepad1.right_stick_x, 1);
        boolean extendLift = gamepad2.x;
        boolean retractLift = gamepad2.a;
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

        telemetry.addData("Drive", "%.2f", drivePower);
        telemetry.addData("Steer", "%.2f", steerPower);
        telemetry.addData("Extend", extendLift);
        telemetry.addData("Retract", retractLift);
    }
}
