import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import {fixPackJSONObjectType} from '../types/highlights';
import {jiraIssueJSONObjectType} from '../types/changelog';

export default class SortableTable extends Component {
	static propTypes = {
		jsonObject: PropTypes.oneOfType(
			[fixPackJSONObjectType, jiraIssueJSONObjectType]
		).isRequired,
		name: PropTypes.string.isRequired,
		orderBy: PropTypes.oneOf(['asc', 'desc']).isRequired,
		table: PropTypes.shape(
			{
				tableBody: PropTypes.func,
				tableHeader: PropTypes.func,
			}
		).isRequired
	};

	state = {
		orderBy: this.props.orderBy
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

	sortResults = newOrderBy => {
		const {
			jsonObject: {results},
			orderBy
		} = this.props;

		return orderBy === newOrderBy
			? results.concat()
			: results.concat().reverse();
	};

	render() {
		const {
			jsonObject: {total},
			name,
			table
		} = this.props;
		const {orderBy} = this.state;

		const results = this.sortResults(orderBy);

		return (
			<Fragment>
				<div className="results-count">
					{total === 1
						? AUI().Lang.sub(
							Liferay.Language.get('x-result'),
							[
								total.toString()
							]
						)
						: AUI().Lang.sub(
							Liferay.Language.get('x-results'),
							[
								total.toString()
							]
						)
					}
				</div>

				<table className={`table ${name}-table table-autofit table-list`} role="table">
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