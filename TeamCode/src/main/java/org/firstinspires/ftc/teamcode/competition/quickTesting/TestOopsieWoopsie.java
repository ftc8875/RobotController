package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="Definitely Official Autonomous", group="test")
public class TestOopsieWoopsie extends LinearOpMode {

    private static final String oops = "OOPSIE WOOPSIE! WE MADE A YUCKY WUCKY!";
    private static final int width = Math.min(oops.length(), 40);

    public void runOpMode() {
        int soundID = hardwareMap.appContext.getResources().getIdentifier("speech", "raw", hardwareMap.appContext.getPackageName());
        SoundPlayer.getInstance().preload(hardwareMap.appContext, soundID);

        telemetry.addLine("Initialized!");
        telemetry.update();
        waitForStart();
        /*for (int i=0; i<=oops.length(); i++) {
            int l = width - i;
            l = l<0 ? 0 : l;
            String t = new String(new char[l]).replace('\0', ' ') + oops.substring(0, i);
            telemetry.addLine(t);
            telemetry.update();
            sleep(300);
        }*/
        for (int i=oops.length(); i>=0; i--) {
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, soundID);
            int l = width - i;
            l = l<0 ? 0 : l;
            String t = oops.substring(i, oops.length()) + new String(new char[l]).replace('\0', ' ');
            telemetry.addLine(t);
            telemetry.update();
            sleep(300);
        }
        sleep(5000);
    }

}
