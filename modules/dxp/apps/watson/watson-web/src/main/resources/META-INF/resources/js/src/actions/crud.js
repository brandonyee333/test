import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const crudActions = ['add', 'create', 'edit', 'destroy', 'index', 'search', 'update', 'view'];

export default ({controller, name}) => {
	const actionTypes = crudActions.reduce(
		(result, next) => (
			{
				...result,
				...createActionTypes(next, name, true)
			}
		),
		{}
	);

	const actions = {
		add: data => {
			return {
				[CALL_API]: {
					controller,
					controllerMethod: 'add.json',
					data,
					types: [actionTypes.ADD_REQUEST, actionTypes.ADD_SUCCESS, actionTypes.ADD_FAILURE]
				}
			};
		},

		create: data => {
			return {
				[CALL_API]: {
					controller,
					controllerMethod: 'create.json',
					data,
					types: [actionTypes.CREATE_REQUEST, actionTypes.CREATE_SUCCESS, actionTypes.CREATE_FAILURE]
				}
			};
		},

		destroy: (id = 0, controllerMethod = 'delete.json') => {
			return {
				id,
				[CALL_API]: {
					controller,
					controllerMethod,
					data: {
						id
					},
					types: [actionTypes.DESTROY_REQUEST, actionTypes.DESTROY_SUCCESS, actionTypes.DESTROY_FAILURE]
				}
			};
		},

		edit: id => {
			return {
				[CALL_API]: {
					controller,
					controllerMethod: 'edit.json',
					data: {
						id
					},
					types: [actionTypes.EDIT_REQUEST, actionTypes.EDIT_SUCCESS, actionTypes.EDIT_FAILURE]
				}
			};
		},

		index: (data = {}) => {
			const {end, id, key, start} = data;

			return {
				end,
				id,
				key,
				[CALL_API]: {
					controller,
					controllerMethod: 'index.json',
					data,
					types: [actionTypes.INDEX_REQUEST, actionTypes.INDEX_SUCCESS, actionTypes.INDEX_FAILURE]
				},
				start
			};
		},

		search: data => {
			const {end, key, keywords, start} = data;

			return {
				end,
				key,
				keywords,
				[CALL_API]: {
					controller,
					controllerMethod: 'search.json',
					data,
					types: [actionTypes.SEARCH_REQUEST, actionTypes.SEARCH_SUCCESS, actionTypes.SEARCH_FAILURE]
				},
				start
			};
		},

		update: (data = {}, controllerMethod = 'update.json') => {
			return {
				id: data.id,
				[CALL_API]: {
					controller,
					controllerMethod,
					data,
					types: [actionTypes.UPDATE_REQUEST, actionTypes.UPDATE_SUCCESS, actionTypes.UPDATE_FAILURE]
				}
			};
		},

		view: id => {
			return {
				id,
				[CALL_API]: {
					controller,
					controllerMethod: 'view.json',
					data: {
						id
					},
					types: [actionTypes.VIEW_REQUEST, actionTypes.VIEW_SUCCESS, actionTypes.VIEW_FAILURE]
				}
			};
		}
	};

	return {
		actions,
		actionTypes
	};
};