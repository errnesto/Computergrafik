varying vec4 color;
varying vec3 normal;
varying vec3 vertex;

void main (void) {
	color = gl_Color; 
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; 
	gl_TexCoord[0] = gl_MultiTexCoord0;
	normal = gl_NormalMatrix*gl_Normal;
	vertex = (gl_ModelViewMatrix*gl_Vertex).xyz;
}