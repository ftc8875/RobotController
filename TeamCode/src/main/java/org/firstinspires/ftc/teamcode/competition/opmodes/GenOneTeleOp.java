package org.firstinspires.ftc.teamcode.competition.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.competition.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDrive;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDriveBuilder;

@TeleOp(name = "Gen One", group = "gen1")
public class GenOneTeleOp extends OpMode {

    TankDrive tankDrive;
    RobotLift robotLift;

    private String lastButton = "No button pressed yet...";

    @Override
    public void init() {
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        Servo liftServo = hardwareMap.get(Servo.class, "liftServo");

        tankDrive = new TankDriveBuilder(hardwareMap)
                .withLeftMotors("l")
                .withRightMotors("r")
                .build();

        tankDrive.init();

        robotLift = new RobotLift(liftMotor, liftServo, RobotLift.Mode.LIFT);
        robotLift.init();
    }

    @Override
    public void start() {
        tankDrive.start();
        robotLift.start();
    }

    @Override
    public void loop() {
        float d = -gamepad1.left_stick_y;
        float t = gamepad1.right_stick_x;

        boolean changeMode = gamepad2.right_bumper;
        boolean extend = gamepad2.x;
        boolean retract = gamepad2.y;
        boolean freeze = gamepad2.b;

        if (changeMode) {
            lastButton = "RIGHT BUMPER";
            if (robotLift.getMode() == RobotLift.Mode.LIFT) {
                robotLift.setMode(RobotLift.Mode.LAND);
            } else {
                robotLift.setMode(RobotLift.Mode.LIFT);
            }
        }

        if (extend) {
            lastButton = "X";
            robotLift.extend();
        } else if (retract) {
            lastButton = "Y";
            robotLift.retract();
        }

        if (freeze) {
            lastButton = "B";
            robotLift.freeze();
        }

        tankDrive.drive(d, t);

        telemetry.addData("Drive", "%.2f", d);
        telemetry.addData("Turn ", "%.2f", t);
        telemetry.addData("Mode", robotLift.getMode());
        telemetry.addData("Position", "%.2f", robotLift.getMotorPositionInches());
        telemetry.addData("Last Button", lastButton);

    }

    @Override
    public void stop() {

    }
}
