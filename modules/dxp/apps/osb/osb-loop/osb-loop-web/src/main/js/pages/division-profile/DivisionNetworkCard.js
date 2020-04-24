import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';

import EntityCategoryCard from '../../components/EntityCategoryCard';
import LoopConstants from '../../lib/loop-constants';
import {fetchChildDivisions} from '../../actions/divisions';
import {getRel} from '../../lib/selectors';

const ENTITY_DISPLAY_COUNT = 5;

const {departmentSubtypes} = LoopConstants;

class DivisionNetworkCard extends Component {
	attached() {
		const {
			fetchChildDivisions,
			id
		} = this.props;

		fetchChildDivisions({end: ENTITY_DISPLAY_COUNT, id, subtype: departmentSubtypes.none});
	}

	getCategories_() {
		const {childDivisionsIList, divisionIMap, parentDivisionIMap} = this.props;

		const categories = [];

		const hasChildDivisions = !!childDivisionsIList.size;
		const hasParentDivision = !parentDivisionIMap.isEmpty() && parentDivisionIMap.get('type') !== LoopConstants.types.root;

		if (hasParentDivision) {
			categories.push(
				{
					items: parentDivisionIMap.toJS(),
					label: Liferay.Language.get('belongs-to')
				}
			);
		}

		categories.push(
			{
				items: divisionIMap.toJS(),
				lineBottom: hasChildDivisions,
				lineTop: hasParentDivision
			}
		);

		if (hasChildDivisions) {
			categories.push(
				{
					items: childDivisionsIList.take(ENTITY_DISPLAY_COUNT).toJS(),
					label: Liferay.Language.get('contains')
				}
			);
		}

		return categories;
	}

	render() {
		const {loading, networkCount, seeMoreLink} = this.props;

		return (
			<EntityCategoryCard
				categories={this.getCategories_()}
				displayDirection="column"
				entityCount={networkCount}
				loading={loading}
				seeMoreLinkData={{
					link: seeMoreLink,
					message: Liferay.Language.get('see-full-network')
				}}
				title={Liferay.Language.get('network')}
			/>
		);
	}
}

const STORE = {
	childDivisionsIList: Config.instanceOf(List),
	divisionIMap: Config.instanceOf(Map),
	fetchChildDivisions: Config.func(),
	loading: Config.bool(),
	networkCount: Config.number(),
	parentDivisionIMap: Config.instanceOf(Map)
};

DivisionNetworkCard.PROPS = {
	...STORE,
	id: Config.number().required(),
	seeMoreLink: Config.string()
};

export default connect(
	(state, {id}) => {
		const divisionIMap = state.getIn(['divisions', id], Map());

		const networkIMap = divisionIMap.get('network', Map());

		const childDivisionsIMap = networkIMap.getIn(['childDivisions', departmentSubtypes.none], Map());
		const parentDivisionIMap = networkIMap.get('parentDivision', Map());

		return {
			childDivisionsIList: getRel('divisions', state, childDivisionsIMap.get('data', OrderedSet())),
			divisionIMap: divisionIMap.get('data', Map()),
			loading: [childDivisionsIMap, divisionIMap, parentDivisionIMap].some(group => group.get('loading', true)),
			networkCount: childDivisionsIMap.get('total', 0),
			parentDivisionIMap: getRel('divisions', state, parentDivisionIMap.get('data', -1), Map())
		};
	},
	{
		fetchChildDivisions
	}
)(DivisionNetworkCard);