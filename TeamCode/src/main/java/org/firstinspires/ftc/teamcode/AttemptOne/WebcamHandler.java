package org.firstinspires.ftc.teamcode.AttemptOne;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class WebcamHandler {
    private static final int RED_TAG_ID = 24;
    private static final int  BLUE_TAG_ID = 20;

    double MARGIN_OF_ERROR = 1.5;
    int current_tag_id= 0;
    int enemy_tag_id=0;

    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    /**
     * The variable to store our instance of the AprilTag processor.
     */
    private AprilTagProcessor aprilTag;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    public void initialize (MainLoop opMode){

        // Create the AprilTag processor the easy way.
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        // Create the vision portal the easy way.
        if (USE_WEBCAM) {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    opMode.hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTag);
        } else {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    BuiltinCameraDirection.BACK, aprilTag);
        }
        // Set current_tag_id to the target goal



        if (opMode.team_color().equals("BLUE"))
        {
            current_tag_id = BLUE_TAG_ID;
            enemy_tag_id = RED_TAG_ID;
        }
        else if (opMode.team_color().equals("RED"))
        {
            current_tag_id = RED_TAG_ID;
            enemy_tag_id = BLUE_TAG_ID;
        }

        //ALERT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // TEMP CODE - AT HOME TEST... REMOVE FOR COMP
        enemy_tag_id = 22;


    }   // end method initAprilTag()

    public Double getTargetHeading(){
        AprilTagDetection detection = getCurrentTargetDetection();
        if( detection == null) {
            return null;
        }
        else if(Math.abs(detection.ftcPose.bearing) <= MARGIN_OF_ERROR){
            return 0.0;
        }
        else {
            double limitFactor = 50.0;
            if( Math.abs(detection.ftcPose.bearing) > 45 ){
                //turn faster if we are way off target
                limitFactor = 10.0;
            }
            return detection.ftcPose.bearing / 50.0;
        }


    }
    private AprilTagDetection getCurrentTargetDetection(){
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        for (AprilTagDetection detection : currentDetections) {

            if (detection.id == current_tag_id) {
                return detection;
            }


        }

        return null;
    }
    public AprilTagDetection getEnemyTargetDetection(){
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        for (AprilTagDetection detection : currentDetections) {

            if (detection.id == enemy_tag_id) {
                return detection;
            }


        }

        return null;
    }
    public void telemetryAprilTag(LinearOpMode opMode, String team_color) {


        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        opMode.telemetry.addData("# AprilTags Detected", currentDetections.size());


        AprilTagDetection currentGoalTag = getCurrentTargetDetection();


        if( currentGoalTag == null){
            opMode.telemetry.addLine("No target");
        }
        else if(Math.abs(currentGoalTag.ftcPose.bearing) <= MARGIN_OF_ERROR){
            opMode.telemetry.addLine("ON TARGET, FIRE AWAY");
        }
        else if(currentGoalTag.ftcPose.bearing > 0){
            opMode.telemetry.addLine("TURN LEFT");
        }
        else if(currentGoalTag.ftcPose.bearing < 0) {
            opMode.telemetry.addLine("TURN RIGHT");
        }



/*
        // Step through the list of detections and display info for each one.
         if (currentGoalTag.metadata != null) {
            opMode.telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
            opMode.telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
            opMode.telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
            opMode.telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
        } else {
            opMode.telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
            opMode.telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
        }

*/


    }   // end method telemetryAprilTag()

}
