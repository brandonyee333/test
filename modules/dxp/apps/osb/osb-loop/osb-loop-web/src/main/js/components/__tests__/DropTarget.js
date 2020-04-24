import DropTarget, {DRAG_TYPES} from '../DropTarget';

const mockEventObj = {
	preventDefault() {}
};

describe(
	'DropTarget',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new DropTarget();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call handleDragEnd_',
			() => {
				component = new DropTarget();

				const spy = jest.fn();

				component.handleDragEnd_ = spy;

				component.handleDocumentDrop_(mockEventObj);

				expect(spy).toBeCalled();
			}
		);

		it(
			'should reset state if currently dragging',
			() => {
				component = new DropTarget();

				component.dragging_ = true;
				component.hoverOver_ = true;

				component.handleDragEnd_();

				expect(component.state.dragging_).toBe(false);
				expect(component.state.hoverOver_).toBe(false);
			}
		);

		it(
			'should set dragging to true if matches file type',
			() => {
				component = new DropTarget(
					{
						targetType: DRAG_TYPES.FILE
					}
				);

				component.handleDragEnter_(
					{
						dataTransfer: {
							types: ['text/html']
						}
					}
				);

				expect(component.state.dragging_).toBe(false);

				component.handleDragEnter_(
					{
						dataTransfer: {
							types: ['Files']
						}
					}
				);

				expect(component.state.dragging_).toBe(true);
			}
		);

		it(
			'should call onDrop and handleDragEnd_',
			() => {
				const spy1 = jest.fn();
				const spy2 = jest.fn();

				component = new DropTarget(
					{
						onDrop: spy1
					}
				);

				component.handleDragEnd_ = spy2;
				component.state.dragging_ = true;

				component.handleTargetDrop_(mockEventObj);

				expect(spy1).toBeCalled();
				expect(spy2).toBeCalled();
			}
		);

		it(
			'should set hoverOver_ to true if dragging',
			() => {
				component = new DropTarget();

				component.handleTargetEnter_(mockEventObj);

				expect(component.state.hoverOver_).toBe(false);

				component.state.dragging_ = true;

				component.handleTargetEnter_(mockEventObj);

				expect(component.state.hoverOver_).toBe(true);
			}
		);

		it(
			'should set hoverOver_ to false',
			() => {
				component = new DropTarget();

				component.state.dragging_ = true;
				component.state.hoverOver_ = true;

				component.handleTargetLeave_();

				expect(component.state.hoverOver_).toBe(false);
			}
		);

		it(
			'should call preventDefault if dragging',
			() => {
				const spy = jest.fn();

				component = new DropTarget();

				component.state.dragging_ = true;

				component.handlePreventDefault_(
					{
						preventDefault: spy
					}
				);

				expect(spy).toBeCalled();
			}
		);
	}
);