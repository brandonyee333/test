import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/includes';

import axios from 'axios';
import debounce from 'lodash.debounce';

import {errorType} from '../types/generic';
import {
	artifactVersionFiltersType,
	artifactVersionJSONObjectType
} from '../types/module-changes';

import * as moduleChangesTable from './ModuleChangesTable';

import Button from './Button';
import FilterCheckbox from './FilterCheckbox';
import TableResults from './TableResults';

export default class ModuleChanges extends Component {
	moduleChangesTextInputRef = React.createRef();

	moduleChangesToggleRef = React.createRef();

	static propTypes = {
		description: PropTypes.string.isRequired,
		endpoint: PropTypes.string.isRequired,
		filters: artifactVersionFiltersType.isRequired,
		fromFixPackVersion: PropTypes.string.isRequired,
		jsonObject: PropTypes.oneOfType(
			[errorType, artifactVersionJSONObjectType]
		).isRequired,
		toFixPackVersion: PropTypes.string.isRequired
	};

	state = {
		jsonObject: this.props.jsonObject,
		selectedFilters: {
			changesOnly: false,
			keywords: '',
			owners: []
		}
	};

	debounceEvent(...args) {
		this.debounced = debounce(...args);

		return event => {
			event.persist();

			return this.debounced(event);
		};
	}

	componentWillUnmount() {
		this.debounced.cancel();
	}

	handleCheckboxChange = event => {
		const {selectedFilters} = this.state;

		const {value} = event.currentTarget;

		const {owners} = selectedFilters;

		if (!owners.includes(value)) {
			owners.push(value);
		}
		else {
			owners.splice(selectedFilters.owners.indexOf(value), 1);
		}

		this.setState(
			{
				selectedFilters: {
					...selectedFilters,
					owners
				}
			}
		);

		this.queryArtifactVersionResults(
			owners,
			selectedFilters.keywords,
			selectedFilters.changesOnly
		);
	};

	handleClearFilter = () => {
		this.setState(
			{
				selectedFilters: {
					changesOnly: false,
					keywords: '',
					owners: []
				}
			}
		);

		this.queryArtifactVersionResults();

		this.moduleChangesTextInputRef.current.value = '';
		this.moduleChangesToggleRef.current.checked = false;
	};

	handleFilterTextInputKeyUp = event => {
		const {selectedFilters} = this.state;

		if (event.keyCode === 13) {
			this.setState(
				{
					selectedFilters: {
						...selectedFilters,
						keywords: event.target.value
					}
				}
			);

			this.queryArtifactVersionResults(
				selectedFilters.owners,
				event.target.value,
				selectedFilters.changesOnly
			);
		}
	};

	handleToggleChange = event => {
		const {selectedFilters} = this.state;

		const {checked} = event.currentTarget;

		this.setState(
			{
				selectedFilters: {
					...selectedFilters,
					changesOnly: checked
				}
			}
		);

		this.queryArtifactVersionResults(
			selectedFilters.owners,
			selectedFilters.keywords,
			checked
		);
	};

	queryArtifactVersionResults = (owners = [], keywords = '', changesOnly = false) => {
		const {endpoint} = this.props;

		const {namespace} = window.ReleaseToolConstants;

		const encodedOwnersParam = encodeURIComponent(owners.toString());
		const encodedKeywordsParam = encodeURIComponent(keywords);

		axios
			.get(
				`${endpoint}&${namespace}owners=${encodedOwnersParam}&${namespace}keywords=${encodedKeywordsParam}&${namespace}changesOnly=${changesOnly}`
			)
			.then(
				({data}) => {
					this.setState(
						{
							jsonObject: data
						}
					);
				}
			)
			.catch(
				(err) => {
					if (process.env.NODE_ENV === 'development') {
						console.log(err);
					}
				}
			);
	};

	render() {
		const {
			description,
			filters,
			fromFixPackVersion,
			toFixPackVersion
		} = this.props;

		const {
			jsonObject,
			selectedFilters: {changesOnly, owners, keywords}
		} = this.state;

		return (
			<Fragment>
				<div className="col-md-3 module-changes-filter">
					{!!filters && (
						<div className="refine-by-filters">
							<div className="filter-header">
								<h3>{Liferay.Language.get('refine-by')}</h3>

								{!!(changesOnly || keywords || owners.length) && (
									<Button
										display="link"
										onClick={this.handleClearFilter}
										type="button"
									>
										{Liferay.Language.get('clear-all')}
									</Button>
								)}
							</div>

							<input
								ref={this.moduleChangesTextInputRef}
								className="input-small text-filter"
								onKeyUp={this.debounceEvent(
									this.handleFilterTextInputKeyUp,
									500
								)}
								placeholder={Liferay.Language.get('name')}
								type="text"
							/>

							<div className="filter-subsection module-groups semi-bold">
								{Liferay.Language.get('group')}

								{filters.map(
									checkbox => (
										<FilterCheckbox
											key={checkbox.value}
											checked={!!owners.includes(checkbox.value)}
											handleOnChange={this.handleCheckboxChange}
											label={checkbox.label}
											value={checkbox.value}
										/>
									)
								)}
							</div>

							<div className="filter-subsection module-changes-toggle semi-bold">
								{Liferay.Language.get('only-show')}

								<div className="toggle">
									<label>
										<input ref={this.moduleChangesToggleRef} className="toggle-switch" onChange={this.handleToggleChange} type="checkbox" />

										<span aria-hidden="true" className="toggle-switch-bar">
											<span className="toggle-switch-handle" />
										</span>
									</label>

									{Liferay.Language.get('changes')}
								</div>
							</div>
						</div>
					)}
				</div>

				<div className="col-md-9">
					<TableResults
						fromFixPackVersion={fromFixPackVersion}
						jsonObject={jsonObject}
						tab={{
							tabDescription: description,
							tabName: 'module-changes'
						}}
						table={moduleChangesTable}
						toFixPackVersion={toFixPackVersion}
					/>
				</div>
			</Fragment>
		);
	}
}