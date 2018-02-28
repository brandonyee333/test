import {bindAll, isEmpty} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Input from './Input';
import SelectInput from './SelectInput';
import Toggle from './Toggle';

class DoubleDependentSelectInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleOnChangeInputValue',
			'_handleOnChangeListType',
			'_handleOnChangePrimaryValue',
			'_handleUpdateValue'
		);
	}

	_handleOnChangeInputValue(textValue) {
		this._handleUpdateValue(textValue, 'value');
	}

	_handleOnChangeListType(selectedListType) {
		this._handleUpdateValue(selectedListType, 'watsonListTypeId');
	}

	_handleOnChangePrimaryValue(primary) {
		this._handleUpdateValue(primary, 'primary');
	}

	_handleUpdateValue(option, key) {
		const {
			props: {
				inputId,
				onChange,
				value
			}
		} = this;

		const newValue = {};

		if (!value) {
			newValue[key] = option;

			onChange(newValue, inputId);

		}
		else {
			value[key] = option;

			onChange(value, inputId, key);
		}
	}

	render() {
		const {currentInputConfig: {showToggle}, disabled, options} = this.props;

		const {inputValue, primary, watsonListTypeId} = this.state;

		const toggleLabel = Liferay.Language.get('primary');

		return (
			<div class="dynamic-double-select-group">
				<SelectInput
					disabled={disabled}
					onChange={this._handleOnChangeListType}
					options={options}
					value={watsonListTypeId}
				/>

				{watsonListTypeId &&
					<div class="input-and-toggle" >
						<Input
							disabled={disabled}
							onChange={this._handleOnChangeInputValue}
							value={inputValue}
						/>

						{showToggle &&
							<Toggle
								checked={primary}
								cssClass="primary"
								disabled={disabled}
								label={['', toggleLabel]}
								onChange={this._handleOnChangePrimaryValue}
							/>
						}
					</div>
				}
			</div>
		);
	}

	syncValue(newState) {
		if (newState) {
			const parsedValue = JSON.parse(JSON.stringify(newState));

			let inputValue = '';
			let primary = false;
			let watsonListTypeId = '';

			if (!isEmpty(parsedValue)) {
				inputValue = parsedValue.value || '';
				primary = parsedValue.primary || false;
				watsonListTypeId = parsedValue.watsonListTypeId || '';

				this.setState(
					{
						inputValue,
						primary,
						watsonListTypeId
					}
				);
			}
		}
	}
}

DoubleDependentSelectInput.SYNC_UPDATES = true;

DoubleDependentSelectInput.PROPS = {
	value: Config.value('{}')
};

DoubleDependentSelectInput.STATE = {
	inputValue: Config.string().value(''),
	primary: Config.bool().value(''),
	watsonListTypeId: Config.string().value('')
};

export default DoubleDependentSelectInput;