package de.f0rce.cropper.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

import de.f0rce.cropper.Cropper;

@DomEvent("cropper-ready")
public class CropperReadyEvent extends ComponentEvent<Cropper> {

  public CropperReadyEvent(Cropper source, boolean fromClient) {
    super(source, fromClient);
    // TODO Auto-generated constructor stub
  }
}
