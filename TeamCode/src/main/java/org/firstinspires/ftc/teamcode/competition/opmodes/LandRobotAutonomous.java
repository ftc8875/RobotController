package org.firstinspires.ftc.teamcode.competition.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.competition.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.competition.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDrive;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDriveBuilder;

@Autonomous(name="Land", group = "gen1")
public class LandRobotAutonomous extends LinearOpMode {

    RobotLift robotLift;
    TankDrive driveTrain;

    DcMotor left;
    DcMotor right;

    @Override
    public void runOpMode() {
        initRobot();
        waitForStart();
        runAutonomous();
        stopRobot();
    }

    private void initRobot() {
        robotLift = new RobotLift(hardwareMap.get(DcMotor.class, "liftMotor"),
                hardwareMap.get(Servo.class, "liftServo"),
                RobotLift.Mode.LAND, RobotLift.Position.RETRACTED);
        robotLift.init();

        /*driveTrain = new TankDriveBuilder(hardwareMap)
                .withLeftMotors("l")
                .withRightMotors("r")
                .withEncoders(3640, 11.25f)
                .build();*/

        left = hardwareMap.get(DcMotor.class, "l");
        right = hardwareMap.get(DcMotor.class, "r");
    }

    private void runAutonomous() {
        robotLift.start();
        robotLift.extend();
        while (robotLift.liftBusy()) {}
        robotLift.openHook();
        sleep(1000);

        left.setPower(0.2f);
        right.setPower(-0.2f);
        sleep(1000);
        left.setPower(0);
        right.setPower(0);
        //driveTrain.drive(0,0);
        /*driveTrain.driveDistance(2, 0.2f);
        sleep(5000);*/

        //robotLift.retract();
        //while (robotLift.liftBusy()) {}

        /*try {
            wait(10000);
        } catch (InterruptedException e) {
            robotLift.emergencyStop();
            driveTrain.emergencyStop();
            return;
        }*/

        /*driveTrain.driveDistance(10, 0.5f);

        try {
            wait(10000);
        } catch (InterruptedException e) {
            robotLift.emergencyStop();
            driveTrain.emergencyStop();
            return;
        }*/
    }

    private void stopRobot() {
        robotLift.stop();
    }
}