package de.f0rce.cropper.settings.example;

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
import de.f0rce.cropper.settings.CropperSettings;
import de.f0rce.cropper.settings.enums.ViewMode;

public class CroppableImage extends Div
{
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger();
	private ImageFetcher imageFetcher;
	private CropperSettings cropperSettings = new CropperSettings();
	private Cropper cropper;
	private String mimeType;

	public CroppableImage(ImageFetcher imageFetcher, String mimeType) throws Exception
	{
		this.imageFetcher = imageFetcher;
		this.mimeType = mimeType;
		build();
	}

	public void setInitialAspectRatio(double initialAspectRatio)
	{
		this.cropperSettings.setInitialAspectRatio(initialAspectRatio);
	}

	public void setCroppedImageHeight(int heightPx)
	{
		this.cropperSettings.setCroppedImageHeight(heightPx);
	}

	public void setCroppedImageWidth(int widthPx)
	{
		this.cropperSettings.setCroppedImageWidth(widthPx);
	}

	public void setMinCropBoxHeight(int heightPx)
	{

		this.cropperSettings.setMinCropBoxHeight(heightPx);
	}

	public void setMinCropBoxWidth(int widthPx)
	{
		this.cropperSettings.setMinCropBoxWidth(widthPx);
	}

	public void setViewMode(ViewMode mode)
	{
		this.cropperSettings.setViewMode(mode);
	}

	public void setAspectRatio(double aspectRatio)
	{
		this.cropperSettings.setAspectRatio(aspectRatio);
	}

	public void setScalable(boolean scalable)
	{
		this.cropperSettings.setScalable(scalable);
	}

	public void setZoomable(boolean zoomable)
	{
		this.cropperSettings.setZoomable(zoomable);
	}

	private void build() throws Exception
	{
		getStyle().set("display", "block");
		getStyle().set("max-width", "100%");

		add(buildCropper());

	}

	Component buildCropper() throws Exception
	{
		CropperSettings cropperSettings = new CropperSettings();

		try (var imageStream = this.imageFetcher.apply())
		{
			try
			{
				var b64Image = getB64Src();
				this.cropper = new Cropper(
						cropperSettings, b64Image, this.mimeType);

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
		try (var imageStream = this.imageFetcher.apply())
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
	public InputStream getCroppedImage()
	{

		return new ByteArrayInputStream(this.cropper.getImageBase64());
	}

	/// Returns an image with a b64 embedded src.
	public Image getImage() throws Exception
	{
		var image = new Image();
		image.setSrc(this.getB64Src());
		return image;
	}

	public static interface ImageFetcher
	{
		InputStream apply() throws Exception;
	}

}
