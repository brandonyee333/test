jest.unmock('../modals');

import {hideModal, showModal} from '../modals';

describe(
	'Modals',
	() => {
		it(
			'should call hideModal action',
			() => {
				const action = hideModal();

				expect(typeof action).toBe('object');
				expect(action.type).toBe('HIDE_MODAL');
			}
		);

		it(
			'should call showModal action',
			() => {
				const modalProps = {
					feedId: 0,
					id: 1
				};

				const modalType = 'CONFIRM_DIALOG';

				const action = showModal({modalProps, modalType});

				expect(typeof action).toBe('object');
				expect(action.modalProps).toBe(modalProps);
				expect(action.modalType).toBe(modalType);
				expect(action.type).toBe('SHOW_MODAL');
			}
		);
	}
);