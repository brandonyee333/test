jest.unmock('../../actions/modals');
jest.unmock('../../lib/util');
jest.unmock('../modals');

import {Map} from 'immutable';

import reducer from '../modals';
import {actionTypes} from '../../actions/modals';

describe(
	'Modals Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should hide modal on hideModal',
			() => {
				const action = {
					type: actionTypes.HIDE_MODAL
				};

				const state = reducer(
					Map(
						{
							hideOnBlur: false,
							modalProps: {
								feedId: 0,
								id: 1
							},
							modalType: 'HIDE_MODAL',
							open: true
						}
					),
					action
				);

				expect(state.get('open')).toBe(false);
			}
		);

		it(
			'should show modal on showModal',
			() => {
				const action = {
					type: actionTypes.SHOW_MODAL
				};

				const state = reducer(
					Map(
						{
							hideOnBlur: false,
							modalProps: {
								feedId: 0,
								id: 1
							},
							modalType: 'HIDE_MODAL',
							open: false
						}
					),
					action
				);

				expect(state.get('open')).toBe(true);
			}
		);
	}
);