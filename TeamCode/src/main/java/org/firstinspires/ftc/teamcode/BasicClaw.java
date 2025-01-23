package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class BasicClaw {

    public Servo claw;

    BasicClaw(HardwareMap hardwareMap)
    {
        claw = hardwareMap.get(Servo.class,"claw");

        claw.setPosition(0);
    }

    public class ClawOpen implements Action
    {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(0.5);
            return false;
        }
    }
    public Action ClawOpen(){
        return  new ClawOpen();
    }
}
