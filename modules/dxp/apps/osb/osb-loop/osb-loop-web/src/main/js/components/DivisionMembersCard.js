import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';

import EntityCategoryCard from './EntityCategoryCard';
import {fetchLeads, fetchMembers} from '../actions/divisions';
import {getRel} from '../lib/selectors';

const ENTITY_DISPLAY_COUNT = 10;

class DivisionMembersCard extends Component {
	attached() {
		const {fetchLeads, fetchMembers, id} = this.props;

		fetchLeads({end: ENTITY_DISPLAY_COUNT, id});
		fetchMembers({end: ENTITY_DISPLAY_COUNT, id});
	}

	getCategories_() {
		const {leadsIList, membersIList} = this.props;

		const categories = [];

		if (leadsIList.size) {
			categories.push(
				{
					items: leadsIList.toJS().slice(0, ENTITY_DISPLAY_COUNT),
					label: Liferay.Language.get('leads')
				}
			);
		}

		if (membersIList.size) {
			categories.push(
				{
					items: membersIList.toJS().slice(0, ENTITY_DISPLAY_COUNT),
					label: Liferay.Language.get('members')
				}
			);
		}

		return categories;
	}

	render() {
		const {
			loading,
			networkCount,
			seeMoreLink
		} = this.props;

		return (
			<EntityCategoryCard
				categories={this.getCategories_()}
				entityCount={networkCount}
				loading={loading}
				seeMoreLinkData={{
					link: seeMoreLink,
					message: Liferay.Language.get('see-all')
				}}
				title={Liferay.Language.get('members')}
			/>
		);
	}
}

const STORE = {
	fetchLeads: Config.func(),
	fetchMembers: Config.func(),
	leadsIList: Config.instanceOf(List),
	loading: Config.bool(),
	membersIList: Config.instanceOf(List),
	networkCount: Config.number()
};

DivisionMembersCard.PROPS = {
	...STORE,
	id: Config.number().required(),
	seeMoreLink: Config.string()
};

export default connect(
	(state, {id}) => {
		const networkIMap = state.getIn(['divisions', id, 'network'], Map());

		const leadsIMap = networkIMap.get('leads', Map());
		const membersIMap = networkIMap.get('members', Map());

		return {
			leadsIList: getRel('people', state, leadsIMap.get('data', OrderedSet())),
			loading: [leadsIMap, membersIMap].some(group => group.get('loading', true)),
			membersIList: getRel('people', state, membersIMap.get('data', OrderedSet())),
			networkCount: leadsIMap.get('total', 0) + membersIMap.get('total', 0)
		};
	},
	{
		fetchLeads,
		fetchMembers
	}
)(DivisionMembersCard);