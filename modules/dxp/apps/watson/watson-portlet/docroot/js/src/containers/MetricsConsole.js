import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import DynamicSelectInput from '../components/DynamicInputGenerator';
import HeatMap from '../components/HeatMap';
import SidebarHeader from '../components/SidebarHeader';

import {fetchIncidentMetrics} from '../actions/incidents';
import {updateFilter} from '../actions/display';

class MetricsConsole extends JSXComponent {
	attached() {
		this.props.fetchIncidentMetrics();
	}

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
			'handleUpdateFilter',
			'handleUpdateViewBy'
		);
	}

	disposed() {
		this.clearHeatmap();
	}

	getCurrentView(action, data, loading, viewBy) {
		let currentView = (
			<HeatMap
				data={data}
				loading={loading}
				updateViewBy={this.handleUpdateViewBy}
				viewBy={viewBy}
			/>
		);

		if (action != 'heatmap') {
			currentView = <span class="text">{Liferay.Language.get('please-select-a-method-on-the-left')}</span>;
		}

		return currentView;
	}

	handleUpdateFilter(filterData) {
		const {updateFilter} = this.props;

		updateFilter(filterData, 1, 'incidents');

		this.state.resendRequest = true;
	}

	handleUpdateViewBy(viewBy) {
		const {state} = this;

		if (state.viewBy != viewBy) {
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

		const {action} = props.router.params;

		const {incidentsFilter, incidentsMetricsData, loading} = props;

		const {viewBy} = state;

		return (
			<div class="page-container incidents-metrics no-print">
				<div class="sidebar">
					<SidebarHeader mainHeader={Liferay.Language.get('watson-metrics')} />

					<div class="filter-header">
						{Liferay.Language.get('filter-by')}
					</div>

					<DynamicSelectInput
						elementClasses="filter-input"
						filter={incidentsFilter}
						inputConfig={WatsonConstants.inputConfig.heatmaps.inputs}
						label={Liferay.Language.get('add-filter')}
						onChange={this.handleUpdateFilter}
					/>
				</div>

				{this.getCurrentView(action, incidentsMetricsData, loading, viewBy)}
			</div>
		);
	}

	rendered() {
		const {props, state} = this;

		if (state.resendRequest) {
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
					fields: fieldsArray,
					keywords: keywordsArray,
					type: state.viewBy
				}
			);

			this.clearHeatmap();

			this.state.resendRequest = false;
		}
	}
}

MetricsConsole.PROPS = {
	incidentsFilter: Config.value(new Map())
};

MetricsConsole.STATE = {
	resendRequest: Config.bool(),
	viewBy: Config.string().value('9361')
};

function mapDispatchToProps(dispatch) {
	return {
		fetchIncidentMetrics: data => {
			dispatch(
				fetchIncidentMetrics(data)
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
		}
	};
}

function mapStateToProps(state) {
	const incidentsFilter = state.getIn(['display', 'filter', 1, 'incidents']) || new Map();
	const incidentsLoading = state.getIn(['incidents', 'loading']) || false;
	const incidentsMetricsData = state.getIn(['incidents', 'metricsData']) || new Map();

	return {
		incidentsFilter,
		incidentsMetricsData,
		loading: incidentsLoading
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(MetricsConsole);