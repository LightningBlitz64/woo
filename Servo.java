
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;

@TeleOp(name="Omar")
public class Omar extends LinearOpMode {
    Servo hookL;
    Servo hookR;
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor wrist;
    DcMotor leftShoulder;
    DcMotor rightShoulder;
    BNO055IMU imu;

@Override
    public void runOpMode() {
      hookL = hardwareMap.get(Servo.class, "hookL");
      hookR = hardwareMap.get(Servo.class, "hookR");
        
      // Put initialization blocks here
      waitForStart();
      // Put run blocks here
      while (opModeIsActive()) {
          if (gamepad1.y){
              
              hookL.setPosition(0);
              
          } 
          
          else if(gamepad1.x){
              hookL.setPosition(0.7);
              
          }
          
          else if(gamepad1.a){
              
              hookL.setPosition(1);
              
          }
          
       

      }
    }
    
}
