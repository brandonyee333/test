import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import {default as langSub} from '../helpers/lang-util';
import {fixPackJSONObjectType} from '../types/highlights';
import {jiraIssueJSONObjectType} from '../types/changelog';

export default class SortableTable extends Component {
	static defaultProps = {
		sortingFunction: null
	};

	static propTypes = {
		jsonObject: PropTypes.oneOfType(
			[fixPackJSONObjectType, jiraIssueJSONObjectType]
		).isRequired,
		name: PropTypes.string.isRequired,
		orderBy: PropTypes.oneOf(['asc', 'desc']).isRequired,
		sortingFunction: PropTypes.func,
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
		const {sortingFunction} = this.props;
		const {orderBy} = this.state;

		const currentOrderBy = orderBy === 'desc' ? 'asc' : 'desc';

		this.setState(
			{
				orderBy: currentOrderBy
			}
		);

		if (sortingFunction) {
			sortingFunction(currentOrderBy);
		}
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
						? langSub(
							Liferay.Language.get('x-result'),
							[
								total.toString()
							]
						)
						: langSub(
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
							{table.tableHeader(
								orderBy,
								this.handleSort,
								this.props
							)}
						</tr>
					</thead>

					<tbody>
						{!!results.length && table.tableBody(results, this.props)}
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