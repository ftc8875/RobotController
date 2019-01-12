package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.newcode.components.software.Vuforia;
import org.firstinspires.ftc.teamcode.newcode.components.software.VuforiaBuilder;

import java.util.List;

@Autonomous(name="Test Vuforia", group="test")
public class TestVuforia extends OpMode {

        private static final String VUFORIA_KEY = "AYfuV+P/////AAAAGTY+YjM4LE8HtPCZ1JH9+10fYp6RxcfbBLgIXt+cznm9RWskA72GmhlAOVQ8BsZUBVNpStBMsFbapua+e3iGRC2xd9+qd0NXHlwr7QD2NxEdX7T/XnG/mEMdrax2JiRKAlWorUMamvj3JJ02zhYJosP5X8iVcMq8G0ayaFE0LtVl9sdEtpJo9Ipdxx+X+Ns/+np0sMyK3SDY+yc2H6qT85Ro8gHneO0YMMwEZsnoTE+3uA6UGrmOHTqYLrr8SW/knYuvBcTNxqfFMfZFLPZUbX1270BcPJuZWF0aFArSbtbDVstT6r6F1PF/x730MjsV1ITATw+P9YUqx3xK86u6ZsVugfFXmehkmwrp/6Ag5SRs";

    Vuforia vuforia;

    @Override
    public void init() {

        OpenGLMatrix identity = OpenGLMatrix.identityMatrix();

        vuforia = new VuforiaBuilder(VUFORIA_KEY)
                .withCameraDirection(VuforiaLocalizer.CameraDirection.BACK)
                .withCameraLocation(identity)
                .withVuforiaAssetName("RoverRuckus")
                .withVuforiaTrackableNames("one", "two", "three", "four")
                .withVuforiaTrackableLocations(identity, identity, identity, identity)
                .withCameraMonitoring(this.hardwareMap)
                .build();

        vuforia.init();
    }

    @Override
    public void start() {
        vuforia.start();
    }

    @Override
    public void loop() {
        telemetry.clear();
        telemetry.addLine("Visible Trackables");
        List<String> visibleTrackables = vuforia.getVisibleTrackableNames();
        for (String name : visibleTrackables) {
            telemetry.addLine(name);
        }

        if (visibleTrackables.size() > 0) {
            OpenGLMatrix pos = vuforia.getRobotPositionFromTrackable(visibleTrackables.get(0));
            if (pos != null) {
                telemetry.addLine(pos.formatAsTransform());
            } else {
                telemetry.addLine("MATRIX IS NULL!!!!!!!");
            }
        }
    }

    @Override
    public void stop() {
        vuforia.stop();
    }
}
