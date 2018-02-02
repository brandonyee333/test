import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import {convertMapToObject, deepCompareIsEqual, formatModelName, getModifiedMoment, updateDOMTitle} from '../../lib/util';
import Form from '../../components/Form';
import Modal from '../../components/Modal';

import {
	destroyCaseworkActivities,
	editCaseworkActivities,
	updateCaseworkActivities,
	updateCaseworkActivitiesDataManually,
	updateCaseworkActivitiesFormData
} from '../../actions/casework-activities';

import {
	destroyCounselingReports,
	editCounselingReports,
	updateCounselingReports,
	updateCounselingReportsDataManually,
	updateCounselingReportsFormData
} from '../../actions/counseling-reports';

import {
	destroyDocuments,
	editDocuments,
	updateDocuments,
	updateDocumentsDataManually,
	updateDocumentsFormData
} from '../../actions/documents';

import {
	destroyIllnesses,
	editIllnesses,
	updateIllnesses,
	updateIllnessesDataManually,
	updateIllnessesFormData
} from '../../actions/illnesses';

import {
	destroyLegals,
	editLegals,
	updateLegals,
	updateLegalsDataManually,
	updateLegalsFormData
} from '../../actions/legals';

import {
	destroyPhysicalExams,
	editPhysicalExams,
	updatePhysicalExams,
	updatePhysicalExamsDataManually,
	updatePhysicalExamsFormData
} from '../../actions/physical-exams';

import {
	destroyProgressReports,
	editProgressReports,
	updateProgressReports,
	updateProgressReportsDataManually,
	updateProgressReportsFormData
} from '../../actions/progress-reports';

class GenericChildForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {model, watsonPrimaryKey} = props;

		if (model && watsonPrimaryKey) {
			const editModelMethod = props[`edit${formatModelName(model, true)}`];

			editModelMethod(watsonPrimaryKey);
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
			'handleTranslationRequest',
			'handleUpdateFormData'
		);
	}

	detached() {
		const {
			action,
			model,
			response
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			const updateModelDataManuallyMethod = this.props[`update${formatModelName(model, true)}DataManually`];

			updateModelDataManuallyMethod(
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

		const {model, watsonChildId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		const {props} = this;

		const updateModelMethod = props[`update${formatModelName(props.model, true)}`];

		updateModelMethod(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {props} = this;

		const {model, watsonChildId, watsonPrimaryKey} = props;

		if (watsonPrimaryKey) {
			const destroyModelMethod = props[`destroy${formatModelName(model, true)}`];

			destroyModelMethod(watsonPrimaryKey);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/index`);
		}
	}

	handleLeave() {
		this.handleClearFormData();

		this.handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	handleTranslationRequest() {
		const {props} = this;

		const {model, watsonChildId, watsonPrimaryKey} = props;

		const requestModelTranslationMethod = props[`request${formatModelName(model, true)}Translation`];

		const translationURL = `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/${watsonPrimaryKey}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey
		};

		requestModelTranslationMethod(translationRequestData);
	}

	handleUpdateFormData(formData) {
		const {props} = this;

		const {model, watsonPrimaryKey} = props;

		const updateModelFormData = props[`update${formatModelName(model, true)}FormData`];

		updateModelFormData(formData, watsonPrimaryKey);
	}

	render() {
		const {props} = this;

		const {
			action,
			button,
			buttonLabel,
			disabled,
			errors,
			fieldConfig,
			formConfig,
			formData,
			loading,
			model,
			modelKey,
			modelLabel,
			response,
			showTranslationButtons,
			storeData = props.data,
			watsonChildId
		} = props;

		let {
			cancelMethod,
			headerStringLeft,
			headerStringRight,
			submitMethod = props[`update${formatModelName(model, true)}`],
			watsonPrimaryKey
		} = props;

		const {
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response && action === 'create') {
			if (response.get('status') === 'success') {
				const responseData = response.get('data');

				watsonPrimaryKey = responseData.get('id');

				if (watsonPrimaryKey) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/${watsonPrimaryKey}/edit`);
				}
			}
			else if (errors) {
				this.state.dataSent = false;
			}
		}

		let deleteMethod;
		let reportHref;
		let requestTranslationMethod;
		let translateHref;

		if (action === 'edit') {
			deleteMethod = disabled ? undefined : this.handleDelete;

			headerStringLeft = storeData.get('name') || sub(Liferay.Language.get('edit-x'), modelLabel);
			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			reportHref = `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/${watsonPrimaryKey}/report`;
			requestTranslationMethod = showTranslationButtons ? this.handleTranslationRequest : null;

			if (!disabled && WatsonConstants.currentUser.translatorRole && showTranslationButtons) {
				translateHref = `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/${watsonPrimaryKey}/translate`;
			}
		}
		else if (action === 'create' && watsonChildId) {
			cancelMethod = this.handleCancel;
			headerStringLeft = sub(Liferay.Language.get('create-x'), modelLabel);
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
						fieldConfig={fieldConfig}
						formConfig={formConfig}
						formData={formData}
						loading={loading}
						model={model}
						modelKey={modelKey}
						reportHref={reportHref}
						requestTranslationMethod={requestTranslationMethod}
						response={response}
						storeData={storeData}
						submitMethod={submitMethod}
						translateHref={translateHref}
						updateFormData={this.handleUpdateFormData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={watsonPrimaryKey}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {childName, modelLabel, storeData} = this.props;

		const modelName = sub(Liferay.Language.get('x-x'), modelLabel, storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('child-x-x'), childName, modelName));
	}
}

GenericChildForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string(),
	modelKey: Config.any(),
	modelLabel: Config.string(),
	response: Config.object(),
	showTranslationButtons: Config.bool().value(true),
	storeData: Config.value(null),
	watsonChildId: Config.value(''),
	watsonPrimaryKey: Config.value('')
};

GenericChildForm.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapDispatchToProps(dispatch) {
	return {
		destroyCaseworkActivities: watsonReportId => {
			dispatch(
				destroyCaseworkActivities(watsonReportId)
			);
		},
		destroyCounselingReports: watsonReportId => {
			dispatch(
				destroyCounselingReports(watsonReportId)
			);
		},
		destroyDocuments: watsonDocumentId => {
			dispatch(
				destroyDocuments(watsonDocumentId)
			);
		},
		destroyIllnesses: watsonReportId => {
			dispatch(
				destroyIllnesses(watsonReportId)
			);
		},
		destroyLegals: watsonReportId => {
			dispatch(
				destroyLegals(watsonReportId)
			);
		},
		destroyPhysicalExams: watsonReportId => {
			dispatch(
				destroyPhysicalExams(watsonReportId)
			);
		},
		destroyProgressReports: watsonReportId => {
			dispatch(
				destroyProgressReports(watsonReportId)
			);
		},
		editCaseworkActivities: watsonReportId => {
			dispatch(
				editCaseworkActivities(watsonReportId)
			);
		},
		editCounselingReports: watsonReportId => {
			dispatch(
				editCounselingReports(watsonReportId)
			);
		},
		editDocuments: watsonDocumentId => {
			dispatch(
				editDocuments(watsonDocumentId)
			);
		},
		editIllnesses: watsonDocumentId => {
			dispatch(
				editIllnesses(watsonDocumentId)
			);
		},
		editLegals: watsonReportId => {
			dispatch(
				editLegals(watsonReportId)
			);
		},
		editPhysicalExams: watsonReportId => {
			dispatch(
				editPhysicalExams(watsonReportId)
			);
		},
		editProgressReports: watsonReportId => {
			dispatch(
				editProgressReports(watsonReportId)
			);
		},
		updateCaseworkActivities: data => {
			dispatch(
				updateCaseworkActivities(data)
			);
		},
		updateCaseworkActivitiesDataManually: data => {
			dispatch(
				updateCaseworkActivitiesDataManually(data)
			);
		},
		updateCaseworkActivitiesFormData: (formData, watsonReportId = 0) => {
			const data = {
				formData,
				watsonReportId
			};

			dispatch(
				updateCaseworkActivitiesFormData(data)
			);
		},
		updateCounselingReports: data => {
			dispatch(
				updateCounselingReports(data)
			);
		},
		updateCounselingReportsDataManually: data => {
			dispatch(
				updateCounselingReportsDataManually(data)
			);
		},
		updateCounselingReportsFormData: (formData, watsonReportId = 0) => {
			const data = {
				formData,
				watsonReportId
			};

			dispatch(
				updateCounselingReportsFormData(data)
			);
		},
		updateDocuments: data => {
			dispatch(
				updateDocuments(data)
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
		},
		updateIllnesses: data => {
			dispatch(
				updateIllnesses(data)
			);
		},
		updateIllnessesDataManually: data => {
			dispatch(
				updateIllnessesDataManually(data)
			);
		},
		updateIllnessesFormData: (formData, watsonReportId = 0) => {
			const data = {
				formData,
				watsonReportId
			};

			dispatch(
				updateIllnessesFormData(data)
			);
		},
		updateLegals: data => {
			dispatch(
				updateLegals(data)
			);
		},
		updateLegalsDataManually: data => {
			dispatch(
				updateLegalsDataManually(data)
			);
		},
		updateLegalsFormData: (formData, watsonReportId = 0) => {
			const data = {
				formData,
				watsonReportId
			};

			dispatch(
				updateLegalsFormData(data)
			);
		},
		updatePhysicalExams: data => {
			dispatch(
				updatePhysicalExams(data)
			);
		},
		updatePhysicalExamsDataManually: data => {
			dispatch(
				updatePhysicalExamsDataManually(data)
			);
		},
		updatePhysicalExamsFormData: (formData, watsonReportId = 0) => {
			const data = {
				formData,
				watsonReportId
			};

			dispatch(
				updatePhysicalExamsFormData(data)
			);
		},
		updateProgressReports: data => {
			dispatch(
				updateProgressReports(data)
			);
		},
		updateProgressReportsDataManually: data => {
			dispatch(
				updateProgressReportsDataManually(data)
			);
		},
		updateProgressReportsFormData: (formData, watsonReportId = 0) => {
			const data = {
				formData,
				watsonReportId
			};

			dispatch(
				updateProgressReportsFormData(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {model, watsonPrimaryKey = 0} = props;

	const errors = state.getIn([model, 'errors']) || new Map();

	return {
		errors,
		loading: state.getIn(
			[
				model,
				'loading'
			]
		),
		modelLabel: !model ? '' : WatsonConstants.inputConfig[model].singularLabel,
		response: state.getIn(
			[
				model,
				'response'
			]
		),
		watsonPrimaryKey
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(GenericChildForm);