package org.firstinspires.ftc.teamcode.competition.hardware;

public class MotorNotDefinedException extends RuntimeException {

    public String toString() {
        return "Drivetrain motors not defined.";
    }
}
