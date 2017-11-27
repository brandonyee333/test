import {actionTypes} from '../actions/documents';
import {composeReducers} from '../lib/util';
import createBaseReducer from './crud';
import createSupplementalReducer from './supplemental';

export default composeReducers(
	createBaseReducer(
		{
			actionTypes,
			primaryKey: 'Document'
		}
	),
	createSupplementalReducer(
		{
			actionTypes,
			primaryKey: 'Document'
		}
	)
);