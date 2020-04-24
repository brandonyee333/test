import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import IndexPage from './IndexPage';
import LoopConstants from '../lib/loop-constants';
import {ALL, ROUTER_URLS_MAP} from '../lib/router-constants';
import {modalTypes} from '../actions/modals';
import {setActiveTabIndex, setPageTitle} from '../actions/toolbar';

const {PEOPLE_URL} = ROUTER_URLS_MAP;

class PeopleIndex extends Component {
	attached() {
		const {setActiveTabIndex, setPageTitle} = this.props;

		setPageTitle(Liferay.Language.get('people'));

		setActiveTabIndex(1);
	}

	getNavItemsIList_() {
		const {peopleTotal, router} = this.props;

		const {subNavId = ALL} = router.params;

		return List(
			[
				{
					href: `${PEOPLE_URL}/${ALL}`,
					label: Liferay.Language.get('all'),
					selected: subNavId === ALL,
					total: peopleTotal
				}
			]
		);
	}

	render() {
		return (
			<IndexPage
				classNameId={LoopConstants.classNameIds.people}
				createLabel={Liferay.Language.get('create-person')}
				createPermission={this.props.permissionCreate}
				modalType={modalTypes.CREATE_PERSON}
				navItemsIList={this.getNavItemsIList_()}
			/>
		);
	}
}

const STORE = {
	peopleTotal: Config.number(),
	permissionCreate: Config.bool(),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func()
};

PeopleIndex.PROPS = {
	...STORE,
	router: Config.object()
};

export default connect(
	state => {
		const peopleListIMap = state.getIn(['lists', 'people'], Map());

		return {
			peopleTotal: peopleListIMap.get('total', 0),
			permissionCreate: peopleListIMap.get('permissionCreate', false)
		};
	},
	{
		setActiveTabIndex,
		setPageTitle
	}
)(PeopleIndex);