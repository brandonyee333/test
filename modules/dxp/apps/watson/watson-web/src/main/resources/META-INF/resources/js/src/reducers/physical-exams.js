import {actionTypes} from '../actions/physical-exams';
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