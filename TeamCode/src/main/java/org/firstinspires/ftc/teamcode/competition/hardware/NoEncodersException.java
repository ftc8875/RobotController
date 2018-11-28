package org.firstinspires.ftc.teamcode.competition.hardware;

public class NoEncodersException extends RuntimeException {

    @Override
    public String toString() {
        return "Encoders must be enabled to use Rudolph.";
    }
}
