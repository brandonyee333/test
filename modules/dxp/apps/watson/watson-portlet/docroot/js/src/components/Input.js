import {bindAll, debounce} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Toggle from './Toggle';

class Input extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnBlur',
			'handleOnChange',
			'handleOnChangeValue'
		);

		this.debouncedOnChangeValue_ = debounce(this.handleOnChangeValue, 100);
	}

	detached() {
		this.debouncedOnChangeValue_.cancel();
	}

	handleOnBlur(event) {
		const {inputId, onBlur} = this.props;

		const {value} = event.target;

		if (onBlur) {
			onBlur(value, inputId);
		}
	}

	handleOnChange(value) {
		const {inputId, onChange} = this.props;

		onChange(value, inputId);
	}

	handleOnChangeValue(event) {
		this.handleOnChange(event.target.value);
	}

	render() {
		const {autoFocus, disabled, htmlType, value} = this.props;

		let retVal = {};

		if (htmlType === 'checkbox') {
			retVal = (
				<Toggle
					autoFocus={autoFocus}
					checked={!!value}
					cssClass="watson-input"
					disabled={disabled}
					label={[Liferay.Language.get('false'), Liferay.Language.get('true')]}
					onChange={this.handleOnChange}
				/>
			);
		}
		else {
			retVal = (
				<input
					autoFocus={autoFocus}
					class="watson-input"
					data-onblur={this.handleOnBlur}
					data-oninput={this.debouncedOnChangeValue_}
					disabled={disabled}
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
	tooltipLabel: Config.string(),
	value: Config.any
};

export default Input;