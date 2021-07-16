package de.f0rce.cropper;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;

import de.f0rce.cropper.settings.CropperSettings;
import de.f0rce.cropper.settings.enums.ViewMode;

@Route("")
public class View extends Div {

	public View() {

		ByteArrayOutputStream os = new ByteArrayOutputStream(65536);

		class FileReceiver implements Receiver {

			@Override
			public OutputStream receiveUpload(String fileName, String mimeType) {
				os.reset();
				return os;
			}

		}

		Upload upload = new Upload(new FileReceiver());
		upload.setAcceptedFileTypes("image/*");
		upload.setAutoUpload(true);

		upload.addSucceededListener(evt -> {
			Dialog dialog = new Dialog();
			dialog.setHeight("600px");
			dialog.setWidth("1050px");
			dialog.setCloseOnOutsideClick(false);
			addToDialog(os, dialog, evt.getMIMEType());
		});

		add(upload);
	}

	public void addToDialog(ByteArrayOutputStream os, Dialog dialog, String mimeType) {
		Button cropButton = new Button("Crop");

		CropperSettings croppersett = new CropperSettings();
		croppersett.setAspectRatio(1);
		croppersett.setViewMode(ViewMode.ONE);
		croppersett.setCroppedImageHeight(250);
		croppersett.setCroppedImageHeight(250);
		croppersett.setRoundCropBox(true);

		Cropper crop = new Cropper(croppersett, java.util.Base64.getEncoder().encodeToString(os.toByteArray()),
				mimeType);
		crop.setHeight("500px");
		crop.setWidth("1000px");
		crop.setEncoderQuality(1.00);

		cropButton.addClickListener(event -> {
			dialog.close();
			String imageUri = crop.getImageUri();
			Image img = new Image();
			img.setSrc(imageUri);
			this.add(img);
		});

		dialog.add(crop, cropButton);
		dialog.open();
	}
}
