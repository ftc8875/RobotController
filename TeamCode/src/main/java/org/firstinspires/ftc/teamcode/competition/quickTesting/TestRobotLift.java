package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.competition.hardware.RobotLift;

@Autonomous(name="Test Robot Lift", group="Test")
public class TestRobotLift extends LinearOpMode {

    RobotLift robotLift;

    DcMotor m;
    Servo s;

    public void runOpMode() {
        m = hardwareMap.get(DcMotor.class, "motor");
        s = hardwareMap.get(Servo.class, "servo");

        robotLift = new RobotLift(m, s);
        robotLift.init();

        telemetry.addLine("Initialized");
        updateTelemetry(telemetry);

        waitForStart();

        telemetry.addLine("STARTED");
        updateTelemetry(telemetry);

        robotLift.lift();

        telemetry.addLine("Done");
        updateTelemetry(telemetry);

        sleep(1000);

    }

}
