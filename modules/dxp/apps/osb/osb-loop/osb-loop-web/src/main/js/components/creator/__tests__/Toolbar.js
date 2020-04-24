jest.unmock('../../../lib/util');

import Toolbar from '../Toolbar';
import mockStore from '../../../tests/mock-store';

describe(
	'Toolbar',
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
				component = new Toolbar({store: mockStore()});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onTypeChange with text type',
			() => {
				const spy = jest.fn();

				component = new Toolbar(
					{
						onTypeChange: spy,
						store: mockStore(),
						type: 'foo'
					}
				);

				component.components.child.changePostTypeText_();

				expect(spy).toBeCalledWith('text');
			}
		);

		it(
			'should call onTypeChange with new link type',
			() => {
				const spy = jest.fn();

				component = new Toolbar(
					{
						onTypeChange: spy,
						store: mockStore()
					}
				);

				component.components.child.changePostTypeLink_();

				expect(spy).toBeCalledWith('link');
			}
		);

		it(
			'should toggle announcement',
			() => {
				const spy = jest.fn();

				component = new Toolbar(
					{
						onAnnouncementChange: spy,
						store: mockStore()
					}
				);

				component.components.child.toggleAnnouncement_();

				expect(spy).toBeCalledWith(true);
			}
		);

		it(
			'should not toggle announcement',
			() => {
				const spy = jest.fn();

				component = new Toolbar(
					{
						forceAnnouncement: true,
						onDataChange: spy,
						store: mockStore()
					}
				);

				component.components.child.toggleAnnouncement_();

				expect(spy).not.toBeCalled();
			}
		);
	}
);