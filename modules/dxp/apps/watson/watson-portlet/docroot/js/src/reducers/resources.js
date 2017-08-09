import {actionTypes} from '../actions/resources';
import {composeReducers} from '../lib/util';
import createBaseReducer from './crud';
import createSupplementalReducer from './supplemental';

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Resource'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Resource'
		}
	)
);