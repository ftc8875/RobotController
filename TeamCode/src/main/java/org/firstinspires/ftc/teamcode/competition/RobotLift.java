package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.general.RobotComponent;

public class RobotLift implements RobotComponent {

    private static final float SERVO_CLOSE = 0;
    private static final float SERVO_OPEN = 90;

    private static final int EXTEND_ENCODER_POSITION = 1000;
    private static final int RETRACT_ENCODER_POSITION = 1000;
    private static final int OVERSHOOT_ENCODER_POSITION = 1050;

    private static final float LIFT_EXTEND_POWER = 0.95f;
    private static final float LIFT_RETRACT_POWER = 0.5f;
    private static final float LAND_EXTEND_POWER = 0.5f;
    private static final float LAND_RETRACT_POWER = 0.95f;
    private static final float OVERSHOOT_POWER = 0.8f;

    private static final DcMotor.Direction LIFT_MOTOR_DIRECTION = DcMotor.Direction.FORWARD;

    private DcMotor liftMotor;
    private Servo releaser;

    /**
     * Initialize a RobotLift.
     * @param liftMotor - the DcMotor that lifts the robot up
     * @param releaser - the Servo that opens and closes the clip
     */
    public RobotLift(DcMotor liftMotor, Servo releaser) {
        this.liftMotor = liftMotor;
        this.releaser = releaser;
    }

    /**
     * Handles the sequence of events necessary to lift the robot from the playing field to the
     * lifted position on the lander.
     */
    public void lift() {
        setMotorModeToEncoder();
        openHook();
        extendLifter(LIFT_EXTEND_POWER);
        closeHook();
        retractLifter(LIFT_RETRACT_POWER);
        freezeMotor();
    }

    /**
     * Handles the sequence of events necessary to land the robot from the lifted position on the
     * lander to the playing field.
     */
    public void land() {
        setMotorModeToEncoder();
        extendLifter(LAND_EXTEND_POWER);
        openHook();
        retractLifter(LAND_RETRACT_POWER);
    }

    private void extendLifter(float power) {
        liftMotor.setTargetPosition(EXTEND_ENCODER_POSITION);
        liftMotor.setPower(power);
    }

    private void retractLifter(float power) {
        liftMotor.setTargetPosition(RETRACT_ENCODER_POSITION);
        liftMotor.setPower(power);
    }

    private void openHook() {
        releaser.setPosition(SERVO_OPEN);
    }

    private void closeHook() {
        releaser.setPosition(SERVO_CLOSE);
    }

    private void overShoot() {
        liftMotor.setTargetPosition(OVERSHOOT_ENCODER_POSITION);
        liftMotor.setPower(OVERSHOOT_POWER);
    }

    private void freezeMotor() {
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setPower(0.0);
    }

    private void setMotorModeToEncoder() {
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void init() {
        liftMotor.setDirection(LIFT_MOTOR_DIRECTION);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setPower(0);
    }

    @Override
    public void start() {
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorModeToEncoder();
    }

    @Override
    public void stop() {
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setPower(0);
    }

    @Override
    public void emergencyStop() {
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        liftMotor.setPower(0);
        releaser.close();
    }
}
