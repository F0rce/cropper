package de.f0rce.cropper.settings.enums;

public enum ViewMode {

	ZERO(0), ONE(1), TWO(2), THREE(3);

	private int value;

	private ViewMode(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
