uniform sampler2D texture;

varying vec4 color;
varying vec3 normal;
varying vec3 vertex;
vec3 light = vec3(10,100,200);

void main (void) { 
	vec4 texColor = texture2D(texture,gl_TexCoord[0].st);
	float h = dot(normalize(normal),normalize(light-vertex));
	gl_FragColor = vec4(max(-h,h)*texColor.rgb,texColor.a);
}