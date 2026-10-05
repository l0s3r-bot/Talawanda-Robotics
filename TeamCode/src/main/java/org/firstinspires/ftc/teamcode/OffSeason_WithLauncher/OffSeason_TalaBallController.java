package org.firstinspires.ftc.teamcode.OffSeason_WithLauncher;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class OffSeason_TalaBallController {
    private DcMotor launcherFront;
    private DcMotor launcherRear;
    private CRServo launcherPrimer;

    double launcherTPS;

    int baseLauncherRPM;

    boolean motorsFound;

    public void initialize (LinearOpMode opMode) {

        try {
            launcherFront = opMode.hardwareMap.get(DcMotor.class, "launcherRight");
            launcherRear = opMode.hardwareMap.get(DcMotor.class, "launcherLeft");
            launcherPrimer = opMode.hardwareMap.get(CRServo.class, "launcherPrimer");

            motorsFound = true;
        } catch (RuntimeException e) {

            motorsFound = false;
        }

        if( motorsFound ){
            launcherPrimer.setDirection(CRServo.Direction.REVERSE);
            baseLauncherRPM = 2000;
            launcherRear.setDirection(DcMotor.Direction.REVERSE);
            launcherRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            launcherFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void launcherTelemetry(LinearOpMode opMode) {
        if( motorsFound ){
            opMode.telemetry.addData("LauncherTPS", launcherTPS);
        }
        else{
            opMode.telemetry.addLine("Launcher Motors not found");
        }
    }

    public void handleLauncherControlsInLoop(OffSeasonMain_WithLauncher opMode) {
        if( !motorsFound ){
            return;
        }
        double launcherRPM = Math.round(opMode.gamepad1.right_trigger * baseLauncherRPM );

        launcherTPS = (launcherRPM * 28) / 60.0;

        ((DcMotorEx) launcherFront).setVelocity(launcherTPS * opMode.opmode_launch_throttle());
        ((DcMotorEx) launcherRear).setVelocity(launcherTPS * opMode.opmode_launch_throttle());

        if (opMode.gamepad1.left_bumper || opMode.gamepad1.circle) {
            launcherPrimer.setPower(1);
        } else {
            launcherPrimer.setPower(0);
        }


    }
}