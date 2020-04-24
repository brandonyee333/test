import createBase from './crud';
import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const base = createBase(
	{
		name: 'topic',
		plural: 'topics'
	}
);

const actionTypes = {
	...base.actionTypes,
	...createActionTypes('add', 'featured_topic'),
	...createActionTypes('remove', 'add_topic'),
	...createActionTypes('fetch', 'experts'),
	...createActionTypes('fetch', 'featured'),
	...createActionTypes('remove', 'featured_topic'),
	...createActionTypes('remove', 'own_topic'),
	...createActionTypes('set', 'own_topic')
};

export function addFeaturedTopic(id) {
	return {
		[CALL_API]: {
			controller: 'topics',
			controllerMethod: 'addFeatured.json',
			data: {id},
			types: [actionTypes.ADD_FEATURED_TOPIC_REQUEST, actionTypes.ADD_FEATURED_TOPIC_SUCCESS, actionTypes.ADD_FEATURED_TOPIC_FAILURE]
		}
	};
}

export function fetchExperts({end, id, start}) {
	return {
		[CALL_API]: {
			controller: 'topics',
			controllerMethod: 'viewVerifiedExperts.json',
			data: {
				end,
				id,
				start
			},
			types: [actionTypes.FETCH_EXPERTS_REQUEST, actionTypes.FETCH_EXPERTS_SUCCESS, actionTypes.FETCH_EXPERTS_FAILURE]
		},
		id
	};
}

export function fetchFeaturedTopics() {
	return {
		[CALL_API]: {
			controller: 'topics',
			controllerMethod: 'viewFeatured.json',
			types: [actionTypes.FETCH_FEATURED_REQUEST, actionTypes.FETCH_FEATURED_SUCCESS, actionTypes.FETCH_FEATURED_FAILURE]
		}
	};
}

export function setOwnTopic(id) {
	return {
		[CALL_API]: {
			controller: 'topics',
			controllerMethod: 'setOwnTopic.json',
			data: {id},
			types: [actionTypes.SET_OWN_TOPIC_REQUEST, actionTypes.SET_OWN_TOPIC_SUCCESS, actionTypes.SET_OWN_TOPIC_FAILURE]
		}
	};
}

export function removeFeaturedTopic(id) {
	return {
		[CALL_API]: {
			controller: 'topics',
			controllerMethod: 'removeFeatured.json',
			data: {id},
			method: 'GET',
			types: [actionTypes.REMOVE_FEATURED_TOPIC_REQUEST, actionTypes.REMOVE_FEATURED_TOPIC_SUCCESS, actionTypes.REMOVE_FEATURED_TOPIC_FAILURE]
		},
		id
	};
}

export function removeOwnTopic(id) {
	return {
		[CALL_API]: {
			controller: 'topics',
			controllerMethod: 'removeOwnTopic.json',
			data: {id},
			types: [actionTypes.REMOVE_OWN_TOPIC_REQUEST, actionTypes.REMOVE_OWN_TOPIC_SUCCESS, actionTypes.REMOVE_OWN_TOPIC_FAILURE]
		}
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
	add as addTopic,
	create as createTopic,
	destroy as destroyTopic,
	fetch as fetchTopic,
	hydrate as hydrateTopic,
	search as searchTopics,
	update as updateTopic
};