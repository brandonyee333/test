import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Icon from '../Icon';
import LoadingWrapper from '../LoadingWrapper.js';
import MarkdownContent from '../MarkdownContent';
import Overlay from '../Overlay';
import PageHeader from './PageHeader';
import {addAlert, alertTypes} from '../../actions/alerts';
import {fetchPage} from '../../actions/pages';
import {getPage} from '../../lib/selectors';
import {overlayTypes} from '../../actions/overlays';

class Page extends Component {
	created() {
		const {hash} = window.location;

		this.handleFetchPage_();

		if (hash) {
			this._hash = hash.replace('#', '');
		}
	}

	rendered() {
		if (!this.props.loading) {
			const element = document.getElementById(this._hash);

			if (element) {
				element.scrollIntoView();
			}
		}
	}

	handleFetchPage_() {
		const {displayURL, fetchPage, id} = this.props;

		return fetchPage(id).catch(
			({message}) => {
				Liferay.Loop.SPA.navigate(displayURL);

				addAlert(
					{
						alertType: alertTypes.ERROR,
						message: message ? message : Liferay.Language.get('unable-to-fetch-page')
					}
				);
			}
		);
	}

	render() {
		const {
			creatorIMap,
			displayURL,
			itemIMap,
			loading
		} = this.props;

		const {
			classPK,
			entityClassPK,
			modifiedTime,
			payload: {message} = {},
			permissionDelete,
			permissionEdit,
			title
		} = itemIMap.toJS();

		return (
			<LoadingWrapper elementClasses="page-container" loading={loading} mask={true}>
				<PageHeader creatorIMap={creatorIMap} modifiedTime={modifiedTime} />

				{(permissionDelete || permissionEdit) &&
					<Overlay
						containerClass="page-menu-container"
						overlayProps={{
							displayURL,
							id: entityClassPK,
							ownerId: classPK,
							permissionDelete,
							permissionEdit
						}}
						overlayType={overlayTypes.PAGE_MENU}
					>
						<Icon name="ellipsis" />
					</Overlay>
				}

				<h1 class="title">{title}</h1>

				<MarkdownContent message={message} />
			</LoadingWrapper>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	creatorIMap: Config.instanceOf(Map),
	fetchPage: Config.func(),
	itemIMap: Config.instanceOf(Map),
	loading: Config.bool()
};

Page.PROPS = {
	...STORE,
	displayURL: Config.string(),
	id: Config.number()
};

export default connect(
	(state, {id}) => getPage(state, id),
	{
		addAlert,
		fetchPage
	}
)(Page);