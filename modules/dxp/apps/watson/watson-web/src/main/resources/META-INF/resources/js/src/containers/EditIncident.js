import {bindAll, capitalize} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../components/Button';
import ActivityForm from './forms/Activity';
import AddressForm from './forms/Address';
import HistoryView from './views/History';
import IncidentForm from './forms/Incident';
import Navigation from '../components/Navigation';
import NavigationHeader from '../components/NavigationHeader';
import PersonForm from './forms/Person';
import ResourceForm from './forms/Resource';
import RelationshipsView from './views/Relationships';
import Sort from '../components/Sort';
import TranslateActivityForm from './forms/TranslateActivity';
import TranslateAddressForm from './forms/TranslateAddress';
import TranslateIncidentForm from './forms/TranslateIncident';
import TranslatePersonForm from './forms/TranslatePerson';
import TranslateResourceForm from './forms/TranslateResource';
import TranslateVehicleForm from './forms/TranslateVehicle';
import VehicleForm from './forms/Vehicle';
import ViewIndex from './views/ViewIndex';

import {updateActivitiesDataManually} from '../actions/activities';
import {importAddresses, updateAddressesDataManually, updateAddressesFormData} from '../actions/addresses';
import {updateCollapsedEntries, updateCollapsedEntry} from '../actions/display';
import {updateHistoriesDataManually} from '../actions/histories';
import {editIncident, indexIncidents, refreshSubModel, updateIncidentsDataManually, updateIncidentsFormData} from '../actions/incidents';
import {importPeople, updatePeopleDataManually} from '../actions/people';
import {updateRelationshipsDataManually} from '../actions/relationships';
import {importResources, updateResourcesDataManually} from '../actions/resources';
import {importVehicles, updateVehiclesDataManually} from '../actions/vehicles';

import {getOptionsLabelFromWatsonConstants} from '../lib/util';

class EditIncident extends JSXComponent {
	attached() {
		const {props} = this;

		const {incidentStoreData} = props;

		if (incidentStoreData.size < 1) {
			props.editIncident(props.router.params.watsonIncidentId);
		}
	}

	created() {
		bindAll(
			this,
			'handleNavigationOnChange',
			'handlePrintReport',
			'handleUpdateIncidentRelationshipsFormData'
		);
	}

	detached() {
		const {props} = this;

		props.updateIncidentsFormData({formData: {}, watsonIncidentId: 0});
		props.updateAddressesFormData({formData: {}, watsonAddressId: 0});
	}

	getCurrentView(action, entryId, incidentDisabled, incidentName, model, watsonIncidentId) {
		const {props} = this;

		let view;

		const modelCreateMethod = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/create`);
		const modelIndexRedirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/index`);

		if (model === 'activities') {
			if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-activity'),
						method: modelCreateMethod
					}
				];
				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<TranslateActivityForm
						action={action}
						disabled={incidentDisabled}
						incidentName={incidentName}
						watsonActivityId={entryId}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else {
				view = (
					<ActivityForm
						action={action}
						disabled={incidentDisabled}
						formData={props.modelFormData}
						incidentName={incidentName}
						storeData={props.modelStoreData}
						watsonActivityId={entryId}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
		}
		else if (model === 'addresses') {
			if (action === 'translate') {
				view = (
					<TranslateAddressForm
						action={action}
						disabled={incidentDisabled}
						incidentName={incidentName}
						watsonAddressId={entryId}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'import') {
				const buttonData = [
					{
						label: Liferay.Language.get('import')
					},
					{
						label: Liferay.Language.get('cancel'),
						method: modelIndexRedirect
					}
				];

				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						redirect={modelIndexRedirect}
						submitMethod={props.importAddress}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-address'),
						method: modelCreateMethod
					},
					{
						label: Liferay.Language.get('import'),
						method: () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/import`)
					}
				];

				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else {
				view = (
					<AddressForm
						action={action}
						disabled={incidentDisabled}
						formData={props.modelFormData}
						incidentName={incidentName}
						storeData={props.modelStoreData}
						watsonAddressId={entryId}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
		}
		else if (model === 'histories') {
			view = (
				<HistoryView
					action={action}
					disabled={incidentDisabled}
					incidentName={incidentName}
					watsonIncidentId={watsonIncidentId}
				/>
			);
		}
		else if (model === 'people') {
			if (action === 'import') {
				const buttonData = [
					{
						label: Liferay.Language.get('import')
					},
					{
						label: Liferay.Language.get('cancel'),
						method: modelIndexRedirect
					}
				];

				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						redirect={modelIndexRedirect}
						submitMethod={props.importPeople}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-person'),
						method: modelCreateMethod
					},
					{
						label: Liferay.Language.get('import'),
						method: () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/import`)
					}
				];
				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<TranslatePersonForm
						action={action}
						disabled={incidentDisabled}
						incidentName={incidentName}
						watsonIncidentId={watsonIncidentId}
						watsonPersonId={entryId}
					/>
				);
			}
			else {
				view = (
					<PersonForm
						action={action}
						disabled={incidentDisabled}
						formData={props.modelFormData}
						incidentName={incidentName}
						storeData={props.modelStoreData}
						watsonIncidentId={watsonIncidentId}
						watsonPersonId={entryId}
					/>
				);
			}
		}
		else if (model === 'relationships') {
			view = (
				<RelationshipsView
					disabled={incidentDisabled}
					incidentName={incidentName}
					watsonIncidentId={watsonIncidentId}
				/>
			);
		}
		else if (model === 'resources') {
			if (action === 'import') {
				const buttonData = [
					{
						label: Liferay.Language.get('import')
					},
					{
						label: Liferay.Language.get('cancel'),
						method: modelIndexRedirect
					}
				];

				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						redirect={modelIndexRedirect}
						submitMethod={props.importResources}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-resource'),
						method: modelCreateMethod
					},
					{
						label: Liferay.Language.get('import'),
						method: () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/import`)
					}
				];

				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<TranslateResourceForm
						action={action}
						disabled={incidentDisabled}
						incidentName={incidentName}
						watsonIncidentId={watsonIncidentId}
						watsonResourceId={entryId}
					/>
				);
			}
			else {
				view = (
					<ResourceForm
						action={action}
						disabled={incidentDisabled}
						formData={props.modelFormData}
						incidentName={incidentName}
						storeData={props.modelStoreData}
						watsonIncidentId={watsonIncidentId}
						watsonResourceId={entryId}
					/>
				);
			}
		}
		else if (model === 'vehicles') {
			if (action === 'import') {
				const buttonData = [
					{
						label: Liferay.Language.get('import')
					},
					{
						label: Liferay.Language.get('cancel'),
						method: modelIndexRedirect
					}
				];

				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						redirect={modelIndexRedirect}
						submitMethod={props.importVehicles}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-vehicle'),
						method: modelCreateMethod
					},
					{
						label: Liferay.Language.get('import'),
						method: () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/import`)
					}
				];

				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={incidentDisabled}
						model={model}
						primaryName={incidentName}
						watsonIncidentId={watsonIncidentId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<TranslateVehicleForm
						action={action}
						disabled={incidentDisabled}
						incidentName={incidentName}
						watsonIncidentId={watsonIncidentId}
						watsonVehicleId={entryId}
					/>
				);
			}
			else {
				view = (
					<VehicleForm
						action={action}
						disabled={incidentDisabled}
						formData={props.modelFormData}
						incidentName={incidentName}
						storeData={props.modelStoreData}
						watsonIncidentId={watsonIncidentId}
						watsonVehicleId={entryId}
					/>
				);
			}
		}
		else if (action === 'translate') {
			view = (
				<TranslateIncidentForm
					action={action}
					disabled={incidentDisabled}
					incidentName={incidentName}
					storeData={props.incidentStoreData}
					watsonIncidentId={watsonIncidentId}
				/>
			);
		}
		else if (action === 'relate') {
			const incidentRelateRedirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/`);

			const buttonData = [
				{
					label: Liferay.Language.get('relate')
				},
				{
					label: Liferay.Language.get('cancel'),
					method: incidentRelateRedirect
				}
			];

			let selectedIds = [];

			if (props.incidentFormData) {
				selectedIds = props.incidentFormData.watsonIncidentRelIds;
			}

			view = (
				<ViewIndex
					action={action}
					buttonData={buttonData}
					disabled={incidentDisabled}
					keysToOmit={[watsonIncidentId]}
					model={model}
					primaryName={incidentName}
					redirect={incidentRelateRedirect}
					selectedIds={selectedIds}
					submitMethod={this.handleUpdateIncidentRelationshipsFormData}
					watsonIncidentId={watsonIncidentId}
				/>
			);
		}
		else {
			view = (
				<IncidentForm
					action={action}
					disabled={incidentDisabled}
					formData={props.incidentFormData}
					incidentName={incidentName}
					incidents={props.incidents}
					storeData={props.incidentStoreData}
					watsonIncidentId={watsonIncidentId}
				/>
			);
		}

		return view;
	}

	handleNavigationOnChange(newNavigationState) {
		const {updateCollapsedEntries, watsonIncidentId} = this.props;

		updateCollapsedEntries(newNavigationState, watsonIncidentId);
	}

	handlePrintReport() {
		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${this.props.watsonIncidentId}/report`);
	}

	handleUpdateIncidentRelationshipsFormData(relationshipIds) {
		const {
			incidentFormData = {},
			updateIncidentsFormData,
			watsonIncidentId = 0
		} = this.props;

		incidentFormData.watsonIncidentRelIds = relationshipIds.selectedIds;

		updateIncidentsFormData(incidentFormData, watsonIncidentId);
	}

	render() {
		const {
			action = 'edit',
			collapsedEntries,
			entryId = 0,
			incidentStoreData,
			model,
			watsonIncidentId
		} = this.props;

		const activityNav = [];
		const addressNav = [];
		const personNav = [];
		const resourceNav = [];
		const vehicleNav = [];

		const incidentDisabled = incidentStoreData.get('disabled');
		const incidentMetaHeader = `${sub(Liferay.Language.get('created-by-x-on-x'), incidentStoreData.get('reportedBy') || '', incidentStoreData.get('createDate') || '')}`;
		const incidentName = incidentStoreData.get('name') || Liferay.Language.get('loading');

		const incidentTypeLabel = getOptionsLabelFromWatsonConstants('incidents', 'typeWatsonListTypeId', incidentStoreData.get('typeWatsonListTypeId'));

		if (incidentStoreData.get('activities')) {
			const incidentActivities = Sort(incidentStoreData.get('activities'), null, 'name');

			const activityList = [];

			incidentActivities.forEach(
				incidentActivity => {
					const activityId = incidentActivity.get('id');

					activityList.push(activityId);

					const activityName = incidentActivity.get('name') || activityId;

					activityNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/${activityId}/edit`,
							name: `activities_${activityId}`,
							selected: (entryId === activityId && model === 'activities'),
							text: activityName
						}
					);
				}
			);
		}

		if (incidentStoreData.get('addresses')) {
			const incidentAddresses = Sort(incidentStoreData.get('addresses'), null, 'name');

			const addressList = [];

			incidentAddresses.forEach(
				incidentAddressData => {
					const addressId = incidentAddressData.get('id');

					addressList.push(addressId);

					const addressName = incidentAddressData.get('name') || addressId;

					addressNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/${addressId}/edit`,
							name: `addresses_${addressId}`,
							selected: (entryId === addressId && model === 'addresses'),
							text: addressName
						}
					);
				}
			);
		}

		if (incidentStoreData.get('people')) {
			const incidentPeople = Sort(incidentStoreData.get('people'), null, 'name');

			const personList = [];

			incidentPeople.forEach(
				incidentPersonData => {
					const personId = incidentPersonData.get('id');

					personList.push(personId);

					const personName = incidentPersonData.get('name') || personId;

					personNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/${personId}/edit`,
							name: `people_${personId}`,
							selected: (entryId === personId && model === 'people'),
							text: personName
						}
					);
				}
			);
		}

		if (incidentStoreData.get('resources')) {
			const incidentResources = Sort(incidentStoreData.get('resources'), null, 'name');

			const resourceList = [];

			incidentResources.forEach(
				incidentResourceData => {
					const resourceId = incidentResourceData.get('id');

					resourceList.push(resourceId);

					const resourceName = incidentResourceData.get('name') || resourceId;

					resourceNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${resourceId}/edit`,
							name: `resources_${resourceId}`,
							selected: (entryId === resourceId && model === 'resources'),
							text: resourceName
						}
					);
				}
			);
		}

		if (incidentStoreData.get('vehicles')) {
			const incidentVehicles = Sort(incidentStoreData.get('vehicles'), null, 'name');

			const vehicleList = [];

			incidentVehicles.forEach(
				incidentVehicleData => {
					const vehicleId = incidentVehicleData.get('id');

					vehicleList.push(vehicleId);

					const vehicleName = incidentVehicleData.get('name') || vehicleId;

					vehicleNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${vehicleId}/edit`,
							name: `vehicles_${vehicleId}`,
							selected: (entryId === vehicleId && model === 'vehicles'),
							text: vehicleName
						}
					);
				}
			);
		}

		const nav = [
			{
				collapsible: false,
				entries: null,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit`,
				selected: !model,
				text: Liferay.Language.get('details')
			},
			{
				collapsible: true,
				entries: activityNav,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'activities'),
				text: Liferay.Language.get('activities')
			},
			{
				collapsible: true,
				entries: addressNav,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'addresses'),
				text: Liferay.Language.get('addresses')
			},
			{
				collapsible: true,
				entries: personNav,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'people'),
				text: Liferay.Language.get('people')
			},
			{
				collapsible: true,
				entries: resourceNav,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'resources'),
				text: Liferay.Language.get('resources')
			},
			{
				collapsible: true,
				entries: vehicleNav,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'vehicles'),
				text: Liferay.Language.get('vehicles')
			},
			{
				collapsible: false,
				entries: null,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/relationships/edit`,
				selected: (model === 'relationships'),
				text: Liferay.Language.get('relationships')
			},
			{
				collapsible: false,
				entries: null,
				href: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/histories/index`,
				selected: (model === 'histories'),
				text: Liferay.Language.get('history')
			}
		];

		return (
			<div class="incidents-edit page-container hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader mainHeader={incidentName} metaHeader={incidentMetaHeader} subHeader={incidentTypeLabel} />

					<Navigation entries={nav} navigationState={collapsedEntries} onChange={this.handleNavigationOnChange} />

					<Button label={Liferay.Language.get('print-report')} onClick={this.handlePrintReport} />
				</div>

				{this.getCurrentView(action, entryId, incidentDisabled, incidentName, model, watsonIncidentId)}
			</div>
		);
	}

	rendered() {
		const {
			entryId,
			lastAutoOpenedEntry,
			model,
			refreshSubModel,
			shouldUpdateModel,
			updateCollapsedEntry,
			watsonIncidentId
		} = this.props;

		if (shouldUpdateModel) {
			refreshSubModel(
				{
					id: watsonIncidentId,
					model
				}
			);

			const updateDataManuallyMethodName = `update${capitalize(model)}DataManually`;

			const updateManuallyMethod = this.props[updateDataManuallyMethodName];

			if (updateManuallyMethod) {
				updateManuallyMethod({update: false});
			}
		}

		if (model) {
			const collapsedEntryHash = `${WatsonConstants.inputConfig[model].pluralLabel}_${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/index`;

			if (entryId && (!lastAutoOpenedEntry || lastAutoOpenedEntry !== collapsedEntryHash)) {

				updateCollapsedEntry(watsonIncidentId, collapsedEntryHash, false, true);
			}
		}
	}
}

EditIncident.PROPS = {
	action: Config.string().value(''),
	collapsedEntries: Config.value(''),
	entryId: Config.value(''),
	incidentFormData: Config.object().value({}),
	incidents: Config.value(new Map()),
	incidentStoreData: Config.value(new Map()),
	lastAutoOpenedEntry: Config.string().value(''),
	model: Config.string().value(''),
	modelFormData: Config.object().value({}),
	modelStoreData: Config.value(new Map()),
	shouldUpdateModel: Config.bool().value(false),
	watsonIncidentId: Config.value('')
};

EditIncident.SYNC_UPDATES = true;

function mapDispatchToProps(dispatch) {
	return {
		editIncident: data => {
			dispatch(
				editIncident(data)
			);
		},
		importAddress: data => {
			dispatch(
				importAddresses(data)
			);
		},
		importPeople: data => {
			dispatch(
				importPeople(data)
			);
		},
		importResources: data => {
			dispatch(
				importResources(data)
			);
		},
		importVehicles: data => {
			dispatch(
				importVehicles(data)
			);
		},
		indexIncidents: () => {
			dispatch(
				indexIncidents()
			);
		},
		refreshSubModel: data => {
			dispatch(
				refreshSubModel(data)
			);
		},
		updateActivitiesDataManually: data => {
			dispatch(
				updateActivitiesDataManually(data)
			);
		},
		updateAddressesDataManually: data => {
			dispatch(
				updateAddressesDataManually(data)
			);
		},
		updateAddressesFormData: data => {
			dispatch(
				updateAddressesFormData(data)
			);
		},
		updateCollapsedEntries: (collapsedEntries, watsonIncidentId) => {
			const data = {
				collapsedEntries,
				watsonIncidentId
			};

			dispatch(
				updateCollapsedEntries(data)
			);
		},
		updateCollapsedEntry: (watsonIncidentId, collapsedEntryHash, value, auto = false) => {
			const data = {
				auto,
				collapsedEntryHash,
				value,
				watsonIncidentId
			};

			dispatch(
				updateCollapsedEntry(data)
			);
		},
		updateHistoriesDataManually: data => {
			dispatch(
				updateHistoriesDataManually(data)
			);
		},
		updateIncidentsDataManually: data => {
			dispatch(
				updateIncidentsDataManually(data)
			);
		},
		updateIncidentsFormData: (formData, watsonIncidentId) => {
			const data = {
				formData,
				watsonIncidentId
			};

			dispatch(
				updateIncidentsFormData(data)
			);
		},
		updatePeopleDataManually: data => {
			dispatch(
				updatePeopleDataManually(data)
			);
		},
		updateRelationshipsDataManually: data => {
			dispatch(
				updateRelationshipsDataManually(data)
			);
		},
		updateResourcesDataManually: data => {
			dispatch(
				updateResourcesDataManually(data)
			);
		},
		updateVehiclesDataManually: data => {
			dispatch(
				updateVehiclesDataManually(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {action = 'edit', entryId = 0, model, watsonIncidentId} = props.router.params;

	const incidentFormData = state.getIn(['incidents', 'formData', watsonIncidentId]) || {};
	const incidentStoreData = state.getIn(['incidents', 'data', watsonIncidentId]) || new Map();
	const modelFailure = state.getIn([model, 'failure']) || false;
	const modelForbidden = state.getIn([model, 'forbidden']) || false;
	const modelFormData = state.getIn([model, 'formData', entryId]) || {};
	const modelStoreData = state.getIn([model, 'data', entryId]) || new Map();
	const shouldUpdateModel = (modelFailure || modelForbidden) ? false : state.getIn([model, 'update']);

	return {
		action,
		collapsedEntries: state.getIn(
			[
				'display',
				'collapsedEntries',
				watsonIncidentId
			]
		),
		entryId,
		incidentFormData,
		incidents: state.getIn(
			[
				'incidents',
				'data'
			]
		),
		incidentStoreData,
		lastAutoOpenedEntry: state.getIn(
			[
				'display',
				'lastAutoOpenedEntry'
			]
		),
		model,
		modelFormData,
		modelStoreData,
		shouldUpdateModel,
		watsonIncidentId
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(EditIncident);