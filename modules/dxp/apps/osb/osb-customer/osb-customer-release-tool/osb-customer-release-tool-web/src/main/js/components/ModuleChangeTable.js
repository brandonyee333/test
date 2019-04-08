import React, {Fragment} from 'react';

export const tableBody = results =>
	results.map((result, index) => (
		<tr
			key={`${result.fromVersion}-${result.toVersion}-${index}`}
			className="journal-article-row"
			id={`${result.fromVersion}-${result.toVersion}-${index}`}
		>
			<td className="lfr-name-column">{result.name}</td>
			<td className="lfr-group-column">{result.group}</td>
			<td className="lfr-from-version-column">
				<a href={result.fromRepositoryURL}>{result.fromVersion}</a>
			</td>
			<td className="lfr-to-version-column">
				<a href={result.toRepositoryURL}>{result.toVersion}</a>
			</td>
		</tr>
	));

export const tableHeader = () => (
	<Fragment>
		<th className="lfr-released-column">{Liferay.Language.get('name')}</th>
		<th className="lfr-details-column">{Liferay.Language.get('group')}</th>
	</Fragment>
);