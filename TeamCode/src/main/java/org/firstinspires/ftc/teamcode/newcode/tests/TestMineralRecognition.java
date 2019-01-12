package org.firstinspires.ftc.teamcode.newcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.newcode.components.software.MineralRecognition;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Disabled // broken
@TeleOp(name="Test Mineral Recognition",group="test")
public class TestMineralRecognition extends OpMode {

    MineralRecognition mineralRecognition;
    VuforiaLocalizer vuforia;

    private static final String VUFORIA_KEY = "AYfuV+P/////AAAAGTY+YjM4LE8HtPCZ1JH9+10fYp6RxcfbBLgIXt+cznm9RWskA72GmhlAOVQ8BsZUBVNpStBMsFbapua+e3iGRC2xd9+qd0NXHlwr7QD2NxEdX7T/XnG/mEMdrax2JiRKAlWorUMamvj3JJ02zhYJosP5X8iVcMq8G0ayaFE0LtVl9sdEtpJo9Ipdxx+X+Ns/+np0sMyK3SDY+yc2H6qT85Ro8gHneO0YMMwEZsnoTE+3uA6UGrmOHTqYLrr8SW/knYuvBcTNxqfFMfZFLPZUbX1270BcPJuZWF0aFArSbtbDVstT6r6F1PF/x730MjsV1ITATw+P9YUqx3xK86u6ZsVugfFXmehkmwrp/6Ag5SRs";

    public void init() {
        VuforiaLocalizer.Parameters p = new VuforiaLocalizer.Parameters();
        p.vuforiaLicenseKey = VUFORIA_KEY;
        p.cameraDirection = BACK;
        vuforia = ClassFactory.getInstance().createVuforia(p);

        mineralRecognition = new MineralRecognition(vuforia, hardwareMap);
    }

    public void loop() {
        mineralRecognition.activate();
        telemetry.addLine("Minerals Found:");
        List<String> minerals = mineralRecognition.recognize();
        List<Recognition> recognitions = mineralRecognition.getLastRecognitions();
        if (minerals.size() != recognitions.size()) {
            return;
        }

        for (int i = 0; i < minerals.size(); i++) {
            String mineral = minerals.get(i);
            Recognition r = recognitions.get(i);
            telemetry.addData(mineral, String.format("%.3f, %.3f", r.getRight() / r.getImageWidth(), r.getTop() / r.getImageHeight()));
        }
        telemetry.update();
    }
}
