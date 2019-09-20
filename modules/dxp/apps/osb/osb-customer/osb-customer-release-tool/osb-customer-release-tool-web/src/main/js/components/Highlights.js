import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import {errorType} from '../types/generic';
import {fixPackFieldsType, fixPackJSONObjectType} from '../types/highlights';

import {getSearchParamValue, setSearchParam} from '../helpers/url-search-params';

import * as highlightsTable from './HightlightsTable';

import Button from './Button';
import FilterCheckbox from './FilterCheckbox';
import TableResults from './TableResults';

export default class Highlights extends Component {
	static propTypes = {
		description: PropTypes.string.isRequired,
		filters: fixPackFieldsType.isRequired,
		jsonObject: PropTypes.oneOfType(
			[errorType, fixPackJSONObjectType]
		).isRequired
	};

	getFilterByFromSearchParam = () => {
		const filterBy = getSearchParamValue('refineBy');

		return filterBy ? filterBy.split(',') : [];
	};

	state = {
		filterBy: this.getFilterByFromSearchParam()
	};

	handleCheckboxChange = event => {
		const {filterBy} = this.state;

		const {value} = event.currentTarget;

		const checkedFilters = filterBy;

		if (!filterBy.includes(value)) {
			checkedFilters.push(value);
		}
		else {
			checkedFilters.splice(filterBy.indexOf(value), 1);
		}

		setSearchParam('refineBy', checkedFilters.toString());

		this.setState(
			{
				filterBy: checkedFilters
			}
		);
	};

	handleClearFilter = () => {
		setSearchParam('refineBy', '');

		this.setState(
			{
				filterBy: []
			}
		);
	};

	filterResults = () => {
		const {jsonObject} = this.props;
		const {filterBy} = this.state;

		if (jsonObject.results && filterBy.length) {
			const newResults = jsonObject.results.filter(
				result => filterBy.some(
					filter => result.fieldsUsed.includes(filter)
				)
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
						<div className="sidebar-filters">
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
						filterByClassName={filterBy.toString().replace(/,/g, ' ')}
						jsonObject={this.filterResults()}
						tab={{
							tabDescription: description,
							tabName: 'highlights'
						}}
						table={highlightsTable}
					/>
				</div>
			</Fragment>
		);
	}
}