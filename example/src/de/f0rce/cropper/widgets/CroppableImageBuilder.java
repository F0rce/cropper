package de.f0rce.cropper.widgets;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.f0rce.cropper.settings.CropperSettings;
import de.f0rce.cropper.settings.enums.ViewMode;

public class CroppableImageBuilder
{
	Logger logger = LogManager.getLogger();
	ImageFetcher imageFetcher;
	CropperSettings cropperSettings = new CropperSettings();
	String mimeType;
	String imageId;

	public CroppableImageBuilder(ImageFetcher imageFetcher, String mimeType) throws Exception
	{
		this.imageFetcher = imageFetcher;
		this.mimeType = mimeType;

	}

	public void setInitialAspectRatio(double initialAspectRatio)
	{
		this.cropperSettings.setInitialAspectRatio(initialAspectRatio);
	}

	/* There is no way to directly set the size of the crop box so we hack it.
	 * By calling this method we set the min crop box size (width and height)
	 * and then set the autoCropArea to a really small value which results
	 * in the min crop box size being set as the initial crop box.
	 */
	public void forceCropBoxSize(int widthPx, int heightPx)
	{
		this.cropperSettings.setAutoCropArea(0.01);
		this.cropperSettings.setMinCropBoxWidth(widthPx);
		this.cropperSettings.setMinCropBoxHeight(heightPx);
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

	public CroppableImage build() throws Exception
	{
		return new CroppableImage(this);
	}

	public static interface ImageFetcher
	{
		InputStream apply() throws Exception;
	}

	public void setImageId(String id)
	{
		this.imageId = id;

	}

}
