import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map, OrderedMap} from 'immutable';
import Router from 'metal-router';
import scrollIntoView from 'scroll-into-view';
import sub from 'string-sub';

import ContentHeader from '../components/ContentHeader';
import DynamicSelectInput from '../components/DynamicInputGenerator';
import IndexList from '../components/IndexList';
import LinkButton from '../components/LinkButton';
import NavigationHeader from '../components/NavigationHeader';
import SelectInput from '../components/SelectInput';

import {indexActivities, searchActivities} from '../actions/activities';
import {indexAddresses, searchAddresses} from '../actions/addresses';
import {indexCaseworkActivities, searchCaseworkActivities} from '../actions/casework-activities';
import {indexChildren, searchChildren} from '../actions/children';
import {indexCounselingReports, searchCounselingReports} from '../actions/counseling-reports';
import {updateDisplayBy, updateFilter, updateHideLoadingOverlay, updateLastFocus, updateLastItemsLoaded, updateSortBy} from '../actions/display';
import {indexDocuments, searchDocuments} from '../actions/documents';
import {indexIllnesses, searchIllnesses} from '../actions/illnesses';
import {indexIncidents, searchIncidents} from '../actions/incidents';
import {indexLegals, searchLegals} from '../actions/legals';
import {indexPeople, searchPeople} from '../actions/people';
import {indexPhysicalExams, searchPhysicalExams} from '../actions/physical-exams';
import {indexProgressReports, searchProgressReports} from '../actions/progress-reports';
import {indexResources, searchResources} from '../actions/resources';
import {indexVehicles, searchVehicles} from '../actions/vehicles';

import {formatModelName, getPluralMessage, updateDOMTitle} from '../lib/util';

class Index extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleLoadMore',
			'handleIndexRequest',
			'handleItemClick',
			'handleSearchRequest',
			'handleUpdateDisplayBy',
			'handleUpdateFilter',
			'handleUpdateItemsLoaded',
			'handleUpdateSortBy',
			'handleUpdateChildViewBy',
			'handleUpdateIncidentViewBy',
			'refreshData',
			'scrollToPosition'
		);
	}

	detached() {
		this.handleUpdateItemsLoaded();
	}

	disposed() {
		this.props.updateHideLoadingOverlay(false);
	}

	formatHeaderString(data) {
		const {props: {filter, model, modelCount, modelLoading}} = this;

		let headerStringLeft = `${Liferay.Language.get('loading')}`;

		if (data.size || !modelLoading) {
			const modelLabel = getPluralMessage(WatsonConstants.inputConfig[model].singularLabel, WatsonConstants.inputConfig[model].pluralLabel, modelCount);

			if (filter && filter.size > 0) {
				headerStringLeft = sub(Liferay.Language.get('x-loaded-of-x-matched-x'), data.size, modelCount, modelLabel);
			}
			else {
				headerStringLeft = sub(Liferay.Language.get('x-loaded-of-x-x'), data.size, modelCount, modelLabel);
			}
		}

		return headerStringLeft;
	}

	handleIndexRequest(end = 0) {
		const {props, state} = this;

		const {model, sortBy} = props;

		const {batchCount, itemsLoaded} = state;

		const indexModelMethod = props[`index${formatModelName(model, true)}`];

		end = (end < 1) ? itemsLoaded + batchCount : end;

		if (indexModelMethod) {
			indexModelMethod(
				{
					end,
					sortBy,
					start: itemsLoaded
				}
			);
		}
	}

	handleItemClick(event) {
		const {delegateTarget} = event;

		if (delegateTarget && delegateTarget.attributes.id) {
			const lastClicked = delegateTarget.attributes.id.value;

			this.setState({lastClicked});
		}
	}

	handleLoadMore() {
		const {batchCount, filterActive, itemsLoaded} = this.state;

		if (filterActive) {
			this.handleSearchRequest();
		}
		else {
			this.handleIndexRequest();
		}

		this.state.itemsLoaded = itemsLoaded + batchCount;
	}

	handleSearchRequest(end = 0) {
		const {props, state} = this;

		const {filter, model, sortBy} = props;

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
			end = (end < 1) ? itemsLoaded + batchCount : end;

			searchModelMethod(
				{
					end,
					fields: fieldsArray,
					keywords: keywordsArray,
					sortBy,
					start: itemsLoaded
				}
			);
		}
	}

	handleUpdateDisplayBy() {
		const {displayBy, model, updateDisplayBy} = this.props;

		if ((model && model !== displayBy) || model === '') {
			updateDisplayBy(model);
		}
	}

	handleUpdateFilter(filterData) {
		const {
			model,
			updateFilter
		} = this.props;

		updateFilter(filterData, 0, model);
	}

	handleUpdateItemsLoaded() {
		const {
			model,
			updateLastItemsLoaded
		} = this.props;

		const {itemsLoaded, lastClicked} = this.state;

		const data = {
			lastClicked,
			lastLoaded: itemsLoaded,
			model,
			watsonParentPrimaryKey: 0
		};

		if (updateLastItemsLoaded) {
			updateLastItemsLoaded(data);
		}
	}

	handleUpdateSortBy(sortByData) {
		const {
			model,
			updateSortBy
		} = this.props;

		updateSortBy(sortByData, 0, model);
	}

	handleUpdateIncidentViewBy(model) {
		Router.router().navigate(`${WatsonConstants.urls.baseURL}/${model}`);
	}

	handleUpdateChildViewBy(model) {
		Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/index/${model}`);
	}

	refreshData(forceReindex) {
		const {props, state} = this;

		const {loading, model} = props;

		const {filterActive, resendSearchRequest} = state;

		if (model !== state.modelDisplayed && !filterActive || forceReindex) {
			this.state.modelDisplayed = model;

			this.state.itemsLoaded = 0;

			this.handleLoadMore();
		}

		if (!loading && resendSearchRequest) {
			this.handleSearchRequest();

			this.state.resendSearchRequest = false;
		}
	}

	render() {
		const {
			filter,
			incidentsData,
			incidentsLoading,
			model,
			modelCount,
			modelData,
			modelLoading,
			sortBy
		} = this.props;

		let data = incidentsData;
		let loading = incidentsLoading;

		if (model !== 'incidents') {
			data = modelData;
			loading = modelLoading;
		}

		const {childrensHomeManagerRole, incidentManagerRole, watsonAdministratorRole} = WatsonConstants.currentUser;

		let retVal = {};

		if (WatsonConstants.inputConfig.children.viewByOptions[model]) {
			retVal = (
				<span>
					<NavigationHeader
						mainHeader={Liferay.Language.get('children')}
					/>

					<LinkButton
						className="primary"
						href={`${WatsonConstants.urls.baseURL}/children/create/`}
						label={Liferay.Language.get('create-child')}
					/>

					{watsonAdministratorRole == true || (childrensHomeManagerRole == true && incidentManagerRole == true) &&
						<LinkButton
							className="primary"
							href={`${WatsonConstants.urls.baseURL}/children/import/`}
							label={Liferay.Language.get('import-from-ZCR')}
						/>
					}

					<div class="view-by-label">
						{Liferay.Language.get('display-by')}
					</div>

					<SelectInput
						omitBlankOption={true}
						onChange={this.handleUpdateChildViewBy}
						options={WatsonConstants.inputConfig.children.viewByOptions}
						value={model}
					/>
				</span>
			);
		}
		else {
			retVal = (
				<span>
					<NavigationHeader
						mainHeader={Liferay.Language.get('incidents')}
					/>

					<LinkButton
						className="primary"
						href={`${WatsonConstants.urls.baseURL}/incidents/create/`}
						label={Liferay.Language.get('create-incident')}
					/>

					<div class="view-by-label">
						{Liferay.Language.get('display-by')}
					</div>

					<SelectInput
						omitBlankOption={true}
						onChange={this.handleUpdateIncidentViewBy}
						options={WatsonConstants.inputConfig.incidents.viewByOptions}
						value={model}
					/>
				</span>
			);
		}

		return (
			<div class="incidents-index page-container hidden-print">
				<div class="navigation-sidebar">

					{retVal}

					<div class="filter-header">
						{Liferay.Language.get('filter-by')}
					</div>

					<DynamicSelectInput
						elementClasses="filter-input"
						filter={filter}
						inputConfig={WatsonConstants.inputConfig[model].inputs}
						label={Liferay.Language.get('add-filter')}
						onChange={this.handleUpdateFilter}
					/>
				</div>

				<div class="content-container">
					<div class="content-header">
						<ContentHeader headerStringLeft={this.formatHeaderString(data, model)} headerStringRight={Liferay.Language.get('sort-by')} />

						<SelectInput omitBlankOption={true} onChange={this.handleUpdateSortBy} options={WatsonConstants.inputConfig[model].sortByOptions} value={sortBy} />
					</div>

					{((data.size > 0) || loading) &&
						<div class="content">
							<IndexList
								data={data}
								filterActive={this.state.filterActive}
								hasMoreResults={data.size < modelCount}
								incidentsData={incidentsData}
								loading={loading}
								loadMoreMethod={this.handleLoadMore}
								model={model}
								onClick={this.handleItemClick}
								simple={false}
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

	rendered(firstRender) {
		this.refreshData(false);

		const {hideLoadingOverlay, lastFocus, lastLoadedItemData, loading, model, updateHideLoadingOverlay, updateLastFocus} = this.props;

		this.handleUpdateDisplayBy();

		updateDOMTitle(WatsonConstants.inputConfig[model].pluralLabel);

		const autoScrolling = this.scrollToPosition(firstRender, lastLoadedItemData, loading);

		if (autoScrolling && hideLoadingOverlay) {
			updateHideLoadingOverlay(false);
		}
		else if (!autoScrolling && !hideLoadingOverlay) {
			updateHideLoadingOverlay(true);
		}

		const {lastClicked} = lastLoadedItemData;

		if ((!loading && !firstRender && !autoScrolling) && (!lastFocus || lastClicked != lastFocus)) {
			const element = document.getElementById(lastClicked);

			if (element) {
				scrollIntoView(element, updateLastFocus(lastClicked));
			}
		}
	}

	scrollToPosition(firstRender, lastLoadedItemData, loading) {
		let autoScrolling = false;

		const {filterActive, itemsLoaded} = this.state;

		const {lastLoaded} = lastLoadedItemData;

		if (!firstRender && !loading && lastLoaded > 0 && (itemsLoaded < lastLoaded)) {
			if (filterActive) {
				this.handleSearchRequest(lastLoaded);
			}
			else {
				this.handleIndexRequest(lastLoaded);
			}

			this.state.itemsLoaded = lastLoaded + itemsLoaded;

			autoScrolling = true;
		}

		return autoScrolling;
	}

	syncFilter(newState, oldState) {
		if (newState && newState !== oldState) {
			if (newState.size > 0) {
				this.setState(
					{
						filterActive: true,
						itemsLoaded: 0,
						resendSearchRequest: true
					}
				);
			}
			else {
				this.setState(
					{
						filterActive: false,
						itemsLoaded: 0,
						resendSearchRequest: false
					}
				);

				this.refreshData(true);
			}
		}
	}

	syncModel(newState) {
		this.setState(
			{
				model: newState,
				modelDisplayed: newState
			}
		);
	}

	syncSortBy(newState, oldState) {
		if (newState && newState !== oldState) {
			this.state.itemsLoaded = 0;

			this.refreshData(true);
		}
	}
}

Index.PROPS = {
	filter: Config.value(new Map()),
	incidentsData: Config.value(new Map()),
	incidentsLoading: Config.bool().value(false),
	lastFocus: Config.string().value(''),
	lastLoadedItemData: Config.object().value({}),
	loading: Config.bool(),
	model: Config.string(),
	modelCount: Config.string().value('0'),
	modelData: Config.value(new Map()),
	modelLoading: Config.bool().value(false),
	sortBy: Config.string().value('watsonIncidentId')
};

Index.STATE = {
	batchCount: Config.number().value(30),
	filterActive: Config.bool(),
	hasMoreResults: Config.bool().value(false),
	itemsLoaded: Config.number().value(0),
	lastClicked: Config.string().value(''),
	modelDisplayed: Config.string(),
	resendSearchRequest: Config.bool()
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
		updateDisplayBy: displayBy => {
			dispatch(
				updateDisplayBy(displayBy)
			);
		},
		updateFilter: (filterData, watsonParentPrimaryKey, model) => {
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
		updateLastFocus: data => {
			dispatch(
				updateLastFocus(data)
			);
		},
		updateLastItemsLoaded: data => {
			dispatch(
				updateLastItemsLoaded(data)
			);
		},
		updateSortBy: (sortByData, watsonParentPrimaryKey, model) => {
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
	const {model = 'incidents'} = props.router.params;

	const incidentsData = state.getIn(['incidents', 'data']) || new OrderedMap();
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const modelCount = state.getIn([model, 'count']) || '0';
	const modelData = state.getIn([model, 'data']) || new OrderedMap();
	const modelLoading = state.getIn([model, 'loading']);

	const lastLoaded = state.getIn(['display', 'lastLoaded', 0]) || {};

	const lastLoadedItemData = lastLoaded[model] || {};

	const sortBy = state.getIn(['display', 'sortBy', 0, model]) || WatsonConstants.inputConfig[model].sortByDefault;

	return {
		displayBy: state.getIn(['display', 'displayBy']),
		filter: state.getIn(['display', 'filter', 0, model]),
		hideLoadingOverlay: state.getIn(['display', 'hideLoadingOverlay']),
		incidentsData,
		incidentsLoading,
		lastFocus: state.getIn(
			[
				'display',
				'lastFocus'
			]
		),
		lastLoadedItemData,
		loading: modelLoading,
		model,
		modelCount,
		modelData,
		modelLoading,
		sortBy
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(Index);