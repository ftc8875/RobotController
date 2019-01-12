package org.firstinspires.ftc.teamcode.newcode.components.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain {

    private static final double WHEEL_DIAMETER_INCHES = 3.5;
    private static final double WHEEL_CIRCUMFERENCE_INCHES = WHEEL_DIAMETER_INCHES * Math.PI;
    private static final double ENCODER_COUNTS_PER_REV = 1120;
    private static final double ENCODER_COUNTS_PER_INCH = ENCODER_COUNTS_PER_REV / WHEEL_CIRCUMFERENCE_INCHES;

    private static final double TURN_DIAMETER = 11.5;

    private static final double ENCODER_POWER = 0.75;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public Drivetrain(DcMotor leftMotor, DcMotor rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Drives the robot like a tank, directly controlling the motors.
     * @param leftPower - the power to give the left motor, -1 to 1.
     * @param rightPower - the power to give the right motor, -1 to 1.
     */
    public void driveTank(double leftPower, double rightPower) {
        setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
        leftPower = Range.clip(leftPower, -1.0, 1.0);
        rightPower = Range.clip(rightPower, -1.0, 1.0);
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    /**
     * Drives the robot like a car, with an overall power and an amount to steer.
     * @param power - the overall power to give the robot, -1 to 1.
     * @param steer - the amount to steer the robot, -1 to 1. -1 is CCW, 1 is CW.
     */
    public void driveSteer(double power, double steer) {
        setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
        double leftPower = 0.5 * (power - steer);
        double rightPower = 0.5 * (power + steer);
        driveTank(leftPower, rightPower);
    }

    /**
     * Drives directly forward the specified number of inches
     * @param inches - the number of inches to drive forward
     * @param power - the power to apply to the motors, 0 to 1
     */
    public void driveDistance(double inches, double power) {
        setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setTargetPosition((int) Math.round(inches * ENCODER_COUNTS_PER_INCH));
        rightMotor.setTargetPosition((int) Math.round(inches * ENCODER_COUNTS_PER_INCH));
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    /**
     * Turns the robot about its center of rotation the specified number of degrees
     * @param degrees - the number of degrees to turn. +ve is CCW, -ve is CW.
     * @param power - the power to apply to the motors, 0 to 1
     */
    public void turn(double degrees, double power) {
        setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        double arcLength = degrees * TURN_DIAMETER * Math.PI / 360.0;
        leftMotor.setTargetPosition((int) Math.round(-arcLength * ENCODER_COUNTS_PER_INCH));
        rightMotor.setTargetPosition((int) Math.round(arcLength * ENCODER_COUNTS_PER_INCH));
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public boolean isBusy() {
        return leftMotor.isBusy() || rightMotor.isBusy();
    }

    private void setMotorModes(DcMotor.RunMode mode) {
        leftMotor.setMode(mode);
        rightMotor.setMode(mode);
    }

    public DcMotor getLeftMotor() {
        return leftMotor;
    }

    public DcMotor getRightMotor() {
        return rightMotor;
    }

}
