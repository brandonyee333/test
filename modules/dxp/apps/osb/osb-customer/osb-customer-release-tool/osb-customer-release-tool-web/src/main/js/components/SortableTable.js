import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

export default class SortableTable extends Component {
	static propTypes = {
		fixPackJSONObject: PropTypes.shape(
			{
				total: PropTypes.number,
				results: PropTypes.arrayOf(
					PropTypes.shape(
						{
						content: PropTypes.string,
						releaseDate: PropTypes.string,
						resourcePrimKey: PropTypes.string,
						title: PropTypes.string
					}
					)
				)
			}
		)
	};

	state = {
		orderBy: 'desc'
	};

	handleSort = () => {
		const {orderBy} = this.state;

		this.setState(
			{
				orderBy: orderBy === 'desc' ? 'asc' : 'desc'
			},
			() => this.sortResults()
		);
	};

	sortResults = () => {
		const {
			fixPackJSONObject: {results}
		} = this.props;
		const {orderBy} = this.state;

		return orderBy === 'desc' ? results.concat() : results.concat().reverse()
	};

	render() {
		const {fixPackJSONObject: {total}} = this.props;
		const {orderBy} = this.state;

		const results = this.sortResults(orderBy);

		return (
			<Fragment>
				<div className="showing-results">
					{Liferay.Language.get(
						'showing-x-results',
						total.toString()
					)}
				</div>

				<table className="table table-autofit table-list">
					<thead>
						<tr>
							<th className="lfr-released-column">
								{Liferay.Language.get('released')}{' '}

								<svg className={`${orderBy} lexicon-icon sorting-indicator`} onClick={this.handleSort}>
									<use xlinkHref="#arrow-up" />
								</svg>
							</th>
							<th className="lfr-details-column">
								{Liferay.Language.get('details')}
							</th>
						</tr>
					</thead>

					<tbody>
						{!!results.length &&
							results.map(fixPack => (
								<tr
									key={fixPack.resourcePrimKey}
									className="journal-article-row"
									id={fixPack.resourcePrimKey}
								>
									<td className="lfr-released-column">
										{fixPack.releaseDate}
									</td>
									<td className="lfr-details-column">
										<div
											dangerouslySetInnerHTML={{__html: fixPack.content}}
										/>
									</td>
								</tr>
						))}
					</tbody>
				</table>

				{!results.length && (
					<div className="no-results">
						{Liferay.Language.get(
							'no-highlights-found-to-match-your-selection'
						)}
						<h5 className="secondary-text-color">
							{Liferay.Language.get(
								'try-modifying-your-criteria-or-viewing-the-changelog'
							)}
						</h5>
					</div>
				)}
			</Fragment>
		);
	}
}