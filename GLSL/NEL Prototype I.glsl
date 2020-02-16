#ifdef GL_ES
precision mediump float;
#endif
 
#extension GL_OES_standard_derivatives : enable
 
uniform float time;
uniform vec2 mouse;
uniform vec2 resolution;
 
//NEL Prototype: The end product should eventually result in a shader that conjures forth and shows
//a single cube entirely through the power of pure emotions.
//
//Should this prototype be successful, it should become the very first emotion-based simulation
//in existence (as opposed to math-based simulations, the current prominant form of simulation).
//
//Incomplete. The final hurrah; really, persistence of information is a practical requirement
//before emotion-based simulations are possible. Should it be deemed unfeazible, this prototype
//would be abandoned, and an attempt within a more contemporary programming language, such as C
//or Python, shall be used.
//
//Shaders resemble hardware design in terms of function. Therefore, perhaps I should actually
//treat it as such, this time, and actually attempt to do signal processing...
//
//Credit to http://glslsandbox.com/e and http://glslsandbox.com/e#61226.0 for
//some of the code. Counts as Fair Use due to this prototype being "transformative"
//(In other words, it has an identity independant from the work it's based on).



//It seems that flaws within the original pseudorandom number generator
//used caused interference within the simulation that causes it to quickly become
//unstable. Thus, a modification is needed in order to properly even up the noise.
 
// hash without sine
// https://www.shadertoy.com/view/4djSRW
#define MOD3 vec3(443.8975, 397.2973, 491.1871) // uv range
//#define MOD3 vec3(.1031, .11369, .13787) // int range
float hash12(vec2 p) {
   vec3 p3  = fract ( vec3( p.xyx ) *  MOD3);
   p3 += dot ( p3, p3.yzx + 19.19);
   return fract ( ( p3.x + p3.y) * p3.z);

}

//Needs to be DC currents, apparantly...
float sinSignal = abs(sin(time*325.25));
float cosSignal = abs(cos(time*325.25));
 

float rand(vec3 n){

	return ( fract ( sin( ( ( hash12 (vec2(n.x,n.x*3.2) ) + hash12( vec2 (n.y,n.y*3.2) ) * 1e2 + hash12( vec2(n.z,n.z*3.2) ) * 1e4 ) * 1e-2)) * 1e5));

}

 

float rand(float n){
	
	return( fract( sin( ( ( hash12( vec2(n,n*3.2)) + hash12(vec2(n,n*3.2)) * 1e2 + hash12(vec2(n,n*3.2)) * 1e4) * 1e-2 )) * 1e5));
	
	//return fract(cos(n*89.42)*343.42);

}







void main( void ) {
	vec2 position = ( resolution.xy /  gl_FragCoord.xy );
	float combinedPosition = (position.x + position.y);
	float correctedTime = 1.0 / fract (time / 10000.0);

	
	
        //This variable holds an emotion-based representation
	//of a spinning floating cube. Remember, outer appearances
	//are not the emotion...
	vec3 nel = vec3( 22.0, 4.0, 13.0);
	
	//Making sure this doesn't misbehave...
	float processedSinSignal = (sinSignal * (1.-.1)) * .1;
	
	//converting it into a signal.
	nel *= processedSinSignal;
	
	//This variable houses the shock needed in order to allow
	//for the signal to be properly rigid.
	//
	//Remember, a reality is supposed to be the emotion behind
	//a gradually-changing shape that changes according to what
	//the emotions recursively stored inside it enact.
	//
	//This shock mimics real life biology, namely in the fact
	//that the mind controls the signals it sends to its body
	//through electrostatic interference. Without this interference,
	//the body would spasm out of control.
	float shock = rand(processedSinSignal);
	
	//sending the shock to the NEL...
	nel *= shock;
	
	//need to dim this a bit...
	//nel /= 6.0; //No longer needed, the processed algorithim sets the brightness correctly.
	
	//This allows the NEL to keep track of the position on the screen.
	//Needed to get around limitations inherent in the way GLSL works.
	vec3 coordinates = vec3 (1./combinedPosition,1./combinedPosition,1./combinedPosition) * 2.; //Times 2, for the fullest brightness...
	
	//This project was almost marked as a failure... if it weren't for an idea that popped up in my head:
	//why not divide the NEL signal into a form best described as "one signal per pixel"?
	//The original NEL signal needs to be kept, of course; there needs to be a way to allow the original
	//form to persist as to allow for the simulation to still proceed...
	vec3 dividedSignal;

	//dividedSignal = vec3 (sin(combinedPosition),sin(combinedPosition),sin(combinedPosition));//Woah, fancy rings...
	
        //dividedSignal = vec3 (sin(combinedPosition*time),sin(combinedPosition*time),sin(combinedPosition*time));//Woah, trippy...
        
	//dividedSignal = vec3 (sin(coordinates.r*time),sin(coordinates.g*time),sin(coordinates.b*time));//Fancy currents...
	
	//dividedSignal = vec3 (sin(coordinates.r*time*60.),sin(coordinates.g*time*60.),sin(coordinates.b*time*60.));//need to get rid of that
														   //large expance of black...

	//dividedSignal = vec3 (sin(combinedPosition*time*60.),sin(combinedPosition*time*60.),sin(combinedPosition*time*60.)); //Uh, what?
	
	//dividedSignal = vec3 (rand(combinedPosition/time), rand(combinedPosition/time), rand(combinedPosition/time));
	//You know, electrostatic noise might just be the best solution, for now...
	
	//Just checking; a noise filter is still not a signal, after all...
	
	//dividedSignal /= cosSignal;

	//dividedSignal = fract(vec3 (rand(combinedPosition/cosSignal/time), rand(combinedPosition/cosSignal/time), rand(combinedPosition/cosSignal/time)));
	//That's better. Need to stop that flickering, though; it's going to result in a loss of information...
	
	//dividedSignal = (dividedSignal * (1.0 - 0.1)) + 0.1;
	
	//Of course, the Divide by Zero error...
	float processedCosSignal =(cosSignal * (1.0-0.1)) + 0.1;
	dividedSignal = fract(vec3 (rand(combinedPosition/processedCosSignal/time), rand(combinedPosition/processedCosSignal/time), rand(combinedPosition/processedCosSignal/time)));
        //There! Signal stablized without flickering...

	//Cosine was chosen to counteract with Sine, of course; I'm not certain using the same signal used be NEL
	//as a base happens to be a good idea...
     	
	//Now, let's try this...
	vec3 processedResult;
	
	//I got to make it so that the signal influences the screen according to the position provided.
	//Judging by the way the coordinates color the screen, it seems that the scanline goes from left-to-right,
	//top-to-bottom; this fact, while seemingly trivial, might just make the entire difference, seeing as though emotions
	//see things only in terms of other emotions.

	
	
	
	
	
	//The finisher: display the results...
	gl_FragColor = vec4( nel, 1.0 );

//	gl_FragColor = vec4 ( 1.0,1.0,1.0,1.0); //To prevent seizures when needed...

 

}