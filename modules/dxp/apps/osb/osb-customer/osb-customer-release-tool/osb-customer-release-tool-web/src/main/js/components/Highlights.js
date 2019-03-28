import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import {error} from '../types/generic';
import {fixPackJSONObject} from '../types/highlights';

import FilterCheckbox from './FilterCheckbox';
import TableResults from './TableResults';

export default class Highlights extends Component {
	static propTypes = {
		description: PropTypes.string.isRequired,
		filters: PropTypes.array.isRequired,
		fixPackJSONObject: PropTypes.oneOfType(
			[error, fixPackJSONObject]
		).isRequired,
		fixPackResultsURL: PropTypes.string.isRequired
	};

	state = {
		filterBy: []
	};

	handleCheckboxChange = value => {
		const {filterBy} = this.state;

		let filterByArray = filterBy;

		if (!filterBy.includes(value)) {
			filterByArray.push(value);
		}
		else {
			const index = filterBy.indexOf(value);

			filterByArray.splice(index, 1);
		}

		this.setState(
			{
				filterBy: filterByArray
			}
		);
	};

	filterResults = () => {
		const {fixPackJSONObject} = this.props;
		const {filterBy} = this.state;

		if (fixPackJSONObject.results) {
			const filteredResults = fixPackJSONObject.results.filter(
				result => {
					return filterBy.some(
						filter => result.fieldsUsed[filter]
					);
				}
			);

			return {results: filteredResults, total: filteredResults.length};
		}

		return fixPackJSONObject;
	}

	render() {
		const {
			description,
			filters,
			fixPackJSONObject,
		} = this.props;
		const {filterBy} = this.state;

		const jsonObject = filterBy.length
			? this.filterResults()
			: fixPackJSONObject;

		return (
			<Fragment>
				<div className="col-md-3">
					<div className="refine-by-filters">
						<h3>
							{Liferay.Language.get('refine-by')}
						</h3>

						{!!filters && filters.map(
							checkbox => (
								<FilterCheckbox
									key={checkbox.value}
									handleOnChange={this.handleCheckboxChange}
									label={checkbox.label}
									value={checkbox.value}
								/>
							)
						)}
					</div>
				</div>

				<div className="col-md-9">
					<TableResults
						jsonObject={jsonObject}
						tab={{
							tabDescription: description,
							tabName: 'highlights'
						}}
					/>
				</div>
			</Fragment>
		);
	}
}