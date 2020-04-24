import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import AboutContent from './AboutContent';
import AssetCreator from '../../components/AssetCreator';
import BaseLayout from '../BaseLayout';
import ContactCard from '../../components/ContactCard';
import ElsewhereCard from '../../components/ElsewhereCard';
import ErrorWrapper from '../../components/ErrorWrapper';
import Feed from '../../components/Feed';
import FollowersContent from '../FollowersContent';
import FollowingContent from './FollowingContent';
import GroupsContent from './GroupsContent';
import InactiveCard from '../../components/InactiveCard';
import LoopConstants from '../../lib/loop-constants';
import MarkdownContentCard from '../../components/MarkdownContentCard';
import NetworkContent from './NetworkContent';
import PersonGroupsCard from '../../components/PersonGroupsCard';
import PersonNetworkCard from '../../components/PersonNetworkCard';
import Profile from '../Profile';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearFeed} from '../../actions/feeds';
import {fetchPerson} from '../../actions/people';
import {getId} from '../../lib/router-util';
import {setActiveTabIndex, setPageTitle} from '../../actions/toolbar';

import {
	ABOUT,
	FOLLOWERS,
	FOLLOWING,
	GROUPS,
	NETWORK,
	STREAM
} from '../../lib/router-constants';

const {classNameIds} = LoopConstants;

const TABS_MAP = {
	[ABOUT]: id => [<AboutContent id={id} key="aboutContent" />],
	[FOLLOWERS]: id => [<FollowersContent classNameId={classNameIds.people} id={id} key="followersContent" />],
	[FOLLOWING]: id => [<FollowingContent classNameId={classNameIds.people} id={id} key="followingContent" />],
	[GROUPS]: id => [<GroupsContent id={id} key="groupsContent" />],
	[NETWORK]: id => [<NetworkContent id={id} key="networkContent" />],
	[STREAM]: (id, personDataIMap) => {
		const {displayURL, inactive, name} = personDataIMap.toJS();

		const streamId = `${classNameIds.people}-${id}`;

		return [
			<BaseLayout.SixColumn elementClasses="flex-order-last" key="sixColumn">
				{inactive &&
					<InactiveCard name={name} />
				}

				{!inactive &&
					<AssetCreator streamId={streamId} />
				}

				<Feed id={streamId} itemsPerPage={7} />
			</BaseLayout.SixColumn>,
			<BaseLayout.ThreeColumn key="threeColumn">
				<ContactCard id={id} />

				<ElsewhereCard id={id} />

				<PersonNetworkCard
					displayCount={10}
					displaySize="small"
					id={id}
					seeMoreLink={`${displayURL}/${NETWORK}`}
				/>

				<PersonGroupsCard id={id} seeMoreLink={`${displayURL}/${GROUPS}`} />
			</BaseLayout.ThreeColumn>
		];
	}
};

class PersonProfile extends Component {
	attached() {
		this.props.setActiveTabIndex(null);
	}

	fetchPersonData_() {
		const {
			addAlert,
			fetchPerson,
			id,
			setPageTitle
		} = this.props;

		fetchPerson(id).then(
			({data}) => {
				setPageTitle(data.name);

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
		const {personDataIMap, router} = this.props;

		const {
			displayURL,
			followersCount,
			followingCount,
			loopParticipantAssignmentsCount
		} = personDataIMap.toJS();

		const {subNavId = STREAM} = router.params;

		return List(
			[
				{
					href: `${displayURL}/${STREAM}`,
					label: Liferay.Language.get('stream'),
					selected: subNavId === STREAM
				},
				{
					href: `${displayURL}/${NETWORK}`,
					label: Liferay.Language.get('network'),
					selected: subNavId === NETWORK
				},
				{
					href: `${displayURL}/${GROUPS}`,
					label: Liferay.Language.get('groups'),
					selected: subNavId === GROUPS,
					total: loopParticipantAssignmentsCount
				},
				{
					href: `${displayURL}/${FOLLOWERS}`,
					label: Liferay.Language.get('followers'),
					selected: subNavId === FOLLOWERS,
					total: followersCount
				},
				{
					href: `${displayURL}/${FOLLOWING}`,
					label: Liferay.Language.get('following'),
					selected: subNavId === FOLLOWING,
					total: followingCount
				},
				{
					href: `${displayURL}/${ABOUT}`,
					label: Liferay.Language.get('about'),
					selected: subNavId === ABOUT
				}
			]
		);
	}

	syncRouter(newVal, prevVal) {
		if (!prevVal || prevVal.params.entityId !== newVal.params.entityId) {
			this.props.clearFeed();

			this.fetchPersonData_();
		}
	}

	render() {
		const {
			props: {
				id,
				loading,
				personDataIMap,
				router
			},
			state: {error_}
		} = this;

		const {subNavId = STREAM} = router.params;

		return (
			<ErrorWrapper error={error_} loading={loading && personDataIMap.isEmpty()}>
				<Profile classNameId={classNameIds.people} id={id} key={`profile${id}`}>
					<BaseLayout
						content={TABS_MAP[subNavId](id, personDataIMap)}
						leftContent={[
							<MarkdownContentCard
								content={personDataIMap.get('jobResponsibilitiesMarkdownHTML')}
								headerTitle={Liferay.Language.get('job-responsibilities')}
								key="jobResponsibilities"
								noContentMessage={Liferay.Language.get('none-provided')}
							/>
						]}
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
	personDataIMap: Config.instanceOf(Map),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func()
};

PersonProfile.PROPS = {
	...STORE,
	router: Config.object()
};

PersonProfile.STATE = {
	error_: Config.bool().value(false)
};

export default connect(
	(state, {router}) => {
		const peopleIMap = state.get('people', Map());

		const id = getId(peopleIMap, router.params.entityId);

		return {
			id,
			loading: peopleIMap.getIn([id, 'loading'], true),
			personDataIMap: peopleIMap.getIn([id, 'data'], Map())
		};
	},
	{
		addAlert,
		clearFeed,
		fetchPerson,
		setActiveTabIndex,
		setPageTitle
	}
)(PersonProfile);