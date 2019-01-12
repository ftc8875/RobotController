package org.firstinspires.ftc.teamcode.OLDcompetition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OLDgeneral.dsopts.DriverStationOptions;

@Disabled
@TeleOp(name="Test DS Options", group = "test")
public class TestDSOptions extends OpMode {

    DriverStationOptions options;

    public void init() {
        options = new DriverStationOptions();
        options.addDiscreteOption("Alliance", "Blue", "Red");
        options.addDiscreteOption("Side", "Up", "Down");
    }

    public void init_loop() {
        if (gamepad1.dpad_up) {
            options.selectPreviousOption();
        } else if (gamepad1.dpad_down) {
            options.selectNextOption();
        } else if (gamepad1.dpad_left) {
            options.decrementCurrentOption();
        } else if (gamepad1.dpad_right) {
            options.incrementCurrentOption();
        }
        telemetry.addLine(options.output());
    }

    public void start() {}

    public void loop() {}

}
