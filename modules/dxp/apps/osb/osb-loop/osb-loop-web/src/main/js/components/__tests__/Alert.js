import Alert from '../Alert';
import dom from 'metal-dom';
import {fromJS} from 'immutable';

import {alertTypes} from '../../actions/alerts';

describe(
	'Alert',
	() => {
		const alertIMap = fromJS(
			{
				alertType: alertTypes.ALERT,
				id: 0,
				message: 'Version 2.0 is available! Refresh the page.'
			}
		);

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
				component = new Alert({alertIMap});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render message',
			() => {
				component = new Alert({alertIMap});

				expect(component.element.innerHTML).toContain(alertIMap.get('message'));

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onRemoveAlert on click',
			() => {
				const spy = jest.fn();

				component = new Alert(
					{
						alertIMap,
						onRemoveAlert: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.dismiss .loop-icon-container'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);