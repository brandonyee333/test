import React, {Fragment} from 'react';

export const tableBody = results =>
	results.map(
		(result, index) => {
			const moduleChangedCssClass =
				result.fromVersion === result.toVersion ? '' : 'module-changed';

			return (
				<tr
					key={`${result.fromVersion}-${result.toVersion}-${index}`}
					className={`journal-article-row ${moduleChangedCssClass}`}
					id={`${result.fromVersion}-${result.toVersion}-${index}`}
				>
					<td className="lfr-name-column table-cell-content">{result.name}</td>
					<td className="lfr-group-column">{result.group}</td>
					<td className="lfr-from-version-column">
						<a href={result.fromRepositoryURL}>{result.fromVersion}</a>
					</td>
					<td className="lfr-to-version-column">
						<a href={result.toRepositoryURL}>{result.toVersion}</a>
					</td>
				</tr>
			);
		}
	);

export const tableHeader = (orderBy, handleSort, props) => {
	const {fromFixPackVersion, toFixPackVersion} = props;

	return (
		<Fragment>
			<th className="lfr-name-column table-cell-content">
				{Liferay.Language.get('name')}
			</th>
			<th className="lfr-group-column">{Liferay.Language.get('group')}</th>
			<th className="lfr-from-version-column">
				{Liferay.Language.get('fixpack')} {fromFixPackVersion}
			</th>
			<th className="lfr-to-version-column">
				{Liferay.Language.get('fixpack')} {toFixPackVersion}
			</th>
		</Fragment>
	);
};