import {connect} from 'metal-redux';
import DropDown from 'metal-dropdown';
import JSXComponent, {Config} from 'metal-jsx';

import {getURLForLanguageId} from '../lib/util';
import LoadingIndicator from './LoadingIndicator';

class SidebarToolbar extends JSXComponent {
	formatDropDown() {
		const formattedOptions = (
			<ul class="items">
				<li><a class="item" id="en_US" onclick={this.languageOnClick}>{English}</a></li>
				<li><a class="item" id="th" onclick={this.languageOnClick}>{Thai}</a></li>
			</ul>);

		return formattedOptions;
	}

	languageOnClick(event) {
		const {target} = event;

		const languageId = target.attributes.id.value;

		if (languageId) {
			window.location.href = getURLForLanguageId(languageId);
		}
	}

	render() {
		const {
			displayBy = ''
		} = this.props;

		const selected = 'selected';

		const languageToggleDiv = (<button class="watson-button dropdown" data-onclick="toggle" id="language-toggle" title={Liferay.Language.get('switch-language')} />);

		return (
			<div class="watson-sidebar-toolbar">
				<span class="upper">
					<a class="watson-logo-link" href={`${WatsonConstants.urls.baseURL}/${displayBy}`} />
					<a class={selected} href={WatsonConstants.urls.incidents} id="incidents-report" title={Liferay.Language.get('incident-report')} />
					<a class={selected} href={WatsonConstants.urls.children} id="children-home" title={Liferay.Language.get('children-home')} />
					<a class={selected} href={`${WatsonConstants.urls.baseURL}/incidents/metrics/heatmap`} id="map" title={Liferay.Language.get('heatmap')} />
					<a class={selected} href={`${WatsonConstants.urls.baseURL}/incidents/metrics/report`} id="reports" title={Liferay.Language.get('reports')} />
				</span>

				<span class="lower">
					<div class="metal-dropdown-menu">
						<DropDown
							alignElementSelector="button"
							body={this.formatDropDown()}
							header={languageToggleDiv}
							position="right"
						/>
					</div>

					{WatsonConstants.currentUser.portalAdminRole &&
						<a class={selected} href={`${WatsonConstants.urls.baseURL}/incidents/admin`} id="admin" title={Liferay.Language.get('administrator-console')} />
					}

					<a class={selected} href={`${themeDisplay.getPortalURL()}${themeDisplay.getPathMain()}/portal/logout`} id="logout" title={Liferay.Language.get('logout')} />
				</span>

				<LoadingIndicator />
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