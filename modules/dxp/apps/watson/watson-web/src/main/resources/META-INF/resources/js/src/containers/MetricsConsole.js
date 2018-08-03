import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import DateRangeInput from '../components/DateRangeInput';
import DynamicSelectInput from '../components/DynamicInputGenerator';
import HeatMap from '../components/HeatMap';
import MetricsReport from '../components/MetricsReport';
import Navigation from '../components/Navigation';
import NavigationHeader from '../components/NavigationHeader';

import {fetchIncidentMetrics, updateIncidentsDataManually} from '../actions/incidents';
import {updateFilter, updatePageTitle} from '../actions/display';

class MetricsConsole extends JSXComponent {
	clearHeatmap() {
		if (Liferay.Watson.mapComponent.heatMap) {
			Liferay.Watson.mapComponent.heatMap.setMap(null);
		}

		if (Liferay.Watson.mapComponent.markers) {
			Liferay.Watson.mapComponent.markers.clearMarkers();
		}
	}

	created() {
		bindAll(
			this,
			'_handleOnChange',
			'_handleUpdateFilter',
			'_handleUpdateViewBy'
		);

		this.props.updatePageTitle('metrics');
	}

	detached() {
		this.clearHeatmap();

		this.props.updateIncidentsDataManually();

		this.props.updateFilter(new Map(), 'incidents', 1);
	}

	getCurrentView(action, data, loading, viewBy) {
		let currentView = <span class="text">{Liferay.Language.get('please-select-a-method-on-the-left')}</span>;

		if (action === 'heatmap') {
			currentView = (
				<HeatMap
					data={data}
					loading={loading}
					updateViewBy={this._handleUpdateViewBy}
					viewBy={viewBy}
				/>
			);
		}
		else if (action === 'report') {
			const {dateRangeString} = this.state;

			const contentHeader = !dateRangeString ? Liferay.Language.get('report-year-to-date') : Liferay.Language.get('report-by-date-range');

			currentView = (
				<MetricsReport
					contentHeader={contentHeader}
					data={data}
					loading={loading}
					onChange={this._handleOnChange}
				/>
			);
		}

		return currentView;
	}

	_handleOnChange(dateRangeString) {
		if (this.state.dateRangeString !== dateRangeString) {
			this.setState(
				{
					dateRangeString,
					resendRequest: true
				}
			);
		}
	}

	_handleUpdateFilter(filterData) {
		const {updateFilter} = this.props;

		updateFilter(filterData, 'incidents', 1);

		this.state.resendRequest = true;
	}

	_handleUpdateViewBy(viewBy) {
		const {state} = this;

		if (state.viewBy !== viewBy) {
			this.setState(
				{
					resendRequest: true,
					viewBy
				}
			);
		}
	}

	render() {
		const {props, state} = this;

		const {action, incidentsFilter, incidentsLoading, incidentsMetricsData} = props;

		const {viewBy} = state;

		const consoleNav = [
			{
				collapsible: false,
				href: `${WatsonConstants.urls.baseURL}/incidents/metrics/heatmap`,
				selected: action === 'heatmap',
				text: Liferay.Language.get('heatmap')
			},
			{
				collapsible: false,
				href: `${WatsonConstants.urls.baseURL}/incidents/metrics/report`,
				selected: action === 'report',
				text: Liferay.Language.get('report')
			}
		];

		return (
			<div class="page-container incidents-metrics hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader mainHeader={Liferay.Language.get('watson-metrics')} />

					{action === 'heatmap' &&
						<div class="filter-header">
							{Liferay.Language.get('filter-by')}
						</div> &&

						<DynamicSelectInput
							elementClasses="filter-input"
							filter={incidentsFilter}
							inputConfig={WatsonConstants.inputConfig.metrics.heatmaps.inputs}
							label={Liferay.Language.get('add-filter')}
							onChange={this._handleUpdateFilter}
						/>
					}

					{action === 'report' &&
						<div class="filter-header">
							{Liferay.Language.get('filter-by')}
						</div> &&

						<DateRangeInput
							disabled={false}
							onChange={this._handleOnChange}
						/>
					}

					{!action &&
						<Navigation entries={consoleNav} />
					}
				</div>

				<div class="metrics-content">
					{this.getCurrentView(action, incidentsMetricsData, incidentsLoading, viewBy)}
				</div>
			</div>
		);
	}

	rendered() {
		const {props, state} = this;

		const {action} = props;

		if (state.resendRequest && action) {
			const fieldsArray = [];
			const keywordsArray = [];

			props.incidentsFilter.forEach(
				data => {
					const key = data[0];
					const value = data[1];

					if (key && value) {
						fieldsArray.push(key);
						keywordsArray.push(value);
					}
				}
			);

			props.fetchIncidentMetrics(
				{
					actionType: action,
					dateRangeString: state.dateRangeString,
					fields: fieldsArray,
					keywords: keywordsArray,
					type: state.viewBy
				}
			);

			this.clearHeatmap();

			this.state.actionDisplayed = action;
			this.state.resendRequest = false;
		}

		if (action && action !== state.actionDisplayed) {
			this.state.actionDisplayed = action;

			this.state.resendRequest = true;
		}
	}
}

MetricsConsole.PROPS = {
	action: Config.string(),
	incidentsFilter: Config.value(new Map()),
	incidentsLoading: Config.bool(),
	incidentsMetricsData: Config.any()
};

MetricsConsole.STATE = {
	actionDisplayed: Config.string().value(''),
	dateRangeString: Config.string().value(''),
	resendRequest: Config.bool().value(true),
	viewBy: Config.string().value('9361')
};

function mapDispatchToProps(dispatch) {
	return {
		fetchIncidentMetrics: data => {
			dispatch(
				fetchIncidentMetrics(data)
			);
		},
		updateFilter: (filterData, model, watsonIncidentId) => {
			const action = {
				filterData,
				model,
				watsonParentPrimaryKey: watsonIncidentId
			};

			dispatch(
				updateFilter(action)
			);
		},
		updateIncidentsDataManually: () => {
			dispatch(
				updateIncidentsDataManually({metricsData: []})
			);
		},
		updatePageTitle: data => {
			dispatch(
				updatePageTitle(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {action} = props.router.params;

	const incidentsFilter = state.getIn(['display', 'filter', 1, 'incidents']) || new Map();
	const incidentsLoading = state.getIn(['incidents', 'loading']) || false;
	const incidentsMetricsData = state.getIn(['incidents', 'metricsData']) || new Map();

	return {
		action,
		incidentsFilter,
		incidentsLoading,
		incidentsMetricsData
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(MetricsConsole);