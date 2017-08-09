import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Button from './Button';
import Modal from './Modal';

class ButtonModal extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleClick',
			'handleClose',
			'handleConfirm'
		);
	}

	render() {
		const {buttons, cancelLabel, confirmLabel, modalData: {body, footer, header}} = this.props;

		let modalWindow;

		const renderedButtons = [];

		for (const button of buttons) {
			const {className = 'modal-button', label} = button;

			renderedButtons.push(<Button className={className} key="btn-primary" label={label} onClick={this.handleClick} />);

			if (this.state.showModal) {
				const modalFooter = [
					<Button className={className} key="btn-action" label={confirmLabel} onClick={this.handleConfirm} />,
					<Button className={className} key="btn-cancel" label={cancelLabel} onclick={this.handleClose} />
				];

				modalWindow = <Modal body={body} close={this.handleClose} footer={footer || modalFooter} header={header} label={label} />;
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

	handleClick() {
		this.state.showModal = true;
	}

	handleClose() {
		this.state.showModal = false;
	}

	handleConfirm() {
		this.props.action();

		this.handleClose();
	}

	syncRemoteCloseModal(newState) {
		if (newState == true) {
			this.handleClose();
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