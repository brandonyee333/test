jest.unmock('../../lib/util');
jest.unmock('../crud');

import {Map} from 'immutable';

import createActions from '../crud';
import {CALL_API} from '../../middleware/api';

describe(
	'CRUD Actions',
	() => {
		const NAME = 'TEST';

		const {actions} = createActions(
			{
				controller: NAME,
				name: NAME
			}
		);

		it(
			'should return an object containing action creators',
			() => {
				Object.keys(actions, actionCreator => expect(typeof actionCreator).toBe('function'));
			}
		);

		it(
			'should return an add action',
			() => {
				const {add} = actions;

				const action = add();

				action[CALL_API].types.forEach(type => expect(type).toContain(`ADD_${NAME}`));
			}
		);

		it(
			'should return a fetch action',
			() => {
				const {fetch} = actions;

				const action = fetch(5);

				action[CALL_API].types.forEach(type => expect(type).toContain(`FETCH_${NAME}`));
			}
		);

		it(
			'should return a hydrate action',
			() => {
				const {hydrate} = actions;

				const action = hydrate(
					5,
					{
						name: 'tester'
					}
				);

				expect(action.type).toBe(`HYDRATE_${NAME}`);
			}
		);

		it(
			'should return an update action',
			() => {
				const {update} = actions;

				const action = update(
					{
						id: 5,
						title: 'tester'
					}
				);

				const apiCall = action[CALL_API];

				expect(typeof apiCall).toBe('object');
				expect(apiCall.data.id).toBe(5);
				expect(apiCall.data.title).toBe('tester');
				expect(apiCall.controllerMethod).toBe('update.json');

				action[CALL_API].types.forEach(type => expect(type).toContain(`UPDATE_${NAME}`));
			}
		);

		it(
			'should return a delete action thunk',
			() => {
				const {destroy} = actions;

				const dispatch = jest.fn();

				destroy(1)(dispatch, jest.fn().mockReturnValue(Map()));

				const action = dispatch.mock.calls[0][0];

				action[CALL_API].types.forEach(type => expect(type).toContain(`DESTROY_${NAME}`));
			}
		);

		it(
			'should return a create action',
			() => {
				const {create} = actions;

				const action = create(
					{
						name: 'test',
						title: 'tester'
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.name).toBe('test');
				expect(apiCall.data.title).toBe('tester');

				apiCall.types.forEach(type => expect(type).toContain(`CREATE_${NAME}`));
			}
		);

		it(
			'should return a search action',
			() => {
				const {search} = actions;

				const action = search(
					{
						keywords: 'test'
					}
				);

				const apiCall = action[CALL_API];

				apiCall.types.forEach(type => expect(type).toContain(`SEARCH_${NAME}`));
			}
		);
	}
);