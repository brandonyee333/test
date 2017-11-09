import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import sub from 'string-sub';

import {getURLForLanguageId} from '../lib/util';
import LoadingIndicator from './LoadingIndicator';

class SidebarToolbar extends JSXComponent {
	languageOnClick() {
		window.location.href = getURLForLanguageId(WatsonConstants.otherLanguageId);
	}

	render() {
		const {
			displayBy = ''
		} = this.props;

		return (
			<div class="watson-sidebar-toolbar">
				<div class="watson-sidebar-toolbar-content">
					<a class="watson-logo-link" href={`${WatsonConstants.urls.baseURL}/${displayBy}`} />

					<a href={WatsonConstants.urls.incidents} id="incidents-report" title={Liferay.Language.get('incident-report')} />
					<a href={WatsonConstants.urls.children} id="children-home" title={Liferay.Language.get('children-home')} />
					<a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/heatmap`} id="map" title={Liferay.Language.get('heatmap')} />
					<a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/report`} id="reports" title={Liferay.Language.get('reports')} />
					<a id="language-toggle" onClick={this.languageOnClick} title={sub(Liferay.Language.get('switch-to-x'), (WatsonConstants.otherLanguageId).toUpperCase())} />

					{WatsonConstants.currentUser.portalAdminRole &&
						<a href={`${WatsonConstants.urls.baseURL}/incidents/admin`} id="admin" title={Liferay.Language.get('administrator-console')} />
					}

					<a href={`${themeDisplay.getPortalURL()}${themeDisplay.getPathMain()}/portal/logout`} id="logout" title={Liferay.Language.get('logout')} />

					<LoadingIndicator />
				</div>
			</div>
		);
	}
}

SidebarToolbar.PROPS = {
	displayBy: Config.string().value('')
};

SidebarToolbar.STATE = {};

function mapStateToProps(state) {
	return {
		displayBy: state.getIn(['display', 'displayBy'])
	};
}

export default connect(mapStateToProps)(SidebarToolbar);