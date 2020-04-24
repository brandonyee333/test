import Component, {Config} from 'metal-jsx';
import {bindAll, noop, some} from 'lodash';
import {connect} from 'metal-redux';
import {is, List, OrderedSet} from 'immutable';

import AnnouncementCarousel from '../components/AnnouncementCarousel';
import AssetCreator from '../components/AssetCreator';
import BaseLayout from './BaseLayout';
import CuratedEntitiesCard from './CuratedEntitiesCard';
import Feed from '../components/Feed';
import LoopConstants from '../lib/loop-constants';
import NoBookmarksMessage from '../components/NoBookmarksMessage';
import {clearFeed} from '../actions/feeds';
import {fetchFeaturedTopics} from '../actions/topics';
import {fetchNewDivisions} from '../actions/divisions';
import {fetchNewPeople} from '../actions/people';
import {getRel} from '../lib/selectors';
import {modalTypes, showModal} from '../actions/modals';

import {
	removeDisplay,
	setActiveTabIndex,
	setDisplay,
	setPageTitle
} from '../actions/toolbar';

import {
	ANNOUNCEMENTS,
	BOOKMARKS,
	FOLLOWING,
	PRIVATE_POSTS,
	ROUTER_URLS_MAP
} from '../lib/router-constants';

const {
	currentPerson,
	feedItemsPerPage,
	loopStreamAliasIds,
	postTypes
} = LoopConstants;

const {
	announcements,
	announcementsSticky,
	bookmarks,
	following,
	privatePosts
} = loopStreamAliasIds;

const {announcement, post} = postTypes;

const {HOME_URL} = ROUTER_URLS_MAP;

const defaultFeedProps = {
	id: following,
	key: 'followingFeed',
	noResultsMessage: null,
	noResultsTitle: Liferay.Language.get('there-are-no-posts-yet'),
	type: post
};

const ENTITY_DISPLAY_COUNT = 4;

const FEED_PROPS_MAP = {
	[ANNOUNCEMENTS]: {
		id: announcements,
		key: 'announcementsFeed',
		noResultsTitle: Liferay.Language.get('there-are-no-announcements-yet'),
		type: announcement
	},
	[BOOKMARKS]: {
		...defaultFeedProps,
		id: bookmarks,
		key: 'bookmarksFeed',
		noResultsMessage: <NoBookmarksMessage />,
		noResultsTitle: Liferay.Language.get('you-do-not-have-any-posts-bookmarked-yet')
	},
	[FOLLOWING]: defaultFeedProps,
	[PRIVATE_POSTS]: {
		...defaultFeedProps,
		id: privatePosts,
		key: 'privatePostsFeed',
		noResultsTitle: Liferay.Language.get('you-do-not-have-any-private-posts-yet')
	}
};

class Home extends Component {
	created() {
		bindAll(
			this,
			'openFeaturedTopicsSettings'
		);
	}

	attached() {
		const {
			fetchFeaturedTopics,
			fetchNewDivisions,
			fetchNewPeople,
			setActiveTabIndex,
			setPageTitle
		} = this.props;

		setActiveTabIndex(0);

		setPageTitle(Liferay.Language.get('home'));

		this._featuredTopicsRequest = fetchFeaturedTopics().catch(noop);

		this._newDivisionsRequest = fetchNewDivisions().catch(noop);

		this._newPeopleRequest = fetchNewPeople().catch(noop);
	}

	disposed() {
		this.props.clearFeed();

		if (this._featuredTopicsRequest) {
			this._featuredTopicsRequest.cancel();
		}

		if (this._newDivisionsRequest) {
			this._newDivisionsRequest.cancel();
		}

		if (this._newPeopleRequest) {
			this._newPeopleRequest.cancel();
		}
	}

	openFeaturedTopicsSettings() {
		this.props.showModal(
			{
				hideOnBlur: true,
				modalProps: {},
				modalType: modalTypes.FEATURED_TOPICS_SETTINGS
			}
		);
	}

	renderCreator_() {
		let retVal;

		const {subNavId = FOLLOWING} = this.props.router.params;

		if (subNavId === FOLLOWING || subNavId === PRIVATE_POSTS || (subNavId === ANNOUNCEMENTS && currentPerson.permissionAddAnnouncement)) {
			retVal = <AssetCreator streamId={FEED_PROPS_MAP[subNavId].id} />;
		}

		return retVal;
	}

	renderCenterContent_() {
		const {
			featuredTopicsIList,
			featuredTopicsLoading,
			featuredTopicsTotal,
			newDivisionsIList,
			newDivisionsLoading,
			newDivisionsTotal,
			newPeopleIList,
			newPeopleLoading,
			newPeopleTotal,
			router
		} = this.props;

		const {subNavId = FOLLOWING} = router.params;

		const streamProps = FEED_PROPS_MAP[subNavId];

		return [
			<BaseLayout.SixColumn elementClasses="flex-order-last" key={streamProps.id}>
				{this.renderCreator_()}

				{subNavId === FOLLOWING &&
					<AnnouncementCarousel href={`${HOME_URL}/${ANNOUNCEMENTS}`} id={announcementsSticky} />
				}

				<Feed {...streamProps} itemsPerPage={feedItemsPerPage} />
			</BaseLayout.SixColumn>,
			<BaseLayout.ThreeColumn key="spacer">
				{(!!featuredTopicsIList.size || currentPerson.permissionCurator) &&
					<CuratedEntitiesCard
						entityIList={featuredTopicsIList.take(ENTITY_DISPLAY_COUNT)}
						key="featuredTopics"
						loading={featuredTopicsLoading}
						modalType={modalTypes.FEATURED_TOPICS}
						onSettingsClick={currentPerson.permissionCurator ? this.openFeaturedTopicsSettings : null}
						seeMoreMessage={featuredTopicsIList.size > ENTITY_DISPLAY_COUNT ? Liferay.Language.get('see-all') : null}
						title={Liferay.Language.get('featured-topics')}
						total={featuredTopicsTotal}
					/>
				}

				{!!newPeopleIList.size &&
					<CuratedEntitiesCard
						entityIList={newPeopleIList.take(ENTITY_DISPLAY_COUNT)}
						key="newPeople"
						loading={newPeopleLoading}
						modalType={modalTypes.NEW_PEOPLE}
						seeMoreMessage={newPeopleTotal > ENTITY_DISPLAY_COUNT ? Liferay.Language.get('see-all') : null}
						title={Liferay.Language.get('new-hires')}
						total={newPeopleTotal}
					/>
				}

				{!!newDivisionsIList.size &&
					<CuratedEntitiesCard
						entityIList={newDivisionsIList.take(ENTITY_DISPLAY_COUNT)}
						key="newDivisions"
						loading={newDivisionsLoading}
						modalType={modalTypes.NEW_DIVISIONS}
						seeMoreMessage={newDivisionsTotal > ENTITY_DISPLAY_COUNT ? Liferay.Language.get('see-all') : null}
						title={Liferay.Language.get('new-groups')}
						total={newDivisionsTotal}
					/>
				}
			</BaseLayout.ThreeColumn>
		];
	}

	getNavItemsIList_() {
		const {subNavId = FOLLOWING} = this.props.router.params;

		return List(
			[
				{
					href: `${HOME_URL}/${FOLLOWING}`,
					label: Liferay.Language.get('following'),
					selected: subNavId === FOLLOWING
				},
				{
					href: `${HOME_URL}/${ANNOUNCEMENTS}`,
					label: Liferay.Language.get('announcements'),
					selected: subNavId === ANNOUNCEMENTS
				},
				{
					href: `${HOME_URL}/${PRIVATE_POSTS}`,
					label: Liferay.Language.get('private-posts'),
					selected: subNavId === PRIVATE_POSTS
				},
				{
					href: `${HOME_URL}/${BOOKMARKS}`,
					label: Liferay.Language.get('bookmarks'),
					selected: subNavId === BOOKMARKS
				}
			]
		);
	}

	syncRouter(newRouter) {
		const {setDisplay, subNavId} = this.props;

		if (subNavId === PRIVATE_POSTS) {
			setDisplay('primary');
		}
		else {
			setDisplay('default');
		}
	}

	shouldUpdate(nextState, nextProps) {
		var retVal = some(
			nextProps,
			({newVal, prevVal}, prop) => {
				return !is(newVal, prevVal);
			}
		);

		if (!retVal) {
			retVal = some(
				nextProps,
				({newVal, prevVal}, state) => {
					return !is(newVal, prevVal);
				}
			);
		}

		return retVal;
	}

	render() {
		return (
			<BaseLayout
				content={this.renderCenterContent_()}
				elementClasses="home-container"
				key="home"
				navItemsIList={this.getNavItemsIList_()}
			/>
		);
	}
}

const STORE = {
	clearFeed: Config.func(),
	featuredTopicsIList: Config.instanceOf(List),
	featuredTopicsLoading: Config.bool(),
	featuredTopicsTotal: Config.number(),
	newDivisionsIList: Config.instanceOf(List),
	newDivisionsLoading: Config.bool(),
	newDivisionsTotal: Config.number(),
	newPeopleIList: Config.instanceOf(List),
	newPeopleLoading: Config.bool(),
	newPeopleTotal: Config.number()
};

Home.PROPS = {
	...STORE,
	router: Config.object()
};

export default connect(
	(state, ownProps) => (
		{
			featuredTopicsIList: getRel('topics', state, state.getIn(['lists', 'featuredTopics', 'data'], OrderedSet())),
			featuredTopicsLoading: state.getIn(['lists', 'featuredTopics', 'loading']),
			featuredTopicsTotal: state.getIn(['lists', 'featuredTopics', 'total']),
			newDivisionsIList: getRel('divisions', state, state.getIn(['lists', 'newDivisions', 'data'], OrderedSet())),
			newDivisionsLoading: state.getIn(['lists', 'newDivisions', 'loading']),
			newDivisionsTotal: state.getIn(['lists', 'newDivisions', 'total']),
			newPeopleIList: getRel('people', state, state.getIn(['lists', 'newPeople', 'data'], OrderedSet())),
			newPeopleLoading: state.getIn(['lists', 'newPeople', 'loading']),
			newPeopleTotal: state.getIn(['lists', 'newPeople', 'total'])
		}
	),
	{
		clearFeed,
		fetchFeaturedTopics,
		fetchNewDivisions,
		fetchNewPeople,
		removeDisplay,
		setActiveTabIndex,
		setDisplay,
		setPageTitle,
		showModal
	}
)(Home);