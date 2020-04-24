jest.unmock('../../actions/crud');
jest.unmock('../../actions/divisions');
jest.unmock('../../actions/feeds');
jest.unmock('../../actions/followers');
jest.unmock('../../actions/people');
jest.unmock('../../actions/posts');
jest.unmock('../../actions/topics');
jest.unmock('../../lib/util');
jest.unmock('../normalizer');
jest.unmock('normalizr');

import {range} from 'lodash';

import middleware from '../normalizer';
import {actionTypes as divisionActionTypes} from '../../actions/divisions';
import {actionTypes as feedActionTypes} from '../../actions/feeds';
import {actionTypes as followersActionTypes} from '../../actions/followers';
import {actionTypes as homeActionTypes} from '../../actions/home';
import {actionTypes as imageActionTypes} from '../../actions/images';
import {actionTypes as jobTitleActionTypes} from '../../actions/job-titles';
import {actionTypes as listActionTypes} from '../../actions/lists';
import {actionTypes as pagesActionTypes} from '../../actions/pages';
import {actionTypes as peopleActionTypes} from '../../actions/people';
import {actionTypes as postActionTypes} from '../../actions/posts';
import {actionTypes as topicActionTypes} from '../../actions/topics';

const {keys} = Object;

const {classNameIds} = LoopConstants;

const {
	getDivision,
	getJobTitle,
	getPage,
	getPerson,
	getPost,
	getTopic
} = LoopAssets;

function createDataArray(entityType, total) {
	let getType;

	if (entityType === 'division') {
		getType = getDivision;
	}
	else if (entityType === 'person') {
		getType = getPerson;
	}
	else if (entityType === 'topic') {
		getType = getTopic;
	}
	else if (entityType === 'jobTitle') {
		getType = getJobTitle;
	}

	return range(total).map(count => getType(count));
}

function createDataWithTotal(entityType, total) {
	return {
		results: createDataArray(entityType, total),
		total
	};
}

function getNewAction(action) {
	const next = jest.fn();

	middleware()(next)(action);

	return next.mock.calls[0][0];
}

const page = {
	classNameId: classNameIds.divisions,
	classPK: 0,
	createTime: 1483982880145,
	creator: getPerson(),
	entityClassPK: 2,
	modifiedTime: 1483982880145,
	payload: {
		creator: getPerson(),
		message: 'test',
		rawMessage: 'raw test'
	},
	title: 'testing test'
};

const post = {
	assetEntrySetId: 25,
	childAssetEntrySets: [
		{assetEntrySetId: 8},
		{assetEntrySetId: 9},
		{assetEntrySetId: 10}
	],
	entityClassPK: 25,
	payload: {
		creator: getPerson(5),
		likedParticipants: {
			participants: createDataArray('person', 2)
		},
		sharedTo: [
			getDivision(5),
			getDivision(6),
			getTopic(7)
		]
	}
};

const postArray = [post, getPost(1), getPost(2)];

describe(
	'normalizer middlware',
	() => {
		it(
			'should only modify actions that need to be normalized',
			() => {
				const action = {
					type: 'TEST'
				};

				const newAction = getNewAction(action);

				expect(newAction.entities).toBeUndefined();
			}
		);

		it(
			'should normalize a response with post data',
			() => {
				const action = {
					data: post,
					type: postActionTypes.FETCH_SUCCESS
				};

				const newAction = getNewAction(action);

				const {entities} = newAction;

				expect(keys(entities.divisions).length).toBe(2);
				expect(keys(entities.people).length).toBe(3);
				expect(keys(entities.posts).length).toBe(3);
				expect(keys(entities.topics).length).toBe(1);

				const creator = {
					id: 5,
					schema: 'people'
				};

				expect(newAction.data.payload.creator).toEqual(creator);
			}
		);

		it(
			'should normalize an array of posts in a fetch posts request',
			() => {
				const action = {
					data: postArray,
					type: feedActionTypes.FETCH_POSTS_SUCCESS
				};

				const newAction = getNewAction(action);

				const {entities} = newAction;

				expect(keys(entities.divisions).length).toBe(2);
				expect(keys(entities.people).length).toBe(4);
				expect(keys(entities.posts).length).toBe(6);
				expect(keys(entities.topics).length).toBe(1);
			}
		);

		it(
			'should normalize entities in a full search request',
			() => {

				const action = {
					data: {
						divisions: createDataWithTotal('division', 2),
						people: createDataWithTotal('person', 4),
						topics: createDataWithTotal('topic', 3)
					},
					type: homeActionTypes.FULL_SEARCH_SUCCESS
				};

				const newAction = getNewAction(action);

				const {entities} = newAction;

				expect(keys(entities.divisions).length).toBe(2);
				expect(keys(entities.people).length).toBe(4);
				expect(keys(entities.topics).length).toBe(3);
			}
		);

		it(
			'should normalize a person in set cover image request',
			() => {
				const action = {
					data: getPerson(20),
					type: imageActionTypes.SET_COVER_IMAGE_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(1);
			}
		);

		it(
			'should normalize a person in set profile image request',
			() => {
				const action = {
					data: getPerson(8),
					type: imageActionTypes.SET_PROFILE_IMAGE_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(1);
			}
		);

		it(
			'should normalize divisions in fetch index request',
			() => {
				const total = 10;

				const action = {
					data: createDataWithTotal('division', total),
					entityClassNameId: classNameIds.divisions,
					type: listActionTypes.FETCH_INDEX_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(total);
			}
		);

		it(
			'should normalize divisions in an add division request',
			() => {
				const action = {
					data: getDivision(88),
					type: divisionActionTypes.ADD_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(1);
			}
		);

		it(
			'should normalize people in fetch index request',
			() => {
				const total = 40;

				const action = {
					data: createDataWithTotal('person', total),
					entityClassNameId: classNameIds.people,
					type: listActionTypes.FETCH_INDEX_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize topics in fetch index request',
			() => {
				const total = 9;

				const action = {
					data: createDataWithTotal('topic', total),
					entityClassNameId: classNameIds.topics,
					type: listActionTypes.FETCH_INDEX_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(total);
			}
		);

		it(
			'should normalize people in an add person request',
			() => {
				const action = {
					data: getPerson(99),
					type: peopleActionTypes.ADD_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(1);
			}
		);

		it(
			'should normalize topics in a fetch following request',
			() => {
				const total = 2;

				const action = {
					data: createDataWithTotal('topic', total),
					entityClassNameId: classNameIds.topics,
					type: peopleActionTypes.FETCH_FOLLOWING_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(total);
			}
		);

		it(
			'should always normalize people in a fetch followers request',
			() => {
				const total = 2;

				const action = {
					data: createDataArray('person', total),
					entityClassNameId: classNameIds.topics,
					type: followersActionTypes.FETCH_FOLLOWERS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize divisions in fetch parent division request',
			() => {
				const action = {
					data: getDivision(),
					type: divisionActionTypes.FETCH_PARENT_DIVISION_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(1);
			}
		);

		it(
			'should normalize divisions in fetch division request',
			() => {
				const action = {
					data: {
						loopDivisionCompositeJSONObject: getDivision(0),
						parentLoopDivisionCompositeJSONObject: getDivision(1)
					},
					type: divisionActionTypes.FETCH_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(2);
			}
		);

		it(
			'should normalize divisions in search division request',
			() => {
				const total = 5;

				const action = {
					data: createDataWithTotal('division', total),
					type: divisionActionTypes.SEARCH_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(total);
			}
		);

		it(
			'should normalize divisions in a set child divisions request',
			() => {
				const childDivision = createDataArray('division', 1);

				const action = {
					childDivisions: childDivision,
					data: {
						childLoopDivisionCompositesJSONArray: childDivision,
						parentLoopDivisionCompositeJSONObject: getDivision(2)
					},
					type: divisionActionTypes.SET_CHILD_DIVISIONS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(1);
			}
		);

		it(
			'should normalize divisions in a set parent division request',
			() => {
				const action = {
					data: {
						childLoopDivisionCompositesJSONArray: createDataArray('division', 1),
						parentLoopDivisionCompositeJSONObject: getDivision(2)
					},
					type: divisionActionTypes.SET_PARENT_DIVISION_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(1);
			}
		);

		it(
			'should normalize divisions in a fetch child divisions request',
			() => {
				const total = 6;

				const action = {
					data: createDataWithTotal('division', total),
					type: divisionActionTypes.FETCH_CHILD_DIVISIONS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(total);
			}
		);

		it(
			'should normalize divisions in a fetch divisions request',
			() => {
				const total = 6;

				const action = {
					data: {
						results: createDataArray('division', total),
						total
					},
					type: peopleActionTypes.FETCH_DIVISIONS_SUCCESS
				};

				const newAction = getNewAction(action);

				const {entities} = newAction;

				expect(keys(entities.divisions).length).toBe(total);
			}
		);

		it(
			'should normalize topics on fetch expertise success',
			() => {
				const total = 5;

				const action = {
					data: createDataArray('topic', total),
					type: peopleActionTypes.FETCH_EXPERTISE_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(5);
			}
		);
		it(
			'should normalize divisions in a fetch leading divisions request',
			() => {
				const total = 6;

				const action = {
					data: createDataArray('division', total),
					type: peopleActionTypes.FETCH_LEADING_DIVISIONS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(total);
			}
		);

		it(
			'should normalize divisions when fetching new divisions',
			() => {
				const total = 6;

				const action = {
					data: createDataWithTotal('division', total),
					type: divisionActionTypes.FETCH_NEW_DIVISIONS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(total);
			}
		);

		it(
			'should normalize job titles in an add job title request',
			() => {
				const action = {
					data: getJobTitle(99),
					type: jobTitleActionTypes.ADD_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.jobTitles).length).toBe(1);
			}
		);

		it(
			'should normalize job titles in a set inactive request',
			() => {
				const action = {
					data: getJobTitle(85),
					type: jobTitleActionTypes.SET_INACTIVE_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.jobTitles).length).toBe(1);
			}
		);

		it(
			'should normalize people in a fetch leads request',
			() => {
				const total = 4;

				const action = {
					data: createDataWithTotal('person', total),
					type: divisionActionTypes.FETCH_LEADS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize people in a fetch members request',
			() => {
				const total = 4;

				const action = {
					data: createDataWithTotal('person', total),
					type: divisionActionTypes.FETCH_MEMBERS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize people in a fetch managers request',
			() => {
				const total = 2;

				const action = {
					data: createDataWithTotal('person', total),
					type: peopleActionTypes.FETCH_MANAGERS_SUCCESS
				};

				const newAction = getNewAction(action);

				const {entities} = newAction;

				expect(keys(entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize people in a fetch subordinates request',
			() => {
				const total = 10;

				const action = {
					data: createDataWithTotal('person', total),
					type: peopleActionTypes.FETCH_SUBORDINATES_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize people in a search request',
			() => {
				const total = 6;

				const action = {
					data: createDataWithTotal('person', total),
					type: peopleActionTypes.SEARCH_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize people when fetching new hires',
			() => {
				const total = 6;

				const action = {
					data: createDataWithTotal('person', total),
					type: peopleActionTypes.FETCH_NEW_PEOPLE_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize posts in a search request',
			() => {
				const action = {
					data: {
						results: postArray,
						total: 3
					},
					type: postActionTypes.SEARCH_SUCCESS
				};

				const newAction = getNewAction(action);

				const {entities} = newAction;

				expect(keys(entities.divisions).length).toBe(2);
				expect(keys(entities.people).length).toBe(4);
				expect(keys(entities.posts).length).toBe(6);
				expect(keys(entities.topics).length).toBe(1);
			}
		);

		it(
			'should normalize people in fetch experts request',
			() => {
				const total = 12;

				const action = {
					data: {
						results: createDataArray('person', total),
						total
					},
					type: topicActionTypes.FETCH_EXPERTS_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
			}
		);

		it(
			'should normalize divisions in fetch topic request',
			() => {
				const action = {
					data: {
						loopTopicCompositeJSONObject: getTopic(0),
						parentLoopTopicCompositeJSONObject: getTopic(1)
					},
					type: topicActionTypes.FETCH_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(2);
			}
		);

		it(
			'should normalize topics in an add topic request',
			() => {
				const action = {
					data: getTopic(77),
					type: topicActionTypes.ADD_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(1);
			}
		);

		it(
			'should normalize topics in a remove own topic request',
			() => {
				const action = {
					data: getPerson(10),
					type: topicActionTypes.REMOVE_OWN_TOPIC_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(1);
			}
		);

		it(
			'should normalize topics in a set own topic request',
			() => {
				const action = {
					data: getPerson(10),
					type: topicActionTypes.SET_OWN_TOPIC_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(1);
			}
		);

		it(
			'should normalize topics in search request',
			() => {
				const total = 8;

				const action = {
					data: createDataWithTotal('topic', total),
					type: topicActionTypes.SEARCH_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(total);
			}
		);

		it(
			'should normalize topics when adding new featured topic',
			() => {
				const action = {
					data: getTopic(),
					type: topicActionTypes.ADD_FEATURED_TOPIC_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(1);
			}
		);

		it(
			'should normalize topics when fetching for featured topics',
			() => {
				const total = 8;

				const action = {
					data: createDataWithTotal('topic', total),
					type: topicActionTypes.FETCH_FEATURED_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.topics).length).toBe(total);
			}
		);

		it(
			'should normalize divisions when fetching for divison hierarchy',
			() => {
				const divisionArray = createDataArray('division', 20).map(
					item => (
						{
							childLoopDivisionCompositeJSONArray: {
								childLoopDivisionCompositeJSONArray: createDataArray('division', 5),
								loopDivisionCompositeJSONObject: getDivision()
							},
							loopDivisionCompositeJSONObject: item
						}
					)
				);

				const action = {
					data: {
						childLoopDivisionCompositeJSONArray: divisionArray,
						loopDivisionCompositeJSONObject: getDivision()
					},
					type: divisionActionTypes.FETCH_HIERARCHY_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions)).toMatchSnapshot();
			}
		);

		it(
			'should normalize divisions on set type success',
			() => {
				const action = {
					data: {
						childLoopDivisionCompositeJSONObject: getDivision(),
						parentLoopDivisionCompositeJSONObject: getDivision(1)
					},
					type: divisionActionTypes.SET_TYPE_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.divisions).length).toBe(2);
			}
		);

		it(
			'should normalize pages and people on fetch pages success',
			() => {
				const total = 1;

				const action = {
					data: {
						results: [page],
						total
					},
					end: total,
					id: 1,
					reverseSort: true,
					sortFieldName: 'title',
					start: 0,
					type: pagesActionTypes.FETCH_PAGES_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(total);
				expect(keys(newAction.entities.pages).length).toBe(total);
			}
		);

		it(
			'should normalize people on fetch page success',
			() => {
				const action = {
					data: page,
					id: 1,
					type: pagesActionTypes.FETCH_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(1);
			}
		);

		it(
			'should normalize people on add page success',
			() => {
				const action = {
					data: page,
					type: pagesActionTypes.ADD_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(1);
			}
		);

		it(
			'should normalize people on update page success',
			() => {
				const action = {
					data: page,
					type: pagesActionTypes.UPDATE_SUCCESS
				};

				const newAction = getNewAction(action);

				expect(keys(newAction.entities.people).length).toBe(1);
			}
		);

		it(
			'should normalize pages in a search request',
			() => {
				const action = {
					data: {
						results: range(3).map(count => getPage(count)),
						total: 3
					},
					type: pagesActionTypes.SEARCH_SUCCESS
				};

				const newAction = getNewAction(action);

				const {entities} = newAction;

				expect(keys(entities.divisions).length).toBe(3);
				expect(keys(entities.pages).length).toBe(3);
			}
		);
	}
);