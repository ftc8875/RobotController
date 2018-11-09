package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.general.RobotComponent;

public class ServoClaw implements RobotComponent {

    Servo servo;

    boolean isClosed;

    double closePosition;
    double openPosition;

    public ServoClaw(Servo servo, boolean startClosed, double closePosition, double openPosition) {
        this.servo = servo;
        setClosePosition(closePosition);
        setOpenPosition(openPosition);
        isClosed = startClosed;
    }

    public void setClosePosition(double position) {
        if (position > Servo.MAX_POSITION || position < Servo.MIN_POSITION) {
            throw new IllegalArgumentException(String.format("Position " + position + " out of range", "%.2f"));
        }
        this.closePosition = position;
    }

    public void setOpenPosition(double position) {
        if (position > Servo.MAX_POSITION || position < Servo.MIN_POSITION) {
            throw new IllegalArgumentException(String.format("Position " + position + " out of range", "%.2f"));
        }
        this.openPosition = position;
    }

    private void setState(boolean closed) {
        if (closed) {
            close();
            this.isClosed = true;
        } else {
            open();
            this.isClosed = false;
        }
    }

    public void open() {
        servo.setPosition(openPosition);
    }

    public void close() {
        servo.setPosition(closePosition);
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void init() {
        setState(isClosed);
    }
    public void start() {}
    public void stop() {}
    public void emergencyStop() {}
}
