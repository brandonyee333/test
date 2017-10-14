import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchVehicleTranslation, updateVehicle, viewVehicle} from '../../actions/vehicles';
import {updateDOMTitle} from '../../lib/util';

class TranslateVehicleForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonVehicleId} = props;

		if (watsonVehicleId) {
			props.fetchTranslation(watsonVehicleId);
			props.viewVehicle(watsonVehicleId);
		}
	}

	getConfig() {
		return [
			'description'
		];
	}

	render() {
		const {
			disabled = !WatsonConstants.currentUser.translator,
			errors,
			loading,
			response,
			storeData,
			translatedData,
			translateVehicle,
			watsonIncidentId,
			watsonVehicleId
		} = this.props;

		const headerStringLeft = storeData.get('name') || Liferay.Language.get('translate-vehicle');

		const redirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${watsonVehicleId}/edit`);

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<TranslationForm
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.vehicles.inputs}
						formConfig={this.getConfig()}
						id={watsonVehicleId}
						loading={loading}
						redirect={redirect}
						response={response}
						storeData={storeData}
						submitMethod={translateVehicle}
						translatedData={translatedData}
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

TranslateVehicleForm.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonVehicleId: Config.value('')
};

function mapStateToProps(state, props) {
	const {watsonVehicleId} = props;

	const storeData = state.getIn(['vehicles', 'data', watsonVehicleId]) || new Map();
	const translatedData = state.getIn(['vehicles', 'translationData', watsonVehicleId]) || new Map();

	return {
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
		storeData,
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchTranslation: watsonVehicleId => {
			const data = {
				id: watsonVehicleId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchVehicleTranslation(data)
			);
		},
		translateVehicle: data => {
			dispatch(
				updateVehicle(data, 'updateTranslation.json')
			);
		},
		viewVehicle: watsonVehicleId => {
			dispatch(
				viewVehicle(watsonVehicleId)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TranslateVehicleForm);