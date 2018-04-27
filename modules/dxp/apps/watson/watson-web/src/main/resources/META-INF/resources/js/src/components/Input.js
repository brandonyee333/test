import {bindAll, debounce} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import DateInput from './DateInput';
import TimeInput from './TimeInput';
import Toggle from './Toggle';

class Input extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleOnBlur',
			'_handleOnChange',
			'_handleOnChangeValue'
		);

		this.debouncedOnChangeValue_ = debounce(this._handleOnChangeValue, 100);
	}

	detached() {
		this.debouncedOnChangeValue_.cancel();
	}

	_handleOnBlur(event) {
		const {inputId, onBlur} = this.props;

		const {value} = event.target;

		if (onBlur) {
			onBlur(value, inputId);
		}
	}

	_handleOnChange(value) {
		const {inputId, onChange} = this.props;

		if (onChange) {
			onChange(value, inputId);
		}
	}

	_handleOnChangeValue(event) {
		this._handleOnChange(event.target.value);
	}

	render() {
		const {
			autoFocus,
			disabled,
			htmlType,
			placeholder,
			value
		} = this.props;

		let retVal = {};

		if (htmlType === 'checkbox') {
			retVal = (
				<Toggle
					autoFocus={autoFocus}
					checked={value}
					cssClass="watson-input"
					disabled={disabled}
					label={[Liferay.Language.get('no'), Liferay.Language.get('yes')]}
					onChange={this._handleOnChange}
				/>
			);
		}
		else if (htmlType === 'date') {
			retVal = (
				<DateInput
					autoFocus={autoFocus}
					disabled={disabled}
					onChange={this._handleOnChange}
					value={value}
				/>
			);
		}
		else if (htmlType === 'time') {
			retVal = (
				<TimeInput
					autoFocus={autoFocus}
					cssClass="watson-input"
					disabled={disabled}
					onChange={this._handleOnChange}
					value={value}
				/>
			);
		}
		else {
			retVal = (
				<input
					{...this.otherProps()}
					autoFocus={autoFocus}
					class="watson-input"
					data-onblur={this._handleOnBlur}
					data-oninput={this.debouncedOnChangeValue_}
					disabled={disabled}
					placeholder={placeholder}
					type={htmlType}
					value={value}
				/>
			);
		}

		return retVal;
	}
}

Input.PROPS = {
	autoFocus: Config.bool(),
	disabled: Config.bool(),
	htmlType: Config.string().value('input'),
	onBlur: Config.func(),
	onChange: Config.func(),
	placeholder: Config.string(),
	tooltipLabel: Config.string(),
	value: Config.any()
};

export default Input;