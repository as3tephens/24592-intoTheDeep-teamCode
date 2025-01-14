package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import androidx.annotation.NonNull;
import androidx.annotation.NonNull;

//rr imports
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

//ftc imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "OverPark")
public class OverPark extends LinearOpMode {
    //MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        //wait for start
        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(35, 62, Math.toRadians(180)))
                        //.waitSeconds(3)
                        //.strafeTo(new Vector2d(-27, 65))
                        .lineToX(25)
                        .build()
        );
        //drive.setDrivePowers(new PoseVelocity2d(new Vector2d(-17, 62), 0.5));
    }
}
