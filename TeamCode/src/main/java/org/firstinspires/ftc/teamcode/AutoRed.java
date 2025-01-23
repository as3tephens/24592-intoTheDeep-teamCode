package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import org.firstinspires.ftc.teamcode.BasicSlides;

import org.firstinspires.ftc.teamcode.BasicClaw;
import org.firstinspires.ftc.teamcode.MecanumDrive;
public class AutoRed extends LinearOpMode{
    @Override

    public void runOpMode() throws InterruptedException {
    Pose2d beginPose = new Pose2d(-27,62,Math.toRadians(-90));

    MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
    BasicClaw claw = new BasicClaw(hardwareMap);
    BasicSlides slides = new BasicSlides(hardwareMap);

    waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .setTangent(0)
                        //first sample

                        //move slides up
                        .afterTime(0,)
                        //go to bar
                        .splineToConstantHeading(new Vector2d(0,38),Math.PI*2)

                        //move slides down and let go of specamen
                        .stopAndAdd(claw.ClawOpen())
                        .waitSeconds(0.1)
                        .waitSeconds(0.2)


                        //go to get to samples on feild
                        .strafeTo(new Vector2d(-35,35))

                        //push one in observatoin
                        .waitSeconds(0.1)
                        .lineToY(13)
                        .splineToLinearHeading(new Pose2d(-47,10,Math.toRadians(90)),Math.PI *2)
                        .waitSeconds(0.1)
                        .lineToY(60)
                        .lineToY(10)

                        //push into observation
                        .splineToLinearHeading(new Pose2d(-58,10,Math.toRadians(90)),Math.PI/2)
                        .lineToY(13)
                        .waitSeconds(0.1)
                        .lineToY(60)

                        //get in posision to pick one up
                        .splineToLinearHeading(new Pose2d(-47,57,Math.toRadians(90)),Math.PI * 2)
                        .waitSeconds(1)
                        .setTangent(0)

                        //sample 2

                        //go to bar
                        .splineToLinearHeading(new Pose2d(1,38,Math.toRadians(270)),Math.PI*2)
                        //move slides up
                        .waitSeconds(0.5)

                        //score it
                        .waitSeconds(1)

                        //let go of it
                        .waitSeconds(0.2)

                        //get last one
                        .strafeTo(new Vector2d(-17,44))

                        //put slides up

                        //travle to get it
                        .splineToSplineHeading(new Pose2d(-47,57,Math.toRadians(90)),Math.PI)
                        .waitSeconds(1)

                        //grab

                        //sample 3

                        //travle to bar
                        .splineToLinearHeading(new Pose2d(1,38,Math.toRadians(270)),Math.PI*2)
                        .waitSeconds(0.1)

                        //score it
                        .waitSeconds(0.6)

                        //let go of it
                        .setTangent(0)
                        //sample 4

                        //travle to bar
                        .splineToLinearHeading(new Pose2d(1,38,Math.toRadians(270)),Math.PI*2)
                        .waitSeconds(0.1)

                        //score it.waitSeconds(0.6)

                        //let go of it
                        //park
                        .strafeTo(new Vector2d(-17,44))
                        .splineToSplineHeading(new Pose2d(-47,57,Math.toRadians(90)),Math.PI)
                        .build()
        );

    }
}
