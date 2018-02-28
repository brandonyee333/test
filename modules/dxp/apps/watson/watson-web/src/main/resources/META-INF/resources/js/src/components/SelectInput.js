import {bindAll, forEach, isEmpty, isEqual} from 'lodash';
import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import Select from 'react-select';

const MetalSelect = bridge(Select);

class SelectInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'_formatOptions',
			'_handleOnChange'
		);
	}

	_formatOptions(options, renderedOptions, sortOptions) {
		if (!isEmpty(options)) {
			forEach(
				options,
				entry => {
					const {label, value} = entry;

					renderedOptions.push(
						{
							label,
							value: value || entry
						}
					);
				}
			);

			if (sortOptions === 'alpha') {
				renderedOptions.sort((a, b) => a.label.localeCompare(b.label));
			}
			else if (sortOptions === 'numerical') {
				renderedOptions.sort(
					(a, b) => a.value.localeCompare(
						b.value,
						{},
						{ignorePunctuation: true, numeric: true}
					)
				);
			}
		}

		this.setState(
			{
				formattedOptions: renderedOptions,
				givenOptions: options
			}
		);
	}

	_handleOnChange(option) {
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

		const {
			formattedOptions,
			givenOptions
		} = this.state;

		let renderedOptions = [];

		if (!omitBlankOption) {
			renderedOptions.push(
				{
					label: '',
					value: ''
				}
			);
		}

		renderedOptions = isEqual(givenOptions, options) ? formattedOptions : this._formatOptions(options, renderedOptions, sortOptions);

		return (
			<div class={`select-wrapper ${cssClassName}`}>
				<MetalSelect
					autoFocus={autoFocus}
					disabled={disabled}
					isLoading={optionsLoading}
					onChange={this._handleOnChange}
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
	sortOptions: Config.string().value(''),
	tooltipLabel: Config.string(),
	value: Config.any()
};

SelectInput.STATE = {
	formattedOptions: Config.array(),
	givenOptions: Config.object()
};

export default SelectInput;