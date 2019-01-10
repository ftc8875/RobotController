package org.firstinspires.ftc.teamcode.newcode.opmodes;

import org.firstinspires.ftc.teamcode.newcode.components.Drivetrain;
import org.firstinspires.ftc.teamcode.newcode.components.PhoneSwivel;

public class DriveTrainSwivelBehavior implements SwivelBehavior {

    private Drivetrain drivetrain;
    private double turnDegrees;
    private double turnPower;

    public DriveTrainSwivelBehavior(Drivetrain drivetrain, double turnDegrees, double turnPower) {
        this.drivetrain = drivetrain;
        this.turnDegrees = turnDegrees;
        this.turnPower = turnPower;
    }

    public void setNextPosition(SwivelBehavior.Position position) {

    }

    public boolean runWasSuccessful() {
        return true;
    }

    public void run() {}
}
