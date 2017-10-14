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
	destroyResource,
	editResource,
	requestResourceTranslation,
	updateResource,
	updateResourcesDataManually,
	updateResourcesFormData
} from '../../actions/resources';

class ResourceForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonResourceId} = props;

		if (watsonResourceId) {
			props.editResource(watsonResourceId);
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
			updateResourcesDataManually
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updateResourcesDataManually(
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

		const {watsonIncidentId} = this.props;

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleCreate(data) {
		this.props.updateResource(data);

		this.state.dataSent = true;
	}

	handleDelete() {
		const {watsonIncidentId, watsonResourceId} = this.props;

		if (watsonResourceId) {
			this.props.destroyResource(watsonResourceId);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/index`);
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

		const {model, requestResourceTranslation, watsonIncidentId, watsonResourceId} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonResourceId}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey: watsonResourceId
		};

		requestResourceTranslation(translationRequestData);
	}

	handleUpdateFormData(formData) {
		const {
			updateResourcesFormData,
			watsonResourceId
		} = this.props;

		updateResourcesFormData(formData, watsonResourceId);
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
			headerStringLeft = Liferay.Language.get('create-resource'),
			headerStringRight,
			submitMethod = props.updateResource,
			watsonResourceId
		} = props;

		const {
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response && action === 'create') {
			if (response.get('status') === 'success') {
				const responseData = response.get('data');

				watsonResourceId = responseData.get('watsonResourceId');

				if (watsonResourceId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${watsonResourceId}/edit`);
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

			headerStringLeft = storeData.get('name') || Liferay.Language.get('edit-resource');
			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			reportHref = `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${watsonResourceId}/report`;

			requestTranslationMethod = this.handleTranslationRequest;

			translateHref = (disabled || !WatsonConstants.currentUser.translator) ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${watsonResourceId}/translate`;
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
						entryId={watsonResourceId}
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
						fieldConfig={WatsonConstants.inputConfig.resources.inputs}
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
						watsonPrimaryKey={watsonResourceId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName, storeData} = this.props;

		const resourceName = sub(Liferay.Language.get('activity-x'), storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, resourceName));
	}
}

ResourceForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('resources'),
	response: Config.object(),
	storeData: Config.value(null),
	watsonIncidentId: Config.value(''),
	watsonResourceId: Config.value('')
};

ResourceForm.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {watsonResourceId = 0} = props;

	const errors = state.getIn(['resources', 'errors']) || new Map();

	return {
		errors,
		loading: state.getIn(
			[
				'resources',
				'loading'
			]
		),
		response: state.getIn(
			[
				'resources',
				'response'
			]
		),
		watsonResourceId
	};
}

function mapDispatchToProps(dispatch) {
	return {
		destroyResource: watsonResourceId => {
			dispatch(
				destroyResource(watsonResourceId)
			);
		},
		editResource: watsonResourceId => {
			dispatch(
				editResource(watsonResourceId)
			);
		},
		requestResourceTranslation: data => {
			dispatch(
				requestResourceTranslation(data)
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
		updateResourcesFormData: (formData, watsonResourceId = 0) => {
			const data = {
				formData,
				watsonResourceId
			};

			dispatch(
				updateResourcesFormData(data)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ResourceForm);