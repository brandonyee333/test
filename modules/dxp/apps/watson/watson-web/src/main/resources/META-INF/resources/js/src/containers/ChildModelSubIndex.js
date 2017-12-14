import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import sub from 'string-sub';

import NavigationHeader from '../components/NavigationHeader';
import ViewIndex from './views/ViewIndex';

import {editAddresses} from '../actions/addresses';
import {fetchIncidentAffiliations} from '../actions/incidents';
import {editPeople} from '../actions/people';
import {editResources} from '../actions/resources';
import {editVehicles} from '../actions/vehicles';

import {formatModelName, getOptionsLabelFromWatsonConstants, getPluralMessage} from '../lib/util';

class ChildModelSubIndex extends JSXComponent {
	attached() {
		this.handleFetchDataRequest();
	}

	created() {
		bindAll(
			this,
			'handleFetchDataRequest',
			'handleSearchRequest'
		);
	}

	handleFetchDataRequest() {
		const {props} = this;

		const {entryId, fetchIncidentAffiliations, model} = props;

		const singularLabel = WatsonConstants.inputConfig[model].singularLabel;

		const editModelMethod = props[`edit${formatModelName(singularLabel)}`];

		if (entryId) {
			editModelMethod(entryId);
			fetchIncidentAffiliations({id: entryId, model});
		}
	}

	handleSearchRequest(filter) {
		const {entryId, fetchIncidentAffiliations, model} = this.props;

		const fieldsArray = [];
		const keywordsArray = [];

		filter.forEach(
			data => {
				const key = data[0];
				const value = data[1];

				if (key && value) {
					fieldsArray.push(key);
					keywordsArray.push(value);
				}
			}
		);

		if (fetchIncidentAffiliations && !isEmpty(fieldsArray) && !isEmpty(keywordsArray)) {
			fetchIncidentAffiliations(
				{
					fields: fieldsArray,
					id: entryId,
					keywords: keywordsArray,
					model
				}
			);
		}
		else {
			this.handleFetchDataRequest();
		}
	}

	render() {
		const {
			action = 'view',
			affiliationData,
			entryId = 0,
			model,
			modelData,
			modelLoading,
			watsonIncidentId
		} = this.props;

		const modelMetaHeader = `${sub(Liferay.Language.get('created-by-x-on-x'), modelData.get('reportedBy') || '', modelData.get('createDate') || '')}`;
		const modelName = modelData.get('name');

		const modelTypeLabel = getOptionsLabelFromWatsonConstants(model, 'typeWatsonListTypeId', modelData.get('typeWatsonListTypeId'));

		return (
			<div class="model-index page-container hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader mainHeader={modelName} metaHeader={modelMetaHeader} subHeader={modelTypeLabel} />
				</div>

				<ViewIndex
					action={action}
					buttonData={[]}
					disableDataFetch={true}
					entryId={entryId}
					filterActiveCallback={this.handleSearchRequest}
					forwardedData={affiliationData}
					forwardedLoading={modelLoading}
					headerStringLeft={sub(Liferay.Language.get('affiliated-with-x-other-x'), affiliationData.size, getPluralMessage(Liferay.Language.get('incident'), Liferay.Language.get('incidents'), affiliationData.size))}
					model="incidents"
					watsonIncidentId={watsonIncidentId}
				/>
			</div>
		);
	}
}

ChildModelSubIndex.PROPS = {
	action: Config.string().value('view'),
	affiliationData: Config.any(),
	entryId: Config.value(''),
	filter: Config.value(new Map()),
	model: Config.string().value(''),
	modelData: Config.value(new Map()),
	modelLoading: Config.bool(),
	watsonIncidentId: Config.value('')
};

function mapDispatchToProps(dispatch) {
	return {
		editAddresses: watsonAddressesId => {
			dispatch(
				editAddresses(watsonAddressesId)
			);
		},
		editPeople: watsonPeopleId => {
			dispatch(
				editPeople(watsonPeopleId)
			);
		},
		editResources: watsonResourcesId => {
			dispatch(
				editResources(watsonResourcesId)
			);
		},
		editVehicles: watsonVehiclesId => {
			dispatch(
				editVehicles(watsonVehiclesId)
			);
		},
		fetchIncidentAffiliations: data => {
			dispatch(
				fetchIncidentAffiliations(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {entryId = 0, model = 'incidents', watsonIncidentId} = props.router.params;

	const affiliationData = state.getIn(['incidents', 'data']) || new Map();
	const modelData = state.getIn([model, 'data', entryId]) || new Map();
	const modelLoading = state.getIn([model, 'loading']) || false;

	return {
		affiliationData,
		entryId,
		filter: state.getIn(['display', 'filter', watsonIncidentId, model]),
		model,
		modelData,
		modelLoading,
		watsonIncidentId
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ChildModelSubIndex);