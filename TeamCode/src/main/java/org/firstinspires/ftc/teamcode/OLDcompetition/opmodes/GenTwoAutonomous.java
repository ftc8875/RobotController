package org.firstinspires.ftc.teamcode.OLDcompetition.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.OLDcompetition.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.OLDcompetition.hardware.TankDriveBuilder;

public class GenTwoAutonomous extends LinearOpMode {

    DriveTrain driveTrain;
    NormalizedColorSensor colorSensor;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        run();
    }

    private void initialize() {
        driveTrain = new TankDriveBuilder(hardwareMap)
                .withRightMotors("rightMotor")
                .withLeftMotors("leftMotor")
                .withEncoders(1160, 11.25f)
                .build();
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorSensor");
        telemetry.addLine("Initialized!");
    }

    private void run() {
        driveToSampleMinerals();
        sample();
        driveToSafeZone();
        dropTeamMarker();
        park();
    }

    private void driveToSampleMinerals() {
        
    }
    private void sample() {}
    private void driveToSafeZone() {}
    private void dropTeamMarker() {}
    private void park() {}

}
