import Alert from 'marble-alert';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import moment from 'moment';
import sub from 'string-sub';

import Button from '../components/Button';
import ButtonModal from '../components/ButtonModal';
import Input from '../components/Input';
import LoadingIndicator from '../components/LoadingIndicator';

import {checkUserAuthorizationStatus, submitAuthenticationToken} from '../actions/authentication';

const authStates = {
	approved: Liferay.Language.get('approved'),
	expired: Liferay.Language.get('expired'),
	invalid: Liferay.Language.get('invalid'),
	pending: Liferay.Language.get('pending')
};

class UserAuthentication extends JSXComponent {
	attached() {
		this.props.checkUserAuthorizationStatus();
	}

	created() {
		bindAll(
			this,
			'_handleNewTokenRequest',
			'_handleOnChange',
			'_handleSubmit'
		);
	}

	_handleNewTokenRequest() {
		this.props.checkUserAuthorizationStatus(true);

		this.setState({emailSentTime: Date.now()});
	}

	_handleSubmit() {
		const {props, state} = this;

		const {submitAuthenticationToken} = props;

		if (submitAuthenticationToken) {
			const postData = {};

			postData.token = state.value;

			submitAuthenticationToken(postData);
		}
	}

	_handleOnChange(value) {
		this.setState({value});
	}

	render() {
		const {authenticationStatus} = this.props;

		const {approved, emailSentTime, value} = this.state;

		const sentMoment = moment(parseInt(emailSentTime, 10));

		let alertContent = '';

		if (authenticationStatus === 'pending') {
			alertContent = emailSentTime ? sub(Liferay.Language.get('an-authorization-code-was-successfully-sent-x'), sentMoment.fromNow(false)) : Liferay.Language.get('an-authorization-code-was-successfully-sent');
		}

		const modal = {
			body: Liferay.Language.get('are-you-sure-that-you-would-like-to-request-a-new-authorization-code-the-old-code-will-be-invalidated')
		};

		const sendNewEmailButtons = [];

		sendNewEmailButtons.push(
			{
				label: Liferay.Language.get('send-new-authorization-code')
			}
		);

		const errorMessage = authenticationStatus !== 'pending' ? authStates[authenticationStatus] : '';

		const cssClass = errorMessage ? 'error' : '';

		if (authenticationStatus === 'approved' && !approved) {
			this.props.onStateChange();

			this.setState({approved: true});
		}

		return (
			<div class="page-container landing-page">
				<div class="welcome-landing-text">
					{sub(Liferay.Language.get('welcome-to-watson-x'), Liferay.ThemeDisplay.getUserName())}
				</div>

				<LoadingIndicator />

				<div class="authentication-content">
					<Alert
						body={alertContent}
						dismissible={false}
						elementClasses="alert-success"
						key="alert-success"
						visible={!!alertContent}
					/>

					<span class="login-label">{Liferay.Language.get('to-complete-the-login-process-we-have-sent-an-authorization-code')}</span>

					<span class="input-container">
						<Input class={`watson-input ${cssClass}`} onChange={this._handleOnChange} value={value} />

						<Button label={Liferay.Language.get('submit')} onclick={this._handleSubmit} />
					</span>

					<span class="alert-container">
						<Alert
							body={errorMessage}
							dismissible={false}
							elementClasses="alert-danger"
							key="alert-error"
							visible={!!errorMessage}
						/>
					</span>

					<span class="small-label-container">
						<span class="login-small-label">{Liferay.Language.get('this-code-will-expire-in-30-minutes')}</span>

						<ButtonModal action={this._handleNewTokenRequest} buttons={sendNewEmailButtons} modalData={modal} />
					</span>
				</div>
			</div>
		);
	}
}

UserAuthentication.PROPS = {
	authenticationLoading: Config.bool(),
	authenticationStatus: Config.any(),
	onStateChange: Config.func()
};

UserAuthentication.STATE = {
	approved: Config.bool(),
	emailSentTime: Config.number(),
	value: Config.any()
};

function mapDispatchToProps(dispatch) {
	return {
		checkUserAuthorizationStatus: force => {
			dispatch(
				checkUserAuthorizationStatus({force})
			);
		},
		submitAuthenticationToken: data => {
			dispatch(
				submitAuthenticationToken(data)
			);
		}
	};
}

function mapStateToProps(state) {
	const authenticationLoading = state.getIn(['authentication', 'loading']) || false;
	const authenticationStatus = state.getIn(['authentication', 'status']);

	return {
		authenticationLoading,
		authenticationStatus
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(UserAuthentication);