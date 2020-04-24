import getCN from 'classnames';
import {bindAll, uniqueId} from 'lodash';

import FormComponent, {Config} from './FormComponent';
import Label from './Label';
import validateValue from '../../lib/form-validators';

class FormTextarea extends FormComponent {
	created() {
		this._id = uniqueId('FormTextarea');

		const {initialValue} = this.props;

		if (initialValue) {
			this.state.value_ = initialValue;
		}

		bindAll(
			this,
			'handleInput_',
			'handleBlur_'
		);
	}

	handleBlur_(event) {
		this.validateFormElement_();
	}

	handleInput_(event) {
		if (this.state.show_) {
			this.validateFormElement_();
		}

		this.state.value_ = event.target.value;
	}

	isRequired_() {
		const {validator} = this.props;

		return validator && (validator === 'required' || Object.keys(validator).includes('required'));
	}

	validateFormElement_() {
		const {validator} = this.props;

		let retVal = true;

		if (validator) {
			if (this._validateRequest) {
				this._validateRequest.cancel();
			}

			this._validateRequest = validateValue(this.state.value_, validator);

			retVal = this._validateRequest.then(
				result => {
					if (result instanceof Array) {
						result = result.find(item => !item.valid);
					}

					const valid = result ? result.valid : true;

					this.setState(
						{
							message_: result ? result.message : '',
							show_: true,
							valid_: valid
						}
					);

					return valid;
				}
			);
		}

		return retVal;
	}

	syncInitialValue(initialValue) {
		if (initialValue) {
			this.state.value_ = initialValue;
		}
	}

	render() {
		const {label, name} = this.props;

		const {
			message_,
			show_,
			valid_,
			value_
		} = this.state;

		let status = 'default';

		if (show_) {
			status = valid_ ? 'success' : 'error';
		}

		return (
			<div class={getCN('form-textarea-container', status)}>
				{label &&
					<Label for={this._id} required={this.isRequired_()} text={label} />
				}

				<textarea
					{...this.otherProps()}
					id={this._id}
					name={name}
					onBlur={this.handleBlur_}
					onInput={this.handleInput_}
					value={value_}
				/>

				{show_ && !valid_ &&
					<div class="error-message">{message_}</div>
				}
			</div>
		);
	}
}

FormTextarea.PROPS = {
	initialValue: Config.string().value(''),
	label: Config.string(),
	validator: Config.oneOfType(
		[
			Config.string(),
			Config.object()
		]
	)
};

FormTextarea.STATE = {
	message_: Config.value(''),
	show_: Config.value(false),
	valid_: Config.value(true),
	value_: Config.value('')
};

export default FormTextarea;