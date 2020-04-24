import Component, {Config} from 'metal-jsx';

import DropTarget, {DRAG_TYPES} from '../DropTarget';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

const style = {
	border: '1px solid grey',
	height: '200px',
	width: '200px'
};

class DropTargetExample extends Component {
	handleAlert_(event) {
		alert('Dropped!');
	}

	render() {
		return (
			<DropTarget
				dragMessage="dragging..."
				hoverMessage="hovering..."
				onDrop={this.handleAlert_}
				targetType={this.props.type}
			>
				<div class="text-center" style={style}>{'Drag Here'}</div>
			</DropTarget>
		);
	}
}

DropTargetExample.PROPS = {
	type: Config.string()
};

class DropTargetKit extends Component {
	render() {
		return (
			<Kit header="DropTarget">
				<ElementContainer header="Drag File">
					<DropTargetExample type={DRAG_TYPES.FILE} />
				</ElementContainer>

				<ElementContainer header="Drag Text">
					<DropTargetExample type={DRAG_TYPES.TEXT} />
				</ElementContainer>

				<ElementContainer header="Drag URL">
					<DropTargetExample type={DRAG_TYPES.URL} />
				</ElementContainer>

				<ElementContainer header="Drag HTML">
					<DropTargetExample type={DRAG_TYPES.HTML} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default DropTargetKit;