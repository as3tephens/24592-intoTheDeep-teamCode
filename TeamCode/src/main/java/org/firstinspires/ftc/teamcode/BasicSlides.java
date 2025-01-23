package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class BasicSlides {
    public DcMotor slides;
    public DcMotor TR;

    BasicSlides(HardwareMap hardwareMap)
    {
        slides = hardwareMap.get(DcMotor.class,"slides");
        TR = hardwareMap.get(DcMotor.class,"TR");
    }

    public class SlidesUp implements Action
    {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            TR.setTargetPosition(-6000);
            TR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            return false;
        }
    }
    public Action SlidesUp(){
        return new SlidesUp();
    }
}
