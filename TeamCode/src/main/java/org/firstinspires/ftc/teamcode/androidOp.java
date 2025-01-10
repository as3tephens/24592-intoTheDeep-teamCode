package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "androidOp")
public class androidOp extends LinearOpMode {

    private DcMotor BL;
    private DcMotor BR;
    private DcMotor FL;
    private DcMotor FR;
    private DcMotor TL;
    private DcMotor TR;
    private Servo roll;
    private Servo pitch;
    private Servo claw;
    private DcMotor slides;
    // Software limits for the slide (you can modify these)
    private static final double SLIDE_MIN_POSITION = 0.0;  // minimum position (e.g., bottom of slide)
    private static final double SLIDE_MAX_POSITION = 4750;  // maximum position (e.g., top of slide)
    private static final double SLIDE_SPEED = 1;        // speed multiplier for the slide movement


    @Override
    public void runOpMode() {
        double rotate2 = 0;
        double rol = 0.5;

        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");


        pitch = hardwareMap.get(Servo.class, "pitch");
        roll = hardwareMap.get(Servo.class, "roll");
        claw = hardwareMap.get(Servo.class, "claw");


        slides = hardwareMap.get(DcMotor.class, "slides");
        slides.setDirection(DcMotorSimple.Direction.REVERSE);
        //slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        TL = hardwareMap.get(DcMotor.class,"TL");
        TR = hardwareMap.get(DcMotor.class,"TR");
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TL.setDirection(DcMotor.Direction.REVERSE);

        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BL.setDirection(DcMotor.Direction.REVERSE);
        FL.setDirection(DcMotor.Direction.REVERSE);


        claw.setPosition(0);
        pitch.setPosition(0);
        roll.setPosition(0);

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                //
                //slides.setPower(gamepad2.left_stick_y);
                //slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


                BL.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x/2);
                BR.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x/2);
                FL.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x/2);
                FR.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x/2);


                TR.setPower(gamepad2.left_stick_y);

                //broken strafing
        /*if (gamepad1.left_bumper) {
          FL.setPower(-1);
          FR.setPower(1);
          BR.setPower(-1);
          BL.setPower(-5);
        }else if (gamepad1.right_bumper) {
          FL.setPower(1);
          FR.setPower(1);
          BR.setPower(1);
          BL.setPower(1);
        }*/

                //claw rotation
                if (gamepad2.dpad_left) {
                    rol += 0.1;
                    sleep(100);
                } else if (gamepad2.dpad_right) {
                    rol -= 0.1;
                    sleep(100);
                }

                //claw angle
                if (gamepad2.dpad_up) {
                    rotate2 += 0.1;
                    sleep(200);
                } else if (gamepad2.dpad_down) {
                    rotate2 -= 0.1;
                    sleep(200);
                }

                //varables cant go past 1
                if (rotate2 < 0)
                {
                    rotate2 = 0;
                } else if (rotate2 > 1)
                {
                    rotate2 = 1;
                }

                if (rol > 1)
                {
                    rol = 1;
                } else if (rol < 0)
                {
                    rol = 0;
                }
                if (gamepad2.a)
                {
                    claw.setPosition(0.5);
                } else if(gamepad2.b)
                {
                    claw.setPosition(0);
                }
                if (gamepad2.x)
                {
                    rol = 0.6;
                    rotate2 = 0.7;
                } else if (gamepad2.y)
                {
                    rol = 0;
                }
                // Get joystick input (negative is up, positive is down)
                double joystickInput = -gamepad2.right_stick_y;  // Invert if needed (up = negative, down = positive)

                // Map joystick input to slide speed
                double slidePower = joystickInput * SLIDE_SPEED;

                // Apply the software limits
                if (slides.getCurrentPosition() <= SLIDE_MIN_POSITION && slidePower < 0) {
                    slidePower = 0;  // Prevent moving down past the minimum limit
                } else if (slides.getCurrentPosition() >= SLIDE_MAX_POSITION && slidePower > 0) {
                    slidePower = 0;  // Prevent moving up past the maximum limit
                }

                // Set motor power to the calculated slidePower
                slides.setPower(slidePower);

                // Add telemetry for debugging
                telemetry.addData("Slide Position", slides.getCurrentPosition());
                telemetry.addData("Joystick Input", joystickInput);
                pitch.setPosition(rotate2);
                roll.setPosition(rol);
                //nate.setPosition(rotate2);
                telemetry.addData("rotate", rotate2);
                telemetry.addData("Left Pow", BL.getPower());
                telemetry.addData("Right Pow", BR.getPower());
                telemetry.addData("rol: ",rol);
                telemetry.update();
            }
        }
    }
}