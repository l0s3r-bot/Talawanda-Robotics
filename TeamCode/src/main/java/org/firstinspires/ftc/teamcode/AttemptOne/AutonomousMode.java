//package org.firstinspires.ftc.teamcode.AttemptOne;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//@Autonomous(name = "Autonomous Mode", group="Competition")
//public class AutonomousMode extends MainLoop {
//    private ElapsedTime runtime = new ElapsedTime();
//    public void runOpMode(LinearOpMode opMode) {
//        opMode.waitForStart();
//
//        while (runtime.seconds() <= 2.0) {
//            mFL.setPower(1);
//            mFR.setPower(1);
//            mBL.setPower(1);
//            mBR.setPower(1);
//        }
//        mFL.setPower(0);
//        mFR.setPower(0);
//        mBL.setPower(0);
//        mBR.setPower(0);
//
//    }
//}
