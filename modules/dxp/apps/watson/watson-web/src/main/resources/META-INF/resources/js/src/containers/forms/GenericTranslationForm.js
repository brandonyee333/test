import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchActivitiesTranslation, updateActivities, viewActivities} from '../../actions/activities';
import {fetchAddressesTranslation, updateAddresses, viewAddresses} from '../../actions/addresses';
import {fetchCaseworkActivitiesTranslation, updateCaseworkActivities, viewCaseworkActivities} from '../../actions/casework-activities';
import {fetchCounselingReportsTranslation, updateCounselingReports, viewCounselingReports} from '../../actions/counseling-reports';
import {fetchChildrenTranslation, updateChildren, viewChildren} from '../../actions/children';
import {fetchLegalsTranslation, updateLegals, viewLegals} from '../../actions/legals';
import {fetchIllnessesTranslation, updateIllnesses, viewIllnesses} from '../../actions/illnesses';
import {fetchIncidentsTranslation, updateIncidents, viewIncidents} from '../../actions/incidents';
import {fetchPeopleTranslation, updatePeople, viewPeople} from '../../actions/people';
import {fetchResourcesTranslation, updateResources, viewResources} from '../../actions/resources';
import {fetchVehiclesTranslation, updateVehicles, viewVehicles} from '../../actions/vehicles';

import sendRequest from '../../lib/request';
import {formatModelName, getModifiedMoment, updateDOMTitle} from '../../lib/util';

class GenericTranslationForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {model, watsonPrimaryKey} = props;

		if (model && watsonPrimaryKey) {
			const fetchTranslationMethod = props[`fetch${formatModelName(model)}Translation`];
			const viewModelMethod = props[`view${formatModelName(model)}`];

			if (viewModelMethod) {
				viewModelMethod(watsonPrimaryKey);
			}

			if (fetchTranslationMethod) {
				fetchTranslationMethod(watsonPrimaryKey);
			}
		}
	}

	created() {
		bindAll(
			this,
			'handleAutoSave',
			'handleRedirect',
			'handleSubmit'
		);
	}

	handleAutoSave(formData) {
		const {
			disabled,
			loading,
			model,
			requestFailure
		} = this.props;

		if (!requestFailure && !loading && !disabled) {
			sendRequest(
				{
					controller: model,
					controllerMethod: 'updateTranslation.json',
					data: formData
				}
			).then(
				response => {
					if (response) {
						this.setState(
							{
								autoSaved: Date.now(),
								autoSaveResponse: response.data
							}
						);
					}
				}
			).catch(
				() => {
					this.setState({autoSaved: 0});

					const {intervalId} = this.state;

					clearInterval(intervalId);
				}
			);
		}
	}

	handleRedirect() {
		const {model, parentModel, watsonChildId, watsonIncidentId, watsonPrimaryKey} = this.props;

		const watsonParentPrimaryKey = watsonIncidentId ? watsonIncidentId : watsonChildId;

		if (watsonPrimaryKey === watsonParentPrimaryKey) {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/${parentModel}/${watsonParentPrimaryKey}/edit/`);
		}
		else {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/${parentModel}/${watsonParentPrimaryKey}/edit/${model}/${watsonPrimaryKey}/edit`);
		}
	}

	handleSubmit(data) {
		const {props} = this;

		const submitMethod = props[`translate${formatModelName(props.model)}`];

		if (submitMethod) {
			submitMethod(data);
		}
	}

	render() {
		const {props} = this;

		const {
			autoSavedEnabled,
			disabled = !WatsonConstants.currentUser.translatorRole,
			errors,
			fieldConfig,
			formConfig,
			loading,
			modelLabel,
			response,
			storeData,
			translatedData,
			watsonPrimaryKey
		} = props;

		const {autoSaved, autoSaveResponse} = this.state;

		const headerStringLeft = storeData.get('name') || sub(Liferay.Language.get('translate-x'), modelLabel);

		const headerStringRight = !autoSaved ? getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp')) : getModifiedMoment(storeData.get('modifiedUserName'), autoSaved);

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} headerStringRight={headerStringRight} />
				</div>

				<div class="content">
					<TranslationForm
						autoSaveEnabled={autoSavedEnabled}
						autoSaveMethod={this.handleAutoSave}
						autoSaveResponse={autoSaveResponse}
						disabled={disabled}
						errors={errors}
						fieldConfig={fieldConfig}
						formConfig={formConfig}
						id={watsonPrimaryKey}
						loading={loading}
						redirect={this.handleRedirect}
						response={response}
						storeData={storeData}
						submitMethod={this.handleSubmit}
						translatedData={translatedData}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {primaryName, storeData} = this.props;

		const translateName = sub(Liferay.Language.get('translate-x'), storeData.get('id') || '');

		updateDOMTitle(`${primaryName} ${translateName}`);
	}
}

GenericTranslationForm.PROPS = {
	autoSavedEnabled: Config.bool().value(false),
	disabled: Config.bool().value(false),
	fieldConfig: Config.any(),
	formConfig: Config.any(),
	loading: Config.bool().value(false),
	model: Config.string(),
	modelLabel: Config.string(),
	primaryName: Config.string(),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonChildId: Config.any(),
	watsonIncidentId: Config.any(),
	watsonPrimaryKey: Config.value('')
};

GenericTranslationForm.STATE = {
	autoSaved: Config.number().value(0),
	autoSaveResponse: Config.object()
};

GenericTranslationForm.SYNC_UPDATES = true;

function mapStateToProps(state, props) {
	const {model, watsonPrimaryKey} = props;

	const requestFailure = state.getIn([model, 'response', 'failure']);
	const storeData = state.getIn([model, 'data', watsonPrimaryKey]) || new Map();
	const translatedData = state.getIn([model, 'translationData', watsonPrimaryKey]) || new Map();

	return {
		loading: state.getIn(
			[
				model,
				'loading'
			]
		),
		modelLabel: !model ? '' : WatsonConstants.inputConfig[model].singularLabel,
		requestFailure,
		response: state.getIn(
			[
				model,
				'response'
			]
		),
		storeData,
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchActivitiesTranslation: watsonActivityId => {
			const data = {
				id: watsonActivityId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchActivitiesTranslation(data)
			);
		},
		fetchAddressesTranslation: watsonAddressId => {
			const data = {
				id: watsonAddressId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchAddressesTranslation(data)
			);
		},
		fetchCaseworkActivitiesTranslation: watsonReportId => {
			const data = {
				id: watsonReportId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchCaseworkActivitiesTranslation(data)
			);
		},
		fetchChildrenTranslation: watsonChildId => {
			const data = {
				id: watsonChildId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchChildrenTranslation(data)
			);
		},
		fetchCounselingReportsTranslation: watsonReportId => {
			const data = {
				id: watsonReportId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchCounselingReportsTranslation(data)
			);
		},
		fetchIllnessesTranslation: watsonReportId => {
			const data = {
				id: watsonReportId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchIllnessesTranslation(data)
			);
		},
		fetchIncidentsTranslation: watsonIncidentId => {
			const data = {
				id: watsonIncidentId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchIncidentsTranslation(data)
			);
		},
		fetchLegalsTranslation: watsonReportId => {
			const data = {
				id: watsonReportId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchLegalsTranslation(data)
			);
		},
		fetchPeopleTranslation: watsonPersonId => {
			const data = {
				id: watsonPersonId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchPeopleTranslation(data)
			);
		},
		fetchResourcesTranslation: watsonResourceId => {
			const data = {
				id: watsonResourceId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchResourcesTranslation(data)
			);
		},
		fetchVehiclesTranslation: watsonVehicleId => {
			const data = {
				id: watsonVehicleId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchVehiclesTranslation(data)
			);
		},
		translateActivities: data => {
			dispatch(
				updateActivities(data, 'updateTranslation.json')
			);
		},
		translateAddresses: data => {
			dispatch(
				updateAddresses(data, 'updateTranslation.json')
			);
		},
		translateCaseworkActivities: data => {
			dispatch(
				updateCaseworkActivities(data, 'updateTranslation.json')
			);
		},
		translateChildren: data => {
			dispatch(
				updateChildren(data, 'updateTranslation.json')
			);
		},
		translateCounselingReports: data => {
			dispatch(
				updateCounselingReports(data, 'updateTranslation.json')
			);
		},
		translateIllnesses: data => {
			dispatch(
				updateIllnesses(data, 'updateTranslation.json')
			);
		},
		translateIncidents: data => {
			dispatch(
				updateIncidents(data, 'updateTranslation.json')
			);
		},
		translateLegals: data => {
			dispatch(
				updateLegals(data, 'updateTranslation.json')
			);
		},
		translatePeople: data => {
			dispatch(
				updatePeople(data, 'updateTranslation.json')
			);
		},
		translateResources: data => {
			dispatch(
				updateResources(data, 'updateTranslation.json')
			);
		},
		translateVehicles: data => {
			dispatch(
				updateVehicles(data, 'updateTranslation.json')
			);
		},
		viewActivities: watsonActivityId => {
			dispatch(
				viewActivities(watsonActivityId)
			);
		},
		viewAddresses: watsonAddressId => {
			dispatch(
				viewAddresses(watsonAddressId)
			);
		},
		viewCaseworkActivities: watsonReportId => {
			dispatch(
				viewCaseworkActivities(watsonReportId)
			);
		},
		viewChildren: watsonChildId => {
			dispatch(
				viewChildren(watsonChildId)
			);
		},
		viewCounselingReports: watsonReportId => {
			dispatch(
				viewCounselingReports(watsonReportId)
			);
		},
		viewIllnesses: watsonReportId => {
			dispatch(
				viewIllnesses(watsonReportId)
			);
		},
		viewIncidents: watsonIncidentId => {
			dispatch(
				viewIncidents(watsonIncidentId)
			);
		},
		viewLegals: watsonReportId => {
			dispatch(
				viewLegals(watsonReportId)
			);
		},
		viewPeople: watsonPersonId => {
			dispatch(
				viewPeople(watsonPersonId)
			);
		},
		viewResources: watsonResourceId => {
			dispatch(
				viewResources(watsonResourceId)
			);
		},
		viewVehicles: watsonVehicleId => {
			dispatch(
				viewVehicles(watsonVehicleId)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(GenericTranslationForm);