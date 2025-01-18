package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@Autonomous(name = "reset")
public class Reset extends LinearOpMode {
    private DcMotor TR;
    private DcMotor slides;
    private Servo claw;
    private Servo roll;
    private Servo pitch;

    @Override
    public void runOpMode() throws InterruptedException {
    TR = hardwareMap.get(DcMotor.class, "TR");
        slides = hardwareMap.get(DcMotor.class,"slides");
        claw = hardwareMap.get(Servo.class,"claw");
        roll = hardwareMap.get(Servo.class,"roll");
        pitch = hardwareMap.get(Servo.class,"pitch");
        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        claw.setPosition(0);
        pitch.setPosition(0);
        roll.setPosition(0);
    waitForStart();
        slides.setTargetPosition(0);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slides.setPower(0.5);
        if (slides.getCurrentPosition() == 0)
        {
            TR.setTargetPosition(0);
            TR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            TR.setPower(0.5);
        }
    }
}
