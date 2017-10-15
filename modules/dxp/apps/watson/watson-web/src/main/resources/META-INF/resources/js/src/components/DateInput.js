import {bindAll, isNumber} from 'lodash';
import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import moment from 'moment';
import {SingleDatePicker} from 'react-dates';

const MetalDateInput = bridge(SingleDatePicker);

class DateInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnChange',
			'onFocusChange'
		);
	}

	handleOnChange(date) {
		const {props} = this;

		const dateInMillis = date ? date._d.getTime() : null;

		this.setState({dateInMillis});

		if (props.value !== dateInMillis) {
			props.onChange(dateInMillis);
		}
	}

	onFocusChange({focused}) {
		this.setState({focused});
	}

	render() {
		const {disabled, required, value} = this.props;

		const {focused} = this.state;

		let {dateInMillis} = this.state;

		if (!dateInMillis && value) {
			dateInMillis = isNumber(value) ? value : Date.parse(value);
		}

		const outsideRangeBypass = () => false;

		return (
			<div class="date-wrapper">
				<MetalDateInput
					date={dateInMillis ? moment(dateInMillis) : null}
					disabled={disabled}
					elementClasses="watson-input"
					focused={focused}
					isOutsideRange={outsideRangeBypass}
					numberOfMonths={1}
					onDateChange={this.handleOnChange}
					onFocusChange={this.onFocusChange}
					orientation="vertical"
					placeholder={Liferay.Language.get('pick-date')}
					required={required}
					showClearDate={true}
				/>
			</div>
		);
	}
}

DateInput.Props = {
	disabled: Config.bool(),
	onChange: Config.func(),
	required: Config.bool(),
	value: Config.any()
};

DateInput.STATE = {
	dateInMillis: Config.any(),
	focused: Config.bool()
};

export default DateInput;