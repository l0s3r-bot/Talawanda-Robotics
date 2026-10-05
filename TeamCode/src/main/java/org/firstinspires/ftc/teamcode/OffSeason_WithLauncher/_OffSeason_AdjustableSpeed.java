package org.firstinspires.ftc.teamcode.OffSeason_WithLauncher;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "OffSeasonMain_WithLauncher_AdjustableSpeed", group="OffSeason")
public class _OffSeason_AdjustableSpeed extends OffSeasonMain_WithLauncher {
private Double currentspeed = .5;

    @Override
    public Double opmode_drive_throttle(){
        return currentspeed;
    }
    @Override
    public Double opmode_launch_throttle(){
        return Math.max(currentspeed, .6); //launcher does not work at low speeds
    }


    @Override
    public void opmode_hanldecontrols(){
        //do nothing, but allow inherited classes to implement it if they want to


        if (this.gamepad1.right_stick_button) {
            if( this.gamepad1.dpad_up){
                currentspeed += .001;
            }
            else if(this.gamepad1.dpad_down ){
                currentspeed -= .001;
            }

            //make sure current speed never goes below 0
            //MAYBE replace 0 with a different minimum, lowest detected movement value for motors???
            currentspeed = Math.max(0, currentspeed);

            //make sure current speed never goes above 1
            currentspeed = Math.min(1, currentspeed);
        }


        this.telemetry.addData("Adjustable Speed: ", currentspeed);
    }
}
