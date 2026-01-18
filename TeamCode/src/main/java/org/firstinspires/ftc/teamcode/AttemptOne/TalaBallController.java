package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class TalaBallController{
    private Servo launcherTilt;
    private DcMotor launcherFront;
    private DcMotor launcherRear;
    private CRServo launcherPrimer;
    private CRServo ballLiftWheel;
    private CRServo intakeWheel;

    private CRServo transferWheelLeft;
    private CRServo transferWheelRight;
    double launcherTPS;

    double tiltValue = .45;

    int launcherRPM;

    boolean toggleButtonPressed = false;
    boolean intakeRunning = false;

    public void initialize (LinearOpMode opMode) {

         ballLiftWheel = opMode.hardwareMap.get(CRServo.class, "ballLiftWheel");
         intakeWheel = opMode.hardwareMap.get(CRServo.class, "intakeWheel");
         transferWheelLeft = opMode.hardwareMap.get(CRServo.class,"transferWheelLeft");
        transferWheelRight = opMode.hardwareMap.get(CRServo.class,"transferWheelRight");

        intakeWheel.setDirection(DcMotor.Direction.FORWARD);
        ballLiftWheel.setDirection(DcMotor.Direction.REVERSE);
        transferWheelLeft.setDirection(CRServo.Direction.REVERSE);


        launcherTilt = opMode.hardwareMap.get(Servo.class, "launcherTilt");
        launcherFront = opMode.hardwareMap.get(DcMotor.class, "launcherLeft");
        launcherRear = opMode.hardwareMap.get(DcMotor.class, "launcherRight");
        launcherPrimer = opMode.hardwareMap.get(CRServo.class, "launcherPrimer");


        //launcherTilt.scaleRange(0.55, 0.675);
        //launcherTilt.setDirection(Servo.Direction.REVERSE);
        launcherPrimer.setDirection(CRServo.Direction.REVERSE);
        launcherRPM = 2000;
        launcherRear.setDirection(DcMotor.Direction.REVERSE);
        launcherRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcherFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void launcherTelemetry(LinearOpMode opMode) {
        opMode.telemetry.addData("launcher Tilt", launcherTilt.getPosition());
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

        if (opMode.gamepad2.left_bumper) {
            launcherPrimer.setPower(1);
        } else {
            launcherPrimer.setPower(0);
        }
        launcherRPM += Math.round(opMode.gamepad2.right_stick_y * 20);

        if (opMode.gamepad1.b && !toggleButtonPressed)
        {
            intakeRunning = !intakeRunning;
                       toggleButtonPressed = true;
        }
        else if (!opMode.gamepad1.b)
        {
            toggleButtonPressed = false;
        }

        if (intakeRunning)
        {
            ballLiftWheel.setPower(1);
            intakeWheel.setPower(1);
            transferWheelLeft.setPower(1);
            transferWheelRight.setPower(1);

        } else {
            ballLiftWheel.setPower(0);
            intakeWheel.setPower(0);
            transferWheelLeft.setPower(0);
            transferWheelRight.setPower(0);
        }

        if (opMode.gamepad2.dpad_up)
        {
            tiltValue += .0033;
        }
        if (opMode.gamepad2.dpad_down)
        {
            tiltValue -= .0033;
        }
        if (tiltValue < .43)
        {
            tiltValue = .43;
        }
        if (tiltValue > .52)
        {
            tiltValue = .52;
        }
        launcherTilt.setPosition(tiltValue);
    }

    private void launcher_init() {
        //launcherTilt.scaleRange(0.55, 0.675);
        launcherTilt.setDirection(Servo.Direction.REVERSE);
        launcherPrimer.setDirection(CRServo.Direction.REVERSE);
        launcherRPM = 2250;
        launcherRear.setDirection(DcMotor.Direction.REVERSE);
        launcherRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcherFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}