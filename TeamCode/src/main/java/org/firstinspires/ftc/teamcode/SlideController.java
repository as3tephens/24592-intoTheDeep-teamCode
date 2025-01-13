package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideController {
    private DcMotor slideMotor; // Motor controlling the slides
    private Servo slideAngleServo; // Servo for angling the slides

    // Slide positions and angles
    private static final int SLIDE_EXTENDED_POSITION = 1000; // Replace with actual encoder value
    private static final int SLIDE_RETRACTED_POSITION = 0;   // Replace with actual encoder value
    private static final double ANGLE_UP_POSITION = 1.0;     // Replace with actual servo position
    private static final double ANGLE_DOWN_POSITION = 0.0;   // Replace with actual servo position

    public SlideController(HardwareMap hardwareMap) {
        slideMotor = hardwareMap.get(DcMotor.class, "slideMotor");
        slideAngleServo = hardwareMap.get(Servo.class, "slideAngleServo");

        // Set up motor mode
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // Extend the slides
    public void extend() {
        slideMotor.setTargetPosition(SLIDE_EXTENDED_POSITION);
        slideMotor.setPower(1.0); // Adjust speed as needed
        while (slideMotor.isBusy()) {
            // Wait for the slides to reach the extended position
        }
        slideMotor.setPower(0);
    }

    // Retract the slides
    public void retract() {
        slideMotor.setTargetPosition(SLIDE_RETRACTED_POSITION);
        slideMotor.setPower(1.0); // Adjust speed as needed
        while (slideMotor.isBusy()) {
            // Wait for the slides to reach the retracted position
        }
        slideMotor.setPower(0);
    }

    // Set slides to angle up
    public void setAngleUp() {
        slideAngleServo.setPosition(ANGLE_UP_POSITION);
    }

    // Set slides to angle down
    public void setAngleDown() {
        slideAngleServo.setPosition(ANGLE_DOWN_POSITION);
    }
}
