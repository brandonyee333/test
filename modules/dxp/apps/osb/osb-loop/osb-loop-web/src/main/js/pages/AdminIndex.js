import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List} from 'immutable';

import AuthTokens from '../components/AuthTokens';
import Card from '../components/card';
import BaseLayout from './BaseLayout';
import ListCard from '../components/ListCard';
import {ALL, ROUTER_URLS_MAP} from '../lib/router-constants';
import {setActiveTabIndex, setPageTitle} from '../actions/toolbar';

const {ADMIN_URL, JOB_TITLES_URL} = ROUTER_URLS_MAP;

const jobTitlesItem = {
	content: Liferay.Language.get('job-titles'),
	icon: 'person',
	url: JOB_TITLES_URL
};

class AdminIndex extends Component {
	attached() {
		const {setActiveTabIndex, setPageTitle} = this.props;

		setPageTitle(Liferay.Language.get('admin'));

		setActiveTabIndex(null);
	}

	getNavItemsIList_() {
		const {subNavId = ALL} = this.props.router.params;

		return List(
			[
				{
					href: `${ADMIN_URL}/${ALL}`,
					label: Liferay.Language.get('all'),
					selected: subNavId === ALL
				}
			]
		);
	}

	render() {
		return (
			<BaseLayout
				content={[
					<BaseLayout.NineColumn key="adminAll">
						<ListCard elementClasses="settings-container" items={[jobTitlesItem]} title={Liferay.Language.get('admin-page')} />

						<Card>
							<Card.Header>
								{Liferay.Language.get('all-device-tokens')}
							</Card.Header>

							<Card.Body>
								<AuthTokens />
							</Card.Body>
						</Card>
					</BaseLayout.NineColumn>
				]}
				navItemsIList={this.getNavItemsIList_()}
			/>
		);
	}
}

const STORE = {
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func()
};

AdminIndex.PROPS = {
	...STORE,
	router: Config.object()
};

export default connect(
	null,
	{
		setActiveTabIndex,
		setPageTitle
	}
)(AdminIndex);