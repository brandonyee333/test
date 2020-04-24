import Component, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';
import {connect} from 'metal-redux';
import {Map, OrderedMap} from 'immutable';

import Comment from './Comment';
import LoopConstants from '../lib/loop-constants';
import Spinner from './Spinner';
import {fetchComments} from '../actions/feeds';
import {getPost} from '../lib/selectors';
import {lang} from '../lib/lang-util';

export class CommentFeed extends Component {
	created() {
		this.loadMoreComments_ = this.loadMoreComments_.bind(this);
	}

	loadMoreComments_(event) {
		const {ctrlKey, metaKey, shiftKey} = event;

		if (!ctrlKey && !metaKey && !shiftKey) {
			event.preventDefault();

			event.stopImmediatePropagation();

			const {
				commentFeedId,
				feedCommentsIOMap,
				fetchComments,
				itemsPerPage,
				parentId
			} = this.props;

			let paginationTime = Date.now();

			if (feedCommentsIOMap.size > 0) {
				const lastCommentIMap = feedCommentsIOMap.first().get('post');

				paginationTime = lastCommentIMap.get('createTime', paginationTime) - 1;
			}

			fetchComments(
				{
					commentFeedId,
					itemsPerPage,
					parentId,
					sessionTime: paginationTime
				}
			);
		}
	}

	renderComment_(feedCommentIMap) {
		const {commentFeedId, commentProps} = this.props;

		const creatorIMap = feedCommentIMap.get('creator', Map());
		const postIMap = feedCommentIMap.get('post', Map());

		const id = postIMap.get('assetEntrySetId');

		return (
			<Comment
				{...commentProps}
				commentFeedId={commentFeedId}
				creatorIMap={creatorIMap}
				id={id}
				key={id}
				messageInfoIMap={feedCommentIMap.get('messageInfo')}
				postIMap={postIMap}
			/>
		);
	}

	render() {
		const {
			feedCommentsIOMap,
			feedCommentsLoading,
			parentId,
			showPreviousEnabled,
			total
		} = this.props;

		const displayedCommentsCount = feedCommentsIOMap.size;

		const remainingCommentsCount = total - displayedCommentsCount;

		return (
			<div class="comment-feed-container">
				{showPreviousEnabled && remainingCommentsCount > 0 &&
					<div class="previous-comments">
						<a href={`${LoopConstants.urls.feed}/${parentId}`} onClick={this.loadMoreComments_}>
							{Liferay.Language.get('show-previous-comments')}
						</a>

						<span class="secondary-info">
							{lang(Liferay.Language.get('x-of-x'), [displayedCommentsCount, total])}
						</span>
					</div>
				}

				{!feedCommentsIOMap.size && feedCommentsLoading &&
					<Spinner />
				}

				<Transition name="transition-fade-out-slide-up">
					{
						feedCommentsIOMap.valueSeq().map(
							feedCommentIMap => this.renderComment_(feedCommentIMap)
						)
					}
				</Transition>
			</div>
		);
	};
}

const STORE = {
	feedCommentsIOMap: Config.instanceOf(OrderedMap),
	feedCommentsLoading: Config.bool(),
	fetchComments: Config.func()
};

CommentFeed.PROPS = {
	...STORE,
	commentFeedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	commentProps: Config.object(),
	itemsPerPage: Config.number().value(7),
	showPreviousEnabled: Config.bool().value(true),
	total: Config.number()
};

export default connect(
	(state, ownProps) => {
		const feedCommentsIOMap = state.getIn(['feeds', ownProps.commentFeedId, 'posts'], OrderedMap()).map(
			feedCommentIMap => {
				const {creatorIMap, messageInfoIMap, postIMap} = getPost(state, feedCommentIMap.get('id'));

				return feedCommentIMap.set('creator', creatorIMap).set('messageInfo', messageInfoIMap).set('post', postIMap);
			}
		);

		return {
			feedCommentsIOMap,
			feedCommentsLoading: state.getIn(['feeds', ownProps.commentFeedId, 'loading'], true)
		};
	},
	{
		fetchComments
	}
)(CommentFeed);