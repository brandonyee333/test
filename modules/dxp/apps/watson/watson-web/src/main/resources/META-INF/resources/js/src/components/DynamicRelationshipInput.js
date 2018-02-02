import {bindAll, isEmpty, noop} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import SelectInput from './SelectInput';
import TripleDependentSelectInput from './TripleDependentSelectInput';

import {indexActivities} from '../actions/activities';
import {indexAddresses} from '../actions/addresses';
import {indexPeople} from '../actions/people';
import {indexResources} from '../actions/resources';
import {indexVehicles} from '../actions/vehicles';

import {formatModelName} from '../lib/util';

class DynamicRelationshipInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnChangeModel',
			'handleOnChangeModelId',
			'handleUpdateValue'
		);
	}

	getIndexModelOptions(selectedModelData) {
		const options = {};

		selectedModelData.forEach(
			model => {
				const id = model.get('id');

				options[id] = {
					label: model.get('name') || id,
					value: id
				};
			}
		);

		return options;
	}

	handleOnChangeModel(selectedModel1) {
		let classNameId1 = '';

		if (selectedModel1) {
			classNameId1 = WatsonConstants.inputConfig.relationships.relationshipObjectOptions[selectedModel1].key;
		}

		const formattedValue = {};

		formattedValue.classNameId1 = classNameId1;
		formattedValue.selectedModel1 = selectedModel1;

		this.handleUpdateValue(selectedModel1, 'selectedModel1', formattedValue);

		this.refreshData(selectedModel1, false);
	}

	handleOnChangeModelId(classPK1) {
		this.handleUpdateValue(classPK1, 'classPK1');
	}

	handleUpdateValue(option, key, formattedValue) {
		const {
			props: {
				inputId,
				onChange,
				value = {}
			}
		} = this;

		if (formattedValue) {
			onChange(formattedValue, inputId);
		}
		else {
			value[key] = option;

			onChange(value, inputId);
		}
	}

	refreshData(selectedModel1, forceRefresh) {
		const {props} = this;

		const {selectedModel1: oldSelectedModel} = this.state;

		if (selectedModel1 !== oldSelectedModel || forceRefresh) {
			const modelName = `index${formatModelName(selectedModel1, true)}`;

			const indexModel = props[modelName];

			if (indexModel) {
				indexModel(props.watsonIncidentId);
			}

			this.setState({selectedModel1});
		}
	}

	render() {
		const {disabled, model, selectedModelData, tripleOnly, watsonIncidentId, watsonPrimaryKey} = this.props;

		const {classPK1, classPK2, selectedModel2, typeWatsonListTypeId} = this.state;

		let {selectedModel1} = this.state;

		if (tripleOnly && !selectedModel1) {
			selectedModel1 = model;
		}

		const values = {classPK2, selectedModel1, selectedModel2, typeWatsonListTypeId};

		let generatedInput;

		if (!tripleOnly && selectedModel1 && classPK1 && selectedModel2 && classPK2 && typeWatsonListTypeId) {
			let incidentLink = '';
			let name = '';
			let selectedModel1Label = '';
			let watsonIncidentId = '';

			if (!tripleOnly) {
				selectedModel1Label = `(${WatsonConstants.inputConfig[selectedModel1].singularLabel})`;

				const watsonModel = selectedModelData.get(classPK1);

				if (watsonModel) {
					name = watsonModel.get('name');
					watsonIncidentId = watsonModel.get('watsonIncidentId');
				}

				incidentLink = watsonIncidentId ? () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${selectedModel1}/${classPK1}/edit`) : noop;
			}

			generatedInput = (
				<div class="dynamic-relationship-generator-input">
					{!tripleOnly &&
						<table class="dynamic-relationship-generator-view">
							<a class="input-value" onClick={incidentLink} style="cursor: pointer">
								<td class="one">{name} </td>
								<td class="two">{selectedModel1Label} </td>
							</a>

							<td class="relationship-arrow fixed" />
						</table>
					}

					<TripleDependentSelectInput
						omitClassPK={watsonPrimaryKey || classPK1}
						onChange={this.handleUpdateValue}
						tripleOnly={tripleOnly}
						value={values}
						watsonIncidentId={watsonIncidentId}
					/>
				</div>
			);
		}
		else {
			generatedInput = (
				<div class="dynamic-relationship-generator-input">
					<div class="dynamic-double-select-input">
						{!tripleOnly &&
							<SelectInput
								cssClassName="narrow"
								disabled={disabled}
								onChange={this.handleOnChangeModel}
								options={WatsonConstants.inputConfig.relationships.relationshipObjectOptions}
								value={selectedModel1}
							/>
						}

						{!tripleOnly &&
							<SelectInput
								cssClassName="wide"
								disabled={disabled}
								onChange={this.handleOnChangeModelId}
								options={this.getIndexModelOptions(selectedModelData)}
								value={classPK1}
							/>
						}
					</div>

					{!tripleOnly &&
						<td class="relationship-arrow" />
					}

					<TripleDependentSelectInput
						omitClassPK={watsonPrimaryKey || classPK1}
						onChange={this.handleUpdateValue}
						tripleOnly={tripleOnly}
						value={values}
						watsonIncidentId={watsonIncidentId}
					/>
				</div>
			);
		}

		return generatedInput;
	}

	rendered() {
		const {loading, selectedModelData} = this.props;

		const {classPK1, classPKArray, selectedModel1} = this.state;

		if (!loading && selectedModel1 && classPK1 && !selectedModelData.get(classPK1) && !classPKArray.includes(classPK1)) {
			this.refreshData(selectedModel1, true);

			classPKArray.push(classPK1);

			this.setState({classPKArray});
		}
	}

	syncValue(newState) {
		if (newState) {
			const parsedValue = JSON.parse(JSON.stringify(newState));

			let classPK1 = '';
			let classPK2 = '';
			let selectedModel1 = '';
			let selectedModel2 = '';
			let typeWatsonListTypeId = '';

			if (!isEmpty(parsedValue)) {
				classPK1 = parsedValue.classPK1 || '';
				classPK2 = parsedValue.classPK2 || '';
				selectedModel1 = parsedValue.selectedModel1 || '';
				selectedModel2 = parsedValue.selectedModel2 || '';
				typeWatsonListTypeId = parsedValue.typeWatsonListTypeId || '';

				this.setState(
					{
						classPK1,
						classPK2,
						selectedModel1,
						selectedModel2,
						typeWatsonListTypeId
					}
				);
			}
		}
	}
}

DynamicRelationshipInput.PROPS = {
	value: Config.value('{}')
};

DynamicRelationshipInput.STATE = {
	classNameId1: Config.string().value(''),
	classNameId2: Config.string().value(''),
	classPK1: Config.string().value(''),
	classPK2: Config.string().value(''),
	classPKArray: Config.array().value([]),
	selectedModel1: Config.string().value(''),
	selectedModel2: Config.string().value(''),
	typeWatsonListTypeId: Config.string().value('')
};

function mapStateToProps(state, props) {
	const {value = {}} = props;

	let selectedModel1 = '';

	if (!isEmpty(value)) {
		selectedModel1 = value.selectedModel1;
	}

	let loading;

	let selectedModelData = new Map();

	if (selectedModel1) {
		loading = state.getIn([selectedModel1, 'loading']);
		selectedModelData = state.getIn([selectedModel1, 'data']) || new Map();
	}

	return {
		loading,
		selectedModelData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		indexActivities: id => {
			dispatch(
				indexActivities({id})
			);
		},
		indexAddresses: id => {
			dispatch(
				indexAddresses({id})
			);
		},
		indexPeople: id => {
			dispatch(
				indexPeople({id})
			);
		},
		indexResources: id => {
			dispatch(
				indexResources({id})
			);
		},
		indexVehicles: id => {
			dispatch(
				indexVehicles({id})
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(DynamicRelationshipInput);