import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {Map} from 'immutable';

import Avatar from './Avatar';
import EntityLink from './EntityLink';
import FollowButton from './FollowButton';
import IconLabel from './IconLabel';
import Icon from './Icon';
import LoopConstants from '../lib/loop-constants';
import ModalLink from './ModalLink';
import {alignmentPositions} from '../actions/overlays';
import {getIconName, getPlaceholderColorIndex, getTypeName, isCurrentPerson} from '../lib/util';
import {getPluralMessage, getTypeLabel, lang} from '../lib/lang-util';
import {modalTypes} from '../actions/modals';

const {classNameIds} = LoopConstants;

class EntitySummary extends Component {
	getChildContext() {
		const {onMouseInside, onMouseLeave} = this.props;

		return {
			onOverlayLeave: onMouseLeave,
			onOverlayOver: onMouseInside
		};
	}

	render() {
		const {entityIMap, externalLink} = this.props;

		const {
			coverImageData = {},
			displayURL,
			entityClassNameId,
			entityClassPK,
			followersCount,
			inactive,
			jobTitle,
			locationName,
			loopParticipantAssignmentsCount,
			loopTopicAssignmentsCount,
			name,
			type
		} = entityIMap.toJS();

		let coverImageStyles;

		if (!inactive) {
			coverImageStyles = {
				backgroundImage: `url(${coverImageData.imageURL_web})`
			};
		}

		return (
			<div class={getCN('entity-summary-container', {inactive})}>
				<div class="header">
					{!inactive &&
						<div
							class={
								getCN(
									'cover-image',
									`avatar-cover-image-color-${getPlaceholderColorIndex(entityClassPK)}`
								)
							}
							style={coverImageStyles}
						/>
					}

					{inactive &&
						<div class="inactive-alert">
							<IconLabel
								display="primary"
								label={lang(Liferay.Language.get('x-is-no-longer-active'), [name])}
								name="alert"
								size="small"
							/>
						</div>
					}

					<Avatar entity={entityIMap.toJS()} externalLink={externalLink} size="large" />
				</div>

				<div class="body">
					<EntityLink entity={entityIMap.toJS()} externalLink={externalLink} />

					<div class="entity-details secondary-info">
						{jobTitle &&
							<p>{jobTitle}</p>
						}

						{locationName &&
							<p>{locationName}</p>
						}

						{entityClassNameId !== classNameIds.people &&
							<div>
								<IconLabel
									display={inactive ? 'inactive' : null}
									label={getTypeLabel(type)}
									name={getIconName(type)}
									size="small"
								/>
							</div>
						}
					</div>

					<div class="entity-stats secondary-info">
						<IconLabel
							display={inactive ? 'inactive' : 'warning'}
							label={getPluralMessage(Liferay.Language.get('x-follower'), Liferay.Language.get('x-followers'), followersCount)}
							name="star"
							size="small"
						/>

						{entityClassNameId === classNameIds.people &&
							<IconLabel
								display={inactive ? 'inactive' : 'success'}
								label={getPluralMessage(Liferay.Language.get('x-group'), Liferay.Language.get('x-groups'), loopParticipantAssignmentsCount)}
								name="groups"
								size="small"
							/>
						}

						{entityClassNameId === classNameIds.people &&
							<IconLabel
								display={inactive ? 'inactive' : 'primary'}
								label={lang(Liferay.Language.get('x-expertise'), [loopTopicAssignmentsCount])}
								name="hash"
								size="small"
							/>
						}

						{entityClassNameId === classNameIds.divisions &&
							<IconLabel
								display={inactive ? 'inactive' : 'primary'}
								label={getPluralMessage(Liferay.Language.get('x-member'), Liferay.Language.get('x-members'), loopParticipantAssignmentsCount)}
								name="persons"
								size="small"
							/>
						}

						{entityClassNameId === classNameIds.topics &&
							<IconLabel
								display={inactive ? 'inactive' : 'primary'}
								label={getPluralMessage(Liferay.Language.get('x-expert'), Liferay.Language.get('x-experts'), loopTopicAssignmentsCount)}
								name="persons"
								size="small"
							/>
						}
					</div>
				</div>

				{!isCurrentPerson(entityClassPK) && !inactive &&
					<div class="footer">
						<FollowButton
							classNameId={entityClassNameId}
							displayURL={displayURL}
							followingName={name}
							followMenuAlignment={alignmentPositions.RIGHT}
							id={entityClassPK}
						/>

						{getTypeName(type) === 'department' && !externalLink &&
							<ModalLink
								config={{
									fullScreen: true,
									hideOnBlur: false,
									modalProps: {id: entityClassPK},
									modalType: modalTypes.ORG_CHART
								}}
								data-tooltip
								title={Liferay.Language.get('see-org-chart')}
							>
								<Icon name="org-chart" />
							</ModalLink>
						}
					</div>
				}
			</div>
		);
	}
}

EntitySummary.PROPS = {
	entityIMap: Config.instanceOf(Map),
	externalLink: Config.bool().value(false)
};

export default EntitySummary;