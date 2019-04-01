import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/includes';

import {errorType} from '../types/generic';
import {fixPackFieldsType, fixPackJSONObjectType} from '../types/highlights';

import FilterCheckbox from './FilterCheckbox';
import TableResults from './TableResults';

export default class Highlights extends Component {
	static propTypes = {
		description: PropTypes.string.isRequired,
		filters: fixPackFieldsType.isRequired,
		fixPackJSONObject: PropTypes.oneOfType(
			[errorType, fixPackJSONObjectType]
		).isRequired,
		fixPackResultsURL: PropTypes.string.isRequired
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

	filterResults = () => {
		const {fixPackJSONObject} = this.props;
		const {filterBy} = this.state;

		if (fixPackJSONObject.results) {
			const newResults = fixPackJSONObject.results.filter(
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

		return fixPackJSONObject;
	}

	render() {
		const {description, filters} = this.props;

		return (
			<Fragment>
				<div className="col-md-3">
					{!!filters && (
						<div className="refine-by-filters">
							<h3>
								{Liferay.Language.get('refine-by')}
							</h3>

							{filters.map(
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