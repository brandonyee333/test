import {bindAll} from 'lodash';
import JSXComponent from 'metal-jsx';

class TextAreaInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleOnBlur',
			'_handleOnChange'
		);
	}

	_handleOnBlur(event) {
		const {value} = event.target;

		const {inputId, onBlur} = this.props;

		if (onBlur) {
			onBlur(value, inputId);
		}
	}

	render() {
		const {autoFocus, disabled, value} = this.props;

		return (
			<textarea
				autoFocus={autoFocus}
				class="watson-input"
				data-onblur={this._handleOnBlur}
				data-oninput={this._handleOnChange}
				disabled={disabled}
				value={value}
			/>
		);
	}

	_handleOnChange(event) {
		const {value} = event.target;

		const {inputId, onChange} = this.props;

		onChange(value, inputId);
	}
}

export default TextAreaInput;