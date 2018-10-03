package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.competition.TankDrive;

@Autonomous(name="Tank Drive no Controller", group="Test")
public class TestTankDrive extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotor l = hardwareMap.get(DcMotor.class, "l");
        DcMotor r = hardwareMap.get(DcMotor.class, "r");

        TankDrive tankDrive = new TankDrive().leftMotors(l).rightMotors(r);
        tankDrive.init();

        waitForStart();

        tankDrive.start();

        tankDrive.driveTurn(0.9f ,0.0f);
        sleep(1000);

        tankDrive.stop();

    }
}
