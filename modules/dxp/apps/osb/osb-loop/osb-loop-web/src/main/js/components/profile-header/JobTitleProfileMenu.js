import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import LoopConstants from '../../lib/loop-constants';
import MenuList from '../menu-list';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearLists} from '../../actions/lists';
import {destroyJobTitle, setInactive} from '../../actions/job-titles';
import {lang} from '../../lib/lang-util';
import {modalTypes, showModal} from '../../actions/modals';

class JobTitleProfileMenu extends Component {
	created() {
		bindAll(
			this,
			'handleDeleteJobTitle_',
			'handleEditJobTitle_',
			'handleToggleDeactivateJobTitle_'
		);
	}

	handleToggleDeactivateJobTitle_() {
		const {
			addAlert,
			clearLists,
			id,
			name,
			setInactive,
			showModal,
			status
		} = this.props;

		const inactive = status === LoopConstants.status.inactive;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					message: inactive ? lang(Liferay.Language.get('are-you-sure-you-want-to-activate-x'), [name]) : lang(Liferay.Language.get('are-you-sure-you-want-to-deactivate-x'), [name]),
					onConfirm: () => setInactive({id, inactive: !inactive}).then(
						() => {
							clearLists();

							addAlert(
								{
									alertType: alertTypes.SUCCESS,
									message: inactive ? lang(Liferay.Language.get('x-was-successfully-activated'), [name]) : lang(Liferay.Language.get('x-was-successfully-deactivated'), [name])
								}
							);
						}
					).catch(
						({message}) => {
							if (message) {
								addAlert(
									{
										alertType: alertTypes.ERROR,
										message
									}
								);
							}
						}
					)
				},
				modalType: modalTypes.CONFIRM_DIALOG
			}
		);
	}

	handleDeleteJobTitle_() {
		const {
			addAlert,
			clearLists,
			destroyJobTitle,
			id,
			name,
			showModal
		} = this.props;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					message: Liferay.Language.get('are-you-sure-you-want-to-delete-this-job-title'),
					onConfirm: () => destroyJobTitle(id).then(
						() => {
							clearLists();

							Liferay.Loop.SPA.navigate(LoopConstants.urls.jobTitles);

							addAlert(
								{
									alertType: alertTypes.SUCCESS,
									message: lang(Liferay.Language.get('x-was-successfully-deleted'), [name])
								}
							);
						}
					)
				},
				modalType: modalTypes.CONFIRM_DIALOG
			}
		);
	}

	handleEditJobTitle_() {
		const {id, showModal} = this.props;

		showModal(
			{
				modalProps: {id},
				modalType: modalTypes.EDIT_JOB_TITLE
			}
		);
	}

	render() {
		const inactive = this.props.status === LoopConstants.status.inactive;

		return (
			<MenuList>
				<MenuList.Item onClick={this.handleEditJobTitle_}>
					{Liferay.Language.get('edit-job-title')}
				</MenuList.Item>

				<MenuList.Item onClick={this.handleToggleDeactivateJobTitle_}>
					{inactive ? Liferay.Language.get('activate-job-title') : Liferay.Language.get('deactivate-job-title')}
				</MenuList.Item>

				<MenuList.Item onClick={this.handleDeleteJobTitle_}>
					{Liferay.Language.get('delete-job-title')}
				</MenuList.Item>
			</MenuList>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	clearLists: Config.func(),
	destroyJobTitle: Config.func(),
	setInactive: Config.func(),
	showModal: Config.func()
};

JobTitleProfileMenu.PROPS = {
	...STORE,
	id: Config.number(),
	name: Config.string(),
	status: Config.number()
};

export default connect(
	null,
	{
		addAlert,
		clearLists,
		destroyJobTitle,
		setInactive,
		showModal
	}
)(JobTitleProfileMenu);