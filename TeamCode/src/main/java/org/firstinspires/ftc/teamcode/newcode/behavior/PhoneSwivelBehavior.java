package org.firstinspires.ftc.teamcode.newcode.behavior;

import org.firstinspires.ftc.teamcode.newcode.components.hardware.PhoneSwivel;

import static org.firstinspires.ftc.teamcode.newcode.behavior.SwivelBehavior.Position.CENTER;

public class PhoneSwivelBehavior implements SwivelBehavior {

    private SwivelBehavior.Position desiredPosition;
    private PhoneSwivel phoneSwivel;

    public PhoneSwivelBehavior(PhoneSwivel phoneSwivel) {
        this.phoneSwivel = phoneSwivel;
        this.desiredPosition = CENTER;
    }

    @Override
    public boolean runWasSuccessful() {
        return true;
    }

    @Override
    public void run() {
        phoneSwivel.swivel(desiredPosition);
    }

    public void setNextPosition(SwivelBehavior.Position position) {
        desiredPosition = position;
    }
}
