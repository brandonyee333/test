import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import MenuIconLabel from '../MenuIconLabel';
import MenuList from '../menu-list';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearPages, destroyPage} from '../../actions/pages';
import {EDIT} from '../../lib/router-constants';
import {modalTypes, showModal} from '../../actions/modals';

class PageMenu extends Component {
	created() {
		this.handleDeletePage_ = this.handleDeletePage_.bind(this);
	}

	handleDeletePage_() {
		const {
			addAlert,
			clearPages,
			destroyPage,
			displayURL,
			id,
			ownerId,
			showModal
		} = this.props;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					message: Liferay.Language.get('are-you-sure-you-want-to-delete-this-page'),
					onConfirm: () => {
						destroyPage(id).then(
							() => {
								clearPages(ownerId);

								Liferay.Loop.SPA.navigate(displayURL);

								return addAlert(
									{
										alertType: alertTypes.SUCCESS,
										message: Liferay.Language.get('page-was-successfully-deleted')
									}
								);
							}
						).catch(
							({message}) => addAlert(
								{
									alertType: alertTypes.ERROR,
									message: message ? message : Liferay.Language.get('unable-to-delete-page')
								}
							)
						);
					}
				},
				modalType: modalTypes.CONFIRM_DIALOG
			}
		);
	}

	render() {
		const {id, permissionDelete, permissionEdit} = this.props;

		return (
			<MenuList>
				{permissionEdit &&
					<MenuList.Item href={`${id}/${EDIT}`}>
						<MenuIconLabel
							iconClasses="menu-icon"
							label={Liferay.Language.get('edit')}
							name="pen"
						/>
					</MenuList.Item>
				}

				{permissionDelete &&
					<MenuList.Item onClick={this.handleDeletePage_}>
						<MenuIconLabel
							iconClasses="menu-icon"
							label={Liferay.Language.get('delete')}
							name="trash"
						/>
					</MenuList.Item>
				}
			</MenuList>
		);
	};
}

const STORE = {
	addAlert: Config.func(),
	clearPages: Config.func(),
	destroyPage: Config.func(),
	showModal: Config.func()
};

PageMenu.PROPS = {
	...STORE,
	displayURL: Config.string(),
	id: Config.number(),
	ownerId: Config.number(),
	permissionDelete: Config.bool().value(false),
	permissionEdit: Config.bool().value(false)
};

export default connect(
	null,
	{
		addAlert,
		clearPages,
		destroyPage,
		showModal
	}
)(PageMenu);