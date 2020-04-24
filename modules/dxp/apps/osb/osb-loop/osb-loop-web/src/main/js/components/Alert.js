import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {Map} from 'immutable';

import Icon from './Icon';
import Spinner from './Spinner';
import {alertTypes} from '../actions/alerts';

const ALERT_DISPLAYS = {
	[alertTypes.ALERT]: {
		display: 'warning',
		icon: 'alert'
	},
	[alertTypes.DEFAULT]: {
		display: 'default',
		icon: 'alert'
	},
	[alertTypes.ERROR]: {
		display: 'danger',
		icon: 'close-short'
	},
	[alertTypes.SUCCESS]: {
		display: 'success',
		icon: 'check'
	}
};

class Alert extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		const {alertIMap, onRemoveAlert} = this.props;

		onRemoveAlert(alertIMap.get('id'));
	}

	render() {
		const {alertIMap} = this.props;

		const alertType = alertIMap.get('alertType');
		const displayType = ALERT_DISPLAYS[alertType];

		const alertClass = getCN(
			'transition-wrapper',
			{
				active: ALERT_DISPLAYS[alertType]
			}
		);

		const pendingAlert = alertType === alertTypes.PENDING;

		const message = alertIMap.get('message');

		return (
			<div class="alert-container">
				<span class="icon-wrapper">
					<div class={alertClass}>
						{!pendingAlert &&
							<Icon
								display={displayType.display}
								invertedColor={true}
								name={displayType.icon}
								size="small"
							/>
						}
					</div>

					{pendingAlert &&
						<Spinner key="spinner" />
					}
				</span>

				<span>
					{message.toJS ? message.toJS() : message}
				</span>

				<span class="dismiss" data-tooltip title={Liferay.Language.get('close')}>
					<Icon name="cancel" onClick={this.handleClick_} />
				</span>
			</div>
		);
	}
}

Alert.PROPS = {
	alertIMap: Config.instanceOf(Map),
	onRemoveAlert: Config.func()
};

export default Alert;