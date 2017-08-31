import {isArray} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import Table from 'metal-datatable';

import ContentHeader from './ContentHeader';

class MetricsReport extends JSXComponent {
	render() {
		const {data} = this.props;

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={Liferay.Language.get('report')} />
				</div>

				<div class="content" id="table">
					<Table
						data={isArray(data) ? data : [{loading: Liferay.Language.get('loading')}]}
						elementClasses="table-wrapper"
					/>
				</div>
			</div>
		);
	}
}

MetricsReport.PROPS = {
	data: Config.array()
};

export default MetricsReport;