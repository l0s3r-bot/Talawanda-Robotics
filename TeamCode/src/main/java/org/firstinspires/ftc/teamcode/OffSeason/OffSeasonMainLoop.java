package org.firstinspires.ftc.teamcode.OffSeason;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "OffSeasonMainLoop", group="Off Season")
public class OffSeasonMainLoop extends LinearOpMode {

    public String team_color(){
        return "";
    }
    public Double parking_bearing(){
        return 0.0;
    }
    public Double parking_range(){
        return 98.6;
    }

    OffSeasonDrive driveController;


    /**
     * This OpMode illustrates how to program your robot to drive field relative. This means
     * that the robot drives the direction you push the joystick regardless of the current orientation of the robot.
     *
     * This OpMode assumes that you have four mecanum wheels each on its own motor named:
     *  front_left_motor, front_right_motor, back_left_motor, back_right_motor
     *
     * and that the left motors are flipped such that when they turn clockwise the wheel
     * moves backwards
     */
    @Override
    public void runOpMode() {
        driveController = new OffSeasonDrive();


        driveController.initialize(this);


        waitForStart();
        // Put run blocks here.



        while (opModeIsActive()) {
            // Put loop blocks here.
            driveController.handleControlsInLoop(this);







            telemetry.update();
        } //ends while loop
    }
    //ends runOpMode function
}//ends MainLoop class
