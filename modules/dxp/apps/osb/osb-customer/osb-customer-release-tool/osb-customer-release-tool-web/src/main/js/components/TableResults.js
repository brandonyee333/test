import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

import {errorType} from '../types/generic';
import {fixPackJSONObjectType} from '../types/highlights';
import {jiraIssueJSONObjectType} from '../types/changelog';

import Alert from './Alert';
import SortableTable from './SortableTable';

TableResults.propTypes = {
	jsonObject: PropTypes.oneOfType(
		[
			errorType,
			fixPackJSONObjectType,
			jiraIssueJSONObjectType
		]
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
			<h2>
				{tab.tabName === 'highlights'
					? Liferay.Language.get('highlights')
					: Liferay.Language.get('changelog')}
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
				<SortableTable jsonObject={jsonObject} tabName={tab.tabName} />
			)}
		</Fragment>
	);
}