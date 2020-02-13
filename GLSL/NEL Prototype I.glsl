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
//Incomplete; Alright, got the "Pure Intent" behind the simulated reality to finally show. 
//Just need it to command the relevant drawing protocals...

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
//Decoding is done through the usage of the Cosine; something that counteracts with sine
//makes perfect sense, if you are to think about it...

//Credit to http://glslsandbox.com/e and http://glslsandbox.com/e#61226.0 for
//some of the code. Counts as Fair Use due to this prototype being "transformative"
//(In other words, it has an identity independant from the work it's based on).

float sinSig = sin(time*4.0);
float cosSig = cos(time*4.0);

float rand(vec3 n){
	return (fract(sin(((n.x + n.y * 1e2 + n.z * 1e4 ) * 1e-2)) * 1e5));
}

float rand(float n){
	return(fract(sin(((n + n * 1e2 + n * 1e4) * 1e-2 )) * 1e5));
}


void main( void ) {

	vec2 position = ( gl_FragCoord.xy / resolution.xy );
	
	vec2 pos = position * sinSig;
	vec2 pos2 = position * cosSig;

	vec3 color = vec3(5.0,1.0,2.0) * sinSig;
	color *= color;
	
	color += (rand(color)* 2.0 ,rand(color) * 2.0,rand(color) * 2.0);
	color *= fract(color * time * sinSig);
	color = (color * (1.0-0.8)) + 0.8;
	
	//vec3 colIntent = color;
	//color *= (time * abs(sinSig)) * colIntent;
	
	
	//color *= vec3(rand(color),rand(color),rand(color)) * time * colIntent;
	
	//color *= abs(sinSig) / (( rand(color), rand ( color ),rand (color)) * (color));
	
	//color = normalize(color) ;	                //Anchoring to time makes it conformant to its rules,
						        //as much as it causes the variable to steadily climb...
	//color.r += sin( position.x * cos( time / 15.0 ) * 80.0 ) + cos( position.y * cos( time / 15.0 ) * 10.0 );
	//color.g += sin( position.y * sin( time / 10.0 ) * 40.0 ) + cos( position.x * sin( time / 25.0 ) * 40.0 );
	//color.b += sin( position.x * sin( time / 5.0 ) * 10.0 )  + sin( position.y * sin( time / 35.0 ) * 80.0 );

		
		
	vec3 color2;
	
	color2.r = rand(time*(position.x) + (position.y));
	color2.g = rand(time*(position.x) + (position.y));
	color2.b = rand(time*(position.x) + (position.y));
	
	
	color2 *=  (color2 * color);
	
	//color2 *= color2 + cosSig;
	//color2;
	//color2.r *= (pos2.x * pos2.y) * (color.r);
	//color2.g *= (pos2.x * pos2.y) * (color.g); //pos2.x * color.g;
	//color2.b *= (pos2.x * pos2.y) * (color.b); //(pos2.x / pos2.y)*color.b;

	//color2 = normalize(color2);
	//color *= time;
	
	gl_FragColor = vec4( color2, 1.0 );
	//gl_FragColor = vec4 ( 1.0,1.0,1.0,1.0); //To prevent seizures when needed...

}