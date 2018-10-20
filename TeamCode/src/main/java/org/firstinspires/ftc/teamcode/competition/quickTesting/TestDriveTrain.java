package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.competition.hardware.TankDriveBuilder;
import org.firstinspires.ftc.teamcode.competition.hardware.TankDrive;

@TeleOp(name="Test Drive Train", group="Test")
public class TestDriveTrain extends OpMode {

    TankDrive tankDrive;

    @Override
    public void init() {
        tankDrive = new TankDriveBuilder(hardwareMap)
                .withLeftMotors("l")
                .withRightMotors("r")
                .build();

        tankDrive.init();
    }

    @Override
    public void start() {
        tankDrive.start();
    }

    @Override
    public void loop() {
        float d = -gamepad1.left_stick_y;
        float t = gamepad1.right_stick_x;
        tankDrive.drive(d, t);
        telemetry.addData("Left ", "%.2f", d);
        telemetry.addData("Right", "%.2f", t);
    }

    @Override
    public void stop() {
        tankDrive.stop();
    }

}
