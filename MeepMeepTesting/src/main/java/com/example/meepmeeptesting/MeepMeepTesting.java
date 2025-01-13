package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-27, 62, Math.toRadians(-90)))
                        .setTangent(0)
                        //first sample

                        //move slides up

                        //go to bar
                        .splineToConstantHeading(new Vector2d(0,38),Math.PI*2)

                        //move slides down and let go of specamen
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
                        .setTangent(0)
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
                        .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)

                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}