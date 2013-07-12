import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * creates a Display obeject and placeholder Methods to set Up OpenGL and draw some output
 * @author Ernesto
 *
 */
public abstract class AbstractSetUp {
	AbstractSetUp(){
		try {
			Display.setDisplayMode(new DisplayMode(800,800));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initOpenGl();
	}
	
	/**
	 * updates the display 
	 */
	public void start(){
		while(!Display.isCloseRequested()){
			render();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	protected abstract void initOpenGl(); 
	
	protected abstract void render(); 

}
