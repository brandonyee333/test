import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
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

class PersonForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonPersonId} = props;

		if (watsonPersonId) {
			props.editPerson(watsonPersonId);
		}

		Router.router().on('beforeNavigate', this.handleBeforeLeave);

		this.handleClearFormData();
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
			updatePeopleDataManually
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updatePeopleDataManually(
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
			'rescued',
			'dateRescued',
			'accepted',
			'dateAccepted',
			'nameWatsonListTypeRels',
			'birthDate',
			'startAge',
			'endAge',
			'sexWatsonListTypeId',
			'height',
			'weight',
			'hairWatsonListTypeId',
			'eyesWatsonListTypeId',
			'occupation',
			'countryWatsonListTypeId',
			'ethnicityWatsonListTypeId',
			'birthCountryId',
			'citizenshipWatsonListTypeId',
			'countryIDWatsonListTypeRels',
			'phoneNumberWatsonListTypeRels',
			'socialMediaAccountWatsonListTypeRels',
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

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		this.props.updatePerson(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {watsonIncidentId, watsonPersonId} = this.props;

		if (watsonPersonId) {
			this.props.destroyPerson(watsonPersonId);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/index`);
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

		const {model, requestPersonTranslation, watsonIncidentId, watsonPersonId} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPersonId}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey: watsonPersonId
		};

		requestPersonTranslation(translationRequestData);
	}

	handleUpdateFormData(formData) {
		const {
			updatePeopleFormData,
			watsonPersonId
		} = this.props;

		updatePeopleFormData(formData, watsonPersonId);
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
			headerStringLeft = Liferay.Language.get('create-person'),
			headerStringRight,
			submitMethod = props.updatePerson,
			watsonPersonId
		} = props;

		const {
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response && action === 'create') {
			if (response.get('status') === 'success') {
				const responseData = response.get('data');

				watsonPersonId = responseData.get('watsonPersonId');

				if (watsonPersonId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/${watsonPersonId}/edit`);
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

			headerStringLeft = storeData.get('name') || Liferay.Language.get('edit-person');
			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			reportHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/${watsonPersonId}/report`;

			requestTranslationMethod = this.handleTranslationRequest;

			translateHref = (disabled || !WatsonConstants.currentUser.translator) ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/${watsonPersonId}/translate`;
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
						entryId={watsonPersonId}
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
						fieldConfig={WatsonConstants.inputConfig.people.inputs}
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
						watsonPrimaryKey={watsonPersonId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName, storeData} = this.props;

		const personName = sub(Liferay.Language.get('person-x'), storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, personName));
	}
}

PersonForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('people'),
	response: Config.object(),
	storeData: Config.value(null),
	watsonIncidentId: Config.value(''),
	watsonPersonId: Config.value('')
};

PersonForm.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {watsonPersonId = 0} = props;

	const errors = state.getIn(['people', 'errors']) || new Map();

	return {
		errors,
		loading: state.getIn(
			[
				'people',
				'loading'
			]
		),
		response: state.getIn(
			[
				'people',
				'response'
			]
		),
		watsonPersonId
	};
}

function mapDispatchToProps(dispatch) {
	return {
		destroyPerson: watsonPersonId => {
			dispatch(
				destroyPerson(watsonPersonId)
			);
		},
		editPerson: watsonPersonId => {
			dispatch(
				editPerson(watsonPersonId)
			);
		},
		requestPersonTranslation: data => {
			dispatch(
				requestPersonTranslation(data)
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
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(PersonForm);