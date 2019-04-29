import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

import {errorType} from '../types/generic';
import {fixPackJSONObjectType} from '../types/highlights';
import {jiraIssueJSONObjectType} from '../types/changelog';

import Alert from './Alert';
import SortableTable from './SortableTable';

TableResults.defaultProps = {
	orderBy: 'desc'
};

TableResults.propTypes = {
	jsonObject: PropTypes.oneOfType(
		[
			errorType,
			fixPackJSONObjectType,
			jiraIssueJSONObjectType
		]
	).isRequired,
	orderBy: PropTypes.oneOf(['asc', 'desc']),
	tab: PropTypes.shape(
		{
			tabDescription: PropTypes.string,
			tabName: PropTypes.string
		}
	).isRequired,
	table: PropTypes.shape(
		{
			tableBody: PropTypes.func,
			tableHeader: PropTypes.func,
		}
	).isRequired
};

const displayTabName = name => {
	// Liferay.Language.get() only accepts string parameter.

	let tabName = Liferay.Language.get('module-version-changes');

	if (name === 'highlights') {
		tabName = Liferay.Language.get('highlights');
	} else if (name === 'changelog') {
		tabName = Liferay.Language.get('changelog');
	}

	return tabName;
}

export default function TableResults(props) {
	const {jsonObject, orderBy, tab, table} = props;

	return (
		<Fragment>
			<h2>
				{displayTabName(tab.tabName)}
			</h2>

			{!!tab.tabDescription && (
				<h5
					className="secondary-text-color section-subtitle"
					dangerouslySetInnerHTML={{__html: tab.tabDescription}}
				/>
			)}

			{!!jsonObject.error && (
				<Alert type="danger">{jsonObject.error.message}</Alert>
			)}

			{!!jsonObject.results && (
				<SortableTable
					jsonObject={jsonObject}
					name={tab.tabName}
					orderBy={orderBy}
					table={table}
					{...props}
				/>
			)}
		</Fragment>
	);
}