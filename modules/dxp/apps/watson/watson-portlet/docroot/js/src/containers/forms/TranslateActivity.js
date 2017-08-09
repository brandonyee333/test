import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchActivityTranslation, updateActivity, viewActivity} from '../../actions/activities';
import {updateDOMTitle} from '../../lib/util';

class TranslateActivityForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonActivityId} = props;

		if (watsonActivityId) {
			props.fetchTranslation(watsonActivityId);
			props.viewActivity(watsonActivityId);
		}
	}

	getConfig() {
		return [
			'narrative'
		];
	};

	render() {
		const {
			disabled = !WatsonConstants.currentUser.translator,
			errors,
			loading,
			response,
			storeData,
			translateActivity,
			translatedData,
			watsonActivityId,
			watsonIncidentId
		} = this.props;

		const redirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/${watsonActivityId}/edit`);

		const headerStringLeft = storeData.get('name') || Liferay.Language.get('translate-activity');

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<TranslationForm
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.activities.inputs}
						formConfig={this.getConfig()}
						id={watsonActivityId}
						loading={loading}
						redirect={redirect}
						response={response}
						storeData={storeData}
						submitMethod={translateActivity}
						translatedData={translatedData}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName, storeData} = this.props;

		const activityName = sub(Liferay.Language.get('activity-x'), storeData.get('id') || '');

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, activityName));
	}
}

TranslateActivityForm.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonActivityId: Config.value('')
};

TranslateActivityForm.SYNC_UPDATES = true;

function mapStateToProps(state, props) {
	const {watsonActivityId} = props;

	const storeData = state.getIn(['activities', 'data', watsonActivityId]) || new Map();
	const translatedData = state.getIn(['activities', 'translationData', watsonActivityId]) || new Map();

	return {
		loading: state.getIn(
			[
				'activities',
				'loading'
			]
		),
		response: state.getIn(
			[
				'activities',
				'response'
			]
		),
		storeData,
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchTranslation: watsonActivityId => {
			const data = {
				id: watsonActivityId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchActivityTranslation(data)
			);
		},
		translateActivity: data => {
			dispatch(
				updateActivity(data, 'updateTranslation.json')
			);
		},
		viewActivity: watsonActivityId => {
			dispatch(
				viewActivity(watsonActivityId)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TranslateActivityForm);