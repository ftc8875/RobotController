package org.firstinspires.ftc.teamcode.newcode.components.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.newcode.behavior.SwivelBehavior;

public class PhoneSwivel {

    private static final double STRAIGHT_POS = 0.35;
    private static final double LEFT_POS = 0.15;
    private static final double RIGHT_POS = 0.48;

    private Servo servo;
    private final double straightPos;
    private final double leftPos;
    private final double rightPos;
    private SwivelBehavior.Position currentPos;

    public PhoneSwivel(Servo servo) {
        this(servo, STRAIGHT_POS, LEFT_POS, RIGHT_POS);
    }

    public PhoneSwivel(Servo servo, double straightPos, double leftPos, double rightPos) {
        this.servo = servo;
        this.straightPos = straightPos;
        this.leftPos = leftPos;
        this.rightPos = rightPos;
        currentPos = SwivelBehavior.Position.OTHER;
    }

    /**
     * Swivels the phone swivel servo to the specified position
     * @param pos - the position
     */
    public void swivel(double pos) {
        currentPos = SwivelBehavior.Position.OTHER;
        servo.setPosition(pos);
    }


    /**
     * Swivels the phone swivel servo to the position
     * @param position - the position--LEFT, CENTER, RIGHT. If given OTHER, no action is taken.
     */
    public void swivel(SwivelBehavior.Position position) {
        currentPos = position;
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

    public SwivelBehavior.Position getCurrentPos() {
        return currentPos;
    }
}
