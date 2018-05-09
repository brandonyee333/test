import {bindAll, noop} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import configureStore from './store/configure-store';
import configureTemporaryStore from './store/store-prod';

import UserAuthentication from './containers/UserAuthentication';
import Watson from './App';

class Main extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleUpdateState'
		);
	}

	_handleUpdateState() {
		this.setState({authenticated: true});
	}

	render() {
		const {authenticated} = this.state;

		let retVal = null;

		const {pathname} = window.location;

		const {currentLanguageId} = WatsonConstants;

		if (pathname.startsWith(`/${currentLanguageId}`)) {
			window.location.href = pathname.substring(currentLanguageId.length + 1);
		}
		else if (authenticated) {
			retVal = (
				<Provider store={configureStore(new Map())}>
					<Watson key="watson-main" />
				</Provider>
			);
		}
		else {
			retVal = (
				<Provider store={configureTemporaryStore(new Map())}>
					<UserAuthentication key="user-auth" onStateChange={this._handleUpdateState} />
				</Provider>
			);
		}

		return retVal;
	}
}

Main.STATE = {
	authenticated: Config.bool().value(false)
};

Liferay.Watson = {
	initialize() {
		if (!this.initialized) {
			new Main();

			this.initialized = true;

			Liferay.on(
				'sessionExpired',
				() => {
					Liferay.Watson.invalidateAuthToken();

					window.location = WatsonConstants.urls.sessionExpired;
				}
			);
		}
	},
	debouncedSessionExtend: noop,
	invalidateAuthToken: noop,
	mapComponent: {}
};