import Component, {Config} from 'metal-jsx';

import Modal from './modal';
import MarkdownContent from './MarkdownContent';
import cheatsheet from '../lib/markdown-cheatsheet';

class MarkdownCheatsheetModal extends Component {
	render() {
		return (
			<div class="markdown-cheatsheet-container">
				<Modal.Header onClose={this.props.hideModal}>
					{Liferay.Language.get('markdown-cheatsheet')}
				</Modal.Header>

				<Modal.Body>
					<MarkdownContent message={cheatsheet} />
				</Modal.Body>
			</div>
		);
	}
}

MarkdownCheatsheetModal.PROPS = {
	hideModal: Config.func()
};

export default MarkdownCheatsheetModal;