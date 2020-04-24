import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {keys, noop, values} from 'lodash';
import {List, Map, OrderedSet} from 'immutable';

import EntityListCard from './EntityListCard';
import {DIVISION_TYPE_NAME_MAP} from '../lib/util';
import {fetchDivisions} from '../actions/people';
import {getRel} from '../lib/selectors';

const ENTITY_DISPLAY_COUNT = 5;

const divisionTypeNames = values(DIVISION_TYPE_NAME_MAP);
const divisionTypes = keys(DIVISION_TYPE_NAME_MAP);

class PersonGroupsCard extends Component {
	created() {
		const {fetchDivisions, id} = this.props;

		divisionTypes.map(
			divisionType => fetchDivisions({divisionType, end: ENTITY_DISPLAY_COUNT, id}).catch(noop)
		);
	}

	render() {
		const {divisionsIList, loading, total} = this.props;

		return (
			<span>
				<EntityListCard
					{...this.otherProps()}
					entitiesIList={divisionsIList}
					loading={loading}
					seeMoreMessage={Liferay.Language.get('see-all')}
					title={Liferay.Language.get('groups')}
					total={total}
				/>
			</span>
		);
	}
}

const STORE = {
	divisionsIList: Config.instanceOf(List),
	fetchDivisions: Config.func(),
	loading: Config.bool(),
	total: Config.number()
};

PersonGroupsCard.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, {id}) => {
		const peopleIMap = state.getIn(['people', id], Map());

		const peopleDivisionsIMap = peopleIMap.getIn(['network', 'divisions'], Map());

		let divisionsIList = List();

		divisionTypeNames.forEach(
			divisionTypeName => {
				if (divisionsIList.size < ENTITY_DISPLAY_COUNT) {
					divisionsIList = divisionsIList.concat(
						peopleDivisionsIMap.getIn(
							[divisionTypeName, 'data'],
							OrderedSet()
						)
					).take(ENTITY_DISPLAY_COUNT);
				}
			}
		);

		return {
			divisionsIList: getRel('divisions', state, divisionsIList),
			loading: divisionTypeNames.some(divisionTypeName => peopleDivisionsIMap.getIn([divisionTypeName, 'loading'], true)),
			total: peopleIMap.getIn(['data', 'loopParticipantAssignmentsCount'], 0)
		};
	},
	{
		fetchDivisions
	}
)(PersonGroupsCard);