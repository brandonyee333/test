import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchPeopleTranslation, updatePeople, viewPeople} from '../../actions/people';
import {updateDOMTitle} from '../../lib/util';

class TranslatePersonForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonPersonId} = props;

		if (watsonPersonId) {
			props.fetchTranslation(watsonPersonId);
			props.viewPeople(watsonPersonId);
		}
	}

	getConfig() {
		return [
			'occupation',
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
			translatePerson,
			watsonIncidentId,
			watsonPersonId
		} = this.props;

		const headerStringLeft = storeData.get('name') || Liferay.Language.get('translate-person');

		const redirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/${watsonPersonId}/edit`);

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<TranslationForm
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.people.inputs}
						formConfig={this.getConfig()}
						id={watsonPersonId}
						loading={loading}
						redirect={redirect}
						response={response}
						storeData={storeData}
						submitMethod={translatePerson}
						translatedData={translatedData}
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

TranslatePersonForm.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonPersonId: Config.value('')
};

function mapStateToProps(state, props) {
	const {watsonPersonId} = props;

	const storeData = state.getIn(['people', 'data', watsonPersonId]) || new Map();
	const translatedData = state.getIn(['people', 'translationData', watsonPersonId]) || new Map();

	return {
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
		storeData,
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchTranslation: watsonPersonId => {
			const data = {
				id: watsonPersonId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchPeopleTranslation(data)
			);
		},
		translatePerson: data => {
			dispatch(
				updatePeople(data, 'updateTranslation.json')
			);
		},
		viewPeople: watsonPersonId => {
			dispatch(
				viewPeople(watsonPersonId)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TranslatePersonForm);