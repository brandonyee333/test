import Component, {Config} from 'metal-jsx';
import Cropper from 'cropperjs';
import {bindAll, mapValues} from 'lodash';

import Button from '../Button';
import Modal from '../modal';

class CropImageModal extends Component {
	created() {
		bindAll(
			this,
			'initializeCropper_',
			'handleCancel_',
			'handleSave_'
		);
	}

	initializeCropper_(event) {
		this._cropper = new Cropper(
			event.target,
			{
				aspectRatio: 1,
				autoCropArea: 1,
				zoomable: false
			}
		);
	}

	handleCancel_() {
		const {hideModal, onClose} = this.props;

		hideModal();
		onClose();
	}

	handleSave_() {
		const {imageData, onSubmit} = this.props;

		imageData.cropRegion = mapValues(
			this._cropper.getData(),
			value => Math.round(value)
		);

		onSubmit(imageData);
	}

	render() {
		return (
			<div class="crop-image-modal-container">
				<Modal.Header onClose={this.handleCancel_}>
					{Liferay.Language.get('crop-image')}
				</Modal.Header>

				<Modal.Body>
					<div class="image-cropper">
						<img class="crop-image" onLoad={this.initializeCropper_} src={this.props.imageData.imageURL_raw} />
					</div>
				</Modal.Body>

				<Modal.Footer>
					<Button onClick={this.handleCancel_} role="secondary">{Liferay.Language.get('cancel')}</Button>

					<Button onClick={this.handleSave_} role="primary">{Liferay.Language.get('save')}</Button>
				</Modal.Footer>
			</div>
		);
	}
}

CropImageModal.PROPS = {
	hideModal: Config.func(),
	imageData: Config.object(),
	onClose: Config.func(),
	onSubmit: Config.func()
};

export default CropImageModal;