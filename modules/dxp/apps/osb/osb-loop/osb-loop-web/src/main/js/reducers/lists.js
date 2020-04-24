import {
	fromJS,
	List,
	Map,
	OrderedSet
} from 'immutable';

import LoopConstants from '../lib/loop-constants';
import {actionTypes as divisionActionTypes} from '../actions/divisions';
import {actionTypes as listsActionTypes} from '../actions/lists';
import {actionTypes as peopleActionTypes} from '../actions/people';
import {actionTypes as topicActionTypes} from '../actions/topics';
import {createReducer, getDivisionListType} from '../lib/util';

const {department, location, team} = LoopConstants.types;

function replaceList(listName) {
	return (state, {data}) => {
		const {results, total} = data;

		return state.mergeIn(
			[listName],
			fromJS(
				{
					data: OrderedSet(results),
					loading: false,
					total
				}
			)
		);
	};
}

function updateListLoading(val) {
	return (state, {listName}) => state.setIn([listName, 'loading'], val);
}

const actionHandlers = {
	[divisionActionTypes.ADD_SUCCESS]: (state, {divisionType}) => {
		const divisionListType = getDivisionListType(divisionType);

		return state.updateIn(
			[divisionListType, 'total'],
			0,
			total => total + 1
		);
	},

	[divisionActionTypes.FETCH_NEW_DIVISIONS_FAILURE]: state => state.setIn(['newDivisions', 'loading'], false),

	[divisionActionTypes.FETCH_NEW_DIVISIONS_REQUEST]: state => state.setIn(['newDivisions', 'loading'], true),

	[divisionActionTypes.FETCH_NEW_DIVISIONS_SUCCESS]: replaceList('newDivisions'),

	[divisionActionTypes.FETCH_TYPE_TOTALS_SUCCESS]: (state, {data}) => {
		return state.mergeDeep(
			fromJS(
				{
					departments: {
						total: data[department]
					},
					locations: {
						total: data[location]
					},
					teams: {
						total: data[team]
					}
				}
			)
		);
	},

	[listsActionTypes.CLEAR_LISTS]: state => state.clear(),

	[listsActionTypes.FETCH_INDEX_FAILURE]: updateListLoading(false),

	[listsActionTypes.FETCH_INDEX_REQUEST]: updateListLoading(true),

	[listsActionTypes.FETCH_INDEX_SUCCESS]: (state, {data, listName}) => {
		const {permissionCreate, results, total} = data;

		return state.updateIn(
			[listName, 'data'],
			List(),
			curResults => curResults.concat(results)
		).mergeIn(
			[listName],
			fromJS(
				{
					loading: false,
					permissionCreate,
					total
				}
			)
		);
	},

	[peopleActionTypes.FETCH_NEW_PEOPLE_FAILURE]: state => state.setIn(['newPeople', 'loading'], false),

	[peopleActionTypes.FETCH_NEW_PEOPLE_REQUEST]: state => state.setIn(['newPeople', 'loading'], true),

	[peopleActionTypes.FETCH_NEW_PEOPLE_SUCCESS]: replaceList('newPeople'),

	[topicActionTypes.ADD_FEATURED_TOPIC_SUCCESS]: (state, {data}) => {
		return state.updateIn(
			['featuredTopics', 'data'],
			OrderedSet(),
			curResults => OrderedSet([data]).concat(curResults)
		).updateIn(
			['featuredTopics', 'total'],
			0,
			total => total + 1
		);
	},

	[topicActionTypes.FETCH_FEATURED_FAILURE]: state => state.setIn(['featuredTopics', 'loading'], false),

	[topicActionTypes.FETCH_FEATURED_REQUEST]: state => state.setIn(['featuredTopics', 'loading'], true),

	[topicActionTypes.FETCH_FEATURED_SUCCESS]: replaceList('featuredTopics'),

	[topicActionTypes.REMOVE_FEATURED_TOPIC_SUCCESS]: (state, {id}) => {
		return state.updateIn(
			['featuredTopics', 'data'],
			OrderedSet(),
			curResults => curResults.filter(
				item => item !== id
			)
		).updateIn(
			['featuredTopics', 'total'],
			total => total - 1
		);
	}
};

export default createReducer(Map(), actionHandlers);