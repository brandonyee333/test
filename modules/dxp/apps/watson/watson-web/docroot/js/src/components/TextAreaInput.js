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
		const value = event.target.value;

		if (this.props.onBlur) {
			this.props.onBlur(value, this.props.inputId);
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
		const value = event.target.value;

		this.props.onChange(value, this.props.inputId);
	}
}

export default TextAreaInput;