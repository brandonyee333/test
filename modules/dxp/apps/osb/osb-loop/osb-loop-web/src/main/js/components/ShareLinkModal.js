import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, debounce} from 'lodash';

import Button from './Button';
import Modal from './modal';

class ShareLinkModal extends Component {
	created() {
		bindAll(
			this,
			'onClick_',
			'resetMessage_'
		);

		this.resetMessage_ = debounce(this.resetMessage_, 1000);
	}

	onClick_() {
		this.resetMessage_.cancel();

		this.setState(
			{
				success_: true
			},
			this.resetMessage_
		);
	}

	resetMessage_() {
		if (!this.isDisposed()) {
			this.state.success_ = false;
		}
	}

	render() {
		const {success_} = this.state;
		const {header, hideModal, url} = this.props;

		const classes = getCN(
			'input-append',
			{
				success: success_
			}
		);

		return (
			<div class="share-link-container">
				<Modal.Header onClose={hideModal}>
					{header}
				</Modal.Header>

				<Modal.Body>
					<p>
						{Liferay.Language.get('copy-the-link-below')}
					</p>

					<div class={classes} >
						<input class="input" readOnly type="text" value={url} />

						<Button
							data-clipboard-text={url}
							data-tooltip-response={Liferay.Language.get('copied')}
							onClick={this.onClick_}
							role="primary"
							title={Liferay.Language.get('click-to-copy')}
						>
							{success_ ? Liferay.Language.get('copied') : Liferay.Language.get('copy')}
						</Button>
					</div>
				</Modal.Body>
			</div>
		);
	}
}

ShareLinkModal.PROPS = {
	header: Config.string().value(Liferay.Language.get('share-this-post')),
	hideModal: Config.func(),
	url: Config.string()
};

ShareLinkModal.STATE = {
	success_: Config.value(false)
};

export default ShareLinkModal;