jest.unmock('../../actions/followers');
jest.unmock('../../lib/util');
jest.unmock('../followers');

import {is, Map, OrderedSet} from 'immutable';

import reducer from '../followers';
import {actionTypes} from '../../actions/followers';
import {classNameIdToSchema} from '../../lib/util';

const {classNameIds} = LoopConstants;

describe(
	'Followers Reducer',
	() => {
		it(
			'should add ids to a divisions followers',
			() => {
				const id = 1;
				const items = OrderedSet([2, 3, 4]);
				const schema = classNameIdToSchema(classNameIds.divisions);

				const action = {
					data: items,
					entityClassNameId: classNameIds.divisions,
					id,
					type: actionTypes.FETCH_FOLLOWERS_SUCCESS
				};

				const state = reducer(Map(), action);

				const data = state.getIn([schema, id, 'followers', 'data']);
				const loading = state.getIn([schema, id, 'followers', 'loading']);

				expect(is(data, items)).toBeTruthy();
				expect(loading).toBeFalsy();
			}
		);

		it(
			'should add ids to a persons followers',
			() => {
				const id = 1;
				const items = OrderedSet([2, 3, 4]);
				const schema = classNameIdToSchema(classNameIds.people);

				const action = {
					data: items,
					entityClassNameId: classNameIds.people,
					id,
					type: actionTypes.FETCH_FOLLOWERS_SUCCESS
				};

				const state = reducer(Map(), action);

				const data = state.getIn([schema, id, 'followers', 'data']);
				const loading = state.getIn([schema, id, 'followers', 'loading']);

				expect(is(data, items)).toBeTruthy();
				expect(loading).toBeFalsy();
			}
		);

		it(
			'should add ids to a topics followers',
			() => {
				const id = 1;
				const items = OrderedSet([2, 3, 4]);
				const schema = classNameIdToSchema(classNameIds.topics);

				const action = {
					data: items,
					entityClassNameId: classNameIds.topics,
					id,
					type: actionTypes.FETCH_FOLLOWERS_SUCCESS
				};

				const state = reducer(Map(), action);

				const data = state.getIn([schema, id, 'followers', 'data']);
				const loading = state.getIn([schema, id, 'followers', 'loading']);

				expect(is(data, items)).toBeTruthy();
				expect(loading).toBeFalsy();
			}
		);

		it(
			'should update loading when requesting followers',
			() => {
				const id = 1;

				const action = {
					entityClassNameId: classNameIds.divisions,
					id,
					type: actionTypes.FETCH_FOLLOWERS_REQUEST
				};

				const state = reducer(Map(), action);

				const loading = state.getIn([classNameIdToSchema(classNameIds.divisions), id, 'followers', 'loading']);

				expect(loading).toBeTruthy();
			}
		);

		it(
			'should update loading when requesting followers',
			() => {
				const id = 1;

				const action = {
					entityClassNameId: classNameIds.people,
					id,
					type: actionTypes.FETCH_FOLLOWERS_REQUEST
				};

				const state = reducer(Map(), action);

				const loading = state.getIn([classNameIdToSchema(classNameIds.people), id, 'followers', 'loading']);

				expect(loading).toBeTruthy();
			}
		);

		it(
			'should update loading when requesting followers',
			() => {
				const id = 1;

				const action = {
					entityClassNameId: classNameIds.topics,
					id,
					type: actionTypes.FETCH_FOLLOWERS_REQUEST
				};

				const state = reducer(Map(), action);

				const loading = state.getIn([classNameIdToSchema(classNameIds.topics), id, 'followers', 'loading']);

				expect(loading).toBeTruthy();
			}
		);

		it(
			'should handle fetch followers failure',
			() => {
				const id = 1;
				const schema = classNameIdToSchema(classNameIds.divisions);

				const action = {
					entityClassNameId: classNameIds.divisions,
					id,
					type: actionTypes.FETCH_FOLLOWERS_FAILURE
				};

				const state = reducer(
					Map().setIn([schema, id, 'followers', 'loading'], true),
					action
				);

				expect(state.getIn([schema, id, 'followers', 'loading'])).toBe(false);
			}
		);

		it(
			'should handle fetch followers failure',
			() => {
				const id = 1;
				const schema = classNameIdToSchema(classNameIds.people);

				const action = {
					entityClassNameId: classNameIds.people,
					id,
					type: actionTypes.FETCH_FOLLOWERS_FAILURE
				};

				const state = reducer(
					Map().setIn([schema, id, 'followers', 'loading'], true),
					action
				);

				expect(state.getIn([schema, id, 'followers', 'loading'])).toBe(false);
			}
		);

		it(
			'should handle fetch followers failure',
			() => {
				const id = 1;
				const schema = classNameIdToSchema(classNameIds.topics);

				const action = {
					entityClassNameId: classNameIds.topics,
					id,
					type: actionTypes.FETCH_FOLLOWERS_FAILURE
				};

				const state = reducer(
					Map().setIn([schema, id, 'followers', 'loading'], true),
					action
				);

				expect(state.getIn([schema, id, 'followers', 'loading'])).toBe(false);
			}
		);
	}
);