import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import moment from 'moment';
import sub from 'string-sub';

import {formatModelName, getOptionsLabelFromWatsonConstants} from '../lib/util';

import {indexActivities} from '../actions/activities';
import {indexAddresses} from '../actions/addresses';
import {indexCaseworkActivities} from '../actions/casework-activities';
import {indexCounselingReports} from '../actions/counseling-reports';
import {indexChildren} from '../actions/children';
import {indexDocuments} from '../actions/documents';
import {indexIllnesses} from '../actions/illnesses';
import {indexLegals} from '../actions/legals';
import {indexPeople} from '../actions/people';
import {indexPhysicalExams} from '../actions/physical-exams';
import {indexProgressReports} from '../actions/progress-reports';
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
			const modelLoading = props[`${formatModelName(model, false)}Loading`];

			if (!modelLoading) {
				const modelData = props[`${formatModelName(model, false)}Data`];

				if (modelData) {
					watsonModelObject = modelData.get(classPK);

					if (!watsonModelObject && !classPKArray.includes(classPK)) {
						const modelName = `index${formatModelName(model, true)}`;

						const indexModel = props[modelName];

						if (indexModel) {
							indexModel(props.watsonParentId);
						}

						classPKArray.push(classPK);

						this.setState({classPKArray});
					}
				}
			}
		}

		return watsonModelObject;
	}

	formatHistoryData(watsonHistories, watsonParentModel, showCreated, showDeleted, showUpdated) {
		const datesExist = {};
		const formattedHistories = [];

		watsonHistories.forEach(
			watsonHistory => {
				const historyType = watsonHistory.get('typeLabel');
				const historyTypeId = watsonHistory.get('type');
				const timeStamp = watsonHistory.get('timeStamp');
				const watsonClassPK = watsonHistory.get('classPK');
				const watsonModelName = watsonHistory.get('watsonModel');
				const watsonParentId = watsonHistory.get('watsonParentId');

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

				const modelLink = this.getModelLink(watsonModelName, watsonClassPK, watsonParentId, watsonParentModel, historyType);

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

	getModelLink(watsonModelName, watsonClassPK, watsonParentId, watsonParentModel, historyType) {
		let linkRetVal = '';

		if ((watsonParentId > 0) && watsonModelName && watsonClassPK && (historyType !== 'deleted')) {
			if (watsonModelName === 'incidents' || watsonModelName === 'children') {
				linkRetVal = `${WatsonConstants.urls.baseURL}/${watsonParentModel}/${watsonParentId}/edit/`;
			}
			else {
				linkRetVal = `${WatsonConstants.urls.baseURL}/${watsonParentModel}/${watsonParentId}/edit/${watsonModelName}/${watsonClassPK}/edit`;
			}
		}

		return linkRetVal;
	}

	render() {
		const {historyData = new Map(), showCreated, showDeleted, showUpdated, watsonParentModel} = this.props;

		return (
			<div class="history-view">
				{this.formatHistoryData(historyData, watsonParentModel, showCreated, showDeleted, showUpdated)}
			</div>
		);
	}
}

HistoryView.PROPS = {
	activitiesData: Config.value(new Map()),
	activitiesLoading: Config.bool(),
	addressesData: Config.value(new Map()),
	addressesLoading: Config.bool(),
	caseworkActivitiesData: Config.value(new Map()),
	caseworkActivitiesLoading: Config.bool(),
	childrenData: Config.value(new Map()),
	childrenLoading: Config.bool(),
	counselingReportsData: Config.value(new Map()),
	counselingReportsLoading: Config.bool(),
	documentsData: Config.value(new Map()),
	documentsLoading: Config.bool(),
	historyData: Config.value(new Map()),
	illnessesData: Config.value(new Map()),
	illnessesLoading: Config.bool(),
	incidentsData: Config.value(new Map()),
	incidentsLoading: Config.bool(),
	legalsData: Config.value(new Map()),
	legalsLoading: Config.bool(),
	peopleData: Config.value(new Map()),
	peopleLoading: Config.bool(),
	physicalExamsData: Config.value(new Map()),
	physicalExamsLoading: Config.bool(),
	progressReportsData: Config.value(new Map()),
	progressReportsLoading: Config.bool(),
	resourcesData: Config.value(new Map()),
	resourcesLoading: Config.bool(),
	showCreated: Config.bool().value(true),
	showDeleted: Config.bool().value(true),
	showUpdated: Config.bool().value(true),
	vehiclesData: Config.value(new Map()),
	vehiclesLoading: Config.bool(),
	watsonParentId: Config.string(),
	watsonParentModel: Config.string()
};

HistoryView.STATE = {
	classPKArray: Config.array().value([])
};

function mapStateToProps(state) {
	const activitiesData = state.getIn(['activities', 'data']) || new Map();
	const activitiesLoading = state.getIn(['activities', 'loading']);
	const addressesData = state.getIn(['addresses', 'data']) || new Map();
	const addressesLoading = state.getIn(['addresses', 'loading']);
	const caseworkActivitiesData = state.getIn(['casework_activities', 'data']) || new Map();
	const caseworkActivitiesLoading = state.getIn(['casework_activities', 'loading']);
	const childrenData = state.getIn(['children', 'data']) || new Map();
	const childrenLoading = state.getIn(['children', 'loading']);
	const counselingReportsData = state.getIn(['counseling_reports', 'data']) || new Map();
	const counselingReportsLoading = state.getIn(['counseling_reports', 'loading']);
	const documentsData = state.getIn(['documents', 'data']) || new Map();
	const documentsLoading = state.getIn(['documents', 'loading']);
	const illnessesData = state.getIn(['illnesses', 'data']) || new Map();
	const illnessesLoading = state.getIn(['illnesses', 'loading']);
	const incidentsData = state.getIn(['incidents', 'data']) || new Map();
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const legalsData = state.getIn(['legals', 'data']) || new Map();
	const legalsLoading = state.getIn(['legals', 'loading']);
	const peopleData = state.getIn(['people', 'data']) || new Map();
	const peopleLoading = state.getIn(['people', 'loading']);
	const physicalExamsData = state.getIn(['physical_exams', 'data']) || new Map();
	const physicalExamsLoading = state.getIn(['physical_exams', 'loading']);
	const progressReportsData = state.getIn(['progress_reports', 'data']) || new Map();
	const progressReportsLoading = state.getIn(['progress_reports', 'loading']);
	const resourcesData = state.getIn(['resources', 'data']) || new Map();
	const resourcesLoading = state.getIn(['resources', 'loading']);
	const vehiclesData = state.getIn(['vehicles', 'data']) || new Map();
	const vehiclesLoading = state.getIn(['vehicles', 'loading']);

	return {
		activitiesData,
		activitiesLoading,
		addressesData,
		addressesLoading,
		caseworkActivitiesData,
		caseworkActivitiesLoading,
		childrenData,
		childrenLoading,
		counselingReportsData,
		counselingReportsLoading,
		documentsData,
		documentsLoading,
		illnessesData,
		illnessesLoading,
		incidentsData,
		incidentsLoading,
		legalsData,
		legalsLoading,
		peopleData,
		peopleLoading,
		physicalExamsData,
		physicalExamsLoading,
		progressReportsData,
		progressReportsLoading,
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
		indexCaseworkActivities: id => {
			const data = {
				id,
				includeInactive: true,
				key: WatsonConstants.inputConfig.casework_activities.key
			};

			dispatch(
				indexCaseworkActivities(data)
			);
		},
		indexChildren: id => {
			const data = {
				id,
				includeInactive: true
			};

			dispatch(
				indexChildren(data)
			);
		},
		indexCounselingReports: id => {
			const data = {
				id,
				includeInactive: true,
				key: WatsonConstants.inputConfig.counseling_reports.key
			};

			dispatch(
				indexCounselingReports(data)
			);
		},
		indexDocuments: id => {
			const data = {
				id,
				includeInactive: true
			};

			dispatch(
				indexDocuments(data)
			);
		},
		indexIllnesses: id => {
			const data = {
				id,
				includeInactive: true,
				key: WatsonConstants.inputConfig.illnesses.key
			};

			dispatch(
				indexIllnesses(data)
			);
		},
		indexLegals: id => {
			const data = {
				id,
				includeInactive: true,
				key: WatsonConstants.inputConfig.legals.key
			};

			dispatch(
				indexLegals(data)
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
		indexPhysicalExams: id => {
			const data = {
				id,
				includeInactive: true,
				key: WatsonConstants.inputConfig.physical_exams.key
			};

			dispatch(
				indexPhysicalExams(data)
			);
		},
		indexProgressReports: id => {
			const data = {
				id,
				includeInactive: true,
				key: WatsonConstants.inputConfig.progress_reports.key
			};

			dispatch(
				indexProgressReports(data)
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