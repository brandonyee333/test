import {bindAll, capitalize, noop} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import SelectInput from './SelectInput';

import {indexActivities} from '../actions/activities';
import {indexAddresses} from '../actions/addresses';
import {indexPeople} from '../actions/people';
import {indexResources} from '../actions/resources';
import {indexVehicles} from '../actions/vehicles';

class TripleDependentSelectInput extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnChangeModel',
			'handleOnChangeModelId',
			'handleOnChangeTypeId',
			'handleUpdateValue'
		);
	}

	getIndexModelOptions(selectedModel2Data, omitClassPK) {
		const options = {};

		selectedModel2Data.forEach(
			model => {
				const id = model.get('id');

				if (id !== omitClassPK) {
					options[id] = {
						label: model.get('name') || id,
						value: id
					};
				}
			}
		);

		return options;
	}

	handleOnChangeModel(selectedModel2) {
		let classNameId2 = '';

		if (selectedModel2) {
			classNameId2 = WatsonConstants.inputConfig[this.state.selectedModel1].relationshipObjectOptions[selectedModel2].key;
		}

		if (this.props.tripleOnly) {
			const formattedValue = {};

			formattedValue.selectedModel2 = selectedModel2;
			formattedValue.classNameId2 = classNameId2;

			this.handleUpdateValue(selectedModel2, 'selectedModel2', formattedValue);
		}
		else {
			this.handleUpdateValue(classNameId2, 'classNameId2');
			this.handleUpdateValue(selectedModel2, 'selectedModel2');
		}

		this.refreshData(selectedModel2, false);
	}

	handleOnChangeModelId(classPK2) {
		this.handleUpdateValue(classPK2, 'classPK2');
	}

	handleOnChangeTypeId(typeWatsonListTypeId) {
		this.handleUpdateValue(typeWatsonListTypeId, 'typeWatsonListTypeId');
	}

	handleUpdateValue(option, key, formattedValue) {
		this.props.onChange(option, key, formattedValue);
	}

	refreshData(selectedModel2, forceRefresh) {
		const {props} = this;

		const {selectedModel2: oldSelectedModel2} = this.state;

		if (selectedModel2 !== oldSelectedModel2 || forceRefresh) {
			const modelName = `index${capitalize(selectedModel2)}`;

			const indexModel = props[modelName];

			if (indexModel) {
				indexModel(props.watsonIncidentId);
			}

			this.setState({selectedModel2});
		}
	}

	render() {
		const {disabled, omitClassPK, selectedModel2Data} = this.props;

		const {classPK2, selectedModel1, selectedModel2, typeWatsonListTypeId} = this.state;

		let relationshipTypeOptions = '';
		let selectedModelOptions = '';

		if (selectedModel1) {
			selectedModelOptions = WatsonConstants.inputConfig[selectedModel1].relationshipObjectOptions;

			if (selectedModel2) {
				relationshipTypeOptions = WatsonConstants.inputConfig[selectedModel1].relationshipObjectOptions[selectedModel2].options;
			}
		}

		let generatedInput;

		if (classPK2 && selectedModel1 && selectedModel2 && typeWatsonListTypeId) {
			const selectedModelLabel = `(${WatsonConstants.inputConfig[selectedModel2].singularLabel})`;
			const watsonModel = selectedModel2Data.get(classPK2);

			let name = '';
			let typeWatsonListTypeIdLabel = '';
			let watsonIncidentId = '';

			if (watsonModel) {
				name = watsonModel.get('name');
				watsonIncidentId = watsonModel.get('watsonIncidentId');
			}

			if (relationshipTypeOptions) {
				const typeWatsonListType = relationshipTypeOptions[typeWatsonListTypeId];

				if (typeWatsonListType) {
					typeWatsonListTypeIdLabel = typeWatsonListType.label;
				}
			}

			const incidentLink = watsonIncidentId ? () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${selectedModel2}/${classPK2}/edit`) : noop;

			generatedInput = (
				<table class="dynamic-relationship-generator-view">

					<a class="input-value" onClick={incidentLink} style="cursor: pointer">
						<td class="one">{name} </td>
						<td class="two">{selectedModelLabel} </td>
						<td class="three">{typeWatsonListTypeIdLabel} </td>
					</a>
				</table>
			);
		}
		else {
			generatedInput = (
				<div class="dynamic-triple-select-group-input">
					<SelectInput
						cssClassName="narrow"
						disabled={disabled}
						onChange={this.handleOnChangeModel}
						options={selectedModelOptions}
						value={selectedModel2}
					/>

					{selectedModel1 &&
						<SelectInput
							cssClassName="wide"
							disabled={disabled}
							onChange={this.handleOnChangeModelId}
							options={this.getIndexModelOptions(selectedModel2Data, omitClassPK)}
							value={classPK2}
						/>
					}

					{selectedModel1 &&
						<SelectInput
							cssClassName="narrow"
							disabled={disabled}
							onChange={this.handleOnChangeTypeId}
							options={relationshipTypeOptions}
							value={typeWatsonListTypeId}
						/>
					}
				</div>
			);
		}

		return generatedInput;
	}

	rendered() {
		const {loading, selectedModel2Data} = this.props;

		const {classPK2, classPKArray, selectedModel2} = this.state;

		if (!loading && selectedModel2 && classPK2 && !selectedModel2Data.get(classPK2) && !classPKArray.includes(classPK2)) {
			this.refreshData(selectedModel2, true);

			classPKArray.push(classPK2);

			this.setState({classPKArray});
		}
	}

	syncValue(newState, oldState) {
		if (newState && newState !== oldState) {
			const {classPK2, selectedModel1, selectedModel2, typeWatsonListTypeId} = newState;

			this.setState(
				{
					classPK2,
					selectedModel1,
					selectedModel2,
					typeWatsonListTypeId
				}
			);
		}
	}
}

TripleDependentSelectInput.PROPS = {
	selectedModel2Data: Config.value(new Map()),
	value: Config.object().value({})
};

TripleDependentSelectInput.STATE = {
	classNameId2: Config.string().value(''),
	classPK2: Config.string().value(''),
	classPKArray: Config.array().value([]),
	selectedModel1: Config.string().value(''),
	selectedModel2: Config.string().value(''),
	typeWatsonListTypeId: Config.string().value('')
};

function mapStateToProps(state, props) {
	const {value = {}} = props;

	const {selectedModel2} = value;

	let loading;

	let selectedModel2Data = new Map();

	if (selectedModel2) {
		loading = state.getIn([selectedModel2, 'loading']);
		selectedModel2Data = state.getIn([selectedModel2, 'data']) || new Map();
	}

	return {
		loading,
		selectedModel2Data
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

export default connect(mapStateToProps, mapDispatchToProps)(TripleDependentSelectInput);