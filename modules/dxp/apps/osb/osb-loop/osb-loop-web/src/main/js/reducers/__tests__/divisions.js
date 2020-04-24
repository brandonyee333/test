jest.unmock('../../actions/crud');
jest.unmock('../../actions/divisions');
jest.unmock('../../actions/pages');
jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../divisions');

import {
	fromJS,
	is,
	Map,
	OrderedSet
} from 'immutable';

import reducer, {getLocation} from '../divisions';
import {actionTypes} from '../../actions/divisions';
import {actionTypes as pagesActionTypes} from '../../actions/pages';

describe(
	'Divisions Reducer',
	() => {
		it(
			'should update childDivisions on fetch child division request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_CHILD_DIVISIONS_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'childDivisions']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update childDivisions on fetch child divisions success',
			() => {
				const data = OrderedSet([1, 2]);
				const id = 1;

				const action = {
					data,
					id,
					type: actionTypes.FETCH_CHILD_DIVISIONS_SUCCESS
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'childDivisions']);

				expect(is(divisionState.get('data'), data)).toBeTruthy();
			}
		);

		it(
			'should update parentDivision on fetch parent division request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_PARENT_DIVISION_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'parentDivision']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update parentDivision on fetch parent division success',
			() => {
				const data = {test: 'test'};
				const id = 1;

				const action = {
					data,
					id,
					type: actionTypes.FETCH_PARENT_DIVISION_SUCCESS
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'parentDivision']);

				expect(is(divisionState.get('data'), Map(data))).toBeTruthy();
			}
		);

		it(
			'should update leads on fetch leads request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_LEADS_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'leads']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update leads on fetch leads success',
			() => {
				const id = 1;
				const results = OrderedSet([1, 2]);
				const total = 5;

				const action = {
					data: {
						results,
						total
					},
					end: 2,
					id,
					start: 0,
					type: actionTypes.FETCH_LEADS_SUCCESS
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'leads']);

				expect(is(divisionState.get('data'), results)).toBeTruthy();
				expect(divisionState.get('total')).toBe(total);
			}
		);

		it(
			'should update members on fetch members request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_MEMBERS_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'members']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update members on fetch members success',
			() => {
				const id = 1;
				const results = OrderedSet([1, 2]);
				const total = 5;

				const action = {
					data: {
						results,
						total
					},
					end: 2,
					id,
					start: 0,
					type: actionTypes.FETCH_MEMBERS_SUCCESS
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'members']);

				expect(is(divisionState.get('data'), results)).toBeTruthy();
				expect(divisionState.get('total')).toBe(total);
			}
		);

		it(
			'should replace members on fetch members success',
			() => {
				const data = OrderedSet([1, 2]);
				const id = 1;
				const results = OrderedSet([3, 4]);
				const total = 5;

				const action = {
					data: {
						results,
						total
					},
					end: 4,
					id,
					start: 2,
					type: actionTypes.FETCH_MEMBERS_SUCCESS
				};

				const state = reducer(
					Map(
						{
							1: {
								'network': {
									'members': {
										data,
										loading: false
									}
								}
							}
						}
					),
					action
				);

				const divisionState = state.getIn([id, 'network', 'members']);

				expect(is(divisionState.get('data'), results)).toBeTruthy();
				expect(divisionState.get('total')).toBe(total);
			}
		);

		it(
			'should update childDivisions loading on set child division request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.SET_CHILD_DIVISIONS_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'childDivisions']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update childDivisions on set child divisions success',
			() => {
				const data = OrderedSet([1, 2]);
				const id = 1;

				const action = {
					data,
					id,
					type: actionTypes.SET_CHILD_DIVISIONS_SUCCESS
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'childDivisions']);

				expect(is(divisionState.get('data'), data)).toBeTruthy();
			}
		);

		it(
			'should not update childDivisions on set child division failure',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.SET_CHILD_DIVISIONS_FAILURE
				};

				const state = reducer(
					fromJS(
						{
							[id]: {
								network: {
									childDivisions: {
										loading: true
									}
								}
							}
						}
					),
					action
				);

				const divisionState = state.getIn([id, 'network', 'childDivisions']);

				expect(divisionState.get('loading')).toBeFalsy();
			}
		);

		it(
			'should update parentDivision loading on set parent division request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.SET_PARENT_DIVISION_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'parentDivision']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update parentDivision on set parent division success',
			() => {
				const data = {test: 'test'};
				const id = 1;

				const action = {
					data,
					id,
					type: actionTypes.SET_PARENT_DIVISION_SUCCESS
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'parentDivision']);

				expect(is(divisionState.get('data'), Map(data))).toBeTruthy();
			}
		);

		it(
			'should not update parentDivision on set parent division failure',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.SET_PARENT_DIVISION_FAILURE
				};

				const state = reducer(
					fromJS(
						{
							[id]: {
								network: {
									parentDivision: {
										loading: true
									}
								}
							}
						}
					),
					action
				);

				const divisionState = state.getIn([id, 'network', 'parentDivision']);

				expect(divisionState.get('loading')).toBeFalsy();
			}
		);

		it(
			'should set parentDivision in network',
			() => {
				const action = {
					data: {
						loopDivisionCompositeJSONObject: 0,
						parentLoopDivisionCompositeJSONObject: 1
					},
					id: 0,
					type: actionTypes.FETCH_SUCCESS
				};

				const state = reducer(Map(), action);

				const parentDivisionIMap = state.getIn([0, 'network', 'parentDivision']);

				expect(parentDivisionIMap.get('data')).toBe(1);
				expect(parentDivisionIMap.get('loading')).toBeFalsy();
			}
		);

		it(
			'should set remove an entity if the id is a string',
			() => {
				const id = 'tests';

				const action = {
					data: {
						loopDivisionCompositeJSONObject: 0,
						parentLoopDivisionCompositeJSONObject: 1
					},
					id,
					type: actionTypes.FETCH_SUCCESS
				};

				const prevState = Map({[id]: {loading: true}});

				expect(prevState.get(id)).toBeTruthy();

				const state = reducer(prevState, action);

				const parentDivisionIMap = state.getIn([0, 'network', 'parentDivision']);

				expect(parentDivisionIMap.get('data')).toBe(1);
				expect(parentDivisionIMap.get('loading')).toBeFalsy();
				expect(state.get(id)).toBeFalsy();
			}
		);

		it(
			'getLocation',
			() => {
				const id = 1;
				const subtype = 1;
				const type = 'foo';

				expect(getLocation(id, type)).toEqual([id, 'network', type]);
				expect(getLocation(id, type, subtype)).toEqual([id, 'network', type, subtype]);
			}
		);

		it(
			'should increment the page count by 1 on addPage',
			() => {
				const classPK = 1;

				const action = {
					data: {
						classPK,
						rawMessage: 'test test',
						title: 'test'
					},
					type: pagesActionTypes.ADD_SUCCESS
				};

				const state = reducer(Map(), action);

				expect(state.getIn([classPK, 'data', 'pagesCount'])).toBe(1);
			}
		);

		it(
			'should clear pages',
			() => {
				const ownerId = 1;

				const action = {
					ownerId,
					type: pagesActionTypes.CLEAR_PAGES
				};

				const state = reducer(Map().setIn([ownerId, 'pages', 'data'], Map({test: 'test'})), action);

				expect(state.getIn([ownerId, 'pages'])).toBeFalsy();
			}
		);

		it(
			'should only update loading on fetch pages failure',
			() => {
				const ownerId = 1;
				const sortFieldName = 'title';

				const action = {
					ownerId,
					reverseSort: true,
					sortFieldName,
					type: pagesActionTypes.FETCH_PAGES_FAILURE
				};

				const state = reducer(Map(), action);

				expect(state.getIn([ownerId, 'pages', sortFieldName, 'ascending', 'loading'])).toBeFalsy();
			}
		);

		it(
			'should not update anything on fetchpages request',
			() => {
				const ownerId = 1;
				const sortFieldName = 'title';

				const action = {
					ownerId,
					reverseSort: true,
					sortFieldName,
					type: pagesActionTypes.FETCH_PAGES_REQUEST
				};

				const state = reducer(Map(), action);

				expect(state.getIn([ownerId, 'pages', sortFieldName, 'ascending', 'loading'])).toBeTruthy();
			}
		);

		it(
			'should update total & array on fetch pages success',
			() => {
				const ownerId = 1;
				const results = OrderedSet([1, 2, 3]);
				const sortFieldName = 'title';
				const total = 3;

				const action = {
					data: {
						results,
						total
					},
					ownerId,
					reverseSort: true,
					sortFieldName,
					type: pagesActionTypes.FETCH_PAGES_SUCCESS
				};

				const state = reducer(Map(), action);

				expect(state.getIn([ownerId, 'pages', sortFieldName, 'ascending', 'loading'])).toBeFalsy();
				expect(is(state.getIn([ownerId, 'pages', sortFieldName, 'ascending', 'data']), results)).toBeTruthy();
				expect(state.getIn([ownerId, 'data', 'pagesCount'])).toBe(total);
			}
		);
	}
);