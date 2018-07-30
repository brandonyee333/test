import {bindAll, isNumber} from 'lodash';
import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import moment from 'moment';
import {SingleDatePicker} from 'react-dates';

const MetalDateInput = bridge(SingleDatePicker);

const outsideRangeBypass = () => false;

class DateInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleOnChange',
			'onFocusChange'
		);
	}

	_handleOnChange(date) {
		const {props} = this;

		const dateState = date ? date.format('YYYY-MM-DD') : null;

		this.setState({dateState});

		if (props.value !== dateState) {
			props.onChange(dateState);
		}
	}

	onFocusChange({focused}) {
		this.setState({focused});
	}

	render() {
		const {disabled, required, value} = this.props;

		const {focused} = this.state;

		let {dateState} = this.state;

		if (!dateState && value) {
			dateState = isNumber(value) ? value : Date.parse(value);
		}

		return (
			<div class="date-wrapper">
				<MetalDateInput
					date={dateState ? moment(dateState) : null}
					disabled={disabled}
					displayFormat="DD-MM-YYYY"
					elementClasses="watson-input"
					focused={focused}
					isOutsideRange={outsideRangeBypass}
					numberOfMonths={1}
					onDateChange={this._handleOnChange}
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
	dateState: Config.any(),
	focused: Config.bool()
};

export default DateInput;