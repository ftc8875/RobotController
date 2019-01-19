package org.firstinspires.ftc.teamcode.newcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.newcode.behavior.DropBehavior;
import org.firstinspires.ftc.teamcode.newcode.behavior.KnockGoldBehavior;
import org.firstinspires.ftc.teamcode.newcode.behavior.PhoneSwivelBehavior;
import org.firstinspires.ftc.teamcode.newcode.behavior.RobotBehavior;
import org.firstinspires.ftc.teamcode.newcode.behavior.SwivelBehavior;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.PhoneSwivel;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.RobotLift;
import org.firstinspires.ftc.teamcode.newcode.components.hardware.Drivetrain;
import org.firstinspires.ftc.teamcode.newcode.components.software.MineralRecognition;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous(name = "Knock Gold Autonomous", group = "competition")
public class CompetitionAutonomous extends LinearOpMode {

    private static final double SWIVEL_STRAIGHT = 0.40;
    private static final double SWIVEL_LEFT = 0.27;
    private static final double SWIVEL_RIGHT = 0.47;

    private Drivetrain drivetrain;
    private MineralRecognition mineralRecognition;
    private RobotLift robotLift;
    private PhoneSwivel phoneSwivel;

    private static final String VUFORIA_KEY = "AYfuV+P/////AAAAGTY+YjM4LE8HtPCZ1JH9+10fYp6RxcfbBLgIXt+cznm9RWskA72GmhlAOVQ8BsZUBVNpStBMsFbapua+e3iGRC2xd9+qd0NXHlwr7QD2NxEdX7T/XnG/mEMdrax2JiRKAlWorUMamvj3JJ02zhYJosP5X8iVcMq8G0ayaFE0LtVl9sdEtpJo9Ipdxx+X+Ns/+np0sMyK3SDY+yc2H6qT85Ro8gHneO0YMMwEZsnoTE+3uA6UGrmOHTqYLrr8SW/knYuvBcTNxqfFMfZFLPZUbX1270BcPJuZWF0aFArSbtbDVstT6r6F1PF/x730MjsV1ITATw+P9YUqx3xK86u6ZsVugfFXmehkmwrp/6Ag5SRs";

    private RobotBehavior knockMineral;
    private RobotBehavior dropRobot;
    private boolean drop = false;

    public void runOpMode() {
        initRobot();
        //selectOptions();
        waitForStart();
        run();
    }

    private void selectOptions() {
        double maxRuntime = getRuntime() + 15.0;
        while(!gamepad1.a && getRuntime() < maxRuntime) {
            if (gamepad1.right_bumper) {
                drop = !drop;
            }
            telemetry.addLine("Drop Robot: " + drop);
            telemetry.update();
        }
        telemetry.addLine("SELECTED");
        telemetry.addLine("Drop Robot: " + drop);
        telemetry.update();
    }

    private void initRobot() {
        initHardware();
        initMineralRecognition();
        initBehaviors();
        telemetry.addLine("INITIALIZED!!!!!!!!!!");
        telemetry.update();
    }

    private void initHardware() {
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "l");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "r");
        DcMotor liftMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo swivelServo = hardwareMap.get(Servo.class, "swivel");
        drivetrain = new Drivetrain(leftMotor, rightMotor);
        robotLift = new RobotLift(liftMotor);
        phoneSwivel = new PhoneSwivel(swivelServo);
    }

    private void initMineralRecognition() {
        VuforiaLocalizer.Parameters p = new VuforiaLocalizer.Parameters();
        p.vuforiaLicenseKey = VUFORIA_KEY;
        p.cameraDirection = BACK;
        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(p);
        mineralRecognition = new MineralRecognition(vuforia, hardwareMap);
    }

    private void initBehaviors() {
        SwivelBehavior swivelBehavior = new PhoneSwivelBehavior(phoneSwivel);
        knockMineral = new KnockGoldBehavior(mineralRecognition, drivetrain, this, swivelBehavior);
        dropRobot = new DropBehavior(robotLift);
    }

    private void run() {
        if (drop) {
            dropRobot.run();
        }

        //drivetrain.driveDistance(12.0, 0.2);
        sleep(1000);

        mineralRecognition.activate();
        knockMineral.run();
        telemetry.addLine("DONE!");
        telemetry.addLine("Knocked? " + knockMineral.runWasSuccessful());
        telemetry.update();

        sleep(5000);
    }
}
