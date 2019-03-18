import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

export const tableHeader = (orderBy, handleSort) => (
	<Fragment>
		<th className="lfr-released-column">
			{Liferay.Language.get('release')}{' '}
			<svg
				className={`${orderBy} lexicon-icon sorting-indicator`}
				onClick={handleSort}
				role="button"
			>
				<use xlinkHref="#arrow-up" />
			</svg>
		</th>
		<th className="lfr-details-column">{Liferay.Language.get('details')}</th>
	</Fragment>
);

export const tableBody = results =>
	results.map(
		fixPack => (
			<tr
				key={fixPack.resourcePrimKey}
				className="journal-article-row"
				id={fixPack.resourcePrimKey}
			>
				<td className="lfr-released-column">{fixPack.releaseDate}</td>
				<td className="lfr-details-column">
					<div dangerouslySetInnerHTML={{__html: fixPack.content}} />
				</td>
			</tr>
		)
	);