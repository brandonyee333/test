import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import {convertMapToObject, deepCompareIsEqual, getModifiedMoment, updateDOMTitle} from '../../lib/util';
import Form from '../../components/Form';
import Modal from '../../components/Modal';

import {
	destroyDocument,
	editDocument,
	updateDocument,
	updateDocumentsDataManually,
	updateDocumentsFormData
} from '../../actions/documents';

class DocumentForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonDocumentId} = props;

		if (watsonDocumentId) {
			props.editDocument(watsonDocumentId);
		}

		Router.router().on('beforeNavigate', this.handleBeforeLeave);
	}

	created() {
		bindAll(
			this,
			'handleBeforeLeave',
			'handleCancel',
			'handleClearFormData',
			'handleClose',
			'handleCreate',
			'handleDelete',
			'handleLeave',
			'handleUpdateFormData'
		);
	}

	detached() {
		const {
			action,
			response,
			updateDocumentsDataManually
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updateDocumentsDataManually(
				{
					response: {
						message: null
					}
				}
			);
		}

		Router.router().off('beforeNavigate', this.handleBeforeLeave);

		this.handleClearFormData();
	}

	getConfig() {
		return [
			'id',
			'imagePayload',
			'receivedDate',
			'originalDocument',
			'parentTypeWatsonListTypeId',
			'typeWatsonListTypeId',
			'subtypeWatsonListTypeId',
			'watsonRelationships'
		];
	};

	handleBeforeLeave(data) {
		const {
			action,
			formData = {},
			storeData,
			watsonChildId
		} = this.props;

		const {
			dataSent,
			unlockNavigate
		} = this.state;

		if (watsonChildId > 0 && !isEmpty(formData) && (!isEmpty(storeData) || action === 'create' && !dataSent)) {
			const originalData = convertMapToObject(storeData);

			if (!unlockNavigate && !deepCompareIsEqual(formData, originalData)) {
				this.setState(
					{
						navigateAwayPath: data.path,
						showLeaveModal: true
					}
				);

				if (data.event) {
					data.event.preventDefault();
				}

				throw new Error();
			}
		}
	}

	handleCancel() {
		this.handleClearFormData();

		const {watsonChildId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		this.props.updateDocument(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {watsonChildId, watsonDocumentId} = this.props;

		if (watsonDocumentId) {
			this.props.destroyDocument(watsonDocumentId);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documentsindex`);
		}
	}

	handleLeave() {
		this.handleClearFormData();

		this.handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	handleUpdateFormData(formData) {
		const {
			updateDocumentsFormData,
			watsonDocumentId
		} = this.props;

		updateDocumentsFormData(formData, watsonDocumentId);
	}

	render() {
		const {props} = this;

		const {
			action,
			button,
			buttonLabel,
			disabled,
			errors,
			formData,
			loading,
			model,
			response,
			storeData = props.data,
			watsonChildId
		} = props;

		let {
			cancelMethod,
			headerStringLeft = Liferay.Language.get('create-document'),
			headerStringRight,
			submitMethod = props.updateDocument,
			watsonDocumentId
		} = props;

		const {
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response && action === 'create') {
			if (response.get('status') === 'success') {
				const responseData = response.get('data');

				watsonDocumentId = responseData.get('watsonDocumentId');

				if (watsonDocumentId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/${watsonDocumentId}/edit`);
				}
			}
			else if (errors) {
				this.state.dataSent = false;
			}
		}

		let deleteMethod;
		let reportHref;

		if (action === 'edit') {
			deleteMethod = disabled ? undefined : this.handleDelete;

			headerStringLeft = storeData.get('name') || Liferay.Language.get('edit-document');
			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			reportHref = `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/${watsonDocumentId}/report`;
		}
		else if (action === 'create' && watsonChildId) {
			cancelMethod = this.handleCancel;
			headerStringRight = Liferay.Language.get('unsaved');
			submitMethod = this.handleCreate;
		}

		const modalFooter = [
			<Button className="modal-button" key="btn-action" label={Liferay.Language.get('watson-leave')} onClick={this.handleLeave} />,
			<Button className="modal-button" key="btn-cancel" label={Liferay.Language.get('stay')} onclick={this.handleClose} />
		];

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} headerStringRight={headerStringRight} />
				</div>

				{showLeaveModal &&
					<Modal body={Liferay.Language.get('you-have-unsaved-changes-that-will-be-lost-if-you-continue')} close={this.handleClose} footer={modalFooter} header={Liferay.Language.get('do-you-want-to-leave-this-page')} />
				}

				<div class="content">
					<Form
						action={action}
						button={button}
						buttonLabel={buttonLabel}
						cancelMethod={cancelMethod}
						deleteMethod={deleteMethod}
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.documents.inputs}
						formConfig={this.getConfig()}
						formData={formData}
						loading={loading}
						model={model}
						reportHref={reportHref}
						response={response}
						storeData={storeData}
						submitMethod={submitMethod}
						updateFormData={this.handleUpdateFormData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={watsonDocumentId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {childName, storeData} = this.props;

		const documentName = sub(Liferay.Language.get('document-x'), storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('child-x-x'), childName, documentName));
	}
}

DocumentForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('children'),
	response: Config.object(),
	storeData: Config.value(null),
	watsonChildId: Config.value(''),
	watsonDocumentId: Config.value('')
};

DocumentForm.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {watsonDocumentId = 0} = props;

	const errors = state.getIn(['documents', 'errors']) || new Map();

	return {
		errors,
		loading: state.getIn(
			[
				'documents',
				'loading'
			]
		),
		response: state.getIn(
			[
				'documents',
				'response'
			]
		),
		watsonDocumentId
	};
}

function mapDispatchToProps(dispatch) {
	return {
		destroyDocument: watsonDocumentId => {
			dispatch(
				destroyDocument(watsonDocumentId)
			);
		},
		editDocument: watsonDocumentId => {
			dispatch(
				editDocument(watsonDocumentId)
			);
		},
		updateDocument: data => {
			dispatch(
				updateDocument(data)
			);
		},
		updateDocumentsDataManually: data => {
			dispatch(
				updateDocumentsDataManually(data)
			);
		},
		updateDocumentsFormData: (formData, watsonDocumentId = 0) => {
			const data = {
				formData,
				watsonDocumentId
			};

			dispatch(
				updateDocumentsFormData(data)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(DocumentForm);