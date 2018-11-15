package org.firstinspires.ftc.teamcode.competition.quickTesting;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.File;

/**
 * This file demonstrates how to play simple sounds on both the RC and DS phones.
 * It illustrates how to build sounds into your application as a resource.
 * This technique is best suited for use with Android Studio since it assumes you will be creating a new application
 *
 * If you are using OnBotJava, please see the ConceptSoundsOnBotJava sample
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 *
 * Operation:
 *
 * Gamepad X & B buttons are used to trigger sounds in this example, but any event can be used.
 * Note: Time should be allowed for sounds to complete before playing other sounds.
 *
 * For sound files to be used as a compiled-in resource, they need to be located in a folder called "raw" under your "res" (resources) folder.
 * You can create your own "raw" folder from scratch, or you can copy the one from the FtcRobotController module.
 *
 *     Android Studio coders will ultimately need a folder in your path as follows:
 *       <project root>/TeamCode/src/main/res/raw
 *
 *     Copy any .wav files you want to play into this folder.
 *     Make sure that your files ONLY use lower-case characters, and have no spaces or special characters other than underscore.
 *
 *     The name you give your .wav files will become the resource ID for these sounds.
 *     eg:  gold.wav becomes R.raw.gold
 *
 *     If you wish to use the sounds provided for this sample, they are located in:
 *     <project root>/FtcRobotController/src/main/res/raw
 *     You can copy and paste the entire 'raw' folder using Android Studio.
 *
 */

@TeleOp(name="Test Sounds", group="test")
public class TestSounds extends LinearOpMode {

    // Declare OpMode members.
    private boolean goldFound;      // Sound file present flags
    private boolean silverFound;
    private boolean oopsFound;

    private boolean isX = false;    // Gamepad button state variables
    private boolean isB = false;
    private boolean isA = false;

    private boolean wasX = false;   // Gamepad button history variables
    private boolean WasB = false;
    private boolean wasA = false;

    @Override
    public void runOpMode() {

        // Determine Resource IDs for sounds built into the RC application.
        int silverSoundID = hardwareMap.appContext.getResources().getIdentifier("silver", "raw", hardwareMap.appContext.getPackageName());
        int goldSoundID   = hardwareMap.appContext.getResources().getIdentifier("gold",   "raw", hardwareMap.appContext.getPackageName());
        int oopsSoundID   = hardwareMap.appContext.getResources().getIdentifier("speech", "raw", hardwareMap.appContext.getPackageName());

        // Determine if sound resources are found.
        // Note: Preloading is NOT required, but it's a good way to verify all your sounds are available before you run.
        if (goldSoundID != 0)
            goldFound   = SoundPlayer.getInstance().preload(hardwareMap.appContext, goldSoundID);

        if (silverSoundID != 0)
            silverFound = SoundPlayer.getInstance().preload(hardwareMap.appContext, silverSoundID);

        if (oopsSoundID != 0)
            oopsFound = SoundPlayer.getInstance().preload(hardwareMap.appContext, oopsSoundID);

        // Display sound status
        telemetry.addData("gold resource",   goldFound ?   "Found" : "NOT found\n Add gold.wav to /src/main/res/raw" );
        telemetry.addData("silver resource", silverFound ? "Found" : "Not found\n Add silver.wav to /src/main/res/raw" );
        telemetry.addData("oopsie woopsie resource", oopsFound ? "found" : "NOT found. OOPSIE WOOPSIE!");

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData(">", "Press Start to continue");
        telemetry.update();
        waitForStart();

        telemetry.addData(">", "Press X, B, A to play sounds.");
        telemetry.update();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // say Silver each time gamepad X is pressed (This sound is a resource)
            if (silverFound && (isX = gamepad1.x) && !wasX) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, silverSoundID);
                telemetry.addData("Playing", "Resource Silver");
                telemetry.update();
            }

            // say Gold each time gamepad B is pressed  (This sound is a resource)
            if (goldFound && (isB = gamepad1.b) && !WasB) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, goldSoundID);
                telemetry.addData("Playing", "Resource Gold");
                telemetry.update();
            }

            if (oopsFound && (isA = gamepad1.a) && !wasA) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, oopsSoundID);
                telemetry.addData("Playing", "Resource Oopsie Woospie");
                telemetry.update();
            }

            // Save last button states
            wasX = isX;
            WasB = isB;
            wasA = isA;
        }
    }
}

