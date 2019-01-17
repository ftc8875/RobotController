package org.firstinspires.ftc.teamcode.newcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Test Swivel", group = "test")
public class TestSwivelServo extends OpMode {

    private Servo servo;

    public void init() {
        servo = hardwareMap.get(Servo.class, "swivel");
        servo.setPosition(0.5);
    }

    public void loop() {
        if (gamepad1.dpad_left) {
            servo.setPosition(servo.getPosition() - 0.01);
        } else if (gamepad1.dpad_right) {
            servo.setPosition(servo.getPosition() + 0.01);
        }
        telemetry.addData("Servo Position", "%.2f", servo.getPosition());
    }

}
