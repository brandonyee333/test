import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import AddressForm from './forms/Address';
import Button from '../components/Button';
import IncidentForm from './forms/Incident';
import Modal from '../components/Modal';
import Navigation from '../components/Navigation';

import {addIncidents, updateIncidentAddressFormData, updateIncidentsFormData} from '../actions/incidents';

import {updatePageTitle} from '../actions/display';

class CreateIncident extends JSXComponent {
	attached() {
		Router.router().on('beforeNavigate', this._handleBeforeLeave);
	}

	created() {
		bindAll(
			this,
			'_handleBeforeLeave',
			'_handleClearFormData',
			'_handleClose',
			'_handleLeave',
			'_handleSubmit',
			'_handleSubmitIncident',
		);
	}

	detached() {
		Router.router().off('beforeNavigate', this._handleBeforeLeave);
	}

	getCurrentForm(action, model) {
		const {addressFormData, incidentFormData} = this.props;

		let form;

		const storeData = new Map();

		const cancelMethod = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents`);

		if (model === 'address') {
			const addressButton = {
				action: () => {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/create`);
				},
				label: Liferay.Language.get('previous')
			};

			form = (
				<AddressForm
					action={action}
					button={addressButton}
					cancelMethod={cancelMethod}
					formData={addressFormData}
					headerStringLeft={Liferay.Language.get('create-incident')}
					headerStringRight={Liferay.Language.get('unsaved')}
					storeData={storeData}
					submitMethod={this._handleSubmit}
					watsonAddressId={0}
				/>
			);
		}
		else {
			form = (
				<IncidentForm
					action={action}
					buttonLabel={Liferay.Language.get('next')}
					cancelMethod={cancelMethod}
					formData={incidentFormData}
					headerStringLeft={Liferay.Language.get('create-incident')}
					headerStringRight={Liferay.Language.get('unsaved')}
					storeData={storeData}
					submitMethod={this._handleSubmitIncident}
					watsonIncidentId={0}
				/>
			);
		}

		return form;
	}

	_handleBeforeLeave(data) {
		const {
			addressFormData = {},
			incidentFormData = {}
		} = this.props;

		const {
			dataSent,
			unlockNavigate
		} = this.state;

		const createPath = !!data.path.match(/web\/guest\/home\/-\/watson\/incidents\/create/);

		if ((!Object.is({}, addressFormData) || Object.is({}, incidentFormData)) && !dataSent && !unlockNavigate && !createPath) {
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

	_handleClearFormData() {
		const {
			updateIncidentAddressFormData,
			updateIncidentsFormData
		} = this.props;

		updateIncidentAddressFormData({}, 0);

		updateIncidentsFormData({}, 0);
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

	_handleSubmit() {
		const {props} = this;

		const {incidentFormData} = props;

		if (incidentFormData) {
			incidentFormData.address = props.addressFormData;

			props.addIncidents(incidentFormData);

			this.state.dataSent = true;
		}
		else {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/create`);
		}
	}

	_handleSubmitIncident() {
		Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/create/address`);
	}

	render() {
		const {
			action,
			errors,
			loading,
			model,
			response
		} = this.props;

		const {
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response) {
			if (response.get('status') === 'success') {
				const responseData = response.get('data') || new Map();

				const watsonIncidentId = responseData.get('watsonIncidentId');

				if (watsonIncidentId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit`);
				}
			}
			else if (errors) {
				this.state.dataSent = false;

				if (errors.get('incidentErrors')) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/create`);
				}
			}
		}

		const nav = [
			{
				collapsible: false,
				href: `${WatsonConstants.urls.baseURL}/incidents/create`,
				selected: !model,
				text: Liferay.Language.get('details')
			},
			{
				collapsible: false,
				href: `${WatsonConstants.urls.baseURL}/incidents/create/address`,
				selected: model === 'address',
				text: Liferay.Language.get('incident-location')
			}
		];

		const modalFooter = [
			<Button className="modal-button" key="btn-action" label={Liferay.Language.get('leave')} onClick={this._handleLeave} />,
			<Button className="modal-button" key="btn-cancel" label={Liferay.Language.get('stay')} onclick={this._handleClose} />
		];

		return (
			<div class="page-container incidents-create hidden-print">
				<div class="navigation-sidebar">
					<Navigation entries={nav} />
				</div>

				{showLeaveModal &&
					<Modal body={Liferay.Language.get('you-have-unsaved-changes-that-will-be-lost-if-you-continue')} close={this._handleClose} footer={modalFooter} header={Liferay.Language.get('do-you-want-to-leave-this-page')} />
				}

				{this.getCurrentForm(action, model)}
			</div>
		);
	}

	rendered(firstRender) {
		if (firstRender) {
			this.props.updatePageTitle(Liferay.Language.get('create-incident'));
		}
	}
}

CreateIncident.PROPS = {
	addressFormData: Config.object().value({}),
	errors: Config.value(new Map()),
	incidentFormData: Config.object().value({}),
	loading: Config.bool().value(false),
	response: Config.object()
};

CreateIncident.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

CreateIncident.SYNC_UPDATES = true;

function mapDispatchToProps(dispatch) {
	return {
		addIncidents: data => {
			dispatch(
				addIncidents(data)
			);
		},
		updateIncidentAddressFormData: data => {
			dispatch(
				updateIncidentAddressFormData(data)
			);
		},
		updateIncidentsFormData: data => {
			dispatch(
				updateIncidentsFormData(data)
			);
		},
		updatePageTitle: data => {
			dispatch(
				updatePageTitle(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {action = 'create', model} = props.router.params;
	const incidentFormData = state.getIn(['incidents', 'formData', 0]) || {};

	const addressFormData = incidentFormData.address || {};

	return {
		action,
		addressFormData,
		errors: state.getIn(
			[
				'incidents',
				'errors'
			]
		),
		incidentFormData,
		loading: state.getIn(
			[
				'incidents',
				'loading'
			]
		),
		model,
		response: state.getIn(
			[
				'incidents',
				'response'
			]
		)
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(CreateIncident);