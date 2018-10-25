package org.firstinspires.ftc.teamcode.competition.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.competition.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDrive;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDriveBuilder;

@TeleOp(name = "Gen One", group = "competition")
public class GenOneTeleOp extends OpMode {

    TankDrive tankDrive;
    RobotLift robotLift;

    @Override
    public void init() {
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        Servo liftServo = hardwareMap.get(Servo.class, "liftServo");

        tankDrive = new TankDriveBuilder(hardwareMap)
                .withLeftMotors("l")
                .withRightMotors("r")
                .build();

        tankDrive.init();

        robotLift = new RobotLift(liftMotor, liftServo, RobotLift.Mode.LIFT);
        robotLift.init();
    }

    @Override
    public void start() {
        tankDrive.start();
        robotLift.start();
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {

    }
}
