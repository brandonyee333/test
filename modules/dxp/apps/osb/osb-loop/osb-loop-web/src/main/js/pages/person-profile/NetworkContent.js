import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';
import {noop} from 'lodash';

import Card from '../../components/card';
import IndexPagination from '../../components/IndexPagination';
import BaseLayout from '../BaseLayout';
import {fetchManagers, fetchSubordinates} from '../../actions/people';
import {getRel} from '../../lib/selectors';

const DISPLAY_COUNT = 9;

class NetworkContent extends Component {
	created() {
		this.getMoreSubordinates_ = this.getMoreSubordinates_.bind(this);

		const {fetchManagers, id} = this.props;

		fetchManagers({end: -1, id, start: -1}).catch(noop);

		this.getMoreSubordinates_();
	}

	getMoreSubordinates_() {
		const {fetchSubordinates, id, subordinatesIList} = this.props;

		const start = subordinatesIList.size;

		return fetchSubordinates({end: start + DISPLAY_COUNT, id, start}).catch(noop);
	}

	render() {
		const {
			managersIList,
			subordinatesHasMoreResults,
			subordinatesIList,
			subordinatesLoading
		} = this.props;

		return (
			<BaseLayout.NineColumn>
				<Card>
					<IndexPagination
						card={false}
						entitiesIList={managersIList}
						label={Liferay.Language.get('reports-to')}
						loading={false}
					/>

					<IndexPagination
						card={false}
						entitiesIList={subordinatesIList}
						hasMoreResults={subordinatesHasMoreResults}
						infiniteScroll={true}
						label={Liferay.Language.get('manages')}
						loading={subordinatesLoading}
						onScrollEnd={this.getMoreSubordinates_}
					/>
				</Card>
			</BaseLayout.NineColumn>
		);
	}
}

const STORE = {
	fetchManagers: Config.func(),
	fetchSubordinates: Config.func(),
	managersIList: Config.instanceOf(List),
	subordinatesHasMoreResults: Config.bool(),
	subordinatesIList: Config.instanceOf(List),
	subordinatesLoading: Config.bool()
};

NetworkContent.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, {id}) => {
		const networkIMap = state.getIn(['people', id, 'network'], Map());

		const subordinatesIMap = networkIMap.get('subordinates', Map());

		const subordinatesIOSet = subordinatesIMap.get('data', OrderedSet());

		return {
			managersIList: getRel('people', state, networkIMap.getIn(['managers', 'data'], OrderedSet())),
			subordinatesHasMoreResults: subordinatesIMap.get('total', 0) > subordinatesIOSet.size,
			subordinatesIList: getRel('people', state, subordinatesIOSet),
			subordinatesLoading: subordinatesIMap.get('loading', false)
		};
	},
	{
		fetchManagers,
		fetchSubordinates
	}
)(NetworkContent);