import Component, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';
import {bindAll, cloneDeep, debounce} from 'lodash';

import ConfirmMask from '../ConfirmMask';
import LoopConstants from '../../lib/loop-constants';
import SettingToggle from './SettingToggle';
import sendRequest from '../../lib/request';

const NOTIFICATIONS_CONTROLLER = 'notifications';

const {
	email: TYPE_EMAIL,
	inApp: TYPE_IN_APP
} = LoopConstants.notificationTypes;

const TYPE_MAP = {
	[TYPE_EMAIL]: 'email',
	[TYPE_IN_APP]: 'inApp'
};

class SettingsHead extends Component {
	render() {
		return (
			<tr>
				<th class="category-head left">{this.props.label}</th>
				<th class="category-head">{Liferay.Language.get('in-app')}</th>
				<th class="category-head">{Liferay.Language.get('email')}</th>
			</tr>
		);
	}
}

SettingsHead.PROPS = {
	label: Config.string()
};

const EMAIL_REGEX_FILTER = /liked/;

const INAPP_REGEX_FILTER = /mentioned/;

class SettingsRow extends Component {
	render() {
		const {
			label,
			onChange,
			settingKey,
			values: {email, inApp}
		} = this.props;

		const disableEmail = EMAIL_REGEX_FILTER.test(settingKey);
		const disableInApp = INAPP_REGEX_FILTER.test(settingKey);

		return (
			<tr>
				<th class="left">{label}</th>

				<td>
					<SettingToggle
						{...this.otherProps()}
						disabled={disableInApp}
						onChange={onChange}
						settingKey={settingKey}
						type={TYPE_IN_APP}
						value={inApp}
					/>
				</td>

				<td>
					<Transition name="transition-scale-in-out">
						{inApp &&
							<SettingToggle
								disabled={disableEmail}
								disabledTitle={Liferay.Language.get('you-cannot-turn-on-this-option')}
								onChange={onChange}
								settingKey={settingKey}
								type={TYPE_EMAIL}
								value={email}
							/>
						}
					</Transition>
				</td>
			</tr>
		);
	}
}

SettingsRow.PROPS = {
	label: Config.string(),
	onChange: Config.func(),
	settingKey: Config.string(),
	values: Config.object()
};

export class Settings extends Component {
	render() {
		const {
			categories,
			confirmActive,
			onConfirm,
			onConfirmCancel,
			settings,
			toggleWarning
		} = this.props;

		return (
			<div class="notification-settings-container">
				<ConfirmMask
					active={confirmActive}
					elementClasses="settings"
					message={Liferay.Language.get('this-change-will-apply-to-all-existing-notification-settings-for-this-category')}
					onCancel={onConfirmCancel}
					onConfirm={onConfirm}
				>
					<table>
						<tbody>
							{
								categories.map(
									category => (
										[
											<SettingsHead key={category.label} label={category.label} />,
											{
												...category.settings.map(
													({key, label}) => (
														<SettingsRow
															key={key}
															label={label}
															onChange={toggleWarning}
															settingKey={key}
															values={settings[key]}
														/>
													)
												)
											}
										]
									)
								)
							}
						</tbody>
					</table>
				</ConfirmMask>
			</div>
		);
	}
}

Settings.PROPS = {
	categories: Config.array().value([]),
	confirmActive: Config.bool().value(false),
	onConfirm: Config.func().value(null),
	onConfirmCancel: Config.func(),
	settings: Config.object().value({}),
	toggleWarning: Config.func()
};

class SettingsContainer extends Component {
	created() {
		bindAll(
			this,
			'onConfirmCancel_',
			'saveSetting_',
			'setSettingsState_',
			'toggleSetting_',
			'toggleWarning_'
		);

		this.toggleWarning_ = debounce(
			this.toggleWarning_,
			400,
			{
				leading: true,
				trailing: false
			}
		);

		this.request_ = sendRequest(
			{
				controller: NOTIFICATIONS_CONTROLLER,
				controllerMethod: 'viewSettings.json'
			}
		).then(this.setSettingsState_);
	}

	disposed() {
		if (this.request_) {
			this.request_.cancel();
		}

		if (this.saveRequest_) {
			this.saveRequest_.cancel();
		}
	}

	onConfirmCancel_() {
		this.state.confirmActive_ = false;
	}

	saveSetting_(key, value, type) {
		const data = {
			key,
			value
		};

		if (type) {
			data.type = type;
		}

		this.saveRequest_ = sendRequest(
			{
				controller: NOTIFICATIONS_CONTROLLER,
				controllerMethod: 'saveSetting.json',
				data
			}
		).then(this.setSettingsState_);

		return this.saveRequest_;
	}

	setSettingsState_(response) {
		const {categories, showWarning} = response;

		const settings = {};

		categories.forEach(
			category => {
				category.settings.forEach(
					({key, values}) => {
						settings[key] = values;
					}
				);
			}
		);

		this.setState(
			{
				categories_: categories,
				settings_: settings,
				showWarning_: showWarning
			}
		);
	}

	toggleSetting_(key, value, type) {
		const currentValue = this.state.settings_;

		const nextValue = cloneDeep(currentValue);

		nextValue[key][TYPE_MAP[type]] = value;

		this.setState(
			{
				confirmActive_: false,
				settings_: nextValue

			}
		);

		return this.saveSetting_(key, value, type).catch(
			() => {
				this.state.settings_ = currentValue;
			}
		);
	}

	toggleWarning_(key, value, type) {
		if (this.state.showWarning_) {
			this.setState(
				{
					confirmActive_: true,
					onConfirm_: disableWarning => this.toggleSetting_(key, value, type).then(
						response => {
							return disableWarning ? this.saveSetting_('showWarning', false) : response;
						}
					)
				}
			);
		}
		else {
			this.toggleSetting_(key, value, type);
		}
	}

	render() {
		const {
			categories_,
			confirmActive_,
			onConfirm_,
			settings_
		} = this.state;

		return (
			<Settings
				categories={categories_}
				confirmActive={confirmActive_}
				onConfirm={onConfirm_}
				onConfirmCancel={this.onConfirmCancel_}
				settings={settings_}
				toggleWarning={this.toggleWarning_}
			/>
		);
	}
}

SettingsContainer.STATE = {
	categories_: Config.array().value([]),
	confirmActive_: Config.bool().value(false),
	onConfirm_: Config.func().value(null),
	settings_: Config.object().value({}),
	showWarning_: Config.bool().value(true)
};

export default SettingsContainer;