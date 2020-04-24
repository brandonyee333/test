import Component, {Config} from 'metal-jsx';
import {bindAll, noop} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import ExternalLink from './ExternalLink';
import UndoCard from './UndoCard';
import {addAlert, alertTypes} from '../actions/alerts';
import {bookmarkPost, followPost} from '../actions/posts';

import {
	getBookmarkAlertLabel,
	getFollowAlertLabel,
	getPostTypeLabel,
	lang
} from '../lib/lang-util';

import {getPostURL} from '../lib/asset-entry-set-utils';
import {removePost} from '../actions/feeds';

class PostUndoCard extends Component {
	created() {
		bindAll(
			this,
			'handleClose_',
			'handleUndo_'
		);
	}

	handleClose_() {
		const {feedId, postIMap, removePost} = this.props;

		removePost(feedId, postIMap.get('assetEntrySetId'));
	}

	handleUndo_() {
		const {
			addAlert,
			bookmarkPost,
			feedId,
			followPost,
			postIMap,
			removedBy
		} = this.props;

		const assetEntrySetId = postIMap.get('assetEntrySetId');

		const postType = postIMap.get('type');

		let getAlertLabel = getFollowAlertLabel;
		let postAction = followPost;

		if (removedBy === 'bookmark') {
			getAlertLabel = getBookmarkAlertLabel;
			postAction = bookmarkPost;
		}

		postAction(assetEntrySetId, true, feedId).then(
			() => addAlert(
				{
					alertType: alertTypes.SUCCESS,
					message: lang(
						getAlertLabel(false, postType),
						[<ExternalLink href={getPostURL(assetEntrySetId)} key={0}>{getPostTypeLabel(postType)}</ExternalLink>]
					)
				}
			)
		).catch(noop);
	}

	render() {
		const {postIMap, removedBy} = this.props;

		const assetEntrySetId = postIMap.get('assetEntrySetId');

		const postType = postIMap.get('type');

		let getAlertLabel = getFollowAlertLabel;

		if (removedBy === 'bookmark') {
			getAlertLabel = getBookmarkAlertLabel;
		}

		return (
			<UndoCard
				message={
					lang(
						getAlertLabel(true, postType),
						[<ExternalLink href={getPostURL(assetEntrySetId)} key={0}>{getPostTypeLabel(postType)}</ExternalLink>]
					)
				}
				onClose={this.handleClose_}
				onUndo={this.handleUndo_}
			/>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	bookmarkPost: Config.func(),
	followPost: Config.func(),
	removePost: Config.func()
};

PostUndoCard.PROPS = {
	...STORE,
	feedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	postIMap: Config.instanceOf(Map),
	removedBy: Config.oneOf(['bookmark', 'follow'])
};

export default connect(
	null,
	{
		addAlert,
		bookmarkPost,
		followPost,
		removePost
	}
)(PostUndoCard);