import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import IndexPage from './IndexPage';
import LoopConstants from '../lib/loop-constants';
import {ALL, ROUTER_URLS_MAP} from '../lib/router-constants';
import {modalTypes} from '../actions/modals';
import {setActiveTabIndex, setPageTitle} from '../actions/toolbar';

const {JOB_TITLES_URL} = ROUTER_URLS_MAP;

class JobTitlesIndex extends Component {
	attached() {
		const {setActiveTabIndex, setPageTitle} = this.props;

		setPageTitle(Liferay.Language.get('job-titles'));

		setActiveTabIndex(null);
	}

	getNavItemsIList_() {
		const {jobTitleTotal, router} = this.props;

		const {subNavId = ALL} = router.params;

		return List(
			[
				{
					href: `${JOB_TITLES_URL}/${ALL}`,
					label: Liferay.Language.get('all'),
					selected: subNavId === ALL,
					total: jobTitleTotal
				}
			]
		);
	}

	render() {
		return (
			<IndexPage
				classNameId={LoopConstants.classNameIds.jobTitles}
				createLabel={Liferay.Language.get('create-job-title')}
				createPermission={this.props.permissionCreate}
				modalType={modalTypes.CREATE_JOB_TITLE}
				navItemsIList={this.getNavItemsIList_()}
				status={LoopConstants.status.any}
			/>
		);
	}
}

const STORE = {
	jobTitleTotal: Config.number(),
	permissionCreate: Config.bool(),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func()
};

JobTitlesIndex.PROPS = {
	...STORE,
	router: Config.object()
};

export default connect(
	state => {
		const jobTitlesListIMap = state.getIn(['lists', 'jobTitles'], Map());

		return {
			jobTitleTotal: jobTitlesListIMap.get('total', 0),
			permissionCreate: jobTitlesListIMap.get('permissionCreate', false)
		};
	},
	{
		setActiveTabIndex,
		setPageTitle
	}
)(JobTitlesIndex);