package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


public class MainLoop extends LinearOpMode {

    public String team_color(){
        return "";
    }

    TalaDriveController driveController;
    TalaBallController launcherController;
    WebcamHandler Webcam;
    TalaSlideLiftController slideController;

  /**
   * This OpMode illustrates how to program your robot to drive field relative. This means
   * that the robot drives the direction you push the joystick regardless of the current orientation of the robot.
   *
   * This OpMode assumes that you have four mecanum wheels each on its own motor named:
   *  front_left_motor, front_right_motor, back_left_motor, back_right_motor
   *
   * and that the left motors are flipped such that when they turn clockwise the wheel
   * moves backwards
   */
  @Override
  public void runOpMode() {
        driveController = new TalaDriveController();
        launcherController = new TalaBallController();
        Webcam = new WebcamHandler();
        slideController = new TalaSlideLiftController();

        driveController.initialize(this);
        launcherController.initialize(this);
        Webcam.initialize(this);
        slideController.slide_init(this);

    waitForStart();
      // Put run blocks here.



      while (opModeIsActive()) {
        // Put loop blocks here.
            driveController.handleControlsInLoop(this , Webcam);
            launcherController.handleLauncherControlsInLoop(this);
            slideController.handleSlideControlsInLoop(this);


            driveController.addTelemetryOutput(this);
            launcherController.launcherTelemetry(this);
            Webcam.telemetryAprilTag(this, team_color()); //Change string to change teams!
            slideController.slideTelemetry(this);


            telemetry.update();
      } //ends while loop
  }
  //ends runOpMode function
}//ends MainLoop class
