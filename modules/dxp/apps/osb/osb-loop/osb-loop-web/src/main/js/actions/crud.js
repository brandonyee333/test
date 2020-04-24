import {Map} from 'immutable';

import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const crudActions = ['add', 'create', 'destroy', 'fetch', 'search', 'update'];

export default ({controller, name, plural}) => {
	controller = controller || plural;

	const actionTypes = crudActions.reduce(
		(actions, action) => ({...actions, ...createActionTypes(action, name, true)}),
		{}
	);

	actionTypes.HYDRATE = `HYDRATE_${name.toUpperCase()}`;

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

		destroy: id => {
			return (dispatch, getState) => {
				return dispatch(
					{
						[CALL_API]: {
							controller,
							controllerMethod: 'delete.json',
							data: {
								id
							},
							types: [actionTypes.DESTROY_REQUEST, actionTypes.DESTROY_SUCCESS, actionTypes.DESTROY_FAILURE]
						},
						id,
						previous: getState().getIn([plural, id], Map()).toJS()
					}
				);
			};
		},

		fetch: id => {
			return {
				id,
				[CALL_API]: {
					controller,
					controllerMethod: 'view.json',
					data: {
						id
					},
					types: [actionTypes.FETCH_REQUEST, actionTypes.FETCH_SUCCESS, actionTypes.FETCH_FAILURE]
				}
			};
		},

		hydrate: (id, data) => {
			return {
				data,
				id,
				type: actionTypes.HYDRATE
			};
		},

		search: data => {
			const {keywords} = data;

			return {
				keywords,
				[CALL_API]: {
					controller,
					controllerMethod: 'search.json',
					data,
					method: 'GET',
					types: [actionTypes.SEARCH_REQUEST, actionTypes.SEARCH_SUCCESS, actionTypes.SEARCH_FAILURE]
				}
			};
		},

		update: data => {
			return {
				id: data.id,
				[CALL_API]: {
					controller,
					controllerMethod: 'update.json',
					data,
					types: [actionTypes.UPDATE_REQUEST, actionTypes.UPDATE_SUCCESS, actionTypes.UPDATE_FAILURE]
				}
			};
		}
	};

	return {
		actions,
		actionTypes
	};
};