package org.firstinspires.ftc.teamcode.AttemptOne;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Autonomous Mode - red", group="Competition", preselectTeleOp="Red Mode")
public class AutonomousModeRed extends LinearOpMode {
    TalaDriveController driveController;
    @Override
    public void runOpMode() {
        driveController = new TalaDriveController();
        driveController.initialize(this);
        driveController.autonomousModeSimpleDrive(this);
    }
}
