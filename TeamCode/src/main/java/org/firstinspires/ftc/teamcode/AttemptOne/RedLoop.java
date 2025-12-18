package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Red Mode", group="Competition")
public class RedLoop extends MainLoop {
    @Override
    public String team_color(){
        return "RED";
    }
}