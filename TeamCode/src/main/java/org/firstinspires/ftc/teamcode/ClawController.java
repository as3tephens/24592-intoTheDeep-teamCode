package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ClawController {
    private Servo clawServo;       // Servo controlling the claw grip
    private Servo clawPitchServo;  // Servo controlling the claw pitch

    // Claw positions
    private static final double CLAW_OPEN_POSITION = 1.0;     // Replace with actual servo position
    private static final double CLAW_CLOSED_POSITION = 0.0;   // Replace with actual servo position

    // Claw pitch positions
    private static final double PITCH_UP_POSITION = 1.0;      // Replace with actual servo position
    private static final double PITCH_DOWN_POSITION = 0.0;    // Replace with actual servo position

    public ClawController(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        clawPitchServo = hardwareMap.get(Servo.class, "clawPitchServo");
    }

    // Open the claw
    public void open() {
        clawServo.setPosition(CLAW_OPEN_POSITION);
    }

    // Close the claw
    public void close() {
        clawServo.setPosition(CLAW_CLOSED_POSITION);
    }

    // Pitch the claw up
    public void pitchUp() {
        clawPitchServo.setPosition(PITCH_UP_POSITION);
    }

    // Pitch the claw down
    public void pitchDown() {
        clawPitchServo.setPosition(PITCH_DOWN_POSITION);
    }
}
