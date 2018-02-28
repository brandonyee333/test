import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Button from './Button';
import Modal from './Modal';

class ButtonModal extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleClick',
			'_handleClose',
			'_handleConfirm'
		);
	}

	render() {
		const {buttons, cancelLabel, confirmLabel, modalData: {body, footer, header}} = this.props;

		let modalWindow;

		const renderedButtons = [];

		for (const button of buttons) {
			const {className = 'modal-button', label} = button;

			renderedButtons.push(<Button className={className} key="btn-primary" label={label} onClick={this._handleClick} />);

			if (this.state.showModal) {
				const modalFooter = [
					<Button className={className} key="btn-action" label={confirmLabel} onClick={this._handleConfirm} />,
					<Button className={className} key="btn-cancel" label={cancelLabel} onclick={this._handleClose} />
				];

				modalWindow = <Modal body={body} close={this._handleClose} footer={footer || modalFooter} header={header} label={label} />;
			}
		}

		return (
			<div>
				{modalWindow}
				<div class="buttons">
					{renderedButtons}
				</div>
			</div>
		);
	}

	_handleClick() {
		this.state.showModal = true;
	}

	_handleClose() {
		this.state.showModal = false;
	}

	_handleConfirm() {
		this.props.action();

		this._handleClose();
	}

	syncRemoteCloseModal(newState) {
		if (newState === true) {
			this._handleClose();
		}
	}
}

ButtonModal.PROPS = {
	action: Config.func(),
	buttons: Config.array(),
	cancelLabel: Config.string().value(Liferay.Language.get('no')),
	confirmLabel: Config.string().value(Liferay.Language.get('yes')),
	modalData: Config.object().value({}),
	remoteCloseModal: Config.bool().value(false)
};

ButtonModal.STATE = {
	showModal: Config.bool().value(false)
};

ButtonModal.SYNC_UPDATES = true;

export default ButtonModal;