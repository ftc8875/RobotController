package org.firstinspires.ftc.teamcode.OLDgeneral.dsopts;

import java.util.Arrays;
import java.util.List;

public class DiscreteDSOption<T> implements DriverStationOption {

    String optionName;
    List<T> possibleValues;
    int currentIndex = 0;

    public DiscreteDSOption(String optionName, T ... possibleValues) {
        this(optionName, Arrays.asList(possibleValues));
    }

    public DiscreteDSOption(String optionName, List<T> possibleValues) {
        this.optionName = optionName;
        this.possibleValues.addAll(possibleValues);
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public T getValue(int index) {
        return possibleValues.get(index);
    }

    public T iterateForward() {
        currentIndex++;
        if (currentIndex >= possibleValues.size()) {
            currentIndex = 0;
        }
        return possibleValues.get(currentIndex - 1);
    }

    public T iterateBackward() {
        currentIndex--;
        if (currentIndex <= 0) {
            currentIndex = possibleValues.size() - 1;
        }
        return possibleValues.get(currentIndex - 1);
    }

    public T getCurrentValue() {
        return possibleValues.get(currentIndex);
    }

}
