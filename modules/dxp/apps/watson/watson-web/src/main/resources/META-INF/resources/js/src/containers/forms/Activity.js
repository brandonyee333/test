import {bindAll, isEmpty, isEqual} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import {convertMapToObject, deepCompareIsEqual, getModifiedMoment, updateDOMTitle} from '../../lib/util';
import Form from '../../components/Form';
import Modal from '../../components/Modal';
import sendRequest from '../../lib/request';

import {
	destroyActivities,
	editActivities,
	fetchActivitiesDraft,
	requestActivitiesTranslation,
	updateActivities,
	updateActivitiesDataManually,
	updateActivitiesFormData
} from '../../actions/activities';

class ActivityForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {
			action,
			watsonActivityId,
			watsonIncidentId
		} = props;

		if (watsonActivityId) {
			props.editActivities(watsonActivityId);
		}
		else if (action === 'create' && watsonIncidentId) {
			props.fetchActivitiesDraft(watsonIncidentId);
		}

		Router.router().on('beforeNavigate', this.handleBeforeLeave);

		this.state.intervalId = setInterval(() => this.handleAutoSave(), 60000);
	}

	created() {
		bindAll(
			this,
			'handleAutoCreate',
			'handleAutoSave',
			'handleBeforeLeave',
			'handleCancel',
			'handleClearFormData',
			'handleClose',
			'handleCreate',
			'handleDelete',
			'handleLeave',
			'handleTranslationRequest',
			'handleUpdate',
			'handleUpdateFormData'
		);
	}

	detached() {
		const {
			action,
			response,
			updateActivitiesDataManually
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updateActivitiesDataManually(
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

	disposed() {
		const {intervalId} = this.state;

		clearInterval(intervalId);
	}

	getConfig() {
		return [
			'id',
			'typeWatsonListTypeId',
			'subtypeWatsonListTypeId',
			'activityResource',
			'startDate',
			'reportDate',
			'watsonRelationships',
			'narrative'
		];
	};

	handleAutoCreate() {
		const {
			autoCreateActivities,
			formData = {},
			watsonActivityId = 0,
			watsonIncidentId
		} = this.props;

		if (formData) {
			let autoSavePermitted = false;

			const activityKeys = this.getConfig();

			activityKeys.forEach(
				key => {
					autoSavePermitted = autoSavePermitted ? autoSavePermitted : formData[key];
				}
			);

			if (autoSavePermitted) {
				formData.id = watsonActivityId;
				formData.watsonIncidentId = watsonIncidentId;

				autoCreateActivities(formData);

				this.setState(
					{
						autoCreated: true,
						autoSaved: Date.now(),
						dataSent: true
					}
				);
			}
		}
	}

	handleAutoSave() {
		const {
			action,
			disabled,
			formData,
			loading,
			requestFailure,
			watsonActivityId = 0,
			watsonIncidentId
		} = this.props;

		if (!requestFailure && !loading && !disabled) {
			if (action === 'create') {
				this.handleAutoCreate();
			}
			else {
				formData.id = watsonActivityId;
				formData.watsonIncidentId = watsonIncidentId;

				sendRequest(
					{
						controller: 'activities',
						controllerMethod: 'autoSave.json',
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
	}

	handleBeforeLeave(data) {
		const {
			action,
			formData = {},
			storeData,
			watsonIncidentId
		} = this.props;

		const {
			autoSaved,
			autoSaveResponse,
			dataSent,
			unlockNavigate
		} = this.state;

		if (watsonIncidentId > 0 && !isEmpty(formData) && (!isEmpty(storeData) || action === 'create' && !dataSent)) {
			const originalData = convertMapToObject(storeData);

			if (autoSaved) {
				autoSaveResponse.modifiedDateTimeStamp = 0;
				autoSaveResponse.modifiedDate = 0;
				formData.modifiedDate = 0;
				formData.modifiedDateTimeStamp = 0;
			}

			if (!unlockNavigate && ((autoSaved && !isEqual(formData, autoSaveResponse)) || (!deepCompareIsEqual(formData, originalData) && autoSaved < 1))) {
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

		const {watsonIncidentId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		this.props.updateActivities(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {destroyActivities, watsonActivityId, watsonIncidentId} = this.props;

		if (watsonActivityId) {
			destroyActivities(watsonActivityId);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/index`);
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

		const {model, requestActivitiesTranslation, watsonActivityId, watsonIncidentId} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonActivityId}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey: watsonActivityId
		};

		requestActivitiesTranslation(translationRequestData);
	}

	handleUpdate(data) {
		this.props.updateActivities(data);

		this.state.autoCreated = false;
	}

	handleUpdateFormData(formData) {
		const {
			updateActivitiesFormData,
			watsonActivityId
		} = this.props;

		updateActivitiesFormData(formData, watsonActivityId);
	}

	render() {
		const {props} = this;

		const {
			action,
			button,
			buttonLabel,
			errors,
			formData,
			loading,
			model,
			response,
			storeData = props.data,
			watsonIncidentId
		} = props;

		let {
			cancelMethod,
			disabled,
			headerStringLeft = Liferay.Language.get('create-activity'),
			headerStringRight,
			submitMethod = this.handleUpdate,
			watsonActivityId
		} = props;

		const {
			autoSaved,
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response && action === 'create') {
			if (response.get('status') === 'success') {
				const responseData = response.get('data');

				watsonActivityId = responseData.get('watsonActivityId');

				if (watsonActivityId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/${watsonActivityId}/edit`);
				}
			}
			else if (errors) {
				this.state.dataSent = false;
			}
		}

		let deleteMethod;
		let message;
		let reportHref;
		let requestTranslationMethod;
		let translateHref;

		if (action === 'edit') {
			if (storeData) {
				reportHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/${watsonActivityId}/report`;

				if (!disabled && WatsonConstants.currentUser.translatorRole) {
					translateHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/${watsonActivityId}/translate`;
				}

				disabled = disabled || !storeData.get('editable');
			}

			deleteMethod = disabled ? undefined : this.handleDelete;

			requestTranslationMethod = this.handleTranslationRequest;

			headerStringLeft = storeData.get('name') || Liferay.Language.get('edit-activity');

			headerStringRight = !autoSaved ? getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp')) : getModifiedMoment(storeData.get('modifiedUserName'), autoSaved);

			message = autoSaved ? Liferay.Language.get('activity-automatically-saved') : '';
		}
		else if (action === 'create' && watsonIncidentId) {
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
						autoSaved={autoSaved}
						button={button}
						buttonLabel={buttonLabel}
						cancelMethod={cancelMethod}
						deleteMethod={deleteMethod}
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.activities.inputs}
						formConfig={this.getConfig()}
						formData={formData}
						loading={loading}
						message={message}
						model={model}
						reportHref={reportHref}
						requestTranslationMethod={requestTranslationMethod}
						response={response}
						storeData={storeData}
						submitMethod={submitMethod}
						translateHref={translateHref}
						updateFormData={this.handleUpdateFormData}
						watsonIncidentId={watsonIncidentId}
						watsonPrimaryKey={watsonActivityId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName, storeData} = this.props;

		const activityName = sub(Liferay.Language.get('activity-x'), storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, activityName));
	}
}

ActivityForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('activities'),
	requestFailure: Config.bool(),
	response: Config.object(),
	storeData: Config.value(null),
	watsonActivityId: Config.value(''),
	watsonIncidentId: Config.value('')
};

ActivityForm.STATE = {
	autoCreated: Config.bool().value(false),
	autoSaved: Config.number().value(0),
	autoSaveResponse: Config.object(),
	dataSent: Config.bool().value(false),
	intervalId: Config.value(null),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {watsonActivityId = 0} = props;

	const errors = state.getIn(['activities', 'errors']) || new Map();
	const requestFailure = state.getIn(['activities', 'response', 'failure']);

	return {
		errors,
		loading: state.getIn(
			[
				'activities',
				'loading'
			]
		),
		requestFailure,
		response: state.getIn(
			[
				'activities',
				'response'
			]
		),
		watsonActivityId
	};
}

function mapDispatchToProps(dispatch) {
	return {
		autoCreateActivities: data => {
			dispatch(
				updateActivities(data, 'autoCreate.json')
			);
		},
		destroyActivities: watsonActivityId => {
			dispatch(
				destroyActivities(watsonActivityId)
			);
		},
		editActivities: watsonActivityId => {
			dispatch(
				editActivities(watsonActivityId)
			);
		},
		fetchActivitiesDraft: watsonIncidentId => {
			dispatch(
				fetchActivitiesDraft({watsonIncidentId})
			);
		},
		requestActivitiesTranslation: data => {
			dispatch(
				requestActivitiesTranslation(data)
			);
		},
		updateActivities: data => {
			dispatch(
				updateActivities(data)
			);
		},
		updateActivitiesDataManually: data => {
			dispatch(
				updateActivitiesDataManually(data)
			);
		},
		updateActivitiesFormData: (formData, watsonActivityId = 0) => {
			const data = {
				formData,
				watsonActivityId
			};

			dispatch(
				updateActivitiesFormData(data)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ActivityForm);