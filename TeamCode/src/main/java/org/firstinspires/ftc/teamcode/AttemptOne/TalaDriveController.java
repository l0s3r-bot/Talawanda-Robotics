package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TalaDriveController  {
    public DcMotor mBL;
    public DcMotor mFL;
    public DcMotor mFR;
    public DcMotor mBR;

    private ElapsedTime runtime = new ElapsedTime();

    
  public void initialize(LinearOpMode opMode){

    mBL = opMode.hardwareMap.get(DcMotor.class, "mBL");
    mFL = opMode.hardwareMap.get(DcMotor.class, "mFL");
    mFR = opMode.hardwareMap.get(DcMotor.class, "mFR");
    mBR = opMode.hardwareMap.get(DcMotor.class, "mBR");


      mBR.setDirection(DcMotor.Direction.REVERSE);
      mFR.setDirection(DcMotor.Direction.REVERSE);
      mBL.setDirection(DcMotor.Direction.FORWARD);
      mFL.setDirection(DcMotor.Direction.FORWARD);
    // This uses RUN_USING_ENCODER to be more accurate.   If you don't have the encoder wires, you should remove these
    mFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    mFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    mBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    mBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

  }

    



    public void handleControlsInLoop(LinearOpMode opMode, WebcamHandler cam){
      double driveSpeedCoefficient = (1 - (opMode.gamepad1.left_trigger * .75) + opMode.gamepad1.right_trigger);
      double drive = -(opMode.gamepad1.left_stick_y * driveSpeedCoefficient); // Reduce drive rate to 50%.
      double strafe = opMode.gamepad1.left_stick_x * driveSpeedCoefficient; // Reduce strafe rate to 50%.
      double turn;
      if (!opMode.gamepad1.left_bumper || (cam.getTargetHeading() == null)) {
          turn = opMode.gamepad1.right_stick_x * driveSpeedCoefficient;  // Reduce turn rate to 33%.

      } else {
          turn = cam.getTargetHeading();
      }
      driveBot(drive, strafe, turn);



  }



    public void driveBot(double drive, double strafe, double turn) {
        
      double frontLeftPower    =  drive + strafe + turn;
      double frontRightPower   =  drive - strafe - turn;
      double backLeftPower     =  drive - strafe + turn;
      double backRightPower    =  drive + strafe - turn;

      double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
      max = Math.max(max, Math.abs(backLeftPower));
      max = Math.max(max, Math.abs(backRightPower));

      if (max > 1.0) {
          frontLeftPower /= max;
          frontRightPower /= max;
          backLeftPower /= max;
          backRightPower /= max;
      }


          mFL.setPower(frontLeftPower);
          mFR.setPower(frontRightPower);
          mBL.setPower(backLeftPower);
          mBR.setPower(backRightPower);

  }




    public void addTelemetryOutput(LinearOpMode opMode){
        opMode.telemetry.addLine("The left joystick sets the robot direction");
        opMode.telemetry.addLine("Moving the right joystick left and right turns the robot");
        opMode.telemetry.addLine("Press right bumper to enter crawl mode");
    }



    }