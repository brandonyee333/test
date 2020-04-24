import Component, {Config} from 'metal-jsx';

import Avatar from './Avatar';
import EntityLink from './EntityLink';
import FollowButton from './FollowButton';
import LoopConstants from '../lib/loop-constants';
import {getPluralMessage, getTypeLabel} from '../lib/lang-util';
import {isCurrentPerson} from '../lib/util';

const {classNameIds} = LoopConstants;

function getDivisionsMessage(type, memberCount) {
	return `${getTypeLabel(type)} • ${getPluralMessage(Liferay.Language.get('x-member'), Liferay.Language.get('x-members'), memberCount, true)}`;
}

class EntityDisplay extends Component {
	render() {
		const {
			disableLinks,
			entity,
			externalLink,
			inlineFollow,
			rightDisplay,
			size,
			summary
		} = this.props;

		const {
			displayURL,
			entityClassNameId,
			entityClassPK,
			followersCount,
			jobTitle,
			locationName,
			loopParticipantAssignmentsCount,
			name,
			type
		} = entity;

		let details;

		if (entityClassNameId === classNameIds.divisions) {
			details = getDivisionsMessage(type, loopParticipantAssignmentsCount);
		}
		else if (entityClassNameId === classNameIds.people) {
			details = jobTitle;

			if (size !== 'smallest') {
				const dot = jobTitle && locationName ? '• ' : '';

				details += ` ${dot}${locationName}`;
			}
		}
		else if (entityClassNameId === classNameIds.topics) {
			details = getPluralMessage(Liferay.Language.get('x-follower'), Liferay.Language.get('x-followers'), followersCount);
		}

		return (
			<div class="entity-display-container">
				<Avatar
					disableLink={disableLinks}
					entity={entity}
					externalLink={externalLink}
					size={size}
					summary={summary}
				/>

				<div class="entity-info">
					<div class="name">
						<EntityLink
							disabled={disableLinks}
							entity={entity}
							externalLink={externalLink}
							showTrigger={false}
							summary={summary}
						/>
					</div>

					<div class="details">
						{details}
					</div>
				</div>

				{!rightDisplay && inlineFollow && !isCurrentPerson(entityClassPK) &&
					<FollowButton
						classNameId={entityClassNameId}
						display="mini"
						displayURL={displayURL}
						followingName={name}
						id={entityClassPK}
					/>
				}

				{rightDisplay &&
					<div>
						{rightDisplay}
					</div>
				}
			</div>
		);
	}
}

EntityDisplay.PROPS = {
	disableLinks: Config.bool().value(false),
	entity: Config.object(),
	externalLink: Config.bool().value(false),
	inlineFollow: Config.bool().value(false),
	rightDisplay: Config.any(),
	size: Config.oneOf(['small', 'smallest', 'large']).value('small'),
	summary: Config.bool().value(true)
};

export default EntityDisplay;