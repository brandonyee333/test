import dom from 'metal-dom';
import {noop} from 'lodash';

import ConfirmDialogModal from '../ConfirmDialogModal';

describe(
	'ConfirmDialogModal',
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
				component = new ConfirmDialogModal({onConfirm: noop});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call hideModal for submit',
			() => {
				const spy = jest.fn();

				component = new ConfirmDialogModal(
					{
						hideModal: spy,
						onConfirm: noop
					}
				);

				dom.triggerEvent(component.element.querySelector('.btn-primary'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call hideModal for cancel',
			() => {
				const spy = jest.fn();

				component = new ConfirmDialogModal(
					{
						hideModal: spy,
						onConfirm: noop
					}
				);

				dom.triggerEvent(component.element.querySelector('.btn-secondary'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);