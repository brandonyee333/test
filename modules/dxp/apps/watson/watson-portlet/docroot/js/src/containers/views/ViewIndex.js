import {bindAll, capitalize, isEmpty, noop} from 'lodash';
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
import {updateFilter, updateHideLoadingOverlay, updateSortBy} from '../../actions/display';
import {indexIncidents, searchIncidents} from '../../actions/incidents';
import {indexPeople, searchPeople} from '../../actions/people';
import {indexResources, searchResources} from '../../actions/resources';
import {indexVehicles, searchVehicles} from '../../actions/vehicles';

import {getPluralMessage, updateDOMTitle} from '../../lib/util';

class ViewIndex extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnClick',
			'handleLoadMore',
			'handleUpdateFilter',
			'handleUpdateSortBy',
			'handleButtonClick',
			'refreshData'
		);

		this.props.updateHideLoadingOverlay(true);
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

	handleButtonClick() {
		const {props, state} = this;

		const {buttonMethod, redirect, submitMethod} = props;

		if (buttonMethod) {
			buttonMethod();
		}

		if (submitMethod && state.selectedIds) {
			const postData = {};

			postData.selectedIds = state.selectedIds;

			postData.id = props.watsonIncidentId;

			submitMethod(postData);

			if (redirect) {
				redirect();
			}
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

	handleOnClick(event) {
		const {delegateTarget} = event;

		if (delegateTarget && delegateTarget.attributes.id) {
			const entryId = delegateTarget.attributes.id.value;

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

	handleIndexRequest() {
		const {props, state} = this;

		const {action, disableDataFetch, model, sortBy, watsonIncidentId} = props;

		if (!disableDataFetch) {
			const {batchCount, itemsLoaded} = state;

			const indexModelMethod = props[`index${capitalize(model)}`];

			if (indexModelMethod) {
				indexModelMethod(
					{
						actionType: action,
						end: itemsLoaded + batchCount,
						id: watsonIncidentId,
						sortBy,
						start: itemsLoaded
					}
				);
			}
		}
	}

	handleSearchRequest() {
		const {props, state} = this;

		const {action, disableDataFetch, filter, model, sortBy} = props;

		if (!disableDataFetch) {
			const {batchCount, itemsLoaded} = state;

			const searchModelMethod = props[`search${capitalize(model)}`];

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
				if (action !== 'import') {
					fieldsArray.push('watsonIncidentId');
					keywordsArray.push(props.watsonIncidentId);
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

	handleUpdateFilter(filterData) {
		const {
			model,
			updateFilter,
			watsonIncidentId
		} = this.props;

		updateFilter(filterData, watsonIncidentId, model);
	}

	handleUpdateSortBy(sortByData) {
		const {
			model,
			updateSortBy,
			watsonIncidentId
		} = this.props;

		updateSortBy(sortByData, watsonIncidentId, model);
	}

	refreshData(forceReindex) {
		const {props, state} = this;

		const {action, disableDataFetch, loading, model} = props;

		const {actionDisplayed, modelDisplayed, resendSearchRequest} = state;

		if (!disableDataFetch) {
			if ((model !== modelDisplayed || action !== actionDisplayed) || forceReindex) {
				this.state.actionDisplayed = action;
				this.state.modelDisplayed = model;

				this.state.itemsLoaded = 0;

				this.handleIndexRequest();
			}

			if (!loading && resendSearchRequest) {
				this.handleSearchRequest();

				this.state.resendSearchRequest = false;
			}
		}
	}

	render() {
		const {props} = this;

		const {
			action = 'index',
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

		const {selectedIds} = this.state;

		if (disableDataFetch) {
			data = forwardedData;
			loading = forwardedLoading;
		}
		else if (model !== 'incidents') {
			data = modelData;
			loading = modelLoading;
		}

		const buttons = [];

		if (buttonData && !disabled) {
			buttonData.forEach(
				button => {
					buttons.push(<Button label={button.label} onClick={button.method || this.handleButtonClick} />);
				}
			);
		}

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft || this.formatHeaderString(data)} headerStringRight={Liferay.Language.get('sort-by')} />

					<SelectInput omitBlankOption={true} onChange={this.handleUpdateSortBy} options={WatsonConstants.inputConfig[model].sortByOptions} value={sortBy || 'watsonIncidentId'} />
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
							onChange={this.handleUpdateFilter}
						/>
					</div>

					{((data.size > 0) || loading) &&
						<div class="view-index">
							<IndexList
								data={data}
								filterActive={this.state.filterActive}
								hasMoreResults={data.size < modelCount}
								keysToOmit={keysToOmit}
								loading={loading}
								loadMoreMethod={this.handleLoadMore}
								model={model}
								onClick={action === 'index' ? undefined : this.handleOnClick}
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

		const {hideLoadingOverlay, incidentName, model, updateHideLoadingOverlay} = this.props;

		if (!hideLoadingOverlay) {
			updateHideLoadingOverlay(true);
		}

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, WatsonConstants.inputConfig[model].pluralLabel));
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
		if (newState && newState !== oldState) {
			this.state.itemsLoaded = 0;

			this.refreshData(true);
		}
	}
}

ViewIndex.PROPS = {
	action: Config.string(),
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
	selectedIds: Config.array().value([]),
	sortBy: Config.string().value('watsonIncidentId'),
	watsonIncidentId: Config.value('')
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
		indexIncidents: data => {
			dispatch(
				indexIncidents(data)
			);
		},
		indexPeople: data => {
			dispatch(
				indexPeople(data)
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
		searchIncidents: data => {
			dispatch(
				searchIncidents(data)
			);
		},
		searchPeople: data => {
			dispatch(
				searchPeople(data)
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
		updateFilter: (filterData, watsonIncidentId, model) => {
			const action = {
				filterData,
				model,
				watsonIncidentId
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
		updateSortBy: (sortByData, watsonIncidentId, model) => {
			const action = {
				model,
				sortByData,
				watsonIncidentId
			};

			dispatch(
				updateSortBy(action)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {model = 'incidents', watsonIncidentId = 0} = props;
	const incidentsData = state.getIn(['incidents', 'data']) || new Map();
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const modelLoading = state.getIn([model, 'loading']);

	const loading = (model !== 'incidents') ? modelLoading : incidentsLoading;

	const sortBy = state.getIn(['display', 'sortBy', watsonIncidentId, model]) || WatsonConstants.inputConfig[model].sortByDefault;

	return {
		filter: state.getIn(['display', 'filter', watsonIncidentId, model]),
		hideLoadingOverlay: state.getIn(['display', 'hideLoadingOverlay']),
		incidentsData,
		incidentsLoading,
		loading,
		model,
		modelCount: state.getIn([model, 'count']) || '0',
		modelData: state.getIn([model, 'data']) || new Map(),
		modelLoading,
		sortBy
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ViewIndex);