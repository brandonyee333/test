import React, {Fragment} from 'react';

export const tableBody = (results, props) =>
	results.map(
		fixPack => {
			const {filterByClassName} = props;

			return (
				<tr
					key={fixPack.resourcePrimKey}
					className="journal-article-row"
					id={fixPack.resourcePrimKey}
				>
					<td className="lfr-released-column">{fixPack.releaseDate}</td>
					<td className="lfr-details-column">
						<div className={`details ${filterByClassName}`} dangerouslySetInnerHTML={{__html: fixPack.content}} />
					</td>
				</tr>
			)
		}
	);

export const tableHeader = (orderBy, handleSort) => (
	<Fragment>
		<th className="lfr-released-column">
			{Liferay.Language.get('release')}{' '}
			<svg className={`arrow-up ${orderBy} lexicon-icon sorting-indicator`} onClick={handleSort} role="button">
				<use xlinkHref="#arrow-up" />
			</svg>
		</th>
		<th className="lfr-details-column">{Liferay.Language.get('details')}</th>
	</Fragment>
);