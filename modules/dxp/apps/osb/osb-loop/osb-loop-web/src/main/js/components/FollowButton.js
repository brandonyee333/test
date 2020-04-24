import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {connect} from 'metal-redux';
import {noop} from 'lodash';

import Button from './Button';
import ExternalLink from './ExternalLink';
import Icon from './Icon';
import IconLabel from './IconLabel';
import Overlay from './Overlay';
import {addAlert, alertTypes} from '../actions/alerts';
import {alignmentPositions, overlayTypes} from '../actions/overlays';
import {classNameIdToSchema} from '../lib/util';
import {follow} from '../actions/following';
import {lang} from '../lib/lang-util';

class FollowButton extends Component {
	created() {
		this.handleClick = this.handleClick.bind(this);
	}

	handleClick() {
		const {
			addAlert,
			classNameId,
			displayURL,
			follow,
			following,
			followingName,
			id
		} = this.props;

		if (!following) {
			follow(classNameId, id, true).then(
				() => addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(Liferay.Language.get('you-just-followed-x'), [<ExternalLink href={displayURL} key={0}>{followingName}</ExternalLink>])
					}
				)
			).catch(noop);
		}
	}

	render() {
		const {
			classNameId,
			display,
			following,
			followMenuAlignment,
			id
		} = this.props;

		const label = following ? Liferay.Language.get('following') : Liferay.Language.get('follow');

		const followButton = (
			<Button
				display="secondary"
				elementClasses={getCN('follow-button-container', display, {following})}
				onClick={this.handleClick}
				role="following"
			>
				{display === 'mini' &&
					<Icon name="star" size="small" title={label} />
				}

				{display === 'regular' &&
					<IconLabel label={label} name="star" />
				}

				{following &&
					<Icon elementClasses="dropdown-arrow" name="arrow-down-short" size="small" />
				}
			</Button>
		);

		return (
			<Overlay
				alignment={followMenuAlignment}
				containerClass="follow-menu-container"
				disabled={!following}
				offset={12}
				overlayProps={{
					...this.otherProps(),
					classNameId,
					id
				}}
				overlayType={overlayTypes.FOLLOW_MENU}
			>
				{followButton}
			</Overlay>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	follow: Config.func(),
	following: Config.bool()
};

FollowButton.PROPS = {
	...STORE,
	classNameId: Config.number(),
	display: Config.oneOf(
		['mini', 'regular']
	).value('regular'),
	displayURL: Config.string(),
	followingName: Config.string(),
	followMenuAlignment: Config.number().value(alignmentPositions.BOTTOM),
	id: Config.number()
};

export default connect(
	(state, {classNameId, id}) => (
		{
			following: state.getIn([classNameIdToSchema(classNameId), id, 'data', 'following'])
		}
	),
	{
		addAlert,
		follow
	}
)(FollowButton);