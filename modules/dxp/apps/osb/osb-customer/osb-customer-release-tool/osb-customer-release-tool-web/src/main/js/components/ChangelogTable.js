import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

export const tableHeader = (orderBy, handleSort) => (
	<Fragment>
		<th className="lfr-summary-column table-cell-content">{Liferay.Language.get('summary')}</th>
		<th className="lfr-component-column">
			{Liferay.Language.get('component')}
		</th>
		<th className="lfr-release-column">
			{Liferay.Language.get('release')}{' '}
			<svg
				className={`${orderBy} lexicon-icon sorting-indicator`}
				onClick={handleSort}
				role="button"
			>
				<use xlinkHref="#arrow-up" />
			</svg>
		</th>
		<th className="lfr-key-column">{Liferay.Language.get('key')}</th>
	</Fragment>
);

export const tableBody = results =>
	results.map(
		issue => (
			<tr
				key={`${issue.key}${issue.release}`}
				className="journal-article-row"
				id={`${issue.key}${issue.release}`}
			>
				<td className="lfr-summary-column table-cell-content">{issue.summary}</td>
				<td className="lfr-component-column">
					{issue.components.toString().replace(',', ', ')}
				</td>
				<td className="lfr-release-column">{issue.release}</td>
				<td className="lfr-key-column">
					<a href={issue.url}>{issue.key}</a>
				</td>
			</tr>
		)
	);