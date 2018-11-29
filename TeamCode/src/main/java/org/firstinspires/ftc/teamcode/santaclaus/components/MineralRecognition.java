package org.firstinspires.ftc.teamcode.santaclaus.components;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class MineralRecognition {

    private VuforiaLocalizer vuforia;

    public enum MineralType {
        GOLD, SILVER, UNKNOWN
    }

    public MineralRecognition(VuforiaLocalizer vuforia) {
        this.vuforia = vuforia;
    }

    public MineralType recognize() {
        // TODO implement
        return MineralType.UNKNOWN;
    }
}
