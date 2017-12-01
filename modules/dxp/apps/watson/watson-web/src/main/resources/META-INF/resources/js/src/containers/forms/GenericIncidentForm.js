import {bindAll, capitalize, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import AffiliationLink from '../../components/AffiliationLink';
import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import {convertMapToObject, deepCompareIsEqual, getModifiedMoment, updateDOMTitle} from '../../lib/util';
import Form from '../../components/Form';
import Modal from '../../components/Modal';

import {
	destroyPerson,
	editPerson,
	requestPersonTranslation,
	updatePeopleDataManually,
	updatePeopleFormData,
	updatePerson
} from '../../actions/people';

import {
	destroyResource,
	editResource,
	requestResourceTranslation,
	updateResource,
	updateResourcesDataManually,
	updateResourcesFormData
} from '../../actions/resources';

import {
	destroyVehicle,
	editVehicle,
	requestVehicleTranslation,
	updateVehicle,
	updateVehiclesDataManually,
	updateVehiclesFormData
} from '../../actions/vehicles';

class GenericIncidentForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {model, watsonPrimaryKey} = props;

		if (model && watsonPrimaryKey) {
			const editModelMethod = props[`edit${capitalize(model)}`];

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
			response
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			const updateModelDataManuallyMethod = this.props[`update${capitalize(model)}DataManually`];

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

	getConfig() {
		return [
			'id',
			'typeWatsonListTypeId',
			'name',
			'imagePayload',
			'description',
			'watsonRelationships'
		];
	};

	handleBeforeLeave(data) {
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

		if (watsonIncidentId > 0 && !isEmpty(formData) && (!isEmpty(storeData) || action === 'create' && !dataSent)) {
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

		const {model, watsonIncidentId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		const {props} = this;

		const updateModelMethod = props[`update${capitalize(props.model)}`];

		updateModelMethod(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {props} = this;

		const {model, watsonIncidentId, watsonPrimaryKey} = props;

		if (watsonPrimaryKey) {
			const destroyModelMethod = props[`destroy${capitalize(model)}`];

			destroyModelMethod(watsonPrimaryKey);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/index`);
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

		const {model, requestResourceTranslation, watsonIncidentId, watsonPrimaryKey} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPrimaryKey}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey
		};

		requestResourceTranslation(translationRequestData);
	}

	handleUpdateFormData(formData) {
		const {props} = this;

		const {model, watsonPrimaryKey} = props;

		const updateModelFormData = props[`update${capitalize(model)}FormData`];

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
			submitMethod = props[`update${capitalize(model)}`],
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
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${watsonPrimaryKey}/edit`);
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

			reportHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPrimaryKey}/report`;

			requestTranslationMethod = this.handleTranslationRequest;

			translateHref = (disabled || !WatsonConstants.currentUser.translatorRole) ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPrimaryKey}/translate`;
		}
		else if (action === 'create' && watsonIncidentId) {
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
					<AffiliationLink
						affiliationData={storeData.get('affiliatedIncidents')}
						entryId={watsonPrimaryKey}
						model={model}
						watsonIncidentId={watsonIncidentId}
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
						updateFormData={this.handleUpdateFormData}
						watsonIncidentId={watsonIncidentId}
						watsonPrimaryKey={watsonPrimaryKey}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName, modelLabel, storeData} = this.props;

		const modelName = sub(Liferay.Language.get('x-x'), modelLabel, storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, modelName));
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
		destroyPerson: watsonPersonId => {
			dispatch(
				destroyPerson(watsonPersonId)
			);
		},
		destroyResource: watsonPrimaryKey => {
			dispatch(
				destroyResource(watsonPrimaryKey)
			);
		},
		destroyVehicle: watsonVehicleId => {
			dispatch(
				destroyVehicle(watsonVehicleId)
			);
		},
		editPerson: watsonPersonId => {
			dispatch(
				editPerson(watsonPersonId)
			);
		},
		editResource: watsonPrimaryKey => {
			dispatch(
				editResource(watsonPrimaryKey)
			);
		},
		editVehicle: watsonVehicleId => {
			dispatch(
				editVehicle(watsonVehicleId)
			);
		},
		requestPersonTranslation: data => {
			dispatch(
				requestPersonTranslation(data)
			);
		},
		requestResourceTranslation: data => {
			dispatch(
				requestResourceTranslation(data)
			);
		},
		requestVehicleTranslation: data => {
			dispatch(
				requestVehicleTranslation(data)
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
		updatePerson: data => {
			dispatch(
				updatePerson(data)
			);
		},
		updateResource: data => {
			dispatch(
				updateResource(data)
			);
		},
		updateResourcesDataManually: data => {
			dispatch(
				updateResourcesDataManually(data)
			);
		},
		updateResourcesFormData: (formData, watsonPrimaryKey = 0) => {
			const data = {
				formData,
				watsonPrimaryKey
			};

			dispatch(
				updateResourcesFormData(data)
			);
		},
		updateVehicle: data => {
			dispatch(
				updateVehicle(data)
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