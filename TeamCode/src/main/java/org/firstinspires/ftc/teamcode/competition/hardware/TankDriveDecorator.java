package org.firstinspires.ftc.teamcode.competition.hardware;

public abstract class TankDriveDecorator implements TankDrive {

    protected TankDrivePlain tankDrivePlain;

    public TankDriveDecorator(TankDrivePlain tankDrivePlain) {
        this.tankDrivePlain = tankDrivePlain;
    }

    public void drive(float power, float turn) {
        tankDrivePlain.drive(power, turn);
    }

    public void driveTank(float left, float right) {
        tankDrivePlain.drive(left, right);
    }
}
