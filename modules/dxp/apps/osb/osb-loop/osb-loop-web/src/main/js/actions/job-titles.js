import createBase from './crud';
import {CALL_API} from '../middleware/api';
import {createActionTypes} from '../lib/util';

const controller = 'job_titles';

const base = createBase(
	{
		controller,
		name: 'jobTitle',
		plural: 'jobTitles'
	}
);

const actionTypes = {
	...base.actionTypes,
	...createActionTypes('set', 'inactive')
};

export function setInactive({id, inactive}) {
	return {
		[CALL_API]: {
			controller,
			controllerMethod: inactive ? 'deactivate.json' : 'activate.json',
			data: {id},
			types: [actionTypes.SET_INACTIVE_REQUEST, actionTypes.SET_INACTIVE_SUCCESS, actionTypes.SET_INACTIVE_FAILURE]
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
	add as addJobTitle,
	create as createJobTitle,
	destroy as destroyJobTitle,
	fetch as fetchJobTitle,
	hydrate as hydrateJobTitle,
	search as searchJobTitles,
	update as updateJobTitle
};