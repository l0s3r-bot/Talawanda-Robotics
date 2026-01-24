package org.firstinspires.ftc.teamcode.AttemptOne;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Autonomous Mode - blue", group="Competition", preselectTeleOp="Blue Mode1")
public class AutonomousModeBlue extends LinearOpMode {
    TalaDriveController driveController;
    @Override
    public void runOpMode() {
        driveController = new TalaDriveController();
        driveController.initialize(this);
        driveController.autonomousModeSimpleDrive(this);
    }
}

