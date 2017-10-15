import {bindAll, isEmpty, noop} from 'lodash';
import {connect} from 'metal-redux';
import {List} from 'immutable';
import JSXComponent, {Config} from 'metal-jsx';

import Button from './Button';
import DateRangeInput from './DateRangeInput';
import DoubleDependentSelectInput from './DoubleDependentSelectInput';
import DynamicMicroFormModal from './DynamicMicroFormModal';
import DynamicRelationshipInput from './DynamicRelationshipInput';
import Input from './Input';
import SelectInput from './SelectInput';

class DynamicInputGenerator extends JSXComponent {
	addInput() {
		const {addedInputs = []} = this.state;

		this.setState(
			{
				addedInputs: addedInputs.push([null])
			}
		);
	}

	created() {
		bindAll(
			this,
			'addInput',
			'handleOnChange',
			'handleRemoveInput'
		);
	}

	generateInput(disabled, inputPath, value, index, depth, type) {
		const path = inputPath.slice(0, depth + 1);

		const {props, state} = this;

		const {primaryIndex} = state;

		let {inputConfig} = props;

		if (depth > 0) {
			inputConfig = this.getInputConfig(
				path.slice(0, depth)
			);
		}
		else if (type === 'filter') {
			inputConfig = this.removeUnfilterableOptions(inputConfig);
		}

		let generatedInput;

		if (inputConfig && type !== 'fancy') {
			const {htmlType, type: inputType} = inputConfig;
			const options = inputConfig.options || inputConfig;

			let last = true;

			if (options) {
				const childKey = Object.keys(options)[0];

				const childConfig = options[childKey];

				if (childConfig) {
					last = !childConfig.type;
				}
			}

			const config = {
				disabled,
				inputId: {
					index,
					last,
					path
				},
				onChange: this.handleOnChange,
				value
			};

			const {inputTypes: inputTypeConstants} = WatsonConstants.inputConfig;

			if ((depth === 0 && type === 'filter') || inputType === inputTypeConstants.selectInput || inputType === inputTypeConstants.multiSelectInput || inputType === inputTypeConstants.dependentSelectInput) {
				generatedInput = (
					<SelectInput
						{...config}
						options={options}
					/>
				);
			}
			else if ((depth === 0 && type === 'generator') || inputType === inputTypeConstants.dynamicInputGenerator) {
				if (primaryIndex >= 0) {
					config.value = Object.assign(
						config.value || {},
						{
							primary: index === primaryIndex
						}
					);
				}

				generatedInput = (
					<DoubleDependentSelectInput
						{...config}
						currentInputConfig={inputConfig}
						options={options}
					/>
				);
			}
			else if ((depth === 0 && type === 'simple') || inputType === inputTypeConstants.dynamicRelationshipInputGenerator) {
				const {tripleOnly} = props;

				generatedInput = (
					<DynamicRelationshipInput
						{...config}
						model={props.model}
						tripleOnly={tripleOnly}
						watsonIncidentId={props.watsonIncidentId}
						watsonPrimaryKey={props.watsonPrimaryKey}
					/>
				);
			}
			else if (inputConfig.inputType === inputTypeConstants.microForm) {
				generatedInput = (
					<DynamicMicroFormModal
						{...config}
						watsonIncidentId={props.watsonIncidentId}
						watsonPrimaryKey={props.watsonPrimaryKey}
					/>
				);
			}
			else if (inputType === inputTypeConstants.input && htmlType === 'date') {
				generatedInput = (
					<DateRangeInput {...config} />
				);
			}
			else {
				generatedInput = (
					<Input {...config} htmlType={htmlType} />
				);
			}
		}
		else if (type === 'fancy') {
			value = value.toString();

			let name = '';
			let watsonIncidentId = '';
			let watsonListTypeLabel = '';

			const {
				formData: {
					watsonIncidentRels = {}
				}
			} = props;

			if (watsonIncidentRels[value]) {
				const incidentData = watsonIncidentRels[value];

				name = incidentData.name;
				watsonIncidentId = incidentData.watsonIncidentId;

				const {typeWatsonListTypeId} = incidentData;

				if (inputConfig && typeWatsonListTypeId > 0) {
					const watsonListType = inputConfig.typeWatsonListTypeId.options[typeWatsonListTypeId];

					if (watsonListType) {
						watsonListTypeLabel = watsonListType.label;
					}
				}
			}
			else {
				const {incidents} = props;

				const watsonModel = incidents.get(value);

				if (watsonModel) {
					name = watsonModel.get('name');
					watsonIncidentId = watsonModel.get('watsonIncidentId');

					const watsonListTypeId = watsonModel.get('typeWatsonListTypeId');

					if (inputConfig && watsonListTypeId > 0) {
						const watsonListType = inputConfig.typeWatsonListTypeId.options[watsonListTypeId];

						if (watsonListType) {
							watsonListTypeLabel = watsonListType.label;
						}
					}
				}
			}

			if (!name) {
				name = Liferay.Language.get('this-change-will-only-be-shown-after-you-refresh-the-page');
				watsonIncidentId = value;
			}

			const incidentLink = watsonIncidentId ? `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit` : undefined;

			generatedInput = (
				<a class="input-value" data-senna-off="true" href={incidentLink} target="_blank">
					<span class="one">{name} </span>
					<span class="two">{watsonListTypeLabel} </span>
				</a>
			);
		}

		return generatedInput;
	}

	getInputConfig(inputPath) {
		const {inputConfig: inputOptions} = this.props;

		return inputPath.reduce(
			(prevValue, nextValue) => {
				let options = {};

				if (prevValue) {
					options = prevValue.options || prevValue;
				}

				return options[nextValue];
			},
			inputOptions
		);
	}

	handleOnChange(value, data, key) {
		const {addedInputs: prevAddedInputs} = this.state;

		const {index, last, path} = data;

		path[path.length - 1] = value;

		const resetValue = !last && value !== path;

		let {primaryIndex} = this.state;

		if (key && key === 'primary' && value) {
			primaryIndex = index;
		}

		if (!last) {
			path.push(null);
		}

		const addedInputs = prevAddedInputs.set(
			index,
			path
		);

		this.setState({addedInputs, primaryIndex});

		if (last || resetValue) {
			const {inputId, onChange, type} = this.props;

			const key = type === 'simple' || type === 'generator';

			onChange(addedInputs, inputId, key);
		}
	}

	handleRemoveInput(event) {
		const target = event.target;

		const index = target.dataset.index;

		const addedInputs = this.state.addedInputs.delete(index);

		this.setState(
			{
				addedInputs
			}
		);

		this.props.onChange(addedInputs, this.props.inputId);
	}

	removeUnfilterableOptions(options) {
		const filterableOptions = {};

		for (const key in options) {
			if (options.hasOwnProperty(key)) {
				const option = options[key];

				const {filterable} = option;

				if (filterable !== false) {
					filterableOptions[key] = option;
				}
			}
		}

		return filterableOptions;
	}

	render() {
		const {addedInputs} = this.state;

		const {disabled, label, type = 'filter', value} = this.props;

		let {onClick = this.addInput} = this.props;

		onClick = disabled ? noop : onClick;

		const renderedInputs = addedInputs.reduce(
			(prev, inputPath, index) => {
				const inputGroup = inputPath.map(
					(value, depth) => this.generateInput(disabled, inputPath, value, index, depth, type)
				);

				return prev.concat(
					<div class="dynamic-select-input-group">
						{!disabled &&
							<Button className="remove-button" data-index={index} onClick={this.handleRemoveInput} />
						}

						{inputGroup}
					</div>
				);
			},
			[]
		);

		return (
			<div class="dynamic-select-input">
				<div class="input-container">
					{renderedInputs}
				</div>

				{!disabled &&
					<Button className="primary" label={label} onClick={onClick} />
				}

				{disabled && isEmpty(value) &&
					<div class="input-value">{Liferay.Language.get('none')}</div>
				}
			</div>
		);
	}

	syncFilter(newState) {
		if (newState) {
			let {addedInputs} = this.state;

			if (addedInputs.isEmpty() && newState) {
				addedInputs = newState;
			}

			this.setState({addedInputs});
		}
	}

	syncInputConfig(newState, oldState) {
		if (JSON.stringify(newState) !== JSON.stringify(oldState)) {
			this.setState(
				{
					addedInputs: new List(),
					inputConfig: newState
				}
			);
		}
	}

	syncValue(newState) {
		if (newState) {
			let {addedInputs, primaryIndex} = this.state;

			newState.forEach(
				(entry, index) => {
					if (entry && entry.primary) {
						primaryIndex = index;
					}

					if (!addedInputs.get(index)) {
						addedInputs = addedInputs.set(index, [entry]);
					}
				}
			);

			this.setState({addedInputs, primaryIndex});
		}
	}
}

DynamicInputGenerator.PROPS = {
	filter: Config.value(new List()),
	incidents: Config.value(new Map()),
	inputConfig: Config.object().value({}),
	value: Config.value(new List())
};

DynamicInputGenerator.STATE = {
	addedInputs: Config.value(new List()),
	primaryIndex: Config.number().value(0)
};

function mapStateToProps(state) {
	return {
		incidents: state.getIn(
			[
				'incidents',
				'data'
			]
		)
	};
}

export default connect(mapStateToProps)(DynamicInputGenerator);