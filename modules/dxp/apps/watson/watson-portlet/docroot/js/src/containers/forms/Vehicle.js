import {bindAll, isEmpty} from 'lodash';
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
	destroyVehicle,
	editVehicle,
	requestVehicleTranslation,
	updateVehicle,
	updateVehiclesDataManually,
	updateVehiclesFormData
} from '../../actions/vehicles';

class VehicleForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonVehicleId} = props;

		if (watsonVehicleId) {
			props.editVehicle(watsonVehicleId);
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
			response,
			updateVehiclesDataManually
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updateVehiclesDataManually(
				{
					response: {
						message: null
					}
				}
			);
		}

		Router.router().off('beforeNavigate', this.handleBeforeLeave);
	}

	getConfig() {
		return [
			'id',
			'imagePayload',
			'typeWatsonListTypeId',
			'makeWatsonListTypeId',
			'modelWatsonListTypeId',
			'colorWatsonListTypeId',
			'yearWatsonListTypeId',
			'licensePlate',
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

		const {watsonIncidentId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		this.props.updateVehicle(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {watsonIncidentId, watsonVehicleId} = this.props;

		if (watsonVehicleId) {
			this.props.destroyVehicle(watsonVehicleId);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/index`);
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

		const {model, requestVehicleTranslation, watsonIncidentId, watsonVehicleId} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${watsonVehicleId}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey: watsonVehicleId
		};

		requestVehicleTranslation(translationRequestData);
	}

	handleUpdateFormData(formData) {
		const {
			updateVehiclesFormData,
			watsonVehicleId
		} = this.props;

		updateVehiclesFormData(formData, watsonVehicleId);
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
			watsonIncidentId
		} = props;

		let {
			cancelMethod,
			headerStringLeft = Liferay.Language.get('create-vehicle'),
			headerStringRight,
			submitMethod = props.updateVehicle,
			watsonVehicleId
		} = props;

		const {
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response && action === 'create') {
			if (response.get('status') === 'success') {
				const responseData = response.get('data');

				watsonVehicleId = responseData.get('watsonVehicleId');

				if (watsonVehicleId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${watsonVehicleId}/edit`);
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
			headerStringLeft = storeData.get('name') || Liferay.Language.get('edit-vehicle');

			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			reportHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${watsonVehicleId}/report`;

			requestTranslationMethod = this.handleTranslationRequest;

			translateHref = (disabled || !WatsonConstants.currentUser.translator) ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${watsonVehicleId}/translate`;
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
					<AffiliationLink
						affiliationData={storeData.get('affiliatedIncidents')}
						entryId={watsonVehicleId}
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
						fieldConfig={WatsonConstants.inputConfig.vehicles.inputs}
						formConfig={this.getConfig()}
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
						watsonPrimaryKey={watsonVehicleId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName, storeData} = this.props;

		const vehicleName = sub(Liferay.Language.get('vehicle-x'), storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, vehicleName));
	}
}

VehicleForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('vehicles'),
	response: Config.object(),
	storeData: Config.value(null),
	watsonIncidentId: Config.value(''),
	watsonVehicleId: Config.value('')
};

VehicleForm.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {watsonVehicleId = 0} = props;

	const errors = state.getIn(['vehicles', 'errors']) || new Map();

	return {
		errors,
		loading: state.getIn(
			[
				'vehicles',
				'loading'
			]
		),
		response: state.getIn(
			[
				'vehicles',
				'response'
			]
		),
		watsonVehicleId
	};
}

function mapDispatchToProps(dispatch) {
	return {
		destroyVehicle: watsonVehicleId => {
			dispatch(
				destroyVehicle(watsonVehicleId)
			);
		},
		editVehicle: watsonVehicleId => {
			dispatch(
				editVehicle(watsonVehicleId)
			);
		},
		requestVehicleTranslation: data => {
			dispatch(
				requestVehicleTranslation(data)
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

export default connect(mapStateToProps, mapDispatchToProps)(VehicleForm);