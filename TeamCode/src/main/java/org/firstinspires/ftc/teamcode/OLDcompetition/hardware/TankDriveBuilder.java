package org.firstinspires.ftc.teamcode.OLDcompetition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.newcode.components.software.Vuforia;

import java.util.ArrayList;

public class TankDriveBuilder {

    private HardwareMap hardwareMap;

    private ArrayList<DcMotor> leftMotors = new ArrayList<>();
    private ArrayList<DcMotor> rightMotors = new ArrayList<>();
    private boolean encoders = false;
    private float encoderCountsPerInch;
    private float turnDiameter;
    private boolean vuforia = false;
    private Vuforia vuforiaInstance;

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

    public TankDriveBuilder withVuforia(Vuforia vuforiaInstance) {
        this.vuforia = true;
        this.vuforiaInstance = vuforiaInstance;
        return this;
    }

    public TankDrive build() {
        TankDrivePlain tankDrivePlain = new TankDrivePlain(this);
        if (encoders) {
            if (vuforia) {
                return new TankDriveVuforia(tankDrivePlain, encoderCountsPerInch, turnDiameter, vuforiaInstance);
            }
            return new TankDriveEncoders(tankDrivePlain, encoderCountsPerInch, turnDiameter);
        } else if (vuforia) {
            throw new NoEncodersException();
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
