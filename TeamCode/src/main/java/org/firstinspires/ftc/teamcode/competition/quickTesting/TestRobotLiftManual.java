package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.competition.hardware.RobotLift;

@Disabled
@TeleOp(name="Test Robot Lift w/ Controller", group="Test")
public class TestRobotLiftManual extends OpMode {

    private RobotLift robotLift;
    private String lastButton = "No button pressed yet...";

    @Override
    public void init() {
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        Servo liftServo = hardwareMap.get(Servo.class, "liftServo");
        robotLift = new RobotLift(liftMotor, liftServo, RobotLift.Mode.LIFT);
    }

    @Override
    public void start() {
        robotLift.start();
    }

    @Override
    public void loop() {
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

        telemetry.addData("Mode", robotLift.getMode());
        telemetry.addData("Position", "%.2f", robotLift.getMotorPositionInches());
        telemetry.addData("Last Button", lastButton);

    }

    @Override
    public void stop() {
        robotLift.stop();
    }
}
