package org.firstinspires.ftc.teamcode.santaclaus.hooves;

import com.qualcomm.robotcore.hardware.DcMotor;

public class SleighLift {

    private static final double MOTOR_POWER = 0.5;

    private static final double ENCODER_COUNTS_PER_REV = 2240;
    private static final double INCHES_PER_REV = 0.25;
    private static final double ENCODER_COUNTS_PER_INCH = ENCODER_COUNTS_PER_REV / INCHES_PER_REV;

    private static final double EXTEND_INCHES = 3.5;
    private static final int EXTEND_POSITION = (int) Math.round(EXTEND_INCHES * ENCODER_COUNTS_PER_INCH);
    private static final double RETRACT_INCHES = 0.5;
    private static final int RETRACT_POSITION = (int) Math.round(RETRACT_INCHES * ENCODER_COUNTS_PER_INCH);

    private DcMotor liftMotor;

    public SleighLift(DcMotor liftMotor) {
        this.liftMotor = liftMotor;
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void extend() {
        liftMotor.setTargetPosition(EXTEND_POSITION);
        liftMotor.setPower(MOTOR_POWER);
    }

    public void retract() {
        liftMotor.setTargetPosition(RETRACT_POSITION);
        liftMotor.setPower(MOTOR_POWER);
    }

    public DcMotor getLiftMotor() {
        return liftMotor;
    }
}
