import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, noop} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Checkbox from './Checkbox';
import ExternalLink from './ExternalLink';
import LoopConstants from '../lib/loop-constants';
import MenuList from './menu-list';
import RadioGroup from './radio-group';
import Toggle from './Toggle';
import {addAlert, alertTypes} from '../actions/alerts';
import {classNameIdToSchema} from '../lib/util';
import {lang} from '../lib/lang-util';
import {follow} from '../actions/following';
import {notify, notifyEmail} from '../actions/notifying';

const followingTypeConstants = LoopConstants.followingType;

class FollowMenu extends Component {
	created() {
		bindAll(
			this,
			'handleNotify_',
			'handleNotifyEmail_',
			'handleRadioChange_',
			'handleUnfollowClick_'
		);
	}

	getChildContext() {
		const {onMouseInside, onMouseLeave} = this.props;

		return {
			onOverlayLeave: onMouseLeave,
			onOverlayOver: onMouseInside
		};
	}

	handleNotify_() {
		const {
			classNameId,
			id,
			notify,
			notifyEmail,
			notifying,
			notifyingEmail
		} = this.props;

		notify(classNameId, id, !notifying);

		if (notifying && notifyingEmail) {
			notifyEmail(classNameId, id, false);
		}
	}

	handleNotifyEmail_() {
		const {
			classNameId,
			id,
			notifyEmail,
			notifyingEmail
		} = this.props;

		notifyEmail(classNameId, id, !notifyingEmail);
	}

	handleRadioChange_(value) {
		const {
			classNameId,
			follow,
			following,
			id
		} = this.props;

		follow(classNameId, id, following, value);
	}

	handleUnfollowClick_() {
		const {
			addAlert,
			classNameId,
			displayURL,
			follow,
			followingName,
			id
		} = this.props;

		follow(classNameId, id, false).then(
			() => {
				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(
							Liferay.Language.get('you-just-unfollowed-x'),
							[<ExternalLink href={displayURL} key={0}>{followingName}</ExternalLink>]
						)
					}
				);
			}
		).catch(noop);
	}

	render() {
		const {
			classNameId,
			followingName,
			followingType,
			notifying,
			notifyingEmail,
			showUnfollow
		} = this.props;

		const emailClassNames = getCN(
			'indent',
			'notify-email',
			{
				off: !notifying
			}
		);

		const {full, limited} = followingTypeConstants;

		return (
			<MenuList>
				{classNameId === LoopConstants.classNameIds.people &&
					<li>
						<ul class="stream-options">
							<MenuList.Label>{Liferay.Language.get('what-to-show-on-your-stream')}</MenuList.Label>

							<li>
								<RadioGroup checked={followingType} name="followRadio" onChange={this.handleRadioChange_}>
									<RadioGroup.Option label={Liferay.Language.get('posts-comments-and-mentions')} value={full} />

									<RadioGroup.Option label={Liferay.Language.get('only-posts')} value={limited} />
								</RadioGroup>
							</li>
						</ul>
					</li>
				}

				<MenuList.Label>{Liferay.Language.get('notification-settings')}</MenuList.Label>

				<MenuList.Item onClick={this.handleNotify_}>
					<Toggle checked={notifying} label={notifying ? Liferay.Language.get('turn-off-notifications') : Liferay.Language.get('turn-on-notifications')} readOnly={true} />
				</MenuList.Item>

				<MenuList.Item elementClasses={emailClassNames} onClick={this.handleNotifyEmail_}>
					<Checkbox checked={notifyingEmail} label={Liferay.Language.get('get-email-notifications')} readOnly={true} />
				</MenuList.Item>

				{showUnfollow &&
					<MenuList.Divider />
				}

				{showUnfollow &&
					<MenuList.Item elementClasses="unfollow" onClick={this.handleUnfollowClick_}>
						{lang(Liferay.Language.get('unfollow-x'), [followingName])}
					</MenuList.Item>
				}
			</MenuList>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	displayURL: Config.string(),
	follow: Config.func(),
	following: Config.bool(),
	followingName: Config.string(),
	followingType: Config.number(),
	notify: Config.func(),
	notifyEmail: Config.func(),
	notifying: Config.bool(),
	notifyingEmail: Config.bool()
};

FollowMenu.PROPS = {
	...STORE,
	classNameId: Config.number(),
	id: Config.number(),
	showUnfollow: Config.bool().value(true),
	trigger: Config.any()
};

FollowMenu.STATE = {
	open_: Config.value(false)
};

export default connect(
	(state, {classNameId, id}) => {
		const entityIMap = state.getIn([classNameIdToSchema(classNameId), id, 'data'], Map());

		return {
			displayURL: entityIMap.get('displayURL'),
			following: entityIMap.get('following'),
			followingName: entityIMap.get('name'),
			followingType: entityIMap.get('followingType'),
			notifying: entityIMap.get('notifying'),
			notifyingEmail: entityIMap.get('notifyingEmail')
		};
	},
	{
		addAlert,
		follow,
		notify,
		notifyEmail
	}
)(FollowMenu);