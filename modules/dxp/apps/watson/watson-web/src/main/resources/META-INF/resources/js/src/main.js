import {bindAll, noop} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import configureStore from './store/configure-store.js';

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

		const store = configureStore(new Map());

		const {pathname} = window.location;

		const {currentLanguageId} = WatsonConstants;

		if (pathname.startsWith(`/${currentLanguageId}`)) {
			window.location.href = pathname.substring(currentLanguageId.length + 1);
		}
		else if (authenticated) {
			retVal = (
				<Provider store={store}>
					<Watson key="watson-main" />
				</Provider>
			);
		}
		else {
			retVal = (
				<Provider store={store}>
					<div class="watson-app">

						<div class="print-helper-message" id="print-helper-message">{Liferay.Language.get('this-page-is-not-printable')} </div>

						<UserAuthentication key="user-auth" onStateChange={this._handleUpdateState} />
					</div>
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
		}
	},
	debouncedSessionExtend: noop,
	mapComponent: {}
};