package org.firstinspires.ftc.teamcode.competition.hardware;

public interface TankDrive extends DriveTrain {

    void driveTank(float left, float right);
    void driveDistance(float inches, float power);
    void rotate(float degrees, float power);

}
