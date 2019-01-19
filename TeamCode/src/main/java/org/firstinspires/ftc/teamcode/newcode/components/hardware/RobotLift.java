package org.firstinspires.ftc.teamcode.newcode.components.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RobotLift {

    private static final double MOTOR_POWER = 0.5;

    private static final double ENCODER_COUNTS_PER_REV = 2240;
    private static final double INCHES_PER_REV = 1.0;
    private static final double ENCODER_COUNTS_PER_INCH = ENCODER_COUNTS_PER_REV / INCHES_PER_REV;

    private static final double EXTEND_INCHES = 3.0;
    private static final int EXTEND_POSITION = (int) Math.round(EXTEND_INCHES * ENCODER_COUNTS_PER_INCH);
    private static final double RETRACT_INCHES = 0.5;
    private static final int RETRACT_POSITION = (int) Math.round(RETRACT_INCHES * ENCODER_COUNTS_PER_INCH);

    private static final int BUMP_TICKS = 10;
    private static final double BUMP_POWER = 0.25;

    private DcMotor liftMotor;

    public RobotLift(DcMotor liftMotor) {
        this.liftMotor = liftMotor;
        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public boolean isBusy() {
        return liftMotor.isBusy();
    }

    public void extend() {
        liftMotor.setTargetPosition(EXTEND_POSITION);
        liftMotor.setPower(MOTOR_POWER);
    }

    public void retract() {
        liftMotor.setTargetPosition(RETRACT_POSITION);
        liftMotor.setPower(MOTOR_POWER);
    }

    public void bumpUp() {
        bump(true);
    }

    public void bumpDown() {
        bump(false);
    }

    private void bump(boolean up) {
        int m = (up ? 1 : -1) * BUMP_TICKS;
        int pos = liftMotor.getCurrentPosition();
        liftMotor.setTargetPosition(pos + m);
        liftMotor.setPower(BUMP_POWER);
    }

    public void stop() {
        liftMotor.setPower(0);
    }

    public DcMotor getLiftMotor() {
        return liftMotor;
    }
}
