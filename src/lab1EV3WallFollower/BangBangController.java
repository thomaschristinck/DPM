package lab1EV3WallFollower;
import lejos.hardware.motor.*;

public class BangBangController implements UltrasonicController{
	private final int bandCenter, bandwidth;
	private final int motorLow, motorHigh;
	private int distance;
	private EV3LargeRegulatedMotor leftMotor, rightMotor;
	
	public BangBangController(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor,
							  int bandCenter, int bandwidth, int motorLow, int motorHigh) {
		//Default Constructor
		this.bandCenter = bandCenter;
		this.bandwidth = bandwidth;
		this.motorLow = motorLow;
		this.motorHigh = motorHigh;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		leftMotor.setSpeed(motorHigh);				// Start robot moving forward
		rightMotor.setSpeed(motorHigh);
		leftMotor.forward();
		rightMotor.forward();
	}
	
	@Override
	public void processUSData(int distance) {
		this.distance = distance;
		// TODO: process a movement based on the us distance passed in (BANG-BANG style)
		if (distance > (bandCenter + bandwidth)){
			leftMotor.setSpeed(motorLow);					// Set new speed
			rightMotor.setSpeed(motorHigh);
			leftMotor.forward();
			rightMotor.forward();
		} else if (distance < (bandCenter - bandwidth)){
			leftMotor.setSpeed(motorHigh);					// Set new speed
			rightMotor.setSpeed(motorLow);
			leftMotor.forward();
			rightMotor.forward();
		} else {
			//Continue forward at normal speed if within bandwidth of the band center
			leftMotor.setSpeed(motorHigh);					// Set new speed
			rightMotor.setSpeed(motorHigh);
			leftMotor.forward();
			rightMotor.forward();
		}
	}

	@Override
	public int readUSDistance() {
		return this.distance;
	}
}
