package org.firstinspires.ftc.teamcode.OLDcompetition.hardware;

public class NoEncodersException extends RuntimeException {

    @Override
    public String toString() {
        return "Encoders must be enabled to use Vuforia.";
    }
}
