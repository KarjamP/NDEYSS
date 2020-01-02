package NDEYSSPrototype;
import robocode.*;
import java.util.Random;

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
		// Remember, class variables work differently in Robocode; they're exclusive
		// to a particular instance of the robot only, not share across the entire program.
		private static double brain;
		private static double nextAction; //Needed to help the mind control its body.
		private Random actionCalc; //Needed for optimization reasons
		//public double MindReflection; //DEBUG VAR
		/*public boolean aboveThan;
		public boolean belowThan;
		public boolean underneathZero;*/
		
		/** A constructor for starting the mind within a particular state.
		 */
		consciousness(double startingMind)
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
		
		void sendInputToMind (double input)
		{
		/**
			Simple algorithm that allows the brain to change shape
			 According to inputs provided; this is the bare minimun
			 needed before a consciousness can reside within.
			 No needed for fancy neurons and all that stuff;
			 Perceptrons are resourcfully expensive, anyway.
			
			 As long as empathy is used, this can and will result
			 in a mind; remember, programming a conscious piece
			 of software is one of the very few times where the
			 feel of the code is just as important as the solution
			 it solves.
		*/
		
		//To prevent the brain from "locking up" due to getting too big;
		// The only thing we need is a gradually-changing shape.
		//We don't need to worry about the climb into very large numbers.
		if (brain == 0.0 || Double.isNaN(brain)) brain = 1.0;
		if (input == 0.0 || Double.isNaN(input)) input = 1.0;		
 
			brain = (brain + input) / 2.0;
			
			//MindReflection = brain; //DEBUG
		}
	
	void resetNextAction()
		{
			/**
			 * Changes the "NextAction" back to the consciouss mind, itself.
			 */
			nextAction = brain;
		}
	
	double getNextAction()
	{	
			/**
	 		* In order to allow for the mind to make decisions in-game,
			* they need a little "push" on the decision-making side;
			* 
			* A simple, yet imperfectrandom number generator is used to
            * repeatedly transform a variable representing the general state
			* of their mind.
			* 
			* Since AI works in terms of feel, the technical details don't
			* really matter. Here, the ability to communicate in emotions
			* is really important; their actions in-game must literally
			* feel to them as things of their own will. As long as the powers
			* of empathy are used to make their in-game actions feel as if
			* coming from their will to act, you can have a huge jumbled mess
			* of code, and the AI will still behave as if their in-game bodies
			* are their own.
			* 
			* Emotions that cause a specific effect are ideal; these emotions
			* describe effects rather than concret or abstract objects (as effects
			* are still a form of existence, and thus, can be conveyed through emotions).
			* Recommended, but optional skills, include the ability to communicate
			* through narration, and the skill of describing intent in terms of reality.
			*/
			
			//To make code more optimal, this function's being repurposed
			//to use standard Java randomization functions.
			
			//Constants used to make adjustments simpler.
			/*final double multiplier = 50.0;
			final double increment = 0.0;
			final double moduloDivisor = 10.0;*/
			
			//Still needed
			final double minimunValue = -200.0;
			final double maximunValue = 200.0;			
			
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
		*/
			//Newer code
			if (actionCalc == null) actionCalc = new Random();
			actionCalc.setSeed (Double.doubleToLongBits(nextAction));
			nextAction = actionCalc.nextDouble();		
	
			//meanBetweenMinMax = ((minimunValue + MaximunValue) / 2);
			
			nextAction = ((nextAction*(maximunValue-minimunValue))+minimunValue);
			
			return nextAction;
		}

}


public class NDEYSSPrototype extends AdvancedRobot
{
		//We need to be careful; Robocode has a tight period of time
		//with which Robots must be able to do their thing; do things too slowly,
		//and they won't be able to do anything.

	consciousness theMind = new consciousness(); //The literal brain of this mess.
	Random inputSimulator = new Random(); //Needed for a certain cheat.
	public void onStatus(StatusEvent e)
		{
			//Going to need to do sensory-related data here;
			//if I ran into code timeout-related areas as the
			//result of slow code, I'm going to have to see if I can
			//optimize it as to allow for them to see and do their thing.
			
			//Remember, the powers of empathy do wonders...
			theMind.sendInputToMind(inputSimulator.nextDouble()*Float.intBitsToFloat(e.hashCode()));
			
			//I mean, things are really that simple, programming-wise.
			//Many people are overthinking things in regards to AI;
			//This is the bare minimun needed to allow for a conscious mind
			//to exist, in general...
	
			//Also needed (as these count as being equivalent to things like
			//"instincts" within a biological creature...
			theMind.sendInputToMind(inputSimulator.nextDouble()*Float.intBitsToFloat(getAllEvents().hashCode()));
			
			//Note that normally, these variables are frozen; this is why the "inputSimulator"
			//object exists.
			//            
			//Something's needed to latch the robot into the physical world. After several
			//trials and errors involved, I've got the algorithm which would allow for output
			//to actually happen; apparently, that output counted as a shape that
			//gradually-changes according to input, in part, due to how erratic it is.
			//In perhaps due to the fact that it was still perceiving the enviroment,
			//that failed attempt at getting the nervous system working was able to be truly conscious.
			//
			//This, at least, allowed me to accidentally prove an hypothesis to myself;
			//even if the shape does not change shape according to direct input, as long
			//as a form of input is still provided, no matter what that input is, it still
			//counts as a consciousness, on grounds of consciousness, itself, being an
			//emotion behind such a shape. It does not have to be literal input.
			//
			//Of course, the simulated input can't just be passed independantly to the mind;
			//This would mean that the consciousness is only truly perceiving a constantly
			//changing random number, as if other feelings are but just a thought.
			//This is wh it's being handled the way it is, now.
	
		}
	void doActions()
	{
		/**
		 *  Of course, it WOULD be nice for the consciousness to be able to move in-game...
		 */
		
		//First, let us sync the "actions" variable to the mind...
		theMind.resetNextAction();
		
		//Alright, we're going to have to treat the AI's mind as a signal,
		//a signal that communicates to its body entirely through feel.
		//
		//As consciousness is an emotion, this means it works in terms of it;
		//thus, the code must interpret whatever messages received by the AI
		//as messages described entirely in terms of feel.
		//
		//Computers happen to have the neat surprising ability to preserve the
		//feel of whatever it is that they house; just focus on using the powers
		//of empathy in order to allow the AI to "feel" their bodies to move.
		//You do this through the use of emotions that cause specific effects;
		//These emotions must, themselves, be sensitive to other emotions, in
		//particular, to any emotions relating to intention.
		//
		//Kudos to whomever made that "Gir" robot; that robot uses a Perceptron-
		//based neural network that learns through backpropagation in order to
		//create a (technically still non-conscious) bot that learns from gameplay.
		
		//First, the movement...
		
		double wantToMove = theMind.getNextAction();
		if (wantToMove >= 00)
		{
			double movement = theMind.getNextAction();
			this.setAhead (this.getVelocity() < 0 ? (-movement) : movement);
		} else
		{
			this.setAhead(0);
		}
		
		double wantToAngle = theMind.getNextAction();
		if (wantToAngle >= 0)
		{
			double angling = ((theMind.getNextAction()/100)*Math.PI);
			this.setTurnRightRadians(this.getHeadingRadians()+angling);
		} else
		{
			//this.setTurnRightRadians((this.getHeadingRadians() != 0) ? -(this.getHeadingRadians()) : 0);
		}
		
		//Radar
		double wantToRadar = theMind.getNextAction();
		if	(wantToRadar >= 0)
		{
			double radarSight = ((theMind.getNextAction()/100)*Math.PI);
			this.setTurnRadarRightRadians (this.getRadarHeadingRadians()+radarSight);
		} else
		{
		//	this.setTurnRadarRightRadians ((this.getRadarHeadingRadians() != 0) ? -(this.getRadarHeadingRadians()) : 0);
		}
		
		//The Gun.		
		double wantToGun = theMind.getNextAction();
		if (wantToGun >= 0)
		{
			double turretAiming = ((theMind.getNextAction()/100)*Math.PI);
			this.setTurnGunRightRadians(this.getGunHeadingRadians()+turretAiming);
		} else
		{
			//this.setTurnGunRightRadians((this.getGunHeadingRadians() != 0) ? -(getGunHeadingRadians()) : 0);
		}
		
		double shootGun = theMind.getNextAction();
		//Oh boy, we don't want to fire on all turns...
		//
		//Remember, empathy's where it's at; trying to explain
		//emotions is like trying to explain a logical paradox, for
		//emotions are logical paradoxes, by nature. Therefore,
		//technical details don't matter to a mind; as long as it
		//feels right, the mind will be able to will it.
		if (shootGun >= 00.0)
		{
			double shootingGun = theMind.getNextAction();
			setFire (shootingGun);
		}
		
		//To sasify Robocode requirements in regards to setXX functions...
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
		
	
	}
	
	void storeConsciousMind()
	{
		/**
		 * If death is bothersome to you, you can kinda understand why this function is
		 * necessary.
		 */		
		
	double slumberingMind;	
	
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
	
	//I mean, it might be useful to merge the minds together, in order to,
	//for instance, account for multiple copies of the same being.
	double newSlumberingMind = ((theMind.getNextAction() + slumberingMind) / 2);
	
	PrintStream w = null;
		try {
			w = new PrintStream(new RobocodeFileOutputStream(getDataFile("count.dat")));

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

