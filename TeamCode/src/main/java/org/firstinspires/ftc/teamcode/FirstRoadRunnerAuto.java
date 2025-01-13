package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

//rr imports
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

//ftc imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "first Road Runner Auto")
public class FirstRoadRunnerAuto extends LinearOpMode
{
    @Override

    public void runOpMode() throws InterruptedException
    {
        //init drive train
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));

        //init claw servos
        Servo claw = hardwareMap.servo.get("claw");
        Servo pitch = hardwareMap.servo.get("pitch");
        Servo roll = hardwareMap.servo.get("roll");

        //"warm up" servos
        claw.setPosition(0);
        pitch.setPosition(0);
        roll.setPosition(0);

        //init slides and tower motors
        DcMotor TR = hardwareMap.dcMotor.get("TR");
        DcMotor slides = hardwareMap.dcMotor.get("slides");


        //wait for start
        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(-27, 62, Math.toRadians(270)))
                        .setTangent(0)
                        //first sample

                        //move slides up
                        .stopAndAdd(new MoveSlides(TR,1,1000))

                        //go to bar
                        .splineToConstantHeading(new Vector2d(0,38),Math.PI*2)

                        //move slides down and let go of specamen
                        .stopAndAdd(new MoveSlides(TR,1,500))
                        .waitSeconds(0.1)
                        .stopAndAdd(new MoveClaw(claw,roll,pitch,0,0,0))
                        .waitSeconds(0.2)


                        //go to get to samples on feild
                        .strafeTo(new Vector2d(-35,35))

                        //push one in observatoin
                        .waitSeconds(0.1)
                        .lineToY(13)
                        .splineToLinearHeading(new Pose2d(-47,10,Math.toRadians(90)),Math.PI *2)
                        .waitSeconds(0.1)
                        .lineToY(60)

                        //push into observation
                        .splineToLinearHeading(new Pose2d(-58,10,Math.toRadians(90)),Math.PI/2)
                        .lineToY(13)
                        .waitSeconds(0.1)
                        .lineToY(60)

                        //get in posision to pick one up
                        .splineToLinearHeading(new Pose2d(-47,57,Math.toRadians(90)),Math.PI * 2)
                        .waitSeconds(1)
                        .stopAndAdd(new MoveClaw(claw,roll,pitch,0.4,0,0))
                        .setTangent(0)

                        //sample 2

                        //go to bar
                        .splineToLinearHeading(new Pose2d(1,38,Math.toRadians(270)),Math.PI*2)
                        //move slides up
                        .stopAndAdd(new MoveSlides(TR,1,500))
                        .waitSeconds(0.5)

                        //score it
                        .stopAndAdd(new MoveSlides(TR,-1,500))
                        .waitSeconds(1)

                        //let go of it
                        .stopAndAdd(new MoveClaw(claw,roll,pitch,0,0,0))
                        .waitSeconds(0.2)

                        //get last one
                        .strafeTo(new Vector2d(-17,44))

                        //put slides up
                        .stopAndAdd(new MoveSlides(TR,1,500))

                        //travle to get it
                        .splineToSplineHeading(new Pose2d(-47,57,Math.toRadians(90)),Math.PI)
                        .waitSeconds(1)

                        //grab
                        .stopAndAdd(new MoveClaw(claw,roll,pitch,0.4,0,0))
                        .setTangent(0)
                        //sample 3

                        //travle to bar
                        .splineToLinearHeading(new Pose2d(1,38,Math.toRadians(270)),Math.PI*2)
                        .waitSeconds(0.1)

                        //score it
                        .stopAndAdd(new MoveSlides(TR,-1,500))
                        .waitSeconds(0.6)

                        //let go of it
                        .stopAndAdd(new MoveClaw(claw,roll,pitch,0,0,0))
                        //grab last one
                        .stopAndAdd(new MoveClaw(claw,roll,pitch,0.4,0,0))
                        .setTangent(0)
                        //sample 4

                        //travle to bar
                        .splineToLinearHeading(new Pose2d(1,38,Math.toRadians(270)),Math.PI*2)
                        .waitSeconds(0.1)

                        //score it
                        .stopAndAdd(new MoveSlides(TR,-1,500))
                        .waitSeconds(0.6)

                        //let go of it
                        .stopAndAdd(new MoveClaw(claw,roll,pitch,0,0,0))
                        //park
                        .strafeTo(new Vector2d(-17,44))
                        .splineToSplineHeading(new Pose2d(-47,57,Math.toRadians(90)),Math.PI)
                        .build()
        );
    }




    public class MoveSlides implements Action{
        DcMotor TR;
        int sleep;
        double power;
        public MoveSlides(DcMotor d, double p, int s){
            this.TR = d;
            this.power = p;
            this.sleep = s;

        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            TR.setPower(power);
            sleep(sleep);
            TR.setPower(0);
            return false;
        }
    }
    public class MoveClaw implements Action{
        Servo claw;
        Servo roll;
        Servo pitch;
        double positionC;
        double positionR;
        double positionP;
        public MoveClaw(Servo s, Servo s1, Servo s2, double c, double p, double r){
            this.claw = s;
            this.roll = s1;
            this.pitch = s2;
            this.positionP = p;
            this.positionC = c;
            this.positionR = r;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(positionC);
            roll.setPosition(positionR);
            pitch.setPosition(positionP);
            return false;
        }
    }

}
