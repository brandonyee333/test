import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import IndexPage from './IndexPage';
import LoopConstants from '../lib/loop-constants';
import ModalLink from '../components/ModalLink';
import {fetchTypeTotals} from '../actions/divisions';
import {modalTypes} from '../actions/modals';
import {setActiveTabIndex, setPageTitle} from '../actions/toolbar';

import {
	DEPARTMENTS,
	LOCATIONS,
	ROUTER_URLS_MAP,
	TEAMS
} from '../lib/router-constants';

const {
	classNameIds,
	types: {department, location, team}
} = LoopConstants;

const TYPES_MAP = {
	[DEPARTMENTS]: {
		type: department
	},
	[LOCATIONS]: {
		type: location
	},
	[TEAMS]: {
		type: team
	}
};

const {DIVISIONS_URL} = ROUTER_URLS_MAP;

class DivisionsIndex extends Component {
	created() {
		this._modalConfig = {
			fullScreen: true,
			hideOnBlur: false,
			modalProps: {},
			modalType: modalTypes.ORG_CHART
		};
	}

	attached() {
		const {fetchTypeTotals, setActiveTabIndex, setPageTitle} = this.props;

		setPageTitle(Liferay.Language.get('groups'));

		setActiveTabIndex(2);

		this._request = fetchTypeTotals();
	}

	disposed() {
		if (this._request && this._request.cancel) {
			this._request.cancel();
		}
	}

	getNavItemsIList_() {
		const {
			departmentTotal,
			locationTotal,
			router,
			teamTotal
		} = this.props;

		const {subNavId = DEPARTMENTS} = router.params;

		return List(
			[
				{
					href: `${DIVISIONS_URL}/${DEPARTMENTS}`,
					label: Liferay.Language.get('departments'),
					selected: subNavId === DEPARTMENTS,
					total: departmentTotal
				},
				{
					href: `${DIVISIONS_URL}/${TEAMS}`,
					label: Liferay.Language.get('teams'),
					selected: subNavId === TEAMS,
					total: teamTotal
				},
				{
					href: `${DIVISIONS_URL}/${LOCATIONS}`,
					label: Liferay.Language.get('locations'),
					selected: subNavId === LOCATIONS,
					total: locationTotal
				}
			]
		);
	}

	render() {
		const {permissionCreate, router} = this.props;

		const {subNavId = DEPARTMENTS} = router.params;

		return (
			<IndexPage
				bottomNavContent={[
					<ModalLink config={this._modalConfig} key="modalLink">
						{Liferay.Language.get('see-company-org-chart')}
					</ModalLink>
				]}
				classNameId={classNameIds.divisions}
				createLabel={Liferay.Language.get('create-group')}
				createPermission={permissionCreate}
				elementClasses="division-index-container"
				listName={subNavId}
				modalType={modalTypes.CREATE_DIVISION}
				navItemsIList={this.getNavItemsIList_()}
				requestParams={TYPES_MAP[subNavId]}
			/>
		);
	}
}

const STORE = {
	departmentTotal: Config.number(),
	fetchTypeTotals: Config.func(),
	locationTotal: Config.number(),
	permissionCreate: Config.bool(),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func(),
	teamTotal: Config.number()
};

DivisionsIndex.PROPS = {
	...STORE,
	router: Config.object()
};

export default connect(
	(state, {router}) => {
		const {subNavId = DEPARTMENTS} = router.params;

		const divisionTypes = state.get('lists', Map());

		return {
			departmentTotal: divisionTypes.getIn(['departments', 'total'], 0),
			locationTotal: divisionTypes.getIn(['locations', 'total'], 0),
			permissionCreate: divisionTypes.getIn([subNavId, 'permissionCreate'], false),
			teamTotal: divisionTypes.getIn(['teams', 'total'], 0)
		};
	},
	{
		fetchTypeTotals,
		setActiveTabIndex,
		setPageTitle
	}
)(DivisionsIndex);