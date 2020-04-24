import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import AssetEditModal from '../AssetEditModal';
import AssignChildrenModal from '../profile-header/AssignChildrenModal';
import AssignParentModal from '../profile-header/AssignParentModal';
import Body from './Body';
import ConfirmDialogModal from '../ConfirmDialogModal';
import ConvertDivisionTypeModal from '../profile-header/ConvertDivisionTypeModal';
import CreateDivisionModal from '../CreateDivisionModal';
import CreateJobTitleModal from '../CreateJobTitleModal';
import CreatePersonModal from '../CreatePersonModal';
import CreateTopicModal from '../CreateTopicModal';
import CropImageModal from '../profile-header/CropImageModal';
import EditJobTitleModal from '../EditJobTitleModal';
import EntityListModal from '../EntityListModal';
import FeaturedTopicsModal from '../FeaturedTopicsModal';
import FeaturedTopicsSettingsModal from '../FeaturedTopicsSettingsModal';
import Footer from './Footer';
import Header from './Header';
import HotKeysModal from '../HotKeysModal';
import LikedParticipantsModal from '../LikedParticipantsModal';
import MarkdownCheatsheetModal from '../MarkdownCheatsheetModal';
import MergeTopicsModal from '../profile-header/MergeTopicsModal';
import NewDivisionsModal from '../NewDivisionsModal';
import NewPeopleModal from '../NewPeopleModal';
import OrgChartModal from '../org-chart-modal';
import PostPreviewModal from '../PostPreviewModal';
import SharedToModal from '../SharedToModal';
import ShareLinkModal from '../ShareLinkModal';
import {hideModal, modalTypes} from '../../actions/modals';
import {hotKeyManager} from '../../lib/HotKeyManager';

export const COMPONENT_MAP = {
	[modalTypes.ASSIGN_CHILDREN]: AssignChildrenModal,
	[modalTypes.ASSIGN_PARENT]: AssignParentModal,
	[modalTypes.CONFIRM_DIALOG]: ConfirmDialogModal,
	[modalTypes.CONVERT_DIVISION_TYPE]: ConvertDivisionTypeModal,
	[modalTypes.CREATE_DIVISION]: CreateDivisionModal,
	[modalTypes.CREATE_JOB_TITLE]: CreateJobTitleModal,
	[modalTypes.CREATE_PERSON]: CreatePersonModal,
	[modalTypes.CREATE_TOPIC]: CreateTopicModal,
	[modalTypes.CROP_IMAGE]: CropImageModal,
	[modalTypes.EDIT_JOB_TITLE]: EditJobTitleModal,
	[modalTypes.EDIT_POST_MODAL]: AssetEditModal,
	[modalTypes.ENTITY_LIST]: EntityListModal,
	[modalTypes.FEATURED_TOPICS]: FeaturedTopicsModal,
	[modalTypes.FEATURED_TOPICS_SETTINGS]: FeaturedTopicsSettingsModal,
	[modalTypes.HOT_KEYS]: HotKeysModal,
	[modalTypes.LIKED_PARTICIPANTS]: LikedParticipantsModal,
	[modalTypes.MARKDOWN_CHEATSHEET]: MarkdownCheatsheetModal,
	[modalTypes.MERGE_TOPICS]: MergeTopicsModal,
	[modalTypes.NEW_DIVISIONS]: NewDivisionsModal,
	[modalTypes.NEW_PEOPLE]: NewPeopleModal,
	[modalTypes.ORG_CHART]: OrgChartModal,
	[modalTypes.POST_PREVIEW]: PostPreviewModal,
	[modalTypes.SHARE_LINK]: ShareLinkModal,
	[modalTypes.SHARED_TO]: SharedToModal
};

const BODY_CLASS_NAME = 'modal-page-body-open';

export class Modal extends Component {
	created() {
		bindAll(
			this,
			'handleOverlayClick_',
			'hideModal_'
		);
	}

	attached() {
		this.setHideModalShorcut_();
	}

	detached() {
		hotKeyManager.unbind('esc');
	}

	handleOverlayClick_({target}) {
		const {modalIMap} = this.props;

		if (target === this.refs.wrapper && modalIMap.get('hideOnBlur')) {
			this.hideModal_();
		}
	}

	hideModal_() {
		const {hideModal, modalIMap} = this.props;

		const redirectURL = modalIMap.get('redirectURL');

		if (redirectURL) {
			Liferay.Loop.SPA.navigate(redirectURL);
		}

		const onClose = modalIMap.get('onClose');

		if (onClose) {
			onClose();
		}

		hideModal();
	}

	setHideModalShorcut_() {
		hotKeyManager.bind(
			{
				action: this.hideModal_,
				definition: Liferay.Language.get('hide-pop-up'),
				keys: 'esc'
			}
		);
	}

	syncModalIMap(newVal) {
		const {classList} = document.body;

		if (newVal.get('open')) {
			classList.add(BODY_CLASS_NAME);
		}
		else {
			classList.remove(BODY_CLASS_NAME);
		}
	}

	render() {
		const {modalIMap} = this.props;

		let content;

		const modalType = modalIMap.get('modalType');

		if (modalType) {
			const SpecificModal = COMPONENT_MAP[modalType];

			content = <SpecificModal {...modalIMap.get('modalProps').toJS()} hideModal={this.hideModal_} />;
		}

		const classes = getCN(
			'loop-portlet',
			'modal-container',
			{full: modalIMap.get('fullScreen', false)}
		);

		return (
			<div class={classes}>
				{modalIMap.get('open') &&
					<div class="wrapper" onClick={this.handleOverlayClick_} ref="wrapper">
						<div class="modal">
							<div class="modal-content">
								{content}
							</div>
						</div>
					</div>
				}
			</div>
		);
	}
}

const STORE = {
	hideModal: Config.func(),
	modalIMap: Config.instanceOf(Map),
	onClose: Config.func()
};

Modal.PROPS = STORE;

const ConnectedModal = connect(
	state => (
		{
			modalIMap: state.get('modals')
		}
	),
	{hideModal}
)(Modal);

ConnectedModal.Body = Body;
ConnectedModal.Footer = Footer;
ConnectedModal.Header = Header;

export default ConnectedModal;