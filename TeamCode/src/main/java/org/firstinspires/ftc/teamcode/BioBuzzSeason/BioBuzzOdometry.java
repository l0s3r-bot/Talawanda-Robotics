package org.firstinspires.ftc.teamcode.BioBuzzSeason;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "BioBuzzDriver")
public class BioBuzzOdometry extends LinearOpMode {

    public DcMotor mBL;
    public DcMotor mFL;
    public DcMotor mFR;
    public DcMotor mBR;
    //private DcMotor ballIntake;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    /**
     * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
     * Comment Blocks show where to place Initialization code (runs once, after touching the
     * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
     * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
     * Stopped).
     */
    @Override
    public void runOpMode() {
        mBL = hardwareMap.get(DcMotor.class, "mBL");
        mFL = hardwareMap.get(DcMotor.class, "mFL");
        mFR = hardwareMap.get(DcMotor.class, "mFR");
        mBR = hardwareMap.get(DcMotor.class, "mBR");
        //ballIntake = hardwareMap.get(DcMotor.class, "ballIntake");

        mBR.setDirection(DcMotor.Direction.REVERSE);
        mFR.setDirection(DcMotor.Direction.REVERSE);
        mBL.setDirection(DcMotor.Direction.FORWARD);
        mFL.setDirection(DcMotor.Direction.FORWARD);
        // This uses RUN_USING_ENCODER to be more accurate.   If you don't have the encoder wires, you should remove these
        mFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive() && (runtime.seconds() < 3.0)) { //incredibly inefficient, I'll fix later (Evan)
                // Put loop blocks here.
                mFL.setPower(FORWARD_SPEED);
                mFR.setPower(FORWARD_SPEED);
                mBL.setPower(FORWARD_SPEED);
                mBR.setPower(FORWARD_SPEED);
            }
            while (opModeIsActive() && (runtime.seconds() > 3.0) && (runtime.seconds() < 6.0)) {
                // Put loop blocks here.
                mFL.setPower(-FORWARD_SPEED);
                mFR.setPower(-FORWARD_SPEED);
                mBL.setPower(-FORWARD_SPEED);
                mBR.setPower(-FORWARD_SPEED);
            }
            while (opModeIsActive() && (runtime.seconds() > 6.0) && (runtime.seconds() < 9.0) ) {
                // Put loop blocks here.
                mFL.setPower(FORWARD_SPEED);
                mFR.setPower(FORWARD_SPEED);
                mBL.setPower(FORWARD_SPEED);
                mBR.setPower(FORWARD_SPEED);
            }
            while (opModeIsActive() && (runtime.seconds() > 9.0) && (runtime.seconds() < 10.0) ) {
                mFL.setPower(-FORWARD_SPEED);
                mFR.setPower(FORWARD_SPEED);
                mBL.setPower(-FORWARD_SPEED);
                mBR.setPower(FORWARD_SPEED);
            }
            while (opModeIsActive() && (runtime.seconds() > 10.0) && (runtime.seconds() < 13.0) ) {
                // Put loop blocks here.
                mFL.setPower(FORWARD_SPEED);
                mFR.setPower(FORWARD_SPEED);
                mBL.setPower(FORWARD_SPEED);
                mBR.setPower(FORWARD_SPEED);
            }
        }
    }



}

