/*
package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

//RR imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
//FTC imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@Config

@Autonomous(name = "RoadRunnerAuto", group = "Autonomous")

public class IMessedUpTheLastOne extends LinearOpMode
{
    public class Slides
    {
        private DcMotor slides;
        private DcMotor TR;

        public Slides(HardwareMap hardwareMap)
        {
            slides = hardwareMap.get(DcMotor.class, "slides");
            TR = hardwareMap.get(DcMotor.class,"TR");
            slides.setDirection(DcMotorSimple.Direction.REVERSE);
            TR.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    public static class ClawServos
    {
        private Servo claw;
        private Servo roll;
        private Servo pitch;
        public ClawServos(HardwareMap hardwareMap)
        {
            claw = hardwareMap.get(Servo.class,"claw");
            roll = hardwareMap.get(Servo.class,"roll");
            pitch = hardwareMap.get(Servo.class, "pitch");
        }
        public class OpenClaw implements Action
        {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                claw.setPosition(0);
                return false;
            }
        }
        public static class CloseClaw implements Action
        {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                claw.setPosition(1);
                return false;
            }
        }
    }





    @Override
    public void runOpMode() throws InterruptedException
    {
        Pose2d initalPose = new Pose2d(-27,70,Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initalPose);
        ClawServos clawServos = new ClawServos(hardwareMap);
        Slides slide = new Slides(hardwareMap);
        new ClawServos.CloseClaw();

       drive.actionBuilder(new Pose2d(-27, 62, Math.toRadians(-90)))
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(0,38),Math.PI*2)

                .waitSeconds(2)
                .strafeTo(new Vector2d(-35,35))
                .waitSeconds(0.1)
                .lineToY(13)
                .splineToLinearHeading(new Pose2d(-47,10,Math.toRadians(90)),Math.PI *2)
                .waitSeconds(0.1)
                .lineToY(60)
                .lineToY(13)
                .strafeTo(new Vector2d(-55,13))
                .waitSeconds(0.1)
                .lineToY(60)
                .lineToY(13)
                .strafeTo(new Vector2d(-60,13))
                .waitSeconds(0.1)
                .lineToY(60)
                .lineToY(57)
                .strafeTo(new Vector2d(-47,57))
                .waitSeconds(2)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(1,38,Math.toRadians(270)),Math.PI*2)
                .waitSeconds(1)
                .strafeTo(new Vector2d(-50,59))
                .waitSeconds(3)
                .strafeTo(new Vector2d(-50,59))
               .build();

    }


}
*/
