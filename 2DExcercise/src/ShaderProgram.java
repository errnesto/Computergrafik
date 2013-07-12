import static org.lwjgl.opengl.GL20.*;

import java.io.InputStream;
import java.util.Scanner;

public class ShaderProgram {
	public int id;

	public ShaderProgram(String vertexResourceName, String fragmentResourceName) {
		int vertexShader = compileFromSource(vertexResourceName, GL_VERTEX_SHADER);
		int fragmentShader = compileFromSource(fragmentResourceName, GL_FRAGMENT_SHADER);

		id = glCreateProgram();
		glAttachShader(id, vertexShader);
		glAttachShader(id, fragmentShader);

		glLinkProgram(id);
		if (glGetProgrami(id, GL_LINK_STATUS) != 1) {
			System.err.println(glGetProgramInfoLog(id, 4096));
		}
	}

	private InputStream createInputStreamFromResourceName(String resourceName) {
		return getClass().getResourceAsStream("/shaders/" + resourceName);
	}

	private int compileFromSource(String resourceName, int type) {
		int shader = 0;
		try {
			Scanner in = new Scanner(createInputStreamFromResourceName(resourceName));
			String source = in.useDelimiter("\\A").next();
			shader = glCreateShader(type);
			glShaderSource(shader, source);
			glCompileShader(shader);

			String compileLog = glGetShaderInfoLog(shader, 4096);
			if (!compileLog.isEmpty()) {
				System.err.println(resourceName + ": " + compileLog);
			}
		} catch(Exception e){
			e.printStackTrace();
			
		}

			return shader;
	}
}