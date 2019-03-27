import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

import {error} from '../types/generic';
import {fixPackJSONObject} from '../types/highlights';

import FilterCheckbox from './FilterCheckbox';
import TableResults from './TableResults';

export default class Highlights extends Component {
	static propTypes = {
		fixPackJSONObject: PropTypes.oneOfType(
			[error, fixPackJSONObject]
		).isRequired,
		fixPackResultsURL: PropTypes.string.isRequired,
		highlightsDescription: PropTypes.string.isRequired,
		highlightsFiltersArray: PropTypes.array.isRequired
	};

	state = {
		refineBy: []
	};

	handleCheckboxChange = checkboxValue => {
		const {refineBy} = this.state;

		let refineByArray = refineBy;

		if (!refineBy.includes(checkboxValue)) {
			refineByArray.push(checkboxValue);
		}
		else {
			const index = refineBy.indexOf(checkboxValue);

			refineByArray.splice(index, 1);
		}

		this.setState(
			{
				refineBy: refineByArray
			}
		);
	};

	refineObject = results => {
		const {refineBy} = this.state;

		const refinedResults = results.filter(
			result => {
				return refineBy.some(
					filter => result.fieldsUsed[filter]
				);
			}
		);

		return {results: refinedResults, total: refinedResults.length};
	}

	render() {
		const {
			fixPackJSONObject,
			highlightsDescription,
			highlightsFiltersArray
		} = this.props;
		const {refineBy} = this.state;

		const highlightsTab = {
			tabDescription: highlightsDescription,
			tabName: 'highlights'
		}

		const {results} = fixPackJSONObject;

		const jsonObject = refineBy.length ? this.refineObject(results) : fixPackJSONObject;

		if (refineBy.length) {
			this.refineObject(results);
		}

		return (
			<Fragment>
				<div className="col-md-3">
					<div className="refinement-filters">
						<h3 className="refine-by">
							{Liferay.Language.get('refine-by')}
						</h3>

						{!!highlightsFiltersArray && highlightsFiltersArray.map(
							checkbox => (
								<FilterCheckbox
									key={checkbox.value}
									checkboxLabel={checkbox.label}
									checkboxValue={checkbox.value}
									handleCheckboxChange={this.handleCheckboxChange}
								/>
							)
						)}
					</div>
				</div>

				<div className="col-md-9">
					<TableResults
						jsonObject={jsonObject}
						tab={highlightsTab}
					/>
				</div>
			</Fragment>
		);
	}
}