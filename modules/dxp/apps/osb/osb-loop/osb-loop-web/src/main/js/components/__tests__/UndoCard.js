import dom from 'metal-dom';

import UndoCard from '../UndoCard';

describe(
	'UndoCard',
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
				component = new UndoCard();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onClick on dismiss',
			() => {
				const spy = jest.fn();

				component = new UndoCard(
					{
						onClose: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.dismiss'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call onClick on undo',
			() => {
				const spy = jest.fn();

				component = new UndoCard(
					{
						onUndo: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.undo'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should display message',
			() => {
				component = new UndoCard(
					{
						message: 'hello world'
					}
				);

				expect(component.element.innerHTML).toContain('hello world');
			}
		);
	}
);