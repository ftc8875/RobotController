package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Disabled
@Autonomous(name = "Test Color Sensor", group = "test")
public class TestColorSensor extends OpMode {

    NormalizedColorSensor colorSensor;

    @Override
    public void init() {
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "color-distance");
    }

    @Override
    public void loop() {
        NormalizedRGBA color = colorSensor.getNormalizedColors();
        telemetry.addData("r", "%.2f", color.red);
        telemetry.addData("g", "%.2f", color.green);
        telemetry.addData("b", "%.2f", color.blue);
        telemetry.addData("a", "%.2f", color.alpha);
    }
}
