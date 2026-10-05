package org.firstinspires.ftc.teamcode.OffSeason_WithLauncher;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "OffSeasonMain_HalfSpeed", group="OffSeason")
public class _OffSeason_HalfSpeed extends OffSeasonMain_WithLauncher {
    @Override
    public Double opmode_drive_throttle(){
        return .5;
    }
    @Override
    public Double opmode_launch_throttle(){
        return .5;
    }
}
