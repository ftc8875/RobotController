package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public class TankDriveEncoders extends TankDriveDecorator {

    private float encoderCountsPerInch;
    private float turnDiameter;

    TankDriveEncoders(TankDrivePlain tankDrive, float encoderCountsPerInch, float turnDiameter) {
        super(tankDrive);
        this.encoderCountsPerInch = encoderCountsPerInch;
        this.turnDiameter = turnDiameter;
        tankDrivePlain.setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveDistance(float inches, float power) {
        tankDrivePlain.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        setTargetPositions(getPositionFromInches(inches));
        tankDrivePlain.driveTank(power, power);
    }

    public void rotate(float degrees, float power) {
        tankDrivePlain.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        // TODO finish this
    }

    private void setTargetPositions(int position, DcMotor ... motors) {
        for (DcMotor motor : motors) {
            motor.setTargetPosition(position);
        }
    }

    private int getPositionFromInches(float inches) {
        return (int)(inches * encoderCountsPerInch);
    }

    @Override
    public void init() {
        tankDrivePlain.init();
    }

    @Override
    public void start() {
        tankDrivePlain.start();
    }

    @Override
    public void stop() {
        tankDrivePlain.stop();
    }

    @Override
    public void emergencyStop() {
        tankDrivePlain.emergencyStop();
    }
}
