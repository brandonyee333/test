import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import NavigationHeader from '../components/NavigationHeader';
import ViewIndex from './views/ViewIndex';

import {importChildren} from '../actions/children';

class ChildModelImport extends JSXComponent {
	render() {
		const {
			action,
			importChildren
		} = this.props;

		const childrenImportRedirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/children`);

		const buttonData = [
			{
				label: Liferay.Language.get('import')
			},
			{
				label: Liferay.Language.get('cancel'),
				method: childrenImportRedirect
			}
		];

		return (
			<div class="model-index page-container hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader mainHeader={Liferay.Language.get('import-child')} />
				</div>

				<ViewIndex
					action={action}
					buttonData={buttonData}
					headerStringLeft={Liferay.Language.get('import-child')}
					model="people"
					redirect={childrenImportRedirect}
					selectedIds={[]}
					submitMethod={importChildren}
					watsonwatsonIncidentId={0}
				/>
			</div>
		);
	}
}

ChildModelImport.PROPS = {
	action: Config.string().value('import'),
	model: Config.string().value('people')
};

function mapDispatchToProps(dispatch) {
	return {
		importChildren: data => {
			dispatch(
				importChildren(data)
			);
		}
	};
}

export default connect(null, mapDispatchToProps)(ChildModelImport);