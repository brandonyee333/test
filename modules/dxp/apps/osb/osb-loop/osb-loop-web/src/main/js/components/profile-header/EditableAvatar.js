import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import Avatar from '../Avatar';
import FileUploader, {IMAGE_FILE_TYPES} from '../../lib/FileUploader';
import Icon from '../Icon';
import LoopConstants from '../../lib/loop-constants';
import Spinner from '../Spinner';
import {addAlert, alertTypes} from '../../actions/alerts';
import {getController} from '../../lib/util';
import {hideModal, modalTypes, showModal} from '../../actions/modals';
import {setProfileImage} from '../../actions/images';

class EditableAvatar extends Component {
	created() {
		bindAll(
			this,
			'handleError_',
			'handleImageUpload_',
			'setProfileImage_',
			'toggleUploader_'
		);

		const {entityClassNameId, entityClassPK} = this.props.entity;

		this._uploader = new FileUploader(
			{
				fileTypes: IMAGE_FILE_TYPES,
				multiple: false,
				onChange: this.handleImageUpload_,
				onError: this.handleError_,
				params: {
					id: entityClassPK
				},
				uploadURL: `${LoopConstants.urls.home}/${getController(entityClassNameId)}/uploadProfileImage.json`
			}
		).render();
	}

	handleError_(message) {
		this.props.addAlert(
			{
				alertType: alertTypes.ERROR,
				message
			}
		);

		this.state.loading_ = false;
	}

	handleImageUpload_(image) {
		if (image.completed) {
			const onClose = () => {
				this.state.loading_ = false;
			};

			this.props.showModal(
				{
					hideOnBlur: false,
					modalProps: {
						imageData: JSON.parse(image.response).data,
						onClose,
						onSubmit: this.setProfileImage_
					},
					modalType: modalTypes.CROP_IMAGE,
					onClose
				}
			);
		}
		else {
			this.state.loading_ = true;
		}
	}

	setProfileImage_(imagePayload) {
		const {
			addAlert,
			entity,
			hideModal,
			setProfileImage
		} = this.props;

		const {entityClassNameId, entityClassPK} = entity;

		setProfileImage(
			entityClassNameId,
			entityClassPK,
			imagePayload
		).then(
			() => {
				hideModal();

				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: Liferay.Language.get('profile-photo-has-been-updated')
					}
				);

				this.state.loading_ = false;
			}
		).catch(
			() => {
				hideModal();

				this.handleError_(Liferay.Language.get('unable-to-set-profile-photo'));
			}
		);
	}

	toggleUploader_() {
		this._uploader.openDialog();
	}

	render() {
		const {
			props: {entity},
			state: {loading_}
		} = this;

		const {entityClassNameId, permissionEdit} = entity;

		const classes = getCN(
			'editable-avatar-container',
			{
				active: loading_
			}
		);

		return (
			<div class={classes}>

				{(entityClassNameId === LoopConstants.classNameIds.topics || permissionEdit) &&
					<span class="controls">
						{!loading_ &&
							<Icon
								display="secondary"
								name="camera"
								onClick={this.toggleUploader_}
								title={Liferay.Language.get('update-profile-photo')}
							/>
						}

						{loading_ &&
							<Spinner />
						}
					</span>
				}

				<Avatar entity={entity} {...this.otherProps()} />
			</div>
		);
	}
}

const STORE = {
	hideModal: Config.func(),
	setProfileImage: Config.func(),
	showModal: Config.func()
};

EditableAvatar.PROPS = {
	...STORE,
	entity: Config.object()
};

EditableAvatar.STATE = {
	loading_: Config.value(false)
};

export default connect(
	null,
	{
		addAlert,
		hideModal,
		setProfileImage,
		showModal
	}
)(EditableAvatar);