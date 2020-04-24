import Component, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Alert from './Alert';
import {removeAlert} from '../actions/alerts';

class AlertFeed extends Component {
	renderAlert_(alertIMap) {
		return (
			<Alert alertIMap={alertIMap} key={alertIMap.get('id')} onRemoveAlert={this.props.removeAlert} />
		);
	}

	render() {
		const {alertsIMap, modalActive} = this.props;

		return (
			<div class={`alert-feed-container${modalActive ? ' modal-active' : ''}`}>
				<Transition name="transition-slide-up">
					{alertsIMap.map(alertIMap => this.renderAlert_(alertIMap))}
				</Transition>
			</div>
		);
	}
}

const STORE = {
	alertsIMap: Config.instanceOf(Map),
	modalActive: Config.bool().value(false),
	removeAlert: Config.func()
};

AlertFeed.PROPS = {
	...STORE
};

export default connect(
	state => (
		{
			alertsIMap: state.get('alerts'),
			modalActive: state.getIn(['modals', 'open'], false)
		}
	),
	{
		removeAlert
	}
)(AlertFeed);