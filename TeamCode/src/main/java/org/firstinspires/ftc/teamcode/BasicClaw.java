package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class BasicClaw {
//servo variables
    public Servo claw;
    public Servo pitch;
    public Servo roll;
    private static double clawClosed = 0;
    private static double clawOpen = 0.5;
    //TODO: Put values for pitch and roll
    private static double pitchLevel = 0;
    private static double rollUp = 0;
    private static double rollDown = 0;

    BasicClaw(HardwareMap hardwareMap)
    {
        claw = hardwareMap.get(Servo.class,"claw");
        pitch = hardwareMap.get(Servo.class,"pitch");
        roll = hardwareMap.get(Servo.class,"roll");

        roll.setPosition(0);
        pitch.setPosition(0);
        claw.setPosition(0);
    }

    //close claw class
    public class ClawClose implements Action
    {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(clawClosed);
            return false;
        }
    }
    //close claw action
    public Action ClawClose() { return new ClawClose();}

    //claw open class
    public class ClawOpen implements Action
    {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(clawOpen);
            return false;
        }
    }
    //claw open action
    public Action ClawOpen(){
        return  new ClawOpen();
    }

    //set pitch position to level class
    public class PitchLevel implements Action
    {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            pitch.setPosition(pitchLevel);
            return false;
        }
    }

    //pitch level action
    public Action PitchLevel() {return new PitchLevel();}

    //roll set to up class
    public class RollUp implements Action
    {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            roll.setPosition(rollUp);
            return false;
        }
    }

    //roll up action
    public Action RollUp(){return new RollUp();}

    //roll down action
    public class RollDown implements Action
    {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            roll.setPosition(rollDown);
            return false;
        }
    }

    //roll down action
    public Action RollDown() {return new RollDown();}
}
