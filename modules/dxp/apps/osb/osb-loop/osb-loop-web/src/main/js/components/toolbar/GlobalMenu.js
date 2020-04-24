import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';

import Avatar from '../Avatar';
import Icon from '../Icon';
import LoopConstants from '../../lib/loop-constants';
import ModalLink from '../ModalLink';

const {
	currentPerson: {
		displayURL,
		permissionAdmin
	},
	feedbackName,
	uiKitEnabled,
	urls: {
		adminPage,
		help,
		topics,
		uiKit
	}
} = LoopConstants;

class GlobalMenu extends Component {
	created() {
		bindAll(
			this,
			'closeMenu_',
			'handleClickoutsideEvents_',
			'toggleMenu_'
		);
	}

	detached() {
		document.removeEventListener('mousedown', this.closeMenu_);
	}

	handleClickoutsideEvents_(active) {
		if (active) {
			document.addEventListener('mousedown', this.closeMenu_);
		}
		else {
			document.removeEventListener('mousedown', this.closeMenu_);
		}
	}

	closeMenu_({target}) {
		if (this.state.active_ && !this.element.contains(target)) {
			this.state.active_ = false;

			this.handleClickoutsideEvents_(false);
		}
	}

	toggleMenu_() {
		const newVal = !this.state.active_;

		this.state.active_ = newVal;

		this.handleClickoutsideEvents_(newVal);
	}

	render() {
		const {hotKeysModalConfig, markdownCheatsheetModalConfig} = this.props;

		return (
			<div class="global-menu-container">
				<div class="toggle" onClick={this.toggleMenu_}>
					<Avatar disableLink={true} entity={this.props.entity} size="smallest" />

					<Icon display="secondary" name="arrow-down-short" size="small" />
				</div>

				{this.state.active_ &&
					<ul class="dropdown">
						<li>
							<a href={displayURL}>
								{Liferay.Language.get('my-profile')}
							</a>
						</li>

						<li class="menu-divider" />

						{permissionAdmin &&
							<li>
								<a href={adminPage}>
									{Liferay.Language.get('admin-page')}
								</a>
							</li>
						}

						{permissionAdmin &&
							<li class="menu-divider" />
						}

						{!!feedbackName.length &&
							<li>
								<a href={`${topics}/_${feedbackName}`}>
									{Liferay.Language.get('provide-feedback')}
								</a>
							</li>
						}

						{!!feedbackName.length &&
							<li class="menu-divider" />
						}

						<li>
							<ModalLink config={markdownCheatsheetModalConfig}>
								{Liferay.Language.get('markdown-cheatsheet')}
							</ModalLink>
						</li>

						<li>
							<a href={help}>
								{Liferay.Language.get('help')}
							</a>
						</li>

						{uiKitEnabled &&
							<li>
								<a href={uiKit}>
									{Liferay.Language.get('ui-kit')}
								</a>
							</li>
						}

						<li class="menu-divider" />

						{hotKeysModalConfig &&
							<li>
								<ModalLink config={hotKeysModalConfig}>
									{Liferay.Language.get('keyboard-shortcuts')}
								</ModalLink>
							</li>
						}

						{hotKeysModalConfig &&
							<li class="menu-divider" />
						}

						<li>
							<a data-metal-router-off href="/c/portal/logout">
								{Liferay.Language.get('sign-out')}
							</a>
						</li>
					</ul>
				}
			</div>
		);
	}
}

GlobalMenu.PROPS = {
	entity: Config.object(),
	hotKeysModalConfig: Config.object(),
	markdownCheatsheetModalConfig: Config.object()
};

GlobalMenu.STATE = {
	active_: Config.value(false)
};

export default GlobalMenu;