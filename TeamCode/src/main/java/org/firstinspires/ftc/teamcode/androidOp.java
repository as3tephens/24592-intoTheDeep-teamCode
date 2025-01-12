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
    private DcMotor TR;
    private Servo roll;
    private Servo pitch;
    private Servo claw;
    private DcMotor slides;
    // Software limits for the slide (you can modify these)
    private final int LOWER_LIMIT = 0; // Minimum encoder position (retracted)
    private final int MAX_EXTENSION_LIMIT = 5000; // Maximum encoder position (fully extended)
    private int limit = 0;

    // Angle limits
   // private final int ANGLE_MIN = 2000; // Minimum angle position (fully lowered)
    //private final int ANGLE_MAX = 4000; // Maximum angle position (fully raised)


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

        TR = hardwareMap.get(DcMotor.class, "TR");
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Reset encoders and set run mode
        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        TR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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


                BL.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x / 2);
                BR.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x / 2);
                FL.setPower(-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x / 2);
                FR.setPower(-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x / 2);


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
                if (rotate2 < 0) {
                    rotate2 = 0;
                } else if (rotate2 > 1) {
                    rotate2 = 1;
                }

                if (rol > 1) {
                    rol = 1;
                } else if (rol < 0) {
                    rol = 0;
                }
                if (gamepad2.a) {
                    claw.setPosition(0.5);
                } else if (gamepad2.b) {
                    claw.setPosition(0);
                }
                if (gamepad2.x) {
                    rol = 0.6;
                    rotate2 = 0.7;
                } else if (gamepad2.y) {
                    rol = 0;
                }

                if (gamepad2.right_stick_button){
                    limit = 6000;
                } else if (gamepad2.left_stick_button)
                {
                    limit = 4000;
                }
                // Slide motor control (gamepad2.right_stick_y)
                int currentPosition = slides.getCurrentPosition();
                double slidePower = -gamepad2.right_stick_y;
                int dynamicUpperLimit = (limit);

                // software limits for slide extension
                if (currentPosition <= LOWER_LIMIT && slidePower < 0) {
                    slidePower = 0;
                } else if (currentPosition >= dynamicUpperLimit && slidePower > 0) {
                    slidePower = 0;
                }
                slides.setPower(slidePower);

                // TR control (gamepad2.left_stick_y)
                /*int anglePosition = TR.getCurrentPosition();
                double anglePower = -gamepad2.left_stick_y;

                //  software limits for TR
                if (anglePosition <= ANGLE_MIN && anglePower < 0) {
                    anglePower = 0;
                } else if (anglePosition >= ANGLE_MAX && anglePower > 0) {
                    anglePower = 0;
                }

                TR.setPower(anglePower);
*/
                // Telemetry for debugging
                telemetry.addData("Slide Power", slidePower);
                telemetry.addData("Slide Position", currentPosition);
                telemetry.addData("Dynamic Upper Limit", dynamicUpperLimit);
                //telemetry.addData("Angle Position", anglePosition);
                pitch.setPosition(rotate2);
                roll.setPosition(rol);
                telemetry.addData("rotate", rotate2);
                telemetry.addData("Left Pow", BL.getPower());
                telemetry.addData("Right Pow", BR.getPower());
                telemetry.addData("rol: ", rol);
                telemetry.update();
            }
        }
    }
}

    /**
     * Calculates the dynamic upper limit based on the slide's current position.
     * @param currentPosition The current position of the slides (encoder value).
     * @return The dynamically calculated upper limit.
     */
/*    private int calculateDynamicUpperLimit(int currentPosition) {
        //Allow more extension as the slide moves up
        if (currentPosition < 1000) {
            return LOWER_LIMIT + 4000;
        } else if (currentPosition < 3000) {
            return LOWER_LIMIT + 6000;
        } else {
            return MAX_EXTENSION_LIMIT;
        }
    }
}
*/