package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TalaDriveController  {
    private IMU imu;
  private DcMotor mBL;
  private DcMotor mFL;
  private DcMotor mFR;
  private DcMotor mBR;


  public void initialize(LinearOpMode opMode){
      imu = opMode.hardwareMap.get(IMU.class, "imu");

    mBL = opMode.hardwareMap.get(DcMotor.class, "mBL");
    mFL = opMode.hardwareMap.get(DcMotor.class, "mFL");
    mFR = opMode.hardwareMap.get(DcMotor.class, "mFR");
    mBR = opMode.hardwareMap.get(DcMotor.class, "mBR");

    
    mBL.setDirection(DcMotor.Direction.REVERSE);
    mFL.setDirection(DcMotor.Direction.REVERSE);
    // This uses RUN_USING_ENCODER to be more accurate.   If you don't have the encoder wires, you should remove these
    mFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    mFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    mBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    mBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

      // Create a RevHubOrientationOnRobot object for use with an IMU in a REV Robotics Control
      // Hub or Expansion Hub, specifying the hub's orientation on the robot via the direction
      // that the REV Robotics logo is facing and the direction that the USB ports are facing.
      imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)));

  }


  public void handleControlsInLoop(LinearOpMode opMode){


      double drive  = -opMode.gamepad1.left_stick_y  / 1.0;  // Reduce drive rate to 50%.
      double strafe = -opMode.gamepad1.left_stick_x  / 1.0;  // Reduce strafe rate to 50%.
      double turn = -opMode.gamepad1.right_stick_x / 3.0;  // Reduce turn rate to 33%.
      driveBot(drive, strafe, turn);

        if (opMode.gamepad1.a) {
          imu.resetYaw();
        }
  }


  
  /**
   * This takes field relative, converts to field relative and drives the robot
   */
  private void drive_relative(float forward_relative, float right_relative, float rotate_relative) {
    double theta;
    double r;

    theta = Math.atan2(forward_relative, right_relative) / Math.PI * 180;
    r = Math.sqrt(forward_relative * forward_relative + right_relative * right_relative);
    theta = AngleUnit.DEGREES.normalize(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
    driveBot((float) (r * Math.sin(theta / 180 * Math.PI)), (float) (r * Math.cos(theta / 180 * Math.PI)), rotate_relative);
  }

  /**
   * Thanks to FTC16072 for sharing this code!
   */
  public void driveBot(double drive, double strafe, double turn) {


      double frontLeftPower    =  drive - strafe - turn;
      double frontRightPower   =  drive + strafe + turn;
      double backLeftPower     =  drive + strafe - turn;
      double backRightPower    =  drive - strafe + turn;

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
        opMode.telemetry.addLine("Press A to reset Yaw");
        opMode.telemetry.addLine("Hold left bumper to drive in robot relative");
        opMode.telemetry.addLine("The left joystick sets the robot direction");
        opMode.telemetry.addLine("Moving the right joystick left and right turns the robot");
    }



}