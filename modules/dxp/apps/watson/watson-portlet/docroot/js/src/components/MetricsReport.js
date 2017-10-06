import {bindAll} from 'lodash';
import Charts from 'metal-charts';
import JSXComponent, {Config} from 'metal-jsx';
import Tabs from 'metal-tabs';

import ContentHeader from './ContentHeader';

class MetricsReport extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleUpdateView'
		);
	}

	handleUpdateView({target}) {
		const tabClicked = target.getAttribute('ref').toString();

		const tabIndex = tabClicked[tabClicked.length - 1];

		const tabs = WatsonConstants.inputConfig.metrics.reports.types;

		const {key, modelKey} = tabs[tabIndex];

		this.props.onChange(key, modelKey);
	}

	render() {
		const {data = {}, loading} = this.props;

		const onClickEvent = {
			click: this.handleUpdateView
		};

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={Liferay.Language.get('report')} />
				</div>

				<div class="content" id="table">
					<div class="tabs-wrapper">
						<Tabs
							events={onClickEvent}
							tabs={WatsonConstants.inputConfig.metrics.reports.types}
							types="pills"
						/>
					</div>

					{(!loading && data.key) &&
						<Charts
							columns={[
								{
									data: data.values,
									id: data[data.key],
									name: data[data.key],
									type: 'line'
								}
							]}
							elementClasses="table-wrapper"
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
	loading: Config.bool().value(true),
	onChange: Config.func()
};

MetricsReport.STATE = {
	axis: Config.object().value(
		{
			x: {
				tick: {
					format: '%Y'
				},
				type: 'timeseries'
			}
		}
	)
};

export default MetricsReport;