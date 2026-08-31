package org.firstinspires.ftc.teamcode.OffSeason;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;


public class OffSeasonDrive {

    public DcMotorEx mBL;
    public DcMotorEx mFL;
    public DcMotorEx mFR;
    public DcMotorEx mBR;


    public static final double NEW_P = 2.5;
    public static final double NEW_I = 0.1;
    public static final double NEW_D = 0.2;

    public void initialize(LinearOpMode opMode) {
        mBL = opMode.hardwareMap.get(DcMotorEx.class, "mBL");
        mFL = opMode.hardwareMap.get(DcMotorEx.class, "mFL");
        mFR = opMode.hardwareMap.get(DcMotorEx.class, "mFR");
        mBR = opMode.hardwareMap.get(DcMotorEx.class, "mBR");

        mBR.setDirection(DcMotor.Direction.REVERSE);
        mFR.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.FORWARD);
        mFL.setDirection(DcMotor.Direction.FORWARD);

        PIDCoefficients pidNew = new PIDCoefficients(NEW_P, NEW_I, NEW_D);

        // This uses RUN_USING_ENCODER to be more accurate.   If you don't have the encoder wires, you should remove these
        mFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void handleControlsInLoop(OffSeasonMainLoop opMode){
        double driveSpeedCoefficient = (1 - (opMode.gamepad1.left_trigger * .15) + opMode.gamepad1.right_trigger);
        double drive = -(opMode.gamepad1.left_stick_y * driveSpeedCoefficient); // Reduce drive rate to 50%.
        double strafe = opMode.gamepad1.left_stick_x * driveSpeedCoefficient; // Reduce strafe rate to 50%.
        double turn = 0.0;
        driveBot(drive, strafe, turn);
    }
    public void driveBot(double drive, double strafe, double turn) {

        double frontLeftPower = drive + strafe + turn;
        double frontRightPower = drive - strafe - turn;
        double backLeftPower = drive - strafe + turn;
        double backRightPower = drive + strafe - turn;

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
}


