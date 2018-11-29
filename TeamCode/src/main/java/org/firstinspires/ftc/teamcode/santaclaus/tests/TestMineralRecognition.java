package org.firstinspires.ftc.teamcode.santaclaus.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.santaclaus.components.MineralRecognition;
import org.firstinspires.ftc.teamcode.santaclaus.components.Vuforia;
import org.firstinspires.ftc.teamcode.santaclaus.components.VuforiaBuilder;

import java.util.List;

@TeleOp(name="test mineral recognition",group="test")
public class TestMineralRecognition extends OpMode {

    MineralRecognition mineralRecognition;
    VuforiaLocalizer vuforia;

    private static final String VUFORIA_KEY = "AYfuV+P/////AAAAGTY+YjM4LE8HtPCZ1JH9+10fYp6RxcfbBLgIXt+cznm9RWskA72GmhlAOVQ8BsZUBVNpStBMsFbapua+e3iGRC2xd9+qd0NXHlwr7QD2NxEdX7T/XnG/mEMdrax2JiRKAlWorUMamvj3JJ02zhYJosP5X8iVcMq8G0ayaFE0LtVl9sdEtpJo9Ipdxx+X+Ns/+np0sMyK3SDY+yc2H6qT85Ro8gHneO0YMMwEZsnoTE+3uA6UGrmOHTqYLrr8SW/knYuvBcTNxqfFMfZFLPZUbX1270BcPJuZWF0aFArSbtbDVstT6r6F1PF/x730MjsV1ITATw+P9YUqx3xK86u6ZsVugfFXmehkmwrp/6Ag5SRs";

    public void init() {
        OpenGLMatrix identity = OpenGLMatrix.identityMatrix();
        Vuforia v = new VuforiaBuilder(VUFORIA_KEY)
                .withCameraDirection(VuforiaLocalizer.CameraDirection.BACK)
                .withCameraLocation(identity)
                .withVuforiaAssetName("RoverRuckus")
                .withVuforiaTrackableNames("one", "two", "three", "four")
                .withVuforiaTrackableLocations(identity, identity, identity, identity)
                .withCameraMonitoring(this.hardwareMap)
                .build();
        v.init();
        vuforia = v.getVuforiaLocalizer();
        mineralRecognition = new MineralRecognition(vuforia, hardwareMap);
    }

    public void loop() {
        mineralRecognition.activate();
        telemetry.addLine("Minerals Found:");
        List<MineralRecognition.MineralType> minerals = mineralRecognition.recognize();
        for (MineralRecognition.MineralType mineral : minerals) {
            telemetry.addLine(mineral.toString());
        }
        telemetry.update();
    }
}
