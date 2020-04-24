import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';

import BaseLayout from '../BaseLayout';
import Card from '../../components/card';
import IndexPagination from '../../components/IndexPagination';
import LoopConstants from '../../lib/loop-constants';
import Tabs from '../../components/Tabs';
import {addAlert, alertTypes} from '../../actions/alerts';
import {classNameIdToSchema} from '../../lib/util';
import {getRel} from '../../lib/selectors';
import {fetchFollowing} from '../../actions/people';

const ITEMS_PER_PAGE = 12;

const {classNameIds} = LoopConstants;

class FollowingList extends Component {
	created() {
		const {
			classNameId,
			fetchFollowing,
			id,
			infiniteScroll
		} = this.props;

		this.handleFetchFollowing_ = this.handleFetchFollowing_.bind(this);

		if (!infiniteScroll) {
			fetchFollowing(
				{
					classNameId,
					end: ITEMS_PER_PAGE,
					id,
					start: 0
				}
			).catch(
				({message}) => addAlert(
					{
						alertType: alertTypes.ERROR,
						message: message ? message : Liferay.Language.get('unable-to-fetch-pages')
					}
				)
			);
		}
	}

	handleFetchFollowing_() {
		const {
			classNameId,
			fetchFollowing,
			followingIList,
			id
		} = this.props;

		const start = followingIList.size;

		return fetchFollowing(
			{
				classNameId,
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
		).catch(
			({message}) => addAlert(
				{
					alertType: alertTypes.ERROR,
					message: message ? message : Liferay.Language.get('unable-to-fetch-pages')
				}
			)
		);
	}

	render() {
		const {
			props: {
				followingIList,
				infiniteScroll,
				label,
				loading
			},
			state: {hasMoreResults_}
		} = this;

		return (
			<IndexPagination
				card={false}
				entitiesIList={infiniteScroll ? followingIList : followingIList.take(ITEMS_PER_PAGE)}
				hasMoreResults={hasMoreResults_}
				infiniteScroll={infiniteScroll}
				label={label}
				loading={loading}
				onScrollEnd={this.handleFetchFollowing_}
			/>
		);
	}
}

const STORE = {
	fetchFollowing: Config.func(),
	followingIList: Config.instanceOf(List),
	loading: Config.bool()
};

FollowingList.PROPS = {
	...STORE,
	classNameId: Config.number(),
	id: Config.number(),
	infiniteScroll: Config.bool().value(false),
	label: Config.string()
};

FollowingList.STATE = {
	hasMoreResults_: Config.bool().value(true)
};

const ConnectedFollowingList = connect(
	(state, {classNameId, id}) => {
		const schema = classNameIdToSchema(classNameId);

		const followingIMap = state.getIn(['people', id, 'following', schema], Map());

		return {
			followingIList: getRel(schema, state, followingIMap.get('data', OrderedSet())),
			loading: followingIMap.get('loading', true)
		};
	},
	{
		addAlert,
		fetchFollowing
	}
)(FollowingList);

class FollowingContent extends Component {
	created() {
		this.handleTabClick_ = this.handleTabClick_.bind(this);
	}

	handleTabClick_(index) {
		this.state.tabIndex_ = index;
	}

	render() {
		const {
			props: {
				divisionsTotal,
				followingTotal,
				peopleTotal,
				topicsTotal,
				id
			},
			state: {tabIndex_}
		} = this;

		return (
			<BaseLayout.NineColumn>
				<Card elementClasses="following-content-container">
					<Tabs index={tabIndex_} onIndexChange={this.handleTabClick_}>
						<Tabs.Content count={followingTotal} name={Liferay.Language.get('all')}>
							<ConnectedFollowingList
								classNameId={classNameIds.people}
								id={id}
								infiniteScroll={true}
								label={Liferay.Language.get('people')}
							/>

							<ConnectedFollowingList
								classNameId={classNameIds.divisions}
								id={id}
								infiniteScroll={true}
								label={Liferay.Language.get('groups')}
							/>

							<ConnectedFollowingList
								classNameId={classNameIds.topics}
								id={id}
								infiniteScroll={true}
								label={Liferay.Language.get('topics')}
							/>
						</Tabs.Content>

						<Tabs.Content count={peopleTotal} name={Liferay.Language.get('people')}>
							<ConnectedFollowingList classNameId={classNameIds.people} id={id} infiniteScroll={true} />
						</Tabs.Content>

						<Tabs.Content count={divisionsTotal} name={Liferay.Language.get('groups')}>
							<ConnectedFollowingList classNameId={classNameIds.divisions} id={id} infiniteScroll={true} />
						</Tabs.Content>

						<Tabs.Content count={topicsTotal} name={Liferay.Language.get('topics')}>
							<ConnectedFollowingList classNameId={classNameIds.topics} id={id} infiniteScroll={true} />
						</Tabs.Content>
					</Tabs>
				</Card>
			</BaseLayout.NineColumn>
		);
	}
}

const followingContentSTORE = {
	divisionsTotal: Config.number(),
	followingTotal: Config.number(),
	peopleTotal: Config.number(),
	topicsTotal: Config.number()
};

FollowingContent.PROPS = {
	...followingContentSTORE,
	id: Config.number()
};

FollowingContent.STATE = {
	tabIndex_: Config.number().value(0)
};

export default connect(
	(state, {id}) => {
		const personIMap = state.getIn(['people', id], Map());

		const followingIMap = personIMap.get('following', Map());

		return {
			divisionsTotal: followingIMap.getIn([classNameIdToSchema(classNameIds.divisions), 'total'], 0),
			followingTotal: personIMap.getIn(['data', 'followingCount'], 0),
			peopleTotal: followingIMap.getIn([classNameIdToSchema(classNameIds.people), 'total'], 0),
			topicsTotal: followingIMap.getIn([classNameIdToSchema(classNameIds.topics), 'total'], 0)
		};
	},
	null
)(FollowingContent);