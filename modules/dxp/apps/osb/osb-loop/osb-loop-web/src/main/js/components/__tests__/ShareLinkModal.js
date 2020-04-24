import dom from 'metal-dom';

import ShareLinkModal from '../ShareLinkModal';

describe(
	'ShareLinkModal',
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
				component = new ShareLinkModal(
					{
						hideModal: jest.fn(),
						url: 'http://www.liferay.com'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls onChange',
			() => {
				component = new ShareLinkModal(
					{
						hideModal: jest.fn(),
						url: 'http://www.liferay.com'
					}
				);

				const button = component.element.querySelector('.btn-container');

				const text = button.textContent;

				dom.triggerEvent(button, 'click');

				jest.runOnlyPendingTimers();

				expect(text).not.toBe(button.textContent);
			}
		);
	}
);