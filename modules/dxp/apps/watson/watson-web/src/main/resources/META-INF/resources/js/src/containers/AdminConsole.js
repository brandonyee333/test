import JSXComponent from 'metal-jsx';

import IncidentImporter from './views/IncidentImporter';

import Navigation from '../components/Navigation';
import NavigationHeader from '../components/NavigationHeader';

class AdminConsole extends JSXComponent {
	getCurrentView(action) {
		let currentView = (
			<div class="content-container">
				<div class="content-header">
					<span class="text">{Liferay.Language.get('please-select-a-method-on-the-left')}</span>
				</div>
			</div>
		);

		if (action === 'importFile') {
			currentView = (<IncidentImporter />);
		}

		return currentView;
	}

	render() {
		const {action} = this.props.router.params;

		const nav = [
			{
				collapsible: false,
				href: `${WatsonConstants.urls.baseURL}/incidents/admin/importFile`,
				selected: action === 'importFile',
				text: Liferay.Language.get('import-file')
			},
			{
				collapsible: false,
				href: WatsonConstants.urls.baseURL,
				selected: false,
				text: Liferay.Language.get('back')
			}
		];

		return (
			<div class="page-container incidents-admin hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader subHeader={Liferay.Language.get('administrator-console')} />

					<Navigation entries={nav} />
				</div>

				{this.getCurrentView(action)}
			</div>
		);
	}
}

export default AdminConsole;