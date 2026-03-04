package org.firstinspires.ftc.teamcode.OffSeason;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name = "OffSeasonMain", group="OffSeason")
public class OffSeasonMain extends LinearOpMode {
    OffSeasonDrive driveController;

    public void runOpMode() {
        driveController = new OffSeasonDrive();


        driveController.initialize(this);


        waitForStart();
        // Put run blocks here.


        while (opModeIsActive()) {
            // Put loop blocks here.
            driveController.handleControlsInLoop(this);


            driveController.addTelemetryOutput(this);


            telemetry.update();
        }
    }
}