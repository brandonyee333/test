import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {isBoolean, isNumber} from 'lodash';
import {List, Map, OrderedSet} from 'immutable';

import Card from '../../components/card';
import IndexPagination from '../../components/IndexPagination';
import BaseLayout from '../BaseLayout';
import LoopConstants from '../../lib/loop-constants';
import {fetchChildDivisions} from '../../actions/divisions';
import {getRel} from '../../lib/selectors';

const {departmentSubtypes} = LoopConstants;

class NetworkContent extends Component {
	syncDepartment(newVal) {
		const {fetchChildDivisions, id} = this.props;

		const configObj = {end: -1, id, start: -1};

		if (newVal) {
			fetchChildDivisions(
				{
					...configObj,
					subtype: departmentSubtypes.functional
				}
			);

			fetchChildDivisions(
				{
					...configObj,
					subtype: departmentSubtypes.regional
				}
			);
		}
		else {
			fetchChildDivisions(
				{
					...configObj,
					subtype: departmentSubtypes.none
				}
			);
		}
	}

	render() {
		const {
			childDivisionsIList,
			childDivisionsIListLoading,
			department,
			functionalChildDivisionsIList,
			functionalChildDivisionsLoading,
			regionalChildDivisionsIList,
			regionalChildDivisionsLoading
		} = this.props;

		return (
			<BaseLayout.NineColumn>
				{isBoolean(department) &&
					<Card>
						{!department &&
							<IndexPagination
								card={false}
								entitiesIList={childDivisionsIList}
								key="all"
								label={Liferay.Language.get('contains')}
								loading={childDivisionsIListLoading}
							/>
						}

						{department &&
							<IndexPagination
								card={false}
								entitiesIList={functionalChildDivisionsIList}
								key="functional"
								label={Liferay.Language.get('functional')}
								loading={functionalChildDivisionsLoading}
							/>
						}

						{department &&
							<IndexPagination
								card={false}
								entitiesIList={regionalChildDivisionsIList}
								key="regional"
								label={Liferay.Language.get('regional')}
								loading={regionalChildDivisionsLoading}
							/>
						}
					</Card>
				}
			</BaseLayout.NineColumn>
		);
	}
}

const STORE = {
	childDivisionsIList: Config.instanceOf(List),
	childDivisionsLoading: Config.bool().value(true),
	department: Config.bool().value(false),
	fetchChildDivisions: Config.func(),
	functionalChildDivisionsIList: Config.instanceOf(List),
	functionalChildDivisionsLoading: Config.bool().value(true),
	regionalChildDivisionsIList: Config.instanceOf(List),
	regionalChildDivisionsLoading: Config.bool().value(true)
};

NetworkContent.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, {id}) => {
		const networkIMap = state.getIn(['divisions', id, 'network'], Map());
		const type = state.getIn(['divisions', id, 'data', 'type']);

		const childDivisionsIMap = networkIMap.getIn(['childDivisions', departmentSubtypes.none], Map());
		const functionalChildDivisionIMap = networkIMap.getIn(['childDivisions', departmentSubtypes.functional], Map());
		const regionalChildDivisionIMap = networkIMap.getIn(['childDivisions', departmentSubtypes.regional], Map());

		return {
			childDivisionsIList: getRel('divisions', state, childDivisionsIMap.get('data', OrderedSet())),
			childDivisionsLoading: childDivisionsIMap.get('loading', true),
			department: isNumber(type) ? type === LoopConstants.types.department : null,
			functionalChildDivisionsIList: getRel('divisions', state, functionalChildDivisionIMap.get('data', OrderedSet())),
			functionalChildDivisionsLoading: functionalChildDivisionIMap.get('loading', true),
			regionalChildDivisionsIList: getRel('divisions', state, regionalChildDivisionIMap.get('data', OrderedSet())),
			regionalChildDivisionsLoading: regionalChildDivisionIMap.get('loading', true)
		};
	},
	{
		fetchChildDivisions
	}
)(NetworkContent);