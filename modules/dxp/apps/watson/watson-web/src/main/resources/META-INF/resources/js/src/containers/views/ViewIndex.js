import {bindAll, isEmpty, noop} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import sub from 'string-sub';

import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import DynamicSelectInput from '../../components/DynamicInputGenerator';
import IndexList from '../../components/IndexList';
import SelectInput from '../../components/SelectInput';

import {indexActivities, searchActivities} from '../../actions/activities';
import {indexAddresses, searchAddresses} from '../../actions/addresses';
import {indexCaseworkActivities, searchCaseworkActivities} from '../../actions/casework-activities';
import {indexChildren, searchChildren} from '../../actions/children';
import {indexCounselingReports, searchCounselingReports} from '../../actions/counseling-reports';
import {updateFilter, updateHideLoadingOverlay, updateSortBy} from '../../actions/display';
import {indexDocuments, searchDocuments} from '../../actions/documents';
import {indexIllnesses, searchIllnesses} from '../../actions/illnesses';
import {indexIncidents, searchIncidents} from '../../actions/incidents';
import {indexLegals, searchLegals} from '../../actions/legals';
import {indexPeople, searchPeople} from '../../actions/people';
import {indexPhysicalExams, searchPhysicalExams} from '../../actions/physical-exams';
import {indexProgressReports, searchProgressReports} from '../../actions/progress-reports';
import {indexResources, searchResources} from '../../actions/resources';
import {indexVehicles, searchVehicles} from '../../actions/vehicles';

import {formatModelName, getPluralMessage, updateDOMTitle} from '../../lib/util';

class ViewIndex extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleButtonClick',
			'_handleLoadMore',
			'_handleOnClick',
			'_handleUpdateFilter',
			'_handleUpdateSortBy',
			'refreshData'
		);

		this.props.updateHideLoadingOverlay(true);
	}

	disposed() {
		this.props.updateHideLoadingOverlay(false);
	}

	formatHeaderString(data) {
		const {filter, model, modelCount, modelLoading} = this.props;

		let headerStringLeft = `${Liferay.Language.get('loading')}`;

		if ((data.size || !modelLoading) && model) {
			const modelConfig = WatsonConstants.inputConfig[model];

			let modelLabel = '';

			if (modelConfig) {
				modelLabel = getPluralMessage(modelConfig.singularLabel, modelConfig.pluralLabel, modelCount);
			}

			if (filter && filter.size > 0) {
				headerStringLeft = sub(Liferay.Language.get('x-loaded-of-x-matched-x'), data.size, modelCount, modelLabel);
			}
			else {
				headerStringLeft = sub(Liferay.Language.get('x-loaded-of-x-x'), data.size, modelCount, modelLabel);
			}
		}

		return headerStringLeft;
	}

	_handleButtonClick() {
		const {props, state} = this;

		const {buttonMethod, redirect, submitMethod} = props;

		if (buttonMethod) {
			buttonMethod();
		}

		if (submitMethod && state.selectedIds) {
			const postData = {};

			postData.selectedIds = state.selectedIds;

			postData.id = props.watsonParentPrimaryKey;

			submitMethod(postData);

			if (redirect) {
				redirect();
			}
		}
	}

	_handleLoadMore() {
		const {batchCount, filterActive, itemsLoaded} = this.state;

		if (filterActive) {
			this._handleSearchRequest();
		}
		else {
			this._handleIndexRequest();
		}

		this.state.itemsLoaded = itemsLoaded + batchCount;
	}

	_handleOnClick(event) {
		const {delegateTarget} = event;

		let entryId = 0;

		if (delegateTarget && delegateTarget.attributes.id) {
			entryId = delegateTarget.attributes.id.value;

			if (this.props.action === 'view') {
				window.open(`${WatsonConstants.urls.baseURL}/incidents/${entryId}/edit`);
			}
			else {
				let {selectedIds} = this.state;

				const index = selectedIds.indexOf(entryId);

				if (index !== -1) {
					selectedIds.splice(index, 1);
				}
				else {
					selectedIds.push(entryId);
				}

				selectedIds = [...(new Set(selectedIds))];

				this.setState({selectedIds});
			}
		}
	}

	_handleIndexRequest() {
		const {props, state} = this;

		const {action, disableDataFetch, model, sortBy, watsonParentPrimaryKey} = props;

		if (!disableDataFetch) {
			const {batchCount, itemsLoaded} = state;

			const indexModelMethod = props[`index${formatModelName(model, true)}`];

			if (indexModelMethod) {
				indexModelMethod(
					{
						actionType: action,
						end: itemsLoaded + batchCount,
						id: watsonParentPrimaryKey,
						sortBy,
						start: itemsLoaded
					}
				);
			}
		}
	}

	_handleSearchRequest() {
		const {props, state} = this;

		const {action, disableDataFetch, filter, model, sortBy} = props;

		if (!disableDataFetch) {
			const {batchCount, itemsLoaded} = state;

			const searchModelMethod = props[`search${formatModelName(model, true)}`];

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

			if (searchModelMethod && !isEmpty(fieldsArray) && !isEmpty(keywordsArray)) {
				if (action !== 'import' && props.watsonIncidentId) {
					fieldsArray.push('watsonIncidentId');
					keywordsArray.push(props.watsonIncidentId);
				}

				if (action !== 'import' && props.watsonChildId) {
					fieldsArray.push('watsonChildId');
					keywordsArray.push(props.watsonChildId);
				}

				searchModelMethod(
					{
						actionType: action,
						end: itemsLoaded + batchCount,
						fields: fieldsArray,
						keywords: keywordsArray,
						sortBy,
						start: itemsLoaded
					}
				);
			}
		}
	}

	_handleUpdateFilter(filterData) {
		const {
			model,
			updateFilter,
			watsonParentPrimaryKey
		} = this.props;

		updateFilter(filterData, model, watsonParentPrimaryKey);
	}

	_handleUpdateSortBy(sortByData) {
		const {
			model,
			updateSortBy,
			watsonParentPrimaryKey
		} = this.props;

		updateSortBy(sortByData, model, watsonParentPrimaryKey);
	}

	refreshData(forceReindex) {
		const {props, state} = this;

		const {action, disableDataFetch, loading, model} = props;

		const {actionDisplayed, modelDisplayed, resendSearchRequest} = state;

		if (!disableDataFetch) {
			if (((model !== modelDisplayed) || (action !== actionDisplayed)) || forceReindex) {
				this.state.actionDisplayed = action;
				this.state.modelDisplayed = model;

				this.state.itemsLoaded = 0;

				this._handleIndexRequest();
			}

			if (!loading && resendSearchRequest) {
				this._handleSearchRequest();

				this.state.resendSearchRequest = false;
			}
		}
	}

	render() {
		const {props} = this;

		const {
			action,
			buttonData,
			disabled,
			disableDataFetch,
			filter,
			forwardedData,
			forwardedLoading,
			headerStringLeft,
			keysToOmit,
			model,
			modelCount,
			modelData,
			modelLoading,
			sortBy
		} = props;

		let {
			incidentsData: data,
			incidentsLoading: loading
		} = props;

		const {filterActive, selectedIds} = this.state;

		if (disableDataFetch) {
			data = forwardedData;
			loading = forwardedLoading;
		}
		else {
			data = modelData;
			loading = modelLoading;
		}

		const buttons = [];

		if (buttonData && !disabled) {
			buttonData.forEach(
				button => {
					buttons.push(<Button label={button.label} onClick={button.method || this._handleButtonClick} />);
				}
			);
		}

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader
						headerStringLeft={headerStringLeft || this.formatHeaderString(data)}
						headerStringRight={Liferay.Language.get('sort-by')}
					/>

					<SelectInput
						omitBlankOption={true}
						onChange={this._handleUpdateSortBy}
						options={WatsonConstants.inputConfig[model].sortByOptions}
						value={sortBy || 'watsonIncidentId'}
					/>
				</div>

				<div class="view-index-content">
					<div class="sub-sidebar">
						{buttons}

						<div class="filter-header">
							{Liferay.Language.get('filter-by')}
						</div>

						<DynamicSelectInput
							elementClasses="filter-input"
							filter={filter}
							inputConfig={WatsonConstants.inputConfig[model].inputs}
							label={Liferay.Language.get('add-filter')}
							onChange={this._handleUpdateFilter}
						/>
					</div>

					{((data.size > 0) || loading) &&
						<div class="view-index">
							<IndexList
								data={data}
								filterActive={filterActive}
								hasMoreResults={data.size < modelCount}
								keysToOmit={keysToOmit}
								loading={loading}
								loadMoreMethod={this._handleLoadMore}
								model={model}
								onClick={action === 'index' ? undefined : this._handleOnClick}
								selectedIds={selectedIds}
								simple={true}
								sortBy={sortBy}
							/>
						</div>
					}

					{((data.size < 1) && !loading) &&
						<div class="no-entries">
							{sub(Liferay.Language.get('no-x-found'), WatsonConstants.inputConfig[model].pluralLabel)}
						</div>
					}
				</div>
			</div>
		);
	}

	rendered() {
		this.refreshData(false);

		const {hideLoadingOverlay, model, primaryName, updateHideLoadingOverlay} = this.props;

		if (!hideLoadingOverlay) {
			updateHideLoadingOverlay(true);
		}

		updateDOMTitle(`${primaryName} ${WatsonConstants.inputConfig[model].pluralLabel}`);
	}

	syncFilter(newState, oldState) {
		if (oldState && newState && newState !== oldState) {
			if (newState.size > 0) {
				this.setState(
					{
						filterActive: true,
						itemsLoaded: 0,
						resendSearchRequest: true
					}
				);

				this.props.filterActiveCallback(newState);
			}
			else {
				this.setState(
					{
						filterActive: false,
						itemsLoaded: 0
					}
				);

				this.refreshData(true);

				this.props.filterActiveCallback(newState);
			}
		}
	}

	syncSelectedIds(newState, oldState) {
		if (newState && JSON.stringify(newState) !== JSON.stringify(oldState)) {
			const formattedSelectedIds = [];

			for (const id of newState) {
				formattedSelectedIds.push(id.toString());
			}

			this.setState({selectedIds: formattedSelectedIds});
		}
	}

	syncSortBy(newState, oldState) {
		if (oldState && newState && newState !== oldState) {
			this.state.itemsLoaded = 0;

			this.refreshData(true);
		}
	}
}

ViewIndex.PROPS = {
	action: Config.string().value('index'),
	buttonData: Config.any(),
	component: Config.any(),
	disableDataFetch: Config.bool().value(false),
	filter: Config.value(new Map()),
	filterActiveCallback: Config.func().value(noop),
	forwardedData: Config.any(),
	forwardedLoading: Config.bool(),
	headerStringLeft: Config.string(),
	incidentsData: Config.value(new Map()),
	incidentsLoading: Config.bool().value(false),
	keysToOmit: Config.array().value([]),
	model: Config.string(),
	modelCount: Config.string().value('0'),
	modelData: Config.value(new Map()),
	modelLoading: Config.bool().value(false),
	primaryName: Config.string(),
	selectedIds: Config.array().value([]),
	sortBy: Config.string(),
	watsonChildId: Config.value(''),
	watsonIncidentId: Config.value(''),
	watsonParentPrimaryKey: Config.value('')
};

ViewIndex.STATE = {
	actionDisplayed: Config.string(),
	batchCount: Config.number().value(20),
	filterActive: Config.bool(),
	hasMoreResults: Config.bool(),
	itemsLoaded: Config.number().value(0),
	modelDisplayed: Config.string(),
	resendSearchRequest: Config.bool().value(false),
	selectedIds: Config.array().value([])
};

function mapDispatchToProps(dispatch) {
	return {
		indexActivities: data => {
			dispatch(
				indexActivities(data)
			);
		},
		indexAddresses: data => {
			dispatch(
				indexAddresses(data)
			);
		},
		indexCaseworkActivities: data => {
			data.key = WatsonConstants.inputConfig.casework_activities.key;

			dispatch(
				indexCaseworkActivities(data)
			);
		},
		indexChildren: data => {
			dispatch(
				indexChildren(data)
			);
		},
		indexCounselingReports: data => {
			data.key = WatsonConstants.inputConfig.counseling_reports.key;

			dispatch(
				indexCounselingReports(data)
			);
		},
		indexDocuments: data => {
			dispatch(
				indexDocuments(data)
			);
		},
		indexIllnesses: data => {
			data.key = WatsonConstants.inputConfig.illnesses.key;

			dispatch(
				indexIllnesses(data)
			);
		},
		indexIncidents: data => {
			dispatch(
				indexIncidents(data)
			);
		},
		indexLegals: data => {
			data.key = WatsonConstants.inputConfig.legals.key;

			dispatch(
				indexLegals(data)
			);
		},
		indexPeople: data => {
			dispatch(
				indexPeople(data)
			);
		},
		indexPhysicalExams: data => {
			data.key = WatsonConstants.inputConfig.physical_exams.key;

			dispatch(
				indexPhysicalExams(data)
			);
		},
		indexProgressReports: data => {
			data.key = WatsonConstants.inputConfig.progress_reports.key;

			dispatch(
				indexProgressReports(data)
			);
		},
		indexResources: data => {
			dispatch(
				indexResources(data)
			);
		},
		indexVehicles: data => {
			dispatch(
				indexVehicles(data)
			);
		},
		searchActivities: data => {
			dispatch(
				searchActivities(data)
			);
		},
		searchAddresses: data => {
			dispatch(
				searchAddresses(data)
			);
		},
		searchCaseworkActivities: data => {
			data.key = WatsonConstants.inputConfig.casework_activities.key;

			dispatch(
				searchCaseworkActivities(data)
			);
		},
		searchChildren: data => {
			dispatch(
				searchChildren(data)
			);
		},
		searchCounselingReports: data => {
			data.key = WatsonConstants.inputConfig.counseling_reports.key;

			dispatch(
				searchCounselingReports(data)
			);
		},
		searchDocuments: data => {
			dispatch(
				searchDocuments(data)
			);
		},
		searchIllnesses: data => {
			data.key = WatsonConstants.inputConfig.illnesses.key;

			dispatch(
				searchIllnesses(data)
			);
		},
		searchIncidents: data => {
			dispatch(
				searchIncidents(data)
			);
		},
		searchLegals: data => {
			data.key = WatsonConstants.inputConfig.legals.key;

			dispatch(
				searchLegals(data)
			);
		},
		searchPeople: data => {
			dispatch(
				searchPeople(data)
			);
		},
		searchPhysicalExams: data => {
			data.key = WatsonConstants.inputConfig.physical_exams.key;

			dispatch(
				searchPhysicalExams(data)
			);
		},
		searchProgressReports: data => {
			data.key = WatsonConstants.inputConfig.progress_reports.key;

			dispatch(
				searchProgressReports(data)
			);
		},
		searchResources: data => {
			dispatch(
				searchResources(data)
			);
		},
		searchVehicles: data => {
			dispatch(
				searchVehicles(data)
			);
		},
		updateFilter: (filterData, model, watsonParentPrimaryKey) => {
			const action = {
				filterData,
				model,
				watsonParentPrimaryKey
			};

			dispatch(
				updateFilter(action)
			);
		},
		updateHideLoadingOverlay: data => {
			dispatch(
				updateHideLoadingOverlay(data)
			);
		},
		updateSortBy: (sortByData, model, watsonParentPrimaryKey) => {
			const action = {
				model,
				sortByData,
				watsonParentPrimaryKey
			};

			dispatch(
				updateSortBy(action)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {action, model = 'incidents', watsonChildId = 0, watsonIncidentId = 0} = props;

	const watsonParentPrimaryKey = watsonChildId ? watsonChildId : watsonIncidentId;

	const modelConfig = WatsonConstants.inputConfig[model];

	const sortBy = state.getIn(['display', 'sortBy', watsonParentPrimaryKey, model]) || (modelConfig ? modelConfig.sortByDefault : '');

	return {
		action,
		filter: state.getIn(['display', 'filter', watsonParentPrimaryKey, model]),
		hideLoadingOverlay: state.getIn(['display', 'hideLoadingOverlay']),
		model,
		modelCount: state.getIn([model, 'count']) || '0',
		modelData: state.getIn([model, 'data']) || new Map(),
		modelLoading: state.getIn([model, 'loading']) || false,
		sortBy,
		watsonParentPrimaryKey
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ViewIndex);