package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Blue Mode1", group="Competition")
public class BlueLoop extends MainLoop {
    @Override
    public String team_color(){
        return "BLUE";
    }
    @Override
    public Double parking_bearing(){
        return -16.6;
    }
}

