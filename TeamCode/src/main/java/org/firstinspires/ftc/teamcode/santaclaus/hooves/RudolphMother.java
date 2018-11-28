package org.firstinspires.ftc.teamcode.santaclaus.hooves;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RudolphMother {

    private VuforiaLocalizer.CameraDirection cameraDirection;
    private boolean enableCameraMonitoring = false;
    private OpenGLMatrix cameraLocation;
    private String vuforiaKey;
    private String vuforiaAssetName;
    private List<String> vuforiaTrackableNames;
    private List<OpenGLMatrix> vuforiaTrackableLocations;
    private HardwareMap hardwareMap;

    public RudolphMother(String vuforiaKey) {
        this.vuforiaKey = vuforiaKey;
    }

    public RudolphMother withCameraDirection(VuforiaLocalizer.CameraDirection cameraDirection) {
        this.cameraDirection = cameraDirection;
        return this;
    }

    public RudolphMother withCameraLocation(OpenGLMatrix cameraLocation) {
        this.cameraLocation = cameraLocation;
        return this;
    }

    public RudolphMother withVuforiaAssetName(String vuforiaAssetName) {
        this.vuforiaAssetName = vuforiaAssetName;
        return this;
    }

    public RudolphMother withVuforiaTrackableNames(String ... vuforiaTrackableNames) {
        Collection<String> namesCollection = Arrays.asList(vuforiaTrackableNames);
        this.vuforiaTrackableNames = new ArrayList<>();
        this.vuforiaTrackableNames.addAll(namesCollection);
        return this;
    }

    public RudolphMother withVuforiaTrackableNames(List<String> vuforiaTrackableNames) {
        this.vuforiaTrackableNames = vuforiaTrackableNames;
        return this;
    }

    public RudolphMother withVuforiaTrackableLocations(OpenGLMatrix ... vuforiaTrackableLocations) {
        Collection<OpenGLMatrix> locationsCollection = Arrays.asList(vuforiaTrackableLocations);
        this.vuforiaTrackableLocations = new ArrayList<>();
        this.vuforiaTrackableLocations.addAll(locationsCollection);
        return this;
    }

    public RudolphMother withVuforiaTrackableLocations(List<OpenGLMatrix> vuforiaTrackableLocations) {
        this.vuforiaTrackableLocations = vuforiaTrackableLocations;
        return this;
    }

    public RudolphMother withCameraMonitoring(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        enableCameraMonitoring = true;
        return this;
    }

    public Rudolph build() {
        return new Rudolph(
                this.cameraDirection,
                this.enableCameraMonitoring,
                this.cameraLocation,
                this.vuforiaKey,
                this.vuforiaAssetName,
                this.vuforiaTrackableNames,
                this.vuforiaTrackableLocations,
                this.hardwareMap
        );

    }
}