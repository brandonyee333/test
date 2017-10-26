import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchIncidentTranslation, updateIncident} from '../../actions/incidents';
import {updateDOMTitle} from '../../lib/util';

class TranslateIncidentForm extends JSXComponent {
	attached() {
		const {props} = this;

		props.fetchTranslation(props.watsonIncidentId);
	}

	getConfig() {
		return [
			'description'
		];
	}

	render() {
		const {
			disabled = !WatsonConstants.currentUser.translatorRole,
			errors,
			loading,
			response,
			storeData,
			translatedData,
			translateIncident,
			watsonIncidentId
		} = this.props;

		const headerStringLeft = storeData.get('name') || Liferay.Language.get('translate-incident');

		const redirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit`);

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<TranslationForm
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.incidents.inputs}
						formConfig={this.getConfig()}
						id={watsonIncidentId}
						loading={loading}
						redirect={redirect}
						response={response}
						storeData={storeData}
						submitMethod={translateIncident}
						translatedData={translatedData}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName} = this.props;

		updateDOMTitle(sub(Liferay.Language.get('incident-x'), incidentName) || '');
	}
}

TranslateIncidentForm.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonIncidentId: Config.value('')
};

function mapStateToProps(state, props) {
	const translatedData = state.getIn(['incidents', 'translationData', props.watsonIncidentId]) || new Map();

	return {
		loading: state.getIn(
			[
				'incidents',
				'loading'
			]
		),
		response: state.getIn(
			[
				'incidents',
				'response'
			]
		),
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchTranslation: watsonIncidentId => {
			const data = {
				id: watsonIncidentId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchIncidentTranslation(data)
			);
		},
		translateIncident: data => {
			dispatch(
				updateIncident(data, 'updateTranslation.json')
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TranslateIncidentForm);