jest.unmock('../../actions/crud');
jest.unmock('../../actions/lists');
jest.unmock('../../actions/topics');
jest.unmock('../../lib/util');
jest.unmock('../lists');
jest.unmock('../crud');

import {fromJS, Map} from 'immutable';

import LoopConstants from '../../lib/loop-constants';
import reducer from '../lists';
import {actionTypes} from '../../actions/lists';
import {actionTypes as divisionActionTypes} from '../../actions/divisions';
import {actionTypes as peopleActionTypes} from '../../actions/people';
import {actionTypes as topicsActionTypes} from '../../actions/topics';

const {department, location, team} = LoopConstants.types;

describe(
	'lists Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should add results to data',
			() => {
				const listName = '1';

				const action = {
					data: {
						results: 'foo'
					},
					listName,
					type: actionTypes.FETCH_INDEX_SUCCESS
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.has(listName)).toBe(true);
				expect(state.getIn([listName, 'data', 0])).toBe('foo');
				expect(state.getIn([listName, 'loading'])).toBe(false);
			}
		);

		it(
			'should add newDivisions and set loading to true on REQUEST',
			() => {
				const listName = 'newDivisions';

				const action = {
					listName,
					type: divisionActionTypes.FETCH_NEW_DIVISIONS_REQUEST
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'loading'])).toBe(true);
			}
		);

		it(
			'should set newDivisions loading to false on FAILURE',
			() => {
				const listName = 'newDivisions';

				const action = {
					listName,
					type: divisionActionTypes.FETCH_NEW_DIVISIONS_FAILURE
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'loading'])).toBe(false);
			}
		);

		it(
			'should update newDivisions data and set loading to false on SUCCESS',
			() => {
				const listName = 'newDivisions';
				const results = [1, 2, 3, 4];

				const action = {
					data: {
						results
					},
					listName,
					type: divisionActionTypes.FETCH_NEW_DIVISIONS_SUCCESS
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'data'])).toContain(1, 2, 3, 4);
				expect(state.getIn([listName, 'loading'])).toBe(false);
			}
		);

		it(
			'should clear all lists',
			() => {
				const action = {
					type: actionTypes.CLEAR_LISTS
				};

				const state = reducer(
					Map().mergeIn(
						[1],
						{
							people: 'test'
						}
					),
					action
				);

				expect(state.isEmpty()).toBe(true);
			}
		);

		it(
			'should update loading to be true',
			() => {
				const listName = '1';

				const action = {
					listName,
					type: actionTypes.FETCH_INDEX_REQUEST
				};

				const state = reducer(
					fromJS(
						{
							[listName]: {
								loading: false
							}
						}
					),
					action
				);

				expect(state.has(listName)).toBe(true);
				expect(state.getIn([listName, 'loading'])).toBe(true);
			}
		);

		it(
			'should update loading to be false',
			() => {
				const listName = '1';

				const action = {
					listName,
					type: actionTypes.FETCH_INDEX_FAILURE
				};

				const state = reducer(
					fromJS(
						{
							[listName]: {
								loading: true
							}
						}
					),
					action
				);

				expect(state.has(listName)).toBe(true);
				expect(state.getIn([listName, 'loading'])).toBe(false);
			}
		);

		it(
			'should add featured topic and update total',
			() => {
				const listName = 'featuredTopics';

				const id = 123;

				const action = {
					data: id,
					type: topicsActionTypes.ADD_FEATURED_TOPIC_SUCCESS
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'data'])).toContain(id);
				expect(state.getIn([listName, 'total'])).toBe(1);
			}
		);

		it(
			'should update loading to false for featuredTopics',
			() => {
				const listName = 'featuredTopics';

				const action = {
					type: topicsActionTypes.FETCH_FEATURED_FAILURE
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'loading'])).toBe(false);
			}
		);

		it(
			'should update loading to true for featuredTopics',
			() => {
				const listName = 'featuredTopics';

				const action = {
					type: topicsActionTypes.FETCH_FEATURED_REQUEST
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'loading'])).toBe(true);
			}
		);

		it(
			'should update featuredTopics that are fetched',
			() => {
				const listName = 'featuredTopics';

				const action = {
					data: {
						results: [3, 4, 5],
						total: 5
					},
					type: topicsActionTypes.FETCH_FEATURED_SUCCESS
				};

				const state = reducer(
					fromJS(
						{
							[listName]: {
								data: [1, 2],
								loading: true,
								total: 2
							}
						}
					),
					action
				);

				expect(state.getIn([listName, 'loading'])).toBe(false);
				expect(state.getIn([listName, 'data'])).toContain(3, 4, 5);
				expect(state.getIn([listName, 'total'])).toBe(5);
			}
		);

		it(
			'should remove featured topic and update total',
			() => {
				const listName = 'featuredTopics';

				const id = 3;

				const action = {
					id,
					type: topicsActionTypes.REMOVE_FEATURED_TOPIC_SUCCESS
				};

				const state = reducer(
					fromJS(
						{
							[listName]: {
								data: [1, 2, 3],
								loading: true,
								total: 3
							}
						}
					),
					action
				);

				expect(state.getIn([listName, 'data'])).not.toContain(id);
				expect(state.getIn([listName, 'total'])).toBe(2);
			}
		);

		it(
			'should update total only for the teams list',
			() => {
				const departmentsList = 'departments';
				const teamsList = 'teams';

				const action = {
					divisionType: team,
					type: divisionActionTypes.ADD_SUCCESS
				};

				const state = reducer(
					fromJS(
						{
							[departmentsList]: {
								data: 'test2',
								loading: false,
								total: 8
							},
							[teamsList]: {
								data: 'test',
								loading: false,
								total: 5
							}
						}
					),
					action
				);

				expect(state.getIn([teamsList, 'total'])).toBe(6);
				expect(state.getIn([departmentsList, 'total'])).toBe(8);
			}
		);

		it(
			'should update the totals for departments, teams, and locations list',
			() => {
				const departmentsList = 'departments';
				const locationsList = 'locations';
				const teamsList = 'teams';

				const action = {
					data: {
						[department]: 4,
						[location]: 9,
						[team]: 2
					},
					type: divisionActionTypes.FETCH_TYPE_TOTALS_SUCCESS
				};

				const state = reducer(
					fromJS(
						{
							[departmentsList]: {
								data: 'department',
								loading: false
							},
							[locationsList]: {
								data: 'location',
								loading: false
							},
							[teamsList]: {
								data: 'team',
								loading: false
							}
						}
					),
					action
				);

				expect(state.getIn([teamsList, 'total'])).toBe(2);
				expect(state.getIn([departmentsList, 'total'])).toBe(4);
				expect(state.getIn([locationsList, 'total'])).toBe(9);
			}
		);

		it(
			'should add newPeople and set loading to true on REQUEST',
			() => {
				const listName = 'newPeople';

				const action = {
					listName,
					type: peopleActionTypes.FETCH_NEW_PEOPLE_REQUEST
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'loading'])).toBe(true);
			}
		);

		it(
			'should set newPeople loading to false on FAILURE',
			() => {
				const listName = 'newPeople';

				const action = {
					listName,
					type: peopleActionTypes.FETCH_NEW_PEOPLE_FAILURE
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'loading'])).toBe(false);
			}
		);

		it(
			'should update newPeople data and set loading to false on SUCCESS',
			() => {
				const listName = 'newPeople';
				const results = [1, 2, 3, 4];

				const action = {
					data: {
						results
					},
					listName,
					type: peopleActionTypes.FETCH_NEW_PEOPLE_SUCCESS
				};

				const state = reducer(
					Map(),
					action
				);

				expect(state.getIn([listName, 'data'])).toContain(1, 2, 3, 4);
				expect(state.getIn([listName, 'loading'])).toBe(false);
			}
		);
	}
);