import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import NavigationHeader from '../components/NavigationHeader';
import ViewIndex from './views/ViewIndex';

import {importChildren} from '../actions/children';

class ChildModelLink extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleDataSent'
		);
	}

	_handleDataSent() {
		this.setState({dataSent: true});
	}

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
					redirect={this._handleDataSent}
					selectedIds={[]}
					submitMethod={linkChildren}
					watsonIncidentId={0}
				/>
			</div>
		);
	}

	rendered() {
		const {loading, responseStatus} = this.props;

		const {dataSent} = this.state;

		if (dataSent && !loading && responseStatus === true) {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/children`);
		}
	}
}

ChildModelLink.PROPS = {
	action: Config.string().value('link'),
	loading: Config.bool(),
	model: Config.string().value('people'),
	responseStatus: Config.bool()
};

ChildModelLink.STATE = {
	dataSent: Config.bool().value(false)
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

function mapStateToProps(state) {
	const loading = state.getIn(['children', 'loading']);
	const responseStatus = state.getIn(['children', 'imported']);

	return {
		loading,
		responseStatus
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ChildModelLink);