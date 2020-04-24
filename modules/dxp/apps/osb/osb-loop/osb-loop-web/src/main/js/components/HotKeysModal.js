import Component, {Config} from 'metal-jsx';

import Modal from './modal';
import HotKey from './HotKey';

class HotKeysModal extends Component {
	render() {
		const {hideModal, shortcuts} = this.props;

		return (
			<div class="hot-keys-modal-container">
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('keyboard-shortcuts')}
				</Modal.Header>

				<Modal.Body>
					<ul class="hot-keys">
						{
							shortcuts.map(
								shortcut => <HotKey key={shortcut.keys} shortcut={shortcut} />
							)
						}
					</ul>
				</Modal.Body>
			</div>
		);
	}
}

HotKeysModal.PROPS = {
	hideModal: Config.func(),
	shortcuts: Config.array().value([])
};

export default HotKeysModal;