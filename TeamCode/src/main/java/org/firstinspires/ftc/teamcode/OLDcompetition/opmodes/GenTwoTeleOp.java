package org.firstinspires.ftc.teamcode.OLDcompetition.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.OLDcompetition.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.OLDcompetition.hardware.ServoClaw;
import org.firstinspires.ftc.teamcode.OLDcompetition.hardware.TankDrive;
import org.firstinspires.ftc.teamcode.OLDcompetition.hardware.TankDriveBuilder;

@Disabled
@TeleOp(name="Gen 2", group="gen2")
public class GenTwoTeleOp extends OpMode {

    private double lastChangeModeTime;

    private TankDrive tankDrive;
    private RobotLift robotLift;
    private ServoClaw claw;

    private String lastButton = "No button pressed yet...";

    @Override
    public void init() {
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        Servo liftServo = hardwareMap.get(Servo.class, "liftServo");
        Servo clawServo = hardwareMap.get(Servo.class, "clawServo");

        tankDrive = new TankDriveBuilder(hardwareMap)
                .withLeftMotors("l")
                .withRightMotors("r")
                .build();

        tankDrive.init();

        robotLift = new RobotLift(liftMotor, liftServo, RobotLift.Mode.LIFT, RobotLift.Position.EXTENDED);
        robotLift.init();

        claw = new ServoClaw(clawServo, true, 0.45, 0.15);
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

        boolean openClaw = gamepad1.a;
        boolean closeClaw = gamepad1.b;

        boolean changeMode = gamepad2.right_bumper;
        boolean extend = gamepad2.x;
        boolean retract = gamepad2.y;
        boolean freeze = gamepad2.b;
        boolean nudgeUp = gamepad2.dpad_up;
        boolean nudgeDown = gamepad2.dpad_down;

        if (changeMode) {
            lastButton = "RIGHT BUMPER";
            double runTime = getRuntime();
            if (runTime > lastChangeModeTime + 0.5) {
                lastChangeModeTime = runTime;
                if (robotLift.getMode() == RobotLift.Mode.LIFT) {
                    robotLift.setMode(RobotLift.Mode.LAND);
                } else {
                    robotLift.setMode(RobotLift.Mode.LIFT);
                }
            }
        }

        if (extend) {
            lastButton = "X";
            robotLift.extend();
        } else if (retract) {
            lastButton = "Y";
            robotLift.retract();
        } else if (nudgeUp) {
            lastButton = "^";
            robotLift.nudgeUp();
        } else if (nudgeDown) {
            lastButton = "v";
            robotLift.nudgeDown();
        }

        if (freeze) {
            lastButton = "B";
            robotLift.freeze();
        }

        tankDrive.drive(d, t);

        if (openClaw) {
            claw.open();
        } else if (closeClaw) {
            claw.close();
        }

        telemetry.addData("Drive      ", "%.2f", d);
        telemetry.addData("Turn       ", "%.2f", t);
        telemetry.addData("Mode       ", robotLift.getMode());
        telemetry.addData("Position   ", "%.2f", robotLift.getMotorPositionInches());
        telemetry.addData("Last Button", lastButton);
        telemetry.addData("Lift Busy  ", robotLift.liftBusy());
        telemetry.addData("Claw Closed", claw.isClosed());
    }

    @Override
    public void stop() {
        robotLift.stop();
        tankDrive.stop();
    }
}
