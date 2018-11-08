package org.firstinspires.ftc.teamcode.general.dsopts;

import java.util.List;

public interface DriverStationOption<T> {

    T iterateForward();
    T iterateBackward();
    T getCurrentValue();
    String getOptionName();
    void setOptionName(String optionName);

}
