import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchResourceTranslation, updateResource, viewResource} from '../../actions/resources';
import {updateDOMTitle} from '../../lib/util';

class TranslateResourceForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonResourceId} = props;

		if (watsonResourceId) {
			props.fetchTranslation(watsonResourceId);
			props.viewResource(watsonResourceId);
		}
	}

	getConfig() {
		return [
			'name',
			'description'
		];
	};

	render() {
		const {
			disabled = !WatsonConstants.currentUser.translator,
			errors,
			loading,
			response,
			storeData,
			translatedData,
			translateResource,
			watsonIncidentId,
			watsonResourceId
		} = this.props;

		const headerStringLeft = storeData.get('name') || Liferay.Language.get('translate-resource');

		const redirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${watsonResourceId}/edit`);

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<TranslationForm
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.resources.inputs}
						formConfig={this.getConfig()}
						id={watsonResourceId}
						loading={loading}
						redirect={redirect}
						response={response}
						storeData={storeData}
						submitMethod={translateResource}
						translatedData={translatedData}
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

TranslateResourceForm.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonResourceId: Config.value('')
};

function mapStateToProps(state, props) {
	const {watsonResourceId} = props;

	const storeData = state.getIn(['resources', 'data', watsonResourceId]) || new Map();
	const translatedData = state.getIn(['resources', 'translationData', watsonResourceId]) || new Map();

	return {
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
		storeData,
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchTranslation: watsonResourceId => {
			const data = {
				id: watsonResourceId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchResourceTranslation(data)
			);
		},
		translateResource: data => {
			dispatch(
				updateResource(data, 'updateTranslation.json')
			);
		},
		viewResource: watsonResourceId => {
			dispatch(
				viewResource(watsonResourceId)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TranslateResourceForm);