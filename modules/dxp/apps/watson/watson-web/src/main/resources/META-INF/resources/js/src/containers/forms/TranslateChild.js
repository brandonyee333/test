import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import TranslationForm from '../../components/TranslationForm';

import {fetchChildTranslation, updateChild, viewChild} from '../../actions/children';
import {updateDOMTitle} from '../../lib/util';

class TranslateChildForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonChildId} = props;

		if (watsonChildId) {
			props.fetchTranslation(watsonChildId);
			props.viewChild(watsonChildId);
		}
	}

	getConfig() {
		return [
			'source'
		];
	}

	render() {
		const {
			disabled = !WatsonConstants.currentUser.translatorRole,
			errors,
			loading,
			response,
			storeData,
			translateChild,
			translatedData,
			watsonChildId
		} = this.props;

		const headerStringLeft = storeData.get('name') || Liferay.Language.get('translate-child');

		const redirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit`);

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<TranslationForm
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.children.inputs}
						formConfig={this.getConfig()}
						id={watsonChildId}
						loading={loading}
						redirect={redirect}
						response={response}
						storeData={storeData}
						submitMethod={translateChild}
						translatedData={translatedData}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {storeData} = this.props;

		const childName = sub(Liferay.Language.get('child-x'), storeData.get('id') || '');

		updateDOMTitle(childName);
	}
}

TranslateChildForm.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(new Map()),
	translatedData: Config.value(new Map()),
	watsonChildId: Config.value('')
};

function mapStateToProps(state, props) {
	const {watsonChildId} = props;

	const storeData = state.getIn(['children', 'data', watsonChildId]) || new Map();
	const translatedData = state.getIn(['children', 'translationData', watsonChildId]) || new Map();

	return {
		loading: state.getIn(
			[
				'children',
				'loading'
			]
		),
		response: state.getIn(
			[
				'children',
				'response'
			]
		),
		storeData,
		translatedData
	};
}

function mapDispatchToProps(dispatch) {
	return {
		fetchTranslation: watsonChildId => {
			const data = {
				id: watsonChildId,
				translatingTo: WatsonConstants.otherLanguageId
			};

			dispatch(
				fetchChildTranslation(data)
			);
		},
		translateChild: data => {
			dispatch(
				updateChild(data, 'updateTranslation.json')
			);
		},
		viewChild: watsonChildId => {
			dispatch(
				viewChild(watsonChildId)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(TranslateChildForm);