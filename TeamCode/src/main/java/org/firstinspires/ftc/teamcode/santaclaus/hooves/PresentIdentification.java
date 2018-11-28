package org.firstinspires.ftc.teamcode.santaclaus.hooves;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class PresentIdentification {

    private VuforiaLocalizer vuforia;

    public enum MineralType {
        GOLD, SILVER, UNKNOWN
    }

    public PresentIdentification(VuforiaLocalizer vuforia) {
        this.vuforia = vuforia;
    }

    public MineralType recognize() {
        // TODO implement
        return MineralType.UNKNOWN;
    }
}
