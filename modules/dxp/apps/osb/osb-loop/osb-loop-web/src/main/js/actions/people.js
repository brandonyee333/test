import {capitalize} from 'lodash';

import createBase from './crud';
import {CALL_API} from '../middleware/api';
import {classNameIdToSchema, createActionTypes, getTypeName} from '../lib/util';

const base = createBase(
	{
		name: 'person',
		plural: 'people'
	}
);

const controller = 'people';

const actionTypes = {
	...base.actionTypes,
	...createActionTypes('fetch', 'following'),
	...createActionTypes('fetch', 'divisions'),
	...createActionTypes('fetch', 'expertise'),
	...createActionTypes('fetch', 'leading_divisions'),
	...createActionTypes('fetch', 'managers'),
	...createActionTypes('fetch', 'new_people'),
	...createActionTypes('fetch', 'subordinates')
};

export function fetchDivisions({divisionType, end, id, start = 0}) {
	return {
		id,
		[CALL_API]: {
			controller,
			controllerMethod: 'viewDivisions.json',
			data: {
				end,
				id,
				start,
				type: divisionType
			},
			types: [actionTypes.FETCH_DIVISIONS_REQUEST, actionTypes.FETCH_DIVISIONS_SUCCESS, actionTypes.FETCH_DIVISIONS_FAILURE]
		},
		divisionType: getTypeName(divisionType)
	};
}

export function fetchExpertise(id) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewVerifiedLoopTopics.json',
			data: {
				id
			},
			types: [actionTypes.FETCH_EXPERTISE_REQUEST, actionTypes.FETCH_EXPERTISE_SUCCESS, actionTypes.FETCH_EXPERTISE_FAILURE]
		},
		id
	};
}

export function fetchFollowing({classNameId, end, id, start}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: `viewFollowing${capitalize(classNameIdToSchema(classNameId))}.json`,
			data: {
				end,
				id,
				start
			},
			types: [actionTypes.FETCH_FOLLOWING_REQUEST, actionTypes.FETCH_FOLLOWING_SUCCESS, actionTypes.FETCH_FOLLOWING_FAILURE]
		},
		end,
		entityClassNameId: classNameId,
		id,
		start
	};
}

export function fetchLeadingDivisions(id) {
	return {
		id,
		[CALL_API]: {
			controller,
			controllerMethod: 'viewLeadingDivisions.json',
			data: {id},
			types: [actionTypes.FETCH_LEADING_DIVISIONS_REQUEST, actionTypes.FETCH_LEADING_DIVISIONS_SUCCESS, actionTypes.FETCH_LEADING_DIVISIONS_FAILURE]
		}
	};
}

export function fetchManagers({end, id, start = 0}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewManagers.json',
			data: {
				end,
				id,
				start
			},
			method: 'GET',
			types: [actionTypes.FETCH_MANAGERS_REQUEST, actionTypes.FETCH_MANAGERS_SUCCESS, actionTypes.FETCH_MANAGERS_FAILURE]
		},
		id
	};
}

export function fetchNewPeople() {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewNewPeople.json',
			types: [actionTypes.FETCH_NEW_PEOPLE_REQUEST, actionTypes.FETCH_NEW_PEOPLE_SUCCESS, actionTypes.FETCH_NEW_PEOPLE_FAILURE]
		}
	};
}

export function fetchSubordinates({end, id, start = 0}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewSubordinates.json',
			data: {
				end,
				id,
				start
			},
			method: 'GET',
			types: [actionTypes.FETCH_SUBORDINATES_REQUEST, actionTypes.FETCH_SUBORDINATES_SUCCESS, actionTypes.FETCH_SUBORDINATES_FAILURE]
		},
		id
	};
}

const {
	add,
	create,
	destroy,
	fetch,
	hydrate,
	search,
	update
} = base.actions;

export {
	actionTypes,
	add as addPerson,
	create as createPerson,
	destroy as destroyPerson,
	fetch as fetchPerson,
	hydrate as hydratePerson,
	search as searchPeople,
	update as updatePerson
};