package org.firstinspires.ftc.teamcode.OLDgeneral.dsopts;

import java.util.List;

public class DriverStationOptions {

    List<DriverStationOption> options;
    int currentIndex = 0;

    public DriverStationOptions() {}

    public void addOption(DriverStationOption option) {
        options.add(option);
    }

    public <T> void addDiscreteOption(String optionName, T ... possibleValues) {
        DriverStationOption<T> option = new DiscreteDSOption<T>(optionName, possibleValues);
        addOption(option);
    }

    public void selectOption(int index) {
        currentIndex = 0;
    }

    public void selectNextOption() {
        currentIndex++;
        if (currentIndex >= options.size()) {
            currentIndex = 0;
        }
    }

    public void selectPreviousOption() {
        currentIndex--;
        if (currentIndex <= 0) {
            currentIndex = options.size() - 1;
        }
    }

    public void incrementCurrentOption() {
        options.get(currentIndex).iterateForward();
    }

    public void decrementCurrentOption() {
        options.get(currentIndex).iterateBackward();
    }

    public String output() {
        String out = "";
        for (int i=0; i<options.size(); i++) {
            DriverStationOption option = options.get(i);
            String name = option.getOptionName();
            String value = option.getCurrentValue().toString();
            if (currentIndex == i) {
                out += ">" + name + " : " + value + "<\n";
            } else {
                out += " " + name + " : " + value + " \n";
            }
        }
        return out;
    }
}
