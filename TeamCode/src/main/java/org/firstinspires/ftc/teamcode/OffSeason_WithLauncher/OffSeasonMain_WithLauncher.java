package org.firstinspires.ftc.teamcode.OffSeason_WithLauncher;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

abstract public class OffSeasonMain_WithLauncher extends LinearOpMode {
    public Double opmode_drive_throttle(){
        return 1.0;
    }
    public Double opmode_launch_throttle(){
        return 1.0;
    }
    public void opmode_hanldecontrols(){
        //do nothing, but allow inherited classes to implement it if they want to
    }

    OffSeasonDrive_WithLauncher driveController;
    OffSeason_TalaBallController launcherController;


    public void runOpMode() {
        driveController = new OffSeasonDrive_WithLauncher();


        driveController.initialize(this);
        launcherController = new OffSeason_TalaBallController();
        launcherController.initialize(this);

        waitForStart();
        // Put run blocks here.

        while (opModeIsActive()) {
            // Put loop blocks here.
            driveController.handleControlsInLoop(this);

            launcherController.handleLauncherControlsInLoop(this);
            launcherController.launcherTelemetry(this);
            driveController.addTelemetryOutput(this);

            opmode_hanldecontrols();


            telemetry.update();
        }
    }
}