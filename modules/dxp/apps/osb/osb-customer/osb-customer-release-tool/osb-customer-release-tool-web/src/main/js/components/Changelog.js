import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/includes';

import axios from 'axios';
import debounce from 'lodash.debounce';

import {errorType} from '../types/generic';
import {jiraComponentsType, jiraIssueJSONObjectType} from '../types/changelog';

import * as changelogTable from './ChangelogTable';

import Button from './Button';
import FilterCheckbox from './FilterCheckbox';
import Pagination from './Pagination';
import TableResults from './TableResults';

const ARTICLES_PER_PAGE = 50;
const FILTER_ON_LOAD = 7;

export default class Changelog extends Component {
	changelogPaginationRef = React.createRef();
	changelogTextInputRef = React.createRef();

	static propTypes = {
		description: PropTypes.string.isRequired,
		endpoint: PropTypes.string.isRequired,
		filters: jiraComponentsType.isRequired,
		jsonObject: PropTypes.oneOfType(
			[errorType, jiraIssueJSONObjectType]
		).isRequired
	};

	state = {
		selectedFilters: {
			components: [],
			keywords: '',
			orderBy: 'asc'
		},
		jsonObject: this.props.jsonObject,
		seeAllFilterValues: false
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

		const name = event.currentTarget.name;

		const components = selectedFilters.components;

		if (!components.includes(name)) {
			components.push(name);
		}
		else {
			components.splice(selectedFilters.components.indexOf(name), 1);
		}

		this.setState(
			{
				selectedFilters: {
					...selectedFilters,
					components: components
				}
			}
		);

		this.queryJiraIssues(
			components,
			selectedFilters.keywords,
			selectedFilters.orderBy
		);
	};

	handleClearFilter = () => {
		this.setState(
			{
				selectedFilters: {
					components: [],
					keywords: '',
					orderBy: 'asc'
				}
			}
		);

		this.queryJiraIssues();

		this.changelogTextInputRef.current.value = '';
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

			this.queryJiraIssues(
				selectedFilters.components,
				event.target.value,
				selectedFilters.orderBy
			);
		}
	};

	handleTogglingFilterValues = () => {
		const {seeAllFilterValues} = this.state;

		this.setState(
			{
				seeAllFilterValues: !seeAllFilterValues
			}
		);
	};

	handlePaginationClick = (number, otherProps) => {
		const {
			selectedFilters: {components, keywords, orderBy}
		} = this.state;

		const newOrderBy = otherProps.orderBy || orderBy;

		// startAt param begins at 0 and not 1

		const startAt = (number - 1) * ARTICLES_PER_PAGE;

		this.queryJiraIssues(components, keywords, newOrderBy, startAt);

		window.scroll(0, 0);
	};

	handleQuerySortedResults = () => {
		const {selectedFilters} = this.state;

		const currentOrderBy = selectedFilters.orderBy === 'desc' ? 'asc' : 'desc';

		this.setState(
			{
				selectedFilters: {
					...selectedFilters,
					orderBy: currentOrderBy
				}
			}
		);

		this.changelogPaginationRef.current.handleClick(
			1,
			{
				orderBy: currentOrderBy
			}
		);
	}

	queryJiraIssues = (
		components = [],
		keywords = '',
		orderBy = 'asc',
		startAt = 0
	) => {
		const {endpoint} = this.props;

		const {namespace} = window.ReleaseToolConstants;

		const encodedComponentsParam = encodeURIComponent(components.toString());
		const encodedKeywordsParam = encodeURIComponent(keywords);

		axios
			.get(
				`${endpoint}&${namespace}components=${encodedComponentsParam}&${namespace}keywords=${encodedKeywordsParam}&${namespace}orderByType=${orderBy}&${namespace}startAt=${startAt}`
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
		const {description, filters} = this.props;
		const {
			jsonObject,
			selectedFilters: {components, keywords, orderBy},
			seeAllFilterValues
		} = this.state;

		const totalPage = jsonObject.total
			? Math.ceil(jsonObject.total / ARTICLES_PER_PAGE)
			: 1;

		return (
			<Fragment>
				<div className="changelog-filter col-md-3">
					{!!filters && (
						<div className="refine-by-filters">
							<div className="filter-header">
								<h3>
									{Liferay.Language.get('refine-by')}
								</h3>

								{!!(keywords || components.length) && (
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
								ref={this.changelogTextInputRef}
								className="input-small text-filter"
								onKeyUp={this.debounceEvent(
									this.handleFilterTextInputKeyUp,
									500
								)}
								placeholder={Liferay.Language.get('contains-text')}
								type="text"
							/>

							<div className="filter-subsection jira-components semibold">
								{Liferay.Language.get('component')}

								{filters.map(
									(checkbox, index) => {
										if (
											seeAllFilterValues
												? seeAllFilterValues
												: index + 1 <= FILTER_ON_LOAD
										) {
											return (
												<FilterCheckbox
													key={checkbox.value}
													checked={!!components.includes(checkbox.name)}
													handleOnChange={this.handleCheckboxChange}
													label={checkbox.name}
													value={checkbox.value}
												/>
											);
										}
									}
								)}
							</div>

							{filters.length > FILTER_ON_LOAD && (
								<div className="more-options">
									<Button
										display="link"
										onClick={this.handleTogglingFilterValues}
										type="button"
									>
										{seeAllFilterValues
											? Liferay.Language.get('see-less')
											: Liferay.Language.get('see-more')}
									</Button>
								</div>
							)}
						</div>
					)}
				</div>

				<div className="col-md-9">
					<TableResults
						jsonObject={jsonObject}
						orderBy={orderBy}
						sortingFunction={
							totalPage > 1 ? this.handleQuerySortedResults : null
						}
						tab={{
							tabDescription: description,
							tabName: 'changelog'
						}}
						table={changelogTable}
					/>

					{totalPage > 1 && (
						<Pagination
							ref={this.changelogPaginationRef}
							onClick={this.handlePaginationClick}
							total={totalPage}
						/>
					)}
				</div>
			</Fragment>
		);
	}
}