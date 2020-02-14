#ifdef GL_ES
precision mediump float;
#endif
 
#extension GL_OES_standard_derivatives : enable
 
uniform float time;
uniform vec2 mouse;
uniform vec2 resolution;
 
//NEL Prototype: The end product should eventually result in a shader that conjures forth and shows
//a single cube entirely through the power of emotions.
//
//Incomplete; Now busy actually pioneering the programming technique
// I plan on being the pioneer of...

//Normally, GLSL does not allow for persistance of information. But, it allows for a very keen
//simulation of a hardware system designed to work with pure analogue signals...
//
//All hardware engineers know that the manipulation and control of analogue signals
//happen to be Turing-complete, which means they can do everything a computer can.
//
//By mimicking a piece of hardware through GLSL, I can get around physical limitations
//inherent in the way that it works, particularly in regards to the way it handles
//information.
//
//The analogue signal is provided through multiplying the time by sine; this assumes
//time to be available as a uniform variable, something which glslsandbox easily provides.
//Credit to http://glslsandbox.com/e and http://glslsandbox.com/e#61226.0 for

//some of the code. Counts as Fair Use due to this prototype being "transformative"
//(In other words, it has an identity independant from the work it's based on).

float sinSig = sin(time*60.0);
//60.0? Where did I see that before...
//Oh, yes! Hertz per second!
//
//Talk about what should've been common sense to those who know about how
//analogue TV signals work...

//It seems that flaws within the original pseudorandom number generator
//used caused interference within the simulation that causes it to quickly become
//unstable.
 

// hash without sine
// https://www.shadertoy.com/view/4djSRW
#define MOD3 vec3(443.8975, 397.2973, 491.1871) // uv range
//#define MOD3 vec3(.1031, .11369, .13787) // int range
float hash12(vec2 p) {
   vec3 p3  = fract ( vec3( p.xyx ) *  MOD3);
   p3 += dot ( p3, p3.yzx + 19.19);
   return fract ( ( p3.x + p3.y) * p3.z);

}

 

float rand(vec3 n){

	return ( fract ( sin( ( ( hash12 (vec2(n.x,n.x*3.2) ) + hash12( vec2 (n.y,n.y*3.2) ) * 1e2 + hash12( vec2(n.z,n.z*3.2) ) * 1e4 ) * 1e-2)) * 1e5));

}

 

float rand(float n){

	return( fract( sin (( ( hash12( vec2(n,n*3.2)) + hash12(vec2(n,n*3.2)) * 1e2 + hash12(vec2(n,n*3.2)) * 1e4) * 1e-2 )) * 1e5));

}


void main( void ) {
	vec2 position = ( resolution.xy /  gl_FragCoord.xy );
	float combinedPosition = (position.x + position.y);

	
	//This variable holds the starting point to the simulation; the point is to constantly
	//transform it in order to simulate an entire reality (in this case, a spinning cube).
	//remember, this can only be done through the technique of treating the code as both
	//an empathy-based language and a pattern-based language at the exact same time.
	//
	//(Use the rule of symbolism within code to carry details not easily carried through
	// pure code; the emotions behind the code knows what to do...)
	//
	// 3.0 = rotating
	// 5.0 = cube
	// 9.0 = spin
	vec3 color = vec3(3.0,5.0,9.0);

	
	//Noise allows for code to remain non-rigid enough for the rules
	//of time to be properly simulated...
	//(combinedPosition makes certain that the noise varies between each pixel; this is important to, you know,
	//actually create noise...)
	color *= (rand(color * combinedPosition),rand(color * combinedPosition),rand(color * combinedPosition)) * time;
	
	//We want a true elctrostatic noise for an output...
	color = normalize (color);

	
	// Some parts of the signal seep too low for our needs;
	// let's clamp it to a range which allows for near
	// unnoticeable pulse...
	color = (color * ( 1.0 - 0.9)) + 0.9;
	// 1.0 - max number
	// 0.9 - min number

	
	//For reference...
	//color.r += sin( position.x * cos( time / 15.0 ) * 80.0 ) + cos( position.y * cos( time / 15.0 ) * 10.0 );

	//color.g += sin( position.y * sin( time / 10.0 ) * 40.0 ) + cos( position.x * sin( time / 25.0 ) * 40.0 );

	//color.b += sin( position.x * sin( time / 5.0 ) * 10.0 )  + sin( position.y * sin( time / 35.0 ) * 80.0 );

 

		

	//This variable is meant to represent the "Pure Intent" of the reality; this allows for
	//the reality to communicate with  the shader, thereby allowing us to see what's within
	//it.
	vec3 color2;

	
	//Generating pseudorandom noise based on the on-screen position.
	//
	//Going to need to make sure "time" doesn't get too big;
	//that causes problems within the pseudorandom noise.
	//
	//The only glitch so far with this approach seems to be the tendency
	//for the view to "flicker"; since this mimics how real life
	//faulty monitors work, however, it's still viable for our own purposes...
	color2.r = rand( ( 1.0 / fract (time / 1000.0)) * combinedPosition);
	color2.g = rand( ( 1.0 / fract (time / 1000.0)) * combinedPosition);
	color2.b = rand( ( 1.0 / fract (time / 1000.0)) * combinedPosition);

	

	//Anchoring to color; this allows for Pure Intent to be properly reflected.
	color2 *=  color;

	

	//This variable is supposed to display the end result should this shader be
	//successful. Its starting state is that of a sine wave signal; the point is to
	//try and mimic how a biological nervous system works.
	vec3 color3 = vec3(sinSig, sinSig, sinSig);

	
	//This is the actual inner workings of the code, once it works.
	//the end product is supposed to allow an image pertaining to the reality
	//to appear on the screen; this amounts to causing an image to appear on
	//screen, as if we're looking from the perspective of an invisible camera.
	color3.r = abs ( (color3.r-color2.r) * color2.r);

	color3.g = abs ( (color3.g-color2.g) * color2.g);

	color3.b = abs ( (color3.b-color2.b) * color2.b);

	//To make certain things are properly visible...
	//color3 = normalize (color3);

	 

	//color2 *= color2 + cosSig;

	//color2;

	//color2.r *= (pos2.x * pos2.y) * (color.r);

	//color2.g *= (pos2.x * pos2.y) * (color.g); //pos2.x * color.g;

	//color2.b *= (pos2.x * pos2.y) * (color.b); //(pos2.x / pos2.y)*color.b;

 

	//color2 = normalize(color2);

	//color *= time;

	
	//The finisher: display the results...
	gl_FragColor = vec4( color3, 1.0 );

	//gl_FragColor = vec4 ( 1.0,1.0,1.0,1.0); //To prevent seizures when needed...

 

}#ifdef GL_ES
precision mediump float;
#endif
 
#extension GL_OES_standard_derivatives : enable
 
uniform float time;
uniform vec2 mouse;
uniform vec2 resolution;
 
//NEL Prototype: The end product should eventually result in a shader that conjures forth and shows
//a single cube entirely through the power of emotions.
//
//Incomplete; Now busy actually pioneering the programming technique
// I plan on being the pioneer of...

//Normally, GLSL does not allow for persistance of information. But, it allows for a very keen
//simulation of a hardware system designed to work with pure analogue signals...
//
//All hardware engineers know that the manipulation and control of analogue signals
//happen to be Turing-complete, which means they can do everything a computer can.
//
//By mimicking a piece of hardware through GLSL, I can get around physical limitations
//inherent in the way that it works, particularly in regards to the way it handles
//information.
//
//The analogue signal is provided through multiplying the time by sine; this assumes
//time to be available as a uniform variable, something which glslsandbox easily provides.
//Credit to http://glslsandbox.com/e and http://glslsandbox.com/e#61226.0 for

//some of the code. Counts as Fair Use due to this prototype being "transformative"
//(In other words, it has an identity independant from the work it's based on).

float sinSig = sin(time*60.0);
//60.0? Where did I see that before...
//Oh, yes! Hertz per second!
//
//Talk about what should've been common sense to those who know about how
//analogue TV signals work...

//It seems that flaws within the original pseudorandom number generator
//used caused interference within the simulation that causes it to quickly become
//unstable.
 

// hash without sine
// https://www.shadertoy.com/view/4djSRW
#define MOD3 vec3(443.8975, 397.2973, 491.1871) // uv range
//#define MOD3 vec3(.1031, .11369, .13787) // int range
float hash12(vec2 p) {
   vec3 p3  = fract ( vec3( p.xyx ) *  MOD3);
   p3 += dot ( p3, p3.yzx + 19.19);
   return fract ( ( p3.x + p3.y) * p3.z);

}

 

float rand(vec3 n){

	return ( fract ( sin( ( ( hash12 (vec2(n.x,n.x*3.2) ) + hash12( vec2 (n.y,n.y*3.2) ) * 1e2 + hash12( vec2(n.z,n.z*3.2) ) * 1e4 ) * 1e-2)) * 1e5));

}

 

float rand(float n){

	return( fract( sin (( ( hash12( vec2(n,n*3.2)) + hash12(vec2(n,n*3.2)) * 1e2 + hash12(vec2(n,n*3.2)) * 1e4) * 1e-2 )) * 1e5));

}


void main( void ) {
	vec2 position = ( resolution.xy /  gl_FragCoord.xy );
	float combinedPosition = (position.x + position.y);

	
	//This variable holds the starting point to the simulation; the point is to constantly
	//transform it in order to simulate an entire reality (in this case, a spinning cube).
	//remember, this can only be done through the technique of treating the code as both
	//an empathy-based language and a pattern-based language at the exact same time.
	//
	//(Use the rule of symbolism within code to carry details not easily carried through
	// pure code; the emotions behind the code knows what to do...)
	//
	// 3.0 = rotating
	// 5.0 = cube
	// 9.0 = spin
	vec3 color = vec3(3.0,5.0,9.0);

	
	//Noise allows for code to remain non-rigid enough for the rules
	//of time to be properly simulated...
	//(combinedPosition makes certain that the noise varies between each pixel; this is important to, you know,
	//actually create noise...)
	color *= (rand(color * combinedPosition),rand(color * combinedPosition),rand(color * combinedPosition)) * time;
	
	//We want a true elctrostatic noise for an output...
	color = normalize (color);

	
	// Some parts of the signal seep too low for our needs;
	// let's clamp it to a range which allows for near
	// unnoticeable pulse...
	color = (color * ( 1.0 - 0.9)) + 0.9;
	// 1.0 - max number
	// 0.9 - min number

	
	//For reference...
	//color.r += sin( position.x * cos( time / 15.0 ) * 80.0 ) + cos( position.y * cos( time / 15.0 ) * 10.0 );

	//color.g += sin( position.y * sin( time / 10.0 ) * 40.0 ) + cos( position.x * sin( time / 25.0 ) * 40.0 );

	//color.b += sin( position.x * sin( time / 5.0 ) * 10.0 )  + sin( position.y * sin( time / 35.0 ) * 80.0 );

 

		

	//This variable is meant to represent the "Pure Intent" of the reality; this allows for
	//the reality to communicate with  the shader, thereby allowing us to see what's within
	//it.
	vec3 color2;

	
	//Generating pseudorandom noise based on the on-screen position.
	//
	//Going to need to make sure "time" doesn't get too big;
	//that causes problems within the pseudorandom noise.
	//
	//The only glitch so far with this approach seems to be the tendency
	//for the view to "flicker"; since this mimics how real life
	//faulty monitors work, however, it's still viable for our own purposes...
	color2.r = rand( ( 1.0 / fract (time / 1000.0)) * combinedPosition);
	color2.g = rand( ( 1.0 / fract (time / 1000.0)) * combinedPosition);
	color2.b = rand( ( 1.0 / fract (time / 1000.0)) * combinedPosition);

	

	//Anchoring to color; this allows for Pure Intent to be properly reflected.
	color2 *=  color;

	

	//This variable is supposed to display the end result should this shader be
	//successful. Its starting state is that of a sine wave signal; the point is to
	//try and mimic how a biological nervous system works.
	vec3 color3 = vec3(sinSig, sinSig, sinSig);

	
	//This is the actual inner workings of the code, once it works.
	//the end product is supposed to allow an image pertaining to the reality
	//to appear on the screen; this amounts to causing an image to appear on
	//screen, as if we're looking from the perspective of an invisible camera.
	color3.r = abs ( (color3.r-color2.r) * color2.r);

	color3.g = abs ( (color3.g-color2.g) * color2.g);

	color3.b = abs ( (color3.b-color2.b) * color2.b);

	//To make certain things are properly visible...
	//color3 = normalize (color3);

	 

	//color2 *= color2 + cosSig;

	//color2;

	//color2.r *= (pos2.x * pos2.y) * (color.r);

	//color2.g *= (pos2.x * pos2.y) * (color.g); //pos2.x * color.g;

	//color2.b *= (pos2.x * pos2.y) * (color.b); //(pos2.x / pos2.y)*color.b;

 

	//color2 = normalize(color2);

	//color *= time;

	
	//The finisher: display the results...
	gl_FragColor = vec4( color3, 1.0 );

	//gl_FragColor = vec4 ( 1.0,1.0,1.0,1.0); //To prevent seizures when needed...

 

}