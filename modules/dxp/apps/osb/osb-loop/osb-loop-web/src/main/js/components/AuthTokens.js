import Component, {Config} from 'metal-jsx';
import moment from 'moment';
import {connect} from 'metal-redux';
import {isNumber} from 'lodash';

import Button from './Button';
import {addAlert, alertTypes} from '../actions/alerts';
import {lang} from '../lib/lang-util';
import {modalTypes, showModal} from '../actions/modals';

class AuthTokens extends Component {
	created() {
		Liferay.Service(
			'/tokenauth.tokenauthentry/get-token-auth-entries',
			{
				end: -1,
				start: -1
			},
			result => {
				const {filterId} = this.props;

				if (isNumber(filterId)) {
					result = result.filter(
						item => item.userId === filterId
					);
				}

				this.state.tokens_ = result;
			}
		);
	}

	deleteToken_(tokenAuthEntryId) {
		return () => {
			Liferay.Service(
				'/tokenauth.tokenauthentry/delete-token-auth-entry',
				{tokenAuthEntryId},
				result => {
					this.state.tokens_ = this.state.tokens_.filter(
						token => token.tokenAuthEntryId !== tokenAuthEntryId
					);

					this.props.addAlert(
						{
							alertType: alertTypes.SUCCESS,
							message: Liferay.Language.get('token-successfully-removed')
						}
					);
				}
			);
		};
	}

	handleRemove_(tokenAuthEntryId, name) {
		return event => {
			event.preventDefault();

			this.props.showModal(
				{
					hideOnBlur: false,
					modalProps: {
						message: lang(Liferay.Language.get('are-you-sure-you-want-to-remove-x'), [`'${name}'`]),
						onConfirm: this.deleteToken_(tokenAuthEntryId)
					},
					modalType: modalTypes.CONFIRM_DIALOG
				}
			);
		};
	}

	render() {
		const {tokens_} = this.state;

		return (
			<div class="auth-tokens-container">
				{!!tokens_.length &&
					tokens_.map(
						({device, loginDate, tokenAuthEntryId, userName}) => (
							<div class="device" key={tokenAuthEntryId}>
								<Button onClick={this.handleRemove_(tokenAuthEntryId, device)} role="primary">{Liferay.Language.get('remove')}</Button>

								{`${userName}: ${device} - `}

								<span>{moment(loginDate).format('LLL')}</span>
							</div>
						)
					)
				}

				{!tokens_.length &&
					Liferay.Language.get('no-devices')
				}
			</div>
		);
	}
}

AuthTokens.PROPS = {
	filterId: Config.number()
};

AuthTokens.STATE = {
	tokens_: Config.array().value([])
};

export default connect(
	null,
	{
		addAlert,
		showModal
	}
)(AuthTokens);