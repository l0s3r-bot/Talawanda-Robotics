package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "Johnny Test w Intake", group="Competition")
public class MainWithIntake extends LinearOpMode {

    public String team_color(){
        return "";
    }

//    TalaDriveController driveController;
  //  TalaBallController launcherController;
    //WebcamHandler Webcam;
    //TalaSlideLiftController slideController;

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
      CRServo launcherPrimer = this.hardwareMap.get(CRServo.class, "launcherPrimer");
      CRServo transferWheelLeft = this.hardwareMap.get(CRServo.class, "transferWheelLeft");
      CRServo transferWheelRight = this.hardwareMap.get(CRServo.class, "transferWheelRight");
      CRServo barrelLoader = this.hardwareMap.get(CRServo.class, "ballLiftWheel");

      transferWheelRight.setDirection(DcMotorSimple.Direction.FORWARD);
      transferWheelLeft.setDirection(DcMotorSimple.Direction.REVERSE );

      launcherPrimer.setDirection(DcMotorSimple.Direction.REVERSE );
      barrelLoader.setDirection(DcMotorSimple.Direction.REVERSE );



      //init
        waitForStart();
      // Put run blocks here.
        while (opModeIsActive()) {
         //handle controls

          //launcherTilt.setPosition(opMode.gamepad2.left_trigger);
          if (this.gamepad1.a) {
              launcherPrimer.setPower(1);
              transferWheelRight.setPower(1);
              transferWheelLeft.setPower(1);
              barrelLoader.setPower(1);
          } else {
              launcherPrimer.setPower(0);
              transferWheelRight.setPower(0);
              transferWheelLeft.setPower(0);
              barrelLoader.setPower(0);
          }
      } //ends while loop
  }
  //ends runOpMode function
}//ends MainLoop class
