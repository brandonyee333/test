import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/includes';

import {errorType} from '../types/generic';
import {fixPackFieldsType, fixPackJSONObjectType} from '../types/highlights';

import Button from './Button';
import FilterCheckbox from './FilterCheckbox';
import TableResults from './TableResults';

export default class Highlights extends Component {
	static propTypes = {
		description: PropTypes.string.isRequired,
		filters: fixPackFieldsType.isRequired,
		fixPackResultsURL: PropTypes.string.isRequired,
		jsonObject: PropTypes.oneOfType(
			[errorType, fixPackJSONObjectType]
		).isRequired
	};

	state = {
		filterBy: []
	};

	handleCheckboxChange = event => {
		const {filterBy} = this.state;

		const value = event.currentTarget.value;

		const checkedFilters = filterBy;

		if (!filterBy.includes(value)) {
			checkedFilters.push(value);
		}
		else {
			checkedFilters.splice(filterBy.indexOf(value), 1);
		}

		this.setState(
			{
				filterBy: checkedFilters
			}
		);
	};

	handleClearFilter = () => {
		this.setState(
			{
				filterBy: []
			}
		);
	};

	filterResults = () => {
		const {jsonObject} = this.props;
		const {filterBy} = this.state;

		if (jsonObject.results) {
			const newResults = jsonObject.results.filter(
				result => {
					return filterBy.every(
						filter => result.fieldsUsed[filter]
					);
				}
			);

			return {
				results: newResults,
				total: newResults.length
			};
		}

		return jsonObject;
	};

	render() {
		const {description, filters} = this.props;
		const {filterBy} = this.state;

		return (
			<Fragment>
				<div className="col-md-3">
					{!!filters && (
						<div className="refine-by-filters">
							<div className="filter-header">
								<h3>
									{Liferay.Language.get('refine-by')}
								</h3>

								{!!filterBy.length && (
									<Button
										display="link"
										onClick={this.handleClearFilter}
										type="button"
									>
										{Liferay.Language.get('clear-all')}
									</Button>
								)}
							</div>

							{filters.map(
								checkbox => (
									<FilterCheckbox
										key={checkbox.value}
										checked={!!filterBy.includes(checkbox.value)}
										handleOnChange={this.handleCheckboxChange}
										label={checkbox.label}
										value={checkbox.value}
									/>
								)
							)}
						</div>
					)}
				</div>

				<div className="col-md-9">
					<TableResults
						jsonObject={this.filterResults()}
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