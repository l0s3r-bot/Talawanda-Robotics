package org.firstinspires.ftc.teamcode.BioBuzzSeason;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

    //public double mBLrpm;
    //public double mFLrpm;
    //public double mFRrpm;
    //public double mBRrpm;

    static final double     FORWARD_SPEED = 0.4;
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
            // Put run blocks here.
            do{
                if (runtime.seconds() > 3.0 && runtime.seconds() < 6.0){
                    //moving backwards
                    mFL.setPower(-FORWARD_SPEED);
                    mFR.setPower(-FORWARD_SPEED);
                    mBL.setPower(-FORWARD_SPEED);
                    mBR.setPower(-FORWARD_SPEED);
                }
                else if (runtime.seconds() > 9.0 && runtime.seconds() < 12.3){
                    //turning
                    mFL.setPower(-FORWARD_SPEED);
                    mFR.setPower(FORWARD_SPEED);
                    mBL.setPower(-FORWARD_SPEED);
                    mBR.setPower(FORWARD_SPEED);
                }
                else if (runtime.seconds() > 15.3){
                    //not movin
                    mFL.setPower(0);
                    mFR.setPower(0);
                    mBL.setPower(0);
                    mBR.setPower(0);
                }
                else{
                    //movin forward (obviously)
                    mFL.setPower(FORWARD_SPEED);
                    mFR.setPower(FORWARD_SPEED);
                    mBL.setPower(FORWARD_SPEED);
                    mBR.setPower(FORWARD_SPEED);
                }
                //telemetry.addData("mFL rpm: ",)
                //telemetry.update();

            } while (opModeIsActive());


    }



}

