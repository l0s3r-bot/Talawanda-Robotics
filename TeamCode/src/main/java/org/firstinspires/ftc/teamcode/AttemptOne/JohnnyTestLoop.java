package org.firstinspires.ftc.teamcode.AttemptOne;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test Loop", group="Competition")
public class JohnnyTestLoop extends MainLoop {
    @Override
    public String team_color(){
        return "BLUE";
    }
    @Override
    public Double parking_bearing(){
        return -5.0;
    }
    @Override
    public Double parking_range(){
        return 80.0;
    }
}

