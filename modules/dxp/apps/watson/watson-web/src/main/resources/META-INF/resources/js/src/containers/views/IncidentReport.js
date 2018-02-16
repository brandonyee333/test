import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../../components/Button';
import HTMLRenderer from '../../components/HTMLRenderer';
import GoogleMap from '../../components/GoogleMap';

import {viewIncidents} from '../../actions/incidents';
import {indexActivities} from '../../actions/activities';
import {indexAddresses} from '../../actions/addresses';
import {indexPeople} from '../../actions/people';
import {indexResources} from '../../actions/resources';
import {indexVehicles} from '../../actions/vehicles';

import {getFormattedDate, getMimeType, updateDOMTitle} from '../../lib/util';

class IncidentReport extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonIncidentId} = props;

		if (watsonIncidentId) {
			props.indexActivities(watsonIncidentId);
			props.indexAddresses(watsonIncidentId);
			props.indexPeople(watsonIncidentId);
			props.indexResources(watsonIncidentId);
			props.indexVehicles(watsonIncidentId);
			props.viewIncidents(watsonIncidentId);
		}
	}

	created() {
		bindAll(
			this,
			'fetchModelData',
			'handleBack',
			'renderIncident',
			'renderIncidentHeader',
			'renderModel'
		);
	}

	disposed() {
		if (this.state.elementStyle) {
			const element = document.getElementById('print-helper-message');

			element.style = this.state.elementStyle;
		}
	}

	fetchModelData(classPK, model) {
		const {props} = this;

		let watsonModelObject = new Map();

		if (model && classPK > 0) {
			const modelLoading = props[`${model}Loading`];

			if (!modelLoading) {
				const modelData = props[`${model}Data`];

				if (modelData) {
					watsonModelObject = modelData.get(classPK);
				}
			}
		}

		return watsonModelObject;
	}

	handleBack() {
		const {entryId, model, watsonIncidentId} = this.props;

		if (model) {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${entryId}/edit`);
		}
		else {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit`);
		}
	}

	handlePrint() {
		window.print();
	}

	renderEntities(entityList, displayFields, model, entryId) {
		return (
			<div class="entity-detail">
				{entryId &&
					this.renderIncidentHeader()
				}

				{this.renderEntitiesHelper(entityList, displayFields, model, entryId)}
			</div>
		);
	}

	renderEntitiesHelper(entityList, displayFields, model, entryId) {
		const renderElement = [];

		entityList.forEach(
			(entity, key) => {
				if (entryId) {
					if (key === entryId) {
						renderElement.push(this.renderEntity(entity, displayFields, model));
					}
				}
				else {
					renderElement.push(this.renderEntity(entity, displayFields, model));
				}
			}
		);

		return renderElement;
	}

	renderEntity(data, displayFields, model) {
		const entityName = data.get('name') || data.get('id') || '';
		const entitySingularLabel = WatsonConstants.inputConfig[model].singularLabel;

		const headerName = entityName.includes(entitySingularLabel) ? entityName : `${WatsonConstants.inputConfig[model].singularLabel} - ${entityName}`;

		return (
			<div class="body">
				<div class="body-header">
					{headerName}
				</div>

				<div class="content">
					{this.renderFields(data, displayFields, model)}
				</div>
			</div>
		);
	}

	renderFields(data, displayFields, model) {
		const renderedFields = [];

		displayFields.forEach(
			fieldName => {
				const fieldConfig = WatsonConstants.inputConfig[model].inputs[fieldName];

				const {label} = fieldConfig;

				const value = data.get(fieldName);

				renderedFields.push(
					<div class="field">
						<div class="label">
							{label}
						</div>

						<div class="value">
							{this.renderValue(fieldConfig, value, fieldName, data)}
						</div>
					</div>
				);
			}
		);

		return renderedFields;
	}

	renderIncident() {
		const {currentIncidentData} = this.props;

		const {incidentsConfig: displayFields} = this.state;

		return (
			<div class="main-detail">
				{this.renderIncidentHeader()}

				<div class="body">
					<div class="body-header">
						{Liferay.Language.get('details')}
					</div>

					<div class="content">
						{this.renderFields(currentIncidentData, displayFields, 'incidents')}
					</div>
				</div>
			</div>
		);
	}

	renderIncidentHeader() {
		const {currentIncidentData} = this.props;

		return (
			<div class="header">
				<div class="logo" />

				<div class="title">
					{currentIncidentData.get('name') || Liferay.Language.get('loading')}
				</div>

				<div class="parent-subtitle">
					{currentIncidentData.get('typeLabel')}
				</div>

				<div class="child-subtitle">
					{sub(Liferay.Language.get('created-by-x-on-x'), currentIncidentData.get('reportedBy') || '', getFormattedDate(currentIncidentData.get('createDate')) || '')}
				</div>
			</div>
		);
	}

	renderModel(entryId, modelName) {
		const modelData = this.props[`${modelName}Data`];

		const displayFields = this.state[`${modelName}Config`];

		return (
			<div class={modelName}>
				{this.renderEntities(modelData, displayFields, modelName, entryId)}
			</div>
		);
	}

	renderValue(inputConfig, value, key, entity) {
		const {inputTypes} = WatsonConstants.inputConfig;

		const {type} = inputConfig;

		let retVal = '';

		if (!isEmpty(value)) {
			if (type === inputTypes.dependentSelectInput || type === inputTypes.selectInput) {
				if (inputConfig && inputConfig.options && inputConfig.options[value]) {
					retVal = inputConfig.options[value].label;
				}

				if (!retVal && key.endsWith('WatsonListTypeId')) {
					let newKey = key.substring(0, key.indexOf('WatsonListTypeId'));

					retVal = entity.get(newKey);

					if (!retVal) {
						newKey += 'Label';

						retVal = entity.get(newKey);
					}
				}
			}
			else if (type === inputTypes.dynamicInputGenerator || type === inputTypes.doubleDependentInput) {
				retVal = [];

				value.forEach(
					listTypeRel => {
						const listTypeObject = inputConfig.options[listTypeRel.get('watsonListTypeId')] || {};

						retVal.push(
							<div>
								{`${listTypeRel.get('value')} - ${listTypeObject.label}`}
							</div>
						);
					}
				);
			}
			else if (type === inputTypes.dynamicRelationshipInputGenerator) {
				retVal = [];

				value.forEach(
					watsonRelationship => {
						if (watsonRelationship) {
							const relatedModelClassName = watsonRelationship.get('selectedModel2');

							const watsonRelatedObject = this.fetchModelData(watsonRelationship.get('classPK2'), relatedModelClassName);

							const relatedObjectName = watsonRelatedObject.get('name') || watsonRelationship.get('classPK2');

							const watsonModelProperClassName = WatsonConstants.inputConfig[relatedModelClassName].singularLabel;

							retVal.push(
								<div>
									<span class="subtext">{`${watsonModelProperClassName} - ${relatedObjectName} - ${watsonRelationship.get('typeLabel')}`}</span>
								</div>
							);
						}
					}
				);
			}
			else if (type === inputTypes.file) {
				if (value.get('previewURL')) {
					if (getMimeType(value.get('mimeType')) !== 'FILE') {
						retVal = (<div class="image" style={`background-image: url(${value.get('previewURL')});`} />);
					}
					else {
						retVal = value.get('name');
					}
				}
			}
			else if (type === inputTypes.googleMap) {
				retVal = (
					<GoogleMap
						value={value}
					/>
				);
			}
			else if (type === inputTypes.input || type === inputTypes.dependentDateInput || type === inputTypes.dependentInput) {
				retVal = (<div class="input-value">{value}</div>);
			}
			else if (type === inputTypes.multiSelectInput) {
				value.forEach(
					selectedId => {
						retVal += `${inputConfig.options[selectedId].label}${'. '}`;
					}
				);
			}
			else if (type === inputTypes.richTextEditor || type === inputTypes.textareaInput) {
				if (type === inputTypes.textareaInput) {
					value = value.replace(/(?:\r\n|\r|\n)/g, '<br />');
				}

				retVal = (
					<HTMLRenderer
						content={value}
					/>
				);
			}
		}

		if (!retVal || isEmpty(retVal)) {
			retVal = Liferay.Language.get('none');
		}

		return retVal;
	}

	render() {
		const {entryId, model} = this.props;

		return (
			<div class="print-report page-container printable">
				<div class="top-buttons">
					<Button
						label={`< ${Liferay.Language.get('back')}`}
						onClick={this.handleBack}
					/>

					<Button
						icon="print-icon"
						onClick={this.handlePrint}
					/>
				</div>

				{!entryId &&
					this.renderIncident()
				}

				{(!entryId || model === 'addresses') &&
					this.renderModel(entryId, 'addresses')
				}

				{(!entryId || model === 'activities') &&
					this.renderModel(entryId, 'activities')
				}

				{(!entryId || model === 'people') &&
					this.renderModel(entryId, 'people')
				}

				{(!entryId || model === 'resources') &&
					this.renderModel(entryId, 'resources')
				}

				{(!entryId || model === 'vehicles') &&
					this.renderModel(entryId, 'vehicles')
				}
			</div>
		);
	}

	rendered(firstRender) {
		const {currentIncidentData} = this.props;

		const incidentName = currentIncidentData.get('name') || '';

		updateDOMTitle(sub(Liferay.Language.get('print-x'), incidentName));

		if (firstRender) {
			const element = document.getElementById('print-helper-message');

			if (element) {
				this.setState({elementStyle: element.style});

				element.style = 'display: none';
			}
		}
	}
}

IncidentReport.PROPS = {
	activitiesData: Config.value(new Map()),
	addressesData: Config.value(new Map()),
	currentIncidentData: Config.value(new Map()),
	entryId: Config.any(),
	incidentsData: Config.value(new Map()),
	model: Config.string(),
	peopleData: Config.value(new Map()),
	resourcesData: Config.value(new Map()),
	vehiclesData: Config.value(new Map()),
	watsonIncidentId: Config.string()
};

IncidentReport.STATE = {
	activitiesConfig: Config.array().value(
		[
			'reportDate',
			'startDate',
			'typeWatsonListTypeId',
			'subtypeWatsonListTypeId',
			'narrative',
			'watsonRelationships'
		]
	),
	addressesConfig: Config.array().value(
		[
			'name',
			'imagePayload',
			'typeWatsonListTypeId',
			'countryId',
			'provinceWatsonListTypeId',
			'districtWatsonListTypeId',
			'subDistrictWatsonListTypeId',
			'postalCode',
			'latitude',
			'longitude',
			'street',
			'number',
			'building',
			'floor',
			'room',
			'description',
			'watsonRelationships'
		]
	),
	elementStyle: Config.value(''),
	entryId: Config.any(),
	incidentsConfig: Config.array().value(
		[
			'incidentStatus',
			'sourceWatsonListTypeId',
			'typeWatsonListTypeId',
			'subtypeWatsonListTypeId',
			'audienceAdultCount',
			'audienceChildCount',
			'externalCaseId',
			'externalCaseWatsonListTypeId',
			'reportDate',
			'startDate',
			'endDate',
			'natureWatsonListType',
			'description'
		]
	),
	model: Config.string(),
	peopleConfig: Config.array().value(
		[
			'imagePayload',
			'typeWatsonListTypeId',
			'accepted',
			'dateAccepted',
			'primaryNameString',
			'nameWatsonListTypeRels',
			'birthDate',
			'startAge',
			'endAge',
			'sexWatsonListTypeId',
			'height',
			'weight',
			'hairWatsonListTypeId',
			'eyesWatsonListTypeId',
			'occupation',
			'countryWatsonListTypeId',
			'ethnicityWatsonListTypeId',
			'birthCountryId',
			'citizenshipWatsonListTypeId',
			'countryIDWatsonListTypeRels',
			'phoneNumberWatsonListTypeRels',
			'socialMediaAccountWatsonListTypeRels',
			'description',
			'watsonRelationships'
		]
	),
	resourcesConfig: Config.array().value(
		[
			'typeWatsonListTypeId',
			'name',
			'imagePayload',
			'description',
			'watsonRelationships'
		]
	),
	vehiclesConfig: Config.array().value(
		[
			'imagePayload',
			'typeWatsonListTypeId',
			'makeWatsonListTypeId',
			'modelWatsonListTypeId',
			'colorWatsonListTypeId',
			'yearWatsonListTypeId',
			'licensePlate',
			'description',
			'watsonRelationships'
		]
	)
};

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
		},
		viewIncidents: id => {
			dispatch(
				viewIncidents(id)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {entryId, model, watsonIncidentId} = props.router.params;

	const activitiesData = state.getIn(['activities', 'data']) || new Map();
	const activitiesLoading = state.getIn(['activities', 'loading']);
	const addressesData = state.getIn(['addresses', 'data']) || new Map();
	const addressesLoading = state.getIn(['addresses', 'loading']);
	const incidentsData = state.getIn(['incidents', 'data']) || new Map();
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const peopleData = state.getIn(['people', 'data']) || new Map();
	const peopleLoading = state.getIn(['people', 'loading']);
	const resourcesData = state.getIn(['resources', 'data']) || new Map();
	const resourcesLoading = state.getIn(['resources', 'loading']);
	const vehiclesData = state.getIn(['vehicles', 'data']) || new Map();
	const vehiclesLoading = state.getIn(['vehicles', 'loading']);

	const currentIncidentData = incidentsData.get(watsonIncidentId) || new Map();

	return {
		activitiesData,
		activitiesLoading,
		addressesData,
		addressesLoading,
		currentIncidentData,
		entryId,
		incidentsData,
		incidentsLoading,
		model,
		peopleData,
		peopleLoading,
		resourcesData,
		resourcesLoading,
		vehiclesData,
		vehiclesLoading,
		watsonIncidentId
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(IncidentReport);