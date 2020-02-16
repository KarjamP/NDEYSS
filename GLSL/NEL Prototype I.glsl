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

float sinSignal = sin(time*100.); 
 

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

	
	
        //This variable holds the starting point to the simulation; the point is to constantly
	//transform it in order to simulate an entire reality (in this case, a spinning cube).
	//remember, this can only be done through the technique of treating the code as both
	//an empathy-based language and a pattern-based language at the exact same time.
	//
	//(Use the rule of symbolism within code to carry details not easily carried through
	// pure code; the emotions behind the code knows what to do...)
	
	
	vec3 nel = vec3( 3.0, 5.0, 9.0);
	
	nel *= sinSignal;
	
	
	//nel = normalize(nel);
	
	//Reliable, but the nature of this implementation forces one to work in 
	//terms of ratio, not direct variables.
	//
	//Close enough. Was struggling with the "direct variable" approach
	//in either case. Now, it's just a matter of discovering how to literally
	//program in ratios...
	

	
	
	vec3 noise = vec3 (rand(combinedPosition/time),rand(combinedPosition/time),rand(combinedPosition/time));

	

	//Anchoring to color; this allows for Pure Intent to be properly reflected.
	//color2 /=  color;

	

	//This variable is supposed to display the end result should this shader be
	//successful. Its starting state is that of a sine wave signal; the point is to
	//try and mimic how a biological nervous system works.
	vec3 color; //= vec3(sinSig, sinSig, sinSig);

	
	//This is the actual inner workings of the code, once it works.
	//the end product is supposed to allow an image pertaining to the reality
	//to appear on the screen; this amounts to causing an image to appear on
	//screen, as if we're looking from the perspective of an invisible camera.
	
	//float nel1Sum = nel1.r+nel1.g+nel1.b;
	color = vec3(rand(noise),rand(noise),rand(noise));
	
	//color = vec3(rand(noise*nel1),rand(noise*(nel2),rand(noise*nel3)));
	

	
	
	
	
	//color3 = normalize(color3);
	
	//TODO: actually translate this so that the reality talks NTSC...
	//color3.r =  ((1./color2.r) * rand(combinedPosition/time) );

	//color3.g =  ((1./color2.g) * rand(combinedPosition*time) );

	//color3.b =  ((1./color2.b) * rand(combinedPosition*time) );
	
	
	//TEST: NTSC algorithm is enough...?
	//color3 = abs(NTSC (color3));
	
	//TEST: Let's see if this works...
	
	//color3 = DecodeGamma (color3, 3.4);
	
	//color3 = mix(color3.rgb, NTSCtoSRGB(color3.rgb), 1.0);
	
	//color3 = normalize(color3);

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
	gl_FragColor = vec4( nel, 1.0 );

	//gl_FragColor = vec4 ( 1.0,1.0,1.0,1.0); //To prevent seizures when needed...

 

}