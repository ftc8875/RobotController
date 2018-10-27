package org.firstinspires.ftc.teamcode.competition.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.competition.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.competition.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDrive;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDriveBuilder;

@Autonomous(name="Land and Crater", group = "gen1")
public class LandRobotAutonomous extends LinearOpMode {

    RobotLift robotLift;
    TankDrive driveTrain;

    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();
        runAutonomous();
        stopRobot();
    }

    private void initRobot() {
        robotLift = new RobotLift(hardwareMap.get(DcMotor.class, "lift-motor"),
                hardwareMap.get(Servo.class, "grip-servo"),
                RobotLift.Mode.LAND);
        robotLift.init();

        driveTrain = new TankDriveBuilder(hardwareMap)
                .withLeftMotors("l")
                .withRightMotors("r")
                .withEncoders(1344, 11.25f)
                .build();
    }

    private void runAutonomous() {
        robotLift.start();
        robotLift.extend();
        robotLift.openHook();
        robotLift.retract();

        try {
            wait(10000);
        } catch (InterruptedException e) {
            robotLift.emergencyStop();
            driveTrain.emergencyStop();
            return;
        }

        driveTrain.driveDistance(10, 0.5f);

        try {
            wait(10000);
        } catch (InterruptedException e) {
            robotLift.emergencyStop();
            driveTrain.emergencyStop();
            return;
        }
    }

    private void stopRobot() {
        robotLift.stop();
    }
}
