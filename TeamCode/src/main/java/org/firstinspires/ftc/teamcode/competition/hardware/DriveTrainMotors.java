package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

public class DriveTrainMotors {

    private HardwareMap hardwareMap;

    private ArrayList<DcMotor> leftMotors = new ArrayList<>();
    private ArrayList<DcMotor> rightMotors = new ArrayList<>();

    public DriveTrainMotors(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void addLeftMotors(String ... leftMotorNames) {
        for (String motorName : leftMotorNames) {
            this.leftMotors.add(hardwareMap.get(DcMotor.class, motorName));
        }
    }

    public void addRightMotors(String ... rightMotorNames) {
        for (String motorName : rightMotorNames) {
            this.rightMotors.add(hardwareMap.get(DcMotor.class, motorName));
        }
    }

    public ArrayList<DcMotor> getLeftMotors() {
        return leftMotors;
    }

    public ArrayList<DcMotor> getRightMotors() {
        return rightMotors;
    }
}
