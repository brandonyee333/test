import Component, {Config} from 'metal-jsx';
import {bindAll, range, uniqueId} from 'lodash';

import Button from '../Button';
import Input from './Input';
import Label from './Label';
import Select from './Select';

class MultiInput extends Component {
	created() {
		this._id = uniqueId('formMultiInput');

		bindAll(
			this,
			'handleDecrement_',
			'handleIncrement_'
		);

		const {initialValue} = this.props;

		if (initialValue && initialValue.length) {
			this.setState(
				{
					inputCount_: initialValue.length,
					value_: initialValue
				}
			);
		}
	}

	handleDecrement_(event) {
		event.preventDefault();

		this.state.inputCount_--;
	}

	handleIncrement_(event) {
		event.preventDefault();

		this.state.inputCount_++;
	}

	render() {
		const {
			props: {
				label,
				selector,
				types,
				typeSelector
			},
			state: {inputCount_, value_}
		} = this;

		return (
			<div class="multi-input-container">
				{label &&
					<Label for={this._id} text={label} />
				}

				{
					range(inputCount_).map(
						index => {
							const item = value_[index];

							return (
								<span key={index}>
									<Input
										{...this.otherProps()}
										initialValue={item ? item[selector] : ''}
										name={`${this.props.name}${index}`}
										type="text"
									/>

									{types && !!types.length &&
										<Select initialValue={item ? item[typeSelector] : ''} name={`${this.props.name}Type${index}`}>
											{
												types.map(
													({label, value}) => (
														<option key={value} selected={item && item[typeSelector] === value} value={value}>{label}</option>
													)
												)
											}
										</Select>
									}
								</span>
							);
						}
					)
				}

				<Button onClick={this.handleIncrement_} role="primary">{'+'}</Button>

				{inputCount_ > 1 &&
					<Button onClick={this.handleDecrement_} role="cancel">{'-'}</Button>
				}
			</div>
		);
	}
}

MultiInput.PROPS = {
	initialValue: Config.array(),
	label: Config.string(),
	selector: Config.string().required(),
	types: Config.array(),
	typeSelector: Config.string()
};

MultiInput.STATE = {
	inputCount_: Config.value(1),
	value_: Config.value([])
};

export default MultiInput;