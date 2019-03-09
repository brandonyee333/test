import React from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

export default class SortableTable extends React.Component {
	static propTypes = {
		fixPacksResultsURL: PropTypes.string.isRequired
	};

	state = {
		fixPacksResults: [],
		sortBy: 'desc'
	};

	componentDidMount() {
		this.handleSortByDesc();
	}

	displayReleaseDateArrow = () => {
		const {sortBy} = this.state;

		let retVal = (
			<svg className="lexicon-icon lexicon-icon-arrow-up" onClick={this.handleSortByAsc}>
				<use xlinkHref="#arrow-up" />
			</svg>
		);

		if (sortBy === 'asc') {
			retVal = (
				<svg className="lexicon-icon lexicon-icon-arrow-down" onClick={this.handleSortByDesc}>
					<use xlinkHref="#arrow-down" />
				</svg>
			);
		}

		return retVal;
	};

	handleGetFixpacks = () => {
		const {fixPacksResultsURL} = this.props;

		const {sortBy} = this.state;

		const {namespace} = window.ReleaseToolConstants;

		axios.get(fixPacksResultsURL, {
			params: {
				[`${namespace}orderByType`]: sortBy
			}
		})
		.then(
			(response) => {
				this.setState(
					{
						fixPacksResults: response.data.results
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
	};

	handleSortByAsc = () => {
		this.setState(
			{
				sortBy: 'asc'
			},
			() => this.handleGetFixpacks()
		)
	};

	handleSortByDesc = () => {
		this.setState(
			{
				sortBy: 'desc'
			},
			() => this.handleGetFixpacks()
		)
	};

	render() {
		const {fixPacksResults, sortBy} = this.state;

		return (
			<table className="table table-autofit table-list">
				<thead>
					<tr>
						<th className="lfr-released-column">
							{Liferay.Language.get('released')}

							{this.displayReleaseDateArrow()}
						</th>
						<th className="lfr-details-column">
							{Liferay.Language.get('details')}
						</th>
					</tr>
				</thead>

				<tbody>
					{fixPacksResults.map(
						(fixPack) => (
							<tr key={fixPack.resourcePrimKey} className="journal-article-row" id={fixPack.resourcePrimKey}>
								<td className="lfr-released-column">
									{fixPack.releaseDate}
								</td>
								<td className="lfr-details-column">
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