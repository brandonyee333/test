import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {isUndefined} from 'lodash';
import {Map, OrderedMap} from 'immutable';

import InfiniteScroll from './InfiniteScroll';
import LoadingCard from './LoadingCard';
import NoResultsDisplay from './NoResultsDisplay';
import Post from './Post';
import PostUndoCard from './PostUndoCard';
import {fetchPosts, filterRemovedPosts} from '../actions/feeds';
import {getPost} from '../lib/selectors';
import {streamIdToKeys} from '../lib/util';

export class Feed extends Component {
	created() {
		this.fetch_ = this.fetch_.bind(this);
	}

	detached() {
		const {filterRemovedPosts, id} = this.props;

		filterRemovedPosts(id);
	}

	fetch_() {
		const {
			feedPostsIOMap,
			fetchPosts,
			id,
			itemsPerPage
		} = this.props;

		let paginationTime = Date.now();

		const lastFeedPostIMap = feedPostsIOMap.last() || OrderedMap();

		const lastPostIMap = lastFeedPostIMap.get('post');

		if (lastPostIMap) {
			const {classPK} = streamIdToKeys(id);

			if (classPK) {
				paginationTime = lastPostIMap.get('createTime');
			}
			else {
				paginationTime = lastPostIMap.get('modifiedTime');
			}
		}

		return fetchPosts(
			{
				id,
				itemsPerPage,
				sessionTime: paginationTime - 1
			}
		).then(
			({data}) => {
				this.state.hasMoreResults_ = data.length > 0;
			}
		);
	}

	renderPost_(feedPostIMap) {
		const {id, itemsPerPage} = this.props;

		const postId = feedPostIMap.get('id');

		return (
			<Post
				childFeedId={feedPostIMap.get('childFeedId')}
				creatorIMap={feedPostIMap.get('creator')}
				feedId={id}
				id={postId}
				itemsPerPage={itemsPerPage}
				key={`${postId}Post`}
				messageInfoIMap={feedPostIMap.get('messageInfo', Map())}
				postIMap={feedPostIMap.get('post')}
			/>
		);
	}

	renderUndoCard_(feedPostIMap) {
		return (
			<PostUndoCard
				feedId={this.props.id}
				key={`${feedPostIMap.get('id')}Undo`}
				postIMap={feedPostIMap.get('post')}
				removedBy={feedPostIMap.get('removedBy')}
			/>
		);
	}

	syncId() {
		this.state.hasMoreResults_ = true;
	}

	syncLoading(newVal) {
		if (isUndefined(newVal) && !this.state.hasMoreResults_) {
			this.fetch_();
		}
	}

	render() {
		const {
			feedPostsIOMap,
			loading = true,
			noResultsMessage,
			noResultsTitle
		} = this.props;

		return (
			<InfiniteScroll
				elementClasses="feed-container"
				hasMoreResults={this.state.hasMoreResults_}
				onScrollEnd={this.fetch_}
				scrollOffset={2000}
			>
				{
					feedPostsIOMap.valueSeq().map(
						feedPostIMap => {
							return feedPostIMap.get('removedBy') ? this.renderUndoCard_(feedPostIMap) : this.renderPost_(feedPostIMap);
						}
					)
				}

				{!loading && !feedPostsIOMap.size &&
					<NoResultsDisplay key="noResultsDisplay" message={noResultsMessage} title={noResultsTitle} />
				}

				{loading &&
					<LoadingCard key="loadingCard" />
				}
			</InfiniteScroll>
		);
	}
}

const STORE = {
	feedPostsIOMap: Config.instanceOf(OrderedMap),
	fetchPosts: Config.func(),
	filterRemovedPosts: Config.func(),
	loading: Config.bool()
};

Feed.PROPS = {
	...STORE,
	id: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	itemsPerPage: Config.number(),
	noResultsMessage: Config.any().value(Liferay.Language.get('there-are-no-posts-yet')),
	noResultsTitle: Config.any(),
	privateAssetEntrySet: Config.bool(),
	type: Config.number()
};

Feed.STATE = {
	hasMoreResults_: Config.bool().value(true)
};

export default connect(
	(state, ownProps) => {
		const currentFeed = state.getIn(['feeds', ownProps.id], Map());

		const feedPostsIOMap = currentFeed.getIn(['posts'], OrderedMap()).map(
			feedPostIMap => {
				const {creatorIMap, messageInfoIMap, postIMap} = getPost(state, feedPostIMap.get('id'));

				return feedPostIMap.merge(
					{
						creator: creatorIMap,
						messageInfo: messageInfoIMap,
						post: postIMap
					}
				);
			}
		);

		return {
			feedPostsIOMap,
			loading: currentFeed.get('loading')
		};
	},
	{
		fetchPosts,
		filterRemovedPosts
	}
)(Feed);