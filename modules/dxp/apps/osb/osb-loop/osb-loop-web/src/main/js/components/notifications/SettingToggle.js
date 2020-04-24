import Component, {Config} from 'metal-jsx';

import Checkbox from '../Checkbox';
import LoopConstants from '../../lib/loop-constants';

const {
	email: TYPE_EMAIL,
	inApp: TYPE_IN_APP
} = LoopConstants.notificationTypes;

class SettingToggle extends Component {
	created() {
		const {type} = this.props;

		if (type === TYPE_IN_APP) {
			this._iconNameOff = 'bell-off';
			this._iconNameOn = 'bell';
		}
		else if (type === TYPE_EMAIL) {
			this._iconNameOff = 'mail-off';
			this._iconNameOn = 'mail-full';
		}

		this.handleChange_ = this.handleChange_.bind(this);
	}

	handleChange_() {
		const {
			onChange,
			settingKey,
			type,
			value
		} = this.props;

		onChange(settingKey, !value, type);
	}

	render() {
		const {
			disabled,
			disabledTitle,
			type,
			value
		} = this.props;

		let title;

		if (disabled) {
			title = disabledTitle;
		}
		else if (type === TYPE_IN_APP) {
			title = value ? Liferay.Language.get('turn-off') : Liferay.Language.get('turn-on');
		}
		else {
			title = value ? Liferay.Language.get('stop-email-notifications') : Liferay.Language.get('get-email-notifications');
		}

		return (
			<Checkbox
				{...this.otherProps()}
				checked={value}
				data-tooltip
				disabled={disabled}
				iconNameOff={this._iconNameOff}
				iconNameOn={this._iconNameOn}
				onChange={disabled ? null : this.handleChange_}
				title={title}
			/>
		);
	}
}

SettingToggle.PROPS = {
	disabled: Config.bool().value(false),
	disabledTitle: Config.string().value(Liferay.Language.get('you-cannot-turn-off-this-option')),
	onChange: Config.func(),
	settingKey: Config.string(),
	type: Config.oneOf(
		[
			TYPE_EMAIL,
			TYPE_IN_APP
		]
	),
	value: Config.bool()
};

export default SettingToggle;