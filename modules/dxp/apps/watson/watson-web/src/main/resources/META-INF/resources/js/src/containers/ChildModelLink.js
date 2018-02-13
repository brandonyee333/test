import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import NavigationHeader from '../components/NavigationHeader';
import ViewIndex from './views/ViewIndex';

import {importChildren} from '../actions/children';

class ChildModelLink extends JSXComponent {
	render() {
		const {
			action,
			linkChildren
		} = this.props;

		const childrenLinkRedirect = () => Router.router().navigate(`${WatsonConstants.urls.baseURL}/children`);

		const buttonData = [
			{
				label: Liferay.Language.get('link')
			},
			{
				label: Liferay.Language.get('cancel'),
				method: childrenLinkRedirect
			}
		];

		return (
			<div class="model-index page-container hidden-print">
				<div class="navigation-sidebar">
					<NavigationHeader mainHeader={Liferay.Language.get('link-child')} />
				</div>

				<ViewIndex
					action={action}
					buttonData={buttonData}
					headerStringLeft={Liferay.Language.get('link-child')}
					model="people"
					redirect={childrenLinkRedirect}
					selectedIds={[]}
					submitMethod={linkChildren}
					watsonwatsonIncidentId={0}
				/>
			</div>
		);
	}
}

ChildModelLink.PROPS = {
	action: Config.string().value('link'),
	model: Config.string().value('people')
};

function mapDispatchToProps(dispatch) {
	return {
		linkChildren: data => {
			dispatch(
				importChildren(data)
			);
		}
	};
}

export default connect(null, mapDispatchToProps)(ChildModelLink);