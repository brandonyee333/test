import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchAddressTranslation, updateAddress, viewAddress} from '../../actions/addresses';
import {updateDOMTitle} from '../../lib/util';

class TranslateAddressForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonAddressId} = props;

		if (watsonAddressId) {
			props.fetchTranslation(watsonAddressId);
			props.viewAddress(watsonAddressId);
		}
	}

	getConfig() {
		return [
			'name',
			'street',
			'building',
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
			translateAddress,
			translatedData,
			watsonAddressId,
			watsonIncidentId
		} = this.props;

		const headerStringLeft = storeData.get('name') || Liferay.Language.get('translate-address');

		const redirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/${watsonAddressId}/edit`);

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<TranslationForm
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.addresses.inputs}
						formConfig={this.getConfig()}
						id={watsonAddressId}
						loading={loading}
						redirect={redirect}
						response={response}
						storeData={storeData}
						submitMethod={translateAddress}
						translatedData={translatedData}
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

TranslateAddressForm.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonAddressId: Config.value('')
};

function mapStateToProps(state, props) {
	const {watsonAddressId} = props;

	const storeData = state.getIn(['addresses', 'data', watsonAddressId]) || new Map();
	const translatedData = state.getIn(['addresses', 'translationData', watsonAddressId]) || new Map();

	return {
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
		),
		storeData,
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchTranslation: watsonAddressId => {
			const data = {
				id: watsonAddressId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchAddressTranslation(data)
			);
		},
		translateAddress: data => {
			dispatch(
				updateAddress(data, 'updateTranslation.json')
			);
		},
		viewAddress: watsonAddressId => {
			dispatch(
				viewAddress(watsonAddressId)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TranslateAddressForm);