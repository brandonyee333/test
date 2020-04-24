import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import Button from '../Button';
import Checkbox from '../Checkbox';
import FileUploader, {IMAGE_FILE_TYPES} from '../../lib/FileUploader';
import Icon from '../Icon';
import LoopConstants from '../../lib/loop-constants';
import {addAlert, alertTypes} from '../../actions/alerts';
import {getPayloadType} from '../../lib/util';

class Tab extends Component {
	render() {
		const {
			children,
			iconName,
			onClick,
			onClose,
			selected,
			title
		} = this.props;

		return (
			<div class={getCN('tab', {selected})}>
				<div data-tooltip onClick={onClick} title={title}>
					{children &&
						children
					}

					{iconName &&
						<Icon name={iconName} />
					}
				</div>

				{onClose &&
					<Icon
						elementClasses="exit-tab"
						name="close-short"
						onClick={onClose}
						size="small"
					/>
				}
			</div>
		);
	}
}

Tab.PROPS = {
	iconName: Config.string(),
	onClick: Config.func(),
	onClose: Config.func(),
	selected: Config.bool(),
	title: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	)
};

class Toolbar extends Component {
	created() {
		bindAll(
			this,
			'changePostTypeLink_',
			'changePostTypeText_',
			'handleError_',
			'handleFileUploaderChange_',
			'handleImageUpload_',
			'toggleAnnouncement_',
			'togglePrivatePosts_'
		);

		this._uploader = new FileUploader(
			{
				fileTypes: IMAGE_FILE_TYPES,
				multiple: true,
				onChange: this.handleFileUploaderChange_,
				onError: this.handleError_,
				uploadURL: `${LoopConstants.urls.feed}/upload.json`
			}
		).render();
	}

	disposed() {
		if (this._uploader && this._uploader.destroy) {
			this._uploader.destroy();
		}
	}

	addFiles(files) {
		this._uploader.addFiles(files);
	}

	changePostTypeLink_() {
		this.props.onTypeChange('link');
	}

	changePostTypeText_() {
		this.props.onTypeChange('text');
	}

	handleError_(message) {
		this.props.addAlert(
			{
				alertType: alertTypes.ERROR,
				message
			}
		);
	}

	handleFileUploaderChange_(image) {
		let {imageData} = this.props;

		if (image.progress === 0) {
			imageData = [...imageData, image];
		}
		else {
			imageData = imageData.map(
				item => {
					let retVal = item;

					if (item._id === image._id) {
						retVal = {...item, ...image};
					}

					return retVal;
				}
			);
		}

		this.props.onImageDataChange(imageData);
	}

	handleImageUpload_() {
		this._uploader.openDialog(true);
	}

	toggleAnnouncement_() {
		const {announcement, forceAnnouncement} = this.props;

		if (!forceAnnouncement) {
			this.props.onAnnouncementChange(!announcement);
		}
	}

	togglePrivatePosts_() {
		const {forcePrivate, onPrivatePostToggle, privateAssetEntrySet} = this.props;

		if (!forcePrivate) {
			onPrivatePostToggle(!privateAssetEntrySet);
		}
	}

	render() {
		const {
			announcement,
			disableSubmit,
			forceAnnouncement,
			forcePrivate,
			onPreview,
			onSubmit,
			privateAssetEntrySet,
			showPrivateToggle,
			submitButtonTitle,
			type
		} = this.props;

		const checkboxClassNames = getCN(
			'private-post-checkbox',
			{
				disabled: forcePrivate
			}
		);

		const typeIsImage = type === getPayloadType('image');
		const typeIsLink = type === getPayloadType('link');
		const typeIsText = type === getPayloadType('text');

		return (
			<div class="creator-toolbar-container">
				{(typeIsText || typeIsImage) &&
					<Tab
						key="imageTab"
						onClose={this.changePostTypeText_}
						selected={typeIsImage}
						title={Liferay.Language.get('add-photos')}
					>
						<Icon name="camera" onClick={this.handleImageUpload_} />
					</Tab>
				}

				{(typeIsText || typeIsLink) &&
					<Tab
						iconName="link"
						key="linkTab"
						onClick={this.changePostTypeLink_}
						onClose={this.changePostTypeText_}
						selected={typeIsLink}
						title={Liferay.Language.get('add-link')}
					/>
				}

				{LoopConstants.currentPerson.permissionAddAnnouncement &&
					<Tab
						elementClasses="announcement-toggle"
						iconName="bullhorn"
						key="announcementTab"
						onClick={this.toggleAnnouncement_}
						selected={announcement}
						title={forceAnnouncement ? Liferay.Language.get('you-can-only-post-announcements-on-the-announcements-tab') : Liferay.Language.get('post-as-an-announcement')}
					/>
				}

				<div class="spacer" />

				<Tab
					iconName="eye"
					key="previewTab"
					onClick={onPreview}
					title={Liferay.Language.get('preview-post')}
				/>

				{showPrivateToggle &&
					<Checkbox
						checked={privateAssetEntrySet}
						data-tooltip
						elementClasses={checkboxClassNames}
						label={Liferay.Language.get('private')}
						onClick={this.togglePrivatePosts_}
						title={forcePrivate ? Liferay.Language.get('you-can-only-post-private-posts-on-the-private-posts-tab') : Liferay.Language.get('share-privately-with-the-people-groups-and-topics-that-are-mentioned')}
					/>
				}

				<Button disabled={disableSubmit} key="submit" onClick={onSubmit} role="primary">{submitButtonTitle}</Button>
			</div>
		);
	}
}

const STORE = {
	addAlert: Config.func()
};

Toolbar.PROPS = {
	...STORE,
	announcement: Config.bool(),
	disableSubmit: Config.bool(),
	forceAnnouncement: Config.bool(),
	forcePrivate: Config.bool(),
	imageData: Config.array(),
	onAnnouncementChange: Config.func(),
	onImageDataChange: Config.func(),
	onPreview: Config.func(),
	onPrivatePostToggle: Config.func(),
	onSubmit: Config.func(),
	onTypeChange: Config.func(),
	privateAssetEntrySet: Config.bool(),
	showPrivateToggle: Config.bool().value(true),
	submitButtonTitle: Config.string(),
	type: Config.string()
};

export default connect(
	null,
	{
		addAlert
	}
)(Toolbar);