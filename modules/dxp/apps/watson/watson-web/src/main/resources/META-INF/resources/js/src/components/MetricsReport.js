import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import bridge from 'metal-react';
import {isEmpty} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import ContentHeader from './ContentHeader';

const Datatable = bridge(BootstrapTable);

class MetricsReport extends JSXComponent {
	render() {
		const {data = {}, loading} = this.props;

		const columns = WatsonConstants.inputConfig.metrics.reports.columns;

		const tableHeaderColumns = [];

		if (!loading && !isEmpty(columns)) {
			columns.forEach(
				(item, index) => {
					if (item) {
						const {key, label} = item;

						tableHeaderColumns.push(
							<TableHeaderColumn dataField={key} isKey={index === 0}>{label}</TableHeaderColumn>
						);
					}
				}
			);
		}

		const formattedData = [];

		if (!loading && !isEmpty(data)) {
			for (const key in data) {
				if (data.hasOwnProperty(key)) {
					const formattedObject = Object.assign({}, data[key]);

					formattedObject.id = key;

					formattedData.push(formattedObject);
				}
			}
		}

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={Liferay.Language.get('report')} />
				</div>

				<div class="content" id="table">

					<Datatable data={formattedData.reverse()}>
						{tableHeaderColumns}
					</Datatable>

					{loading &&
						<span class="input-text">{Liferay.Language.get('loading')}</span>
					}
				</div>
			</div>
		);
	}
}

MetricsReport.PROPS = {
	data: Config.array().value([]),
	loading: Config.bool().value(true)
};

MetricsReport.STATE = {};

export default MetricsReport;