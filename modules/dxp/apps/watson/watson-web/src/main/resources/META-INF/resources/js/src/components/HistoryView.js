import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import moment from 'moment';
import sub from 'string-sub';

import {formatModelName, getOptionsLabelFromWatsonConstants} from '../lib/util';

import {indexActivities} from '../actions/activities';
import {indexAddresses} from '../actions/addresses';
import {indexPeople} from '../actions/people';
import {indexResources} from '../actions/resources';
import {indexVehicles} from '../actions/vehicles';

class HistoryView extends JSXComponent {
	created() {
		bindAll(
			this,
			'fetchModelData'
		);
	}

	fetchModelData(classPK, model) {
		const {props} = this;

		const {classPKArray} = this.state;

		let watsonModelObject = null;

		if (model && classPK > 0) {
			const modelLoading = props[`${model}Loading`];

			if (!modelLoading) {
				const modelData = props[`${model}Data`];

				if (modelData) {
					watsonModelObject = modelData.get(classPK);

					if (!watsonModelObject && !classPKArray.includes(classPK)) {
						const modelName = `index${formatModelName(model)}`;

						const indexModel = props[modelName];

						if (indexModel) {
							indexModel(props.watsonIncidentId);
						}

						classPKArray.push(classPK);

						this.setState({classPKArray});
					}
				}
			}
		}

		return watsonModelObject;
	}

	formatHistoryData(watsonHistories, showCreated, showDeleted, showUpdated) {
		const datesExist = {};
		const formattedHistories = [];

		watsonHistories.forEach(
			watsonHistory => {
				const historyType = watsonHistory.get('typeLabel');
				const historyTypeId = watsonHistory.get('type');
				const timeStamp = watsonHistory.get('timeStamp');
				const watsonClassPK = watsonHistory.get('classPK');
				const watsonIncidentId = watsonHistory.get('watsonIncidentId');
				const watsonModelName = watsonHistory.get('watsonModel');

				const historyTypeLabel = getOptionsLabelFromWatsonConstants('histories', 'type', historyTypeId) || historyType || '';
				let modelName = Liferay.Language.get('not-available');
				let modelTypeLabel = '';

				const modelData = this.fetchModelData(watsonClassPK, watsonModelName);

				if (modelData) {
					modelName = modelData.get('name') || '';
					modelTypeLabel = getOptionsLabelFromWatsonConstants(watsonModelName, 'typeWatsonListTypeId', modelData.get('typeWatsonListTypeId'));
				}

				const editLabel = sub(Liferay.Language.get('x-by-x'), historyTypeLabel, watsonHistory.get('reportedBy'));

				let modifiedTimeMoment = moment(parseInt(timeStamp, 10));

				modifiedTimeMoment = modifiedTimeMoment.locale(WatsonConstants.currentLanguageId);

				const shouldHaveLink = ((watsonIncidentId > 0) && watsonModelName && watsonClassPK && (historyType != 'deleted'));

				const modelLink = shouldHaveLink ? `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${watsonModelName}/${watsonClassPK}/edit` : '';

				const modelNameAndType = modelName.includes(modelTypeLabel) ? modelName : `${modelTypeLabel}  -  ${modelName}`;

				const watsonHistoryView = (
					<a class="watson-history-row" href={modelLink}>
						<span class="timestamp">{modifiedTimeMoment.format('LTS')}</span>
						<span class={`model-icons ${watsonModelName}`} />
						<span class="watson-model"> {modelNameAndType}</span>
						<span class="action-by">{editLabel}</span>
					</a>
				);

				const createDate = modifiedTimeMoment ? modifiedTimeMoment.format('LL') : watsonHistory.get('createDate');

				if ((showCreated && historyType === 'created') || (showDeleted && historyType === 'deleted') || (showUpdated && historyType === 'updated')) {
					const dateMissing = !datesExist[createDate];

					if (dateMissing) {
						const watsonHistoryDateView = (
							<div class="date-view-wrapper">
								<div class="date-view">{modifiedTimeMoment.format('LL')}</div>
								{watsonHistoryView}
							</div>
						);

						formattedHistories.push(watsonHistoryDateView);

						datesExist[createDate] = true;
					}
					else {
						formattedHistories.push(watsonHistoryView);
					}
				}
			}
		);

		return formattedHistories;
	}

	render() {
		const {historyData = new Map(), showCreated, showDeleted, showUpdated} = this.props;

		return (
			<div class="history-view">
				{this.formatHistoryData(historyData, showCreated, showDeleted, showUpdated)}
			</div>
		);
	}
}

HistoryView.PROPS = {
	historyData: Config.value(new Map()),
	showCreated: Config.bool().value(true),
	showDeleted: Config.bool().value(true),
	showUpdated: Config.bool().value(true)
};

HistoryView.STATE = {
	classPKArray: Config.array().value([])
};

function mapStateToProps(state) {
	const activitiesData = state.getIn(['activities', 'data']) || new Map();
	const activitiesLoading = state.getIn(['activities', 'loading']);
	const addressesData = state.getIn(['addresses', 'data']) || new Map();
	const addressesLoading = state.getIn(['addresses', 'loading']);
	const incidentsData = state.getIn(['incidents', 'data']) || new Map();
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const peopleData = state.getIn(['people', 'data']) || new Map();
	const peopleLoading = state.getIn(['people', 'loading']);
	const resourcesData = state.getIn(['resources', 'data']) || new Map();
	const resourcesLoading = state.getIn(['resources', 'loading']);
	const vehiclesData = state.getIn(['vehicles', 'data']) || new Map();
	const vehiclesLoading = state.getIn(['vehicles', 'loading']);

	return {
		activitiesData,
		activitiesLoading,
		addressesData,
		addressesLoading,
		incidentsData,
		incidentsLoading,
		peopleData,
		peopleLoading,
		resourcesData,
		resourcesLoading,
		vehiclesData,
		vehiclesLoading
	};
}

function mapDispatchToProps(dispatch) {
	return {
		indexActivities: id => {
			const data = {
				id,
				includeInactive: true
			};

			dispatch(
				indexActivities(data)
			);
		},
		indexAddresses: id => {
			const data = {
				id,
				includeInactive: true
			};

			dispatch(
				indexAddresses(data)
			);
		},
		indexPeople: id => {
			const data = {
				id,
				includeInactive: true
			};

			dispatch(
				indexPeople(data)
			);
		},
		indexResources: id => {
			const data = {
				id,
				includeInactive: true
			};

			dispatch(
				indexResources(data)
			);
		},
		indexVehicles: id => {
			const data = {
				id,
				includeInactive: true
			};

			dispatch(
				indexVehicles(data)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(HistoryView);