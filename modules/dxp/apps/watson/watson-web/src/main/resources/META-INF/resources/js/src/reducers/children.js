import {actionTypes} from '../actions/children';
import {composeReducers} from '../lib/util';
import createBaseReducer from './crud';
import createSupplementalReducer from './supplemental';

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Child'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Child'
		}
	)
);