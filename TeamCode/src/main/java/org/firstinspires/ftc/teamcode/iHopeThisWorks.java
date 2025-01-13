
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import java.lang.Math;

import com.acmerobotics.roadrunner.Trajectory;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ComplexAutonomous extends LinearOpMode {
    private MecanumDrive drive;
    private SlideController slideController;
    private ClawController clawController;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize drive and hardware
        Pose2d startPose = new Pose2d(-27, 62, Math.toRadians(-90));
        drive = new MecanumDrive(hardwareMap,startPose);
        slideController = new SlideController(hardwareMap); // Handles slide actions
        clawController = new ClawController(hardwareMap);   // Handles claw actions

        // Start position

        // Wait for start signal
        waitForStart();
        if (isStopRequested()) return;

        // 1. Angle slides up and spline to (0, 38)
        slideController.setAngleUp(); // Angle slides up
        followTrajectoryWithCheck(drive.actionBuilder(startPose)
                .splineToLinearHeading(new Pose2d(0, 38, Math.toRadians(-90)), Math.toRadians(-90))
                .build());

        // 2. Extend slides and open claw
        slideController.extend();
        clawController.open();

        // 3. Retract slides and pitch claw down
        slideController.retract();
        clawController.pitchDown();

        // 4. Strafe to (-35, 35) and check position
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeTo(new Vector2d(-35, 35))
                .build());

        // 5. Line to y = 13 and check position
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineTo(new Vector2d(-35, 13))
                .build());

        // 6. Spline to (-47, 10) and face 90
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .splineToLinearHeading(new Pose2d(-47, 10, Math.toRadians(90)), Math.toRadians(90))
                .build());

        // 7. Line to y = 60
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineTo(new Vector2d(-47, 60))
                .build());

        // 8. Line to y = 13 and check position
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineTo(new Vector2d(-47, 13))
                .build());

        // 9. Spline to (-58, 10) and check position
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .splineToLinearHeading(new Pose2d(-58, 10, Math.toRadians(90)), Math.toRadians(90))
                .build());

        // 10. Line to y = 60
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineTo(new Vector2d(-58, 60))
                .build());

        // 11. Spline to (-47, 57), face 90, and check position
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .splineToLinearHeading(new Pose2d(-47, 57, Math.toRadians(90)), Math.toRadians(90))
                .build());

        // 12. Move claw up and close claw
        clawController.pitchUp();
        clawController.close();

        // 13. Angle slides up and spline to (1, 38)
        slideController.setAngleUp();
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .splineToLinearHeading(new Pose2d(1, 38, Math.toRadians(-90)), Math.toRadians(-90))
                .build());

        // 14. Extend slides and open claw
        slideController.extend();
        clawController.open();

        // 15. Retract slides and pitch claw down
        slideController.retract();
        clawController.pitchDown();

        // 16. Strafe to (-17, 44) while angling slides down
        slideController.setAngleDown();
        followTrajectoryWithCheck(drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeTo(new Vector2d(-17, 44))
                .build());

        // Repeat similar steps for the next cycles...

        // End of autonomous routine
        drive.setDrivePower(new Pose2d(0, 0, 0));
    }

    /**
     * Follows a trajectory and checks the robot's position after completion.
     * @param trajectory The trajectory to follow.
     */

    private void followTrajectoryWithCheck(Action trajectory) {
        drive.followTrajectory(trajectory);

        // Check position
        Pose2d targetPose = trajectory.end();
        Pose2d actualPose = drive.getPoseEstimate();

        double positionError = Math.hypot(
                targetPose.getX() - actualPose.getX(),
                targetPose.getY() - actualPose.getY()
        );
        double headingError = Math.abs(targetPose.getHeading() - actualPose.getHeading());

        // Tolerances
        double positionTolerance = 2.0; // inches
        double headingTolerance = Math.toRadians(5); // degrees to radians

        if (positionError > positionTolerance || headingError > headingTolerance) {
            telemetry.addLine("Correcting position...");
            telemetry.update();

            Trajectory correctionTrajectory = drive.trajectoryBuilder(actualPose)
                    .lineToSplineHeading(targetPose)
                    .build();
            drive.followTrajectory(correctionTrajectory);
        }

        telemetry.addData("Target Pose", targetPose);
        telemetry.addData("Actual Pose", actualPose);
        telemetry.update();
    }
}
