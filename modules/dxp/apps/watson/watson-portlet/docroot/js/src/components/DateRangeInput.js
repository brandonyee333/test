import {bindAll} from 'lodash';
import bridge from 'metal-react';
import {DateRangePicker} from 'react-dates';
import JSXComponent, {Config} from 'metal-jsx';

const MetalDateRangeInput = bridge(DateRangePicker);

class DateRangeInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnChange',
			'onDatesChange',
			'onFocusChange'
		);
	}

	handleOnChange(dateRangeString) {
		const {inputId, onChange} = this.props;

		onChange(dateRangeString, inputId);
	}

	onDatesChange({startDate, endDate}) {
		this.setState({endDate, startDate});
	}

	onFocusChange(focusedInput) {
		this.setState({focusedInput});
	}

	render() {
		const {disabled} = this.props;

		const {
			endDate,
			focusedInput,
			startDate
		} = this.state;

		const outsideRangeBypass = () => false;

		return (
			<div class="date-range-wrapper">
				<MetalDateRangeInput
					className="watson-input"
					disabled={disabled}
					endDate={endDate}
					endDatePlaceholderText={Liferay.Language.get('end-date')}
					focusedInput={focusedInput}
					isOutsideRange={outsideRangeBypass}
					numberOfMonths={1}
					onDatesChange={this.onDatesChange}
					onFocusChange={this.onFocusChange}
					orientation="vertical"
					reopenPickerOnClearDates={true}
					showKeyboardShortcuts={false}
					startDate={startDate}
					startDatePlaceholderText={Liferay.Language.get('start-date')}
				/>
			</div>
		);
	}

	rendered() {
		const {endDate, startDate} = this.state;

		if (endDate && startDate) {
			const endDateMillis = endDate._d.getTime();

			const startDateMillis = startDate._d.getTime();

			const dateRangeString = `${startDateMillis}-${endDateMillis}`;

			if (this.props.value !== dateRangeString) {
				this.handleOnChange(dateRangeString);
			}
		}
	}
}

DateRangeInput.Props = {
	disabled: Config.bool(),
	value: Config.string().value('')
};

DateRangeInput.STATE = {
	endDate: Config.any,
	focusedInput: Config.any,
	startDate: Config.any
};

export default DateRangeInput;