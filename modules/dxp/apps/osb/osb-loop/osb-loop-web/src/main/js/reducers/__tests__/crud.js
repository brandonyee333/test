jest.unmock('../../actions/crud');
jest.unmock('../../lib/util');
jest.unmock('../crud');

import {fromJS, Map} from 'immutable';

import createBaseActions from '../../actions/crud';
import createBaseReducer, {mergeData, updateLoading} from '../crud';

describe(
	'CRUD Reducer',
	() => {
		const {actionTypes} = createBaseActions({name: 'TEST'});

		const reducer = createBaseReducer(
			{
				actionTypes,
				primaryKey: 'entityClassPK'
			}
		);

		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should update loading status when fetching',
			() => {
				const id = 5;

				const state = reducer(
					Map(),
					{
						id,
						type: actionTypes.FETCH_REQUEST
					}
				);

				expect(state.getIn([id, 'loading'])).toBe(true);
			}
		);

		it(
			'should update entity on fetch success',
			() => {
				const name = 'test';

				const state = reducer(
					Map().mergeIn(
						[5],
						{
							loading: true
						}
					),
					{
						data: {name},
						id: 5,
						type: actionTypes.FETCH_SUCCESS
					}
				);

				const entity = state.get(5);

				expect(entity.get('loading')).toBe(false);
				expect(entity.getIn(['data', 'name'])).toBe(name);
			}
		);

		it(
			'should update entity on fetch error',
			() => {
				const error = 'error';

				const state = reducer(
					Map().mergeIn(
						[5],
						{
							loading: true
						}
					),
					{
						error,
						id: 5,
						type: actionTypes.FETCH_FAILURE
					}
				);

				expect(state.getIn([5, 'loading'])).toBe(false);
			}
		);

		it(
			'should update loading status on update request',
			() => {
				const id = 1;

				const state = reducer(
					Map(),
					{
						id,
						type: actionTypes.UPDATE_REQUEST
					}
				);

				expect(state.getIn([id, 'loading'])).toBe(true);
			}
		);

		it(
			'should update entity on update success',
			() => {
				const id = 1;

				const state = reducer(
					Map().setIn(
						[id],
						fromJS(
							{
								data: {
									name: 'test'
								},
								loading: true
							}
						)
					),
					{
						data: {
							name: 'joe'
						},
						id,
						type: actionTypes.UPDATE_SUCCESS
					}
				);

				const entityState = state.get(id);

				expect(entityState.get('loading')).toBe(false);
				expect(entityState.getIn(['data', 'name'])).toBe('joe');
			}
		);

		it(
			'should update entity on update error',
			() => {
				const error = 'error';

				const state = reducer(
					Map().mergeIn(
						[5],
						{
							loading: true
						}
					),
					{
						error,
						id: 5,
						type: actionTypes.UPDATE_FAILURE
					}
				);

				expect(state.getIn([5, 'loading'])).toBe(false);
			}
		);

		it(
			'should update entity on destroy request',
			() => {
				const id = 1;

				const state = reducer(
					Map(),
					{
						id,
						type: actionTypes.DESTROY_REQUEST
					}
				);

				expect(state.getIn([id, 'loading'])).toBe(true);
			}
		);

		it(
			'should delete entity on destroy success',
			() => {
				const id = 1;

				const state = reducer(
					Map().mergeIn(
						[id],
						{
							loading: true
						}
					),
					{
						id,
						type: actionTypes.DESTROY_SUCCESS
					}
				);

				expect(state.get(id)).toBeUndefined();
			}
		);

		it(
			'should update entity on destroy failure',
			() => {
				const id = 1;

				const state = reducer(
					Map().mergeIn(
						[id],
						{
							loading: true
						}
					),
					{
						id,
						type: actionTypes.DESTROY_FAILURE
					}
				);

				expect(state.getIn([id, 'loading'])).toBe(false);
			}
		);

		it(
			'should add entity on create success',
			() => {
				const id = 1;

				const state = reducer(
					Map(),
					{
						data: {
							entityClassPK: id,
							name: 'test'
						},
						type: actionTypes.CREATE_SUCCESS
					}
				);

				const entity = state.get(id);

				expect(entity.get('loading')).toBe(false);
				expect(entity.getIn(['data', 'name'])).toBe('test');
			}
		);

		it(
			'should hydrate',
			() => {
				const name = 'test';

				const state = reducer(
					Map().mergeIn(
						[5],
						{
							loading: true
						}
					),
					{
						data: {name},
						id: 5,
						type: actionTypes.HYDRATE
					}
				);

				const entity = state.get(5);

				expect(entity.get('loading')).toBe(false);
				expect(entity.getIn(['data', 'name'])).toBe(name);
			}
		);
	}
);

describe(
	'mergeData',
	() => {
		it(
			'should return a new state with updated data field while setting loading to false and leaving other fields unmodified',
			() => {
				const id = 5;

				const action = {
					data: {
						name: 'test'
					},
					id
				};

				const state = mergeData(
					Map().mergeIn(
						[id],
						{
							data: {
								name: 'notTest'
							},
							following: 'test',
							loading: true
						}
					),
					action
				);

				const entityState = state.get(id);

				expect(entityState.get('following')).toBe('test');
				expect(entityState.get('loading')).toBe(false);
				expect(entityState.getIn(['data', 'name'])).toBe('test');
			}
		);

		it(
			'should return a new state with updated fields while removing the id if the id is a string and using the entityClassPK as an id instead',
			() => {
				const entityClassPK = 5;
				const id = 'test';

				const action = {
					data: {
						entityClassPK,
						name: 'test'
					},
					id
				};

				const state = mergeData(
					Map().mergeIn(
						[id],
						{
							data: {
								name: 'notTest'
							},
							loading: true
						}
					),
					action
				);

				const entityState = state.get(entityClassPK);

				expect(entityState.get('loading')).toBe(false);
				expect(entityState.getIn(['data', 'name'])).toBe('test');
				expect(state.get(id)).toBeFalsy();
			}
		);
	}
);

describe(
	'updateLoading',
	() => {
		it(
			'should return a function that updates loading state',
			() => {
				const actionHandler = updateLoading(false);

				expect(typeof actionHandler).toBe('function');

				const id = 5;

				const action = {
					data: {
						name: 'test'
					},
					id
				};

				const state = actionHandler(
					Map().mergeIn(
						[id],
						{
							data: {
								name: 'notTest'
							},
							loading: true
						}
					),
					action
				);

				const entityState = state.get(id);

				expect(entityState.get('loading')).toBe(false);
				expect(entityState.getIn(['data', 'name'])).toBe('notTest');
			}
		);
	}
);