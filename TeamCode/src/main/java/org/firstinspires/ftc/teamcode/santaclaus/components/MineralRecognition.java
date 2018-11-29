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

    public enum MineralType {
        GOLD, SILVER
    }

    public MineralRecognition(VuforiaLocalizer vuforia, HardwareMap hardwareMap) {
        this.vuforia = vuforia;
        this.hardwareMap = hardwareMap;
        initTfod();
    }

    public void activate() {
        tfod.activate();
    }

    public List<MineralType> recognize() {
        List<Recognition> recognitions = tfod.getUpdatedRecognitions();
        List<MineralType> minerals = new ArrayList<>();
        if (recognitions == null) {
            return minerals;
        }
        for (Recognition recognition : recognitions) {
            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                minerals.add(MineralType.GOLD);
            } else if (recognition.getLabel().equals(LABEL_SILVER_MINERAL)) {
                minerals.add(MineralType.SILVER);
            }
        }
        return minerals;
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
