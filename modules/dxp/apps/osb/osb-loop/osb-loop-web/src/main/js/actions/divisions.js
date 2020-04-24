import createBase from './crud';
import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const base = createBase(
	{
		name: 'division',
		plural: 'divisions'
	}
);

const controller = 'divisions';

const actionTypes = {
	...base.actionTypes,
	...createActionTypes('fetch', 'child_divisions'),
	...createActionTypes('fetch', 'hierarchy'),
	...createActionTypes('fetch', 'leads'),
	...createActionTypes('fetch', 'members'),
	...createActionTypes('fetch', 'new_divisions'),
	...createActionTypes('fetch', 'parent_division'),
	...createActionTypes('fetch', 'type_totals'),
	...createActionTypes('set', 'child_divisions'),
	...createActionTypes('set', 'parent_division'),
	...createActionTypes('set', 'type')
};

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
	create as createDivision,
	destroy as destroyDivision,
	fetch as fetchDivision,
	hydrate as hydrateDivision,
	search as searchDivisions,
	update as updateDivision
};

export function addDivision(data) {
	return {
		...add(data),
		divisionType: data.type
	};
}

export function fetchHierarchy(id, type) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewLoopDivisionHierarchy.json',
			data: {
				id,
				type
			},
			method: 'GET',
			types: [actionTypes.FETCH_HIERARCHY_REQUEST, actionTypes.FETCH_HIERARCHY_SUCCESS, actionTypes.FETCH_HIERARCHY_FAILURE]
		}
	};
}

export function fetchChildDivisions({end, id, start = 0, subtype = 0, type}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewChildLoopDivisions.json',
			data: {
				end,
				id,
				start,
				subtype,
				type
			},
			method: 'GET',
			types: [actionTypes.FETCH_CHILD_DIVISIONS_REQUEST, actionTypes.FETCH_CHILD_DIVISIONS_SUCCESS, actionTypes.FETCH_CHILD_DIVISIONS_FAILURE]
		},
		id,
		start,
		subtype
	};
}

export function fetchLeads({end, id, start = 0}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewLeads.json',
			data: {
				end,
				id,
				start
			},
			method: 'GET',
			types: [actionTypes.FETCH_LEADS_REQUEST, actionTypes.FETCH_LEADS_SUCCESS, actionTypes.FETCH_LEADS_FAILURE]
		},
		id,
		start
	};
}

export function fetchMembers({end, id, start = 0}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewMembers.json',
			data: {
				end,
				id,
				start
			},
			method: 'GET',
			types: [actionTypes.FETCH_MEMBERS_REQUEST, actionTypes.FETCH_MEMBERS_SUCCESS, actionTypes.FETCH_MEMBERS_FAILURE]
		},
		id,
		start
	};
}

export function fetchNewDivisions() {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewNewDivisions.json',
			types: [actionTypes.FETCH_NEW_DIVISIONS_REQUEST, actionTypes.FETCH_NEW_DIVISIONS_SUCCESS, actionTypes.FETCH_NEW_DIVISIONS_FAILURE]
		}
	};
}

export function fetchParentDivision(id) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewParentLoopDivision.json',
			data: {
				id
			},
			method: 'GET',
			types: [actionTypes.FETCH_PARENT_DIVISION_REQUEST, actionTypes.FETCH_PARENT_DIVISION_SUCCESS, actionTypes.FETCH_PARENT_DIVISION_FAILURE]
		},
		id
	};
}

export function fetchTypeTotals() {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'viewLoopDivisionTypeCounts.json',
			method: 'GET',
			types: [actionTypes.FETCH_TYPE_TOTALS_REQUEST, actionTypes.FETCH_TYPE_TOTALS_SUCCESS, actionTypes.FETCH_TYPE_TOTALS_FAILURE]
		}
	};
}

export function setChildDivisions({childDivisions, id}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'setChildLoopDivisions.json',
			data: {
				childLoopDivisionIds: childDivisions.map(childDivision => childDivision.entityClassPK),
				id
			},
			types: [actionTypes.SET_CHILD_DIVISIONS_REQUEST, actionTypes.SET_CHILD_DIVISIONS_SUCCESS, actionTypes.SET_CHILD_DIVISIONS_FAILURE]
		},
		childDivisions,
		id,
		subtype: 0
	};
}

export function setParentDivision({id, parentDivisionId}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'setParentLoopDivision.json',
			data: {
				id,
				parentId: parentDivisionId
			},
			types: [actionTypes.SET_PARENT_DIVISION_REQUEST, actionTypes.SET_PARENT_DIVISION_SUCCESS, actionTypes.SET_PARENT_DIVISION_FAILURE]
		},
		id,
		subtype: 0
	};
}

export function setType({id, parentDivisionId, subtype, type}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: 'setType.json',
			data: {
				id,
				parentId: parentDivisionId,
				subtype,
				type
			},
			types: [actionTypes.SET_TYPE_REQUEST, actionTypes.SET_TYPE_SUCCESS, actionTypes.SET_TYPE_FAILURE]
		}
	};
}