jest.unmock('../MarkdownWithOverlay');
jest.unmock('../../actions/overlays');

import MarkdownWithOverlay from '../MarkdownWithOverlay';
import mockStore from '../../tests/mock-store';

describe(
	'MarkdownWithOverlay',
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
				component = new MarkdownWithOverlay(
					{
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should rerender and display new message if message prop changes',
			() => {
				component = new MarkdownWithOverlay(
					{
						message: 'test test',
						store: mockStore()
					}
				);

				const markdownWithOverlayComp = component.components.child;

				markdownWithOverlayComp.props.message = 'test test 2';

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);
	}
);