import {actionTypes} from '../actions/vehicles';
import {composeReducers} from '../lib/util';
import createBaseReducer from './crud';
import createSupplementalReducer from './supplemental';

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Vehicle'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Vehicle'
		}
	)
);