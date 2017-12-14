import {actionTypes} from '../actions/casework-activities';
import {composeReducers} from '../lib/util';
import createBaseReducer from './crud';
import createSupplementalReducer from './supplemental';

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Report'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Report'
		}
	)
);