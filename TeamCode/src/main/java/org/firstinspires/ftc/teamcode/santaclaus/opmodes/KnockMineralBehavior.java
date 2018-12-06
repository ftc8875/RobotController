package org.firstinspires.ftc.teamcode.santaclaus.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.santaclaus.components.Drivetrain;
import org.firstinspires.ftc.teamcode.santaclaus.components.MineralRecognition;

import java.util.ArrayList;
import java.util.List;

public class KnockMineralBehavior implements RobotBehavior {

    private Drivetrain drivetrain;
    private MineralRecognition mineralRecognition;
    private LinearOpMode opMode;

    public KnockMineralBehavior(MineralRecognition mineralRecognition, Drivetrain drivetrain, LinearOpMode opMode) {
        this.mineralRecognition = mineralRecognition;
        this.drivetrain = drivetrain;
        this.opMode = opMode;
    }

    public void run() {
        opMode.telemetry.addLine("Checking center......");
        opMode.telemetry.update();
        if (visibleMineralIsGold()) {
            opMode.telemetry.addLine("GOLD! Center...");
            opMode.telemetry.update();
            bump();
            return;
        }

        drivetrain.turn(60);
        opMode.telemetry.addLine("TURNING!");
        opMode.telemetry.update();
        opMode.sleep(5000);
        //while(drivetrain.isBusy() && opMode.opModeIsActive()) {}

        opMode.telemetry.addLine("Checking left........");
        opMode.telemetry.update();
        if (visibleMineralIsGold()) {
            opMode.telemetry.addLine("GOLD! Left...");
            opMode.telemetry.update();
            bump();
            return;
        }

        drivetrain.turn(-120);
        opMode.telemetry.addLine("TURNING!");
        opMode.telemetry.update();
        opMode.sleep(5000);
        //while(drivetrain.isBusy() && opMode.opModeIsActive()) {}

        opMode.telemetry.addLine("Checking right.......");
        opMode.telemetry.update();
        if (visibleMineralIsGold()) {
            opMode.telemetry.addLine("GOLD! Right...");
            opMode.telemetry.update();
            bump();
        }
    }

    private void bump() {
        drivetrain.driveDistance(2);
        while(drivetrain.isBusy()) {}
        drivetrain.driveDistance(-2);
        while(drivetrain.isBusy()) {}
    }

    private String getVisibleMineral() {
        List<String> recognitions = new ArrayList<>();
        while (recognitions.size() != 1 && opMode.opModeIsActive()) {
            recognitions = mineralRecognition.recognize();
            //opMode.telemetry.addLine(recognitions.toString());
            //opMode.telemetry.update();
        }
        return recognitions.get(0);
    }

    private boolean visibleMineralIsGold() {
        String visibleMineral = getVisibleMineral();
        opMode.telemetry.addLine("Visible: " + visibleMineral);
        opMode.telemetry.addLine("Gold? " + visibleMineral.equals("Gold Mineral"));
        return visibleMineral.equals("Gold Mineral");
    }
}
