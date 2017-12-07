import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../../components/Button';
import HTMLRenderer from '../../components/HTMLRenderer';
import GoogleMap from '../../components/GoogleMap';

import {indexDocuments} from '../../actions/documents';
import {indexIllnesses} from '../../actions/illnesses';
import {indexLegals} from '../../actions/legals';
import {viewChildren} from '../../actions/children';

import {getMimeType, updateDOMTitle} from '../../lib/util';

class ChildReport extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonChildId} = props;

		if (watsonChildId) {
			props.indexDocuments(watsonChildId);
			props.indexIllnesses(watsonChildId);
			props.indexLegals(watsonChildId);
			props.viewChildren(watsonChildId);
		}
	}

	created() {
		bindAll(
			this,
			'fetchModelData',
			'handleBack',
			'renderChild',
			'renderChildHeader',
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
		const {entryId, model, watsonChildId} = this.props;

		if (model) {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/${entryId}/edit`);
		}
		else {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit`);
		}
	}

	handlePrint() {
		window.print();
	}

	renderEntities(entityList, displayFields, model, entryId) {
		return (
			<div class="entity-detail">
				{entryId &&
					this.renderChildHeader()
				}

				{this.renderEntitiesHelper(entityList, displayFields, model, entryId)}
			</div>
		);
	}

	renderEntitiesHelper(entityList, displayFields, model, entryId) {
		const renderElement = [];

		if (!isEmpty(entityList)) {
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
		}

		return renderElement;
	}

	renderEntity(data, displayFields, model) {
		let headerName = '';

		const entityName = data.get('name') || data.get('id') || '';
		const entitySingularLabel = WatsonConstants.inputConfig[model].singularLabel;

		headerName = entityName.includes(entitySingularLabel) ? entityName : `${WatsonConstants.inputConfig[model].singularLabel} - ${entityName}`;

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

	renderChild() {
		const {currentChildData} = this.props;

		const {childrenConfig: displayFields} = this.state;

		return (
			<div class="main-detail">
				{this.renderChildHeader()}

				<div class="body">
					<div class="body-header">
						{Liferay.Language.get('details')}
					</div>

					<div class="content">
						{this.renderFields(currentChildData, displayFields, 'children')}
					</div>
				</div>
			</div>
		);
	}

	renderChildHeader() {
		const {currentChildData} = this.props;

		return (
			<div class="header">
				<div class="logo" />

				<div class="title">
					{currentChildData.get('name') || Liferay.Language.get('loading')}
				</div>

				<div class="parent-subtitle">
					{currentChildData.get('typeLabel')}
				</div>

				<div class="child-subtitle">
					{sub(Liferay.Language.get('created-by-x-on-x'), currentChildData.get('reportedBy') || '', currentChildData.get('createDate') || '')}
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

		if (value) {
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
			else if (type == inputTypes.richTextEditor || type === inputTypes.textareaInput) {
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
					this.renderChild()
				}

				{(!entryId || model === 'documents') &&
					this.renderModel(entryId, 'documents')
				}

				{(!entryId || model === 'illnesses') &&
					this.renderModel(entryId, 'illnesses')
				}

				{(!entryId || model === 'legals') &&
					this.renderModel(entryId, 'legals')
				}
			</div>
		);
	}

	rendered(firstRender) {
		const {currentChildData} = this.props;

		const childName = currentChildData.get('name') || '';

		updateDOMTitle(sub(Liferay.Language.get('print-x'), childName));

		if (firstRender) {
			const element = document.getElementById('print-helper-message');

			if (element) {
				this.setState({elementStyle: element.style});

				element.style = 'display: none';
			}
		}
	}
}

ChildReport.PROPS = {
	childrenData: Config.value(new Map()),
	documentsData: Config.value(new Map()),
	entryId: Config.any(),
	illnessesData: Config.value(new Map()),
	legalsData: Config.value(new Map()),
	model: Config.string(),
	watsonChildId: Config.any()
};

ChildReport.STATE = {
	childrenConfig: Config.array().value(
		[
			'nameWatsonListTypeRels',
			'typeWatsonListTypeId',
			'sexWatsonListTypeId',
			'countryWatsonListTypeId',
			'ethnicityWatsonListTypeId',
			'birthCountryId',
			'parentNameWatsonListTypeRels',
			'guardianNameWatsonListTypeRels',
			'countryIDWatsonListTypeRels',
			'dateAccepted',
			'dateDischarged',
			'dischargeWatsonListTypeId',
			'dateFollowUp',
			'sourceWatsonListTypeId',
			'sourceSubtypeWatsonListTypeId',
			'source',
			'activitiesInvolvedWatsonListTypeRels',
			'vocationalTrainingWatsonListTypeRels',
			'watsonRelationships'
		]
	),
	documentsConfig: Config.array().value(
		[
			'imagePayload',
			'receivedDate',
			'originalDocument',
			'parentTypeWatsonListTypeId',
			'typeWatsonListTypeId',
			'subtypeWatsonListTypeId',
			'watsonRelationships'
		]
	),
	elementStyle: Config.value(''),
	entryId: Config.any(),
	illnessesConfig: Config.array().value(
		[
			'reportDate',
			'description',
			'fullReport',
			'watsonRelationships'

		]
	),
	legalsConfig: Config.array().value(
		[
			'reportDate',
			'timeSpent',
			'reportedUser',
			'description',
			'watsonRelationships'
		]
	),
	model: Config.string()
};

function mapDispatchToProps(dispatch) {
	return {
		indexDocuments: id => {
			dispatch(
				indexDocuments({id})
			);
		},
		indexIllnesses: id => {
			const data = {
				id,
				key: WatsonConstants.inputConfig.illnesses.key
			};

			dispatch(
				indexIllnesses(data)
			);
		},
		indexLegals: id => {
			const data = {
				id,
				key: WatsonConstants.inputConfig.legals.key
			};

			dispatch(
				indexLegals(data)
			);
		},
		viewChildren: id => {
			dispatch(
				viewChildren(id)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {entryId, model, watsonChildId} = props.router.params;

	const childrenData = state.getIn(['children', 'data']) || new Map();
	const childrenLoading = state.getIn(['children', 'loading']);
	const documentsData = state.getIn(['documents', 'data']) || new Map();
	const documentsLoading = state.getIn(['documents', 'loading']);
	const illnessesData = state.getIn(['illnesses', 'data']) || new Map();
	const illnessesLoading = state.getIn(['illnesses', 'loading']);
	const legalsData = state.getIn(['legals', 'data']) || new Map();
	const legalsLoading = state.getIn(['legals', 'loading']);

	const currentChildData = childrenData.get(watsonChildId) || new Map();

	return {
		childrenData,
		childrenLoading,
		currentChildData,
		documentsData,
		documentsLoading,
		entryId,
		illnessesData,
		illnessesLoading,
		legalsData,
		legalsLoading,
		model,
		watsonChildId
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ChildReport);