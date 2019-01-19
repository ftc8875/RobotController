package org.firstinspires.ftc.teamcode.newcode.components.software;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

    public String recognizeOne() {
        updateRecognitions();
        Recognition centralRecognition = getCentralRecognition(lastRecognitions);
        if (centralRecognition == null) {
            return "";
        }
        return centralRecognition.getLabel();
    }

    public Recognition getCentralRecognition() {
        return getCentralRecognition(lastRecognitions);
    }

    public String getCentralRecognitionLabel() {
        Recognition centralRecognition = getCentralRecognition();
        if (centralRecognition == null) {
            return "";
        }
        return centralRecognition.getLabel();
    }

    private List<Recognition> updateRecognitions() {
        List<Recognition> recognitions = tfod.getUpdatedRecognitions();
        if (recognitions == null) {
            recognitions = new ArrayList<>();
        }

        recognitions = removeOutOfRangeRecognitions(recognitions);

        if (recognitions != null) {
            lastRecognitions = recognitions;
        }
        return recognitions;
    }

    private Recognition getCentralRecognition(List<Recognition> recognitions) {
        if (recognitions.size() == 0) {
            return null;
        }
        double center = 0.5;
        List<Double> offsets = new ArrayList<>();
        for (Recognition r : recognitions) {
             double location = r.getRight() / r.getImageWidth();
            offsets.add(Math.abs(location - center));
        }
        // Get the index of the smallest offset. Return the corresponding Recognition.
        return recognitions.get(offsets.indexOf(Collections.min(offsets)));

    }

    // removes all minerals that are too high (i.e. probably in the crater)
    private List<Recognition> removeOutOfRangeRecognitions(List<Recognition> recognitions) {
        if (recognitions.size() == 0) {
            return recognitions;
        }
        List<Double> locations = new ArrayList<>();
        for (Recognition r : recognitions) {
            double location = r.getTop() / r.getImageHeight();
            locations.add(location);
        }
        double minLocation = Collections.min(locations);
        for (int i=0; i<recognitions.size(); i++) {
            if (locations.get(i) < minLocation - 0.05) {
                recognitions.remove(i);
            }
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
