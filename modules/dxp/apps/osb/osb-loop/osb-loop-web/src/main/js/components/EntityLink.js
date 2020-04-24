import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {fromJS} from 'immutable';

import ExternalLink from './ExternalLink';
import LoopConstants from '../lib/loop-constants';
import Overlay from './Overlay';
import {overlayTypes} from '../actions/overlays';

class EntityLink extends Component {
	render() {
		const {
			disabled,
			entity,
			externalLink,
			showTrigger,
			summary
		} = this.props;

		const {
			displayURL,
			entityClassNameId,
			inactive,
			name = ''
		} = entity;

		let trigger = '';

		if (showTrigger && entityClassNameId === LoopConstants.classNameIds.topics) {
			trigger = '#';
		}

		const classes = getCN(
			'entity-link-container',
			{
				disabled,
				inactive
			}
		);

		let content = trigger + name;

		const linkObj = {
			...this.otherProps(),
			class: classes,
			href: disabled || !displayURL ? 'javascript:;' : displayURL
		};

		if (externalLink) {
			content = (
				<ExternalLink {...linkObj}>
					{content}
				</ExternalLink>
			);
		}
		else {
			content = (
				<a {...linkObj}>
					{content}
				</a>
			);
		}

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

EntityLink.PROPS = {
	disabled: Config.bool().value(false),
	entity: Config.object().value({}),
	externalLink: Config.bool().value(false),
	showTrigger: Config.bool().value(true),
	summary: Config.bool().value(false)
};

export default EntityLink;