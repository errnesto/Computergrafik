import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;

public class SetUp extends AbstractSetUp {
	long t = 0;
	long prevT = Sys.getTime();
	float delta;
	float rotate;
	Texture texture;
	Texture texture2;
	
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
		glFrustum(-10,10,-10,10,5,100);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_MODELVIEW);
		
		glShadeModel(GL_SMOOTH);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	/**
	 * draw a Image
	 */
	@Override
	protected void render() {
		t = Sys.getTime();
		delta = (t-prevT)/1000f;
		//create black background
		glClear(GL_DEPTH_BUFFER_BIT);
		
		glBegin(GL_QUADS);
		//wand
		glColor4f(0,0,0,0.03f);
		glVertex3i(-200,-200,-99);
		glVertex3i(-200,200,-99);
		glVertex3i(200,200,-99);
		glVertex3i(200,-200,-99);
		glEnd();
		
		glPushMatrix();
		glTranslatef(0f,0f,-40f);
		rotate += delta*30;
		glRotatef(rotate,0f,1f,0f);
		glTranslatef(0f,0f,40f);
		
		
		glRotatef(45f,1f,0f,1f);
		glTranslatef(0f,0f,-40f);
		
		glBindTexture(GL_TEXTURE_2D, texture.getId());
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
		glBegin(GL_QUADS);
		
		//vorne
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
		
		glBindTexture(GL_TEXTURE_2D, texture2.getId());
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
		
		glBegin(GL_QUADS);
		//hinten
		glColor3f(1,0.7f,0);
		glTexCoord2f(0,2);
		glVertex3i(-10,-10,-10);
		glTexCoord2f(0,0);
		glVertex3i(-10,10,-10);
		glTexCoord2f(1,0);
		glVertex3i(10,10,-10);
		glTexCoord2f(1,2);
		glVertex3i(10,-10,-10);
		
		//oben
		glColor3f(1,1,0);
		glVertex3i(-10,10,-10);
		glVertex3i(10,10,-10);
		glVertex3i(10,10,10);
		glVertex3i(-10,10,10);
		
		//unten
		glColor3f(1,0,1);
		glVertex3i(-10,-10,-10);
		glVertex3i(10,-10,-10);
		glVertex3i(10,-10,10);
		glVertex3i(-10,-10,10);
		
		glEnd();
		glPopMatrix();
		prevT = Sys.getTime();
		
	}

}
