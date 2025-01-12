package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Dynamic Slide and Single Angle Control")
public class DynamicSlideAndSingleAngleControl extends LinearOpMode {

    private DcMotorEx slideMotor;
    private DcMotorEx angleMotor;

    // Software limits for the slides
    private final int LOWER_LIMIT = 0; // Minimum encoder position (retracted)
    private final int MAX_EXTENSION_LIMIT = 5000; // Maximum encoder position (fully extended)

    // Angle limits
    private final int ANGLE_MIN = 0; // Minimum angle position (fully lowered)
    private final int ANGLE_MAX = 1000; // Maximum angle position (fully raised)

    @Override
    public void runOpMode() {
        // Initialize motors
        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");
        angleMotor = hardwareMap.get(DcMotorEx.class, "angleMotor");

        // Reset encoders and set run mode
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        angleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        angleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            // Slide motor control (gamepad1.left_stick_y)
            int currentPosition = slideMotor.getCurrentPosition();
            int dynamicUpperLimit = calculateDynamicUpperLimit(currentPosition);
            double slidePower = -gamepad1.left_stick_y;

            // Enforce software limits for slide extension
            if (currentPosition <= LOWER_LIMIT && slidePower < 0) {
                slidePower = 0;
            } else if (currentPosition >= dynamicUpperLimit && slidePower > 0) {
                slidePower = 0;
            }
            slideMotor.setPower(slidePower);

            // Angle motor control (gamepad2.left_stick_y)
            int anglePosition = angleMotor.getCurrentPosition();
            double anglePower = -gamepad2.left_stick_y;

            // Enforce software limits for the angle motor
            if (anglePosition <= ANGLE_MIN && anglePower < 0) {
                anglePower = 0;
            } else if (anglePosition >= ANGLE_MAX && anglePower > 0) {
                anglePower = 0;
            }
            angleMotor.setPower(anglePower);

            // Telemetry for debugging
            telemetry.addData("Slide Position", currentPosition);
            telemetry.addData("Dynamic Upper Limit", dynamicUpperLimit);
            telemetry.addData("Angle Position", anglePosition);
            telemetry.update();
        }
    }

    /**
     * Calculates the dynamic upper limit based on the slide's current position.
     * @param currentPosition The current position of the slides (encoder value).
     * @return The dynamically calculated upper limit.
     */
    private int calculateDynamicUpperLimit(int currentPosition) {
        // Example logic: Allow more extension as the slide moves up
        if (currentPosition < 1000) {
            return LOWER_LIMIT + 2000;
        } else if (currentPosition < 3000) {
            return LOWER_LIMIT + 4000;
        } else {
            return MAX_EXTENSION_LIMIT;
        }
    }
}
