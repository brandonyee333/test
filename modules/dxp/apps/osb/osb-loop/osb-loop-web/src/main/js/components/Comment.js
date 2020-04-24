import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, isUndefined} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Avatar from './Avatar';
import CommentCreator from './CommentCreator';
import CommentFeed from './CommentFeed';
import CommentLikeCounter from './CommentLikeCounter';
import EntityLink from './EntityLink';
import Icon from './Icon';
import ImageViewer from './ImageViewer';
import LoopConstants from '../lib/loop-constants';
import MarkdownWithOverlay from './MarkdownWithOverlay';
import Overlay from './Overlay';
import SeeMore from './SeeMore';
import {createPost, likePost, updatePost} from '../actions/posts';
import {clearMessageInfoTruncated, fetchComments, getMessage} from '../actions/feeds';
import {formatTime, scrollIntoView} from '../lib/util';
import {getPluralMessage} from '../lib/lang-util';
import {modalTypes, showModal} from '../actions/modals';
import {overlayTypes} from '../actions/overlays';

class Comment extends Component {
	created() {
		this.handleGetMessage_ = this.handleGetMessage_.bind(this);

		const {focusedId, postIMap} = this.props;

		if (focusedId && postIMap.get('childAssetEntrySets', []).indexOf(focusedId) !== -1) {
			this.state.showReplies_ = true;
		}

		bindAll(
			this,
			'endEdit_',
			'fetchReplies_',
			'focusReplyCreator_',
			'handleAutoMention_',
			'handleClickToReply_',
			'handleLikePost_',
			'handleSubmit_',
			'onEdit_',
			'showLikedModal_',
			'toggleReplies_'
		);
	}

	attached() {
		const {focusedId, id} = this.props;

		if (focusedId === id) {
			scrollIntoView(this.element);
		}
	}

	detached() {
		const {clearMessageInfoTruncated, id, messageInfoIMap} = this.props;

		if (messageInfoIMap) {
			clearMessageInfoTruncated(id);
		}
	}

	endEdit_() {
		this.state.editing_ = false;
	}

	fetchReplies_() {
		const {commentFeedId, fetchComments, id} = this.props;

		fetchComments(
			{
				commentFeedId: `${commentFeedId}-${id}`,
				itemsPerPage: -1,
				parentId: id,
				replaceCommentFeed: true,
				sessionTime: Date.now(),
				start: -1
			}
		);
	}

	focusReplyCreator_() {
		this.refs.replyCreator.refs.child.focus();
	}

	getRepliesMessage(childAssetEntrySetsCount) {
		const {replySize} = this.props;

		let replyMessage = getPluralMessage(Liferay.Language.get('show-x-reply'), Liferay.Language.get('show-x-replies'), replySize || childAssetEntrySetsCount);

		if (this.state.showReplies_ && replySize) {
			replyMessage = getPluralMessage(Liferay.Language.get('hide-reply'), Liferay.Language.get('hide-replies'), childAssetEntrySetsCount);

			if (childAssetEntrySetsCount > replySize) {
				childAssetEntrySetsCount -= replySize;

				replyMessage = getPluralMessage(Liferay.Language.get('show-x-more-reply'), Liferay.Language.get('show-x-more-replies'), childAssetEntrySetsCount);
			}
		}

		return replyMessage;
	}

	handleAutoMention_(creatorIMap) {
		this.focusReplyCreator_();

		this.refs.replyCreator.refs.child.insertMention(creatorIMap);
	}

	handleClickToReply_() {
		if (this.props.onReply) {
			this.handleReplyToReply_();
		}
		else {
			this.handleReplyToComment_();
		}
	}

	handleGetMessage_() {
		const {
			commentFeedId,
			getMessage,
			id,
			messageInfoIMap
		} = this.props;

		getMessage(commentFeedId, id, !messageInfoIMap.get('truncated', false));
	}

	handleLikePost_() {
		const {id, likePost} = this.props;

		likePost(id, !this.liked_());
	}

	handleReplyToComment_() {
		if (!this.state.showReplies_) {
			this.fetchReplies_();

			this.setState(
				{showReplies_: true},
				this.focusReplyCreator_
			);
		}
		else {
			this.focusReplyCreator_();
		}
	}

	handleReplyToReply_() {
		const {creatorIMap, onReply} = this.props;

		onReply(creatorIMap);
	}

	handleSubmit_(postData) {
		return this.props.updatePost(postData).then(this.endEdit_);
	}

	liked_() {
		return this.props.postIMap.getIn(['payload', 'likedParticipants', 'liked'], false);
	}

	onEdit_() {
		this.state.editing_ = true;
	}

	showLikedModal_() {
		const {id, showModal} = this.props;

		showModal(
			{
				modalProps: {id},
				modalType: modalTypes.LIKED_PARTICIPANTS
			}
		);
	}

	toggleReplies_() {
		const {postIMap, replySize} = this.props;

		let retVal = false;

		if (!this.state.showReplies_ || postIMap.get('childAssetEntrySetsCount') > replySize) {
			this.fetchReplies_();

			retVal = true;
		}

		this.state.showReplies_ = retVal;
	}

	render() {
		const {
			props: {
				commentFeedId,
				createPost,
				creatorIMap,
				focusedId,
				id,
				messageInfoIMap,
				onReply,
				postIMap,
				replySize
			},
			state: {editing_, showReplies_}
		} = this;

		if (showReplies_ && !this._alwaysRenderComments) {
			this._alwaysRenderComments = true;
		}

		const creator = creatorIMap.toJS();

		const childAssetEntrySetsCount = postIMap.get('childAssetEntrySetsCount');
		const createTime = postIMap.get('createTime');
		const parentAssetEntrySetId = postIMap.get('parentAssetEntrySetId');
		const payloadIMap = postIMap.get('payload');
		const permissionDelete = postIMap.get('permissionDelete');
		const permissionEdit = postIMap.get('permissionEdit');
		const privateAssetEntrySet = postIMap.get('privateAssetEntrySet');

		const contentModifiedTime = payloadIMap.get('contentModifiedTime');
		const imageDataIList = payloadIMap.get('imageData');
		const truncated = payloadIMap.get('truncated');

		const secondaryTruncation = messageInfoIMap.get('truncated');

		const time = contentModifiedTime ? contentModifiedTime : createTime;

		const href = `${LoopConstants.urls.feed}/${id}`;

		const childCommentFeedId = `${commentFeedId}-${id}`;

		return (
			<div class={`comment-container ${focusedId === id ? 'focused' : ''}`}>
				{editing_ &&
					<CommentCreator
						elementClasses="edit"
						id={id}
						onCancelClick={this.endEdit_}
						onSubmitCallback={this.endEdit_}
						parentId={parentAssetEntrySetId}
						payloadIMap={payloadIMap}
						privateAssetEntrySet={privateAssetEntrySet}
						submitMethod={this.handleSubmit_}
					/>
				}

				{!editing_ &&
					<div class="view">
						<Avatar entity={creator} size="smallest" summary={true} />

						<div class="content">
							<EntityLink entity={creator} summary={true} />

							<MarkdownWithOverlay id={id} inline={true} message={isUndefined(secondaryTruncation) ? payloadIMap.get('message') : messageInfoIMap.get('message')} />

							{secondaryTruncation !== false && truncated &&
								<SeeMore
									href={href}
									messageInfoIMap={messageInfoIMap}
									onSeeMore={this.handleGetMessage_}
									truncated={truncated}
								/>
							}

							{imageDataIList && !!imageDataIList.size &&
								<ImageViewer
									backgroundBlur={false}
									items={imageDataIList.toJS()}
									small={true}
								/>
							}

							<div class="meta-data">
								<a class="secondary-info" href={href}>
									<span data-tooltip title={formatTime(time, true)}>{formatTime(time)}</span>

									{!!contentModifiedTime &&
										<span class="edited">
											{` - ${Liferay.Language.get('edited')}`}
										</span>
									}
								</a>

								<span class="reply-to" onClick={this.handleClickToReply_}>
									{Liferay.Language.get('reply')}
								</span>

								<CommentLikeCounter
									count={postIMap.get('assetEntrySetLikesCount')}
									liked={this.liked_()}
									onClick={this.handleLikePost_}
									onCountClick={this.showLikedModal_}
								/>
							</div>

							{(permissionEdit || permissionDelete) &&
								<Overlay
									overlayProps={{
										id,
										onEdit: this.onEdit_,
										permissionDelete,
										permissionEdit
									}}
									overlayType={overlayTypes.COMMENT_MENU}
								>
									<Icon name="pen" />
								</Overlay>
							}
						</div>
					</div>
				}

				<div class="replies">
					{childAssetEntrySetsCount !== 0 && !onReply &&
						<span class="toggle-replies" onClick={this.toggleReplies_}>
							{this.getRepliesMessage(childAssetEntrySetsCount)}

							<Icon name={showReplies_ && childAssetEntrySetsCount === replySize ? 'arrow-up-short' : 'arrow-down-short'} size="small" />
						</span>
					}

					{(showReplies_ || this._alwaysRenderComments) &&
						<div
							class={
								getCN(
									'reply-feed',
									{
										hide: !showReplies_,
										'show-line': !!childAssetEntrySetsCount
									}
								)
							}
						>
							<CommentFeed
								commentFeedId={childCommentFeedId}
								commentProps={{
									focusedId,
									onReply: this.handleAutoMention_
								}}
								parentId={id}
								showPreviousEnabled={false}
								total={childAssetEntrySetsCount}
							/>

							<CommentCreator
								commentFeedId={childCommentFeedId}
								parentId={id}
								placeholder={Liferay.Language.get('write-a-reply')}
								privateAssetEntrySet={privateAssetEntrySet}
								ref="replyCreator"
								submitMethod={createPost}
							/>
						</div>
					}
				</div>
			</div>
		);
	}
}

const STORE = {
	clearMessageInfoTruncated: Config.func(),
	createPost: Config.func(),
	fetchComments: Config.func(),
	getMessage: Config.func(),
	likePost: Config.func(),
	replySize: Config.number(),
	showModal: Config.func(),
	updatePost: Config.func()
};

Comment.PROPS = {
	...STORE,
	commentFeedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	creatorIMap: Config.instanceOf(Map),
	focusedId: Config.number(),
	id: Config.number(),
	messageInfoIMap: Config.instanceOf(Map).value(Map()),
	onReply: Config.func(),
	postIMap: Config.instanceOf(Map)
};

Comment.STATE = {
	editing_: Config.value(false),
	showReplies_: Config.value(false)
};

export default connect(
	(state, {commentFeedId, id}) => (
		{
			replySize: state.getIn(['feeds', `${commentFeedId}-${id}`, 'posts'], Map()).size
		}
	),
	{
		clearMessageInfoTruncated,
		createPost,
		fetchComments,
		getMessage,
		likePost,
		showModal,
		updatePost
	}
)(Comment);