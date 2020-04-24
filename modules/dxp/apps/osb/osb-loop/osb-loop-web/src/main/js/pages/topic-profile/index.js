import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import AboutCard from '../../components/AboutCard';
import AssetCreator from '../../components/AssetCreator';
import BaseLayout from '../BaseLayout';
import ErrorWrapper from '../../components/ErrorWrapper';
import ExpertsContent from './ExpertsContent';
import Feed from '../../components/Feed';
import FollowersContent from '../FollowersContent';
import LoopConstants from '../../lib/loop-constants';
import Profile from '../Profile';
import TopicExpertsCard from '../../components/TopicExpertsCard';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearFeed} from '../../actions/feeds';
import {EXPERTS, FOLLOWERS, STREAM} from '../../lib/router-constants';
import {fetchTopic} from '../../actions/topics';
import {getId} from '../../lib/router-util';
import {getRootTopicId} from '../../lib/selectors';
import {setActiveTabIndex, setPageTitle} from '../../actions/toolbar';

const {classNameIds} = LoopConstants;

const TABS_MAP = {
	[EXPERTS]: id => [<ExpertsContent classNameId={classNameIds.topics} id={id} key="expertsContent" />],
	[FOLLOWERS]: id => [<FollowersContent classNameId={classNameIds.topics} id={id} key="followersContent" />],
	[STREAM]: (id, displayURL) => {
		const streamId = `${classNameIds.topics}-${id}`;

		return [
			<BaseLayout.SixColumn elementClasses="flex-order-last" key="sixColumn">
				<AssetCreator streamId={streamId} />

				<Feed id={streamId} itemsPerPage={7} />
			</BaseLayout.SixColumn>,
			<BaseLayout.ThreeColumn key="threeColumn">
				<TopicExpertsCard id={id} seeMoreLink={`${displayURL}/${EXPERTS}`} />
			</BaseLayout.ThreeColumn>
		];
	}
};

class TopicProfile extends Component {
	attached() {
		this.props.setActiveTabIndex(null);
	}

	fetchTopicData_() {
		const {
			addAlert,
			fetchTopic,
			id,
			setPageTitle
		} = this.props;

		fetchTopic(id).then(
			({data}) => {
				setPageTitle(data.loopTopicCompositeJSONObject.name);

				this.state.error_ = false;
			}
		).catch(
			({message}) => {
				this.setState(
					{
						error_: true
					},
					() => addAlert(
						{
							alertType: alertTypes.ERROR,
							message
						}
					)
				);
			}
		);
	}

	getNavItemsIList_() {
		const {router, topicDataIMap} = this.props;

		const {displayURL, followersCount, loopTopicAssignmentsCount} = topicDataIMap.toJS();

		const {subNavId = STREAM} = router.params;

		return List(
			[
				{
					href: `${displayURL}/${STREAM}`,
					label: Liferay.Language.get('stream'),
					selected: subNavId === STREAM
				},
				{
					href: `${displayURL}/${EXPERTS}`,
					label: Liferay.Language.get('experts'),
					selected: subNavId === EXPERTS,
					total: loopTopicAssignmentsCount
				},
				{
					href: `${displayURL}/${FOLLOWERS}`,
					label: Liferay.Language.get('followers'),
					selected: subNavId === FOLLOWERS,
					total: followersCount
				}
			]
		);
	}

	syncRouter(newVal, prevVal) {
		if (!prevVal || prevVal.params.entityId !== newVal.params.entityId) {
			this.props.clearFeed();

			this.fetchTopicData_();
		}
	}

	render() {
		const {
			props: {
				id,
				loading,
				router,
				topicDataIMap
			},
			state: {error_}
		} = this;

		const {descriptionMarkdownHTML, displayURL} = topicDataIMap.toJS();

		const {subNavId = STREAM} = router.params;

		return (
			<ErrorWrapper error={error_} loading={loading && topicDataIMap.isEmpty()}>
				<Profile classNameId={classNameIds.topics} id={id} key={`profile${id}`}>
					<BaseLayout
						content={TABS_MAP[subNavId](id, displayURL)}
						leftContent={[<AboutCard key="aboutCard" message={descriptionMarkdownHTML ? descriptionMarkdownHTML : Liferay.Language.get('no-description')} />]}
						navItemsIList={this.getNavItemsIList_()}
					/>
				</Profile>
			</ErrorWrapper>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	clearFeed: Config.func(),
	id: Config.oneOfType([Config.number(), Config.string()]),
	loading: Config.bool(),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func(),
	topicDataIMap: Config.instanceOf(Map)
};

TopicProfile.PROPS = {
	...STORE,
	router: Config.object()
};

TopicProfile.STATE = {
	error_: Config.bool().value(false)
};

export default connect(
	(state, {router}) => {
		const topicsIMap = state.get('topics', Map());

		const id = getRootTopicId(state, getId(topicsIMap, router.params.entityId));

		return {
			id,
			loading: topicsIMap.getIn([id, 'loading'], true),
			topicDataIMap: topicsIMap.getIn([id, 'data'], Map())
		};
	},
	{
		addAlert,
		clearFeed,
		fetchTopic,
		setActiveTabIndex,
		setPageTitle
	}
)(TopicProfile);