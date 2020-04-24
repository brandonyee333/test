import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';
import {noop} from 'lodash';

import EntityCategoryCard from './EntityCategoryCard';
import {fetchManagers, fetchSubordinates} from '../actions/people';
import {getRel} from '../lib/selectors';

class PersonNetworkCard extends Component {
	attached() {
		const {
			displayCount,
			fetchManagers,
			fetchSubordinates,
			id
		} = this.props;

		fetchManagers({end: displayCount, id}).catch(noop);

		fetchSubordinates({end: displayCount, id}).catch(noop);
	}

	getCategories_() {
		const {
			displayCount,
			managersIList,
			personIMap,
			subordinatesIList
		} = this.props;

		const categories = [];

		const hasManagers = !!managersIList.size;
		const hasSubordinates = !!subordinatesIList.size;

		if (hasManagers) {
			categories.push(
				{
					items: managersIList.take(displayCount).toJS(),
					label: Liferay.Language.get('reports-to')
				}
			);
		}

		categories.push(
			{
				items: personIMap.toJS(),
				lineBottom: hasSubordinates,
				lineTop: hasManagers
			}
		);

		if (hasSubordinates) {
			categories.push(
				{
					items: subordinatesIList.take(displayCount).toJS(),
					label: Liferay.Language.get('manages')
				}
			);
		}

		return categories;
	}

	render() {
		const {
			displaySize,
			loading,
			networkCount,
			seeMoreLink
		} = this.props;

		return (
			<span>
				<EntityCategoryCard
					categories={this.getCategories_()}
					displaySize={displaySize}
					entityCount={networkCount}
					loading={loading}
					seeMoreLinkData={{
						link: seeMoreLink,
						message: Liferay.Language.get('see-full-network')
					}}
					title={Liferay.Language.get('network')}
				/>
			</span>
		);
	}
}

const STORE = {
	fetchManagers: Config.func(),
	fetchSubordinates: Config.func(),
	loading: Config.bool(),
	managersIList: Config.instanceOf(List),
	networkCount: Config.number(),
	personIMap: Config.instanceOf(Map),
	subordinatesIList: Config.instanceOf(List)
};

PersonNetworkCard.PROPS = {
	...STORE,
	displayCount: Config.number().value(10),
	displaySize: Config.oneOf(['medium', 'small']),
	id: Config.number().required(),
	seeMoreLink: Config.string()
};

export default connect(
	(state, {id}) => {
		const personIMap = state.getIn(['people', id], Map());

		const networkIMap = personIMap.get('network', Map());

		const managersIMap = networkIMap.get('managers', Map());
		const subordinatesIMap = networkIMap.get('subordinates', Map());

		const groups = [managersIMap, subordinatesIMap];

		return {
			loading: groups.some(group => group.get('loading', true)),
			managersIList: getRel('people', state, managersIMap.get('data', OrderedSet())),
			networkCount: managersIMap.get('total', 0) + subordinatesIMap.get('total', 0),
			personIMap: personIMap.get('data', Map()),
			subordinatesIList: getRel('people', state, subordinatesIMap.get('data', OrderedSet()))
		};
	},
	{
		fetchManagers,
		fetchSubordinates
	}
)(PersonNetworkCard);