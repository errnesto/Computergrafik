uniform sampler2D texture;

varying vec4 color;
varying vec3 normal;
varying vec3 vertex;
vec3 light = vec3(0,0,200);

void main (void) { 	
	float h = dot(normalize(normal),normalize(light-vertex));
	gl_FragColor = vec4( max(-h,h)*color.rgb ,color.a);
}