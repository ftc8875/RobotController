package org.firstinspires.ftc.teamcode.competition.hardware;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.teamcode.santaclaus.hooves.Rudolph;

public class TankDriveVuforia extends TankDriveEncoders {

    private Rudolph rudolph;
    private TankDrivePlain tankDrivePlain;

    TankDriveVuforia(TankDrivePlain tankDrivePlain, float encoderCountsPerInch, float turnDiameter, Rudolph rudolphInstance) {
        super(tankDrivePlain, encoderCountsPerInch, turnDiameter);
        this.tankDrivePlain = tankDrivePlain;
        this.rudolph = rudolphInstance;
    }

    public void driveToPosition(OpenGLMatrix position) {

    }

    @Override
    public void init() {
        this.rudolph.init();
        super.init();
    }

    @Override
    public void start() {
        this.rudolph.start();
        super.start();
    }

    @Override
    public void stop() {
        this.rudolph.stop();
        super.stop();
    }

    @Override
    public void emergencyStop() {
        this.rudolph.emergencyStop();
        super.stop();
    }
}
