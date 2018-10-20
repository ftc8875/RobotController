package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

public class TankDriveBuilder {

    protected HardwareMap hardwareMap;

    protected ArrayList<DcMotor> leftMotors = new ArrayList<>();
    protected ArrayList<DcMotor> rightMotors = new ArrayList<>();

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

    public TankDrive build() {
        return new TankDrive(this);
    }

    protected ArrayList<DcMotor> getLeftMotors() {
        return leftMotors;
    }

    protected ArrayList<DcMotor> getRightMotors() {
        return rightMotors;
    }
}
