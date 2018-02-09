import {bindAll, isEmpty} from 'lodash';
import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import Select from 'react-select';

const MetalSelect = bridge(Select);

class SelectInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnChange'
		);
	}

	_formatOptions(options, renderedOptions, sortOptions) {
		for (const entry in options) {
			if (options.hasOwnProperty(entry)) {
				const {label, value} = options[entry];
				renderedOptions.push(
					{
						label,
						value: value || entry
					}
				);
			}

			if (sortOptions) {
				renderedOptions.sort((a, b) => a.label.localeCompare(b.label));
			}
		}

		return renderedOptions;
	}

	handleOnChange(option) {
		const {props} = this;

		if (props.onChange) {
			props.onChange(option.value, props.inputId);
		}
	}

	render() {
		const {
			autoFocus,
			cssClassName = '',
			disabled,
			omitBlankOption,
			options,
			optionsLoading,
			placeHolder = Liferay.Language.get('select'),
			sortOptions = false,
			value = ''
		} = this.props;

		let renderedOptions = [];

		if (!omitBlankOption) {
			renderedOptions.push(
				{
					label: '',
					value: ''
				}
			);
		}

		renderedOptions = isEmpty(options) ? renderedOptions : this._formatOptions(options, renderedOptions, sortOptions);

		return (
			<div class={`select-wrapper ${cssClassName}`}>
				<MetalSelect
					autoFocus={autoFocus}
					disabled={disabled}
					isLoading={optionsLoading}
					onChange={this.handleOnChange}
					options={renderedOptions}
					placeholder={placeHolder}
					value={value ? value.toString() : ''}
				/>
			</div>
		);
	}
}

SelectInput.PROPS = {
	autoFocus: Config.bool(),
	cssClassName: Config.string().value(''),
	disabled: Config.bool(),
	omitBlankOption: Config.bool(),
	optionsLoading: Config.bool(),
	placeHolder: Config.string(),
	sortOptions: Config.bool(),
	tooltipLabel: Config.string(),
	value: Config.any()
};

export default SelectInput;