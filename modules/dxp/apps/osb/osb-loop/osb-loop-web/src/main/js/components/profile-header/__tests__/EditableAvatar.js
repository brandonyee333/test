jest.unmock('../../../lib/util');

import dom from 'metal-dom';

import EditableAvatar from '../EditableAvatar';
import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';

describe(
	'EditableAvatar',
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
				component = new EditableAvatar(
					{
						entity: {},
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should display profile image edit controls for a person entity',
			() => {
				component = new EditableAvatar(
					{
						entity: {
							...LoopAssets.getPerson(0),
							permissionEdit: true
						},
						store: mockStore()
					}
				);

				const element = component.element;

				dom.triggerEvent(element, 'mouseover');

				jest.runAllTimers();

				expect(element.querySelector('.loop-icon-camera')).toBeTruthy();
			}
		);

		it(
			'should not display profile image edit controls for a person entity',
			() => {
				component = new EditableAvatar(
					{
						entity: {
							...LoopAssets.getPerson(0),
							permissionEdit: false
						},
						store: mockStore()
					}
				);

				const element = component.element;

				dom.triggerEvent(element, 'mouseover');

				jest.runAllTimers();

				expect(element.querySelector('.loop-icon-camera')).toBeFalsy();
			}
		);

		it(
			'should display profile image edit controls for a topic entity',
			() => {
				component = new EditableAvatar(
					{
						entity: LoopAssets.getTopic(0),
						store: mockStore()
					}
				);

				const element = component.element;

				dom.triggerEvent(element, 'mouseover');

				jest.runAllTimers();

				expect(element.querySelector('.loop-icon-camera')).toBeTruthy();
			}
		);
	}
);