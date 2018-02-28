import {bindAll, isEmpty} from 'lodash';
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

import {
	indexIncidents,
	requestIncidentsTranslation,
	updateIncidents,
	updateIncidentsDataManually,
	updateIncidentsFormData
} from '../../actions/incidents';

class IncidentForm extends JSXComponent {
	attached() {
		Router.router().on('beforeNavigate', this._handleBeforeLeave);

		window.onbeforeunload = this._handleBeforeLeave;
	}

	created() {
		bindAll(
			this,
			'_handleBeforeLeave',
			'_handleClearFormData',
			'_handleClose',
			'_handleLeave',
			'_handleSubmit',
			'_handleTranslationRequest',
			'_handleUpdateFormData'
		);
	}

	detached() {
		const {
			action,
			response,
			updateIncidentsDataManually
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updateIncidentsDataManually(
				{
					response: {
						message: null
					}
				}
			);
		}

		Router.router().off('beforeNavigate', this._handleBeforeLeave);

		window.onbeforeunload = undefined;
	}

	getConfig() {
		return [
			'incidentStatus',
			'sourceWatsonListTypeId',
			'typeWatsonListTypeId',
			'victimAdultCount',
			'victimChildCount',
			'audienceAdultCount',
			'audienceChildCount',
			'expenses',
			'subtypeWatsonListTypeId',
			'externalCase',
			'externalCaseId',
			'externalCaseWatsonListTypeId',
			'reportDate',
			'startDate',
			'endDate',
			'natureWatsonListType',
			'description',
			'watsonIncidentRelIds'
		];
	}

	_handleBeforeLeave(data) {
		const {
			formData = {},
			storeData,
			watsonIncidentId
		} = this.props;

		const {unlockNavigate} = this.state;

		let retVal = false;

		if (watsonIncidentId > 0) {
			let pathMatches;

			if (data && data.path) {
				pathMatches = data.path.match(/\/web\/guest\/home\/-\/watson\/incidents\/([0-9]+)\/[a-zA-Z]+\/?$/);
			}

			const pathChanged = !pathMatches || pathMatches[1] != watsonIncidentId;

			if (pathChanged && !isEmpty(formData) && !isEmpty(storeData)) {
				const originalData = convertMapToObject(storeData);

				if (!unlockNavigate && !deepCompareIsEqual(formData, originalData)) {
					if (data) {
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
					else {
						retVal = true;
					}
				}
			}
		}

		return retVal;
	}

	_handleClearFormData() {
		this._handleUpdateFormData({});
	}

	_handleClose() {
		this.setState({showLeaveModal: false});
	}

	_handleLeave() {
		this._handleClearFormData();

		this._handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	_handleSubmit(formData) {
		this.props.updateIncidents(formData);
	}

	_handleTranslationRequest() {
		const {props} = this;

		const {model, requestIncidentsTranslation, watsonIncidentId} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/${model}/${watsonIncidentId}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey: watsonIncidentId
		};

		requestIncidentsTranslation(translationRequestData);
	}

	_handleUpdateFormData(formData) {
		const {
			updateIncidentsFormData,
			watsonIncidentId
		} = this.props;

		updateIncidentsFormData(formData, watsonIncidentId);
	}

	render() {
		const {props} = this;

		const {
			action,
			buttonLabel,
			cancelMethod,
			disabled,
			errors,
			formData,
			headerStringLeft = Liferay.Language.get('edit-incident'),
			loading,
			model,
			response,
			storeData,
			submitMethod = this._handleSubmit,
			watsonIncidentId
		} = props;

		let {headerStringRight = ''} = this.props;

		const {showLeaveModal} = this.state;

		let message = '';
		let relateOnClick;
		let requestTranslationMethod;
		let translateHref;

		if (action === 'create') {
			message = sub(Liferay.Language.get('step-x-of-x'), '1', '2');
		}
		else {
			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			relateOnClick = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/relate`);

			requestTranslationMethod = this._handleTranslationRequest;

			if (!disabled && WatsonConstants.currentUser.translatorRole) {
				translateHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/translate`;
			}
		}

		const modalFooter = [
			<Button className="modal-button" key="btn-action" label={Liferay.Language.get('watson-leave')} onClick={this._handleLeave} />,
			<Button className="modal-button" key="btn-cancel" label={Liferay.Language.get('stay')} onclick={this._handleClose} />
		];

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} headerStringRight={headerStringRight} />
				</div>

				{showLeaveModal &&
					<Modal body={Liferay.Language.get('you-have-unsaved-changes-that-will-be-lost-if-you-continue')} close={this._handleClose} footer={modalFooter} header={Liferay.Language.get('do-you-want-to-leave-this-page')} />
				}

				<div class="content">
					<Form
						action={action}
						buttonLabel={buttonLabel}
						cancelMethod={cancelMethod}
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.incidents.inputs}
						formConfig={this.getConfig()}
						formData={formData}
						loading={loading}
						message={message}
						model={model}
						relateOnClick={relateOnClick}
						requestTranslationMethod={requestTranslationMethod}
						response={response}
						storeData={storeData}
						submitMethod={submitMethod}
						translateHref={translateHref}
						updateFormData={this._handleUpdateFormData}
						watsonIncidentId={watsonIncidentId}
						watsonPrimaryKey={watsonIncidentId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName} = this.props;

		updateDOMTitle(sub(Liferay.Language.get('incident-x'), incidentName || ''));
	}
}

IncidentForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('incidents'),
	response: Config.object(),
	storeData: Config.value(null),
	watsonIncidentId: Config.value('')
};

IncidentForm.STATE = {
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	let errors = state.getIn(['incidents', 'errors']) || new Map();

	const backendErrors = state.getIn(
		[
			'incidents',
			'errors',
			'incidentErrors'
		]
	);

	if (backendErrors) {
		errors = errors.withMutations(
			map => {
				backendErrors.forEach(
					(backendError, key) => {
						map.set(key, backendError);
					}
				);
			}
		);
	}

	const {watsonIncidentId = 0} = props;

	const loading = state.getIn(['incidents', 'loading']) || false;

	return {
		errors,
		loading,
		response: state.getIn(
			[
				'incidents',
				'response'
			]
		),
		watsonIncidentId
	};
}

function mapDispatchToProps(dispatch) {
	return {
		indexIncidents: data => {
			dispatch(
				indexIncidents(data)
			);
		},
		requestIncidentsTranslation: data => {
			dispatch(
				requestIncidentsTranslation(data)
			);
		},
		updateIncidents: data => {
			dispatch(
				updateIncidents(data)
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
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(IncidentForm);