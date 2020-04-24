import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import AssetEditor from './AssetEditor';
import Modal from './modal';
import {getPost, getSharedTo} from '../lib/selectors';
import {getPostType} from '../lib/util';
import {hideModal} from '../actions/modals';

class AssetEditModal extends Component {
	render() {
		const {
			feedId,
			hideModal,
			postIMap,
			sharedToIList
		} = this.props;

		const post = postIMap.toJS();

		post.payload.sharedTo = sharedToIList.toJS();

		const announcement = post.type === getPostType('announcement');

		return (
			<div>
				<Modal.Header onClose={hideModal}>
					{announcement ? Liferay.Language.get('edit-announcement') : Liferay.Language.get('edit-post')}
				</Modal.Header>

				<AssetEditor
					feedId={feedId}
					onSubmit={hideModal}
					post={post}
				/>
			</div>
		);
	}
}

const STORE = {
	hideModal: Config.func(),
	postIMap: Config.instanceOf(Map),
	sharedToIList: Config.instanceOf(List)
};

AssetEditModal.PROPS = {
	...STORE,
	feedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	id: Config.number()
};

export default connect(
	(state, ownProps) => (
		{
			...getPost(state, ownProps.id),
			sharedToIList: getSharedTo(state, ownProps.id)
		}
	),
	{
		hideModal
	}
)(AssetEditModal);