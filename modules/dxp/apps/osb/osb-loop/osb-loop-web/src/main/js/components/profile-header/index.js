import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, keys} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import AffixHeader from '../AffixHeader';
import Button from '../Button';
import Card from '../card';
import EditableAvatar from './EditableAvatar';
import FileUploader, {IMAGE_FILE_TYPES} from '../../lib/FileUploader';
import FollowButton from '../FollowButton';
import FollowersCount from './FollowersCount';
import GroupsCount from './GroupsCount';
import Icon from '../Icon';
import LoopConstants from '../../lib/loop-constants';
import MembersCount from './MembersCount';
import ModalLink from '../ModalLink';
import Overlay from '../Overlay';
import ParentEntity from './ParentEntity';
import Spinner from '../Spinner';
import {addAlert, alertTypes} from '../../actions/alerts';
import {classNameIdToSchema, getController} from '../../lib/util';
import {getLocationSubtypeLabel, getTypeLabel} from '../../lib/lang-util';
import {getRel} from '../../lib/selectors';
import {getTypeName, isCurrentPerson, isJobTitle} from '../../lib/util';
import {overlayTypes} from '../../actions/overlays';
import {setCoverImage} from '../../actions/images';

class ProfileHeader extends Component {
	created() {
		bindAll(
			this,
			'clearPendingImage_',
			'handleError_',
			'handlePendingImage_',
			'handleScroll_',
			'setCoverImage_',
			'toggleUploader_'
		);

		const {classNameId, id} = this.props;

		this._uploader = new FileUploader(
			{
				fileTypes: IMAGE_FILE_TYPES,
				multiple: false,
				onChange: this.handlePendingImage_,
				onError: this.handleError_,
				params: {id},
				uploadURL: `${LoopConstants.urls.home}/${getController(classNameId)}/uploadCoverImage.json`
			}
		).render();
	}

	attached() {
		window.addEventListener('scroll', this.handleScroll_);
	}

	detached() {
		window.removeEventListener('scroll', this.handleScroll_);
	}

	clearPendingImage_() {
		this.setState(
			{
				loadingCoverImage_: false,
				pendingImageData_: null
			}
		);
	}

	handleError_(message) {
		this.props.addAlert(
			{
				alertType: alertTypes.ERROR,
				message
			}
		);

		this.state.loadingCoverImage_ = false;
	}

	handlePendingImage_(image) {
		if (image.completed) {
			this.setState(
				{
					loadingCoverImage_: false,
					pendingImageData_: JSON.parse(image.response).data
				}
			);
		}
		else {
			this.state.loadingCoverImage_ = true;
		}
	}

	handleScroll_() {
		const elementPosition = this.element.getBoundingClientRect().bottom;

		const lastVisiblePosition = elementPosition - 60;

		let showAffixHeader = false;

		if (lastVisiblePosition < 0) {
			showAffixHeader = true;
		}

		this.state.showAffixHeader_ = showAffixHeader;
	}

	setCoverImage_() {
		const {addAlert, entityIMap, setCoverImage} = this.props;

		this.state.loadingCoverImage_ = true;

		setCoverImage(
			entityIMap.get('entityClassNameId', -1),
			entityIMap.get('entityClassPK', -1),
			this.state.pendingImageData_
		).then(
			() => {
				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: Liferay.Language.get('cover-photo-has-been-updated')
					}
				);

				this.clearPendingImage_();
			}
		).catch(
			() => {
				this.handleError_(Liferay.Language.get('unable-to-set-cover-photo'));

				this.clearPendingImage_();
			}
		);
	}

	toggleUploader_() {
		this._uploader.openDialog();
	}

	getSecondaryInfo_(entity) {
		const {
			displayURL,
			entityClassNameId,
			entityClassPK,
			type
		} = entity;

		const typeName = getTypeName(type);

		let retVal;

		if (typeName !== 'topic') {
			let SecondaryInfo;

			if (typeName === 'person') {
				SecondaryInfo = GroupsCount;
			}
			else {
				SecondaryInfo = MembersCount;
			}

			retVal = <SecondaryInfo classNameId={entityClassNameId} displayURL={displayURL} id={entityClassPK} />;
		}

		return retVal;
	}

	getMenu_(entity) {
		const {
			entityClassPK,
			name,
			permissionDelete,
			permissionEdit,
			permissionSetChildLoopDivisions,
			status,
			type
		} = entity;

		const typeName = getTypeName(type);

		let retVal;

		if (typeName === 'topic') {
			retVal = (
				<Overlay offset={12} overlayProps={{id: entityClassPK}} overlayType={overlayTypes.TOPIC_PROFILE_MENU}>
					<Button display="secondary">
						<Icon name="ellipsis" />
					</Button>
				</Overlay>
			);
		}
		else if (typeName === 'person' && (permissionEdit || permissionDelete)) {
			retVal = (
				<Overlay offset={12} overlayProps={{id: entityClassPK}} overlayType={overlayTypes.PERSON_PROFILE_MENU}>
					<Button display="secondary">
						<Icon name="ellipsis" />
					</Button>
				</Overlay>
			);
		}
		else if (permissionSetChildLoopDivisions || permissionEdit) {
			retVal = (
				<Overlay offset={12} overlayProps={{id: entityClassPK}} overlayType={overlayTypes.DIVISION_PROFILE_MENU}>
					<Button display="secondary">
						<Icon name="ellipsis" />
					</Button>
				</Overlay>
			);
		}
		else if (isJobTitle(entity)) {
			retVal = (
				<Overlay offset={12} overlayProps={{id: entityClassPK, name, status}} overlayType={overlayTypes.JOB_TITLE_PROFILE_MENU}>
					<Button display="secondary">
						<Icon name="ellipsis" />
					</Button>
				</Overlay>
			);
		}

		return retVal;
	}

	getControls_() {
		const {loadingCoverImage_, pendingImageData_} = this.state;

		let retVal = (
			<Icon
				display="secondary"
				name="camera"
				onClick={this.toggleUploader_}
				title={Liferay.Language.get('update-cover-photo')}
			/>
		);

		if (loadingCoverImage_) {
			retVal = <Spinner />;
		}
		else if (pendingImageData_) {
			retVal = (
				<span>
					<Icon
						display="success"
						multiplier={2}
						name="check"
						onClick={this.setCoverImage_}
						size="small"
						title={Liferay.Language.get('confirm')}
					/>

					<Icon
						display="danger"
						multiplier={2}
						name="close-short"
						onClick={this.clearPendingImage_}
						size="small"
						title={Liferay.Language.get('cancel')}
					/>
				</span>
			);
		}

		return (
			<span class="controls">
				{retVal}
			</span>
		);
	}

	getDescription_(entity) {
		const {
			jobTitle,
			locationName,
			locationSubtype,
			subtype,
			type
		} = entity;

		const typeName = getTypeName(type);

		let location = locationName;

		if (locationSubtype) {
			location += ` (${getLocationSubtypeLabel(locationSubtype)})`;
		}

		let retVal;

		if (typeName === 'person') {
			retVal = `${jobTitle} ${jobTitle && locationName ? '•' : ''} ${location}`;
		}
		else if (typeName === 'location') {
			retVal = `${getTypeLabel(type)} • ${getLocationSubtypeLabel(subtype)}`;
		}
		else {
			retVal = getTypeLabel(type);
		}

		return retVal;
	}

	render() {
		const {
			props: {
				entityIMap,
				parentEntityIMap,
				secondaryButtonConfig
			},
			state: {
				loadingCoverImage_,
				pendingImageData_,
				showAffixHeader_
			}
		} = this;

		const entity = entityIMap.toJS();

		const {
			coverImageData = {},
			displayURL,
			entityClassNameId,
			entityClassPK,
			inactive,
			name,
			permissionEdit,
			status,
			type
		} = entity;

		let coverImage = coverImageData.imageURL_web;

		if (pendingImageData_) {
			coverImage = pendingImageData_.imageURL_raw;
		}

		const classes = getCN(
			'profile-header-container',
			{
				active: pendingImageData_ || loadingCoverImage_,
				disabled: inactive || (status === LoopConstants.status.inactive),
				image: coverImage
			}
		);

		return (
			<Card elementClasses={classes}>
				<span class="background-image" style={coverImage ? {backgroundImage: `url(${coverImage})`} : null} />

				<AffixHeader active={showAffixHeader_} entity={entity} />

				<div class="entity-toolbar-wrapper">
					<div class="entity-info-wrapper">
						{!!keys(entity).length &&
							<EditableAvatar disableLink={true} entity={entity} size="large" />
						}

						<div class="details">
							<div class="name">{name}</div>

							<div class="description">{this.getDescription_(entity)}</div>

							<div>
								<FollowersCount classNameId={entityClassNameId} displayURL={displayURL} id={entityClassPK} />

								<span class="secondary-info">
									{this.getSecondaryInfo_(entity)}
								</span>
							</div>
						</div>
					</div>

					<div class="actions">
						{!isCurrentPerson(entityClassPK) && !isJobTitle(entity) && !inactive &&
							<FollowButton
								classNameId={entityClassNameId}
								displayURL={displayURL}
								followingName={name}
								id={entityClassPK}
							/>
						}

						{secondaryButtonConfig &&
							<ModalLink config={secondaryButtonConfig.modalConfig} data-tooltip title={secondaryButtonConfig.title}>
								<Icon name={secondaryButtonConfig.iconName} />
							</ModalLink>
						}

						{this.getMenu_(entity)}
					</div>
				</div>

				{permissionEdit &&
					this.getControls_()
				}

				{parentEntityIMap && type !== LoopConstants.types.root &&
					<ParentEntity dark={!!coverImage} entityIMap={parentEntityIMap} />
				}
			</Card>
		);
	}
}

const STORE = {
	entityIMap: Config.instanceOf(Map),
	setCoverImage: Config.func()
};

ProfileHeader.PROPS = {
	...STORE,
	classNameId: Config.number().required(),
	id: Config.number(),
	parentEntityIMap: Config.instanceOf(Map),
	secondaryButtonConfig: Config.shapeOf(
		{
			iconName: Config.string(),
			modalConfig: Config.object(),
			title: Config.string()
		}
	)
};

ProfileHeader.STATE = {
	loadingCoverImage_: Config.value(false),
	pendingImageData_: Config.value(null),
	showAffixHeader_: Config.value(false)
};

export default connect(
	(state, {classNameId, id}) => (
		{
			entityIMap: getRel(classNameIdToSchema(classNameId), state, id, Map())
		}
	),
	{
		addAlert,
		setCoverImage
	}
)(ProfileHeader);