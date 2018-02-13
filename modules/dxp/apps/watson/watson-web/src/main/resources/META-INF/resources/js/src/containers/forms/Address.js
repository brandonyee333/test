import {bindAll, isEmpty, noop} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';
import sub from 'string-sub';

import AffiliationLink from '../../components/AffiliationLink';
import Button from '../../components/Button';
import ButtonModal from '../../components/ButtonModal';
import ContentHeader from '../../components/ContentHeader';
import {convertMapToObject, deepCompareIsEqual, getModifiedMoment, updateDOMTitle} from '../../lib/util';
import Form from '../../components/Form';
import MicroForm from '../../components/MicroForm';
import Modal from '../../components/Modal';

import {
	destroyAddresses,
	editAddresses,
	requestAddressesTranslation,
	sendAddressesCoordinates,
	updateAddresses,
	updateAddressesDataManually,
	updateAddressesFormData
} from '../../actions/addresses';

import {updateIncidentAddressFormData} from '../../actions/incidents';

class AddressForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonAddressId} = props;

		if (watsonAddressId) {
			props.editAddresses(watsonAddressId);
		}

		Router.router().on('beforeNavigate', this.handleBeforeLeave);

		window.onbeforeunload = this.handleBeforeLeave;
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
			'handleMicroFormClose',
			'handleMicroFormSubmit',
			'handleTranslationRequest',
			'handleUpdateFormData'
		);
	}

	detached() {
		const {
			action,
			response,
			updateAddressesDataManually,
			watsonIncidentId
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updateAddressesDataManually(
				{
					response: {
						message: null
					}
				}
			);
		}

		Router.router().off('beforeNavigate', this.handleBeforeLeave);

		window.onbeforeunload = undefined;

		if (watsonIncidentId && watsonIncidentId > 0) {
			this.handleClearFormData();
		}
	}

	getConfig() {
		return [
			'id',
			'name',
			'imagePayload',
			'typeWatsonListTypeId',
			'lastSeenDate',
			'otherType',
			'googleMap',
			'countryId',
			'region',
			'provinceWatsonListTypeId',
			'districtWatsonListTypeId',
			'subDistrictWatsonListTypeId',
			'postalCode',
			'latitude',
			'longitude',
			'street',
			'number',
			'building',
			'floor',
			'room',
			'description',
			'watsonRelationships'
		];
	};

	handleBeforeLeave(data) {
		const {
			action,
			formData = {},
			storeData,
			watsonIncidentId = 0
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

	handleCancel() {
		this.handleClearFormData();

		const {watsonIncidentId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		this.props.updateAddresses(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {watsonAddressId, watsonIncidentId} = this.props;

		if (watsonAddressId) {
			this.props.destroyAddresses(watsonAddressId);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/index`);
		}
	}

	handleLeave() {
		this.handleClearFormData();

		this.handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	handleMicroFormClose() {
		this.setState({closeMicroFormModal: true});
	}

	handleMicroFormSubmit(data) {
		this.props.sendAddressesCoordinates(data);

		this.handleMicroFormClose();
	}

	handleTranslationRequest() {
		const {props} = this;

		const {model, requestAddressesTranslation, watsonAddressId, watsonIncidentId} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonAddressId}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey: watsonAddressId
		};

		requestAddressesTranslation(translationRequestData);
	}

	handleUpdateFormData(formData) {
		const {
			updateAddressesFormData,
			updateIncidentAddressFormData,
			watsonAddressId,
			watsonIncidentId
		} = this.props;

		if (!watsonIncidentId || watsonIncidentId === 0) {
			updateIncidentAddressFormData(formData);
		}
		else {
			updateAddressesFormData(formData, watsonAddressId);
		}
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
			headerStringLeft = Liferay.Language.get('create-address'),
			headerStringRight,
			submitMethod = props.updateAddresses,
			watsonAddressId
		} = props;

		const {
			closeMicroFormModal,
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response && action === 'create') {
			if (response.get('status') === 'success') {
				const responseData = response.get('data');

				watsonAddressId = responseData.get('watsonAddressId');

				if (watsonAddressId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/${watsonAddressId}/edit`);
				}
			}
			else if (errors) {
				this.state.dataSent = false;
			}
		}

		const additionalTopBarButtons = [];

		let deleteMethod;
		let message;
		let reportHref;
		let requestTranslationMethod;
		let translateHref;

		if (action === 'edit') {
			deleteMethod = disabled ? undefined : this.handleDelete;
			headerStringLeft = storeData.get('name') || Liferay.Language.get('edit-address');

			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			reportHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/${watsonAddressId}/report`;

			requestTranslationMethod = this.handleTranslationRequest;

			const optionButtons = [];

			optionButtons.push(
				{
					label: Liferay.Language.get('send-address')
				}
			);

			const modal = {
				body: (
					<MicroForm
						cancelMethod={this.handleMicroFormClose}
						fieldConfig={WatsonConstants.inputConfig.addresses.miscInputs}
						formConfig={['address', 'coordinates', 'emailAddress', 'description']}
						id={watsonAddressId}
						submitMethod={this.handleMicroFormSubmit}
					/>
				),
				footer: [],
				header: Liferay.Language.get('please-fill-out-all-required-fields')
			};

			additionalTopBarButtons.push(
				<ButtonModal action={noop} buttons={optionButtons} modalData={modal} remoteCloseModal={closeMicroFormModal} />
			);

			if (!disabled && WatsonConstants.currentUser.translatorRole) {
				translateHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/${watsonAddressId}/translate`;
			}
		}
		else if (action === 'create') {
			if (watsonIncidentId) {
				cancelMethod = this.handleCancel;
				headerStringRight = Liferay.Language.get('unsaved');
				submitMethod = this.handleCreate;
			}
			else {
				message = sub(Liferay.Language.get('step-x-of-x'), '2', '2');
			}
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
						entryId={watsonAddressId}
						model={model}
						watsonIncidentId={watsonIncidentId}
					/>

					<Form
						action={action}
						additionalTopBarButtons={additionalTopBarButtons}
						button={button}
						buttonLabel={buttonLabel}
						cancelMethod={cancelMethod}
						deleteMethod={deleteMethod}
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.addresses.inputs}
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
						watsonPrimaryKey={watsonAddressId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName, storeData} = this.props;

		const addressName = sub(Liferay.Language.get('address-x'), storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, addressName));
	}
}

AddressForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('addresses'),
	response: Config.object(),
	storeData: Config.value(null),
	watsonAddressId: Config.value(''),
	watsonIncidentId: Config.value('')
};

AddressForm.STATE = {
	closeMicroFormModal: Config.bool(),
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {action} = props;

	let errors = state.getIn(['addresses', 'errors']) || new Map();

	if (action === 'create') {
		const backendErrors = state.getIn(
			[
				'incidents',
				'errors',
				'addressErrors'
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
	}

	return {
		errors,
		loading: state.getIn(
			[
				'addresses',
				'loading'
			]
		),
		response: state.getIn(
			[
				'addresses',
				'response'
			]
		)
	};
}

function mapDispatchToProps(dispatch) {
	return {
		destroyAddresses: watsonAddressesId => {
			dispatch(
				destroyAddresses(watsonAddressesId)
			);
		},
		editAddresses: watsonAddressesId => {
			dispatch(
				editAddresses(watsonAddressesId)
			);
		},
		requestAddressesTranslation: data => {
			dispatch(
				requestAddressesTranslation(data)
			);
		},
		sendAddressesCoordinates: data => {
			dispatch(
				sendAddressesCoordinates(data)
			);
		},
		updateAddresses: data => {
			dispatch(
				updateAddresses(data)
			);
		},
		updateAddressesDataManually: data => {
			dispatch(
				updateAddressesDataManually(data)
			);
		},
		updateAddressesFormData: (formData, watsonAddressId = 0) => {
			const data = {
				formData,
				watsonAddressId
			};

			dispatch(
				updateAddressesFormData(data)
			);
		},
		updateIncidentAddressFormData: data => {
			dispatch(
				updateIncidentAddressFormData(data)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(AddressForm);