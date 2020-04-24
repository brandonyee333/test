jest.unmock('../crud');
jest.unmock('../divisions');

import * as actions from '../divisions';
import {CALL_API} from '../../middleware/api';

describe(
	'addDivision',
	() => {
		const {addDivision} = actions;

		it(
			'should return an api call to add division',
			() => {
				const action = addDivision(
					{
						name: 'test',
						type: LoopConstants.types.team
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controller).toBe('divisions');
				expect(apiCall.controllerMethod).toBe('add.json');
			}
		);
	}
);

describe(
	'fetchChildDivisions',
	() => {
		const {fetchChildDivisions} = actions;

		it(
			'should return an api call to fetch child divisions',
			() => {
				const id = 1;
				const subtype = 1;

				const action = fetchChildDivisions({end: 5, id, subtype});

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.data.subtype).toBe(subtype);
				expect(apiCall.controllerMethod).toBe('viewChildLoopDivisions.json');
			}
		);
	}
);

describe(
	'fetchLeads',
	() => {
		const {fetchLeads} = actions;

		it(
			'should return an api call to fetch leads',
			() => {
				const id = 1;

				const action = fetchLeads(
					{
						end: 10,
						id,
						start: 0
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewLeads.json');
			}
		);

		it(
			'should return an api call with a default start',
			() => {
				const action = fetchLeads(
					{
						end: 10,
						id: 1
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.start).toBe(0);
			}
		);
	}
);

describe(
	'fetchMembers',
	() => {
		const {fetchMembers} = actions;

		it(
			'should return an api call to fetch members',
			() => {
				const id = 1;

				const action = fetchMembers(
					{
						end: 10,
						id,
						start: 0
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewMembers.json');
			}
		);

		it(
			'should return an api call with a default start',
			() => {
				const action = fetchMembers(
					{
						end: 10,
						id: 1
					}
				);

				const apiCall = action[CALL_API];

				expect(apiCall.data.start).toBe(0);
			}
		);
	}
);

describe(
	'fetchParentDivision',
	() => {
		const {fetchParentDivision} = actions;

		it(
			'should return an api call to fetch parent division',
			() => {
				const id = 1;

				const action = fetchParentDivision(id);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.data.id).toBe(id);
				expect(apiCall.controllerMethod).toBe('viewParentLoopDivision.json');
			}
		);
	}
);

describe(
	'fetchTypeTotals',
	() => {
		const {fetchTypeTotals} = actions;

		it(
			'should fetch the division type totals',
			() => {
				const action = fetchTypeTotals();

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('viewLoopDivisionTypeCounts.json');
			}

		);
	}
);

describe(
	'setChildDivisions',
	() => {
		const {setChildDivisions} = actions;

		it(
			'should return an api call to set child divisions',
			() => {
				const childId = 2;
				const id = 1;

				const action = setChildDivisions(
					{
						childDivisions: [{entityClassPK: childId}],
						id
					}
				);

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(action.subtype).toBe(0);
				expect(apiCall.controllerMethod).toBe('setChildLoopDivisions.json');
				expect(apiCall.data.childLoopDivisionIds[0]).toBe(childId);
				expect(apiCall.data.id).toBe(id);
			}
		);
	}
);

describe(
	'setParentDivision',
	() => {
		const {setParentDivision} = actions;

		it(
			'should return an api call to set parent division',
			() => {
				const id = 1;
				const parentDivisionId = 2;

				const action = setParentDivision({id, parentDivisionId});

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(action.subtype).toBe(0);
				expect(apiCall.controllerMethod).toBe('setParentLoopDivision.json');
				expect(apiCall.data.id).toBe(id);
				expect(apiCall.data.parentId).toBe(parentDivisionId);
			}
		);
	}
);

describe(
	'fetchNewDivisions',
	() => {
		const {fetchNewDivisions} = actions;

		it(
			'should return an api call to fetch new divisions',
			() => {
				const action = fetchNewDivisions();

				expect(typeof action).toBe('object');

				const apiCall = action[CALL_API];

				expect(apiCall.controllerMethod).toBe('viewNewDivisions.json');
			}
		);
	}
);

describe(
	'fetchHierarchy',
	() => {
		const {fetchHierarchy} = actions;

		it(
			'should return an api call to fetch division hierarchy',
			() => {
				const action = fetchHierarchy(5);

				const apiCall = action[CALL_API];

				expect(apiCall).toMatchSnapshot();
			}
		);
	}
);

describe(
	'setType',
	() => {
		const {setType} = actions;

		it(
			'should return an api call to set division type',
			() => {
				const action = setType(
					{
						id: 1,
						parentDivisionId: 2,
						subtype: 3,
						type: 4
					}
				);

				expect(action[CALL_API]).toMatchSnapshot();
			}
		);
	}
);