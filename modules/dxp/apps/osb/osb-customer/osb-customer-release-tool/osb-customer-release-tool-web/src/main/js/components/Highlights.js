import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

import {error} from '../types/generic';
import {fixPackJSONObject} from '../types/highlights';

import TableResults from './TableResults';
import * as highlightsFilters from './HighlightsFilters';

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

	refineByCheckbox = checkboxLabel => {
		const {refineBy} = this.state;

		const formattedLabel = checkboxLabel.replace(/\s+/g, '-').toLowerCase();

		let refineByArray = refineBy;

		if (!refineBy.includes(formattedLabel)) {
			refineByArray.push(formattedLabel);
		}
		else {
			const index = refineBy.indexOf(formattedLabel);

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

		let refinedObject = {};
		let refinedResults = [];

		results.map(
			fixPack => {
				refineBy.map(
					filter => {
						const indexOfFilter = fixPack.content.indexOf(filter);
						const indexOfFixPack = refinedResults.indexOf(fixPack);

						if (indexOfFilter != -1 && indexOfFixPack == -1) {
							refinedResults.push(fixPack);
						}
					}
				);
			}
		);

		refinedObject.results = refinedResults;
		refinedObject.total = refinedResults.length;

		return refinedObject;
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

		return (
			<Fragment>
				<div className="col-md-3">
					{highlightsFilters.refinementFilters(
						highlightsFiltersArray,
						this.refineByCheckbox
					)}
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