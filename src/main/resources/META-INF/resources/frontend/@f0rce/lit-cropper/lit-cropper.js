/**
@license MIT
Copyright 2021 David "F0rce" Dodlek
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import { LitElement, html, css } from "lit-element";
import Cropper from "cropperjs";

class LitCropper extends LitElement {
  static get properties() {
    return {
      cropperSettings: { type: String },
      imgSrc: { type: String },
      mimeType: { type: String },
      encoderQuality: { type: Number },
    };
  }

  constructor() {
    super();
    this.encoderQuality = 0.85;
  }

  static get styles() {
    return css`
      /*!
 * Cropper.js v1.5.11
 * https://fengyuanchen.github.io/cropperjs
 *
 * Copyright 2015-present Chen Fengyuan
 * Released under the MIT license
 *
 * Date: 2021-02-17T11:53:21.992Z
 */

      .cropper-container {
        direction: ltr;
        font-size: 0;
        line-height: 0;
        position: relative;
        -ms-touch-action: none;
        touch-action: none;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      .cropper-container img {
        display: block;
        height: 100%;
        image-orientation: 0deg;
        max-height: none !important;
        max-width: none !important;
        min-height: 0 !important;
        min-width: 0 !important;
        width: 100%;
      }

      .cropper-wrap-box,
      .cropper-canvas,
      .cropper-drag-box,
      .cropper-crop-box,
      .cropper-modal {
        bottom: 0;
        left: 0;
        position: absolute;
        right: 0;
        top: 0;
      }

      .cropper-wrap-box,
      .cropper-canvas {
        overflow: hidden;
      }

      .cropper-drag-box {
        background-color: #fff;
        opacity: 0;
      }

      .cropper-modal {
        background-color: #000;
        opacity: 0.5;
      }

      .cropper-view-box {
        display: block;
        height: 100%;
        outline: 1px solid #39f;
        outline-color: rgba(51, 153, 255, 0.75);
        overflow: hidden;
        width: 100%;
      }

      .cropper-dashed {
        border: 0 dashed #eee;
        display: block;
        opacity: 0.5;
        position: absolute;
      }

      .cropper-dashed.dashed-h {
        border-bottom-width: 1px;
        border-top-width: 1px;
        height: calc(100% / 3);
        left: 0;
        top: calc(100% / 3);
        width: 100%;
      }

      .cropper-dashed.dashed-v {
        border-left-width: 1px;
        border-right-width: 1px;
        height: 100%;
        left: calc(100% / 3);
        top: 0;
        width: calc(100% / 3);
      }

      .cropper-center {
        display: block;
        height: 0;
        left: 50%;
        opacity: 0.75;
        position: absolute;
        top: 50%;
        width: 0;
      }

      .cropper-center::before,
      .cropper-center::after {
        background-color: #eee;
        content: " ";
        display: block;
        position: absolute;
      }

      .cropper-center::before {
        height: 1px;
        left: -3px;
        top: 0;
        width: 7px;
      }

      .cropper-center::after {
        height: 7px;
        left: 0;
        top: -3px;
        width: 1px;
      }

      .cropper-face,
      .cropper-line,
      .cropper-point {
        display: block;
        height: 100%;
        opacity: 0.1;
        position: absolute;
        width: 100%;
      }

      .cropper-face {
        background-color: #fff;
        left: 0;
        top: 0;
      }

      .cropper-line {
        background-color: #39f;
      }

      .cropper-line.line-e {
        cursor: ew-resize;
        right: -3px;
        top: 0;
        width: 5px;
      }

      .cropper-line.line-n {
        cursor: ns-resize;
        height: 5px;
        left: 0;
        top: -3px;
      }

      .cropper-line.line-w {
        cursor: ew-resize;
        left: -3px;
        top: 0;
        width: 5px;
      }

      .cropper-line.line-s {
        bottom: -3px;
        cursor: ns-resize;
        height: 5px;
        left: 0;
      }

      .cropper-point {
        background-color: #39f;
        height: 5px;
        opacity: 0.75;
        width: 5px;
      }

      .cropper-point.point-e {
        cursor: ew-resize;
        margin-top: -3px;
        right: -3px;
        top: 50%;
      }

      .cropper-point.point-n {
        cursor: ns-resize;
        left: 50%;
        margin-left: -3px;
        top: -3px;
      }

      .cropper-point.point-w {
        cursor: ew-resize;
        left: -3px;
        margin-top: -3px;
        top: 50%;
      }

      .cropper-point.point-s {
        bottom: -3px;
        cursor: s-resize;
        left: 50%;
        margin-left: -3px;
      }

      .cropper-point.point-ne {
        cursor: nesw-resize;
        right: -3px;
        top: -3px;
      }

      .cropper-point.point-nw {
        cursor: nwse-resize;
        left: -3px;
        top: -3px;
      }

      .cropper-point.point-sw {
        bottom: -3px;
        cursor: nesw-resize;
        left: -3px;
      }

      .cropper-point.point-se {
        bottom: -3px;
        cursor: nwse-resize;
        height: 20px;
        opacity: 1;
        right: -3px;
        width: 20px;
      }

      @media (min-width: 768px) {
        .cropper-point.point-se {
          height: 15px;
          width: 15px;
        }
      }

      @media (min-width: 992px) {
        .cropper-point.point-se {
          height: 10px;
          width: 10px;
        }
      }

      @media (min-width: 1200px) {
        .cropper-point.point-se {
          height: 5px;
          opacity: 0.75;
          width: 5px;
        }
      }

      .cropper-point.point-se::before {
        background-color: #39f;
        bottom: -50%;
        content: " ";
        display: block;
        height: 200%;
        opacity: 0;
        position: absolute;
        right: -50%;
        width: 200%;
      }

      .cropper-invisible {
        opacity: 0;
      }

      .cropper-bg {
        background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQAQMAAAAlPW0iAAAAA3NCSVQICAjb4U/gAAAABlBMVEXMzMz////TjRV2AAAACXBIWXMAAArrAAAK6wGCiw1aAAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M26LyyjAAAABFJREFUCJlj+M/AgBVhF/0PAH6/D/HkDxOGAAAAAElFTkSuQmCC");
      }

      .cropper-hide {
        display: block;
        height: 0;
        position: absolute;
        width: 0;
      }

      .cropper-hidden {
        display: none !important;
      }

      .cropper-move {
        cursor: move;
      }

      .cropper-crop {
        cursor: crosshair;
      }

      .cropper-disabled .cropper-drag-box,
      .cropper-disabled .cropper-face,
      .cropper-disabled .cropper-line,
      .cropper-disabled .cropper-point {
        cursor: not-allowed;
      }

      /*!
      * Lit-Cropper.js v1.0.0
      * https://f0rce.de/
      *
      * Copyright 2020-present David "F0rce" Dodlek
      * Released under the MIT license
      */

      img {
        display: block;
        max-width: 100%;
      }

      :host {
        display: block;
        width: 100%;
        height: 100%;
      }

      .roundCropBox .cropper-view-box,
      .roundCropBox .cropper-face {
        border-radius: 50%;
      }
    `;
  }

  render() {
    return html`<div id="img-container" style="height: 100%; width: 100%">
      <img id="image" />
    </div>`;
  }

  async firstUpdated(changedProperties) {
    this.imageContainer = this.shadowRoot.getElementById("img-container");
    this.currentImage = this.shadowRoot.getElementById("image");

    const settings = await this.validateSettings();
    this.settings = settings;

    this.initializeCropper();
  }

  validateSettings() {
    return new Promise((resolve, reject) => {
      let parsed;
      try {
        const json = JSON.parse(this.cropperSettings);
        parsed = json;
      } catch (error) {
        console.error(error);
        reject("JSON parse failed.");
      }

      if (parsed.initialAspectRatio === 0) {
        parsed.initialAspectRatio = NaN;
      }
      if (parsed.aspectRatio === 0) {
        parsed.aspectRatio = NaN;
      }

      resolve(parsed);
    });
  }

  initializeCropper() {
    if (this.settings.roundCropBox === true) {
      this.imageContainer.classList.add("roundCropBox");
    }

    this.currentImage.src = `data:${this.mimeType};base64,` + this.imgSrc;

    let _this = this;
    this.cropper = new Cropper(this.currentImage, {
      viewMode: this.settings.viewMode,
      dragMode: this.settings.dragMode,
      initialAspectRatio: this.settings.initialAspectRatio,
      aspectRatio: this.settings.aspectRatio,
      responsive: this.settings.responsive,
      restore: this.settings.restore,
      checkCrossOrigin: this.settings.checkCrossOrigin,
      checkOrientation: this.settings.checkOrientation,
      modal: this.settings.modal,
      guides: this.settings.guides,
      center: this.settings.center,
      highlight: this.settings.highlight,
      background: this.settings.background,
      autoCrop: this.settings.autoCrop,
      autoCropArea: this.settings.autoCropArea,
      movable: this.settings.movable,
      rotatable: this.settings.rotatable,
      scalable: this.settings.scalable,
      zoomable: this.settings.zoomable,
      zoomOnTouch: this.settings.zoomOnTouch,
      zoomOnWheel: this.settings.zoomOnWheel,
      wheelZoomRatio: this.settings.wheelZoomRatio,
      cropBoxMovable: this.settings.cropBoxMovable,
      cropBoxResizable: this.settings.cropBoxResizable,
      toggleDragModeOnDblclick: this.settings.toggleDragModeOnDblclick,
      minContainerWidth: this.settings.minContainerWidth,
      minContainerHeight: this.settings.minContainerHeight,
      minCanvasWidth: this.settings.minCanvasWidth,
      minCanvasHeight: this.settings.minCanvasHeight,
      minCropBoxWidth: this.settings.minCropBoxWidth,
      minCropBoxHeight: this.settings.minCropBoxHeight,
      ready() {
        _this.dispatchEvent(
          new CustomEvent("cropper-ready", {
            detail: {},
          })
        );
        _this.imageEncode();
      },
      cropend() {
        _this.imageEncode();
      },
    });
  }

  imageEncode() {
    let croppedImageUri;
    if (this.settings.roundCropBox === true) {
      const roundedCanvas = this._getRoundedCanvas(
        this.cropper.getCroppedCanvas({
          height: this.settings.croppedImageHeight,
          width: this.settings.croppedImageWidth,
        })
      );
      croppedImageUri = roundedCanvas.toDataURL(
        "image/png",
        this.encoderQuality
      );
    } else {
      croppedImageUri = this.cropper
        .getCroppedCanvas({
          height: this.settings.croppedImageHeight,
          width: this.settings.croppedImageWidth,
        })
        .toDataURL(this.mimeType, this.encoderQuality);
    }
    this.dispatchEvent(
      new CustomEvent("cropper-image-encode", {
        detail: {
          imageUri: croppedImageUri,
          mimeType: this.mimeType,
          encoderQuality: this.encoderQuality,
        },
      })
    );
  }

  _getRoundedCanvas(sourceCanvas) {
    var canvas = document.createElement("canvas");
    var context = canvas.getContext("2d");
    var width = sourceCanvas.width;
    var height = sourceCanvas.height;

    canvas.width = width;
    canvas.height = height;
    context.imageSmoothingEnabled = true;
    context.drawImage(sourceCanvas, 0, 0, width, height);
    context.globalCompositeOperation = "destination-in";
    context.beginPath();
    context.arc(
      width / 2,
      height / 2,
      Math.min(width, height) / 2,
      0,
      2 * Math.PI,
      true
    );
    context.fill();
    return canvas;
  }

  enable() {
    this.cropper.enable();
  }

  disable() {
    this.cropper.disable();
  }
}

customElements.define("lit-cropper", LitCropper);
