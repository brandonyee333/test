import {debounce, noop} from 'lodash';
import JSXComponent from 'metal-jsx';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';
import Router from 'metal-router';

import configureStore from './store/configure-store.js';

import SidebarToolbar from './components/SidebarToolbar';

import AdminConsole from './containers/AdminConsole';
import CreateChild from './containers/CreateChild';
import ChildModelImport from './containers/ChildModelImport';
import CreateIncident from './containers/CreateIncident';
import ChildReport from './containers/views/ChildReport';
import EditChild from './containers/EditChild';
import EditIncident from './containers/EditIncident';
import IncidentModelSubIndex from './containers/IncidentModelSubIndex';
import IncidentReport from './containers/views/IncidentReport';
import Index from './containers/Index';
import LandingPage from './containers/LandingPage';
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
				if (key === 'baseURL' || key === 'basePath') {
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

	render() {
		let retVal = null;

		const {pathname} = window.location;

		const {currentLanguageId} = WatsonConstants;

		if (pathname.startsWith(`/${currentLanguageId}`)) {
			window.location.href = pathname.substring(currentLanguageId.length + 1);
		}
		else {
			const store = configureStore(Map());

			retVal = (
				<Provider store={store}>
					<div class="watson-app">

						<SidebarToolbar />

						<div class="print-helper-message" id="print-helper-message">{Liferay.Language.get('this-page-is-not-printable')} </div>

						<Router
							component={CreateIncident}
							path={`${WatsonConstants.urls.basePath}/incidents/create`}
						/>

						<Router
							component={CreateChild}
							path={`${WatsonConstants.urls.basePath}/children/create`}
						/>

						<Router
							component={ChildModelImport}
							path={`${WatsonConstants.urls.basePath}/children/import`}
						/>

						<Router
							component={CreateIncident}
							path={`${WatsonConstants.urls.basePath}/incidents/create/:model([a-z_A-Z]+)`}
						/>

						<Router
							component={IncidentReport}
							path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/report`}
						/>

						<Router
							component={ChildReport}
							path={`${WatsonConstants.urls.basePath}/children/:watsonChildId(\\d+)/report`}
						/>

						<Router
							component={EditChild}
							path={`${WatsonConstants.urls.basePath}/children/:watsonChildId(\\d+)/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={EditIncident}
							path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={EditIncident}
							path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-z_A-Z]+)`}
						/>

						<Router
							component={EditChild}
							path={`${WatsonConstants.urls.basePath}/children/:watsonChildId(\\d+)/edit/:model([a-z_A-Z]+)`}
						/>

						<Router
							component={EditIncident}
							path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-z_A-Z]+)/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={EditChild}
							path={`${WatsonConstants.urls.basePath}/children/:watsonChildId(\\d+)/edit/:model([a-z_A-Z]+)/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={ChildReport}
							path={`${WatsonConstants.urls.basePath}/children/:watsonChildId(\\d+)/edit/:model([a-z_A-Z]+)/:entryId(\\d+)/report`}
						/>

						<Router
							component={IncidentReport}
							path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-z_A-Z]+)/:entryId(\\d+)/report`}
						/>

						<Router
							component={IncidentModelSubIndex}
							path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-z_A-Z]+)/:entryId(\\d+)/view`}
						/>

						<Router
							component={EditChild}
							path={`${WatsonConstants.urls.basePath}/children/:watsonChildId(\\d+)/edit/:model([a-z_A-Z]+)/:entryId(\\d+)/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={EditIncident}
							path={`${WatsonConstants.urls.basePath}/incidents/:watsonIncidentId(\\d+)/edit/:model([a-z_A-Z]+)/:entryId(\\d+)/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={AdminConsole}
							path={`${WatsonConstants.urls.basePath}/incidents/admin`}
						/>

						<Router
							component={AdminConsole}
							path={`${WatsonConstants.urls.basePath}/incidents/admin/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={MetricsConsole}
							path={`${WatsonConstants.urls.basePath}/incidents/metrics`}
						/>

						<Router
							component={MetricsConsole}
							path={`${WatsonConstants.urls.basePath}/incidents/metrics/:action([a-z_A-Z]+)`}
						/>

						<Router
							component={Index}
							path={`${WatsonConstants.urls.basePath}/children/index/:model([a-z_A-Z]+)`}
						/>

						<Router
							component={Index}
							path={`${WatsonConstants.urls.basePath}/:model([a-z_A-Z]+)`}
						/>

						<Router
							component={LandingPage}
							path={this._defaultRoute}
						/>
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
	mapComponent: {}
};