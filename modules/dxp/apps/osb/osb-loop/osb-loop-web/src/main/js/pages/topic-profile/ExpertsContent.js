import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, OrderedSet} from 'immutable';

import BaseLayout from '../BaseLayout';
import IndexPagination from '../../components/IndexPagination';
import {fetchExperts} from '../../actions/topics';
import {getRel} from '../../lib/selectors';

const ITEMS_PER_PAGE = 12;

class ExpertsContent extends Component {
	created() {
		this.fetchExperts_ = this.fetchExperts_.bind(this);
	}

	fetchExperts_(type) {
		const {
			expertsIList,
			fetchExperts,
			id
		} = this.props;

		const start = expertsIList.size;

		return fetchExperts(
			{
				end: start + ITEMS_PER_PAGE,
				id,
				start
			}
		).then(
			({data}) => {
				if (data.results.length === 0) {
					this.state.hasMoreResults_ = false;
				}
			}
		);
	}

	render() {
		const {
			props: {
				expertsIList,
				loading
			},
			state: {hasMoreResults_}
		} = this;

		return (
			<BaseLayout.NineColumn>
				<IndexPagination
					card={true}
					entitiesIList={expertsIList}
					hasMoreResults={hasMoreResults_}
					infiniteScroll={true}
					loading={loading}
					onScrollEnd={this.fetchExperts_}
				/>
			</BaseLayout.NineColumn>
		);
	}
}

const STORE = {
	expertsIList: Config.instanceOf(List),
	fetchExperts: Config.func(),
	loading: Config.bool()
};

ExpertsContent.PROPS = {
	...STORE,
	id: Config.number()
};

ExpertsContent.STATE = {
	hasMoreResults_: Config.bool().value(true)
};

export default connect(
	(state, {id}) => (
		{
			expertsIList: getRel('people', state, state.getIn(['topics', id, 'experts', 'data'], OrderedSet())),
			loading: state.getIn(['topics', id, 'experts', 'loading'], true)
		}
	),
	{
		fetchExperts
	}
)(ExpertsContent);