package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.general.RobotComponent;

public class RobotLift implements RobotComponent {

    private static final float COUNTS_PER_INCH = 384;

    private static final float EXTEND_INCHES = 3.1f;
    private static final float RETRACT_INCHES = 0.1f;
    private static final float OVERSHOOT_INCHES = EXTEND_INCHES + 0.25f;

    private static final float SERVO_CLOSE = 0;
    private static final float SERVO_OPEN = 90;

    private static final int EXTEND_ENCODER_POSITION = Math.round(EXTEND_INCHES * COUNTS_PER_INCH);
    private static final int RETRACT_ENCODER_POSITION = Math.round(RETRACT_INCHES * COUNTS_PER_INCH);
    private static final int OVERSHOOT_ENCODER_POSITION = Math.round(OVERSHOOT_INCHES * COUNTS_PER_INCH);

    private static final float LIFT_EXTEND_POWER = 0.95f;
    private static final float LIFT_RETRACT_POWER = 0.5f;
    private static final float LAND_EXTEND_POWER = 0.5f;
    private static final float LAND_RETRACT_POWER = 0.95f;
    private static final float OVERSHOOT_POWER = 0.8f;

    private static final DcMotor.Direction LIFT_MOTOR_DIRECTION = DcMotor.Direction.REVERSE;

    private DcMotor liftMotor;
    private Servo releaser;

    private Mode mode;

    public enum Mode {
        LIFT,
        LAND
    }

    /**
     * Initialize a RobotLift.
     * @param liftMotor - the DcMotor that lifts the robot up
     * @param releaser - the Servo that opens and closes the clip
     */
    public RobotLift(DcMotor liftMotor, Servo releaser, Mode mode) {
        this.liftMotor = liftMotor;
        this.releaser = releaser;
        this.mode = mode;
        init();
    }

    // DEBUG
    public float getMotorPositionInches() {
        return liftMotor.getCurrentPosition() / COUNTS_PER_INCH;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        freeze();
    }

    public Mode getMode() {
        return mode;
    }

    private void extend(float power) {
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setTargetPosition(EXTEND_ENCODER_POSITION);
        liftMotor.setPower(power);
        //while(liftMotor.isBusy()) {}
    }

    public void extend() {
        switch(mode) {
            case LIFT:
                extend(LIFT_EXTEND_POWER);
                break;
            case LAND:
                extend(LAND_EXTEND_POWER);
                break;
        }
    }

    private void retract(float power) {
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setTargetPosition(RETRACT_ENCODER_POSITION);
        liftMotor.setPower(power);
        //while(liftMotor.isBusy()) {}
    }

    public void retract() {
       switch(mode) {
           case LIFT:
               retract(LIFT_RETRACT_POWER);
               break;
           case LAND:
               retract(LAND_RETRACT_POWER);
               break;
       }
    }

    public void openHook() {
        releaser.setPosition(SERVO_OPEN);
    }

    public void closeHook() {
        releaser.setPosition(SERVO_CLOSE);
    }

    public void overShoot() {
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setTargetPosition(OVERSHOOT_ENCODER_POSITION);
        liftMotor.setPower(OVERSHOOT_POWER);
        //while(liftMotor.isBusy()) {}
    }

    public void freeze() {
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
        switch(mode) {
            case LIFT:
                openHook();
                break;
            case LAND:
                closeHook();
                break;
        }
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
