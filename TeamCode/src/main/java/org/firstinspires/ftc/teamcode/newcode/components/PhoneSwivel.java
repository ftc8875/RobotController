package org.firstinspires.ftc.teamcode.newcode.components;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.newcode.opmodes.SwivelBehavior;

public class PhoneSwivel {

    private Servo servo;
    private final double straightPos;
    private final double leftPos;
    private final double rightPos;

    public PhoneSwivel(Servo servo, double straightPos, double leftPos, double rightPos) {
        this.servo = servo;
        this.straightPos = straightPos;
        this.leftPos = leftPos;
        this.rightPos = rightPos;
    }

    /**
     * Swivels the phone swivel servo to the specified position
     * @param pos - the position
     */
    public void swivel(double pos) {
        servo.setPosition(pos);
    }


    /**
     * Swivels the phone swivel servo to the position
     * @param position - the position--LEFT, CENTER, RIGHT
     */
    public void swivel(SwivelBehavior.Position position) {
        switch(position) {
            case LEFT:
                swivel(leftPos);
                break;
            case RIGHT:
                swivel(rightPos);
                break;
            case CENTER:
                swivel(straightPos);
                break;
        }
    }
}
