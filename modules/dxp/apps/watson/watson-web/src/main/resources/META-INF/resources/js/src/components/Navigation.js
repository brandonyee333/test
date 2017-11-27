import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

class Navigation extends JSXComponent {
	created() {
		bindAll(
			this,
			'collapseToggle',
			'populateNavigation'
		);
	}

	collapseToggle(event) {
		const {
			target: {
				dataset: {
					collapsed = false,
					hash
				}
			}
		} = event;

		if (hash) {
			const {navigationState = {}, onChange} = this.props;

			const currentEntryHashCollapsed = (navigationState && navigationState[hash]) ? !!(navigationState && navigationState[hash]) : JSON.parse(collapsed);

			onChange(
				Object.assign(
					{},
					navigationState,
					{
						[hash]: !currentEntryHashCollapsed
					}
				)
			);
		}
	}

	populateConfig(config, data) {
		for (const key in data) {
			if (data.hasOwnProperty(key)) {
				const dashedKey = key.replace(
					/([A-Z])/g,
					key => {
						return `-${key.toLowerCase()}`;
					}
				);

				config[`data-${dashedKey}`] = data[key];
			}
		}
	}

	populateNavigation(entries) {
		entries = entries || WatsonConstants.defaultNavigation;

		const navRows = [];

		const {navigationState} = this.props;

		if (entries) {
			entries.forEach(
				entry => {
					const selected = entry.selected ? 'selected' : '';

					const config = {
						class: 'watson-nav-link'
					};

					const {data, href, name} = entry;

					if (data) {
						this.populateConfig(config, data);
					}

					if (href) {
						config.href = href;
					}

					if (name) {
						config.id = name;
					}

					let collapseArrow = '';
					let collapsible = '';
					let nestedNavEntries = '';

					if (entry.entries) {
						let collapsedCssClass = '';

						if (entry.collapsible) {
							collapsible = 'collapsible';

							const storeCollapsed = navigationState && navigationState[`${entry.text}_${entry.href}`];

							let collapsed = false;

							if (storeCollapsed || (storeCollapsed == undefined && (entry.collapsed || entry.entries.length > 2))) {
								collapsed = true;
							}

							collapsedCssClass = collapsed ? 'collapsed' : '';

							collapseArrow = (
								<div class={`collapse-arrow ${collapsedCssClass}`}>
									{entry.entries.length}

									<a data-collapsed={JSON.stringify(collapsed)} data-hash={`${entry.text}_${entry.href}`} onClick={this.collapseToggle} />
								</div>
							);
						}

						nestedNavEntries = (
							<ul class={`${collapsible} ${collapsedCssClass}`}>
								{this.populateNavigation(entry.entries)}
							</ul>
						);
					}

					navRows.push(
						<li class={`watson-nav-entry ${collapsible} ${selected}`}>
							<a {...config}>{entry.text}</a>

							{collapseArrow}
						</li>
					);

					if (nestedNavEntries) {
						navRows.push(nestedNavEntries);
					}
				}
			);
		}

		return navRows;
	}

	render() {
		const {entries} = this.props;

		return (
			<nav class="watson-nav">
				<ul class="watson-nav-wrapper">
					{this.populateNavigation(entries)}
				</ul>
			</nav>
		);
	}
}

Navigation.PROPS = {
	navigationState: Config.any(),
	onChange: Config.func()
};

export default Navigation;