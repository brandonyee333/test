import Component from 'metal-jsx';
import Router from 'metal-router';

import AdminIndex from './pages/AdminIndex';
import DivisionEdit from './pages/DivisionEdit';
import DivisionIndex from './pages/DivisionIndex';
import DivisionProfile from './pages/division-profile';
import ErrorPage from './pages/ErrorPage';
import Home from './pages/Home';
import JobTitlesIndex from './pages/JobTitlesIndex';
import JobTitleProfile from './pages/job-title-profile';
import PeopleIndex from './pages/PeopleIndex';
import PersonEdit from './pages/PersonEdit';
import PersonProfile from './pages/person-profile';
import Search from './pages/search';
import SinglePostView from './components/SinglePostView';
import TopicEdit from './pages/TopicEdit';
import TopicsIndex from './pages/TopicsIndex';
import TopicProfile from './pages/topic-profile';
import {getErrorRoute, getHomeRoute, getRouterRegex} from './lib/router-util';

import {
	A_Z,
	ABOUT,
	ALL,
	ALL_PAGES,
	ANNOUNCEMENTS,
	BOOKMARKS,
	COMPANY,
	CREATE,
	DEPARTMENTS,
	EDIT,
	EXPERTS,
	FOLLOWERS,
	FOLLOWING,
	GROUPS,
	LOCATIONS,
	MEMBERS,
	NETWORK,
	NEWEST,
	PAGES,
	PEOPLE,
	POSTS,
	PRIVATE_POSTS,
	ROUTER_URLS_MAP,
	STREAM,
	TEAMS,
	TOPICS,
	TRENDING
} from './lib/router-constants';

const {
	ADMIN_URL,
	COMPANY_URL,
	DEPARTMENTS_URL,
	DIVISIONS_URL,
	FEED_URL,
	HOME_URL,
	JOB_TITLES_URL,
	LOCATIONS_URL,
	PEOPLE_URL,
	SEARCH_URL,
	TEAMS_URL,
	TOPICS_URL
} = ROUTER_URLS_MAP;

const adminRegex = getRouterRegex(ADMIN_URL);
const companyRegex = getRouterRegex(COMPANY_URL);
const departmentsRegex = getRouterRegex(DEPARTMENTS_URL);
const divisionsRegex = getRouterRegex(DIVISIONS_URL);
const feedRegex = getRouterRegex(FEED_URL);
const homeRegex = getRouterRegex(HOME_URL);
const jobTitlesRegex = getRouterRegex(JOB_TITLES_URL);
const locationsRegex = getRouterRegex(LOCATIONS_URL);
const peopleRegex = getRouterRegex(PEOPLE_URL);
const searchRegex = getRouterRegex(SEARCH_URL);
const teamsRegex = getRouterRegex(TEAMS_URL);
const topicsRegex = getRouterRegex(TOPICS_URL);

class LoopApp extends Component {
	attached() {
		Router.router().dispatch();
	}

	render() {
		return (
			<div>
				<Router
					component={AdminIndex}
					path={`${adminRegex}/:subNavId(${ALL}$)?`}
				/>

				<Router
					component={DivisionEdit}
					path={`${homeRegex}/:divisionId(${COMPANY}|${DEPARTMENTS}|${TEAMS}|${LOCATIONS})/_:entityId/:subNavId(${EDIT}$)`}
				/>

				<Router
					component={DivisionProfile}
					path={`${companyRegex}/_:entityId/:subNavId(${STREAM}|${MEMBERS}|${NETWORK}|${FOLLOWERS}|${PAGES})?/:pagesSubNav(${ALL_PAGES})?/:pageId([0-9]+|${CREATE})?/:editing(${EDIT}$)?`}
				/>

				<Router
					component={DivisionProfile}
					path={`${departmentsRegex}/_:entityId/:subNavId(${STREAM}|${MEMBERS}|${NETWORK}|${FOLLOWERS}|${PAGES})?/:pagesSubNav(${ALL_PAGES})?/:pageId([0-9]+|${CREATE})?/:editing(${EDIT}$)?`}
				/>

				<Router
					component={DivisionProfile}
					path={`${locationsRegex}/_:entityId/:subNavId(${STREAM}|${MEMBERS}|${NETWORK}|${FOLLOWERS}|${PAGES})?/:pagesSubNav(${ALL_PAGES})?/:pageId([0-9]+|${CREATE})?/:editing(${EDIT}$)?`}
				/>

				<Router
					component={DivisionProfile}
					path={`${teamsRegex}/_:entityId/:subNavId(${STREAM}|${MEMBERS}|${NETWORK}|${FOLLOWERS}|${PAGES})?/:pagesSubNav(${ALL_PAGES})?/:pageId([0-9]+|${CREATE})?/:editing(${EDIT}$)?`}
				/>

				<Router
					component={DivisionIndex}
					path={`${divisionsRegex}/:subNavId(${DEPARTMENTS}|${TEAMS}|${LOCATIONS}$)?`}
				/>

				<Router
					component={JobTitleProfile}
					path={`${jobTitlesRegex}/_:entityId/:subNavId(${ABOUT}|${EDIT}$)?`}
				/>

				<Router
					component={JobTitlesIndex}
					path={`${jobTitlesRegex}/:subNavId(${ALL}$)?`}
				/>

				<Router
					component={PersonEdit}
					path={`${peopleRegex}/_:entityId/:subNavId(${EDIT})`}
				/>

				<Router
					component={PersonProfile}
					path={`${peopleRegex}/_:entityId/:subNavId(${STREAM}|${NETWORK}|${GROUPS}|${FOLLOWERS}|${FOLLOWING}|${ABOUT}$)?`}
				/>

				<Router
					component={PeopleIndex}
					path={`${peopleRegex}/:subNavId(${ALL}$)?`}
				/>

				<Router
					component={Search}
					path={`${searchRegex}/:subNavId(${ALL}|${PAGES}|${POSTS}|${PEOPLE}|${GROUPS}|${TOPICS}$)?`}
				/>

				<Router
					component={SinglePostView}
					path={`${feedRegex}/:focusedId`}
				/>

				<Router
					component={TopicEdit}
					path={`${topicsRegex}/_:entityId/:subNavId(${EDIT}$)`}
				/>

				<Router
					component={TopicProfile}
					path={`${topicsRegex}/_:entityId/:subNavId(${STREAM}|${EXPERTS}|${FOLLOWERS}$)?`}
				/>

				<Router
					component={TopicsIndex}
					path={`${topicsRegex}/:subNavId(${TRENDING}|${FOLLOWERS}|${A_Z}|${NEWEST}$)?`}
				/>

				<Router
					component={Home}
					path={getHomeRoute}
				/>

				<Router
					component={Home}
					path={`${homeRegex}/:subNavId(${FOLLOWING}|${ANNOUNCEMENTS}|${PRIVATE_POSTS}|${BOOKMARKS}$)?`}
				/>

				<Router
					component={ErrorPage}
					path={getErrorRoute}
				/>
			</div>
		);
	}
}

export default LoopApp;