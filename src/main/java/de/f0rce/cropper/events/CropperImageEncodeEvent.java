package de.f0rce.cropper.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import de.f0rce.cropper.Cropper;

@DomEvent("cropper-image-encode")
public class CropperImageEncodeEvent extends ComponentEvent<Cropper> {

	private String imageUri;
	private String mimeType;
	private double encoderQuality;

	public CropperImageEncodeEvent(Cropper source, boolean fromClient,
			@EventData("event.detail.imageUri") String imageUri, @EventData("event.detail.mimeType") String mimeType,
			@EventData("event.detail.encoderQuality") double encoderQuality) {
		super(source, fromClient);

		this.imageUri = imageUri;
		this.mimeType = mimeType;
		this.encoderQuality = encoderQuality;
	}

	/**
	 * Returns the encoded image uri.
	 * 
	 * @return {@link String}
	 */
	public String getImageUri() {
		return this.imageUri;
	}

	/**
	 * Returns the MIME-Type the image has been encoded with.
	 * 
	 * @return {@link String}
	 */
	public String getMimeType() {
		return this.mimeType;
	}

	/**
	 * Returns the encoder quality the image has been encoded with.
	 * 
	 * @return double
	 */
	public double getEncoderQuality() {
		return this.encoderQuality;
	}

}
