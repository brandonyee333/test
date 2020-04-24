import getCN from 'classnames';
import Component, {Config} from 'metal-jsx';
import {fromJS} from 'immutable';

import ConditionalLink from './ConditionalLink';
import Icon from './Icon';
import LoopConstants from '../lib/loop-constants';
import Overlay from './Overlay';
import {overlayTypes} from '../actions/overlays';

import {
	getIconName,
	getInitials,
	getLocationSubtypeName,
	getPlaceholderColorIndex
} from '../lib/util';

const {types} = LoopConstants;

const AVATAR_SIZES = [
	'large',
	'largest',
	'small',
	'smallest',
	'tiny'
];

const ICON_SIZE_MAP = {
	large: {
		multiplier: 2,
		size: 'small'
	},
	largest: {
		multiplier: 3
	},
	small: {
		multiplier: 1,
		size: 'large'
	},
	smallest: {
		multiplier: 1,
		size: 'small'
	},
	tiny: {
		size: 'tiny'
	}
};

class Avatar extends Component {
	render() {
		const {
			avatarClasses,
			disableLink,
			entity,
			externalLink,
			invertedColor,
			size,
			summary
		} = this.props;

		const {
			displayURL,
			entityClassPK,
			inactive,
			name,
			profileImageData = {},
			subtype,
			type
		} = entity;

		const profileImageURL = profileImageData.imageURL_web;

		let {index} = this.props;

		if (!index) {
			index = getPlaceholderColorIndex(entityClassPK);
		}

		const styles = !profileImageURL ? null : {
			backgroundImage: `url(${profileImageURL})`
		};

		const classes = getCN(
			'avatar',
			avatarClasses,
			{
				[`avatar-color-${index}`]: index && !profileImageURL,
				[`avatar-size-${size}`]: size,
				disabled: inactive,
				inverted: invertedColor
			}
		);

		let content = (
			<div class="avatar-container">
				<ConditionalLink
					elementClasses={classes}
					externalLink={externalLink}
					href={displayURL}
					style={styles}
					title={name}
					useSpan={disableLink}
				>
					{!profileImageURL &&
						<AvatarPlaceholder
							name={name}
							size={size}
							subtype={subtype}
							type={type}
						/>
					}
				</ConditionalLink>
			</div>
		);

		if (summary) {
			content = (
				<Overlay
					delay={600}
					hideOnClick={true}
					offset={12}
					overlayProps={{entityIMap: fromJS(entity), externalLink}}
					overlayType={overlayTypes.ENTITY_SUMMARY}
				>
					{content}
				</Overlay>
			);
		}

		return content;
	}
}

Avatar.PROPS = {
	avatarClasses: Config.string(),
	disableLink: Config.bool(),
	entity: Config.object(),
	externalLink: Config.bool().value(false),
	index: Config.number(),
	invertedColor: Config.bool(),
	size: Config.oneOf(AVATAR_SIZES).value('small'),
	summary: Config.bool().value(false)
};

export default Avatar;

class AvatarPlaceholder extends Component {
	render() {
		const {
			name,
			size,
			subtype,
			type
		} = this.props;

		let iconType = type;

		if (subtype && type === types.location) {
			iconType = getLocationSubtypeName(subtype);
		}

		let placeholder;

		if (type === types.person) {
			placeholder = getInitials(name);
		}
		else {
			placeholder = <Icon {...ICON_SIZE_MAP[size]} name={getIconName(iconType)} />;
		}

		return (
			<div class="avatar-placeholder">
				{placeholder}
			</div>
		);
	};
}

AvatarPlaceholder.PROPS = {
	name: Config.string(),
	size: Config.oneOf(AVATAR_SIZES),
	subtype: Config.number(),
	type: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	)
};