jest.unmock('../../actions/overlays');
jest.unmock('../../actions/modals');
jest.unmock('../../lib/util');
jest.unmock('../overlays');

import {fromJS, Map} from 'immutable';

import reducer from '../overlays';
import {actionTypes, alignmentPositions} from '../../actions/overlays';
import {actionTypes as modalsActionTypes} from '../../actions/modals';

describe(
	'Overlays Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should hide overlay on hideOverlay',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.HIDE_OVERLAY
				};

				const state = reducer(
					Map().set(
						id,
						fromJS(
							{
								id,
								overlayProps: {feedId: 0},
								overlayType: 'ENTITY_SUMMARY'
							}
						)
					),
					action
				);

				expect(state.get(id)).toBeFalsy();
			}
		);

		it(
			'should show overlay on showOverlay',
			() => {
				const alignment = alignmentPositions.BOTTOM;
				const alignWithParent = true;
				const containerClass = 'test-container';
				const feedId = 1;
				const id = 1;
				const offset = 12;
				const overlayType = 'ENTITY_SUMMARY';

				const action = {
					alignment,
					alignWithParent,
					containerClass,
					id,
					offset,
					overlayProps: {feedId},
					overlayType,
					type: actionTypes.SHOW_OVERLAY
				};

				const state = reducer(Map(), action);

				expect(state.getIn([id, 'alignment'])).toBe(alignment);
				expect(state.getIn([id, 'alignWithParent'])).toBe(alignWithParent);
				expect(state.getIn([id, 'containerClass'])).toBe(containerClass);
				expect(state.getIn([id, 'id'])).toBe(id);
				expect(state.getIn([id, 'offset'])).toBe(offset);
				expect(state.getIn([id, 'overlayProps', 'feedId'])).toBe(feedId);
				expect(state.getIn([id, 'overlayType'])).toBe(overlayType);
			}
		);

		it(
			'should clear overlays state on showModal',
			() => {
				const action = {type: modalsActionTypes.SHOW_MODAL};
				const id = 1;

				const state = reducer(
					Map().set(
						id,
						fromJS(
							{
								id,
								overlayProps: {feedId: 0},
								overlayType: 'ENTITY_SUMMARY'
							}
						)
					),
					action
				);

				expect(state.isEmpty()).toBeTruthy();
			}
		);
	}
);