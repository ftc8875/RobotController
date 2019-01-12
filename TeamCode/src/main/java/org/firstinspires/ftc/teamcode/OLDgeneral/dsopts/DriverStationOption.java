package org.firstinspires.ftc.teamcode.OLDgeneral.dsopts;

public interface DriverStationOption<T> {

    T iterateForward();
    T iterateBackward();
    T getCurrentValue();
    String getOptionName();
    void setOptionName(String optionName);

}
