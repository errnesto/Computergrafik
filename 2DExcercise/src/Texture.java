import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class Texture {
	private int id;

	public Texture(String resourceName) {
		try {
			createByteBufferFromImage(ImageIO.read(getClass().getResourceAsStream(resourceName)));
		} catch (IOException e) {
			throw new RuntimeException("Unable to read texture from stream", e);
		}
	}

	public int getId() {
		return id;
	}
	
	private void createByteBufferFromImage(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		boolean hasAlpha = image.getColorModel().hasAlpha();

		ByteBuffer buffer = ByteBuffer.allocateDirect((hasAlpha ? 4 : 3) * width * height);

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				int argb = image.getRGB(x, y);
				buffer.put((byte) ((argb >> 16) & 0xff));
				buffer.put((byte) ((argb >> 8) & 0xff));
				buffer.put((byte) (argb & 0xff));
				if (hasAlpha) {
					buffer.put((byte) ((argb >> 24) & 0xff));
				}
			}
		}
		buffer.flip();

		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		glTexImage2D(GL_TEXTURE_2D, 0, hasAlpha ? GL_RGBA : GL_RGB, width, height, 0, hasAlpha ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, buffer);
	}
}