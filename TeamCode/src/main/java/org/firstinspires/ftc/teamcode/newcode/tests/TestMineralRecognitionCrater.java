package org.firstinspires.ftc.teamcode.newcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.newcode.components.software.MineralRecognition;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@TeleOp(name="Test Mineral Recognition", group="test")
public class TestMineralRecognitionCrater extends LinearOpMode {

    private static final double MINERAL_SEARCH_TIMEOUT_SEC = 10.0;
    private static final String VUFORIA_KEY = "AYfuV+P/////AAAAGTY+YjM4LE8HtPCZ1JH9+10fYp6RxcfbBLgIXt+cznm9RWskA72GmhlAOVQ8BsZUBVNpStBMsFbapua+e3iGRC2xd9+qd0NXHlwr7QD2NxEdX7T/XnG/mEMdrax2JiRKAlWorUMamvj3JJ02zhYJosP5X8iVcMq8G0ayaFE0LtVl9sdEtpJo9Ipdxx+X+Ns/+np0sMyK3SDY+yc2H6qT85Ro8gHneO0YMMwEZsnoTE+3uA6UGrmOHTqYLrr8SW/knYuvBcTNxqfFMfZFLPZUbX1270BcPJuZWF0aFArSbtbDVstT6r6F1PF/x730MjsV1ITATw+P9YUqx3xK86u6ZsVugfFXmehkmwrp/6Ag5SRs";

    private MineralRecognition mineralRecognition;

    public void runOpMode() {
        VuforiaLocalizer.Parameters p = new VuforiaLocalizer.Parameters();
        p.vuforiaLicenseKey = VUFORIA_KEY;
        p.cameraDirection = BACK;
        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(p);

        mineralRecognition = new MineralRecognition(vuforia, hardwareMap);

        waitForStart();

        mineralRecognition.activate();

        List<Recognition> recognitions = new ArrayList<>();
        while (opModeIsActive()) {
            List<String> recognitionStrings = mineralRecognition.recognize();
            recognitions = mineralRecognition.getLastRecognitions();
            String centralRecognition = mineralRecognition.getCentralRecognitionLabel();

            telemetry.addLine("Visible: " + recognitionStrings.toString());
            telemetry.addLine("Count: " + recognitions.size());
            telemetry.addLine("CENTRAL: " + centralRecognition);
            telemetry.update();
            sleep(500);
            //opMode.telemetry.addLine(recognitions.toString());
            //opMode.telemetry.update();
        }
        telemetry.addLine("Recognition: " + recognitions.get(0).getLabel());
        telemetry.update();
        sleep(5000);
    }
}
