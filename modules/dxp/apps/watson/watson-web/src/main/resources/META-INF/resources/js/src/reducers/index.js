import {combineReducers} from 'redux-immutable';

import activities from './activities';
import addresses from './addresses';
import casework_activities from './casework-activities';
import counseling_reports from './counseling-reports';
import children from './children';
import display from './display';
import documents from './documents';
import histories from './histories';
import illnesses from './illnesses';
import incidents from './incidents';
import list_types from './list-types';
import people from './people';
import relationships from './relationships';
import legals from './legals';
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
		casework_activities,
		children,
		counseling_reports,
		display,
		documents,
		histories,
		illnesses,
		incidents,
		legals,
		list_types,
		people,
		relationships,
		resources,
		vehicles
	}
);