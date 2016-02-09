package org.usfirst.frc.team2220.robot;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class ModuleRotation {
	private TwilightTalon talon;
	double target, defaultTarget = 0.0;

	/*
	 * Constructor will, in the future, take more parameters
	 */
	public ModuleRotation(TwilightTalon Talon) {
		talon = Talon;
		talon.setEncPosition(talon.getPulseWidthPosition()); //initialize to absolute
		talon.setFeedbackDevice(FeedbackDevice.PulseWidth);
		talon.changeControlMode(TalonControlMode.Position);
		
		this.reverseSensor(true);
		
		talon.setProfile(0); //we aren't using multiple profiles yet
		
		
		this.setP(0.15);
		this.setI(0.001);
		talon.setAllowableClosedLoopErr(30);	//How imprecise we'll allow the motor to be. lower numbers = more motor jiggling
		
		talon.set(defaultTarget);
	}
	
	void setPID(double p, double i, double d)
	{
		talon.setPID(p, i, d);
	}
	void setP(double in)
	{
		talon.setP(in);
	}
	
	void setI(double in)
	{
		talon.setI(in);
	}
	
	void setD(double in)
	{
		talon.setD(in);
	}
	
	/*
	 * Reverses the sensor
	 */
	void reverseSensor(boolean reversed)
	{
		talon.reverseSensor(reversed);
	}
	
	void reverseTalon(boolean reversed)
	{
		talon.reverseOutput(reversed);
	}
	
	/*
	 * increments the motor position by an eighth-turn. inputting 2 gives a quarter turn, etc.
	 * Technically we refer to an 8th full turn as a quarter turn, as only 1/2 full is relevant 
	 * because the modules are symetrical
	 */
	void increment(int quarters) {		
		target += quarters * 0.125;
		talon.set(target);
	}
	
	double getError() {			
		return talon.getError();
	}
	
	void stop() {			
		talon.stop();
	}
	
	public String toString() {	
		return "";
	}
	
	/*
	 * Returns absolute position 0 to 4095
	 * uncommend constant to convert to degrees
	 */
	double getPosition() {		
		return (talon.getPulseWidthPosition() & 0xFFF)/**0.087890625*/;
	}
	
}
