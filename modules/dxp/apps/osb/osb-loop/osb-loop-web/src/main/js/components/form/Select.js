import getCN from 'classnames';
import {noop, uniqueId} from 'lodash';

import FormComponent, {Config} from './FormComponent';
import Label from './Label';
import validateValue from '../../lib/form-validators';

class Select extends FormComponent {
	created() {
		this._id = uniqueId('formSelect');

		this.handleChange_ = this.handleChange_.bind(this);

		const {initialValue} = this.props;

		if (initialValue) {
			this.state.value_ = initialValue;
		}
	}

	handleChange_({target}) {
		const {value} = target;

		this.state.value_ = value;

		this.props.onChange(value);

		this.validateFormElement();
	}

	isRequired_() {
		const {validator} = this.props;

		return validator && validator === 'required';
	}

	validateFormElement() {
		const {validator} = this.props;

		let retVal = true;

		if (validator) {
			if (this._validateRequest) {
				this._validateRequest.cancel();
			}

			this._validateRequest = validateValue(this.state.value_, validator);

			retVal = this._validateRequest.then(
				result => {
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
		const {
			props: {children, label, showBlankOption},
			state: {message_, show_, valid_}
		} = this;

		let status = 'default';

		if (show_) {
			status = valid_ ? 'success' : 'error';
		}

		return (
			<div class={getCN('form-select-container', status)}>
				{label &&
					<Label for={this._id} required={this.isRequired_()} text={label} />
				}

				<select
					{...this.otherProps()}
					class={getCN(status)}
					id={this._id}
					onChange={this.handleChange_}
				>
					{showBlankOption &&
						<option selected />
					}

					{children}
				</select>

				{show_ && !valid_ &&
					<div class="error-message">{message_}</div>
				}
			</div>
		);
	}
}

Select.PROPS = {
	initialValue: Config.any(),
	label: Config.string(),
	onChange: Config.func().value(noop),
	showBlankOption: Config.bool().value(true),
	validator: Config.string()
};

Select.STATE = {
	message_: Config.value(''),
	show_: Config.value(false),
	valid_: Config.value(true),
	value_: Config.value('')
};

export default Select;