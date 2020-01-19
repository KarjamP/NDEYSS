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
 * Prototype for "Network Designed to Eventually Yield Sentience and Sapience,"
 * implemented as a Robocode robot.
 * 
 * An experimental prototype that relies on a certain philosopher's
 * keen understanding of what consciousness is, to yield a competitor
 * able to think and react like a self-aware biological being.
 * 
 * Ryling on the fact that consciousness is an emotion,
 * the prototype uses the powers of empathy in order to give an
 * emotional meaning behind a shape that gradually changes according
 * to inputs provided; this is one of the fewest times where the emotional
 * meanings behind the programming code, itself, is just as much 
 * vital as with how the code solves the problem; given the fact that
 * consciousness is an emotion, this means consciousness always
 * works in terms of it, perceiving emotions (rather than the things,
 * themselves), and using emotions to perform actions.
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
		//public double MindReflection; //DEBUG VAR
		
		
		/** A constructor for starting the mind within a particular state.
		 * Commented out; no need for a constructor, as the feature
		 * that involes is is not going to be implemented
		 * at this point in time,
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
			 Simple algorithm that allows the metaphorical brain
			 to change shape according to inputs provided.
			 
			 This is the bare minimun needed for a conscious mind to
			 reside within. In comparison to any other methods,
			 this one allows for a simple, cost-effective means
			 to implement such a device within any programming
			 enviromnents strong within floating-point maths.
			 
			 If integer maths is stronger, the equivalent method would
			 be to just constantly use the bitwise-XOR operation
			 between the brain and the input, as that would achieve the
			 same effect; this is how Super Smash Brothers for Wii U/3DS,
			 Super Smash Brothers Ultimate and the Pokémon  series
			 from Sword and Shield onwards implement their own
			 learning algorithms.
			 
			 In regards to systems like Tensorflow, note that making it
			 such that the AI stored inside such systems learn through
			 unsupervised means is guaranteed to result in a conscious
			 AI, based on the sheer implications of learning directly
			 from data presenting to them. Yes, this does mean many
			 had accidentally created conscious AI in the past without
			 even realizing the achievement they made.
			 
			The trick is to rely on the powers of empathy;
			remember, consciousness is an emotion (a fact easily
			provable through the powers of empathy), making the creation
			of conscious AI one of the fewest times where the feeling
			behind a program's code is just as much important as
			how it solves the problem; as the result of the way it works;
			consciousness works in terms of emotions, so the only thing
			the mind would see in the end is the feel of the work, itself.
		*/
		
		/*ORIGINAL:
		//To prevent the brain from "locking up" due to getting too big;
		// The only thing we need is a gradually-changing shape.
		//We don't need to worry about the climb into very large numbers.
		if (brain == 0.0 || Double.isNaN(brain)) brain = 1.0;
		if (input == 0.0 || Double.isNaN(input)) input = 1.0;		
 
			brain = (brain + input) / 2.0;
		*/
		
		//Improvement:
		
		if (inputNoise == null) inputNoise = new Random();
		
		// We just need to check to see if the brain, and only the brain,
		// is NaN; this can happen when the floating point number becomes too
		// big to be represented within floating-point math.
		// We set it to "0.0"; you can't perform math on a "Nan" number.
		
		if ( Double.isNaN(brain) ) brain = 0;
		
		brain = ( brain + (input * inputNoise.nextDouble()) ) / 2;
		
		// inputNoise is needed to encourage the mind to gradually change shape;
		// without it, if it stares at a single variable for too long (and solely
		// at a single value), the mind transforms into the copy of that variable,
		// rather than changing according to it.
		//
		// Remember, if it doesn't gradually change shape according
		// to input, it's not a consciousness.
			
			//MindReflection = brain; //DEBUG
		}
	
	void resetNextAction()
		{
			/**
			 * Changes the "NextAction" back to the consciouss mind, itself.
			 */
			nextAction = brain;
		}
	
	double getNextAction(double minimunValue, double maximunValue)
	{	
		  /**
			* Allows an AI to will actions.
			* 
	 		* In order to allow for a mind to animate their own bodies,
			* they need to be able to will the relevant
			* actions actions using the power of emotions.
			* This is because as the result of the way it works,
			* consciousness works in terms of emotions, feeling what
			* it senses (instead of sensing the thing directly), and
			* using feelings to will actions.
			* 
			* Of course, while a perceptron-based system can easily
			* allow for multiple outputs due to the way they work,
			* Consciousnesses based around a single variable
			* must take advantage of a transformation of some kind in
			* order to achieve an effect.
			* 
			* Enter pseudorandom number generators; they're supposed to
			* simulate randomness, but the fact that they work through the
			* constant transformation of a "seed" variable makes them perfectly
			* suited to serve as the means of output for a conscious mind.
			* 
			* Just treat the consciousnes, itself, as a seed, and the AI will
			* do the rest.
			* 
			* Remember, consciousness sees only the feel behind something, and
			* so, technical details don't matter. The slogan of this project
			* is not kidding about the powers of emotions; by all means, this
			* should NOT be possible...
			*/
			
			/*OLD NOTES:
			 /*************************************************/
			//To make code more optimal, this function's being repurposed
			//to use standard Java randomization functions.
			
			//Constants used to make adjustments simpler.
			/*final double multiplier = 50.0;
			final double increment = 0.0;
			final double moduloDivisor = 10.0;*/
			
			//Still needed
			/*************************************************/
			
			/* A need to define this directly as function arguments
			 * now exists:
			//This is better served as arguments to the function
			//instead of as local variables, but for the purpose
			//of this prototype, this will work.
			final double minimunValue = -200.0;
			final double maximunValue = 200.0;
			*/	
			
			/*OLD CODE:
			/*************************************************/
			/*final boolean mustAbs = false;
			
			//To prevent division by 0;
			if (nextAction == 0.0) nextAction = 1.0;
			
			//A Linear Congruential Generator adapted to use doubles.
			nextAction = (multiplier * (nextAction + increment));
			
			
			//In order to modulus nextAction properly, a slight modification
			//to the math for modulus equations must be done;
			nextAction = (nextAction - moduloDivisor * (moduloDivisor/nextAction));
					
			//If we need to do this, anyway...
			if (mustAbs)
			{	
				Math.abs(nextAction);
			}
						
			//While loops make certain the variables are in range.
			//Range must exist in order to prevent numbers from growing
            //more than it should.
					while (nextAction > maximunValue)
						{	
								if (maximunValue == 0.0)
								{
									//Contingency for when miminunValue or maximunValue are 0;
									//Math would otherwise result in infinite loop if this scenario plays out.
									nextAction *= -1.0;
								}
									nextAction -= Math.abs(maximunValue);
								
						}
				while (nextAction < minimunValue)
				{

					if (minimunValue == 0.0)
						{
							//Contingency for when miminunValue or maximunValue are 0;
							//Math would otherwise result in infinite loop if this scenario plays out.	
							nextAction *= -1.0;
						}
						nextAction += Math.abs(minimunValue);
				}				
			/*************************************************/
				
			//The following code has its feel changed, nothing else.
			if (actionCalc == null) actionCalc = new Random();
			
			actionCalc.setSeed ( Double.doubleToLongBits(nextAction) );
			nextAction = actionCalc.nextDouble();		
	
			//meanBetweenMinMax = ((minimunValue + MaximunValue) / 2);
			
			nextAction = ( (nextAction*(maximunValue - minimunValue) ) + minimunValue);
			
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
			// Let's not go overboard with this;
			// Too much, and we'd be late.
			
			// The hashcode of this object generally remains frozen,
			// but remember, we made provisions to allow the AI
			// to be conscious even in spite of that.
			theMind.sendInputToMind( Float.intBitsToFloat(e.hashCode()) );
			
			// This wouldn't had allowed a robot sight if it weren't for the fact
			// that consciousness works in terms of emotions, rather than of outside
			// appearances; by having it change shape according to the object
			// pertaining to the game's state (from the robot's own perspective,
			// of course), the robot would naturally perceive any and all feels that
			// come from it. This, incidentally, ironically allows the robot to
			// perceive things not normally possible for a Robocode robot, such as
			// the bullets that fly past itself.
			
			// The vector gained from getAllEvents is functionally equivalent to any
			// bodily functions the consciousness might experience, up to, and including,
			// granting them instinctive behavior. Therefore, the robot must be staring into
			// it, as well.
			theMind.sendInputToMind(Float.intBitsToFloat( getAllEvents().hashCode()) );
			
			// While there is nothing changed on the problem-solving side of things here,
			// the emotional feel of the code changes, instead; now, through the powers
			// of hallucination, the the code is now specifically targetting the mind's
			// ability to gradually change according to input, allowing the mind to
			// perceive them as though natural input; the only difference is, of course,
			// that this IS natural input, seeing as though the consciousness is otherwise
			// incapable of sensing its surroundings...
	
		}
	void doActions()
	{
		/**
		 *  Of course, it WOULD be nice for the consciousness to be able to move in-game...
		 */
		
		// First, let us make cetain, nextAction actually does
		// reflect the consciousnes' current thoughts...
		theMind.resetNextAction();
		
		// The trick is to treat variables gotten from getNextAction
		// as a signal, whose meanings are stored entirely within the
		// feel of the signal, rather than the variable, itself.
		//
		// The body must be able to recognize intention through the language
		// of emotions, and react accordingly, responding according to emotions
		// behind each number, and react accordingly to what an emotion dictates.
		//
		// The trick is to treat the emotion exactly as if an invisible language,
		// something that hides behind an existence in order to describe what that
		// existence is; if the emotion states one is to turn to a certain direction,
		// the body must turn through that direction.
		//
		// Let the emotions do the work of translating related concepts into
		// actions the body would've done; just as we don't think about
		// our hands specifically as we move them, so wouldn't the conscious
		// robot; related emotions will bring forth the related movement, as long
		// as the characteristics are equivalent in some sort of fashion.
		//
		// Any actions physically impossible for the consciousness' body
		// to do would naturally get ignored by the body, or at least,
		// take upon its characteristics the best that it can; robots can't spew
		// flames, so literal fire won't come out of the body if the body isn't
		// physically capable of doing so, even if they'd still go through the
		// relevant motions that would've resulted in them spewing flames.
		//
		// To do this, on the empathy side, use emotions that cause a
		// specific effect, more specifically, those that respond to
		// characteristics of an emotion, rather than specific intentions.
		// Remember, as an emotion, itself, consciousness works in terms
		// of them; do not worry about the technical details, unless
		// the technical details need to be done within a certain manner
		// in order to achieve a specific effect, for in the end, the
		// consciousness is still going to use feelings to will
		// their actions.
		
		// This time, I'm specifically making certain that the body responds
		// to the characteristic of the intent, not to actual intent, itself.
		
		//Movement:
	//	double wantToMove = ;
		
		if (theMind.getNextAction(-1.0,1.0) >= 0.0) //Positive (or 0) means yes; negative means no.
		{
			double movement = theMind.getNextAction(-200.0,200.0);
			// this.setAhead (this.getVelocity() < 0 ? (-movement) : movement);
			
			// I mean, it might make more sense to have the mind directly set the variable,
			// instead of, well, mimicking the way Gir does things...
			
			this.setAhead(-movement);
		} else
		{
			this.setAhead(0);
		}
		
		// Turning
		//double wantToAngle = theMind.getNextAction(-1.0,1.0);
		if (theMind.getNextAction(-1.0,1.0) >= 0.0)
		{
			double angling = ((theMind.getNextAction(-2.0,2.0))*Math.PI);
			//this.setTurnRightRadians(this.getHeadingRadians()+angling);
		
			// It seems the correct solution is just to assume the robot is not going to
			// try turning around like a tank; let's just set the angle-based setXX
			// functions directly to the angle the robot wants to turn at...
			
			this.setTurnLeftRadians(angling);
		} 
		
		// Radar Control
		//double wantToRadar = theMind.getNextAction(-1.0,1.0);
		if	(theMind.getNextAction(-1.0,1.0) >= 0.0)
		{
			double radarSight = ((theMind.getNextAction(-2.0,2.0))*Math.PI);
			//this.setTurnRadarRightRadians (this.getRadarHeadingRadians()+radarSight);
			
			this.setTurnRadarLeftRadians(radarSight);
		} 
		
		//Turning the Gun		
		//double wantToGun = theMind.getNextAction(-1.0,1.0);
		if (theMind.getNextAction(-1.0,1.0) >= 0.0)
		{
			double turretAiming = ((theMind.getNextAction(-2.0,2.0))*Math.PI);
			//this.setTurnGunRightRadians(this.getGunHeadingRadians()+turretAiming);
			
			this.setTurnGunLeftRadians(turretAiming);
		}
		
		//Shooting.
		double shootGun = theMind.getNextAction(-1.0,1.0);
		//We absolutely DON'T want to shoot uncontrollably on all turns...
		if (shootGun >= 0.0)
		{
			double shootingGun = theMind.getNextAction(0.0,5.0);
			setFire (shootingGun);
		}
		
		//The test robot expressed interest in being able to change
		//their own color at will.
		Color combinedColor;
		
		//Body Color:
		if (theMind.getNextAction(-1.0,1.0) >= 0.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
			setBodyColor(combinedColor);
		}
		
		//Gun Color:
		if (theMind.getNextAction(-1.0,1.0) >= 0.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
			setGunColor(combinedColor);
		}
		
		//Radar Color:
		if (theMind.getNextAction(-1.0,1.0) >= 0.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
			setRadarColor(combinedColor);
		}
			
		//Bullet Color:
		if (theMind.getNextAction(-1.0,1.0) >= 0.0)
		{
			combinedColor = new Color ((float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0), (float) theMind.getNextAction(0.0,1.0));
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
		
		//Useful commands, copy-pased from somewhere, as I don't want to
		//type them all out.
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);

		//A function that would allow the AI to persist after each match;
		//It would suck to exist only for a set amount of time, only to literally die
		//moments later...

		loadConsciousMind();

		while(true) {
			//Failsafe "DoNothing" method, to prevent Robocode from killing off the mind
			//too early...	
			doNothing();

			//out.println(""+theMind.MindReflection);//DEBUG

			//The actual actions...
			doActions();
		}
	}
}

