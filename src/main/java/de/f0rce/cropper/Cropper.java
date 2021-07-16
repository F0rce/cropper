package de.f0rce.cropper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

import de.f0rce.cropper.events.CropperImageEncodeEvent;
import de.f0rce.cropper.events.CropperReadyEvent;
import de.f0rce.cropper.settings.CropperSettings;

@Tag("lit-cropper")
@JsModule("./@f0rce/lit-cropper/lit-cropper.js")
@NpmPackage(value = "cropperjs", version = "1.5.11")
public class Cropper extends Component implements HasSize {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8763318781975323793L;

	private CropperSettings currentSettings = new CropperSettings(); // default
	private String imageUri = "";
	private double encoderQuality = 0.85;

	/**
	 * Initialize the cropper with settings.
	 * 
	 * @param settings {@link CropperSettings}
	 * @param imgSrc   {@link String}
	 * @param mimeType {@link String}
	 */
	public Cropper(CropperSettings settings, String imgSrc, String mimeType) {
		addListener(CropperImageEncodeEvent.class, this::imageEncode);
		getElement().setProperty("mimeType", mimeType);
		getElement().setProperty("cropperSettings", settings.toJSON());
		this.currentSettings = settings;
		getElement().setProperty("imgSrc", imgSrc);
		this.imageUri = imgSrc;
	}

	/**
	 * Initialize the cropper with default settings.
	 * 
	 * @param imgSrc   {@link String}
	 * @param mimeType {@link String}
	 */
	public Cropper(String imgSrc, String mimeType) {
		addListener(CropperImageEncodeEvent.class, this::imageEncode);
		getElement().setProperty("mimeType", mimeType);
		getElement().setProperty("cropperSettings", currentSettings.toJSON());
		getElement().setProperty("imgSrc", imgSrc);
		this.imageUri = imgSrc;
	}

	// private method to update private values
	private void imageEncode(CropperImageEncodeEvent event) {
		this.imageUri = event.getImageUri();
		this.encoderQuality = event.getEncoderQuality();
	}

	/**
	 * Add's a listener to the cropper as soon as the frontend is ready.
	 * 
	 * @param listener {@link ComponentEventListener}
	 * @return {@link Registration}
	 */
	public Registration onReady(ComponentEventListener<CropperReadyEvent> listener) {
		return addListener(CropperReadyEvent.class, listener);
	}

	/**
	 * Returns the cropped image uri.
	 * 
	 * @return {@link String}
	 */
	public String getImageUri() {
		return this.imageUri;
	}

	/**
	 * Returns the cropped image uri as Base64 encoded byte array.
	 * 
	 * @return byte[]
	 */
	public byte[] getImageBase64() {
		if (this.imageUri.equals("")) {
			return null;
		}
		String split = imageUri.split(",")[1];
		return Base64.getDecoder().decode(split.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Sets the quality of the image encoder (defaults to 0.85).
	 * 
	 * @param encoderQuality double
	 */
	public void setEncoderQuality(double encoderQuality) {
		double x = Math.abs(encoderQuality);
		if (x > 1) {
			x = 1.00;
		}
		getElement().setProperty("encoderOptions", x);
		this.encoderQuality = x;
	}

	/**
	 * Returns the current set image encoder quality.
	 * 
	 * @return double
	 */
	public double getEncoderQuality() {
		return this.encoderQuality;
	}

	/**
	 * When enabled the user can move the crop box.
	 */
	public void enable() {
		getElement().callJsFunction("enable");
	}

	/**
	 * When disabled the user can't move the crop box.
	 */
	public void disable() {
		getElement().callJsFunction("disable");
	}
}
