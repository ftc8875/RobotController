package org.firstinspires.ftc.teamcode.competition.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.competition.hardware.RobotLift;

public class LandRobotAutonomous extends LinearOpMode {

    RobotLift robotLift;

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
    }

    private void runAutonomous() {
        robotLift.start();
        robotLift.extend();
        robotLift.openHook();
        robotLift.retract();
    }

    private void stopRobot() {
        robotLift.stop();
    }
}
