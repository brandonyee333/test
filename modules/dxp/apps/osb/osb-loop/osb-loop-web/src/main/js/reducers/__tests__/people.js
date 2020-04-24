jest.unmock('../../actions/crud');
jest.unmock('../../actions/people');
jest.unmock('../../lib/util');
jest.unmock('../crud');
jest.unmock('../people');

import {
	is,
	List,
	Map,
	OrderedSet
} from 'immutable';

import reducer from '../people';
import {actionTypes} from '../../actions/people';
import {getTypeName} from '../../lib/util';

describe(
	'People Reducer',
	() => {
		it(
			'should update loading on fetch divisions request',
			() => {
				const divisionType = getTypeName(LoopConstants.types.department);
				const id = 3;

				const action = {
					divisionType,
					id,
					type: actionTypes.FETCH_DIVISIONS_REQUEST
				};

				const state = reducer(Map(), action);

				const loading = state.getIn([id, 'network', 'divisions', divisionType, 'loading']);

				expect(loading).toBe(true);
			}
		);

		it(
			'should update divisions on fetch divisions success',
			() => {
				const divisionType = LoopConstants.types.department;
				const id = 1;
				const results = List([5, 6]);

				const action = {
					data: {
						results,
						total: 2
					},
					divisionType: getTypeName(divisionType),
					end: 2,
					id,
					start: 0,
					type: actionTypes.FETCH_DIVISIONS_SUCCESS
				};

				const state = reducer(Map(), action);

				const peopleDivisionsState = state.getIn([id, 'network', 'divisions']);

				expect(is(peopleDivisionsState.getIn(['department', 'data']), results)).toBeTruthy();
			}
		);

		it(
			'should update following on fetch following success',
			() => {
				const id = 1;
				const results = OrderedSet([5, 6]);
				const total = 5;

				const action = {
					data: {
						results,
						total
					},
					end: 10,
					entityClassNameId: LoopConstants.classNameIds.topics,
					id,
					start: 0,
					type: actionTypes.FETCH_FOLLOWING_SUCCESS
				};

				const state = reducer(Map(), action);

				const personState = state.getIn([id, 'following', 'topics']);

				expect(is(personState.get('data'), results)).toBeTruthy();
				expect(personState.get('total')).toBe(total);
			}
		);

		it(
			'should update managers on fetch managers request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_MANAGERS_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'managers']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update managers on fetch managers success',
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
					type: actionTypes.FETCH_MANAGERS_SUCCESS
				};

				const state = reducer(Map(), action);

				const personState = state.getIn([id, 'network', 'managers']);

				expect(is(personState.get('data'), results)).toBeTruthy();
				expect(personState.get('total')).toBe(total);
			}
		);

		it(
			'should update subordinates on fetch subordinates request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_SUBORDINATES_REQUEST
				};

				const state = reducer(Map(), action);

				const divisionState = state.getIn([id, 'network', 'subordinates']);

				expect(divisionState.get('loading')).toBeTruthy();
			}
		);

		it(
			'should update subordinates on fetch subordinates success',
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
					type: actionTypes.FETCH_SUBORDINATES_SUCCESS
				};

				const state = reducer(Map(), action);

				const personState = state.getIn([id, 'network', 'subordinates']);

				expect(is(personState.get('data'), results)).toBeTruthy();
				expect(personState.get('total')).toBe(total);
			}
		);

		it(
			'should update loading to true on fetch expertise request',
			() => {
				const id = 1;

				const action = {
					id,
					type: actionTypes.FETCH_EXPERTISE_REQUEST
				};

				const state = reducer(Map(), action);

				expect(state.getIn([id, 'expertise', 'loading'])).toBe(true);
			}
		);

		it(
			'should update expertise on fetch expertise success',
			() => {
				const data = OrderedSet([1, 2]);
				const id = 1;

				const action = {
					data,
					id,
					type: actionTypes.FETCH_EXPERTISE_SUCCESS
				};

				const state = reducer(Map(), action);

				const expertiseIMap = state.getIn([id, 'expertise']);

				expect(is(expertiseIMap.get('data'), data)).toBeTruthy();
				expect(expertiseIMap.get('loading')).toBe(false);
			}
		);
	}
);