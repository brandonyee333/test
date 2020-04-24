import Component, {Config} from 'metal-jsx';
import {bindAll, isUndefined} from 'lodash';

import fetch from '../lib/fetch';
import Icon from './Icon';
import LinkPreview from './link-preview';
import Overlay from './Overlay';
import Spinner from './Spinner';
import {ENTER, TAB} from '../lib/key-constants';
import {getURL, isValidURL} from '../lib/util';
import {overlayTypes} from '../actions/overlays';

const API_URL = `${themeDisplay.getPortalURL()}/o/loop-url-metadata-scraper-web`;

class LinkPreviewEditor extends Component {
	created(props) {
		const {linkData} = this.props;

		if (linkData && linkData.imageURLs) {
			this.state.currentIndex_ = linkData.imageURLs.indexOf(linkData.imageURL);
		}

		bindAll(
			this,
			'handleHideError_',
			'handleInputChange_',
			'handleKeyDown_',
			'handleNextImage_',
			'handlePaste_',
			'handlePreviousImage_',
			'handleRemoveImage_',
			'handleRemovePreview_'
		);
	}

	attached() {
		const {inputValue, linkData} = this.props;

		if (inputValue && !linkData && isValidURL(inputValue)) {
			this.requestPreview_(inputValue);
		}
	}

	disposed() {
		if (this._request) {
			this._request.cancel();
		}
	}

	handleHideError_() {
		this.state.error_ = false;
	}

	handleImageChange_(index) {
		const {linkData, onDataChange} = this.props;

		this.state.currentIndex_ = index;

		onDataChange(
			{
				...linkData,
				imageURL: linkData.imageURLs[index]
			}
		);
	}

	handleInputChange_(event) {
		this.props.onInputChange(event.target.value);
	}

	handleKeyDown_(event) {
		const {keyCode} = event;

		const {value} = event.target;

		if (keyCode === ENTER || keyCode === TAB) {
			event.preventDefault();

			this.requestPreview_(value);
		}
	}

	handleNextImage_() {
		let {currentIndex_} = this.state;

		const totalImages = this.props.linkData.imageURLs.length;

		if (currentIndex_ === totalImages - 1) {
			currentIndex_ = 0;
		}
		else {
			currentIndex_++;
		}

		this.handleImageChange_(currentIndex_);
	}

	handlePaste_(event) {
		const clipboardTextData = event.clipboardData.getData('text');

		if (isValidURL(clipboardTextData)) {
			event.preventDefault();
		}

		this.requestPreview_(clipboardTextData);
	}

	handlePreviousImage_() {
		let {currentIndex_} = this.state;

		const totalImages = this.props.linkData.imageURLs.length;

		if (currentIndex_ === 0) {
			currentIndex_ = totalImages - 1;
		}
		else {
			currentIndex_--;
		}

		this.handleImageChange_(currentIndex_);
	}

	handleRemoveImage_() {
		this.props.onDataChange(
			{
				...this.props.linkData,
				imageURL: null
			}
		);
	}

	handleRemovePreview_() {
		this.props.onDataChange(null);
	}

	handleRequest_(requestURL, config) {
		const {onDataChange} = this.props;

		return fetch(requestURL, config).then(
			response => {
				if (!response.success) {
					throw new Error(response);
				}

				return response;
			}
		).then(
			response => {
				this.setState(
					{
						currentIndex_: 0,
						loading_: false
					}
				);

				onDataChange(this.processLinkData_(response));
			},
			() => {
				this.setState(
					{
						error_: true,
						loading_: false
					}
				);

				const linkData = this.processLinkData_();

				if (linkData) {
					onDataChange(linkData);
				}
			}
		);
	}

	processLinkData_(linkData) {
		const {currentIndex_} = this.state;

		const {inputValue} = this.props;

		if (linkData && linkData.imageURLs) {
			const previewImages = linkData.imageURLs;

			const imageURL = previewImages[currentIndex_];

			linkData = {
				...linkData,
				imageURL,
				multipleImages: previewImages.length > 1
			};
		}
		else if (isUndefined(linkData) && inputValue) {
			const url = getURL(inputValue);

			if (url) {
				const {host, pathname} = url;

				linkData = {
					shortURL: host + (pathname.length > 1 ? pathname : ''),
					title: host,
					url: url.toString()
				};
			}
		}

		return linkData;
	}

	renderControls_() {
		return (
			<Icon
				elementClasses="remove-preview"
				name="close-long"
				onClick={this.handleRemovePreview_}
				ref="removePreview"
				size="small"
				title={Liferay.Language.get('remove-link')}
			/>
		);
	}

	renderImageControls_(multipleImages) {
		return (
			<div class="edit-image">
				{multipleImages &&
					<div>
						<Icon
							elementClasses="previous-image"
							multiplier={2}
							name="arrow-left-rod"
							onClick={this.handlePreviousImage_}
							ref="nextImage"
							size="small"
							title={Liferay.Language.get('previous-image')}
						/>

						<Icon
							elementClasses="next-image"
							multiplier={2}
							name="arrow-right-rod"
							onClick={this.handleNextImage_}
							ref="prevImage"
							size="small"
							title={Liferay.Language.get('next-image')}
						/>
					</div>
				}

				<Icon
					elementClasses="remove-image"
					name="close-long"
					onClick={this.handleRemoveImage_}
					ref="removeImage"
					size="small"
					title={Liferay.Language.get('remove-image')}
				/>
			</div>
		);
	}

	renderInput_() {
		const {error_, loading_} = this.state;

		const {inputValue, linkData} = this.props;

		return (
			<div class="control-group control-group-inline link-input-wrapper">
				<Overlay
					active={error_}
					overlayProps={{
						message: Liferay.Language.get('could-not-generate-preview'),
						onHide: this.handleHideError_
					}}
					overlayType={overlayTypes.OVERLAY_ERROR_MESSAGE}
				>
					<label class="control-label">{Liferay.Language.get('link')}</label>
				</Overlay>

				{!loading_ &&
					<input
						class="field link-input"
						disabled={!!linkData}
						onChange={this.handleInputChange_}
						onKeyDown={this.handleKeyDown_}
						onPaste={this.handlePaste_}
						placeholder={Liferay.Language.get('type-or-paste-a-website-url-here')}
						ref="linkInput"
						type="text"
						value={inputValue}
					/>
				}

				{loading_ &&
					<span class="placeholder-wrapper">
						<span class="link-input placeholder">{inputValue}</span>

						<Spinner size="small" />
					</span>
				}
			</div>
		);
	}

	requestPreview_(url) {
		const {inputValue, onInputChange} = this.props;

		if (!inputValue) {
			onInputChange(url);
		}

		this.setState(
			{
				error_: false,
				loading_: true
			},
			() => {
				this._request = this.handleRequest_(
					API_URL,
					{
						data: {url}
					}
				);
			}
		);
	}

	syncInputValue(newVal, prevVal) {
		const {linkInput} = this.refs;

		if (linkInput !== document.activeElement && newVal !== prevVal && isValidURL(newVal)) {
			this.requestPreview_(newVal);
		}
	}

	render() {
		const {linkData} = this.props;

		return (
			<div class="link-preview-editor-container">
				{linkData &&
					<div class="link-preview-container">
						{linkData.imageURL &&
							<div class="image-wrapper">
								<LinkPreview.Image linkData={linkData} />

								{this.renderImageControls_(linkData.multipleImages)}
							</div>
						}

						<div class="content-wrapper">
							<LinkPreview.Content linkData={linkData} />

							{this.renderControls_()}
						</div>
					</div>
				}

				{this.renderInput_()}
			</div>
		);
	}
}

LinkPreviewEditor.PROPS = {
	inputValue: Config.string(),
	linkData: Config.object(),
	onDataChange: Config.func(),
	onInputChange: Config.func()
};

LinkPreviewEditor.STATE = {
	currentIndex_: Config.value(0),
	error_: Config.value(false),
	loading_: Config.value(false)
};

export default LinkPreviewEditor;