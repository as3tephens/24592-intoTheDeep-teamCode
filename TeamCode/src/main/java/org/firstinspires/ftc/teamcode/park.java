package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import androidx.annotation.NonNull;

//rr imports
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

//ftc imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "park")
public class park extends LinearOpMode {
    @Override

    public void runOpMode() throws InterruptedException {
        //init drive train
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
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
                drive.actionBuilder(new Pose2d(-17, 62, Math.toRadians(270)))
                        .waitSeconds(3)
                        .strafeTo(new Vector2d(-27, 65))
                        .build()
        );
        drive.setDrivePowers(new PoseVelocity2d(new Vector2d(-17, 62), 0.5));
    }
}


