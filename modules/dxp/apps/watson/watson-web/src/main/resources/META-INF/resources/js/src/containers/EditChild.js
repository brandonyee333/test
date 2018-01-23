import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../components/Button';
import ChildForm from './forms/Child';
import GenericChildForm from './forms/GenericChildForm';
import GenericTranslationForm from './forms/GenericTranslationForm';
import Navigation from '../components/Navigation';
import NavigationHeader from '../components/NavigationHeader';
import Sort from '../components/Sort';
import ViewIndex from './views/ViewIndex';

import {updateCaseworkActivitiesDataManually} from '../actions/casework-activities';
import {updateCounselingReportsDataManually} from '../actions/counseling-reports';
import {editChildren, refreshSubModel, updateChildrenDataManually, updateChildrenFormData} from '../actions/children';
import {updateCollapsedEntries, updateCollapsedEntry} from '../actions/display';
import {updateDocumentsDataManually} from '../actions/documents';
import {updateIllnessesDataManually} from '../actions/illnesses';
import {updateLegalsDataManually} from '../actions/legals';

import {formatModelName, getOptionsLabelFromWatsonConstants} from '../lib/util';

class EditChild extends JSXComponent {
	attached() {
		const {props} = this;

		const {childrenStoreData} = props;

		if (childrenStoreData.size < 1) {
			props.editChildren(props.router.params.watsonChildId);
		}
	}

	created() {
		bindAll(
			this,
			'handleNavigationOnChange',
			'handlePrintReport'
		);
	}

	detached() {
		const {props} = this;

		props.updateChildrenFormData({formData: {}, watsonChildId: 0});
	}

	getCurrentView(action, childDisabled, childName, entryId, model, watsonChildId) {
		const {props} = this;

		let view;

		const modelCreateMethod = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/create`);

		if (model === 'casework_activities') {
			if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-casework-activity'),
						method: modelCreateMethod
					}
				];
				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={childDisabled}
						model={model}
						primaryName={childName}
						watsonChildId={watsonChildId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<GenericTranslationForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.casework_activities.inputs}
						formConfig={[
							'description'
						]}
						model={model}
						parentModel="children"
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
			else {
				view = (
					<GenericChildForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.casework_activities.inputs}
						formConfig={[
							'id',
							'typeWatsonListTypeId',
							'reportDate',
							'description',
							'imagePayload'
						]}
						formData={props.modelFormData}
						model={model}
						modelKey={WatsonConstants.inputConfig.casework_activities.key}
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
		}
		else if (model === 'counseling_reports') {
			if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-counseling-report'),
						method: modelCreateMethod
					}
				];
				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={childDisabled}
						model={model}
						primaryName={childName}
						watsonChildId={watsonChildId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<GenericTranslationForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.counseling_reports.inputs}
						formConfig={[
							'reportedUser',
							'description'
						]}
						model={model}
						parentModel="children"
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
			else {
				view = (
					<GenericChildForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.counseling_reports.inputs}
						formConfig={[
							'id',
							'reportDate',
							'typeWatsonListTypeId',
							'timeSpent',
							'reportedUser',
							'description'
						]}
						formData={props.modelFormData}
						model={model}
						modelKey={WatsonConstants.inputConfig.counseling_reports.key}
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
		}
		else if (model === 'documents') {
			if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-document'),
						method: modelCreateMethod
					}
				];
				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={childDisabled}
						model={model}
						primaryName={childName}
						watsonChildId={watsonChildId}
					/>
				);
			}
			else {
				view = (
					<GenericChildForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.documents.inputs}
						formConfig={[
							'id',
							'imagePayload',
							'receivedDate',
							'originalDocument',
							'parentTypeWatsonListTypeId',
							'typeWatsonListTypeId',
							'subtypeWatsonListTypeId'
						]}
						formData={props.modelFormData}
						model={model}
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
		}
		else if (model === 'illnesses') {
			if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-illness-report'),
						method: modelCreateMethod
					}
				];
				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={childDisabled}
						model={model}
						primaryName={childName}
						watsonChildId={watsonChildId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<GenericTranslationForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.illnesses.inputs}
						formConfig={[
							'description',
							'fullReport'
						]}
						model={model}
						parentModel="children"
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
			else {
				view = (
					<GenericChildForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.illnesses.inputs}
						formConfig={[
							'id',
							'reportDate',
							'description',
							'fullReport'
						]}
						formData={props.modelFormData}
						model={model}
						modelKey={WatsonConstants.inputConfig.illnesses.key}
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
		}
		else if (model === 'legals') {
			if (action === 'index') {
				const buttonData = [
					{
						label: Liferay.Language.get('create-legal-report'),
						method: modelCreateMethod
					}
				];
				view = (
					<ViewIndex
						action={action}
						buttonData={buttonData}
						disabled={childDisabled}
						model={model}
						primaryName={childName}
						watsonChildId={watsonChildId}
					/>
				);
			}
			else if (action === 'translate') {
				view = (
					<GenericTranslationForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.legals.inputs}
						formConfig={[
							'reportedUser',
							'description'
						]}
						model={model}
						parentModel="children"
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
			else {
				view = (
					<GenericChildForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						fieldConfig={WatsonConstants.inputConfig.legals.inputs}
						formConfig={[
							'id',
							'reportDate',
							'timeSpent',
							'reportedUser',
							'description'
						]}
						formData={props.modelFormData}
						model={model}
						modelKey={WatsonConstants.inputConfig.legals.key}
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={entryId}
					/>
				);
			}
		}
		else if (action === 'index') {
			view = (
				<ViewIndex
					action={action}
					childName={childName}
					disabled={childDisabled}
					model={model}
					watsonChildId={watsonChildId}
				/>
			);
		}
		else if (action === 'translate') {
			view = (
				<GenericTranslationForm
					action={action}
					childName={childName}
					disabled={childDisabled}
					fieldConfig={WatsonConstants.inputConfig.children.inputs}
					formConfig={[
						'source'
					]}
					model={model}
					parentModel="children"
					storeData={props.childrenStoreData}
					watsonChildId={watsonChildId}
					watsonPrimaryKey={watsonChildId}
				/>
			);
		}
		else {
			view = (
				<ChildForm
					action={action}
					childName={childName}
					children={props.children}
					disabled={childDisabled}
					formData={props.childFormData}
					storeData={props.childrenStoreData}
					watsonChildId={watsonChildId}
				/>
			);
		}

		return view;
	}

	handleNavigationOnChange(newNavigationState) {
		const {updateCollapsedEntries, watsonChildId} = this.props;

		updateCollapsedEntries(newNavigationState, watsonChildId);
	}

	handlePrintReport() {
		Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${this.props.watsonChildId}/report`);
	}

	render() {
		const {
			action = 'edit',
			childrenStoreData,
			collapsedEntries,
			entryId = 0,
			model,
			watsonChildId
		} = this.props;

		const childDisabled = false;
		const childMetaHeader = `${sub(Liferay.Language.get('created-by-x-on-x'), childrenStoreData.get('reportedBy') || '', childrenStoreData.get('createDate') || '')}`;
		const childName = childrenStoreData.get('name') || Liferay.Language.get('loading');

		const childTypeLabel = getOptionsLabelFromWatsonConstants('children', 'typeWatsonListTypeId', childrenStoreData.get('typeWatsonListTypeId'));

		const caseworkActivitiesNav = [];
		const counselingReportsNav = [];
		const documentsNav = [];
		const illnessesNav = [];
		const legalsNav = [];

		if (childrenStoreData.get('casework_activities')) {
			const caseworkActivities = Sort(childrenStoreData.get('casework_activities'), null, 'name');

			const caseworkActivitiesList = [];

			caseworkActivities.forEach(
				caseworkActivity => {
					const caseworkActivityId = caseworkActivity.get('id');

					caseworkActivitiesList.push(caseworkActivityId);

					const caseworkActivityName = caseworkActivity.get('name') || caseworkActivityId;

					caseworkActivitiesNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/casework_activities/${caseworkActivityId}/edit`,
							name: `caseworkActivities_${caseworkActivityId}`,
							selected: (entryId === caseworkActivityId && model === 'casework_activities'),
							text: caseworkActivityName
						}
					);
				}
			);
		}

		if (childrenStoreData.get('counseling_reports')) {
			const counselingReports = Sort(childrenStoreData.get('counseling_reports'), null, 'name');

			const counselingReportsList = [];

			counselingReports.forEach(
				counselingReport => {
					const counselingReportId = counselingReport.get('id');

					counselingReportsList.push(counselingReportId);

					const counselingReportName = counselingReport.get('name') || counselingReportId;

					counselingReportsNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/counseling_reports/${counselingReportId}/edit`,
							name: `counselingReports_${counselingReportId}`,
							selected: (entryId === counselingReportId && model === 'counseling_reports'),
							text: counselingReportName
						}
					);
				}
			);
		}

		if (childrenStoreData.get('documents')) {
			const childDocuments = Sort(childrenStoreData.get('documents'), null, 'name');

			const documentList = [];

			childDocuments.forEach(
				childDocument => {
					const documentId = childDocument.get('id');

					documentList.push(documentId);

					const documentName = childDocument.get('name') || documentId;

					documentsNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/${documentId}/edit`,
							name: `documents_${documentId}`,
							selected: (entryId === documentId && model === 'documents'),
							text: documentName
						}
					);
				}
			);
		}

		if (childrenStoreData.get('illnesses')) {
			const childIllnesses = Sort(childrenStoreData.get('illnesses'), null, 'name');

			const illnessList = [];

			childIllnesses.forEach(
				childIllness => {
					const illnessId = childIllness.get('id');

					illnessList.push(illnessId);

					const illnessName = childIllness.get('name') || illnessId;

					illnessesNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/illnesses/${illnessId}/edit`,
							name: `illnesses${illnessId}`,
							selected: (entryId === illnessId && model === 'illnesses'),
							text: illnessName
						}
					);
				}
			);
		}

		if (childrenStoreData.get('legals')) {
			const childLegals = Sort(childrenStoreData.get('legals'), null, 'name');

			const legalList = [];

			childLegals.forEach(
				childLegal => {
					const legalId = childLegal.get('id');

					legalList.push(legalId);

					const legalName = childLegal.get('name') || legalId;

					legalsNav.push(
						{
							href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/legals/${legalId}/edit`,
							name: `legals_${legalId}`,
							selected: (entryId === legalId && model === 'legals'),
							text: legalName
						}
					);
				}
			);
		}

		const nav = [
			{
				collapsible: false,
				entries: null,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit`,
				selected: ((action === 'edit' || action === 'relate' || action === 'translate') && model === 'children'),
				text: Liferay.Language.get('details')
			},
			{
				collapsible: true,
				entries: documentsNav,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'documents'),
				text: Liferay.Language.get('documents')
			},
			{
				collapsible: true,
				entries: illnessesNav,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/illnesses/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'illnesses'),
				text: Liferay.Language.get('illness-reports')
			},
			{
				collapsible: true,
				entries: legalsNav,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/legals/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'legals'),
				text: Liferay.Language.get('legal-reports')
			},
			{
				collapsible: true,
				entries: caseworkActivitiesNav,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/casework_activities/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'casework_activities'),
				text: Liferay.Language.get('casework-activities')
			},
			{
				collapsible: true,
				entries: counselingReportsNav,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/counseling_reports/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'counseling_reports'),
				text: Liferay.Language.get('counseling-reports')
			}
		];

		return (
			<div class="children-edit page-container hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader mainHeader={childName} metaHeader={childMetaHeader} subHeader={childTypeLabel} />

					<Navigation entries={nav} navigationState={collapsedEntries} onChange={this.handleNavigationOnChange} />

					<Button label={Liferay.Language.get('print-report')} onClick={this.handlePrintReport} />
				</div>

				{this.getCurrentView(action, childDisabled, childName, entryId, model, watsonChildId)}
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
			watsonChildId
		} = this.props;

		if (shouldUpdateModel) {
			refreshSubModel(
				{
					id: watsonChildId,
					model
				}
			);

			const updateDataManuallyMethodName = `update${formatModelName(model)}DataManually`;

			const updateManuallyMethod = this.props[updateDataManuallyMethodName];

			if (updateManuallyMethod) {
				updateManuallyMethod({update: false});
			}
		}

		if (model && model !== 'children') {
			const modelConfig = WatsonConstants.inputConfig[model];

			if (modelConfig) {
				const collapsedEntryHash = `${modelConfig.pluralLabel}_${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/index`;

				if (entryId && (!lastAutoOpenedEntry || lastAutoOpenedEntry !== collapsedEntryHash)) {

					updateCollapsedEntry(watsonChildId, collapsedEntryHash, false, true);
				}
			}
		}
	}
}

EditChild.PROPS = {
	action: Config.string().value(''),
	childFormData: Config.object().value({}),
	childName: Config.string(),
	children: Config.value(new Map()),
	childrenStoreData: Config.value(new Map()),
	collapsedEntries: Config.value(''),
	entryId: Config.value(''),
	lastAutoOpenedEntry: Config.string().value(''),
	model: Config.string().value(''),
	modelFormData: Config.object().value({}),
	modelStoreData: Config.value(new Map()),
	shouldUpdateModel: Config.bool().value(false),
	watsonChildId: Config.value('')
};

EditChild.SYNC_UPDATES = true;

function mapDispatchToProps(dispatch) {
	return {
		editChildren: data => {
			dispatch(
				editChildren(data)
			);
		},
		refreshSubModel: data => {
			dispatch(
				refreshSubModel(data)
			);
		},
		updateCaseworkActivitiesDataManually: data => {
			dispatch(
				updateCaseworkActivitiesDataManually(data)
			);
		},
		updateChildrenDataManually: data => {
			dispatch(
				updateChildrenDataManually(data)
			);
		},
		updateChildrenFormData: (formData, watsonChildId) => {
			const data = {
				formData,
				watsonChildId
			};

			dispatch(
				updateChildrenFormData(data)
			);
		},
		updateCollapsedEntries: (collapsedEntries, watsonParentPrimaryKey) => {
			const data = {
				collapsedEntries,
				watsonParentPrimaryKey
			};

			dispatch(
				updateCollapsedEntries(data)
			);
		},
		updateCollapsedEntry: (watsonParentPrimaryKey, collapsedEntryHash, value, auto = false) => {
			const data = {
				auto,
				collapsedEntryHash,
				value,
				watsonParentPrimaryKey
			};

			dispatch(
				updateCollapsedEntry(data)
			);
		},
		updateCounselingReportsDataManually: data => {
			dispatch(
				updateCounselingReportsDataManually(data)
			);
		},
		updateDocumentsDataManually: data => {
			dispatch(
				updateDocumentsDataManually(data)
			);
		},
		updateIllnessesDataManually: data => {
			dispatch(
				updateIllnessesDataManually(data)
			);
		},
		updateLegalsDataManually: data => {
			dispatch(
				updateLegalsDataManually(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {action = 'edit', entryId = 0, model = 'children', watsonChildId} = props.router.params;

	const childFormData = state.getIn(['children', 'formData', watsonChildId]) || {};
	const childrenStoreData = state.getIn(['children', 'data', watsonChildId]) || new Map();
	const modelFailure = state.getIn([model, 'failure']) || false;
	const modelForbidden = state.getIn([model, 'forbidden']) || false;
	const modelFormData = state.getIn([model, 'formData', entryId]) || {};
	const modelStoreData = state.getIn([model, 'data', entryId]) || new Map();
	const shouldUpdateModel = (modelFailure || modelForbidden) ? false : state.getIn([model, 'update']);

	return {
		action,
		childFormData,
		children: state.getIn(
			[
				'children',
				'data'
			]
		),
		childrenStoreData,
		collapsedEntries: state.getIn(
			[
				'display',
				'collapsedEntries',
				watsonChildId
			]
		),
		entryId,
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
		watsonChildId
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(EditChild);