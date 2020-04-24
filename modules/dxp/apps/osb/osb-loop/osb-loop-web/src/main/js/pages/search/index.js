import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List} from 'immutable';

import BaseLayout from '../BaseLayout';
import EntitiesSummary from './EntitiesSummary';
import EntityTab from './EntityTab';
import LoadingCard from '../../components/LoadingCard';
import LoopConstants from '../../lib/loop-constants';
import NoResultsDisplay from '../../components/NoResultsDisplay';
import SearchFeed from './SearchFeed';
import {clearFeed} from '../../actions/feeds';
import {clearSearch, fullSearch} from '../../actions/home';
import {lang} from '../../lib/lang-util';
import {searchDivisions} from '../../actions/divisions';
import {searchPeople} from '../../actions/people';
import {searchTopics} from '../../actions/topics';
import {setActiveTabIndex, setPageTitle} from '../../actions/toolbar';
import {setParamValue} from '../../lib/router-util';

import {
	ALL,
	GROUPS,
	PAGES,
	PEOPLE,
	POSTS,
	ROUTER_URLS_MAP,
	TOPICS
} from '../../lib/router-constants';

const {childAssetEntrySetsLimit, classNameIds, likedParticipantsLimit} = LoopConstants;

const ITEMS_PER_PAGE = 5;

const {SEARCH_URL} = ROUTER_URLS_MAP;

class Search extends Component {
	CONTENT_MAP = {
		[ALL]: query => [
			<BaseLayout.SixColumn elementClasses="flex-order-last" key="postsSummary">
				<SearchFeed
					assetEntrySetType={POSTS}
					childAssetEntrySetsLimit={childAssetEntrySetsLimit}
					infiniteScroll={false}
					itemsPerPage={ITEMS_PER_PAGE}
					keywords={query}
					likedParticipantsLimit={likedParticipantsLimit}
					sessionTime={Date.now()}
				/>
			</BaseLayout.SixColumn>,

			<BaseLayout.ThreeColumn key="entitySummary">
				<EntitiesSummary itemsPerPage={ITEMS_PER_PAGE} keywords={query} />
			</BaseLayout.ThreeColumn>
		],
		[GROUPS]: query => [
			<BaseLayout.NineColumn key="divisions">
				<EntityTab classNameId={classNameIds.divisions} query={query} searchMethod={this.props.searchDivisions} />
			</BaseLayout.NineColumn>
		],
		[PAGES]: query => [
			<BaseLayout.NineColumn elementClasses="flex-order-last" key="pages">
				<SearchFeed
					assetEntrySetType={PAGES}
					infiniteScroll={true}
					itemsPerPage={ITEMS_PER_PAGE}
					keywords={query}
					sessionTime={Date.now()}
				/>
			</BaseLayout.NineColumn>
		],
		[PEOPLE]: query => [
			<BaseLayout.NineColumn key="people">
				<EntityTab classNameId={classNameIds.people} query={query} searchMethod={this.props.searchPeople} />
			</BaseLayout.NineColumn>
		],
		[POSTS]: query => [
			<BaseLayout.SixColumn elementClasses="flex-order-last" key="posts">
				<SearchFeed
					assetEntrySetType={POSTS}
					childAssetEntrySetsLimit={childAssetEntrySetsLimit}
					infiniteScroll={true}
					itemsPerPage={ITEMS_PER_PAGE}
					keywords={query}
					likedParticipantsLimit={likedParticipantsLimit}
					sessionTime={Date.now()}
				/>
			</BaseLayout.SixColumn>,
			<BaseLayout.ThreeColumn key="spacer" />
		],
		[TOPICS]: query => [
			<BaseLayout.NineColumn key="topics">
				<EntityTab classNameId={classNameIds.topics} query={query} searchMethod={this.props.searchTopics} />
			</BaseLayout.NineColumn>
		]
	}

	attached() {
		const {setActiveTabIndex, setPageTitle} = this.props;

		setPageTitle(Liferay.Language.get('search'));

		setActiveTabIndex(null);
	}

	disposed() {
		const {clearFeed, clearSearch} = this.props;

		clearFeed();

		clearSearch();
	}

	fullSearch_() {
		const {fullSearch, router: {query}} = this.props;

		fullSearch(
			{
				childAssetEntrySetsLimit,
				end: ITEMS_PER_PAGE,
				itemsPerPage: ITEMS_PER_PAGE,
				keywords: decodeURIComponent(query.q),
				likedParticipantsLimit,
				sessionTime: Date.now(),
				start: 0
			}
		);
	}

	getContent_() {
		const {
			loading,
			router: {
				params: {subNavId = ALL},
				query
			}
		} = this.props;

		const decodedQuery = decodeURIComponent(query.q);

		let retVal;

		if (loading) {
			retVal = [
				<BaseLayout.NineColumn key="loading">
					<LoadingCard />
				</BaseLayout.NineColumn>
			];
		}
		else if (!this.hasResults()) {
			retVal = [
				<BaseLayout.NineColumn key="noResults">
					<NoResultsDisplay
						icon="magnifier"
						message={
							lang(
								Liferay.Language.get('we-could-not-find-any-results-for-x'),
								[<span class="query" key="query">{decodedQuery}</span>]
							)
						}
					/>
				</BaseLayout.NineColumn>
			];
		}
		else {
			retVal = this.CONTENT_MAP[subNavId](decodedQuery);
		}

		return retVal;
	}

	getNavItemsIList_() {
		const {
			divisionsTotal,
			pagesTotal,
			peopleTotal,
			postsTotal,
			router: {
				params: {subNavId = ALL},
				query
			},
			topicsTotal
		} = this.props;

		return List(
			[
				{
					href: setParamValue(`${SEARCH_URL}/${ALL}`, 'q', query.q),
					label: Liferay.Language.get('all'),
					selected: subNavId === ALL
				},
				{
					href: setParamValue(`${SEARCH_URL}/${POSTS}`, 'q', query.q),
					label: Liferay.Language.get('posts'),
					selected: subNavId === POSTS,
					total: postsTotal
				},
				{
					href: setParamValue(`${SEARCH_URL}/${PEOPLE}`, 'q', query.q),
					label: Liferay.Language.get('people'),
					selected: subNavId === PEOPLE,
					total: peopleTotal
				},
				{
					href: setParamValue(`${SEARCH_URL}/${GROUPS}`, 'q', query.q),
					label: Liferay.Language.get('groups'),
					selected: subNavId === GROUPS,
					total: divisionsTotal
				},
				{
					href: setParamValue(`${SEARCH_URL}/${TOPICS}`, 'q', query.q),
					label: Liferay.Language.get('topics'),
					selected: subNavId === TOPICS,
					total: topicsTotal
				},
				{
					href: setParamValue(`${SEARCH_URL}/${PAGES}`, 'q', query.q),
					label: Liferay.Language.get('pages'),
					selected: subNavId === PAGES,
					total: pagesTotal
				}
			]
		);
	}

	hasResults() {
		const {
			divisionsTotal,
			pagesTotal,
			peopleTotal,
			postsTotal,
			topicsTotal
		} = this.props;

		return !!(divisionsTotal || pagesTotal || peopleTotal || postsTotal || topicsTotal);
	}

	syncRouter(newVal, prevVal) {
		if (!prevVal || prevVal.query.q !== newVal.query.q) {
			this.fullSearch_();
		}
	}

	render() {
		return (
			<BaseLayout
				content={this.getContent_()}
				elementClasses="search-page-container"
				key="search"
				loading={this.props.loading}
				navItemsIList={this.getNavItemsIList_()}
			/>
		);
	}
}

const STORE = {
	clearFeed: Config.func(),
	clearSearch: Config.func(),
	divisionsTotal: Config.number(),
	fullSearch: Config.func(),
	loading: Config.bool(),
	pagesTotal: Config.number(),
	peopleTotal: Config.number(),
	postsTotal: Config.number(),
	searchDivisions: Config.func(),
	searchPeople: Config.func(),
	searchTopics: Config.func(),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func(),
	topicsTotal: Config.number()
};

Search.PROPS = {
	...STORE,
	router: Config.object()
};

export default connect(
	state => (
		{
			divisionsTotal: state.getIn(['search', 'divisions', 'total']),
			loading: state.getIn(['search', 'loading'], true),
			pagesTotal: state.getIn(['search', 'pages', 'total']),
			peopleTotal: state.getIn(['search', 'people', 'total']),
			postsTotal: state.getIn(['search', 'posts', 'total']),
			topicsTotal: state.getIn(['search', 'topics', 'total'])
		}
	),
	{
		clearFeed,
		clearSearch,
		fullSearch,
		searchDivisions,
		searchPeople,
		searchTopics,
		setActiveTabIndex,
		setPageTitle
	}
)(Search);