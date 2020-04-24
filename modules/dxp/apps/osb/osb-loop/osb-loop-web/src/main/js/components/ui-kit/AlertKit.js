import Component from 'metal-jsx';
import {fromJS} from 'immutable';

import Alert from '../Alert';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import {alertTypes} from '../../actions/alerts';

const ITEMS = [
	{
		alertType: alertTypes.ALERT,
		message: 'Version 2.0 is available! Refresh the page.'
	},
	{
		alertType: alertTypes.DEFAULT,
		message: 'You are using the offline Mode.'
	},
	{
		alertType: alertTypes.ERROR,
		message: 'UploadFailed! Check your internet connection.'
	},
	{
		alertType: alertTypes.PENDING,
		message: 'Updating your profile picture...'
	},
	{
		alertType: alertTypes.SUCCESS,
		message: 'Congratulations! You are now logged in.'
	}
];

class AlertKit extends Component {
	created() {
		this.handleRemoveClick_ = this.handleRemoveClick_.bind(this);
	}

	handleRemoveClick_() {
		alert('You clicked dismiss.');
	}

	render() {
		const style = {
			'max-width': '380px'
		};

		return (
			<Kit card={false} header="Alert">
				{
					ITEMS.map(
						(alert, i) => (
							<ElementContainer header={alert.alertType} key={i} style={style}>
								<Alert alertIMap={fromJS(alert)} onRemoveAlert={this.handleRemoveClick_} />
							</ElementContainer>
						)
					)
				}
			</Kit>
		);
	}
}

export default AlertKit;