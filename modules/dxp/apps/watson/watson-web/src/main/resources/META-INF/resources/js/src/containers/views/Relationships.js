import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import RelationshipsView from '../../components/RelationshipsView';

import {updatePageTitle} from '../../actions/display';
import {editRelationships, updateRelationshipsDataManually} from '../../actions/relationships';

class RelationshipsForm extends JSXComponent {
	attached() {
		const {editRelationships, watsonIncidentId} = this.props;

		if (watsonIncidentId) {
			editRelationships(watsonIncidentId);
		}
	}

	created() {
		const {incidentName, updatePageTitle} = this.props;

		updatePageTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, Liferay.Language.get('relationships')));
	}

	render() {
		const {props} = this;

		const {
			disabled,
			headerStringLeft = Liferay.Language.get('relationships'),
			incidentName,
			watsonIncidentId,
			watsonRelationships
		} = props;

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />
				</div>

				<div class="content">
					<div class="relationships-view">
						<RelationshipsView
							disabled={disabled}
							incidentName={incidentName}
							watsonIncidentId={watsonIncidentId}
							watsonRelationships={watsonRelationships}
						/>
					</div>
				</div>
			</div>
		);
	}
}

RelationshipsForm.PROPS = {
	disabled: Config.bool().value(false),
	incidentName: Config.string(),
	watsonIncidentId: Config.value(''),
	watsonRelationships: Config.value(new Map())
};

function mapDispatchToProps(dispatch) {
	return {
		editRelationships: watsonIncidentId => {
			dispatch(
				editRelationships(watsonIncidentId)
			);
		},
		updatePageTitle: data => {
			dispatch(
				updatePageTitle(data)
			);
		},
		updateRelationshipsDataManually: data => {
			dispatch(
				updateRelationshipsDataManually(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {watsonIncidentId} = props;

	const errors = state.getIn(['relationships', 'errors']) || new Map();
	const storeData = state.getIn(['relationships', 'data', watsonIncidentId]) || new Map();
	const watsonRelationships = storeData.get('watsonRelationships') || new Map();

	return {
		errors,
		response: state.getIn(
			[
				'relationships',
				'response'
			]
		),
		storeData,
		watsonRelationships
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(RelationshipsForm);