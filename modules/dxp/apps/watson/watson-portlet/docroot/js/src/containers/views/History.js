import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import sub from 'string-sub';

import ContentHeader from '../../components/ContentHeader';
import HistoryView from '../../components/HistoryView';
import Toggle from '../../components/Toggle';

import {indexHistories} from '../../actions/histories';
import {updateDOMTitle} from '../../lib/util';

class HistoriesView extends JSXComponent {
	attached() {
		const {indexHistories, watsonIncidentId} = this.props;

		if (watsonIncidentId) {
			indexHistories(watsonIncidentId);
		}
	}

	created() {
		bindAll(
			this,
			'handleUpdateCreatedChecked',
			'handleUpdateDeletedChecked',
			'handleUpdateUpdatedChecked'
		);
	}

	handleUpdateCreatedChecked(checked) {
		this.setState({showCreated: checked});
	}

	handleUpdateDeletedChecked(checked) {
		this.setState({showDeleted: checked});
	}

	handleUpdateUpdatedChecked(checked) {
		this.setState({showUpdated: checked});
	}

	render() {
		const {
			headerStringLeft = Liferay.Language.get('history'),
			loading,
			storeData,
			watsonIncidentId
		} = this.props;

		const {
			showCreated,
			showDeleted,
			showUpdated
		} = this.state;

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} />

					<div class="top-bar">
						<Toggle
							checked={showCreated}
							label={['', Liferay.Language.get('created')]}
							onChange={this.handleUpdateCreatedChecked}
						/>
						<Toggle
							checked={showDeleted}
							label={['', Liferay.Language.get('deleted')]}
							onChange={this.handleUpdateDeletedChecked}
						/>
						<Toggle
							checked={showUpdated}
							label={['', Liferay.Language.get('updated')]}
							onChange={this.handleUpdateUpdatedChecked}
						/>
					</div>
				</div>

				<div class="content">
					<div class="history-view-container">
						<HistoryView
							historyData={storeData}
							showCreated={showCreated}
							showDeleted={showDeleted}
							showUpdated={showUpdated}
							watsonIncidentId={watsonIncidentId}
						/>

						{((storeData.size < 1) && !loading) &&
							<div class="no-entries">
								{sub(Liferay.Language.get('no-x-found'), WatsonConstants.inputConfig.histories.pluralLabel)}
							</div>
						}
					</div>
				</div>
			</div>
		);
	}

	rendered() {
		const {incidentName} = this.props;

		updateDOMTitle(sub(Liferay.Language.get('incident-x-x'), incidentName, Liferay.Language.get('history')));
	}
}

HistoriesView.PROPS = {
	action: Config.string().value(''),
	loading: Config.bool().value(false),
	storeData: Config.value(null),
	watsonIncidentId: Config.value('')
};

HistoriesView.STATE = {
	showCreated: Config.bool().value(true),
	showDeleted: Config.bool().value(true),
	showUpdated: Config.bool().value(true)
};

function mapDispatchToProps(dispatch) {
	return {
		indexHistories: id => {
			dispatch(
				indexHistories({id})
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {watsonIncidentId} = props;

	const data = state.getIn(['histories', 'data', watsonIncidentId]) || new Map();

	const storeData = data.get('watsonHistories') || new Map();

	return {
		loading: state.getIn(
			[
				'histories',
				'loading'
			]
		),
		response: state.getIn(
			[
				'histories',
				'response'
			]
		),
		storeData
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(HistoriesView);