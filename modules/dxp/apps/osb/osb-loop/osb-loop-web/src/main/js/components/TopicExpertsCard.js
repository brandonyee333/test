import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';
import {noop} from 'lodash';

import EntityListCard from './EntityListCard';
import {fetchExperts} from '../actions/topics';
import {getRel} from '../lib/selectors';

const ITEMS_PER_PAGE = 6;

class TopicExpertsCard extends Component {
	created() {
		this._request = this.fetchExperts_();
	}

	disposed() {
		if (this._request) {
			this._request.cancel();
		}
	}

	fetchExperts_() {
		const {fetchExperts, id} = this.props;

		return fetchExperts(
			{
				end: ITEMS_PER_PAGE,
				id,
				start: 0
			}
		).catch(noop);
	}

	render() {
		const {expertsIList, loading, total} = this.props;

		return (
			<span>
				<EntityListCard
					{...this.otherProps()}
					entitiesIList={expertsIList}
					loading={loading}
					seeMoreMessage={Liferay.Language.get('see-all')}
					title={Liferay.Language.get('experts')}
					total={total}
				/>
			</span>
		);
	}
}

const STORE = {
	expertsIList: Config.instanceOf(List),
	loading: Config.bool(),
	total: Config.number()
};

TopicExpertsCard.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, {id}) => {
		const topicIMap = state.getIn(['topics', id], Map());

		return {
			expertsIList: getRel('people', state, topicIMap.getIn(['experts', 'data'], OrderedSet()).take(ITEMS_PER_PAGE)),
			loading: topicIMap.getIn(['experts', 'loading'], true),
			total: topicIMap.getIn(['data', 'loopTopicAssignmentsCount'], 0)
		};
	},
	{
		fetchExperts
	}
)(TopicExpertsCard);