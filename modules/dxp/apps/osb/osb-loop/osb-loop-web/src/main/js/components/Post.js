import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, noop} from 'lodash';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import AnnouncementHeader from './AnnouncementHeader';
import BookmarkFlag from './BookmarkFlag';
import Card from './card';
import CommentCreator from './CommentCreator';
import CommentFeed from './CommentFeed';
import Icon from './Icon';
import IconLabel from './IconLabel';
import InlineButton from './InlineButton';
import LikeCounter from './LikeCounter';
import LoopConstants from '../lib/loop-constants';
import Overlay from './Overlay';
import PostContent from './PostContent';
import PostHeader from './PostHeader';
import PrivatePostBar from './PrivatePostBar';
import {bookmarkPost, createPost, likePost} from '../actions/posts';
import {clearMessageInfoTruncated, getMessage} from '../actions/feeds';
import {getPostType, toJS} from '../lib/util';
import {modalTypes, showModal} from '../actions/modals';
import {overlayTypes} from '../actions/overlays';

class Post extends Component {
	created() {
		bindAll(
			this,
			'handleBookmarkClick_',
			'handleCommentCreatorFocus_',
			'handleGetMessage_',
			'handleLikeClick_',
			'handleShowLikedModalClick_'
		);
	}

	detached() {
		const {clearMessageInfoTruncated, id, messageInfoIMap} = this.props;

		if (messageInfoIMap) {
			clearMessageInfoTruncated(id);
		}
	}

	handleBookmarkClick_() {
		const {
			bookmarkPost,
			feedId,
			id,
			postIMap
		} = this.props;

		bookmarkPost(id, !postIMap.getIn(['payload', 'bookmarked']), feedId);
	}

	handleCommentCreatorFocus_() {
		this.components.commentCreator.components.child.focus();
	}

	handleGetMessage_() {
		const {
			feedId,
			getMessage,
			id,
			messageInfoIMap
		} = this.props;

		getMessage(feedId, id, !messageInfoIMap.get('truncated', false));
	}

	handleLikeClick_() {
		const {id, likePost, postIMap} = this.props;

		const liked = postIMap.getIn(['payload', 'likedParticipants', 'liked'], false);

		likePost(id, !liked);
	}

	handleShowLikedModalClick_() {
		const {id, showModal} = this.props;

		showModal(
			{
				modalProps: {id},
				modalType: modalTypes.LIKED_PARTICIPANTS
			}
		);
	}

	render() {
		const {
			childFeedId,
			createPost,
			creatorIMap,
			feedId,
			focusedId,
			id,
			messageInfoIMap,
			onDelete,
			postIMap
		} = this.props;

		const creator = creatorIMap.toJS();

		const assetEntrySetLikesCount = postIMap.get('assetEntrySetLikesCount');
		const childAssetEntrySetsCount = postIMap.get('childAssetEntrySetsCount');
		const createTime = postIMap.get('createTime');
		const payloadIMap = postIMap.get('payload');
		const privateAssetEntrySet = postIMap.get('privateAssetEntrySet');

		const bookmarked = payloadIMap.get('bookmarked');
		const contentModifiedTime = payloadIMap.get('contentModifiedTime');
		const liked = payloadIMap.getIn(['likedParticipants', 'liked'], false);
		const location = payloadIMap.getIn(['geolocation', 'locationName']);

		let participantsIList = payloadIMap.getIn(['likedParticipants', 'participants'], List());

		if (liked) {
			participantsIList = participantsIList.push(LoopConstants.currentPerson.entityClassPK);
		}

		const announcement = postIMap.get('type') === getPostType('announcement');

		const classNames = getCN(
			'post-container',
			{
				bookmarked,
				'private-post': privateAssetEntrySet
			}
		);

		return (
			<Card elementClasses={classNames} key={`${id}PostCard`}>
				{privateAssetEntrySet &&
					<PrivatePostBar announcement={announcement} />
				}

				{announcement &&
					<AnnouncementHeader
						contentModifiedTime={contentModifiedTime}
						createTime={createTime}
						creator={creator}
						id={id}
						location={location}
						stickyTime={postIMap.get('stickyTime')}
						title={postIMap.get('title')}
					/>
				}

				{!announcement &&
					<PostHeader
						contentModifiedTime={contentModifiedTime}
						createTime={createTime}
						creator={creator}
						displayURL={postIMap.get('displayURL')}
						id={id}
						location={location}
					/>
				}

				<BookmarkFlag bookmarked={bookmarked} onClick={this.handleBookmarkClick_} />

				<Overlay
					containerClass="post-menu-container"
					overlayProps={{
						feedId,
						id,
						onDelete
					}}
					overlayType={overlayTypes.POST_MENU}
				>
					<Icon name="ellipsis" />
				</Overlay>

				<PostContent
					id={id}
					imageData={toJS(payloadIMap, 'imageData')}
					linkData={toJS(payloadIMap, 'linkData') || null}
					message={payloadIMap.get('message')}
					messageInfoIMap={messageInfoIMap}
					onSeeMore={this.handleGetMessage_}
					truncated={payloadIMap.get('truncated')}
				/>

				<div class="card-actions">
					<InlineButton elementClasses="card-action" onClick={this.handleLikeClick_}>
						<IconLabel
							elementClasses={liked ? 'liked' : ''}
							iconClasses={getCN('heart', {liked})}
							label={liked ? Liferay.Language.get('liked') : Liferay.Language.get('like')}
							name="heart-full"
							spacing="small"
						/>
					</InlineButton>

					<a class="card-action" href="javascript:;" onClick={this.handleCommentCreatorFocus_}>
						<IconLabel
							label={Liferay.Language.get('comment')}
							name="balloon-topic"
							spacing="small"
						/>
					</a>
				</div>

				{assetEntrySetLikesCount !== 0 &&
					<LikeCounter
						count={assetEntrySetLikesCount}
						onOtherClick={this.handleShowLikedModalClick_}
						participantsIList={participantsIList.take(LoopConstants.likedParticipantsLimit)}
					/>
				}

				<div class="comments" key="comments">
					{childAssetEntrySetsCount !== 0 &&
						<CommentFeed
							commentFeedId={childFeedId}
							commentProps={{focusedId}}
							parentId={id}
							total={childAssetEntrySetsCount}
						/>
					}

					<CommentCreator
						commentFeedId={childFeedId}
						key="commentCreator"
						parentId={id}
						privateAssetEntrySet={privateAssetEntrySet}
						ref="commentCreator"
						submitMethod={createPost}
					/>
				</div>
			</Card>
		);
	}
}

const STORE = {
	bookmarkPost: Config.func(),
	clearMessageInfoTruncated: Config.func(),
	createPost: Config.func(),
	getMessage: Config.func(),
	likePost: Config.func(),
	showModal: Config.func()
};

Post.PROPS = {
	...STORE,
	childFeedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	creatorIMap: Config.instanceOf(Map),
	feedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	focusedId: Config.number(),
	id: Config.number(),
	messageInfoIMap: Config.instanceOf(Map).value(Map()),
	onDelete: Config.func().value(noop),
	postIMap: Config.instanceOf(Map)
};

export default connect(
	null,
	{
		bookmarkPost,
		clearMessageInfoTruncated,
		createPost,
		getMessage,
		likePost,
		showModal
	}
)(Post);