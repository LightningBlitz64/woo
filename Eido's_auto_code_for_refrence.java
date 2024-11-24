package org.firstinspires.ftc.teamcode.M12;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.lang.annotation.Target;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import android.util.Size;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous()
public class M12EncoderAutoOnbot extends LinearOpMode {


  DcMotorEx intake, Lextend, Rextend, arm, fl, fr, bl, br;
  Servo wrist, hooks;

  public String alliance = "None";
  public String side = "None";
  public String placement = "None"; //this is the variable for which spot the robot should score
  public int waitTime = 0;
  public boolean grabStack = true; //grabstack means robot will go pick up 2 pixels and deposit

  private ElapsedTime runtime = new ElapsedTime();


static final double COUNTS_PER_MOTOR_REV = 1440; // eg: TETRIX Motor Encoder
static final double DRIVE_GEAR_REDUCTION = 1.0; // No External Gearing.
static final double WHEEL_DIAMETER_INCHES = 4.0; // For figuring circumference
static final double COUNTS_PER_INCH = ((COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
  (WHEEL_DIAMETER_INCHES * 3.1415)) / 3.5625;

@Override
public void runOpMode() {
  
  intake = hardwareMap.get(DcMotorEx.class, "intake");
  Lextend = hardwareMap.get(DcMotorEx.class, "Lelevator");
  Rextend = hardwareMap.get(DcMotorEx.class, "Relevator");
  arm = hardwareMap.get(DcMotorEx.class, "arm");
  fl = hardwareMap.get(DcMotorEx.class, "fl");
  fr = hardwareMap.get(DcMotorEx.class, "fr");
  bl = hardwareMap.get(DcMotorEx.class, "bl");
  br = hardwareMap.get(DcMotorEx.class, "br");
  
  fl.setTargetPosition(0);
  fr.setTargetPosition(0);
  bl.setTargetPosition(0);
  br.setTargetPosition(0);
  fl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
  fr.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
  bl.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
  br.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
  
  Lextend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  Rextend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  
  intake.setPower(0);
  
  //reverse drivetrainmotors
  fr.setDirection(DcMotorEx.Direction.REVERSE);
  br.setDirection(DcMotorEx.Direction.REVERSE);
  
  arm.setTargetPosition(-700);
  arm.setPower(1.0);
  arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
  arm.setTargetPosition(-700); //it doesn't crash when we keep this in so idk
  
  //Lextend.setDirection(DcMotorEx.Direction.REVERSE);
  //Rextend.setDirection(DcMotorEx.Direction.REVERSE)
  
  Lextend.setTargetPosition(0);
  Lextend.setPower(1.0);
  Lextend.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
  Lextend.setTargetPosition(0);
  
  Rextend.setTargetPosition(0);
  Rextend.setPower(1.0);
  Rextend.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
  Rextend.setTargetPosition(0);
  
  wrist = hardwareMap.get(Servo.class, "wrist");
  hooks = hardwareMap.get(Servo.class, "hooks");
  
  wrist.setPosition(0.5);
  hooks.setPosition(1);
  
  

  //grab location of team element
  //press b for red alliance and x for blue alliance
  while (!opModeIsActive() && !isStopRequested()) {
    //Running pipeline
    if (gamepad1.x) {
      alliance = "Blue";
    } else if (gamepad1.b) {
      alliance = "Red";
    }
    
    if (gamepad1.right_bumper) {
      side = "Basket";
    } 
    else if (gamepad1.left_bumper)
    {
      side = "Loser Zone";
    }
    
    if(gamepad1.dpad_up)
    {
      waitTime = waitTime + 1;
      sleep(500);
    }
    else if (gamepad1.dpad_down)
    {
      waitTime = waitTime - 1;
      sleep(500);
    }

    //determine if we should grab the stack
    if (gamepad1.a) {
      grabStack = true;
    } else if (gamepad1.y) {
      grabStack = false;
    }

    telemetry.addData("--M12 2024 High Basket Auto--", true);
    telemetry.addData("alliance: ", alliance);
    telemetry.addData("side", side);
    telemetry.addData("GrabStack?: ", grabStack);
    telemetry.addData("WaitTime: ", waitTime);

    telemetry.addData("Press X for Blue alliance", true);
    telemetry.addData("Press B for Red alliance", true);
    telemetry.addData("Press A for Grabbing Stack", true);
    telemetry.addData("Press Y for NOT Grabbing Stack", true);
    telemetry.update();
  }

  waitTime = Math.abs(waitTime);
  
  intake.setPower(-0.25);
  drive(0.2,-10,0,5);
  highbasket();
  sleep(2000);
  outTake();
  drive(0.2,10,0,5);
  sleep(2000);
  home();

  telemetry.addLine("DID NOT SELECT TEAM COLOR");
  telemetry.update();
  sleep(9999);
}
public void outTake()
{
  intake.setPower(-1);
  wrist.setPosition(0.3);
  sleep(5000);
  intake.setPower(0);
}

public void highbasket() {
  arm.setTargetPosition(-1900);
  sleep(1000);
  int extendTarget = 3500;
  Rextend.setTargetPosition(extendTarget);
  Lextend.setTargetPosition(-extendTarget);
}

public void home() {
  Rextend.setTargetPosition(0);
  Lextend.setTargetPosition(-0);
  sleep(5000);
  arm.setTargetPosition(0);

}

public void drive(double speed, double inches, double angle, double timeoutS) {
  // Ensure that the OpMode is still active
  if (opModeIsActive()) {

    fl.setTargetPosition(fl.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH) + (int)(angle * 6.5));
    fr.setTargetPosition(fr.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH) - (int)(angle * 6.5));
    bl.setTargetPosition(bl.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH) + (int)(angle * 6.5));
    br.setTargetPosition(br.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH) - (int)(angle * 6.5));

    // reset the timeout time and start motion.
    runtime.reset();

    while (opModeIsActive() &&
      (runtime.seconds() < timeoutS) &&
      (fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy())) {

      double realSpeed = Math.min(runtime.seconds() * speed * 2, speed);

      fl.setPower(realSpeed);
      bl.setPower(realSpeed);
      fr.setPower(realSpeed);
      br.setPower(realSpeed);

      // Display it for the driver.
      telemetry.addData("Running to", " %7d :%7d", fl.getTargetPosition(), fr.getTargetPosition());
      telemetry.addData("Currently at", " at %7d :%7d", fl.getCurrentPosition(), fr.getCurrentPosition());
      telemetry.update();
    }

    // Stop all motion;
    fl.setPower(0);
    bl.setPower(0);
    fr.setPower(0);
    br.setPower(0);

    sleep(250); // optional pause after each move.
  }
}

public void strafe(double speed, double inches, double angle, double timeoutS) {

  // Ensure that the OpMode is still active
  if (opModeIsActive()) {

    // Determine new target position, and pass to motor controller
    fl.setTargetPosition((fl.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH)) + (int)(angle * 6.5));
    bl.setTargetPosition((bl.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH)) + (int)(angle * 6.5));
    fr.setTargetPosition((fr.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH)) - (int)(angle * 6.5));
    br.setTargetPosition((br.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH)) - (int)(angle * 6.5));

    // reset the timeout time and start motion.
    runtime.reset();
    fl.setPower(speed);
    bl.setPower(speed);
    fr.setPower(speed);
    br.setPower(speed);

    while (opModeIsActive() &&
      (runtime.seconds() < timeoutS) &&
      (fl.isBusy() && fr.isBusy())) {

      // Display it for the driver.
      telemetry.addData("Running to", " %7d :%7d", fl.getTargetPosition(), fr.getTargetPosition());
      telemetry.addData("Currently at", " at %7d :%7d", fl.getCurrentPosition(), fr.getCurrentPosition());
      telemetry.update();
    }

    // Stop all motion;
    fl.setPower(0);
    bl.setPower(0);
    fr.setPower(0);
    br.setPower(0);

    sleep(250); // optional pause after each move.
  }
}

}
