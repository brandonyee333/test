jest.unmock('../notifying');
jest.unmock('../../actions/notifying');
jest.unmock('../../lib/util');

import {Map} from 'immutable';

import reducer from '../notifying';
import {actionTypes} from '../../actions/notifying';

const {classNameIds} = LoopConstants;

describe(
	'Notifying Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should update data on notify success',
			() => {
				const id = 1;

				const action = {
					entityClassNameId: classNameIds.people,
					id,
					notifying: true,
					type: actionTypes.NOTIFY_ENTITY_SUCCESS
				};

				const state = reducer(
					Map(
						{
							people: Map().mergeIn(
								[id],
								{
									data: {
										name: 'test',
										notifying: false
									}
								}
							)
						}
					),
					action
				);

				const data = state.getIn(['people', id, 'data']);

				expect(data.get('notifying')).toBe(true);
				expect(data.get('name')).toBe('test');
			}
		);

		it(
			'should update data on notify email success',
			() => {
				const id = 1;

				const action = {
					entityClassNameId: classNameIds.people,
					id,
					notifyingEmail: true,
					type: actionTypes.NOTIFY_EMAIL_ENTITY_SUCCESS
				};

				const state = reducer(
					Map(
						{
							people: Map().mergeIn(
								[id],
								{
									data: {
										name: 'test',
										notifyingEmail: false
									}
								}
							)
						}
					),
					action
				);

				const data = state.getIn(['people', id, 'data']);

				expect(data.get('notifyingEmail')).toBe(true);
				expect(data.get('name')).toBe('test');
			}
		);
	}
);