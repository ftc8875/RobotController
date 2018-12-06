package org.firstinspires.ftc.teamcode.santaclaus.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class MineralRecognition {

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private HardwareMap hardwareMap;

    private List<Recognition> lastRecognitions = new ArrayList<>();

    public MineralRecognition(VuforiaLocalizer vuforia, HardwareMap hardwareMap) {
        this.vuforia = vuforia;
        this.hardwareMap = hardwareMap;
        initTfod();
    }

    public void activate() {
        tfod.activate();
    }

    public List<Recognition> getLastRecognitions() {
        return lastRecognitions;
    }

    public List<String> recognize() {
        updateRecognitions();
        return recognitionsToStrings(lastRecognitions);
    }

    private List<Recognition> updateRecognitions() {
        List<Recognition> recognitions = tfod.getUpdatedRecognitions();
        if (recognitions != null) {
            lastRecognitions = recognitions;
        }
        return recognitions;
    }

    private List<String> recognitionsToStrings(List<Recognition> recognitions) {
        List<String> strings = new ArrayList<>();
        for (Recognition r : recognitions) {
            strings.add(r.getLabel());
        }
        return strings;
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
