package org.firstinspires.ftc.teamcode.newcode.behavior;

import org.firstinspires.ftc.teamcode.newcode.components.hardware.Drivetrain;

import static org.firstinspires.ftc.teamcode.newcode.behavior.SwivelBehavior.Position.CENTER;
import static org.firstinspires.ftc.teamcode.newcode.behavior.SwivelBehavior.Position.RIGHT;

public class RobotSwivelBehavior implements SwivelBehavior {

    private static final double TURN_DEGREES = -40;
    private static final double TURN_POWER = 0.20;

    private Drivetrain drivetrain;
    private Position desiredPosition = RIGHT;
    private double currentDegrees = positionToDegrees(RIGHT);

    public RobotSwivelBehavior(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
    public void setNextPosition(Position position) {
        desiredPosition = position;
    }

    private double positionToDegrees(Position position) {
        switch(position) {
            case LEFT:
                return TURN_DEGREES;
            case RIGHT:
                return -TURN_DEGREES;
            case CENTER:
                return 0.0;
            default:
                return 0.0;
        }
    }

    public void run() {
        double newDegrees = positionToDegrees(desiredPosition);
        double turnDegrees = newDegrees - currentDegrees;
        drivetrain.turn(turnDegrees, TURN_POWER);

        while(drivetrain.isBusy()) {}

        currentDegrees = newDegrees;
    }

    @Override
    public boolean runWasSuccessful() {
        return true;
    }
}
