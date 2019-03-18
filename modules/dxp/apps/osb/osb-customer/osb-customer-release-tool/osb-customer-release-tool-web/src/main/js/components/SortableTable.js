import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import * as highlights from './HightlightsTable';

export default class SortableTable extends Component {
	static propTypes = {
		jsonObject: PropTypes.object.isRequired
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
			jsonObject: {results}
		} = this.props;
		const {orderBy} = this.state;

		return orderBy === 'desc' ? results.concat() : results.concat().reverse();
	};

	render() {
		const {jsonObject: {total}} = this.props;
		const {orderBy} = this.state;

		const results = this.sortResults(orderBy);

		return (
			<Fragment>
				<div className="showing-results">
					{Liferay.Language.get('showing-x-results', total.toString())}
				</div>

				<table className="table table-autofit table-list" role="table">
					<thead>
						<tr>
							{highlights.tableHeader(orderBy, this.handleSort)}
						</tr>
					</thead>

					<tbody>
						{!!results.length && highlights.tableBody(results)}
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