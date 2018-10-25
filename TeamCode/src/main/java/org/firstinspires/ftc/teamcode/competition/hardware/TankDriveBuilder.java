package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

public class TankDriveBuilder {

    private HardwareMap hardwareMap;

    private ArrayList<DcMotor> leftMotors = new ArrayList<>();
    private ArrayList<DcMotor> rightMotors = new ArrayList<>();
    private boolean encoders = false;
    private float encoderCountsPerInch;
    private float turnDiameter;

    public TankDriveBuilder(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public TankDriveBuilder withLeftMotors(String ... leftMotorNames) {
        for (String motorName : leftMotorNames) {
            this.leftMotors.add(hardwareMap.get(DcMotor.class, motorName));
        }
        return this;
    }

    public TankDriveBuilder withRightMotors(String ... rightMotorNames) {
        for (String motorName : rightMotorNames) {
            this.rightMotors.add(hardwareMap.get(DcMotor.class, motorName));
        }
        return this;
    }

    public TankDriveBuilder withEncoders(float encoderCountsPerInch, float turnDiameter) {
        encoders = true;
        this.encoderCountsPerInch = encoderCountsPerInch;
        this.turnDiameter = turnDiameter;
        return this;
    }

    public TankDrive build() {
        TankDrivePlain tankDrivePlain = new TankDrivePlain(this);
        if (encoders) {
            return new TankDriveEncoders(tankDrivePlain, encoderCountsPerInch, turnDiameter);
        }
        return tankDrivePlain;
    }

    protected ArrayList<DcMotor> getLeftMotors() {
        return leftMotors;
    }

    protected ArrayList<DcMotor> getRightMotors() {
        return rightMotors;
    }
}
