import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import DropDown from 'metal-dropdown';
import JSXComponent, {Config} from 'metal-jsx';

import LoadingIndicator from './LoadingIndicator';
import Toggle from './Toggle';

class HeaderToolbar extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnClick'
		);
	}

	handleOnClick(event) {
		const {target} = event;

		if (target) {
			this.setState({currentView: target.attributes.id.value});
		}
	}

	render() {
		const {
			displayBy = '',
			languageToggleOnChange,
			thaiIsChecked,
			toggleCSSClass,
			toggleLabel
		} = this.props;

		const {currentView} = this.state;

		let {userName} = this.state;

		userName = userName.length > 20 ? `${userName.substring(0, 20)}...` : userName;

		const userNameDiv = (<button class="watson-button dropdown" data-onclick="toggle">{userName} <span class="caret" /></button>);

		const userNameDropDown = (
			<ul class="items">
				<li><a href={`${WatsonConstants.urls.baseURL}/incidents/admin`}>{Liferay.Language.get('administrator-console')}</a></li>
				<li><a href={`${themeDisplay.getPortalURL()}${themeDisplay.getPathMain()}/portal/logout`}>{Liferay.Language.get('logout')}</a></li>
			</ul>
		);

		const moduleDiv = (<button class="watson-button dropdown" data-onclick="toggle">{currentView} <span class="caret" /></button>);

		const moduleDropDown = (
			<ul class="items">
				<li><a href={`${WatsonConstants.urls.baseURL}/${displayBy}`} id={Liferay.Language.get('incident-report')} onclick={this.handleOnClick}>{Liferay.Language.get('incident-report')}</a></li>
				<li><a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/heatmap`} id={Liferay.Language.get('heatmap')} onclick={this.handleOnClick}>{Liferay.Language.get('heatmap')}</a></li>
				<li><a href={`${WatsonConstants.urls.baseURL}/incidents/metrics/report`} id={Liferay.Language.get('reports')} onclick={this.handleOnClick}>{Liferay.Language.get('reports')}</a></li>
			</ul>
		);

		return (
			<div class="watson-header-toolbar">
				<div class="watson-header-toolbar-content">
					<a class="watson-logo-link" href={`${WatsonConstants.urls.baseURL}/${displayBy}`}>
						<div class="watson-logo" />
					</a>

					<div class="module-dropdown" >
						<DropDown
							alignElementSelector="button"
							body={moduleDropDown}
							header={moduleDiv}
							position="down"
						/>
					</div>

					<Toggle
						checked={thaiIsChecked}
						cssClass={toggleCSSClass}
						label={toggleLabel}
						onChange={languageToggleOnChange}
					/>

					<div class="watson-logout" >
						<DropDown
							alignElementSelector="button"
							body={userNameDropDown}
							header={userNameDiv}
							position="down"
						/>
					</div>

					<LoadingIndicator />
				</div>
			</div>
		);
	}
}

HeaderToolbar.PROPS = {
	displayBy: Config.string().value(''),
	languageToggleOnChange: Config.func(),
	thaiIsChecked: Config.bool(),
	toggleCSSClass: Config.string()
};

HeaderToolbar.STATE = {
	currentView: Config.string().value(Liferay.Language.get('incident-report')),
	userName: Config.string().value(Liferay.ThemeDisplay.getUserName() || '')
};

function mapStateToProps(state) {
	return {
		displayBy: state.getIn(['display', 'displayBy'])
	};
}

export default connect(mapStateToProps)(HeaderToolbar);