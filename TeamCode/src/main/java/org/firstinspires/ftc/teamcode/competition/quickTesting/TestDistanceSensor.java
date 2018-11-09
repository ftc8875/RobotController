package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Disabled
@Autonomous(name = "Test Distance Sensor", group = "test")
public class TestDistanceSensor extends OpMode {

    DistanceSensor distanceSensor;

    @Override
    public void init() {
        distanceSensor = hardwareMap.get(DistanceSensor.class, "color-distance");
    }

    @Override
    public void loop() {
        double distance = distanceSensor.getDistance(DistanceUnit.INCH);
        if (distance == distanceSensor.distanceOutOfRange) {
            telemetry.addData("Distance", "OUT OF RANGE");
        } else {
            telemetry.addData("Distance", "%.2f", distance);
        }
    }
}
