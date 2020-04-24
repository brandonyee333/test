import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List} from 'immutable';

import IndexPage from './IndexPage';
import LoopConstants from '../lib/loop-constants';
import {modalTypes} from '../actions/modals';
import {setActiveTabIndex, setPageTitle} from '../actions/toolbar';

import {
	A_Z,
	FOLLOWERS,
	NEWEST,
	ROUTER_URLS_MAP,
	TRENDING
} from '../lib/router-constants';

const {classNameIds} = LoopConstants;

const {TOPICS_URL} = ROUTER_URLS_MAP;

const PARAM_MAP = {
	[A_Z]: {
		reverseSort: 0,
		sort: 'topicName_sortable'
	},
	[FOLLOWERS]: {
		reverseSort: 1,
		sort: 'followersCount_sortable'
	},
	[NEWEST]: {
		reverseSort: 1,
		sort: 'createDate_sortable'
	},
	[TRENDING]: {
		reverseSort: 1,
		sort: 'score_sortable'
	}
};

class TopicsIndex extends Component {
	attached() {
		const {setActiveTabIndex, setPageTitle} = this.props;

		setPageTitle(Liferay.Language.get('topics'));

		setActiveTabIndex(3);
	}

	getNavItemsIList_() {
		const {subNavId = TRENDING} = this.props.router.params;

		return List(
			[
				{
					href: `${TOPICS_URL}/${TRENDING}`,
					label: Liferay.Language.get('trending'),
					selected: subNavId === TRENDING
				},
				{
					href: `${TOPICS_URL}/${FOLLOWERS}`,
					label: Liferay.Language.get('followers'),
					selected: subNavId === FOLLOWERS
				},
				{
					href: `${TOPICS_URL}/${A_Z}`,
					label: Liferay.Language.get('a-z'),
					selected: subNavId === A_Z
				},
				{
					href: `${TOPICS_URL}/${NEWEST}`,
					label: Liferay.Language.get('newest'),
					selected: subNavId === NEWEST
				}
			]
		);
	}

	render() {
		const {permissionCreate, router} = this.props;

		const {subNavId = TRENDING} = router.params;

		return (
			<IndexPage
				classNameId={classNameIds.topics}
				createLabel={Liferay.Language.get('create-topic')}
				createPermission={permissionCreate}
				listName={subNavId}
				modalType={modalTypes.CREATE_TOPIC}
				navItemsIList={this.getNavItemsIList_()}
				requestParams={PARAM_MAP[subNavId]}
			/>
		);
	}
}

const STORE = {
	permissionCreate: Config.bool(),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func()
};

TopicsIndex.PROPS = {
	...STORE,
	router: Config.object()
};

export default connect(
	(state, {router}) => {
		const {subNavId = TRENDING} = router.params;

		return {
			permissionCreate: state.getIn(['lists', subNavId, 'permissionCreate'], false)
		};
	},
	{
		setActiveTabIndex,
		setPageTitle
	}
)(TopicsIndex);