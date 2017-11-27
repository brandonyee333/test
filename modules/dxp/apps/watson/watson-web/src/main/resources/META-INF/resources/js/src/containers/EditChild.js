import {bindAll, capitalize} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../components/Button';
import ChildForm from './forms/Child';
import DocumentForm from './forms/Document';
import Navigation from '../components/Navigation';
import NavigationHeader from '../components/NavigationHeader';
import Sort from '../components/Sort';
import TranslateChildForm from './forms/TranslateChild';
import ViewIndex from './views/ViewIndex';

import {editChild, refreshSubModel, updateChildrenDataManually, updateChildrenFormData} from '../actions/children';
import {updateCollapsedEntries, updateCollapsedEntry} from '../actions/display';

import {getOptionsLabelFromWatsonConstants} from '../lib/util';

class EditChild extends JSXComponent {
	attached() {
		const {props} = this;

		const {childrenStoreData} = props;

		if (childrenStoreData.size < 1) {
			props.editChild(props.router.params.watsonChildId);
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

	getCurrentView(action, entryId, childDisabled, childName, model, watsonChildId) {
		const {props} = this;

		let view;

		const modelCreateMethod = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/create`);

		if (model === 'documents') {
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
					<DocumentForm
						action={action}
						childName={childName}
						disabled={childDisabled}
						formData={props.modelFormData}
						storeData={props.modelStoreData}
						watsonChildId={watsonChildId}
						watsonDocumentId={entryId}
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
				<TranslateChildForm
					action={action}
					childName={childName}
					disabled={childDisabled}
					storeData={props.childrenStoreData}
					watsonChildId={watsonChildId}
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

		const childDisabled = childrenStoreData.get('disabled');
		const childMetaHeader = `${sub(Liferay.Language.get('created-by-x-on-x'), childrenStoreData.get('reportedBy') || '', childrenStoreData.get('createDate') || '')}`;
		const childName = childrenStoreData.get('name') || Liferay.Language.get('loading');

		const childTypeLabel = getOptionsLabelFromWatsonConstants('children', 'typeWatsonListTypeId', childrenStoreData.get('typeWatsonListTypeId'));

		const documentsNav = [];

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

		const nav = [
			{
				collapsible: false,
				entries: null,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit`,
				selected: !model,
				text: Liferay.Language.get('details')
			},
			{
				collapsible: true,
				entries: documentsNav,
				href: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/index`,
				selected: ((action === 'create' || action === 'index' || action === 'import') && model === 'documents'),
				text: Liferay.Language.get('documents')
			}
		];

		return (
			<div class="children-edit page-container hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader mainHeader={childName} metaHeader={childMetaHeader} subHeader={childTypeLabel} />

					<Navigation entries={nav} navigationState={collapsedEntries} onChange={this.handleNavigationOnChange} />

					<Button label={Liferay.Language.get('print-report')} onClick={this.handlePrintReport} />
				</div>

				{this.getCurrentView(action, entryId, childDisabled, childName, model, watsonChildId)}
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

			const updateDataManuallyMethodName = `update${capitalize(model)}DataManually`;

			const updateManuallyMethod = this.props[updateDataManuallyMethodName];

			if (updateManuallyMethod) {
				updateManuallyMethod({update: false});
			}
		}

		if (model) {
			const collapsedEntryHash = `${WatsonConstants.inputConfig[model].pluralLabel}_${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/index`;

			if (entryId && (!lastAutoOpenedEntry || lastAutoOpenedEntry !== collapsedEntryHash)) {

				updateCollapsedEntry(watsonChildId, collapsedEntryHash, false, true);
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
		editChild: data => {
			dispatch(
				editChild(data)
			);
		},
		refreshSubModel: data => {
			dispatch(
				refreshSubModel(data)
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
		updateCollapsedEntries: (collapsedEntries, watsonChildId) => {
			const data = {
				collapsedEntries,
				watsonChildId
			};

			dispatch(
				updateCollapsedEntries(data)
			);
		},
		updateCollapsedEntry: (watsonChildId, collapsedEntryHash, value, auto = false) => {
			const data = {
				auto,
				collapsedEntryHash,
				value,
				watsonChildId
			};

			dispatch(
				updateCollapsedEntry(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {action = 'edit', entryId = 0, model, watsonChildId} = props.router.params;

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