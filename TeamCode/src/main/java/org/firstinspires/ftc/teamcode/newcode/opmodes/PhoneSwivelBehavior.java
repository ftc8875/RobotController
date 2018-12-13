package org.firstinspires.ftc.teamcode.newcode.opmodes;

import android.provider.ContactsContract;

import org.firstinspires.ftc.teamcode.newcode.components.PhoneSwivel;

import static org.firstinspires.ftc.teamcode.newcode.components.PhoneSwivel.Position.CENTER;

public class PhoneSwivelBehavior implements SwivelBehavior {

    private PhoneSwivel.Position desiredPosition;
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

    public void setNextPosition(PhoneSwivel.Position position) {
        desiredPosition = position;
    }
}
