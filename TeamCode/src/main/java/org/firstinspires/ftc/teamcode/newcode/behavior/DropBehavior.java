package org.firstinspires.ftc.teamcode.newcode.behavior;

import org.firstinspires.ftc.teamcode.OLDcompetition.hardware.RobotLift;

public class DropBehavior implements RobotBehavior {

    private RobotLift robotLift;

    public DropBehavior(RobotLift robotLift) {
        this.robotLift = robotLift;
    }

    @Override
    public void run() {
        robotLift.extend();
        while (robotLift.liftBusy()) {}
    }

    @Override
    public boolean runWasSuccessful() {
        return true;
    }
}
