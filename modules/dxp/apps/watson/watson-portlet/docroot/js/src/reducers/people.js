import {actionTypes} from '../actions/people';
import {composeReducers} from '../lib/util';
import createBaseReducer from './crud';
import createSupplementalReducer from './supplemental';

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Person'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Person'
		}
	)
);