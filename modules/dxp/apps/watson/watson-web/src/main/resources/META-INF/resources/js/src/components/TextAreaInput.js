import {bindAll} from 'lodash';
import JSXComponent from 'metal-jsx';

class TextAreaInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnBlur',
			'handleOnChange'
		);
	}

	handleOnBlur(event) {
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
				data-onblur={this.handleOnBlur}
				data-oninput={this.handleOnChange}
				disabled={disabled}
				value={value}
			/>
		);
	}

	handleOnChange(event) {
		const {value} = event.target;

		const {inputId, onChange} = this.props;

		onChange(value, inputId);
	}
}

export default TextAreaInput;