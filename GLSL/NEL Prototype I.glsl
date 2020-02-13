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
//Incomplete; need to figure out how to interpret electrostatic noise as a video signal.


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

float rand(vec3 n){
	return fract(sin(((n.x+n.y*1e2+n.z*1e4)*1e-2)*1e5)*time);
}

float rand(float n){
	return fract(sin(((n+n*1e2+n*1e4)*1e-2)*1e5)*time);
}


void main( void ) {

	vec2 position = ( gl_FragCoord.xy / resolution.xy );
	
	vec2 pos = vec2 (sin(position.x*time),sin(position.y*time));

	vec3 color = vec3(5.0,1.0,2.0);
	//color.r += sin( position.x * cos( time / 15.0 ) * 80.0 ) + cos( position.y * cos( time / 15.0 ) * 10.0 );
	//color.g += sin( position.y * sin( time / 10.0 ) * 40.0 ) + cos( position.x * sin( time / 25.0 ) * 40.0 );
	//color.b += sin( position.x * sin( time / 5.0 ) * 10.0 ) + sin( position.y * sin( time / 35.0 ) * 80.0 );

		
	color. r = (pos.x + pos.y) * color.r;
	color. g = (pos.x + pos.y) * color.g;
	color. b = (pos.x + pos.y) * color.b;

	color *= rand(color)*color;

	//color *= time;
	
	gl_FragColor = vec4( color, 1.0 );

}