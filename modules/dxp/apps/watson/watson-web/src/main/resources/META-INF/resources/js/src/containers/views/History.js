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
		const {indexHistories, watsonParentId, watsonParentModel} = this.props;

		if (watsonParentId) {
			indexHistories(
				{
					model: watsonParentModel,
					watsonParentId
				}
			);
		}
	}

	created() {
		bindAll(
			this,
			'_handleUpdateCreatedChecked',
			'_handleUpdateDeletedChecked',
			'_handleUpdateUpdatedChecked'
		);
	}

	_handleUpdateCreatedChecked(checked) {
		this.setState({showCreated: checked});
	}

	_handleUpdateDeletedChecked(checked) {
		this.setState({showDeleted: checked});
	}

	_handleUpdateUpdatedChecked(checked) {
		this.setState({showUpdated: checked});
	}

	render() {
		const {
			headerStringLeft = Liferay.Language.get('history'),
			loading,
			storeData,
			watsonParentId,
			watsonParentModel
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
							onChange={this._handleUpdateCreatedChecked}
						/>
						<Toggle
							checked={showDeleted}
							label={['', Liferay.Language.get('deleted')]}
							onChange={this._handleUpdateDeletedChecked}
						/>
						<Toggle
							checked={showUpdated}
							label={['', Liferay.Language.get('updated')]}
							onChange={this._handleUpdateUpdatedChecked}
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
							watsonParentId={watsonParentId}
							watsonParentModel={watsonParentModel}
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
		const {primaryName, watsonParentModel} = this.props;

		updateDOMTitle(`${WatsonConstants.inputConfig[watsonParentModel].singularLabel} ${primaryName}, ${Liferay.Language.get('history')}`);
	}
}

HistoriesView.PROPS = {
	action: Config.string().value(''),
	loading: Config.bool().value(false),
	primaryName: Config.string().value(''),
	storeData: Config.value(null),
	watsonParentId: Config.value(''),
	watsonParentModel: Config.string()
};

HistoriesView.STATE = {
	showCreated: Config.bool().value(true),
	showDeleted: Config.bool().value(true),
	showUpdated: Config.bool().value(true)
};

function mapDispatchToProps(dispatch) {
	return {
		indexHistories: data => {
			dispatch(
				indexHistories(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {watsonParentId} = props;

	const data = state.getIn(['histories', 'data', watsonParentId]) || new Map();

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