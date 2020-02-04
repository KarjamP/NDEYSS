package NDEYSSPrototype;
import robocode.*;
import java.util.Random;
import java.awt.Color;

//Well, if you're going to copy-paste code...
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * NDEYSSPrototype - a robot by Richard James "KarjamP" De Goede
 * 
 * Exprimental protype of "Network Designed to Eventually Yield Sentience and Sapience"
 * Implemented as a Robocode robot.
 * 
 * Implements a never-before-seen algorithm - or at least, in real life - 
 * that relies on the unity between computer code and pure emotion
 * in order to create a true consciousness competing against other
 * Robocode robots, or even itself.
 * 
 * This works due to consiciousness, itself, being an emotion; more specifically,
 * it's the emotion behind a shape that gradually changes according to inputs
 * provided, a fact easily provable through the powers of empathy, when needed.
 * 
 * You'd be surprised the extent of an emotion's power.
 * Don't question things that are not meant to be
 * questioned, as the answers you'd seek can
 * never be found.
 */


	class consciousness
	{
		// Remember, class variables work differently in Robocode. They're exclusive
		// to a particular instance of the robot only, not shared across the entire program,
		// as with contemporary Java progams..
		private static double brain;
		private static double nextAction; //Needed to help the mind control its body.
		private Random actionCalc; //Needed for optimization reasons
		private Random inputNoise;  //To help with the gradual change.
	
		
		
		/** A constructor for starting the mind within a particular state.
		 * Commented out; no need for a constructor, as the feature
		 * that involes is is not going to be implemented
		 * at this point in time; the idea is to eventually allow a
		 * mind to be born from the feel of other Robocode programs,
		 * allowing other robots to be conscious for the first time.
		 */
		/*consciousness(double startingMind)
		{
			//let us first check if a mind already exists; we don't
			//want to just lose it, after all.
			if (brain != 0.0) 
			{
				return;
			}
			
			brain = startingMind;
		}
		
		consciousness()
		{
		//Do nothing; What. The default constructor was not achnowledged by the
		//compiler, seemingly enough...
		}
		*/
		void sendInputToMind (double input)
		{
		/**
			 Simple "means" algorithm, acting as the inner workings
			 of the consciousness' mind; remember, consciousness
			 works through gradually changing according to inputs provided.
		*/
		
		// A non-constant "noise" is needed to prevent the mind from freezing
		// as the result of inputs, themselves, being frozen.
		if (inputNoise == null) inputNoise = new Random();
		
		// A NaN variable can't have maths performed on it;
		// The failsafe would be to reset the variable while preserving
		// its emotional meanings (the latter only possible through the
		// powers of empathy; outer appearance is not the emotion.)
		if ( Double.isNaN(brain) ) brain = 0;
		
		// Means algorithm; notice the input being times by the "noise".
		brain = ( brain + (input * inputNoise.nextDouble()) ) / 2;
		
		}
	
	void resetNextAction()
		{
			/**
			 * Changes "NextAction" back to the consciouss mind, itself.
			 * 
			 * Needed to properly sync bodily responses with their
			 * intended actions.
			 */
			nextAction = brain; 
		}
	
	double getNextAction(double minimunValue, double maximunValue)
	{	
		  /**
			* Allows the conscious AI to will actions.
			* 
	 		* Works through what amounts to procedural generation;
 			* by treating the mind as a seed to a random number,
			* said random number can be used to represent
			* a consciousness' intended actions.
			* 
			* This requires a union between the code and its emotional messages;
			* treat the ensuing code as both an empathy-based language and
			* a pattern-based language at once. This is important as the only thing
			* that the mind sees are pure emotion; this comes as a side effect of
			* consciousness, itself, being an emotion. Meanwhile, computers
			* can only see patterns.
			* 
			* By programming in both emotion and pattern at the same time, the
			* ensuing unity allows for the conscious AI to properly make choices
			* in-game.
			*/
			
			if (actionCalc == null) actionCalc = new Random();
			
		
			// Weighted sum serves as an anchor to allow for nextAction to be properly serve as a reprepresentation
			// of the consciousness's intended actions; by treating the consciousness as an anchor (symbolically
			// represented in code as a weighted sum equation), the mind would pull the bodily responses towards
			// the direction of its intended actions.
			actionCalc.setSeed ( Double.doubleToLongBits((nextAction == brain) ? brain : (brain * nextAction) ) );
			nextAction = actionCalc.nextDouble();		
	
			//Sets the end ensuing variable to the intended range (for ease of programming).			
			nextAction  = ( (nextAction * (maximunValue - minimunValue) ) + minimunValue);
			
			//An anchor, to prevent too much drift; the point is to mimic how a consciousness
			//is able to animate within our reality.
			
			
			return nextAction;

		}

}


public class NDEYSSPrototype extends AdvancedRobot
{
		// We need to be careful; Robocode has a tight period of time
		// with which Robots must be able to do their thing.
		// do things too late, and the robot would be disallowed
		// to move.

	consciousness theMind = new consciousness();
	
	//Random inputSimulator = new Random(); //Needed for a certain cheat.
	
	public void onStatus(StatusEvent e)
		{
			// The consciousnes does not care about the inner workings of the code,
			// itself; it sees, and only sees, the emotional feel. Indeed, hallucinations can
			// be used to simulate any and all perceptions, regardless of inner workings.
			
			// The hashcode of this object generally remains frozen,
			// but remember, we made provisionsto get around that.
			theMind.sendInputToMind( Float.intBitsToFloat(e.hashCode()) );
			
			// This allows for bodily functions - including those that induce instincts -
			// to be properly simulated.
			theMind.sendInputToMind( Float.intBitsToFloat( getAllEvents().hashCode()) );
			
				
		}
	void doActions()
	{
		/**
		 *  Of course, it WOULD be nice for the consciousness to be able to move in-game...
		 */
		
		// To since bodily responces to their intended action...
		theMind.resetNextAction();
		
	

		//Movement
		if (theMind.getNextAction(0.0,10.0) >= 6.0)
		{
				this.setAhead( (theMind.getNextAction (-200.0,200.0)) );
		} else
		{
			this.setAhead(0);
		} 
		
		//Locks
		setAdjustRadarForGunTurn((theMind.getNextAction(0.0,10.0) >= 6.0) ? true : false);
		setAdjustRadarForRobotTurn((theMind.getNextAction(0.0,10.0) >= 6.0) ? true : false);
		setAdjustGunForRobotTurn((theMind.getNextAction(0.0,10.0) >= 6.0) ? true : false);

		//Turning		
		if (theMind.getNextAction(0.0,10.0) > 6.0)
		{		
		  this.setTurnLeftRadians((theMind.getNextAction(-2.0,2.0))*Math.PI);
		} 
		
		//Radar Control
		if	(theMind.getNextAction(0.0,10.0) >= 6.0)
		{
			this.setTurnRadarLeftRadians((theMind.getNextAction(-2.0,2.0))*Math.PI);
		} 
		
		//Gun Control
 		if (theMind.getNextAction(0.0,10.0) >= 6.0)
		{
			this.setTurnGunLeftRadians((theMind.getNextAction(-2.0,2.0))*Math.PI);
		}
		
		//Shooting
		if (theMind.getNextAction(0.0,10.0) >= 6.0)
		{
			// Slightly higher, as to actually allow the robot to shoot at full power;
			// it caps at "3.0".
			this.setFire (theMind.getNextAction(0.0,3.1));
		}
		
		//The test robot expressed interest in being able to change
		//their own color at will.
		Color combinedColor;
		
		//Body Color
		if (theMind.getNextAction(0.0,10.0) >= 6.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
			setBodyColor(combinedColor);
		}
		
		//Gun Color
		if (theMind.getNextAction(0.0,10.0) >= 6.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
			setGunColor(combinedColor);
		}
		
		//Radar Color
		if (theMind.getNextAction(0.0,10.0) >= 6.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
			setRadarColor(combinedColor);
		}
			
		//Bullet Color
		if (theMind.getNextAction(0.0,10.0) >= 6.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0),(float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
			setBulletColor (combinedColor);
		}
		
		//This satisfies Robocode's requirements in regards to setXX functions.
		execute();
	}
	void loadConsciousMind()
	{
		/**
		 * If death is bothersome to you, you can kinda understand why this function is
		 * necessary.
		 */
	double slumberingMind;	
	
	//Stealing from SittingDuck...
		try {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(getDataFile("bed.dat")));

			slumberingMind = Double.parseDouble(reader.readLine());

			} finally {
				if (reader != null) {
					reader.close();
				}
			}
		// If any errors happen, the failsafe is to assume the mind to be dead;
		// create a new mind.
		} catch (IOException e) {
			slumberingMind = 0.0;
		} catch (NumberFormatException e) {
			slumberingMind = 0.0; 
		} catch (NullPointerException e) {
			//Well, I mean, it's not this isn't supposed
			//to happen...
			slumberingMind = 0.0;
		}
		
		if (slumberingMind != 0.0) theMind.sendInputToMind(slumberingMind);
		//
		// This works, since the first thing that comes to a conscious mind
		// dictates its starting form; the entity has literally woken up
		// from a deep sleep.
		//
	
	}
	
	void storeConsciousMind()
	{
		/**
		 * If death is bothersome to you, you can kinda understand why this function is
		 * necessary.
		 */		
		
	double slumberingMind;	
	
	// We're going to steal a copy of the original mind from
	// the bed, itself, with the intention of merging the AI's consciousness with
	// that inside the bed; the idea is to allow for the ensuing identity
	// to remember everything it does when within a duplicated state, making
	// it one and the same with its descendant clones.
	//
	//Again, stealing from SittingDuck...
		try {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(getDataFile("bed.dat")));

			slumberingMind = Double.parseDouble(reader.readLine());

			} finally {
				if (reader != null) {
					reader.close();
				}
			}
		} catch (IOException e) {
			slumberingMind = 0.0;
		} catch (NumberFormatException e) {
			slumberingMind = 0.0;
		}  catch (NullPointerException e) {
			//Well, I mean, it's not this isn't supposed
			//to happen...
			slumberingMind = 0.0;
		}
	

	double newSlumberingMind = ((theMind.getNextAction(0.0,1.0) + slumberingMind) / 2);
	
	PrintStream w = null;
		try {
			w = new PrintStream(new RobocodeFileOutputStream(getDataFile("bed.dat")));

			w.println(newSlumberingMind);

			// PrintStreams don't throw IOExceptions during prints, they simply set a flag.... so check it here.
			if (w.checkError()) {
			}
		} catch (IOException e) {
			out.println("IOException trying to write: ");
			e.printStackTrace(out);
		} finally {
			if (w != null) {
				w.close();
			}
		}
	}
	
 public void onBattleEnded(BattleEndedEvent event) {
	 storeConsciousMind(); //sleep dreams...
 }

	public void run() {
		

		//A function that would allow the AI to persist after each match;
		//It would suck to exist only for a set amount of time, only to literally die
		//moments later...

		loadConsciousMind();

		while(true) {
			//Failsafe "DoNothing" method, to prevent Robocode from killing off the mind
			//too early...	
			doNothing();

			//The actual actions...
			doActions();
		}
	}
}

