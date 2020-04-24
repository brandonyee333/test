import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {isNumber} from 'lodash';
import {List, Map, OrderedSet} from 'immutable';

import BaseLayout from '../BaseLayout';
import Card from '../../components/card';
import IndexPagination from '../../components/IndexPagination';
import {fetchLeads, fetchMembers} from '../../actions/divisions';
import {getRel} from '../../lib/selectors';

const DISPLAY_COUNT = 9;

class MembersContent extends Component {
	created() {
		this.getMoreMembers_ = this.getMoreMembers_.bind(this);

		const {fetchLeads, id} = this.props;

		fetchLeads({end: -1, id, start: -1});
	}

	getMoreMembers_() {
		const {fetchMembers, id, membersIList} = this.props;
		const start = membersIList.size;

		return fetchMembers(
			{
				end: start + DISPLAY_COUNT,
				id,
				start
			}
		);
	}

	render() {
		const {
			leadsIList,
			membersHasMoreResults,
			membersIList,
			membersLoading
		} = this.props;

		return (
			<BaseLayout.NineColumn>
				<Card>
					<IndexPagination
						card={false}
						entitiesIList={leadsIList}
						infiniteScroll={false}
						label={Liferay.Language.get('leads')}
						loading={false}
					/>

					<IndexPagination
						card={false}
						entitiesIList={membersIList}
						hasMoreResults={membersHasMoreResults}
						infiniteScroll={true}
						label={Liferay.Language.get('members')}
						loading={membersLoading}
						onScrollEnd={this.getMoreMembers_}
					/>
				</Card>
			</BaseLayout.NineColumn>
		);
	}
}

const STORE = {
	fetchLeads: Config.func(),
	fetchMembers: Config.func(),
	leadsIList: Config.instanceOf(List),
	membersHasMoreResults: Config.bool(),
	membersIList: Config.instanceOf(List),
	membersLoading: Config.bool()
};

MembersContent.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, {id}) => {
		const networkIMap = state.getIn(['divisions', id, 'network'], Map());

		const membersIMap = networkIMap.get('members', Map());

		const membersIOSet = membersIMap.get('data', OrderedSet());

		const membersTotal = membersIMap.get('total');

		return {
			leadsIList: getRel('people', state, networkIMap.getIn(['leads', 'data'], OrderedSet())),
			membersHasMoreResults: isNumber(membersTotal) ? membersTotal > membersIOSet.size : true,
			membersIList: getRel('people', state, membersIOSet),
			membersLoading: membersIMap.get('loading', true)
		};
	},
	{
		fetchLeads,
		fetchMembers
	}
)(MembersContent);