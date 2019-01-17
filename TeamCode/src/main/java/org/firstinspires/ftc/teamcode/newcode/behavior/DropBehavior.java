package org.firstinspires.ftc.teamcode.newcode.behavior;

import org.firstinspires.ftc.teamcode.newcode.components.hardware.RobotLift;

public class DropBehavior implements RobotBehavior {

    private RobotLift robotLift;

    public DropBehavior(RobotLift robotLift) {
        this.robotLift = robotLift;
    }

    @Override
    public void run() {
        robotLift.extend();
        while (robotLift.isBusy()) {}
    }

    @Override
    public boolean runWasSuccessful() {
        return true;
    }
}
