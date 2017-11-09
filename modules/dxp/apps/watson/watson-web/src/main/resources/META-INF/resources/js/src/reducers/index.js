import {combineReducers} from 'redux-immutable';

import activities from './activities';
import addresses from './addresses';
import children from './children';
import display from './display';
import histories from './histories';
import incidents from './incidents';
import list_types from './list-types';
import people from './people';
import relationships from './relationships';
import resources from './resources';
import vehicles from './vehicles';

function basePath(state) {
	return state || '';
}

export default combineReducers(
	{
		activities,
		addresses,
		basePath,
		children,
		display,
		histories,
		incidents,
		list_types,
		people,
		relationships,
		resources,
		vehicles
	}
);