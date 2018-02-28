import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import AffiliationLink from '../../components/AffiliationLink';
import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import {convertMapToObject, deepCompareIsEqual, formatModelName, getModifiedMoment} from '../../lib/util';
import Form from '../../components/Form';
import Modal from '../../components/Modal';
import PersonChildLink from '../../components/PersonChildLink';

import {updatePageTitle} from '../../actions/display';

import {
	destroyPeople,
	editPeople,
	requestPeopleTranslation,
	updatePeople,
	updatePeopleDataManually,
	updatePeopleFormData
} from '../../actions/people';

import {
	destroyResources,
	editResources,
	requestResourcesTranslation,
	updateResources,
	updateResourcesDataManually,
	updateResourcesFormData
} from '../../actions/resources';

import {
	destroyVehicles,
	editVehicles,
	requestVehiclesTranslation,
	updateVehicles,
	updateVehiclesDataManually,
	updateVehiclesFormData
} from '../../actions/vehicles';

class GenericIncidentForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {model, watsonPrimaryKey} = props;

		if (model && watsonPrimaryKey) {
			const editModelMethod = props[`edit${formatModelName(model, true)}`];

			editModelMethod(watsonPrimaryKey);
		}

		Router.router().on('beforeNavigate', this._handleBeforeLeave);

		window.onbeforeunload = this._handleBeforeLeave;
	}

	created() {
		bindAll(
			this,
			'_handleBeforeLeave',
			'_handleCancel',
			'_handleClearFormData',
			'_handleClose',
			'_handleCreate',
			'_handleDelete',
			'_handleLeave',
			'_handleTranslationRequest',
			'_handleUpdateFormData'
		);

		const {modelLabel, updatePageTitle, watsonPrimaryKey} = this.props;

		const modelName = sub(Liferay.Language.get('x-x'), modelLabel, watsonPrimaryKey);

		updatePageTitle(modelName);
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

		window.onbeforeunload = undefined;

		Router.router().off('beforeNavigate', this._handleBeforeLeave);

		this._handleClearFormData();
	}

	_handleBeforeLeave(data) {
		const {
			action,
			formData = {},
			storeData,
			watsonIncidentId
		} = this.props;

		const {
			dataSent,
			unlockNavigate
		} = this.state;

		let retVal = false;

		if (watsonIncidentId > 0 && !isEmpty(formData) && (!isEmpty(storeData) || action === 'create' && !dataSent)) {
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

		return retVal;
	}

	_handleCancel() {
		this._handleClearFormData();

		const {model, watsonIncidentId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/index`);
	}

	_handleClearFormData() {
		this._handleUpdateFormData({});
	}

	_handleClose() {
		this.setState({showLeaveModal: false});
	}

	_handleCreate(data) {
		const {props} = this;

		const updateModelMethod = props[`update${formatModelName(props.model, true)}`];

		updateModelMethod(data);

		this.state.dataSent = true;
	}

	_handleDelete() {
		const {props} = this;

		const {model, watsonIncidentId, watsonPrimaryKey} = props;

		if (watsonPrimaryKey) {
			const destroyModelMethod = props[`destroy${formatModelName(model, true)}`];

			destroyModelMethod(watsonPrimaryKey);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/index`);
		}
	}

	_handleLeave() {
		this._handleClearFormData();

		this._handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	_handleTranslationRequest() {
		const {props} = this;

		const {model, watsonIncidentId, watsonPrimaryKey} = props;

		const requestModelTranslationMethod = props[`request${formatModelName(model, true)}Translation`];

		const translationURL = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPrimaryKey}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey
		};

		requestModelTranslationMethod(translationRequestData);
	}

	_handleUpdateFormData(formData) {
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
			modelLabel,
			response,
			storeData = props.data,
			watsonIncidentId
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
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPrimaryKey}/edit`);
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
			deleteMethod = disabled ? undefined : this._handleDelete;

			headerStringLeft = storeData.get('name') || sub(Liferay.Language.get('edit-x'), modelLabel);
			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			reportHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPrimaryKey}/report`;

			requestTranslationMethod = this._handleTranslationRequest;

			if (!disabled && WatsonConstants.currentUser.translatorRole) {
				translateHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPrimaryKey}/translate`;
			}
		}
		else if (action === 'create' && watsonIncidentId) {
			cancelMethod = this._handleCancel;
			headerStringLeft = sub(Liferay.Language.get('create-x'), modelLabel);
			headerStringRight = Liferay.Language.get('unsaved');
			submitMethod = this._handleCreate;
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
					<AffiliationLink
						affiliationData={storeData.get('affiliatedIncidents')}
						entryId={watsonPrimaryKey}
						model={model}
						watsonIncidentId={watsonIncidentId}
					/>

					<PersonChildLink
						affiliationData={storeData.get('affiliationData')}
						model="children"
					/>

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
						reportHref={reportHref}
						requestTranslationMethod={requestTranslationMethod}
						response={response}
						storeData={storeData}
						submitMethod={submitMethod}
						translateHref={translateHref}
						updateFormData={this._handleUpdateFormData}
						watsonIncidentId={watsonIncidentId}
						watsonPrimaryKey={watsonPrimaryKey}
					/>
				</div>
			</div>
		);
	}
}

GenericIncidentForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string(),
	modelLabel: Config.string(),
	response: Config.object(),
	storeData: Config.value(null),
	watsonIncidentId: Config.value(''),
	watsonPrimaryKey: Config.value('')
};

GenericIncidentForm.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

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

function mapDispatchToProps(dispatch) {
	return {
		destroyPeople: watsonPersonId => {
			dispatch(
				destroyPeople(watsonPersonId)
			);
		},
		destroyResources: watsonResourceId => {
			dispatch(
				destroyResources(watsonResourceId)
			);
		},
		destroyVehicles: watsonVehicleId => {
			dispatch(
				destroyVehicles(watsonVehicleId)
			);
		},
		editPeople: watsonPersonId => {
			dispatch(
				editPeople(watsonPersonId)
			);
		},
		editResources: watsonResourceId => {
			dispatch(
				editResources(watsonResourceId)
			);
		},
		editVehicles: watsonVehicleId => {
			dispatch(
				editVehicles(watsonVehicleId)
			);
		},
		requestPeopleTranslation: data => {
			dispatch(
				requestPeopleTranslation(data)
			);
		},
		requestResourcesTranslation: data => {
			dispatch(
				requestResourcesTranslation(data)
			);
		},
		requestVehiclesTranslation: data => {
			dispatch(
				requestVehiclesTranslation(data)
			);
		},
		updatePageTitle: data => {
			dispatch(
				updatePageTitle(data)
			);
		},
		updatePeople: data => {
			dispatch(
				updatePeople(data)
			);
		},
		updatePeopleDataManually: data => {
			dispatch(
				updatePeopleDataManually(data)
			);
		},
		updatePeopleFormData: (formData, watsonPersonId = 0) => {
			const data = {
				formData,
				watsonPersonId
			};

			dispatch(
				updatePeopleFormData(data)
			);
		},
		updateResources: data => {
			dispatch(
				updateResources(data)
			);
		},
		updateResourcesDataManually: data => {
			dispatch(
				updateResourcesDataManually(data)
			);
		},
		updateResourcesFormData: (formData, watsonResourceId = 0) => {
			const data = {
				formData,
				watsonResourceId
			};

			dispatch(
				updateResourcesFormData(data)
			);
		},
		updateVehicles: data => {
			dispatch(
				updateVehicles(data)
			);
		},
		updateVehiclesDataManually: data => {
			dispatch(
				updateVehiclesDataManually(data)
			);
		},
		updateVehiclesFormData: (formData, watsonVehicleId = 0) => {
			const data = {
				formData,
				watsonVehicleId
			};

			dispatch(
				updateVehiclesFormData(data)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(GenericIncidentForm);