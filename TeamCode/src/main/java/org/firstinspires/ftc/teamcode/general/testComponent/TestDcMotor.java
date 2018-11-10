package org.firstinspires.ftc.teamcode.general.testComponent;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "Test DC Motor", group = "test")
public class TestDcMotor extends LinearOpMode {

    DcMotor motor;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        telemetry.addLine("Initialized"); telemetry.update();
        waitForStart();

        for (float i=0; i<1; i+=0.01) {
            setMotorPower(i);
        }


        for (float i=1; i>0; i-=0.01) {
            setMotorPower(i);
        }

        for (float i=0; i>-1; i-=0.01) {
            setMotorPower(i);
        }

        for (float i=-1; i<0; i+=0.01) {
            setMotorPower(i);
        }

        telemetry.addLine("Done"); telemetry.update();

    }

    private void setMotorPower(double power) {
        telemetry.addData("Power", "%.2f", power); telemetry.update();
        motor.setPower(power);
        sleep(50);
    }
}
