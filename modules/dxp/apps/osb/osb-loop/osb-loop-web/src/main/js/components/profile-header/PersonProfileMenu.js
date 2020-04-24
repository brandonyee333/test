import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import MenuList from '../menu-list';
import sendRequest from '../../lib/request';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearLists} from '../../actions/lists';
import {lang} from '../../lib/lang-util';
import {modalTypes, showModal} from '../../actions/modals';
import {personSelector} from '../../lib/selectors';

class PersonProfileMenu extends Component {
	created() {
		this.toggleDeactivate = this.toggleDeactivate.bind(this);
	}

	toggleDeactivate() {
		const {
			addAlert,
			clearLists,
			personIMap,
			showModal
		} = this.props;

		const {entityClassPK, inactive, name} = personIMap.toJS();

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					message: inactive ? lang(Liferay.Language.get('are-you-sure-you-want-to-activate-x'), [name]) : lang(Liferay.Language.get('are-you-sure-you-want-to-deactivate-x'), [name]),
					onConfirm: () => sendRequest(
						{
							controller: 'people',
							controllerMethod: inactive ? 'activate.json' : 'delete.json',
							data: {
								id: entityClassPK
							}
						}
					).then(
						() => {
							clearLists();

							window.location.reload();

							addAlert(
								{
									alertType: alertTypes.SUCCESS,
									message: inactive ? lang(Liferay.Language.get('x-was-successfully-activated'), [name]) : lang(Liferay.Language.get('x-was-successfully-deactivated'), [name])
								}
							);
						}
					)
				},
				modalType: modalTypes.CONFIRM_DIALOG
			}
		);
	}

	render() {
		const {
			displayURL,
			inactive,
			permissionDelete,
			permissionEdit
		} = this.props.personIMap.toJS();

		return (
			<MenuList>
				{permissionEdit &&
					<MenuList.Item href={`${displayURL}/edit`}>
						{Liferay.Language.get('edit-user-information')}
					</MenuList.Item>
				}

				{permissionDelete &&
					<MenuList.Item onClick={this.toggleDeactivate}>
						{inactive ? Liferay.Language.get('activate-user') : Liferay.Language.get('deactivate-user')}
					</MenuList.Item>
				}
			</MenuList>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	clearLists: Config.func(),
	personIMap: Config.instanceOf(Map),
	showModal: Config.func()
};

PersonProfileMenu.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	personSelector,
	{
		addAlert,
		clearLists,
		showModal
	}
)(PersonProfileMenu);