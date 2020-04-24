import Component from 'metal-jsx';
import {bindAll} from 'lodash';
import {createStore} from 'redux';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import AlertFeed from '../AlertFeed';
import Button from '../Button';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import middleware from '../../store/configure-middleware';
import reducers from '../../reducers';
import {addAlert, alertTypes, updateAlert} from '../../actions/alerts';

const TIMEOUT = 2000;

class AlertFeedKit extends Component {
	created() {
		bindAll(
			this,
			'addAlert_',
			'addDefault_',
			'addError_',
			'addPending_',
			'addSuccess_'
		);

		this.store = createStore(reducers, Map(), middleware);
	}

	addAlert_() {
		this.store.dispatch(
			addAlert(
				{
					alertType: alertTypes.ALERT,
					message: 'Version 2.0 is available! Refresh the page.',
					timeout: TIMEOUT
				}
			)
		);
	}

	addDefault_() {
		this.store.dispatch(
			addAlert(
				{
					alertType: alertTypes.DEFAULT,
					message: 'You are using the offline Mode.'
				}
			)
		);
	}

	addError_() {
		this.store.dispatch(
			addAlert(
				{
					alertType: alertTypes.ERROR,
					message: 'UploadFailed! Check your internet connection.',
					timeout: true
				}
			)
		);
	}

	addPending_() {
		const pendingAlert = this.store.dispatch(
			addAlert(
				{
					alertType: alertTypes.PENDING,
					message: 'Updating your profile picture...'
				}
			)
		);

		const completedAlert = () => {
			this.store.dispatch(
				updateAlert(
					{
						alertType: alertTypes.SUCCESS,
						id: pendingAlert.id,
						message: 'Your profile picture has been updated',
						timeout: TIMEOUT
					}
				)
			);
		};

		setTimeout(
			completedAlert,
			TIMEOUT
		);
	}

	addSuccess_() {
		this.store.dispatch(
			addAlert(
				{
					alertType: alertTypes.SUCCESS,
					message: 'Congratulations! You are now logged in.',
					timeout: true
				}
			)
		);
	}

	render() {
		return (
			<Kit card={false} header="AlertFeed">
				<Provider store={this.store}>
					<ElementContainer header="Create Alerts">
						<ElementDisplay description="Create Alert Alert with a 2 second timeout">
							<Button display="primary" onClick={this.addAlert_}>{'Create Alert'}</Button>
						</ElementDisplay>

						<ElementDisplay description="Create Default Alert with no timeout">
							<Button display="primary" onClick={this.addDefault_}>{'Create Default'}</Button>
						</ElementDisplay>

						<ElementDisplay description="Create Error Alert with a default timeout">
							<Button display="primary" onClick={this.addError_}>{'Create Error'}</Button>
						</ElementDisplay>

						<ElementDisplay description="Create Pending Alert with a 2 second timeout after completion">
							<Button display="primary" onClick={this.addPending_}>{'Create Pending'}</Button>
						</ElementDisplay>

						<ElementDisplay description="Create Success Alert with a default second timeout">
							<Button display="primary" onClick={this.addSuccess_}>{'Create Success'}</Button>
						</ElementDisplay>
					</ElementContainer>

					<AlertFeed />
				</Provider>
			</Kit>
		);
	}
}

export default AlertFeedKit;