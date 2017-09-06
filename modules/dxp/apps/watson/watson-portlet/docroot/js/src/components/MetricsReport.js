import {bindAll, isArray} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import Table from 'metal-datatable';
import Tabs from 'metal-tabs';

import ContentHeader from './ContentHeader';

class MetricsReport extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleUpdateView'
		);
	}

	handleUpdateView(event) {
		console.log(event);
	}

	render() {
		const {data, loading} = this.props;

		const {tabs} = this.state;

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={Liferay.Language.get('report')} />
				</div>

				<div class="content" id="table">
					<Tabs
						onClickItem={this.handleUpdateView}
						tabs={tabs}
						types="pills"
					/>

					{!loading &&
						<Table
							data={isArray(data) ? data : [{loading: Liferay.Language.get('loading')}]} elementClasses="table-wrapper"
						/>
					}

					{loading &&
						<span>{Liferay.Language.get('loading')}</span>
					}
				</div>
			</div>
		);
	}
}

MetricsReport.PROPS = {
	data: Config.array(),
	loading: Config.bool().value(false)
};

MetricsReport.STATE = {
	tabs: Config.array().value(
		[
			{
				label: Liferay.Language.get('rescued')
			},
			{
				label: Liferay.Language.get('accepted-to-zoe')
			},
			{
				label: Liferay.Language.get('active')
			}
		]
	)
};

export default MetricsReport;