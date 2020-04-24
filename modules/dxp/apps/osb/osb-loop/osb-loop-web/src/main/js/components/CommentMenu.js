import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {noop} from 'lodash';

import MenuList from './menu-list';
import {destroyPost} from '../actions/posts';
import {modalTypes, showModal} from '../actions/modals';

class CommentMenu extends Component {
	created() {
		this.handleDeletePost_ = this.handleDeletePost_.bind(this);
	}

	getChildContext() {
		const {onMouseInside, onMouseLeave} = this.props;

		return {
			onOverlayLeave: onMouseLeave,
			onOverlayOver: onMouseInside
		};
	}

	handleDeletePost_() {
		const {destroyPost, id, showModal} = this.props;

		showModal(
			{
				modalProps: {
					message: Liferay.Language.get('are-you-sure-you-want-to-delete-this-post'),
					onConfirm: destroyPost.bind(this.props, id)
				},
				modalType: modalTypes.CONFIRM_DIALOG
			}
		);
	}

	render() {
		const {onEdit, permissionDelete, permissionEdit} = this.props;

		return (
			<MenuList>
				{permissionEdit &&
					<MenuList.Item onClick={onEdit}>
						{Liferay.Language.get('edit')}
					</MenuList.Item>
				}

				{permissionDelete &&
					<MenuList.Item onClick={this.handleDeletePost_}>
						{Liferay.Language.get('delete')}
					</MenuList.Item>
				}
			</MenuList>
		);
	}
}

const STORE = {
	destroyPost: Config.func(),
	showModal: Config.func()
};

CommentMenu.PROPS = {
	...STORE,
	id: Config.number(),
	onEdit: Config.func().value(noop),
	permissionDelete: Config.bool(),
	permissionEdit: Config.bool()
};

export default connect(
	null,
	{
		destroyPost,
		showModal
	}
)(CommentMenu);