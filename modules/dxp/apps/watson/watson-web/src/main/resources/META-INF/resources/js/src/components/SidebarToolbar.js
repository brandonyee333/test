import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';

import LoadingIndicator from './LoadingIndicator';

class SidebarToolbar extends JSXComponent {
	render() {
		const {
			displayBy = '',
			languageOnClick,
			thaiIsChecked
		} = this.props;

		return (
			<div class="watson-sidebar-toolbar">
				<div class="watson-sidebar-toolbar-content">
					<a class="watson-logo-link" href={`${WatsonConstants.urls.baseURL}/${displayBy}`}>
						<div class="watson-logo" />
					</a>

					<ul>
						<a href={`${WatsonConstants.urls.baseURL}/${displayBy}`} id="incidents-report" title={Liferay.Language.get('incident-report')}/>
						<a href={`${WatsonConstants.urls.baseURL}/incidents`} id="children-home" title={Liferay.Language.get('children')}/>
						<a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/heatmap`} id="map" title={Liferay.Language.get('heatmap')}/>
						<a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/report`} id="reports" title={Liferay.Language.get('reports')}/>

						<a onClick={languageOnClick} id="language-toggle" title={Liferay.Language.get('switch-to-x')}/>

						<a href={`${WatsonConstants.urls.baseURL}/incidents/admin`} id="admin" title={Liferay.Language.get('administrator-console')}/>
						<a href={`${themeDisplay.getPortalURL()}${themeDisplay.getPathMain()}/portal/logout`} id="logout" title={Liferay.Language.get('logout')}/>
					</ul>

					<LoadingIndicator />
				</div>
			</div>
		);
	}
}

SidebarToolbar.PROPS = {
	displayBy: Config.string().value(''),
	languageOnClick: Config.func(),
	thaiIsChecked: Config.bool(),
};

SidebarToolbar.STATE = {};

function mapStateToProps(state) {
	return {
		displayBy: state.getIn(['display', 'displayBy'])
	};
}

export default connect(mapStateToProps)(SidebarToolbar);