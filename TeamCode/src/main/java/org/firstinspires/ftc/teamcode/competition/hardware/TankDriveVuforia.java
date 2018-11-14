package org.firstinspires.ftc.teamcode.competition.hardware;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.teamcode.general.vuforia.Vuforia;

public class TankDriveVuforia extends TankDriveEncoders {

    private Vuforia vuforia;
    private TankDrivePlain tankDrivePlain;

    TankDriveVuforia(TankDrivePlain tankDrivePlain, float encoderCountsPerInch, float turnDiameter, Vuforia vuforiaInstance) {
        super(tankDrivePlain, encoderCountsPerInch, turnDiameter);
        this.tankDrivePlain = tankDrivePlain;
        this.vuforia = vuforiaInstance;
    }

    public void driveToPosition(OpenGLMatrix position) {

    }

    @Override
    public void init() {
        this.vuforia.init();
        super.init();
    }

    @Override
    public void start() {
        this.vuforia.start();
        super.start();
    }

    @Override
    public void stop() {
        this.vuforia.stop();
        super.stop();
    }

    @Override
    public void emergencyStop() {
        this.vuforia.emergencyStop();
        super.stop();
    }
}
