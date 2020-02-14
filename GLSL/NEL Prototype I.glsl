
gallery

  1

2

3

4

5

6

7

8

9

10

11

12

13

14

15

16

17

18

19

20

21

22

23

24

25

26

27

28

29

30

31

32

33

34

35

36

37

38

39

40

41

42

43

44

45

46

47

48

49

50

51

52

53

54

55

56

57

58

59

60

61

62

63

64

65

66

67

68

69

70

71

72

73

74

75

76

77

78

79

80

81

82

83

84

85

86

87

88

89

90

91

92

93

94

95

96

97

98

99

100

101

102

103

104

105

106

107

108

109

110

111

112

113

114

115

116

117

118

119

120

121

122

123

124

125

126

127

128

129

130

131

132

133

134

135

136

137

138

139

140

141

142

143

144

145

146

147

148

149

150

151

152

153

154

155

156

157

158

159

160

161

162

163

164

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

//Incomplete; One more step: Make the reality talk NTSC...

 

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

 

float sinSig = abs(sin(time*59.95));

//Mimics NTSC signal; A perfect fit, if you ask me; it allows for image generation

//via analogue signal decoding, alone.

 

 

 

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

 

//************************//

//The folowing is code copied from https://github.com/libretro/glsl-shaders/blob/master/misc/ntsc-colors.glsl .

//

//Ambigous license; the placement of a GPL license within the folder of a separate shader suggests that the

//individual shaders each have their own licenses.

//

//"Stealing" this code is a lot more easier than finding a way to decode NTSC, and then writing new

//code from scratch.

//

//The use of shader code featured under the LibRetro project should be Fair Use (and thus, allowed);

//no attempts of modification to that code is made, and the relevant code can easily be replaced with

//another. However, things vital for reproduction of programming code transformative in nature

//is not coverable by copyright (as proven within past lawsuits involving reproduction of a computer

//BIOS.

//************************//

 

vec3 DecodeGamma(vec3 color, float gamma)

{

    color = clamp(color, 0.0, 1.0);

    color.r = (color.r <= 0.00313066844250063) ?

    color.r * 12.92 : 1.055 * pow(color.r, 1.0 / gamma) - 0.055;

    color.g = (color.g <= 0.00313066844250063) ?

    color.g * 12.92 : 1.055 * pow(color.g, 1.0 / gamma) - 0.055;

    color.b = (color.b <= 0.00313066844250063) ?

    color.b * 12.92 : 1.055 * pow(color.b, 1.0 / gamma) - 0.055;

 

    return color;

}

 

vec3 RGBtoXYZ(vec3 RGB)

  {

      mat3 m = mat3(

      0.6068909, 0.1735011, 0.2003480,

      0.2989164, 0.5865990, 0.1144845,

      0.0000000, 0.0660957, 1.1162243);

  

    return RGB * m;

  }

 

vec3 XYZtoSRGB(vec3 XYZ)

{

    mat3 m = mat3(

    3.2404542,-1.5371385,-0.4985314,

   -0.9692660, 1.8760108, 0.0415560,

    0.0556434,-0.2040259, 1.0572252);

 

    return XYZ * m;

  }

 

vec3 XYZtoRGB(vec3 XYZ)

  {

      mat3 m = mat3(

      1.9099961, -0.5324542, -0.2882091,

     -0.9846663,  1.9991710, -0.0283082,

      0.0583056, -0.1183781,  0.8975535);

  

    return XYZ * m;

}

 

// conversion from NTSC RGB Reference White D65 ( color space used by NA/Japan TV's ) to XYZ

vec3 NTSC(vec3 c)

 {

     vec3 v = vec3(pow(c.r, 2.2), pow(c.g, 2.2), pow(c.b, 2.2)); //Inverse Companding

     return RGBtoXYZ(v);

 }

 

// conversion from XYZ to sRGB Reference White D65 ( color space used by windows ) 

vec3 sRGB(vec3 c)

 {

     vec3 v = XYZtoSRGB(c);

     v = DecodeGamma(v, 2.4); //Companding

 

     return v;

 }

 

// NTSC RGB to sRGB

vec3 NTSCtoSRGB( vec3 c )

 { 

     return sRGB(NTSC( c )); 

 }

 

//************************//

 

 

 

void main( void ) {

	vec2 position = ( resolution.xy /  gl_FragCoord.xy );

	float combinedPosition = (position.x + position.y);

 

	

	//This variable holds the starting point to the simulation; the point is to constantly

	//transform it in order to simulate an entire reality (in this case, a spinning cube).

	//remember, this can only be done through the technique of treating the code as both

	//an empathy-based language and a pattern-based language at the exact same time.

	//

