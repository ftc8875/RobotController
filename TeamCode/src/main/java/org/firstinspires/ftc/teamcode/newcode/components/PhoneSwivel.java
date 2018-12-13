package org.firstinspires.ftc.teamcode.newcode.components;

import android.provider.ContactsContract;

import com.qualcomm.robotcore.hardware.Servo;

public class PhoneSwivel {

    private Servo servo;
    private double straightPos;
    private double minPos;
    private double maxPos;
    private double minAngle;
    private double maxAngle;
    private double currentAngle;

    public enum Position {
        LEFT, CENTER, RIGHT
    }

    public PhoneSwivel(Servo servo, double straightPos, double minPos, double minAngle, double maxPos, double maxAngle) {
        this.servo = servo;
        this.straightPos = straightPos;
        this.minPos = minPos;
        this.minAngle = minAngle;
        this.maxPos = maxPos;
        this.maxAngle = maxAngle;
        swivel(0);
    }

    public void swivel(double angle) {
        angle = setCurrentAngle(angle);
        servo.setPosition(angleToPos(angle));
    }

    public void swivel(Position position) {
        switch(position) {
            case LEFT:
                swivel(minAngle);
                break;
            case RIGHT:
                swivel(maxAngle);
                break;
            case CENTER:
                swivel(0);
                break;
        }
    }

    private double angleToPos(double angle) {
        return 0.0;
    }

    private double posToAngle(double pos) {
        return 0.0;
    }

    private double setCurrentAngle(double angle) {
        if (angle < minAngle) {
            angle = minAngle;
        } else if (angle > maxAngle) {
            angle = maxAngle;
        }
        currentAngle = angle;
        return angle;
    }
}
