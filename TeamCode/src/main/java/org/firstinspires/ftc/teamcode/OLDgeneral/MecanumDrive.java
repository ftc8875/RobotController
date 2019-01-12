package org.firstinspires.ftc.teamcode.OLDgeneral;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles driving a mecanum drivetrain. The four mecanum wheels should be spaced in a rectangle
 * with even weight distribution on each wheel. Each of the four wheels should be driven by its own
 * motor. Use the same motors with the same gear ratios. This class assumes that the wheels' treads
 * form an 'X', shown below:
 * \ /
 *
 * / \
 *
 * In this class, positive values always indicate FORWARD, RIGHT, and CLOCKWISE.
 * Negative values always indicate BACKWARD, LEFT, and COUNTERCLOCKWISE.
 */
public class MecanumDrive {

    // Names of the four motors in the hardware map. Use these names when configuring the robot
    // on the robot controller phone.
    private static final String FRONT_LEFT_MOTOR_NAME = "front_left_motor";
    private static final String FRONT_RIGHT_MOTOR_NAME = "front_right_motor";
    private static final String REAR_LEFT_MOTOR_NAME = "rear_left_motor";
    private static final String REAR_RIGHT_MOTOR_NAME = "rear_right_motor";

    private HardwareMap hardwareMap;

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearLeftMotor;
    private DcMotor rearRightMotor;

    // Lists of motors to make changing directions and modes slightly easier
    private ArrayList<DcMotor> allMotors = new ArrayList<>();
    private ArrayList<DcMotor> leftMotors = new ArrayList<>();
    private ArrayList<DcMotor> rightMotors = new ArrayList<>();

    /**
     * Make a new MecanumDrive. This constructor also initializes the drivetrain, so the robot can
     * be driven immediately after calling the constructor.
     * @param hardwareMap - the hardwareMap variable within the current OpMode
     */
    public MecanumDrive(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;

        frontLeftMotor = hardwareMap.get(DcMotor.class, FRONT_LEFT_MOTOR_NAME);
        frontRightMotor = hardwareMap.get(DcMotor.class, FRONT_RIGHT_MOTOR_NAME);
        rearLeftMotor = hardwareMap.get(DcMotor.class, REAR_LEFT_MOTOR_NAME);
        rearRightMotor = hardwareMap.get(DcMotor.class, REAR_RIGHT_MOTOR_NAME);

        allMotors.add(frontLeftMotor);
        allMotors.add(frontRightMotor);
        allMotors.add(rearLeftMotor);
        allMotors.add(rearRightMotor);

        leftMotors.add(frontLeftMotor);
        leftMotors.add(rearLeftMotor);

        rightMotors.add(frontRightMotor);
        rightMotors.add(rearRightMotor);

        initialize();
    }

    /**
     * Drives the robot according to the three parameters. Please read the comment before the class
     * definition for more information on how to position the wheels.
     * @param drive - the forward-backward movement of the robot, from -1 to 1. Positive means
     *              FORWARD, negative means BACKWARD.
     * @param turn - the turning motion of the robot, from -1 to 1. Positive means CLOCKWISE,
     *             negative means COUNTERCLOCKWISE.
     * @param strafe - the side-to-side motion of the robot, from -1 to 1. Positive means RIGHT,
     *               negative means LEFT.
     */
    public void drive(double drive, double turn, double strafe) {
        // Math to determine the power to give to each motor. DON'T CHANGE THIS.
        double a = drive + turn + strafe;
        double b = drive - turn - strafe;
        double c = drive - turn + strafe;
        double d = drive + turn - strafe;

        // Because we only can give each motor a power between -1 and 1, we may need to scale down
        // the power values so the powers stay proportional
        double max = maxOfList(Math.abs(a), Math.abs(b), Math.abs(c), Math.abs(d));
        if(max > 1) {
            a /= max;
            b /= max;
            c /= max;
            d /= max;
        }

        // Set the four motors to their respective powers.
        frontLeftMotor.setPower(a);
        frontRightMotor.setPower(b);
        rearRightMotor.setPower(c);
        rearLeftMotor.setPower(d);
    }

    /**
     * Initializes the drivetrain by setting the motor modes and directions, and ensures the motor
     * powers are set to 0.
     */
    private void initialize() {
        setMotorModes(allMotors, DcMotor.RunMode.RUN_USING_ENCODER);
        setMotorDirections(leftMotors, DcMotor.Direction.REVERSE);
        setMotorDirections(rightMotors, DcMotor.Direction.FORWARD);
        drive(0.0, 0.0, 0.0);
    }

    /**
     * Sets the directions of all motors in the list to the specified direction.
     * @param motors
     * @param direction
     */
    private void setMotorDirections(List<DcMotor> motors, DcMotor.Direction direction) {
        for(DcMotor motor : motors) {
            motor.setDirection(direction);
        }
    }

    /**
     * Sets the modes of all motors in the list to the specified mode.
     * @param motors
     * @param mode
     */
    private void setMotorModes(List<DcMotor> motors, DcMotor.RunMode mode) {
        for(DcMotor motor : motors) {
            motor.setMode(mode);
        }
    }

    /**
     * Finds the maximum number in a list of numbers. Because Math.max() only supports 2 arguments.
     * @param numbers - the list of numbers
     * @return - the maximum value of the list of numbers
     */
    private double maxOfList(double ... numbers) {
        double currentMax = numbers[0];
        for(double n : numbers) {
            if(Math.max(currentMax, n) > currentMax)
                currentMax = n;
        }
        return currentMax;
    }
}
