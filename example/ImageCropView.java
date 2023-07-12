package de.f0rce.cropper.settings.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import de.f0rce.cropper.settings.enums.ViewMode;
import dev.onepub.ui.wizard.CroppableImage;

@Route(value = ImageCropView.NAME)
public class ImageCropView extends VerticalLayout
{
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger();

	public static final String NAME = "ImageCropView";


	public ImageCropView()
	{
			build();
	}

	/**
	 * @throws Exception 
	 */
	protected void build()
	{
		var croppableImage = new CroppableImage(
				() -> fetchImage(), "image/png");
		croppableImage.setId(generatedImage.buildSwatchS3Key(this.category, includeVersion));
		croppableImage.setViewMode(ViewMode.ONE);
		croppableImage.setAspectRatio(0.0);
		croppableImage.setInitialAspectRatio(GeneratedImage.SWATCH_HEIGHT / GeneratedImage.SWATCH_WIDTH);
		croppableImage.setScalable(false);
		croppableImage.setZoomable(false);
		croppableImage.setMinCropBoxWidth(ImageRequest.DEFAULT_WIDTH);
		croppableImage.setMinCropBoxHeight(ImageRequest.DEFAULT_HEIGHT);
		

		 Button cropButton = new Button("crop", e -> {
			/// Displayed the cropped image.
			var  image = cropper.getImage();
			add(image);
		});

		return croppableImage;

	}

	InputStream fetchImage() {
		/// load the image from a file or the network and return it
		/// as an input stream.
	}
	
}
