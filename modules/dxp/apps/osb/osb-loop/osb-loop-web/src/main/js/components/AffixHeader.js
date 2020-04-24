import Component, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';

import Avatar from './Avatar';
import FollowButton from './FollowButton';
import LoopConstants from '../lib/loop-constants';
import {getTypeLabel} from '../lib/lang-util';
import {isCurrentPerson} from '../lib/util';

class AffixHeader extends Component {
	created() {
		this.getEntityDetails_ = this.getEntityDetails_.bind(this);
	}

	getEntityDetails_() {
		const {
			entityClassNameId,
			jobTitle,
			locationName,
			type
		} = this.props.entity;

		return entityClassNameId === LoopConstants.classNameIds.people ? `${jobTitle} ${jobTitle && locationName ? '•' : ''} ${locationName}` : getTypeLabel(type);
	}

	render() {
		const {active, entity} = this.props;

		const {
			displayURL,
			entityClassNameId,
			entityClassPK,
			name
		} = entity;

		return (
			<Transition elementClasses="affix-header-container" name="transition-affix-header-slide-down">
				{active &&
					<div class="content-wrapper">
						<div class="content">
							<div class="entity">
								<Avatar disableLink={true} entity={entity} size="smallest" />

								<div class="entity-info">
									<div class="name">
										{name}
									</div>

									<div class="details">
										{this.getEntityDetails_()}
									</div>
								</div>
							</div>

							{!isCurrentPerson(entityClassPK) &&
								<FollowButton
									classNameId={entityClassNameId}
									displayURL={displayURL}
									followingName={name}
									id={entityClassPK}
								/>
							}
						</div>
					</div>
				}
			</Transition>
		);
	}
}

AffixHeader.PROPS = {
	active: Config.bool(),
	entity: Config.object()
};

export default AffixHeader;