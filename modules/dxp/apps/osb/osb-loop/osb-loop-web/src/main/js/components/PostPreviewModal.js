import Component, {Config} from 'metal-jsx';

import Modal from './modal';
import PostPreview from './PostPreview';

class PostPreviewModal extends Component {
	render() {
		const {hideModal} = this.props;

		return (
			<div>
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('preview-post')}
				</Modal.Header>

				<PostPreview {...this.otherProps()} hideModal={hideModal} />
			</div>
		);
	}
}

PostPreviewModal.PROPS = {
	hideModal: Config.func()
};

export default PostPreviewModal;