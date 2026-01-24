package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Red Mode", group="Competition")
public class RedLoop extends MainLoop {
    @Override
    public String team_color(){
        return "RED";
    }
    @Override
    public Double parking_bearing(){
        return 11.1;
    }
    @Override
    public Double parking_range(){
        return 96.9;
    }
}