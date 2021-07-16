package de.f0rce.cropper.settings;

import java.util.Objects;

import de.f0rce.cropper.Cropper;
import de.f0rce.cropper.settings.enums.DragMode;
import de.f0rce.cropper.settings.enums.ViewMode;

/**
 * Define settings for the {@link Cropper}, which can be applied via the
 * {@link Cropper#Cropper(CropperSettings, String, String)} constructor.
 * 
 * @author David "F0rce" Dodlek
 */
public class CropperSettings {

	private ViewMode viewMode = ViewMode.ZERO;
	private DragMode dragMode = DragMode.crop;
	private double initialAspectRatio = 0.0;
	private double aspectRatio = 0.0;
	// private Object data;
	// private Element, Array, NodeList or String preview;
	private boolean responsive = true;
	private boolean restore = true;
	private boolean checkCrossOrigin = true;
	private boolean checkOrientation = true;
	private boolean modal = true;
	private boolean guides = true;
	private boolean center = true;
	private boolean highlight = true;
	private boolean background = true;
	private boolean autoCrop = true;
	private double autoCropArea = 0.8;
	private boolean movable = true;
	private boolean rotatable = true;
	private boolean scalable = true;
	private boolean zoomable = true;
	private boolean zoomOnTouch = true;
	private boolean zoomOnWheel = true;
	private double wheelZoomRatio = 0.1;
	private boolean cropBoxMovable = true;
	private boolean cropBoxResizable = true;
	private boolean toogleDragModeOnDblclick = true;
	private int minContainerWidth = 200;
	private int minContainerHeight = 100;
	private int minCanvasWidth = 0;
	private int minCanvasHeight = 0;
	private int minCropBoxWidth = 0;
	private int minCropBoxHeight = 0;
	private int croppedImageHeight = 4096;
	private int croppedImageWidth = 4096;
	private boolean roundCropBox = false;

	/**
	 * Define the view mode of the cropper. If you set <code>viewMode</code> to
	 * {@link ViewMode#ZERO} the crop box can extend outside the canvas, while a
	 * value of {@link ViewMode#ONE}, {@link ViewMode#TWO} or {@link ViewMode#THREE}
	 * will restrict the crop box to the size of the canvas. <code>viewMode</code>
	 * of {@link ViewMode#TWO} or {@link ViewMode#THREE} will additionally restrict
	 * the canvas to the container. There is no difference between
	 * {@link ViewMode#TWO} and {@link ViewMode#THREE} when the proportions of the
	 * canvas and the container are the same. <br>
	 * <br>
	 * <strong>Default:</strong> {@link ViewMode#ZERO} <br>
	 * <br>
	 * <strong>Options:</strong><br>
	 * - {@link ViewMode#ZERO}: no restrictions<br>
	 * - {@link ViewMode#ONE}: restrict the crop box not to exceed the size of the
	 * canvas.<br>
	 * - {@link ViewMode#TWO}: restrict the minimum canvas size to fit within the
	 * container. If the proportions of the canvas and the container differ, the
	 * minimum canvas will be surrounded by extra space in one of the
	 * dimensions.<br>
	 * - {@link ViewMode#THREE}: restrict the minimum canvas size to fill fit the
	 * container. If the proportions of the canvas and the container are different,
	 * the container will not be able to fit the whole canvas in one of the
	 * dimensions.
	 * 
	 * @param viewMode {@link ViewMode}
	 */
	public void setViewMode(ViewMode viewMode) {
		Objects.requireNonNull(viewMode);
		this.viewMode = viewMode;
	}

	/**
	 * Define the dragging mode of the cropper.<br>
	 * <br>
	 * <strong>Default:</strong> {@link DragMode#crop} <br>
	 * <br>
	 * <strong>Options:</strong> <br>
	 * - {@link DragMode#crop}: creates a new crop box <br>
	 * - {@link DragMode#move}: move the canvas <br>
	 * - {@link DragMode#none}: do nothing
	 * 
	 * @param dragMode {@link DragMode}
	 */
	public void setDragMode(DragMode dragMode) {
		Objects.requireNonNull(dragMode);
		this.dragMode = dragMode;
	}

	/**
	 * Define the initial aspect ratio of the crop box. By default, it is the same
	 * as the aspect ratio of the canvas (image wrapper). <br>
	 * <br>
	 * <strong>Default:</strong> <code>0</code> (interpreted as null/NaN by
	 * frontend)<br>
	 * <br>
	 * Only availible when the {@link #setAspectRatio(double)} option is set to
	 * 0.<br>
	 * <br>
	 * <strong>Example:</strong><br>
	 * <code>
	 * CropperSettings cropperSettings = new CropperSettings();
	 * cropperSettings.setAspectRatio(0); // optional
	 * cropperSettings.setInitialAspectRatio(16.0 / 9.0); // make sure to add the .0 to get the correct result
	 * </code>
	 * 
	 * 
	 * @param initialAspectRatio int
	 */
	public void setInitialAspectRatio(double initialAspectRatio) {
		this.initialAspectRatio = initialAspectRatio;
	}

	/**
	 * Define the fixed aspect ratio of the crop box. By default, the crop box has a
	 * free ratio. <br>
	 * <br>
	 * <strong>Default:</strong> <code>0</code> (interpreted as null/NaN by
	 * frontend)<br>
	 * <br>
	 * <strong>Example:</strong><br>
	 * <code>
	 * CropperSettings cropperSettings = new CropperSettings();
	 * cropperSettings.setAspectRatio(16.0 / 9.0); // make sure to add the .0 to get the correct result
	 * </code>
	 * 
	 * @param aspectRatio int
	 */
	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	/**
	 * Re-render the cropper when resizing the window.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param responsive boolean
	 */
	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
	}

	/**
	 * Restore the cropped area after resizing the window.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param restore boolean
	 */
	public void setRestore(boolean restore) {
		this.restore = restore;
	}

	/**
	 * Check if the current image is a cross-origin image.<br>
	 * <br>
	 * If so, a <code>crossOrigin</code> attribute will be added to the cloned image
	 * element, and a timestamp parameter will be added to the <code>src</code>
	 * attribute to reload the source image to avoid browser cache error.<br>
	 * <br>
	 * Adding a <code>crossOrigin</code> attribute to the image element will stop
	 * adding a timestamp to the image URL and stop reloading the image. But the
	 * request (XMLHttpRequest) to read the image data for orientation checking will
	 * require a timestamp to bust cache to avoid browser cache error. You can set
	 * the {@link #setCheckOrientation(boolean)} option to <code>false</code> to
	 * cancel this request.<br>
	 * <br>
	 * If the value of the image's <code>crossOrigin</code> attribute is
	 * <code>"use-credentials"</code>, then the <code>withCredentials</code>
	 * attribute will set to <code>true</code> when read the image data by
	 * XMLHttpRequest.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param checkCrossOrigin boolean
	 */
	public void setCheckCrossOrigin(boolean checkCrossOrigin) {
		this.checkCrossOrigin = checkCrossOrigin;
	}

	/**
	 * Check the current image's Exif Orientation information. Note that only a JPEG
	 * image may contain Exif Orientation information.<br>
	 * <br>
	 * Exactly, read the Orientation value for rotating or flipping the image, and
	 * then override the Orientation value with 1 (the default value) to avoid some
	 * issues on iOS devices.<br>
	 * <br>
	 * Requires to set both the {@link #setRotatable(boolean)} and
	 * {@link #setScalable(boolean)} options to <code>true</code> at the same
	 * time.<br>
	 * <br>
	 * <strong>Note:</strong> Do not trust this all the time as some JPG images may
	 * have incorrect (non-standard) Orientation values<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param checkOrientation boolean
	 */
	public void setCheckOrientation(boolean checkOrientation) {
		this.checkOrientation = checkOrientation;
	}

	/**
	 * Show the black modal above the image and under the crop box.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param modal boolean
	 */
	public void setModal(boolean modal) {
		this.modal = modal;
	}

	/**
	 * Show the dashed lines above the crop box.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param guides boolean
	 */
	public void setGuides(boolean guides) {
		this.guides = guides;
	}

	/**
	 * Show the center indicator above the crop box.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param center boolean
	 */
	public void setCenter(boolean center) {
		this.center = center;
	}

	/**
	 * Show the white modal above the crop box (highlight the crop box).<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param highlight boolean
	 */
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	/**
	 * Show the grid background of the container.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param background boolean
	 */
	public void setBackground(boolean background) {
		this.background = background;
	}

	/**
	 * Enable to crop the image automatically when initialized.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param autoCrop boolean
	 */
	public void setAutoCrop(boolean autoCrop) {
		this.autoCrop = autoCrop;
	}

	/**
	 * It should be a number between 0 and 1. Define the automatic cropping area
	 * size (percentage).<br>
	 * <br>
	 * <strong>Default:</strong> <code>0.8</code> (80% of the image)
	 * 
	 * @param autoCropArea double
	 */
	public void setAutoCropArea(double autoCropArea) {
		this.autoCropArea = autoCropArea;
	}

	/**
	 * Enable to move the image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param movable boolean
	 */
	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	/**
	 * Enable to rotate the image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param rotatable boolean
	 */
	public void setRotatable(boolean rotatable) {
		this.rotatable = rotatable;
	}

	/**
	 * Enable to scale the image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param scalable boolean
	 */
	public void setScalable(boolean scalable) {
		this.scalable = scalable;
	}

	/**
	 * Enable to zoom the image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param zoomable boolean
	 */
	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}

	/**
	 * Enable to zoom the image by dragging touch.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param zoomOnTouch boolean
	 */
	public void setZoomOnTouch(boolean zoomOnTouch) {
		this.zoomOnTouch = zoomOnTouch;
	}

	/**
	 * Enable to zoom the image by mouse wheeling.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param zoomOnWheel boolean
	 */
	public void setZoomOnWheel(boolean zoomOnWheel) {
		this.zoomOnWheel = zoomOnWheel;
	}

	/**
	 * Define zoom ratio when zooming the image by mouse wheeling.<br>
	 * <br>
	 * <strong>Default:</strong> <code>0.1</code>
	 * 
	 * @param wheelZoomRatio double
	 */
	public void setWheelZoomRatio(double wheelZoomRatio) {
		this.wheelZoomRatio = wheelZoomRatio;
	}

	/**
	 * Enable to move the crop box by dragging.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param cropBoxMovable boolean
	 */
	public void setCropBoxMovable(boolean cropBoxMovable) {
		this.cropBoxMovable = cropBoxMovable;
	}

	/**
	 * Enable to resize the crop box by dragging.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param cropBoxResizable boolean
	 */
	public void setCropBoxResizable(boolean cropBoxResizable) {
		this.cropBoxResizable = cropBoxResizable;
	}

	/**
	 * Enable to toggle drag mode between <code>"crop"</code> and
	 * <code>"move"</code> when clicking twice on the cropper.<br>
	 * <br>
	 * <strong>Default:</strong> <code>true</code>
	 * 
	 * @param toogleDragModeOnDblclick boolean
	 */
	public void setToogleDragModeOnDblclick(boolean toogleDragModeOnDblclick) {
		this.toogleDragModeOnDblclick = toogleDragModeOnDblclick;
	}

	/**
	 * The minimum width of the container.<br>
	 * <br>
	 * <strong>Default:</strong> <code>200</code>
	 * 
	 * @param minContainerWidth int
	 */
	public void setMinContainerWidth(int minContainerWidth) {
		this.minContainerWidth = minContainerWidth;
	}

	/**
	 * The minimum height of the container.<br>
	 * <br>
	 * <strong>Default:</strong> <code>100</code>
	 * 
	 * @param minContainerHeight int
	 */
	public void setMinContainerHeight(int minContainerHeight) {
		this.minContainerHeight = minContainerHeight;
	}

	/**
	 * The minimum width of the canvas (image wrapper).<br>
	 * <br>
	 * <strong>Default:</strong> <code>0</code>
	 * 
	 * @param minCanvasWidth int
	 */
	public void setMinCanvasWidth(int minCanvasWidth) {
		this.minCanvasWidth = minCanvasWidth;
	}

	/**
	 * The minimum height of the canvas (image wrapper).<br>
	 * <br>
	 * <strong>Default:</strong> <code>0</code>
	 * 
	 * @param minCanvasHeight int
	 */
	public void setMinCanvasHeight(int minCanvasHeight) {
		this.minCanvasHeight = minCanvasHeight;
	}

	/**
	 * The minimum width of the crop box.<br>
	 * <br>
	 * <strong>Note:</strong> This size is relative to the page, not the image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>0</code>
	 * 
	 * @param minCropBoxWidth int
	 */
	public void setMinCropBoxWidth(int minCropBoxWidth) {
		this.minCropBoxWidth = minCropBoxWidth;
	}

	/**
	 * The minimum height of the crop box.<br>
	 * <br>
	 * <strong>Note:</strong> This size is relative to the page, not the image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>0</code>
	 * 
	 * @param minCropBoxHeight int
	 */
	public void setMinCropBoxHeight(int minCropBoxHeight) {
		this.minCropBoxHeight = minCropBoxHeight;
	}

	/**
	 * Sets the height of the cropped image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>4096</code>
	 * 
	 * @param croppedImageHeight int
	 */
	public void setCroppedImageHeight(int croppedImageHeight) {
		this.croppedImageHeight = croppedImageHeight;
	}

	/**
	 * Sets the width of the cropped image.<br>
	 * <br>
	 * <strong>Default:</strong> <code>4096</code>
	 * 
	 * @param croppedImageWidth int
	 */
	public void setCroppedImageWidth(int croppedImageWidth) {
		this.croppedImageWidth = croppedImageWidth;
	}

	/**
	 * Sets the crop box of the cropper to be round.
	 * 
	 * @param roundCropBox boolean
	 */
	public void setRoundCropBox(boolean roundCropBox) {
		this.roundCropBox = roundCropBox;
	}

	/**
	 * Turns the settings into an JSON Object which will be sent to the frontend as
	 * soon as cropper gets initialized.
	 * 
	 * @return {@link String}
	 */
	public String toJSON() {
		return "{ \"viewMode\": " + viewMode.getValue() + ", \"dragMode\": \"" + dragMode
				+ "\", \"initialAspectRatio\": " + initialAspectRatio + ", \"aspectRatio\": " + aspectRatio
				+ ", \"responsive\": " + responsive + ", \"restore\": " + restore + ", \"checkCrossOrigin\": "
				+ checkCrossOrigin + ", \"checkOrientation\": " + checkOrientation + ", \"modal\": " + modal
				+ ", \"guides\": " + guides + ", \"center\": " + center + ", \"highlight\": " + highlight
				+ ", \"background\": " + background + ", \"autoCrop\": " + autoCrop + ", \"autoCropArea\": "
				+ autoCropArea + ", \"movable\": " + movable + ", \"rotatable\": " + rotatable + ", \"scalable\": "
				+ scalable + ", \"zoomable\": " + zoomable + ", \"zoomOnTouch\": " + zoomOnTouch + ", \"zoomOnWheel\": "
				+ zoomOnWheel + ", \"wheelZoomRatio\": " + wheelZoomRatio + ", \"cropBoxMovable\": " + cropBoxMovable
				+ ", \"cropBoxResizable\": " + cropBoxResizable + ", \"toogleDragModeOnDblclick\": "
				+ toogleDragModeOnDblclick + ", \"minContainerWidth\": " + minContainerWidth
				+ ", \"minContainerHeight\": " + minContainerHeight + ", \"minCanvasWidth\": " + minCanvasWidth
				+ ", \"minCanvasHeight\": " + minCanvasHeight + ", \"minCropBoxWidth\": " + minCropBoxWidth
				+ ", \"minCropBoxHeight\": " + minCropBoxHeight + ", \"croppedImageHeight\": " + croppedImageHeight
				+ ", \"croppedImageWidth\": " + croppedImageWidth + ", \"roundCropBox\": " + roundCropBox + " }";
	}

}
