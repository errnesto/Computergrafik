import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL20;

public class SetUp extends AbstractSetUp {
	long t = 0;
	long prevT = Sys.getTime();
	float delta;
	float rotate;
	Texture texture;
	Texture texture2;
	ShaderProgram textureShader = new ShaderProgram("VertexShader.glsl","FragmentShader_texture.glsl");
	ShaderProgram colorShader = new ShaderProgram("VertexShader.glsl","FragmentShader_color.glsl");
	
	public static void main(String[] args){
		new SetUp().start();
	}
	/**
	 * set Preferences for OPEN_GL
	 */
	@Override
	protected void initOpenGl(){
		texture = new Texture("/Textures/wall.jpg");
		texture2 = new Texture("/Textures/schwabenlo.jpg");
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//the camera
		glFrustum(-10,10,-10,10,20,500);
		//obejects in front hide objects behind them
		glEnable(GL_DEPTH_TEST);
		//enable transparency
		glEnable(GL_BLEND);
		glEnable(GL_ALPHA_TEST);
		//blend mode so that objects can look transparent
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//enable textures
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_MODELVIEW);
		
		//smooth shader
		glShadeModel(GL_SMOOTH);
		

	}
	/**
	 * draw a Image
	 */
	@Override
	protected void render() {
		//calculate time distance from last call to controll speed of animations
		t = Sys.getTime();
		delta = (t-prevT)/1000f;
		
		//clear depth buffer so objects can go to background and colot buffer for blank frame
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		//wall in the backgound
		glPushMatrix();
			glScalef(20f, 20f, 1f);
			glTranslatef(0f,0f,-300f);
			glBegin(GL_QUADS);
				glNormal3f(0,0,100);
				glColor3f(0.2f,0.7f,0.9f);
				glVertex3i(-10,-10,10);
				glVertex3i(-10,10,10);
				glVertex3i(10,10,10);
				glVertex3i(10,-10,10);
			glEnd();
		glPopMatrix();
		//the cube
		glPushMatrix();
			//move and rotate object in position since we draw then around the origin
			glTranslatef(0f,0f,-70f);
			rotate += delta*30;
			glRotatef(rotate,0f,1f,0f);
			glRotatef(54.7f,1f,0f,1f);
			
			//the first texture
			glBindTexture(GL_TEXTURE_2D, texture.getId());
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
			//use texture shader
			GL20.glUseProgram(textureShader.id);
			
			//the coordinates of the cube
			glBegin(GL_QUADS);
				//front
				glNormal3f(0,0,100);
				glColor3f(1,0.7f,0);
				glTexCoord2f(0,0);
				glVertex3i(-10,-10,10);
				//glColor3f(1,1,0.5f);
				glTexCoord2f(1,0);
				glVertex3i(-10,10,10);
				//glColor3f(1,1,0.3f);
				glTexCoord2f(1,1);
				glVertex3i(10,10,10);
				glTexCoord2f(0,1);
				glVertex3i(10,-10,10);
			glEnd();
			
			//an other texture
			glBindTexture(GL_TEXTURE_2D, texture2.getId());
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
			
			
			glBegin(GL_QUADS);
				//back
				glNormal3f(0,0,-100);
				glColor3f(1,0.7f,0);
				glTexCoord2f(0,2);
				glVertex3i(-10,-10,-10);
				glTexCoord2f(0,0);
				glVertex3i(-10,10,-10);
				glTexCoord2f(1,0);
				glVertex3i(10,10,-10);
				glTexCoord2f(1,2);
				glVertex3i(10,-10,-10);
			glEnd();
				
			//use texture shader
			GL20.glUseProgram(colorShader.id);
			
			glBegin(GL_QUADS);
				//top
				glNormal3f(0,100,0);
				glColor3f(0.8f,1,0);
				glVertex3i(-10,10,-10);
				glColor3f(1,.5f,0);
				glVertex3i(10,10,-10);
				glColor3f(1,0.2f,0);
				glVertex3i(10,10,10);
				glColor3f(0.4f,1,0);
				glVertex3i(-10,10,10);
				
				//bottom
				glNormal3f(0,-100,0);
				glColor3f(1,0,1);
				glVertex3i(-10,-10,-10);
				glVertex3i(10,-10,-10);
				glVertex3i(10,-10,10);
				glVertex3i(-10,-10,10);
			glEnd();
		
		glPopMatrix();
	
	//the pyramid	
	glPushMatrix();
		glTranslatef(0f,10f,-70f);
		glScalef(3f, 3, 3f);
		rotate += delta*30;
		glRotatef(rotate,0f,-1f,0f);
		
		//bottom
		glBegin(GL_QUADS);
			glColor4f(1,0.2f,0,1f);
			glNormal3f(0,100,0);
			glVertex3i(-10,-10,-10);
			glVertex3i(10,-10,-10);
			glVertex3i(10,-10,10);
			glVertex3i(-10,-10,10);
		glEnd();
		
		glBegin(GL_TRIANGLES);
			//back
			glNormal3f(0,-60,40);
			glColor4f(0.5f,.5f,0,1f);
			glVertex3i(-10,-10,-10);
			glVertex3i(-10,-10,10);
			glVertex3i(0,10,0);
			
			//left
			glNormal3f(40,-60,0);
			glColor4f(0,0.7f,1,1f);
			glVertex3i(-10,-10,-10);
			glVertex3i(10,-10,-10);
			glVertex3i(0,10,0);
			
			//right
			glNormal3f(-40,-60,0);
			glColor4f(1,0.5f,1,.5f);
			glVertex3i(10,-10,-10);
			glVertex3i(10,-10,10);
			glVertex3i(0,10,0);
			
			//front
			glNormal3f(0,-60,-40);
			glColor4f(1,0.7f,0.6f,.4f);
			glVertex3i(-10,-10,10);
			glVertex3i(10,-10,10);
			glVertex3i(0,10,0);
		glEnd();
	glPopMatrix();
		
		//save time to calculate time between calls
		prevT = Sys.getTime();
		
	}

}
