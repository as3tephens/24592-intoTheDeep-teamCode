package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareMap {
    public DcMotor FL;
    public DcMotor BL;
    public DcMotor FR;
    public DcMotor BR;
    public Servo roll;
    public Servo pitch;
    public Servo claw;

    //gives hardware map
    public void hardwareMap()
    {
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");


    }
}
