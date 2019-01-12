package org.firstinspires.ftc.teamcode.newcode.behavior;

public interface SwivelBehavior extends RobotBehavior {

    enum Position {
        LEFT, CENTER, RIGHT, OTHER
    }

    void setNextPosition(Position position);

}
