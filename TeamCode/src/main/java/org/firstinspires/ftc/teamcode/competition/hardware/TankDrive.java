package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.general.RobotComponent;

import java.util.List;

public class TankDrive implements DriveTrain {

    private List<DcMotor> leftMotors;
    private List<DcMotor> rightMotors;

    private float leftPower;
    private float rightPower;

    protected TankDrive(TankDriveBuilder builder) {
        this.leftMotors = builder.getLeftMotors();
        this.rightMotors = builder.getRightMotors();
    }

    /**
     * Drive the robot, just as a tank, powering either side independently
     * @param left: the power to give the left-side motors
     * @param right: the power to give the right-side motors
     */
    public void driveTank(float left, float right) {
        setLeftPower(left);
        setRightPower(right);
    }

    @Override
    public void drive(float power, float turn) {
         setLeftPower(0.5f * (power - turn));
         setRightPower(0.5f * (power + turn));
    }

    private void setLeftPower(float power) {
        leftPower = Range.clip(power, -1, 1);
        for (DcMotor motor : leftMotors) {
            motor.setPower(leftPower);
        }
    }
    private void setRightPower(float power) {
        rightPower = Range.clip(power, -1, 1);
        for (DcMotor motor : rightMotors) {
            motor.setPower(rightPower);
        }
    }

    private float getLeftPower() {
        return leftPower;
    }

    private float getRightPower() {
        return rightPower;
    }

    private void initMotor(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0.0);
    }

    private void setMotorZeroPowerBehaviors(DcMotor.ZeroPowerBehavior behavior) {
        for (DcMotor motor : leftMotors) {
            motor.setZeroPowerBehavior(behavior);
        }
        for (DcMotor motor : rightMotors) {
            motor.setZeroPowerBehavior(behavior);
        }
    }

    @Override
    public void init() {
        for(DcMotor motor : leftMotors) {
            motor.setDirection(DcMotor.Direction.REVERSE);
            initMotor(motor);
        }

        for(DcMotor motor : rightMotors) {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
            initMotor(motor);
        }
    }

    @Override
    public void start() {
        drive(0,0);
    }

    @Override
    public void stop() {
        setMotorZeroPowerBehaviors(DcMotor.ZeroPowerBehavior.FLOAT);
        drive(0, 0);
    }

    @Override
    public void emergencyStop() {
        setMotorZeroPowerBehaviors(DcMotor.ZeroPowerBehavior.FLOAT);
        drive(0, 0);
    }
}
