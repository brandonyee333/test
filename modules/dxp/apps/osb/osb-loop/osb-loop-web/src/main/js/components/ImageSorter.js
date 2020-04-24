import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {Drag, DragDrop} from 'metal-drag-drop';
import {noop} from 'lodash';

import Icon from './Icon';

class SortableImage extends Component {
	created() {
		this.handleImageRemove_ = this.handleImageRemove_.bind(this);
	}

	handleImageRemove_() {
		const {image, onRemove} = this.props;

		if (onRemove) {
			onRemove(image.name);
		}
	}

	render() {
		const {display, image, index} = this.props;

		const {
			completed,
			imageURL_web,
			progress,
			response
		} = image;

		let {url} = image;

		let loading = !completed;

		const progressBarStyle = {
			opacity: loading ? 1 : 0,
			width: `${progress}%`
		};

		if (!url && imageURL_web) {
			url = imageURL_web;

			loading = false;
		}
		else if (response) {
			url = JSON.parse(response).data.imageURL_web;
		}

		const classNames = getCN(
			'sortable-image-container',
			{
				[`sortable-image-${display}`]: display,
				loading
			}
		);

		return (
			<div class={classNames} data-index={index} draggable="true">
				<img class="image" src={url} />

				<div
					class="cancel post"
					data-tooltip
					onClick={this.handleImageRemove_}
					title={Liferay.Language.get('remove-photo')}
				>
					<Icon name="close-long" size="small" />
				</div>

				<div class="progress-bar" style={progressBarStyle} />
			</div>
		);
	}
}

SortableImage.PROPS = {
	display: Config.oneOf(['mini']),
	image: Config.object(),
	index: Config.number(),
	onRemove: Config.func()
};

class ImageSorter extends Component {
	created() {
		this.handleRemove_ = this.handleRemove_.bind(this);

		this._dragDrop = null;
	}

	rendered() {
		if (!this._dragDrop && this.props.images.length > 1) {
			const {element} = this;

			const dragDrop = new DragDrop(
				{
					axis: 'y',
					constrain: element,
					container: element,
					minimumDragDistance: 0,
					sources: '.sortable-image-container',
					targets: '.sortable-image-container',
					useShim: false
				}
			);

			dragDrop.on(
				DragDrop.Events.TARGET_ENTER,
				(data, event) => {
					event.preventDefault();

					const sourceIndex = data.source ? data.source.getAttribute('data-index') : null;
					const targetIndex = data.target ? data.target.getAttribute('data-index') : null;

					if (targetIndex) {
						data.placeholder.style.top = '';

						this.handleReorder_(sourceIndex, targetIndex);
					}
				}
			);

			dragDrop.on(
				Drag.Events.START,
				(data, event) => {
					this.state.dragging_ = true;
				}
			);

			dragDrop.on(
				Drag.Events.DRAG,
				(data, event) => {
					event.preventDefault();
				}
			);

			dragDrop.on(
				DragDrop.Events.END,
				(data, event) => {
					event.preventDefault();

					this.state.dragging_ = false;
				}
			);

			this._dragDrop = dragDrop;
		}
	}

	detached() {
		if (this._dragDrop) {
			this._dragDrop.dispose();
		}
	}

	handleRemove_(name) {
		const {images, onChange} = this.props;

		onChange(
			images.filter(item => item.name !== name)
		);
	}

	handleReorder_(currentIndex, hoverIndex) {
		const {images} = this.props;

		const dragImage = images[currentIndex];

		images.splice(currentIndex, 1);

		images.splice(hoverIndex, 0, dragImage);

		this.props.onChange(images);
	}

	render() {
		return (
			<div class="image-sorter-container">
				{
					this.props.images.map(
						(image, i) => (
							<SortableImage
								{...this.otherProps()}
								elementClasses={this.dragging_ ? 'shrink' : ''}
								image={image}
								index={i}
								key={image._id}
								onRemove={this.handleRemove_}
							/>
						)
					)
				}
			</div>
		);
	}
}

ImageSorter.PROPS = {
	images: Config.array().value([]),
	onChange: Config.func().value(noop)
};

ImageSorter.STATE = {
	dragging_: Config.value(false)
};

export default ImageSorter;