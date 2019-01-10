package org.firstinspires.ftc.teamcode.newcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.newcode.components.Drivetrain;
import org.firstinspires.ftc.teamcode.newcode.components.MineralRecognition;

import java.util.ArrayList;
import java.util.List;

public class KnockGoldBehavior implements RobotBehavior {

    private static final double BUMP_INCHES = 48.0;
    private static final double BUMP_POWER = 0.25;
    private static final double TURN_DEGREES = -40;
    private static final double TURN_POWER = 0.20;

    private static final double MINERAL_SEARCH_TIMEOUT_SEC = 7.500;

    private Drivetrain drivetrain;
    private MineralRecognition mineralRecognition;
    private LinearOpMode opMode;
    private boolean goldKnocked = false;
    private SwivelBehavior swivelBehavior;

    public KnockGoldBehavior(MineralRecognition mineralRecognition, Drivetrain drivetrain, LinearOpMode opMode, SwivelBehavior swivelBehavior) {
        this.mineralRecognition = mineralRecognition;
        this.drivetrain = drivetrain;
        this.opMode = opMode;
        this.swivelBehavior = swivelBehavior;
    }

    private void checkForGold(String positionName) {
        opMode.telemetry.addLine("Checking " + positionName);
        opMode.telemetry.update();
        if (visibleMineralIsGold()) {
            opMode.telemetry.addLine("GOLD! " + positionName);
            opMode.telemetry.update();
            bump();
            goldKnocked = true;
        }
    }

    // NEW AND IMPROVED!
    protected void swivel(SwivelBehavior.Position position) {
        swivelBehavior.setNextPosition(position);
        swivelBehavior.run();
    }

    protected void swivel(double degrees) {
        drivetrain.turn(degrees, TURN_POWER);
        opMode.telemetry.addLine("Turning...");
        opMode.telemetry.update();

        while(drivetrain.isBusy() && opMode.opModeIsActive()) {}

        opMode.telemetry.addLine("Done turning");
        opMode.telemetry.update();

        opMode.sleep(1000);
    }

    public void run() {
        checkForGold("Center");
        if (goldKnocked) {
            return;
        }
        swivel(TURN_DEGREES);

        checkForGold("Left");
        if (goldKnocked) {
            return;
        }
        swivel(-2 * TURN_DEGREES);

        bump();

    }

    private void bump() {
        drivetrain.driveDistance(BUMP_INCHES, BUMP_POWER);
        while(drivetrain.isBusy() && opMode.opModeIsActive()) {}
    }

    private String getVisibleMineral() {
        List<Recognition> recognitions = new ArrayList<>();
        double currentTime = opMode.getRuntime();
        double maxTime = currentTime + MINERAL_SEARCH_TIMEOUT_SEC;
        while (recognitions.size() != 1 && opMode.opModeIsActive()) {
            List<String> recognitionNames = mineralRecognition.recognize();
            recognitions = mineralRecognition.getLastRecognitions();

            currentTime = opMode.getRuntime();
            if (currentTime > maxTime) {
                return "";
            }
            opMode.telemetry.addLine("Visible in range: " + recognitionNames.toString());
            opMode.telemetry.addLine("Number in range: " + recognitions.size());
            opMode.telemetry.update();
            opMode.sleep(500);
            //opMode.telemetry.addLine(recognitions.toString());
            //opMode.telemetry.update();
        }
        return recognitions.get(0).getLabel();
    }

    private boolean visibleMineralIsGold() {
        String visibleMineral = getVisibleMineral();
        opMode.telemetry.addLine("Visible: " + visibleMineral);
        opMode.telemetry.addLine("Gold? " + visibleMineral.equals("Gold Mineral"));
        return visibleMineral.equals("Gold Mineral");
    }

    public boolean runWasSuccessful() {
        return goldKnocked;
    }
}
