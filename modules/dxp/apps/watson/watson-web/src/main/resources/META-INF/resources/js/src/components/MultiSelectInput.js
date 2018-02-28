import {bindAll} from 'lodash';
import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import Select from 'react-select';

const MetalMultiSelect = bridge(Select);

class MultiSelectInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleOnChange'
		);
	}

	_handleOnChange(values) {
		const {props} = this;

		const formattedOptions = [...(new Set(values.split(',')))];

		props.onChange(formattedOptions, props.inputId);
	}

	render() {
		const {
			disabled,
			options,
			optionsLoading,
			value
		} = this.props;

		const renderedOptions = [];

		if (options) {
			for (const entry in options) {
				if (options.hasOwnProperty(entry)) {
					const {label, value} = options[entry];

					renderedOptions.push(
						{
							label,
							value
						}
					);
				}
			}
		}

		const renderedValues = [];

		if (value) {
			value.forEach(
				entry => {
					renderedValues.push(entry.toString());
				}
			);
		}

		return (
			<div class="multi-select-wrapper">
				<MetalMultiSelect
					backspaceRemoves={false}
					deleteRemoves={false}
					delimiter=","
					disabled={disabled}
					isLoading={optionsLoading}
					multi={true}
					onChange={this._handleOnChange}
					options={renderedOptions}
					placeholder={Liferay.Language.get('select')}
					simpleValue={true}
					value={renderedValues}
				/>
			</div>
		);
	}
}

MultiSelectInput.PROPS = {
	disabled: Config.bool(),
	omitBlankOption: Config.bool(),
	optionsLoading: Config.bool(),
	tooltipLabel: Config.string(),
	value: Config.any
};

export default MultiSelectInput;