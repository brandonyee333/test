import {
	arrayOf,
	normalize,
	Schema,
	unionOf
} from 'normalizr';

import {actionTypes as divisionActionTypes} from '../actions/divisions';
import {actionTypes as feedActionTypes} from '../actions/feeds';
import {actionTypes as followersActionTypes} from '../actions/followers';
import {actionTypes as homeActionTypes} from '../actions/home';
import {actionTypes as imageActionTypes} from '../actions/images';
import {actionTypes as jobTitleActionTypes} from '../actions/job-titles';
import {actionTypes as listActionTypes} from '../actions/lists';
import {actionTypes as pagesActionTypes} from '../actions/pages';
import {actionTypes as peopleActionTypes} from '../actions/people';
import {actionTypes as postActionTypes} from '../actions/posts';
import {actionTypes as topicActionTypes} from '../actions/topics';
import {classNameIdToSchema} from '../lib/util';

const classPKId = {idAttribute: 'entityClassPK'};

const division = new Schema('divisions', classPKId);
const jobTitle = new Schema('jobTitles', classPKId);
const page = new Schema('pages', classPKId);
const person = new Schema('people', classPKId);
const post = new Schema('posts', {idAttribute: 'assetEntrySetId'});
const topic = new Schema('topics', classPKId);

const entity = {
	divisions: division,
	jobTitles: jobTitle,
	people: person,
	topics: topic
};

const fullSearchRules = {
	divisions: {
		results: arrayOf(division)
	},
	pages: {
		results: arrayOf(page)
	},
	people: {
		results: arrayOf(person)
	},
	posts: {
		results: arrayOf(post)
	},
	topics: {
		results: arrayOf(topic)
	}
};

const schemaFilter = {
	schemaAttribute: item => classNameIdToSchema(item.entityClassNameId)
};

const pageRules = {
	childAssetEntrySets: arrayOf(post),
	foreignEntity: division,
	payload: {
		creator: person,
		likedParticipants: {
			participants: arrayOf(person)
		}
	}
};

const postRules = {
	childAssetEntrySets: arrayOf(post),
	payload: {
		creator: unionOf(entity, schemaFilter),
		likedParticipants: {
			participants: arrayOf(person)
		},
		sharedTo: arrayOf(entity, schemaFilter)
	}
};

page.define(pageRules);
post.define(postRules);

function normalizeEntityArray({results, total}, type) {
	const {entities, result} = normalize(results, arrayOf(type));

	return {
		entities,
		result: {
			results: result,
			total
		}
	};
}

function normalizePage({data}) {
	const {entities, result} = normalize(data, page);

	return {
		entities,
		result: entities.pages[result]
	};
}

function normalizePost({data}) {
	return normalize(data, postRules);
}

function normalizePosts({data}) {
	return normalize(data, arrayOf(post));
}

const hierarchyObj = {
	loopDivisionCompositeJSONObject: division
};

hierarchyObj.childLoopDivisionCompositeJSONArray = arrayOf(hierarchyObj);

const ACTION_MAP = {
	[divisionActionTypes.ADD_SUCCESS]: action => normalize(action.data, division),
	[divisionActionTypes.FETCH_CHILD_DIVISIONS_SUCCESS]: action => normalizeEntityArray(action.data, division),
	[divisionActionTypes.FETCH_HIERARCHY_SUCCESS]: action => normalize(action.data, hierarchyObj),
	[divisionActionTypes.FETCH_LEADS_SUCCESS]: action => normalizeEntityArray(action.data, person),
	[divisionActionTypes.FETCH_MEMBERS_SUCCESS]: action => normalizeEntityArray(action.data, person),
	[divisionActionTypes.FETCH_NEW_DIVISIONS_SUCCESS]: action => normalizeEntityArray(action.data, division),
	[divisionActionTypes.FETCH_PARENT_DIVISION_SUCCESS]: action => normalize(action.data, division),
	[divisionActionTypes.FETCH_SUCCESS]: action => {
		return normalize(
			action.data,
			{
				loopDivisionCompositeJSONObject: division,
				parentLoopDivisionCompositeJSONObject: division
			}
		);
	},
	[divisionActionTypes.SEARCH_SUCCESS]: action => normalize(action.data.results, arrayOf(division)),
	[divisionActionTypes.SET_CHILD_DIVISIONS_SUCCESS]: ({childDivisions}) => {
		return normalizeEntityArray(
			{
				results: childDivisions,
				total: childDivisions.length
			},
			division
		);
	},
	[divisionActionTypes.SET_PARENT_DIVISION_SUCCESS]: action => normalize(action.data.parentLoopDivisionCompositeJSONObject, division),
	[divisionActionTypes.SET_TYPE_SUCCESS]: action => {
		return normalize(
			action.data,
			{
				childLoopDivisionCompositeJSONObject: division,
				parentLoopDivisionCompositeJSONObject: division
			}
		);
	},
	[feedActionTypes.FETCH_COMMENTS_SUCCESS]: action => normalizeEntityArray(action.data, post),
	[feedActionTypes.FETCH_POSTS_SUCCESS]: normalizePosts,
	[followersActionTypes.FETCH_FOLLOWERS_SUCCESS]: action => normalize(action.data, arrayOf(person)),
	[homeActionTypes.FULL_SEARCH_SUCCESS]: action => normalize(action.data, fullSearchRules),
	[imageActionTypes.SET_COVER_IMAGE_SUCCESS]: action => normalize(action.data, unionOf(entity, schemaFilter)),
	[imageActionTypes.SET_PROFILE_IMAGE_SUCCESS]: action => normalize(action.data, unionOf(entity, schemaFilter)),
	[jobTitleActionTypes.ADD_SUCCESS]: action => normalize(action.data, jobTitle),
	[jobTitleActionTypes.SET_INACTIVE_SUCCESS]: action => normalize(action.data, jobTitle),
	[listActionTypes.FETCH_INDEX_SUCCESS]: action => {
		const {permissionCreate, results, total} = action.data;

		const {entities, result} = normalize(results, arrayOf(entity[classNameIdToSchema(action.entityClassNameId)]));

		return {
			entities,
			result: {
				permissionCreate,
				results: result,
				total
			}
		};
	},
	[pagesActionTypes.ADD_SUCCESS]: normalizePage,
	[pagesActionTypes.FETCH_PAGES_SUCCESS]: action => normalizeEntityArray(action.data, page),
	[pagesActionTypes.FETCH_SUCCESS]: normalizePage,
	[pagesActionTypes.SEARCH_SUCCESS]: action => normalize(action.data.results, arrayOf(page)),
	[pagesActionTypes.UPDATE_SUCCESS]: normalizePage,
	[peopleActionTypes.ADD_SUCCESS]: action => normalize(action.data, person),
	[peopleActionTypes.FETCH_DIVISIONS_SUCCESS]: action => normalizeEntityArray(action.data, division),
	[peopleActionTypes.FETCH_EXPERTISE_SUCCESS]: action => normalize(action.data, arrayOf(topic)),
	[peopleActionTypes.FETCH_FOLLOWING_SUCCESS]: action => normalizeEntityArray(action.data, entity[classNameIdToSchema(action.entityClassNameId)]),
	[peopleActionTypes.FETCH_LEADING_DIVISIONS_SUCCESS]: action => normalize(action.data, arrayOf(division)),
	[peopleActionTypes.FETCH_MANAGERS_SUCCESS]: action => normalizeEntityArray(action.data, person),
	[peopleActionTypes.FETCH_NEW_PEOPLE_SUCCESS]: action => normalizeEntityArray(action.data, person),
	[peopleActionTypes.FETCH_SUBORDINATES_SUCCESS]: action => normalizeEntityArray(action.data, person),
	[peopleActionTypes.SEARCH_SUCCESS]: action => normalize(action.data.results, arrayOf(person)),
	[postActionTypes.CREATE_SUCCESS]: normalizePost,
	[postActionTypes.FETCH_SUCCESS]: normalizePost,
	[postActionTypes.HYDRATE]: normalizePost,
	[postActionTypes.SEARCH_SUCCESS]: action => normalize(action.data.results, arrayOf(post)),
	[postActionTypes.UPDATE_SUCCESS]: normalizePost,
	[topicActionTypes.ADD_FEATURED_TOPIC_SUCCESS]: action => normalize(action.data, topic),
	[topicActionTypes.ADD_SUCCESS]: action => normalize(action.data, topic),
	[topicActionTypes.FETCH_EXPERTS_SUCCESS]: action => normalizeEntityArray(action.data, person),
	[topicActionTypes.FETCH_FEATURED_SUCCESS]: action => normalizeEntityArray(action.data, topic),
	[topicActionTypes.FETCH_SUCCESS]: action => {
		return normalize(
			action.data,
			{
				loopTopicCompositeJSONObject: topic,
				parentLoopTopicCompositeJSONObject: topic
			}
		);
	},
	[topicActionTypes.REMOVE_OWN_TOPIC_SUCCESS]: action => normalize(action.data, topic),
	[topicActionTypes.SEARCH_SUCCESS]: action => normalize(action.data.results, arrayOf(topic)),
	[topicActionTypes.SET_OWN_TOPIC_SUCCESS]: action => normalize(action.data, topic)
};

export default store => next => action => {
	const actionHandler = ACTION_MAP[action.type];

	if (actionHandler) {
		const {entities, result} = actionHandler(action);

		action = {
			...action,
			data: result,
			entities
		};
	}

	return next(action);
};