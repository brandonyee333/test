import {debounce, noop} from 'lodash';
import JSXComponent from 'metal-jsx';
import {Map} from 'immutable';
import moment from 'moment';
import momentThai from 'moment/locale/th.js'
import {Provider} from 'metal-redux';
import Router from 'metal-router';

import configureStore from './store/configure-store.js';

import {getURLForLanguageId} from './lib/util';
import SidebarToolbar from './components/SidebarToolbar';

import AdminConsole from './containers/AdminConsole';
import CreateIncident from './containers/CreateIncident';
import EditIncident from './containers/EditIncident';
import IncidentReport from './containers/views/IncidentReport';
import ChildModelIndex from './containers/ChildModelIndex';
import Index from './containers/Index';
import MetricsConsole from './containers/MetricsConsole';

class Watson extends JSXComponent {
	attached() {
		Router.router().dispatch();

		Liferay.Watson.debouncedSessionExtend = debounce(this._handleSessionExtend, 3000);

		Router.router().on('endNavigate', Liferay.Watson.debouncedSessionExtend);
	}

	_defaultRoute(route) {
		const {urls = {}} = WatsonConstants;

		for (const key in urls) {
			if (urls.hasOwnProperty(key)) {
				if (key === 'baseURL' || key === 'incidents' || key === 'basePath') {
					continue;
				}

				if (route === urls[key]) {
					return false;
				}
			}
		}

		return !route.startsWith('/c') && !route.startsWith('/group') && !route.startsWith('/documents');
	}

	_handleSessionExtend() {
		if (Liferay.Session) {
			Liferay.Session.extend();
		}
	}

	languageOnClick() {
		window.location.href = getURLForLanguageId(WatsonConstants.otherLanguageId);
	}

	render() {
		let retVal = null;

		const {pathname} = window.location;

		const {currentLanguageId} = WatsonConstants;

		if (pathname.startsWith(`/${currentLanguageId}`)) {
			window.location.href = pathname.substring(currentLanguageId.length + 1);
		}
		else {
			const store = configureStore(Map());

			const thaiIsChecked = WatsonConstants.otherLanguageId !== 'th';

			if (thaiIsChecked) {
				moment.locale('th', momentThai);
			}

			retVal = (
				<Provider store={store}>
					<div class="watson-app">

						<SidebarToolbar
							languageOnClick={this.languageOnClick}
							thaiIsChecked={thaiIsChecked}
						/>

						<div class="print-helper-message" id="print-helper-message">{Liferay.Language.get('this-page-is-not-printable')} </div>

						<Router component={CreateIncident} path={`${WatsonConstants.urls.basePath}/incidents/create`} />
						<Router component={CreateIncident} path={`${WatsonConstants.urls.basePath}/incidents/create/:model([a-zA-Z]+)`} />
						<Router component={IncidentReport} path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/report`} />
						<Router component={EditIncident} path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/:action([a-zA-Z]+)`} />
						<Router component={EditIncident} path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-zA-Z]+)`} />
						<Router component={EditIncident} path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-zA-Z]+)/:action([a-zA-Z]+)`} />
						<Router component={IncidentReport} path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-zA-Z]+)/:entryId(\\d+)/report`} />
						<Router component={ChildModelIndex} path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-zA-Z]+)/:entryId(\\d+)/view`} />
						<Router component={EditIncident} path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-zA-Z]+)/:entryId(\\d+)/:action([a-zA-Z]+)`} />
						<Router component={AdminConsole} path={`${WatsonConstants.urls.basePath}/incidents/admin`} />
						<Router component={AdminConsole} path={`${WatsonConstants.urls.basePath}/incidents/admin/:action([a-zA-Z]+)`} />
						<Router component={MetricsConsole} path={`${WatsonConstants.urls.basePath}/incidents/metrics`} />
						<Router component={MetricsConsole} path={`${WatsonConstants.urls.basePath}/incidents/metrics/:action([a-zA-Z]+)`} />
						<Router component={Index} path={`${WatsonConstants.urls.basePath}/:model([a-zA-Z]+)`} />
						<Router component={Index} path={this._defaultRoute} />
					</div>
				</Provider>
			);
		}

		return retVal;
	}
}

Liferay.Watson = {
	initialize() {
		if (!this.initialized) {
			new Watson();

			this.initialized = true;

			Liferay.on(
				'sessionExpired',
				() => {
					window.location = WatsonConstants.urls.sessionExpired;
				}
			);
		}
	},
	debouncedSessionExtend: noop,
	mapComponent: {},
	router: null
};