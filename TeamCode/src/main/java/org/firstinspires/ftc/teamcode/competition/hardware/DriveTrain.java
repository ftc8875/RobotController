package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.general.RobotComponent;

import java.util.ArrayList;

public interface DriveTrain extends RobotComponent {

    /**
     * Drive the robot, supplying the overall power to the robot, and the rate at which the robot
     * should be turning.
     * @param power: the overall power, -1 to 1
     * @param turn: the rate at which to turn the robot, -1 to 1. -ve CW, +ve CCW.
     */
    void drive(float power, float turn);

    //boolean isBusy();

}
