package org.firstinspires.ftc.teamcode.OLDcompetition.hardware;

import org.firstinspires.ftc.teamcode.OLDgeneral.RobotComponent;

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
