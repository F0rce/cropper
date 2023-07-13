package de.f0rce.cropper.widgets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

import de.f0rce.cropper.Cropper;

public class CroppableImage extends Div
{
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger();
	private Cropper cropper;
	CroppableImageBuilder builder;
	

	public CroppableImage(CroppableImageBuilder builder) throws Exception
	{
		this.builder = builder;
		build();
	}

	private void build() throws Exception
	{
		getStyle().set("display", "block");
		getStyle().set("max-width", "100%");
		this.setId(builder.imageId);

		add(buildCropper());
	}

	Component buildCropper() throws Exception
	{
		try (var imageStream = this.builder.imageFetcher.apply())
		{
			try
			{
				var b64Image = getB64Src();
				this.cropper = new Cropper(
						this.builder.cropperSettings, b64Image, this.builder.mimeType);
				this.cropper.setId(this.builder.imageId);

				return this.cropper;
			}
			catch (IOException e1)
			{
				this.logger.error(e1, e1);
				throw e1;
			}

		}

	}

	private String getB64Src() throws Exception
	{
		String b64Image;
		try (var imageStream = this.builder.imageFetcher.apply())
		{
			b64Image = java.util.Base64.getEncoder()
					.encodeToString(IOUtils.toByteArray(imageStream));

			return b64Image;
		}
		catch (IOException e1)
		{
			this.logger.error(e1, e1);
			throw e1;
		}

	}

	// Get the cropped image as a byte stream
	public InputStream getCroppedStream()
	{
		return new ByteArrayInputStream(this.cropper.getImageBase64());
	}

	/// Returns the uncropped image with a b64 embedded src.
	public Image getImage() throws Exception
	{
		var image = new Image();
		image.setSrc("data:" + this.builder.mimeType + ";base64," + this.getB64Src());
		return image;
	}
	
	/// Returns the cropped image with a b64 embedded src.
	public Image getCroppedImage() throws Exception
	{
		var bytes = this.cropper.getImageBase64();
		// var b64String = new String(bytes);
		
		var b64String = java.util.Base64.getEncoder()
				.encodeToString(bytes);
		
		var image = new Image();
		image.setSrc("data:" + this.builder.mimeType + ";base64," + b64String);
		return image;
	}


	public static interface ImageFetcher
	{
		InputStream apply() throws Exception;
	}

	public void setImageId(String id)
	{
		this.builder.imageId = id;
		
	}

}
