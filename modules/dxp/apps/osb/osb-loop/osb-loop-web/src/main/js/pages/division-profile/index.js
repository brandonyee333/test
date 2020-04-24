import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import AboutCard from '../../components/AboutCard';
import AssetCreator from '../../components/AssetCreator';
import BaseLayout from '../BaseLayout';
import DivisionEdit from '../DivisionEdit';
import DivisionMembersCard from '../../components/DivisionMembersCard';
import DivisionNetworkCard from './DivisionNetworkCard';
import ErrorWrapper from '../../components/ErrorWrapper';
import Feed from '../../components/Feed';
import FollowersContent from '../FollowersContent';
import LoopConstants from '../../lib/loop-constants';
import MembersContent from './MembersContent';
import NetworkContent from '../division-profile/NetworkContent';
import PagesContent from './PagesContent';
import Profile from '../Profile';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearFeed} from '../../actions/feeds';
import {fetchDivision} from '../../actions/divisions';
import {getId} from '../../lib/router-util';
import {getRel} from '../../lib/selectors';
import {modalTypes} from '../../actions/modals';
import {setActiveTabIndex, setPageTitle} from '../../actions/toolbar';

import {
	CREATE,
	EDIT,
	FOLLOWERS,
	MEMBERS,
	NETWORK,
	PAGES,
	STREAM
} from '../../lib/router-constants';

const {classNameIds, types} = LoopConstants;

const TABS_MAP = {
	[EDIT]: id => [<DivisionEdit id={id} key="editContent" />],
	[FOLLOWERS]: id => [<FollowersContent classNameId={classNameIds.divisions} id={id} key="followersContent" />],
	[MEMBERS]: id => [<MembersContent classNameId={classNameIds.divisions} id={id} key="membersContent" />],
	[NETWORK]: id => [<NetworkContent id={id} key="networkContent" />],
	[PAGES]: (id, divisionDataIMap, routerParams) => {
		const {displayURL, pagesCount, permissionAddPages} = divisionDataIMap.toJS();

		let {editing, pageId} = routerParams;

		if (pageId === CREATE) {
			editing = true;
			pageId = null;
		}
		else if (pageId) {
			pageId = Number(pageId);
		}

		return [
			<PagesContent
				displayURL={displayURL}
				editing={editing}
				hasPages={!!pagesCount}
				key="pagesContent"
				ownerId={id}
				pageId={pageId}
				pagesSubNav={routerParams.pagesSubNav}
				permissionAdd={permissionAddPages}
			/>
		];
	},
	[STREAM]: (id, divisionDataIMap) => {
		const {displayURL, type} = divisionDataIMap.toJS();

		const streamId = `${classNameIds.divisions}-${id}`;

		return [
			<BaseLayout.SixColumn elementClasses="flex-order-last" key="sixColumn">
				<AssetCreator streamId={streamId} />

				<Feed id={streamId} itemsPerPage={7} />
			</BaseLayout.SixColumn>,
			<BaseLayout.ThreeColumn key="threeColumnm">
				<DivisionMembersCard id={id} seeMoreLink={`${displayURL}/${MEMBERS}`} />

				{type && type !== LoopConstants.types.root &&
					<DivisionNetworkCard id={id} seeMoreLink={`${displayURL}/${NETWORK}`} />
				}
			</BaseLayout.ThreeColumn>
		];
	}
};

class DivisionProfile extends Component {
	attached() {
		this.props.setActiveTabIndex(null);
	}

	fetchDivisionData_() {
		const {
			addAlert,
			fetchDivision,
			id,
			setPageTitle
		} = this.props;

		fetchDivision(id).then(
			({data}) => {
				setPageTitle(data.loopDivisionCompositeJSONObject.name);

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

	getButtonConfig_() {
		const {divisionDataIMap, id} = this.props;

		const {type} = divisionDataIMap.toJS();

		let config;

		if (type === types.department || type === types.root) {
			config = {
				iconName: 'org-chart',
				modalConfig: {
					fullScreen: true,
					hideOnBlur: false,
					modalProps: {id},
					modalType: modalTypes.ORG_CHART
				},
				title: Liferay.Language.get('see-org-chart')
			};
		}

		return config;
	}

	getNavItemsIList_() {
		const {divisionDataIMap, router} = this.props;

		const {
			childLoopDivisionsCount,
			displayURL,
			followersCount,
			loopParticipantAssignmentsCount,
			pagesCount
		} = divisionDataIMap.toJS();

		const {subNavId = STREAM} = router.params;

		return List(
			[
				{
					href: `${displayURL}/${STREAM}`,
					label: Liferay.Language.get('stream'),
					selected: subNavId === STREAM
				},
				{
					href: `${displayURL}/${MEMBERS}`,
					label: Liferay.Language.get('members'),
					selected: subNavId === MEMBERS,
					total: loopParticipantAssignmentsCount
				},
				{
					href: `${displayURL}/${NETWORK}`,
					label: Liferay.Language.get('network'),
					selected: subNavId === NETWORK,
					total: childLoopDivisionsCount
				},
				{
					href: `${displayURL}/${FOLLOWERS}`,
					label: Liferay.Language.get('followers'),
					selected: subNavId === FOLLOWERS,
					total: followersCount
				},
				{
					href: `${displayURL}/${PAGES}`,
					label: Liferay.Language.get('pages'),
					selected: subNavId === PAGES,
					total: pagesCount
				}
			]
		);
	}

	syncRouter(newVal, prevVal) {
		if (!prevVal || prevVal.params.entityId !== newVal.params.entityId) {
			this.props.clearFeed();

			this.fetchDivisionData_();
		}
	}

	render() {
		const {
			props: {
				divisionDataIMap,
				id,
				loading,
				parentDivisionIMap,
				router: {params}
			},
			state: {error_}
		} = this;

		const {descriptionMarkdownHTML} = divisionDataIMap.toJS();

		const {subNavId = STREAM} = params;

		return (
			<ErrorWrapper error={error_} loading={loading && divisionDataIMap.isEmpty()}>
				<Profile
					classNameId={classNameIds.divisions}
					id={id}
					key={`profile${id}`}
					parentEntityIMap={parentDivisionIMap}
					secondaryButtonConfig={this.getButtonConfig_()}
				>
					<BaseLayout
						content={TABS_MAP[subNavId](id, divisionDataIMap, params)}
						leftContent={descriptionMarkdownHTML ? [<AboutCard key="aboutCard" message={descriptionMarkdownHTML} />] : null}
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
	divisionDataIMap: Config.instanceOf(Map),
	fetchDivision: Config.func(),
	id: Config.oneOfType([Config.number(), Config.string()]),
	loading: Config.bool(),
	parentDivisionIMap: Config.object(),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func()
};

DivisionProfile.PROPS = {
	...STORE,
	router: Config.object()
};

DivisionProfile.STATE = {
	error_: Config.bool().value(false)
};

export default connect(
	(state, {router}) => {
		const divisionsIMap = state.get('divisions', Map());

		const id = getId(divisionsIMap, router.params.entityId);

		const divisionIMap = divisionsIMap.get(id, Map());

		const divisionDataIMap = divisionIMap.get('data', Map());

		const parentDivisionIMap = divisionIMap.getIn(['network', 'parentDivision'], Map());

		return {
			divisionDataIMap,
			id,
			loading: divisionIMap.get('loading', true),
			parentDivisionIMap: getRel('divisions', state, parentDivisionIMap.get('data', -1), Map())
		};
	},
	{
		addAlert,
		clearFeed,
		fetchDivision,
		setActiveTabIndex,
		setPageTitle
	}
)(DivisionProfile);