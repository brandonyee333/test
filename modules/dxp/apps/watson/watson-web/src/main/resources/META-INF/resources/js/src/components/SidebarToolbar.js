import {bindAll} from 'lodash';
import DropDown from 'metal-dropdown';
import JSXComponent, {Config} from 'metal-jsx';

import {getURLForLanguageId} from '../lib/util';
import LoadingIndicator from './LoadingIndicator';

class SidebarToolbar extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnClick'
		);
	}

	formatDropDown() {
		const formattedOptions = (
			<ul class="items">
				<li><a class="item" id="en_US" onClick={this.languageOnClick}>{'English'}</a></li>
				<li><a class="item" id="th" onClick={this.languageOnClick}>{'Thai'}</a></li>
			</ul>);

		return formattedOptions;
	}

	handleOnClick(event) {
		const {target} = event;

		if (target) {
			this.setState({selected: target.attributes.id.value});
		}
	}

	languageOnClick(event) {
		const {target} = event;

		const languageId = target.attributes.id.value;

		if (languageId) {
			window.location.href = getURLForLanguageId(languageId);
		}
	}

	render() {
		const languageToggleDiv = (<button class="watson-button dropdown" data-onClick="toggle" id="language-toggle" title={Liferay.Language.get('switch-language')} />);

		return (
			<div class="watson-sidebar-toolbar">
				<span class="upper">
					<a href={WatsonConstants.urls.baseURL} id="watson-logo-link" onClick={this.handleOnClick} />

					{WatsonConstants.currentUser.incidentStaffRole &&
						<a href={`${WatsonConstants.urls.baseURL}/incidents`} id="incidents" onClick={this.handleOnClick} title={Liferay.Language.get('incident-report')} />
					}

					{WatsonConstants.currentUser.childrensHomeStaffRole &&
						<a href={WatsonConstants.urls.children} id="children" onClick={this.handleOnClick} title={Liferay.Language.get('children-home')} />
					}

					{WatsonConstants.currentUser.incidentStaffRole &&
						<a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/heatmap`} id="heatmap" onClick={this.handleOnClick} title={Liferay.Language.get('heatmap')} />
					}

					{WatsonConstants.currentUser.incidentStaffRole &&
						<a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/report`} id="report" onClick={this.handleOnClick} title={Liferay.Language.get('reports')} />
					}

				</span>

				<span class="lower">
					<div class="metal-dropdown-menu">
						<DropDown
							alignElementSelector="button"
							body={this.formatDropDown()}
							header={languageToggleDiv}
							position={1}
						/>
					</div>

					{WatsonConstants.currentUser.portalAdministratorRole &&
						<a href={`${WatsonConstants.urls.baseURL}/incidents/admin`} id="admin" onClick={this.handleOnClick} title={Liferay.Language.get('administrator-console')} />
					}

					<a href={`${themeDisplay.getPortalURL()}${themeDisplay.getPathMain()}/portal/logout`} id="logout" title={Liferay.Language.get('logout')} />
				</span>

				<LoadingIndicator />
			</div>
		);
	}

	rendered() {
		this.refreshSelected();
	}

	refreshSelected() {
		const {lastSelected, selected} = this.state;

		if (lastSelected !== selected) {
			const lastSelectedElement = document.getElementById(lastSelected);

			if (lastSelectedElement) {
				lastSelectedElement.className = '';
			}

			const newlySelectedElement = document.getElementById(selected);

			if (newlySelectedElement) {
				newlySelectedElement.className = 'selected';

				this.state.lastSelected = selected;
			}
		}

		const route = window.location.href;

		let selectedFound = false;

		if ((selected === undefined) && (route !== WatsonConstants.urls.baseURL)) {
			const {sidebarURLs = {}} = this.state;

			for (const key in sidebarURLs) {
				if (sidebarURLs.hasOwnProperty(key)) {
					const formattedURL = `${WatsonConstants.urls.baseURL}${sidebarURLs[key]}`;

					if (route === formattedURL) {
						selectedFound = key;
					}
				}
			}

			if (!selectedFound) {
				const incidentsRegex = 'incidents\/[^\d]*(\d+)[^\d]';

				const childrenRegex = 'children\/[^\d]*(\d+)[^\d]';

				if (route.match(incidentsRegex)) {
					selectedFound = 'incidents';
				}
				else if (route.match(childrenRegex)) {
					selectedFound = 'children';
				}
				else {
					const {urls = {}} = WatsonConstants;

					if (!route.includes('children') && !route.includes('documents')) {
						for (const key in urls) {
							if (urls.hasOwnProperty(key)) {

								if (route === urls[key]) {
									selectedFound = 'incidents';
								}
							}
						}
					}
				}
			}

			if (selectedFound) {
				this.state.selected = selectedFound;
			}
		}
	}
}

SidebarToolbar.PROPS = {};

SidebarToolbar.STATE = {
	lastSelected: Config.string(),
	selected: Config.string(),
	sidebarURLs: Config.object().value(
		{
			admin: '/incidents/admin',
			children: '/children',
			heatmap: '/incidents/metrics/heatmap',
			incidents: '/incidents',
			report: '/incidents/metrics/report'
		}
	)
};

export default SidebarToolbar;