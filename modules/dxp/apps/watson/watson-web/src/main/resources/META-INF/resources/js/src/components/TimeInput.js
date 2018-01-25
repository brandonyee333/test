import {bindAll, toNumber} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import SelectInput from './SelectInput';

class TimeInput extends JSXComponent {
	attached() {
		this.setHoursOptions();
		this.setMinutesOptions();
	}

	created() {
		bindAll(
			this,
			'setHoursOptions',
			'setMinutesOptions',
			'handleHourOnChange',
			'handleMinuteOnChange',
			'handleOnChange'
		);
	}

	handleMinuteOnChange(minutes) {
		this.setState({minutesValue: toNumber(minutes)});

		this.handleOnChange();
	}

	handleHourOnChange(hours) {
		this.setState({hoursValue: toNumber(hours)});

		this.handleOnChange();
	}

	handleOnChange() {
		const {disabled, onChange} = this.props;

		if (!disabled) {
			const {hoursValue, minutesValue} = this.state;

			const timeValue = (hoursValue * 60) + minutesValue;

			onChange(toNumber(timeValue));
		}
	}

	render() {
		const {disabled} = this.props;

		const {hoursOptions, hoursValue, minutesOptions, minutesValue} = this.state;

		return (
			<div class="time-input-wrapper">
				<SelectInput
					disabled={disabled}
					onChange={this.handleHourOnChange}
					options={hoursOptions}
					placeHolder={Liferay.Language.get('hours')}
					value={hoursValue}
				/>

				<SelectInput
					disabled={disabled}
					onChange={this.handleMinuteOnChange}
					options={minutesOptions}
					placeHolder={Liferay.Language.get('minutes')}
					value={minutesValue}
				/>
			</div>
		);
	}

	rendered() {
		this.refreshData();
	}

	refreshData() {
		const {value} = this.props;

		const propsValue = toNumber(value);

		const formattedHoursValue = Math.floor(propsValue / 60);
		const formattedMinutesValue = propsValue % 60;

		const {hoursValue, minutesValue} = this.state;

		if (formattedHoursValue !== hoursValue) {
			this.setState(
				{
					hoursValue: formattedHoursValue
				}
			);
		}

		if (formattedMinutesValue !== minutesValue) {
			this.setState(
				{
					minutesValue: formattedMinutesValue
				}
			);
		}
	}

	setHoursOptions() {
		const hoursOptions = {};

		for (let hour = 1; hour < 101; hour++) {
			hoursOptions[hour] = {
				label: `${hour} ${Liferay.Language.get('hours')}`,
				value: hour.toString()
			};
		}

		this.setState({hoursOptions});
	}

	setMinutesOptions() {
		const minutesOptions = {};

		for (let minute = 0; minute < 60; minute++) {
			minutesOptions[minute] = {
				label: `${minute} ${Liferay.Language.get('minutes')}`,
				value: minute.toString()
			};
		}

		this.setState({minutesOptions});
	}
}

TimeInput.Props = {
	disabled: Config.bool(),
	onChange: Config.func(),
	value: Config.any()
};

TimeInput.STATE = {
	hoursOptions: Config.object(),
	hoursValue: Config.number().value(0),
	minutesOptions: Config.object(),
	minutesValue: Config.number().value(0)
};

export default TimeInput;