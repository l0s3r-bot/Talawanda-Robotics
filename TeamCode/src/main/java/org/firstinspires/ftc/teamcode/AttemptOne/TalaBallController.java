package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class TalaBallController{
    //private Servo launcherTilt;
    private DcMotor launcherFront;
    private DcMotor launcherRear;
    private CRServo launcherPrimer;

    double launcherTPS;

    int launcherRPM;

    public void initialize (LinearOpMode opMode) {

        //launcherTilt = opMode.hardwareMap.get(Servo.class, "launcherTilt");
        launcherFront = opMode.hardwareMap.get(DcMotor.class, "launcherFront");
        launcherRear = opMode.hardwareMap.get(DcMotor.class, "launcherRear");
        launcherPrimer = opMode.hardwareMap.get(CRServo.class, "launcherPrimer");


        //launcherTilt.scaleRange(0.55, 0.675);
        //launcherTilt.setDirection(Servo.Direction.REVERSE);
        launcherPrimer.setDirection(CRServo.Direction.REVERSE);
        launcherRPM = 2250;
        launcherRear.setDirection(DcMotor.Direction.REVERSE);
        launcherRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcherFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void launcherTelemetry(LinearOpMode opMode) {
        //opMode.telemetry.addData("launcher_lift", launcherTilt.getPosition());
        opMode.telemetry.addData("launcherRPM", launcherRPM);
        opMode.telemetry.addData("LauncherTPS", launcherTPS);
    }

    public void handleLauncherControlsInLoop(LinearOpMode opMode) {
        launcherTPS = (launcherRPM * 28) / 60.0;
        if (opMode.gamepad2.right_bumper) {
            ((DcMotorEx) launcherFront).setVelocity(launcherTPS);
            ((DcMotorEx) launcherRear).setVelocity(launcherTPS);
        } else {
            ((DcMotorEx) launcherFront).setVelocity(0);
            ((DcMotorEx) launcherRear).setVelocity(0);
        }
        //launcherTilt.setPosition(opMode.gamepad2.left_trigger);
        if (opMode.gamepad2.left_bumper) {
            launcherPrimer.setPower(1);
        } else {
            launcherPrimer.setPower(0);
        }
        launcherRPM += Math.round(opMode.gamepad2.right_stick_y * 20);
    }

    private void launcher_init() {
        //launcherTilt.scaleRange(0.55, 0.675);
       // launcherTilt.setDirection(Servo.Direction.REVERSE);
        launcherPrimer.setDirection(CRServo.Direction.REVERSE);
        launcherRPM = 2250;
        launcherRear.setDirection(DcMotor.Direction.REVERSE);
        launcherRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcherFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}