import {bindAll, mapValues} from 'lodash';
import Cropper from 'cropperjs';
import JSXComponent, {Config} from 'metal-jsx';

import Button from './Button';
import Modal from './Modal';

class CropImageModal extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleCancel',
			'handleSave',
			'initializeCropper_'
		);
	}

	disposed() {
		if (this._cropper) {
			this._cropper.destroy();
		}
	}

	handleCancel() {
		this.setState({showModal: false});

		this.props.onCancel();
	}

	handleSave() {
		const {imageData, onSubmit} = this.props;

		const cropData = mapValues(
			this._cropper.getData(),
			value => Math.round(value)
		);

		onSubmit(imageData.file, cropData);

		this.setState({showModal: false});
	}

	initializeCropper_(event) {
		this._cropper = new Cropper(
			event.target,
			{
				zoomable: false
			}
		);
	}

	render() {
		const {imageData} = this.props;

		const {showModal} = this.state;

		const modalBody = (
			<div class="image-cropper">
				<img class="crop-image" onLoad={this.initializeCropper_} src={imageData.preview} />
			</div>
		);

		const modalFooter = [
			<Button className="modal-button" key="btn-action" label={Liferay.Language.get('cancel')} onClick={this.handleCancel} />,
			<Button className="modal-button" key="btn-cancel" label={Liferay.Language.get('save')} onclick={this.handleSave} />
		];

		return (
			<div class="crop-image-modal-container">
				{showModal &&
					<Modal
						body={modalBody}
						close={this.handleCancel}
						footer={modalFooter}
						header={Liferay.Language.get('crop-image')}
					/>
				}
			</div>
		);
	}
}

CropImageModal.PROPS = {
	imageData: Config.object().value({}),
	onCancel: Config.func(),
	onSubmit: Config.func()
};

CropImageModal.STATE = {
	showModal: Config.bool().value(true)
};

export default CropImageModal;