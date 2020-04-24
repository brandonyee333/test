import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List} from 'immutable';

import Card from './card';
import EntityIndexItem from './EntityIndexItem';
import InfiniteScroll from './InfiniteScroll';
import LoadingCard from './LoadingCard';
import LoadingWrapper from './LoadingWrapper';
import LoopConstants from '../lib/loop-constants';
import {getPluralMessage} from '../lib/lang-util';
import {modalTypes, showModal} from '../actions/modals';

const {classNameIds, types} = LoopConstants;

function getFollowersMessage(followersCount) {
	return getPluralMessage(Liferay.Language.get('x-follower'), Liferay.Language.get('x-followers'), followersCount, true);
}

function getSecondaryDetails(entity) {
	const {
		entityClassNameId,
		followersCount,
		jobTitle,
		locationName,
		loopParticipantAssignmentsCount
	} = entity;

	let retVal = [];

	if (entityClassNameId === classNameIds.people) {
		retVal = [jobTitle, locationName];
	}
	else if (entityClassNameId === classNameIds.topics) {
		retVal = [getFollowersMessage(followersCount)];
	}
	else if (entityClassNameId === classNameIds.divisions) {
		retVal = [`${getPluralMessage(Liferay.Language.get('x-member'), Liferay.Language.get('x-members'), loopParticipantAssignmentsCount, true)} • ${getFollowersMessage(followersCount)}`];
	}

	return retVal;
}

class IndexPagination extends Component {
	created() {
		this.handleOrgChatModal = this.handleOrgChatModal.bind(this);
	}

	getHoverButtonConfig(id) {
		return {
			iconName: 'org-chart',
			onClick: this.handleOrgChatModal(id),
			title: Liferay.Language.get('see-org-chart')
		};
	}

	handleOrgChatModal(id) {
		return () => {
			this.props.showModal(
				{
					fullScreen: true,
					hideOnBlur: false,
					modalProps: {id},
					modalType: modalTypes.ORG_CHART
				}
			);
		};
	}

	render() {
		const {
			card,
			entitiesIList,
			hasMoreResults,
			icon,
			infiniteScroll,
			label,
			loading,
			onScrollEnd
		} = this.props;

		let content = (
			<EntityIndexItem.Wrapper>
				{
					entitiesIList.toJS().map(
						entity => {
							const {entityClassPK, type} = entity;

							return (
								<EntityIndexItem
									entity={entity}
									key={entityClassPK}
									ref={entityClassPK}
									secondaryButtonConfig={type === types.department ? this.getHoverButtonConfig(entityClassPK) : null}
									secondaryDetails={getSecondaryDetails(entity)}
								/>
							);
						}
					)
				}

				{!loading && !entitiesIList.size &&
					<EntityIndexItem.NoResult icon={icon} message={Liferay.Language.get('none')} />
				}
			</EntityIndexItem.Wrapper>
		);

		if (infiniteScroll) {
			content = (
				<InfiniteScroll
					hasMoreResults={hasMoreResults}
					onScrollEnd={onScrollEnd}
					scrollOffset={2000}
				>
					{content}

					{loading &&
						<LoadingCard key="loadingCard" />
					}
				</InfiniteScroll>
			);
		}
		else {
			content = (
				<LoadingWrapper loading={loading} mask={true}>
					{content}
				</LoadingWrapper>
			);
		}

		if (card) {
			content = (
				<Card elementClasses="index-pagination-container">
					{label &&
						<div class="category-label">{label}</div>
					}

					{content}
				</Card>
			);
		}
		else {
			content = (
				<div class="index-pagination-container">
					{label &&
						<div class="category-label">{label}</div>
					}

					{content}
				</div>
			);
		}

		return content;
	}
}

IndexPagination.PROPS = {
	card: Config.bool().value(true),
	entitiesIList: Config.instanceOf(List),
	hasMoreResults: Config.bool(),
	icon: Config.string().value('person'),
	infiniteScroll: Config.bool().value(false),
	label: Config.string(),
	loading: Config.bool().value(true),
	onScrollEnd: Config.func()
};

export default connect(
	null,
	{
		showModal
	}
)(IndexPagination);