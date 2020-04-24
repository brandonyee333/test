import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, take} from 'lodash';

import Icon from './Icon';
import IconLabel from './IconLabel';
import Spinner from './Spinner';
import {ARROW_LEFT, ARROW_RIGHT, ESCAPE} from '../lib/key-constants';
import {lang} from '../lib/lang-util';

export class Image extends Component {
	created() {
		bindAll(
			this,
			'handleImageError_',
			'handleImageLoad_'
		);
	}

	handleImageError_() {
		this.setState(
			{
				error_: true,
				loading_: false
			}
		);
	}

	handleImageLoad_() {
		this.setState(
			{
				error_: false,
				loading_: false
			}
		);
	}

	toggleHover(hover) {
		return () => {
			this.state.hover_ = hover;
		};
	}

	render() {
		const {
			props: {fullScreen, hideBackground, image},
			state: {error_, hover_, loading_}
		} = this;

		const gif = image.mimeType === 'image/gif';

		const gifPreview = gif && !fullScreen;

		const imgUrl = gif && hover_ || fullScreen ? image.imageURL_full : image.imageURL_web;

		return (
			<div
				{...this.otherProps()}
				class={
					getCN(
						'image-container',
						{'no-hover': gif}
					)
				}
				onMouseEnter={gifPreview ? this.toggleHover(true) : null}
				onMouseLeave={gifPreview ? this.toggleHover(false) : null}
				style={error_ || fullScreen || hideBackground || loading_ ? '' : `background-image: url(${imgUrl})`}
			>
				<img
					class={
						getCN(
							'image',
							{hide: !hideBackground && !fullScreen || loading_}
						)
					}
					onError={this.handleImageError_}
					onLoad={this.handleImageLoad_}
					src={imgUrl}
				/>

				{error_ &&
					<div class="error-message" key="errorMessage">
						<IconLabel
							display="danger"
							label={Liferay.Language.get('there-was-an-error-loading-this-image')}
							name="alert"
							size="small"
						/>
					</div>
				}

				{loading_ && !error_ &&
					<Spinner />
				}

				{gifPreview && !loading_ &&
					<span class="gif">{'GIF'}</span>
				}
			</div>
		);
	}
}

Image.PROPS = {
	fullScreen: Config.bool().value(false),
	hideBackground: Config.bool().value(false),
	image: Config.object()
};

Image.STATE = {
	error_: Config.value(false),
	hover_: Config.value(false),
	loading_: Config.value(true)
};

class ImageViewer extends Component {
	created() {
		bindAll(
			this,
			'handleKeyUp_',
			'handleMaximizeClick_',
			'handleMinimizeClick_'
		);
	}

	detached() {
		document.removeEventListener('keyup', this.handleKeyUp_);
	}

	displayHorizontally() {
		const {items} = this.props;

		let retVal;

		if (items.length === 2) {
			const height = items[0].height_web + items[1].height_web;
			const width = items[0].height_web + items[1].width_web;

			retVal = height < width;
		}

		return retVal;
	}

	getMaxWidthPercent() {
		const {height_web, width_web} = this.props.items[0];

		const percentDiff = ((width_web / height_web) - 1) * 100;

		return percentDiff > 50 ? percentDiff : '';
	}

	getNextIndex_(val) {
		const total = this.props.items.length;

		val += this.state.currentIndex_;

		if (val < 0) {
			val += total;
		}

		return val % total;
	}

	handleKeyUp_({keyCode}) {
		const hasMultipleItems = this.props.items.length > 1;

		if (hasMultipleItems && keyCode === ARROW_LEFT) {
			this.handleImageNavigate_(-1)();
		}
		else if (hasMultipleItems && keyCode === ARROW_RIGHT) {
			this.handleImageNavigate_(1)();
		}
		else if (keyCode === ESCAPE) {
			this.minimize();
		}
	}

	handleMaximizeClick_(index) {
		return () => {
			this.setState(
				{
					currentIndex_: index,
					maximized_: true
				}
			);

			document.addEventListener('keyup', this.handleKeyUp_);
		};
	}

	handleMinimizeClick_({delegateTarget, target}) {
		if (target === delegateTarget) {
			this.minimize();
		}
	}

	handleImageNavigate_(increment) {
		return () => {
			this.state.currentIndex_ = this.getNextIndex_(increment);
		};
	}

	minimize() {
		document.removeEventListener('keyup', this.handleKeyUp_);

		this.state.maximized_ = false;
	}

	syncItems(newVal, prevVal) {
		if ((newVal && newVal.length) !== (prevVal && prevVal.length)) {
			this.state.currentIndex_ = 0;
		}
	}

	render() {
		const {
			props: {items, small},
			state: {currentIndex_, maximized_}
		} = this;

		const {length} = items;

		const maxWidthPercent = this.getMaxWidthPercent();

		return (
			<div class={getCN('image-viewer-container', {small})}>
				<div class="grid" style={small && maxWidthPercent ? `max-width: ${maxWidthPercent}%;` : ''}>
					{
						take(items, 4).map(
							(image, i) => {
								const hasMoreImages = (i === 3 && length > 4);

								let retVal = (
									<Image
										elementClasses={
											getCN(
												{
													'grid-item': !hasMoreImages,
													horizontal: this.displayHorizontally()
												}
											)
										}
										hideBackground={small}
										image={image}
										key={hasMoreImages ? null : i}
										onClick={hasMoreImages ? null : this.handleMaximizeClick_(i)}
									/>
								);

								if (hasMoreImages) {
									retVal = (
										<div class="blur grid-item" key={i} onClick={this.handleMaximizeClick_(i)}>
											<span class="more" data-tooltip title={Liferay.Language.get('see-more')}>
												{`+ ${length - 4}`}
											</span>

											{retVal}
										</div>
									);
								}

								return retVal;
							}
						)
					}
				</div>

				{maximized_ &&
					<div class="maximized" onClick={this.handleMinimizeClick_}>
						<Image fullScreen={true} image={items[currentIndex_]} key={items[currentIndex_].imageURL_full} />

						<span class="viewer-close">
							<Icon
								display="secondary"
								multiplier={2}
								name="close-long"
								onClick={this.handleMinimizeClick_}
								ref="icon4"
								size="small"
							/>
						</span>

						{length > 1 &&
							<div class="controls">
								<div class="viewer-control right" onClick={this.handleImageNavigate_(1)}>
									<Icon
										display="secondary"
										multiplier={2}
										name="arrow-right-rod"
										ref="icon1"
										size="small"
									/>
								</div>

								<div class="viewer-control left" onClick={this.handleImageNavigate_(-1)}>
									<Icon
										display="secondary"
										multiplier={2}
										name="arrow-left-rod"
										ref="icon2"
										size="small"
									/>
								</div>

								<div class="viewer-info">
									{lang(Liferay.Language.get('x-of-x'), [this.state.currentIndex_ + 1, length])}
								</div>
							</div>
						}
					</div>
				}
			</div>
		);
	}
}

ImageViewer.PROPS = {
	items: Config.array().value([{}]),
	small: Config.bool().value(false)
};

ImageViewer.STATE = {
	currentIndex_: Config.value(0),
	maximized_: Config.value(false)
};

export default ImageViewer;