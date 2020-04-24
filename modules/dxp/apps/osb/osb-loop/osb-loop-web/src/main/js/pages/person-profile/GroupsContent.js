import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {keys, noop} from 'lodash';
import {List, Map, OrderedSet} from 'immutable';

import BaseLayout from '../BaseLayout';
import Card from '../../components/card';
import IndexPagination from '../../components/IndexPagination';
import {DIVISION_TYPE_NAME_MAP} from '../../lib/util';
import {fetchDivisions} from '../../actions/people';
import {getRel} from '../../lib/selectors';

const divisionTypes = keys(DIVISION_TYPE_NAME_MAP);

class GroupsContent extends Component {
	created() {
		const {fetchDivisions, id} = this.props;

		divisionTypes.map(
			divisionType => fetchDivisions({divisionType, end: -1, id, start: -1}).catch(noop)
		);
	}

	render() {
		const {departmentsIList, locationsIList, teamsIList} = this.props;

		return (
			<BaseLayout.NineColumn>
				<Card>
					<IndexPagination
						card={false}
						entitiesIList={teamsIList}
						icon="people"
						label={Liferay.Language.get('teams')}
						loading={false}
					/>

					<IndexPagination
						card={false}
						entitiesIList={departmentsIList}
						icon="person-card-3"
						label={Liferay.Language.get('departments')}
						loading={false}
					/>

					<IndexPagination
						card={false}
						entitiesIList={locationsIList}
						icon="building"
						label={Liferay.Language.get('locations')}
						loading={false}
					/>
				</Card>
			</BaseLayout.NineColumn>
		);
	}
}

const STORE = {
	departmentsIList: Config.instanceOf(List),
	fetchDivisions: Config.func(),
	locationsIList: Config.instanceOf(List),
	teamsIList: Config.instanceOf(List)
};

GroupsContent.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, {id}) => {
		const peopleDivisionsIMap = state.getIn(['people', id, 'network', 'divisions'], Map());

		return {
			departmentsIList: getRel('divisions', state, peopleDivisionsIMap.getIn(['department', 'data'], OrderedSet())),
			locationsIList: getRel('divisions', state, peopleDivisionsIMap.getIn(['location', 'data'], OrderedSet())),
			teamsIList: getRel('divisions', state, peopleDivisionsIMap.getIn(['team', 'data'], OrderedSet()))
		};
	},
	{
		fetchDivisions
	}
)(GroupsContent);