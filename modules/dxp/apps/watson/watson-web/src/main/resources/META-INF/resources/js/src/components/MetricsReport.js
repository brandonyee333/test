import BootstrapTable from 'react-bootstrap-table-next';
import bridge from 'metal-react';
import {forEach, isEmpty} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import ContentHeader from './ContentHeader';

const Datatable = bridge(BootstrapTable);

class MetricsReport extends JSXComponent {
	_handleRowClasses = row => {
		let classes = null;

		if (row.child === true) {
			classes = 'row-padding';
		}

		return classes;
	};

	render() {
		const {columns} = this.state;

		const {data = {}, loading} = this.props;

		const formattedData = [];

		if (!loading && !Map.isMap(data) && !isEmpty(data)) {
			const oneYearData = data.value;

			const {rows} = WatsonConstants.inputConfig.metrics.reports;

			rows.forEach(
				item => {
					if (item) {
						const {child, key, label} = item;

						const formattedObject = {};

						formattedObject.child = child;
						formattedObject.metric = label;
						formattedObject.number = oneYearData[key];

						formattedData.push(formattedObject);
					}
				}
			);
		}

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={Liferay.Language.get('report')} />
				</div>

				<div class="content" id="table">
					<Datatable
						columns={columns}
						data={formattedData}
						hover={true}
						keyField="metric"
						noDataIndication={Liferay.Language.get('no-metrics-data-was-found')}
						rowClasses={this._handleRowClasses}
					/>
				</div>
			</div>
		);
	}
}

MetricsReport.PROPS = {
	data: Config.object(),
	loading: Config.bool().value(true)
};

MetricsReport.STATE = {
	columns: Config.array().value(
		[
			{
				dataField: 'metric',
				text: Liferay.Language.get('metric')
			},
			{
				dataField: 'number',
				text: Liferay.Language.get('number')
			}
		]
	)
};

export default MetricsReport;