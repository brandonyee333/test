import {connect} from 'metal-redux';
import DropDown from 'metal-dropdown';
import JSXComponent, {Config} from 'metal-jsx';

import LoadingIndicator from './LoadingIndicator';
import Toggle from './Toggle';

class HeaderToolbar extends JSXComponent {
	formatDropDown() {
		const formattedOptions = (
			<ul class="items">
				<li><a href={`${WatsonConstants.urls.baseURL}/incidents/admin`}>{Liferay.Language.get('administrator-console')}</a></li>
				<li><a href={`${WatsonConstants.urls.baseURL}/incidents/metrics`}>{Liferay.Language.get('reports')}</a></li>
				<li><a href={`${themeDisplay.getPortalURL()}${themeDisplay.getPathMain()}/portal/logout`}>{Liferay.Language.get('logout')}</a></li>
			</ul>);

		return formattedOptions;
	}

	render() {
		const {
			displayBy = '',
			languageToggleOnChange,
			thaiIsChecked,
			toggleCSSClass,
			toggleLabel
		} = this.props;

		let {userName} = this.state;

		userName = userName.length > 20 ? `${userName.substring(0, 20)}...` : userName;

		const userNameDiv = (<button class="watson-button dropdown" data-onclick="toggle">{userName} <span class="caret" /></button>);

		return (
			<div class="watson-header-toolbar">
				<div class="watson-header-toolbar-content">
					<a class="watson-logo-link" href={`${WatsonConstants.urls.baseURL}/${displayBy}`}>
						<div class="watson-logo" />
					</a>

					<Toggle
						checked={thaiIsChecked}
						cssClass={toggleCSSClass}
						label={toggleLabel}
						onChange={languageToggleOnChange}
					/>

					<div class="watson-logout" >
						<DropDown
							alignElementSelector="button"
							body={this.formatDropDown()}
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
	userName: Config.string().value(Liferay.ThemeDisplay.getUserName() || '')
};

function mapStateToProps(state) {
	return {
		displayBy: state.getIn(['display', 'displayBy'])
	};
}

export default connect(mapStateToProps)(HeaderToolbar);