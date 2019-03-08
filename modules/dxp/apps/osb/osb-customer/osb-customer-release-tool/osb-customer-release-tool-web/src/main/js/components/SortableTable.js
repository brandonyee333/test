import React from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

export default class SortableTable extends React.Component {
	static propTypes = {
		fixPacksResultsURL: PropTypes.string.isRequired
	};

	state = {
		fixPacksResults: [],
		sortByDesc: true
	};

	componentDidMount() {
		this.handleSortByType('desc');
	}

	handleSortByType = (direction) => {
		const {fixPacksResultsURL} = this.props;

		const {namespace} = window.ReleaseToolConstants;

		this.setState(
			{
				sortByDesc: direction === 'desc' ? true : false
			},
			() => {
				axios.get(fixPacksResultsURL, {
					params: {
						[`${namespace}orderByType`]: direction
					}
				})
				.then(
					(response) => {
						this.setState(
							{
								fixPacksResults: response.data.results,
							}
						);
					}
				)
				.catch(
					(err) => {
						if (process.env.NODE_ENV === 'development') {
							console.log(err);
						}
					}
				);
			}
		);
	};

	render() {
		const {fixPacksResults, sortByDesc} = this.state;

		return (
			<table class="table table-autofit table-list">
				<thead>
					<tr>
						<th class="lfr-released-column">
							{Liferay.Language.get('released')}

							<svg className={`${sortByDesc ? 'hide' : ''} lexicon-icon lexicon-icon-order-arrow-down`} onClick={() => this.handleSortByType('desc')}>
								<use xlinkHref="#order-arrow-down" />
							</svg>

							<svg className={`${sortByDesc ? '' : 'hide'} lexicon-icon lexicon-icon-order-arrow-up`} onClick={() => this.handleSortByType('asc')}>
								<use xlinkHref="#order-arrow-up" />
							</svg>
						</th>
						<th class="lfr-details-column">
							{Liferay.Language.get('details')}
						</th>
					</tr>
				</thead>

				<tbody>
					{fixPacksResults.map(
						(fixPack) => (
							<tr class="journal-article-row" id={fixPack.resourcePrimKey}>
								<td class="lfr-released-column">
									{fixPack.releaseDate}
								</td>
								<td class="lfr-details-column">
									<div dangerouslySetInnerHTML={{__html: fixPack.content}} />
								</td>
							</tr>
						)
					)}
				</tbody>
			</table>
		);
	}
}