import Component, {Config} from 'metal-jsx';
import {bindAll, noop} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import ExternalLink from './ExternalLink';
import Icon from './Icon';
import MenuIconLabel from './MenuIconLabel';
import LoopConstants from '../lib/loop-constants';
import MenuList from './menu-list';
import Overlay from './Overlay';
import {addAlert, alertTypes} from '../actions/alerts';
import {alignmentPositions, overlayTypes} from '../actions/overlays';
import {bookmarkPost, destroyPost, followPost} from '../actions/posts';
import {getPostType} from '../lib/util';
import {getPostURL} from '../lib/asset-entry-set-utils';
import {modalTypes, showModal} from '../actions/modals';

import {
	getBookmarkAlertLabel,
	getFollowAlertLabel,
	getPostTypeLabel,
	lang
} from '../lib/lang-util';

class PostMenu extends Component {
	created() {
		bindAll(
			this,
			'handleBookmarkPost_',
			'handleDeletePost_',
			'handleEditPost_',
			'handleFollowPost_',
			'handleSharePost_'
		);
	}

	announcement_() {
		return this.props.type === getPostType('announcement');
	}

	getChildContext() {
		const {onMouseInside, onMouseLeave} = this.props;

		return {
			onOverlayLeave: onMouseLeave,
			onOverlayOver: onMouseInside
		};
	}

	handleBookmarkPost_() {
		const {
			addAlert,
			assetEntrySetId,
			bookmarked,
			bookmarkPost,
			feedId,
			type
		} = this.props;

		bookmarkPost(
			assetEntrySetId,
			!bookmarked,
			feedId
		).then(
			() => addAlert(
				{
					alertType: alertTypes.SUCCESS,
					message: lang(
						getBookmarkAlertLabel(bookmarked, type),
						[<ExternalLink href={getPostURL(assetEntrySetId)} key={0}>{getPostTypeLabel(type)}</ExternalLink>]
					)
				}
			)
		).catch(noop);
	}

	handleDeletePost_() {
		const {
			addAlert,
			assetEntrySetId,
			destroyPost,
			onDelete,
			showModal
		} = this.props;

		const announcement = this.announcement_();

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					message: announcement ? Liferay.Language.get('are-you-sure-you-want-to-delete-this-announcement') : Liferay.Language.get('are-you-sure-you-want-to-delete-this-post'),
					onConfirm: () => {
						destroyPost(assetEntrySetId).then(
							() => addAlert(
								{
									alertType: alertTypes.SUCCESS,
									message: announcement ? Liferay.Language.get('announcement-was-successfully-deleted') : Liferay.Language.get('post-was-successfully-deleted')
								}
							)
						).catch(noop);

						onDelete();
					}
				},
				modalType: modalTypes.CONFIRM_DIALOG
			}
		);
	}

	handleEditPost_() {
		const {assetEntrySetId, feedId, showModal} = this.props;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					feedId,
					id: assetEntrySetId
				},
				modalType: modalTypes.EDIT_POST_MODAL
			}
		);
	}

	handleFollowPost_() {
		const {
			addAlert,
			assetEntrySetId,
			feedId,
			following,
			followPost,
			type
		} = this.props;

		followPost(
			assetEntrySetId,
			!following,
			feedId
		).then(
			() => addAlert(
				{
					alertType: alertTypes.SUCCESS,
					message: lang(
						getFollowAlertLabel(following, type),
						[<ExternalLink href={getPostURL(assetEntrySetId)} key={0}>{getPostTypeLabel(type)}</ExternalLink>]
					)
				}
			)
		).catch(noop);
	}

	handleSharePost_() {
		const {assetEntrySetId, showModal} = this.props;

		showModal(
			{
				modalProps: {url: `${LoopConstants.urls.feed}/${assetEntrySetId}`},
				modalType: modalTypes.SHARE_LINK
			}
		);
	}

	renderFollowPostItem_() {
		const {assetEntrySetId, following} = this.props;

		let followingLanguage = following ? Liferay.Language.get('unfollow-post') : Liferay.Language.get('follow-post');

		if (this.announcement_()) {
			followingLanguage = following ? Liferay.Language.get('unfollow-announcement') : Liferay.Language.get('follow-announcement');
		}

		return (
			<Overlay
				alignment={alignmentPositions.RIGHT}
				alignWithParent={true}
				containerClass="follow-menu-container"
				disabled={!following}
				offset={12}
				overlayProps={{
					classNameId: LoopConstants.classNameIds.posts,
					id: assetEntrySetId,
					showUnfollow: false
				}}
				overlayType={overlayTypes.FOLLOW_MENU}
			>
				<MenuList.Item justifyBetween={true} onClick={this.handleFollowPost_}>
					<MenuIconLabel
						iconClasses="menu-icon"
						label={followingLanguage}
						name="star"
					/>

					{following &&
						<Icon
							elementClasses="follow-item-arrow menu-icon"
							name="arrow-right-short"
							size="small"
						/>
					}
				</MenuList.Item>
			</Overlay>
		);
	}

	render() {
		const {bookmarked, permissionDelete, permissionEdit} = this.props;

		const bookmarkLanguage = bookmarked ? Liferay.Language.get('remove-from-bookmarks') : Liferay.Language.get('bookmark');

		return (
			<MenuList>
				<MenuList.Item onClick={this.handleBookmarkPost_}>
					<MenuIconLabel
						display={bookmarked ? 'warning' : 'default'}
						iconClasses={bookmarked ? '' : 'menu-icon'}
						label={bookmarkLanguage}
						name="bookmark"
					/>
				</MenuList.Item>

				<MenuList.Item onClick={this.handleSharePost_}>
					<MenuIconLabel
						iconClasses="menu-icon"
						label={Liferay.Language.get('share')}
						name="external"
					/>
				</MenuList.Item>

				{this.renderFollowPostItem_()}

				{permissionDelete &&
					<MenuList.Item onClick={this.handleDeletePost_}>
						<MenuIconLabel
							iconClasses="menu-icon"
							label={Liferay.Language.get('delete')}
							name="trash"
						/>
					</MenuList.Item>
				}

				{permissionEdit &&
					<MenuList.Item onClick={this.handleEditPost_}>
						<MenuIconLabel
							iconClasses="menu-icon"
							label={Liferay.Language.get('edit')}
							name="pen"
						/>
					</MenuList.Item>
				}
			</MenuList>
		);
	};
}

const STORE = {
	addAlert: Config.func(),
	assetEntrySetId: Config.number(),
	bookmarked: Config.bool(),
	bookmarkPost: Config.func(),
	destroyPost: Config.func(),
	following: Config.bool(),
	followPost: Config.func(),
	permissionDelete: Config.bool(),
	permissionEdit: Config.bool(),
	showModal: Config.func(),
	type: Config.oneOfType([Config.number(), Config.string()])
};

PostMenu.PROPS = {
	...STORE,
	feedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	onDelete: Config.func().value(noop)
};

export default connect(
	(state, {id}) => {
		const postIMap = state.getIn(['posts', id, 'data'], Map());

		const payloadIMap = postIMap.get('payload', Map());

		return {
			assetEntrySetId: postIMap.get('assetEntrySetId'),
			bookmarked: payloadIMap.get('bookmarked'),
			following: postIMap.get('following'),
			permissionDelete: postIMap.get('permissionDelete'),
			permissionEdit: postIMap.get('permissionEdit'),
			type: postIMap.get('type')
		};
	},
	{
		addAlert,
		bookmarkPost,
		destroyPost,
		followPost,
		showModal
	}
)(PostMenu);