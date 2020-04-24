import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {isUndefined} from 'lodash';
import {Map, OrderedMap} from 'immutable';

import Card from '../../components/card';
import InfiniteScroll from '../../components/InfiniteScroll';
import LoadingCard from '../../components/LoadingCard';
import NoResultsDisplay from '../../components/NoResultsDisplay';
import PageSearchResultItem from '../../components/PageSearchResultItem';
import Post from '../../components/Post';
import {getAssetEntrySet} from '../../lib/selectors';
import {lang} from '../../lib/lang-util';
import {searchPages} from '../../actions/pages';
import {searchPosts} from '../../actions/posts';
import {PAGES, POSTS, ROUTER_URLS_MAP} from '../../lib/router-constants';
import {setParamValue} from '../../lib/router-util';

const {SEARCH_URL} = ROUTER_URLS_MAP;

export class SearchFeed extends Component {
	created() {
		this.fetch_ = this.fetch_.bind(this);
	}

	fetch_() {
		const {
			assetEntrySetType,
			childAssetEntrySetsLimit,
			feedAssetEntrySetsIOMap,
			itemsPerPage,
			keywords,
			likedParticipantsLimit,
			searchPages,
			searchPosts,
			sessionTime
		} = this.props;

		const searchFn = assetEntrySetType === PAGES ? searchPages : searchPosts;

		const start = feedAssetEntrySetsIOMap.size;

		return searchFn(
			{
				childAssetEntrySetsLimit,
				end: start + itemsPerPage,
				keywords,
				likedParticipantsLimit,
				sessionTime,
				start
			}
		).then(
			({data}) => {
				this.state.hasMoreResults_ = data.results.length > 0;
			}
		);
	}

	renderContent_(children) {
		const {
			props: {infiniteScroll},
			state: {hasMoreResults_}
		} = this;

		let content;

		if (infiniteScroll) {
			content = (
				<InfiniteScroll
					elementClasses="search-feed-container"
					hasMoreResults={hasMoreResults_}
					onScrollEnd={this.fetch_}
					scrollOffset={2000}
				>
					{children}
				</InfiniteScroll>
			);
		}
		else {
			content = (
				<div class="search-feed-container">
					{children}
				</div>
			);
		}

		return content;
	}

	syncLoading(newVal) {
		if (isUndefined(newVal)) {
			this.fetch_();
		}
	}

	render() {
		const {
			assetEntrySetType,
			feedAssetEntrySetsIOMap,
			infiniteScroll,
			itemsPerPage,
			keywords,
			loading = true,
			total
		} = this.props;

		const feedAssetEntrySetsCount = feedAssetEntrySetsIOMap.size;

		const page = assetEntrySetType === PAGES;

		return this.renderContent_(
			<div>
				{
					feedAssetEntrySetsIOMap.valueSeq().map(
						feedAssetEntrySetIMap => {
							const creatorIMap = feedAssetEntrySetIMap.get('creatorIMap');
							const postId = feedAssetEntrySetIMap.get('id');

							let retVal;

							if (page) {
								retVal = <PageSearchResultItem creatorIMap={creatorIMap} ownerIMap={feedAssetEntrySetIMap.get('ownerIMap')} pageIMap={feedAssetEntrySetIMap.get('itemIMap')} />;
							}
							else {
								retVal = (
									<Post
										childFeedId={feedAssetEntrySetIMap.get('childFeedId')}
										creatorIMap={creatorIMap}
										feedId={keywords}
										id={postId}
										itemsPerPage={itemsPerPage}
										key={postId}
										messageInfoIMap={feedAssetEntrySetIMap.get('messageInfoIMap')}
										postIMap={feedAssetEntrySetIMap.get('postIMap')}
									/>
								);
							}

							return retVal;
						}
					).toJS()
				}

				{!loading && !infiniteScroll && total > feedAssetEntrySetsCount &&
					<Card>
						<a class="see-more-results" href={setParamValue(`${SEARCH_URL}/${POSTS}`, 'q', encodeURI(keywords))}>
							{Liferay.Language.get('see-all')}
						</a>
					</Card>
				}

				{!feedAssetEntrySetsCount && !loading &&
					<NoResultsDisplay
						message={lang(Liferay.Language.get('we-could-not-find-any-x-for-x'), [page ? Liferay.Language.get('pages') : Liferay.Language.get('posts'), <span class="query" key="query">{`'${keywords}'`}</span>])}
					/>
				}

				{loading &&
					<LoadingCard />
				}
			</div>
		);
	}
}

const STORE = {
	feedAssetEntrySetsIOMap: Config.instanceOf(OrderedMap),
	loading: Config.bool(),
	searchPages: Config.func(),
	searchPosts: Config.func(),
	sessionTime: Config.number(),
	total: Config.number()
};

SearchFeed.PROPS = {
	...STORE,
	assetEntrySetType: Config.string().value(POSTS),
	childAssetEntrySetsLimit: Config.number(),
	infiniteScroll: Config.bool(),
	itemsPerPage: Config.number(),
	keywords: Config.string(),
	likedParticipantsLimit: Config.number()
};

SearchFeed.STATE = {
	hasMoreResults_: Config.bool().value(true)
};

export default connect(
	(state, {assetEntrySetType, infiniteScroll, keywords}) => {
		const currentFeed = state.getIn(['feeds', keywords], Map());

		let feedAssetEntrySetsIOMap = currentFeed.getIn([assetEntrySetType], OrderedMap()).map(
			feedAssetEntrySetIMap => feedAssetEntrySetIMap.merge(
				getAssetEntrySet(state, feedAssetEntrySetIMap.get('id'), assetEntrySetType)
			)
		);

		if (!infiniteScroll) {
			feedAssetEntrySetsIOMap = feedAssetEntrySetsIOMap.take(5);
		}

		return {
			feedAssetEntrySetsIOMap,
			loading: currentFeed.get('loading'),
			sessionTime: currentFeed.get('sessionTime', Date.now()),
			total: state.getIn(['search', 'posts', 'total'], 0)
		};
	},
	{
		searchPages,
		searchPosts
	}
)(SearchFeed);