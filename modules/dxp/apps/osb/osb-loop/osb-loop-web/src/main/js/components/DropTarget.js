import Component, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';
import {bindAll, includes} from 'lodash';

export const DRAG_TYPES = {
	FILE: 'Files',
	HTML: 'text/html',
	TEXT: 'text/plain',
	URL: 'text/uri-list'
};

class DropTarget extends Component {
	created() {
		bindAll(
			this,
			'handleDocumentDrop_',
			'handleDragEnd_',
			'handleDragEnter_',
			'handlePreventDefault_',
			'handleTargetDrop_',
			'handleTargetEnter_',
			'handleTargetLeave_'
		);
	}

	attached() {
		const {container} = this.props;

		container.addEventListener('dragend', this.handleDragEnd_);
		container.addEventListener('dragenter', this.handleDragEnter_);
		container.addEventListener('dragover', this.handlePreventDefault_);
		container.addEventListener('drop', this.handleDocumentDrop_);
		container.addEventListener('mouseleave', this.handleDragEnd_);
	}

	detached() {
		const {container} = this.props;

		container.removeEventListener('dragend', this.handleDragEnd_);
		container.removeEventListener('dragenter', this.handleDragEnter_);
		container.removeEventListener('dragover', this.handlePreventDefault_);
		container.removeEventListener('drop', this.handleDocumentDrop_);
		container.removeEventListener('mouseleave', this.handleDragEnd_);
	}

	handleDocumentDrop_(event) {
		event.preventDefault();

		this.handleDragEnd_();
	}

	handleDragEnd_() {
		if (this.state.dragging_) {
			this.setState(
				{
					dragging_: false,
					hoverOver_: false
				}
			);
		}
	}

	handleDragEnter_(event) {
		const {types} = event.dataTransfer;

		if (includes(types, this.props.targetType)) {
			this.state.dragging_ = true;
		}
	}

	handleTargetDrop_(event) {
		event.preventDefault();

		this.props.onDrop(event);

		this.handleDragEnd_();
	}

	handleTargetEnter_(event) {
		if (this.state.dragging_) {
			this.state.hoverOver_ = true;
		}
	}

	handleTargetLeave_() {
		if (this.state.dragging_) {
			this.state.hoverOver_ = false;
		}
	}

	handlePreventDefault_(event) {
		if (this.state.dragging_) {
			event.preventDefault();
		}
	}

	render() {
		const {dragging_, hoverOver_} = this.state;

		const {children, dragMessage, hoverMessage} = this.props;

		return (
			<div
				class="drop-target-container"
				onDragEnter={this.handleTargetEnter_}
				onDragLeave={this.handleTargetLeave_}
				onDragOver={this.handlePreventDefault_}
				onDrop={this.handleTargetDrop_}
			>
				<Transition name="transition-fade-in">
					{dragging_ &&
						<div class="drop-zone">
							{hoverOver_ ? hoverMessage : dragMessage}
						</div>
					}
				</Transition>

				{children}
			</div>
		);
	}
}

DropTarget.PROPS = {
	container: Config.object().value(document),
	dragMessage: Config.any(),
	hoverMessage: Config.any(),
	onDrop: Config.func(),
	targetType: Config.oneOf(Object.values(DRAG_TYPES))
};

DropTarget.STATE = {
	dragging_: Config.value(false),
	hoverOver_: Config.value(false)
};

export default DropTarget;