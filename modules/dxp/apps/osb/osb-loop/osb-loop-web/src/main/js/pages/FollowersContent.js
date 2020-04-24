import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';

import BaseLayout from './BaseLayout';
import IndexPagination from '../components/IndexPagination';
import {classNameIdToSchema} from '../lib/util';
import {fetchFollowers} from '../actions/followers';
import {getRel} from '../lib/selectors';

const ITEMS_PER_PAGE = 12;

class FollowersContent extends Component {
	created() {
		this.fetchFollowers_ = this.fetchFollowers_.bind(this);
	}

	fetchFollowers_(type) {
		const {
			classNameId,
			fetchFollowers,
			followersIList,
			id
		} = this.props;

		const start = followersIList.size;

		return fetchFollowers(
			{
				classNameId,
				end: start + ITEMS_PER_PAGE,
				id,
				start
			}
		).then(
			({data}) => {
				if (data.length === 0) {
					this.state.hasMoreResults_ = false;
				}
			}
		);
	}

	render() {
		const {
			props: {
				followersIList,
				loading
			},
			state: {hasMoreResults_}
		} = this;

		return (
			<BaseLayout.NineColumn>
				<IndexPagination
					card={true}
					entitiesIList={followersIList}
					hasMoreResults={hasMoreResults_}
					infiniteScroll={true}
					loading={loading}
					onScrollEnd={this.fetchFollowers_}
				/>
			</BaseLayout.NineColumn>
		);
	}
}

const STORE = {
	fetchFollowing: Config.func(),
	followersIList: Config.instanceOf(List),
	loading: Config.bool()
};

FollowersContent.PROPS = {
	...STORE,
	classNameId: Config.number(),
	id: Config.number()
};

FollowersContent.STATE = {
	hasMoreResults_: Config.bool().value(true)
};

export default connect(
	(state, {classNameId, id}) => {
		const schema = classNameIdToSchema(classNameId);

		const followersIMap = state.getIn([schema, id, 'followers'], Map());

		return {
			followersIList: getRel('people', state, followersIMap.get('data', OrderedSet())),
			loading: followersIMap.get('loading', true)
		};
	},
	{
		fetchFollowers
	}
)(FollowersContent);