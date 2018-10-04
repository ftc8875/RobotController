package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class DriveTrain {

    protected ArrayList<DcMotor> leftMotors;
    protected ArrayList<DcMotor> rightMotors;

    public DriveTrain(DriveTrainMotors motors) {
        this.leftMotors = motors.getLeftMotors();
        this.rightMotors = motors.getRightMotors();
    }
}
