package org.firstinspires.ftc.teamcode.newcode.behavior;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.newcode.components.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.newcode.components.software.MineralRecognition;

public class KnockGoldBehavior implements RobotBehavior {

    private static final double MINERAL_SEARCH_TIMEOUT_SEC = 7.500;

    private Drivetrain drivetrain;
    private MineralRecognition mineralRecognition;
    private LinearOpMode opMode;
    private boolean goldKnocked = false;
    private SwivelBehavior swivelBehavior;
    private RobotSwivelBehavior robotSwivelBehavior;
    private RobotBehavior bumpBehavior;

    /**
     * Creates new KnockGoldBehavior with default drivetrain robot swivel behavior.
     * @param mineralRecognition
     * @param drivetrain
     * @param opMode
     */
    public KnockGoldBehavior(MineralRecognition mineralRecognition, Drivetrain drivetrain, LinearOpMode opMode) {
        this(mineralRecognition, drivetrain, opMode, new RobotSwivelBehavior(drivetrain));
    }

    /**
     * Creates new KnockGoldBehavior with custom swivel behavior. Good when using PhoneSwivel to
     * test for minerals, so entire robot doesn't move.
     * @param mineralRecognition
     * @param drivetrain
     * @param opMode
     * @param swivelBehavior
     */
    public KnockGoldBehavior(MineralRecognition mineralRecognition, Drivetrain drivetrain, LinearOpMode opMode, SwivelBehavior swivelBehavior) {
        this.mineralRecognition = mineralRecognition;
        this.drivetrain = drivetrain;
        this.opMode = opMode;
        this.swivelBehavior = swivelBehavior;
        if (swivelBehavior instanceof RobotSwivelBehavior) {
            this.robotSwivelBehavior = (RobotSwivelBehavior) swivelBehavior;
        } else {
            this.robotSwivelBehavior = new RobotSwivelBehavior(drivetrain);
        }
        this.bumpBehavior = new BumpBehavior(drivetrain, opMode);
    }

    private void checkForGold(SwivelBehavior.Position position) {
        opMode.telemetry.addLine("Checking " + position.toString());
        opMode.telemetry.update();
        if (visibleMineralIsGold()) {
            opMode.telemetry.addLine("GOLD! " + position.toString());
            opMode.telemetry.update();
            swivelRobot(position);
            bump();
            goldKnocked = true;
        }
    }

    // NEW AND IMPROVED!
    private void swivel(SwivelBehavior.Position position) {
        swivelBehavior.setNextPosition(position);
        swivelBehavior.run();
    }

    private void swivelRobot(SwivelBehavior.Position position) {
        robotSwivelBehavior.setNextPosition(position);
        robotSwivelBehavior.run();
    }

    public void run() {
        checkForGold(SwivelBehavior.Position.CENTER);
        if (goldKnocked) {
            return;
        }
        swivel(SwivelBehavior.Position.LEFT);

        checkForGold(SwivelBehavior.Position.LEFT);
        if (goldKnocked) {
            return;
        }
        swivel(SwivelBehavior.Position.RIGHT);

        bump();

    }

    private void bump() {
        bumpBehavior.run();
    }

    private boolean visibleMineralIsGold() {
        String centralMineral = "";
        double maxRuntime = opMode.getRuntime() + MINERAL_SEARCH_TIMEOUT_SEC;
        while (centralMineral.equals("") && opMode.opModeIsActive()) {
            if (opMode.getRuntime() > maxRuntime) {
                return false;
            }
            centralMineral = mineralRecognition.recognizeOne();
        }
        opMode.telemetry.addLine("Central: " + centralMineral);
        opMode.telemetry.addLine("Gold? " + centralMineral.equals("Gold Mineral"));
        opMode.telemetry.update();
        return centralMineral.equals("Gold Mineral");
    }

    public boolean runWasSuccessful() {
        return goldKnocked;
    }
}
