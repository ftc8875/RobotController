package org.firstinspires.ftc.teamcode.newcode.opmodes;

import org.firstinspires.ftc.teamcode.newcode.components.PhoneSwivel;

public interface SwivelBehavior extends RobotBehavior {

    enum Position {
        LEFT, CENTER, RIGHT
    }

    void setNextPosition(Position position);

}
