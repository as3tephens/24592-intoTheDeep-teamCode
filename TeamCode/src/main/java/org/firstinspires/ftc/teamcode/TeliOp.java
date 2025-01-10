package org.firstinspires.ftc.teamcode;

/*
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "TeleOp")

public class TeliOp extends LinearOpMode {
    @Override
    private DcMotor FL = null;
    private DcMotor FR = null;
    private DcMotor BL = null;
    private DcMotor BR = null;
    private DcMotor slides = null;
    private DcMotor TR = null;
    private Servo claw = null;
    private Servo roll = null;
    private Servo pitch = null;



    public void runOpMode() throws InterruptedException {
        //hardware map drive motors
        FL = hardwareMap.get(DcMotor.class,"FL");
        FR = hardwareMap.get(DcMotor.class,"FR");
        BL = hardwareMap.get(DcMotor.class,"BL");
        BR = hardwareMap.get(DcMotor.class,"BR");

        //set motors to break
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //set motor directions
        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);

        //Slide motors hardware map
        TR = hardwareMap.get(DcMotor.class,"TR");
        slides = hardwareMap.get(DcMotor.class,"slides");

        //reversing motor
        slides.setDirection(DcMotorSimple.Direction.REVERSE);

        //claw servo hardware map
        claw = hardwareMap.get(Servo.class,"claw");
        roll = hardwareMap.get(Servo.class,"roll");
        pitch = hardwareMap.get(Servo.class,"pitch");

        //"warm up" servos
        claw.setPosition(1);
        roll.setPosition(0);
        pitch.setPosition(0);

        waitForStart();
        while(opModeIsActive())
        {

        }
    }
}

 */