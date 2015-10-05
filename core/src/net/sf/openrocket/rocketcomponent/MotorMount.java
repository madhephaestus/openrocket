package net.sf.openrocket.rocketcomponent;

import java.util.Iterator;

import net.sf.openrocket.motor.MotorInstance;
import net.sf.openrocket.util.ChangeSource;
import net.sf.openrocket.util.Coordinate;

public interface MotorMount extends ChangeSource, FlightConfigurableComponent {
	

	/**
	 * is this mount currently configured to carry a motor? 
	 * 
	 * @return  whether the component holds a motor
	 */
	public boolean hasMotor();

    /**
     * Set whether the component is acting as a motor mount.
     */
    public void setActive(boolean mount);

	/**
	 * Is the component currently acting as a motor mount.
	 * 
	 * @return if the motor mount is turned on
	 */
	public boolean isActive();
	
	/**
	 * Get all motors configured for this mount.
	 * 
	 * @return an iterator to all motors configured for this component
	 */
	public Iterator<MotorInstance> getMotorIterator();

	/**
	 *   Returns the Default Motor Instance for this mount.
	 *   
	 *    @return The default MotorInstance
	 */
	public MotorInstance getDefaultMotorInstance();
	
	/**
	 * 
	 * @param testInstance  instance to test
	 * @return  if this motor is the default instance
	 */
	public boolean isDefaultMotorInstance( final MotorInstance testInstance);
	
	/**
	 * 
	 * @param fcid  id for which to return the motor (null retrieves the default)
	 * @return  requested motorInstance (which may also be the default motor instance)
	 */
	public MotorInstance getMotorInstance( final FlightConfigurationID fcid);

	/**
	 * 
	 * @param fcid index the supplied motor against this flight configuration 
	 * @param newMotorInstance  motor instance to store
	 */
	public void setMotorInstance(final FlightConfigurationID fcid, final MotorInstance newMotorInstance);
	
	/**
	 * Get the number of motors available for all flight configurations
	 * 
	 * @return  the number of motors.
	 */
	public int getMotorCount();
	
	/**
	 * Return the distance that the motors hang outside this motor mount.
	 * 
	 * @return  the overhang length.
	 */
	public double getMotorOverhang();
	
	/**
	 * Sets the distance that the motors hang outside this motor mount.
	 * 
	 * @param overhang   the overhang length.
	 */
	public void setMotorOverhang(double overhang);
	
	
	
	/**
	 * Return the inner diameter of the motor mount.
	 * 
	 * @return  the inner diameter of the motor mount.
	 */
	public double getMotorMountDiameter();
	
	
	/**
	 * Return the position of the motor relative to this component.  The coordinate
	 * is that of the front cap of the motor.
	 * 
	 * @return	the position of the motor relative to this component.
	 * @throws  IllegalArgumentException if a motor with the specified ID does not exist.
	 */
	public Coordinate getMotorPosition(FlightConfigurationID id);
	
}
