import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';

import Button from './Button';
import Modal from './modal';

class ConfirmDialogModal extends Component {
	created() {
		bindAll(
			this,
			'handleCancel_',
			'handleConfirm_'
		);
	}

	handleCancel_() {
		const {hideModal, onCancel} = this.props;

		hideModal();

		if (onCancel) {
			onCancel();
		}
	}

	handleConfirm_() {
		const {hideModal, onConfirm} = this.props;

		hideModal();
		onConfirm();
	}

	render() {
		const {confirmButtonText, message} = this.props;

		return (
			<div class="confirm-dialog-modal-container">
				<Modal.Body>
					{message}
				</Modal.Body>

				<Modal.Footer>
					<Button onClick={this.handleCancel_} role="secondary">{Liferay.Language.get('cancel')}</Button>

					<Button onClick={this.handleConfirm_} role="primary">{confirmButtonText}</Button>
				</Modal.Footer>
			</div>
		);
	}
}

ConfirmDialogModal.PROPS = {
	confirmButtonText: Config.string().value(Liferay.Language.get('confirm')),
	hideModal: Config.func(),
	message: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	),
	onCancel: Config.func(),
	onConfirm: Config.func().required()
};

export default ConfirmDialogModal;