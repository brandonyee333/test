import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import {fixPackJSONObject} from '../types/highlights';
import {jiraIssueJSONObject} from '../types/changelog';

import * as changelogTable from './ChangelogTable';
import * as highlightsTable from './HightlightsTable';

export default class SortableTable extends Component {
	static propTypes = {
		jsonObject: PropTypes.oneOfType(
			[fixPackJSONObject, jiraIssueJSONObject]
		).isRequired,
		tab: PropTypes.shape(
			{
				tabDescription: PropTypes.string,
				tabName: PropTypes.string
			}
		).isRequired
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
		const {jsonObject: {results}} = this.props;
		const {orderBy} = this.state;

		return orderBy === 'desc' ? results.concat() : results.concat().reverse();
	};

	render() {
		const {
			jsonObject: {total},
			tab: {tabName},
			tab: {tabDescription}
		} = this.props;
		const {orderBy} = this.state;

		const results = this.sortResults(orderBy);

		const table = tabName === 'highlights' ? highlightsTable : changelogTable;

		return (
			<Fragment>
				<h2>
					{tabName === 'highlights' ?
						Liferay.Language.get('highlights')
					:
						tabName === 'changelog' ?
							Liferay.Language.get('changelog')
						:
							Liferay.Language.get('module-changes')
					}
				</h2>

				{tabDescription && (
					<h5 class="secondary-text-color section-subtitle" dangerouslySetInnerHTML={{__html: tabDescription}}></h5>
				)}

				<div className="results-count">
					{Liferay.Language.get('x-results', total.toString())}
				</div>

				<table className={`table ${tabName}-table table-autofit table-list`} role="table">
					<thead>
						<tr>
							{table.tableHeader(orderBy, this.handleSort)}
						</tr>
					</thead>

					<tbody>
						{!!results.length && table.tableBody(results)}
					</tbody>
				</table>

				{!results.length && (
					<div className="no-results">
						{Liferay.Language.get('no-results-found-to-match-your-selection')}
						<h5 className="secondary-text-color">
							{Liferay.Language.get('try-modifying-your-criteria')}
						</h5>
					</div>
				)}
			</Fragment>
		);
	}
}