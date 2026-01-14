package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TalaSlideLiftController {
    private DcMotor lSF;
    private DcMotor lSR;
  int Slide_Speed_Multiplier;
  int Slide_Posistion_relative;
  int LSR_OG_POS;
  int LSF_OG_POS;


public void slide_init(LinearOpMode opMode) {
    lSF = opMode.hardwareMap.get(DcMotor.class, "lSL");
    lSR = opMode.hardwareMap.get(DcMotor.class, "lSR");

    lSF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    lSR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    Slide_Posistion_relative = 0;
    LSR_OG_POS = lSR.getCurrentPosition();
    LSF_OG_POS = lSF.getCurrentPosition();
    Slide_Speed_Multiplier = 75;
}

    public void handleSlideControlsInLoop(LinearOpMode opMode) {
        Slide_Posistion_relative += Math.round(Slide_Speed_Multiplier * opMode.gamepad2.left_stick_y);
        lSF.setTargetPosition(LSF_OG_POS + Slide_Posistion_relative);
        lSR.setTargetPosition(LSR_OG_POS + Slide_Posistion_relative);
        lSF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lSR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lSR.setPower(0.5);
        lSF.setPower(0.5);
    }

  public void slideTelemetry(LinearOpMode opMode) {
      opMode.telemetry.addData("lSF Position", lSF.getCurrentPosition());
      opMode.telemetry.addData("lSR Position", lSR.getCurrentPosition());
    // debug
    if (true) {
        opMode.telemetry.addData("lSR Target", lSR.getTargetPosition());
        opMode.telemetry.addData("lSF Target", lSF.getTargetPosition());
        opMode.telemetry.addData("lSR OG Position", LSR_OG_POS);
        opMode.telemetry.addData("lSF OG Position", LSF_OG_POS);
        opMode.telemetry.addData("Relative Position", Slide_Posistion_relative);
    }
  }


}