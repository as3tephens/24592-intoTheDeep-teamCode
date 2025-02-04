package Xperamentals;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawControler {
    private Servo clawServo;       // Servo controlling the claw grip
    private Servo clawPitch;  // Servo controlling the claw pitch

    /** 1800 degree servo*/
    private Servo armPitchR;
    /** 300 degree servo*/
    private Servo armPitchL;
    private Servo armClaw;

    // Claw positions
    private static final double CLAW_OPEN = 1.0;
    private static final double CLAW_CLOSED = 0.0;

    // Claw pitch positions
    private static final double PITCH_UP = 1.0;
    private static final double PITCH_DOWN = 0.0;

    //arm pitch positions
    private static final double ARM_WALL = 0.0;
    private static final double ARM_HIGH_CHAMBER = 0.0;

    //arm claw positions
    private static final double ARM_CLAW_CLOSED = 0.0;
    private static final double ARM_CLAW_OPEN = 1.0;

    //arm pitch positions
    private static final double ARM_PITCH = 0.0;


    public void ClawController(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        clawPitch = hardwareMap.get(Servo.class, "clawPitch");
        armPitchL = hardwareMap.get(Servo.class,"armPitchL");
        armPitchR = hardwareMap.get(Servo.class,"armPitchR");
        armClaw = hardwareMap.get(Servo.class,"armClaw");
    }

    // Open the claw
    public void open() {
        clawServo.setPosition(CLAW_OPEN);
    }

    // Close the claw
    public void close() {
        clawServo.setPosition(CLAW_CLOSED);
    }

    // Pitch the claw up
    public void pitchUp() {
        clawPitch.setPosition(PITCH_UP);
    }

    // Pitch the claw down
    public void pitchDown() {
        clawPitch.setPosition(PITCH_DOWN);
    }

    //Pitch the arm to wall pickup position
    public void armWall() {
        pitchArm(ARM_WALL);
    }

    //pitch the arm to high chamber position
    public void armHighChamber(){
     pitchArm(ARM_HIGH_CHAMBER);
    }

    //open arm claw
    public void armClawOpen() {
        armClaw.setPosition(ARM_CLAW_OPEN);
    }

    //close arm claw
    public void armClawClose() {
        armClaw.setPosition(ARM_CLAW_CLOSED);
    }

    /**
     * method to rotate the arm a certan amount of degres
     * @param d degres for the arm to rotate
     */
    public void pitchArm(double d) {
        armPitchR.setPosition(d/1800);
        armPitchL.setPosition(d/300);
    }

}
