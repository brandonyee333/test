import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

import {error} from '../types/generic';
import {fixPackJSONObject} from '../types/highlights';
import {jiraIssueJSONObject} from '../types/changelog';

import Alert from './Alert';
import SortableTable from './SortableTable';

TableResults.propTypes = {
	jsonObject: PropTypes.oneOfType(
		[error, fixPackJSONObject, jiraIssueJSONObject]
	).isRequired,
	tab: PropTypes.shape(
		{
			tabDescription: PropTypes.string,
			tabName: PropTypes.string
		}
	).isRequired
};

export default function TableResults({jsonObject, tab}) {
	return (
		<Fragment>
			{!!jsonObject.error && (
				<Alert type="danger">{jsonObject.error.message}</Alert>
			)}

			{!!jsonObject.results && (
				<SortableTable jsonObject={jsonObject} tab={tab} />
			)}
		</Fragment>
	);
}