import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

import {error} from '../types/generic';
import {jiraIssueJSONObject} from '../types/changelog';

import Pagination from './Pagination';
import TableResults from './TableResults';

const ARTICLES_PER_PAGE = 50;

export default class Changelog extends Component {
	static propTypes = {
		description: PropTypes.string.isRequired,
		jiraIssueEndpoint: PropTypes.string.isRequired,
		jiraIssueJSONObject: PropTypes.oneOfType(
			[error, jiraIssueJSONObject]
		).isRequired
	};

	state = {
		jiraIssueJSONObject: this.props.jiraIssueJSONObject,
	}

	handlePaginationClick = (number) => {
		// startAt param begins at 0 and not 1

		const startAt = (number - 1) * ARTICLES_PER_PAGE;

		this.queryJiraIssues(startAt);

		window.scroll(0, 0);
	};

	queryJiraIssues = (startAt) => {
		const {jiraIssueEndpoint} = this.props;

		const {namespace} = window.ReleaseToolConstants;

		axios.get(`${jiraIssueEndpoint}&${namespace}startAt=${startAt}`)
			.then(
				({data}) => {
					this.setState(
						{
							jiraIssueJSONObject: data
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
	}

	render() {
		const {description} = this.props;
		const {jiraIssueJSONObject} = this.state;

		const totalPage = jiraIssueJSONObject.total ? Math.ceil(jiraIssueJSONObject.total / ARTICLES_PER_PAGE) : '';

		return (
			<Fragment>
				<TableResults
					jsonObject={jiraIssueJSONObject}
					tab={{
						tabDescription: description,
						tabName: 'changelog'
					}}
				/>

				{!!totalPage && totalPage > 1 && (
					<Pagination
						onClick={this.handlePaginationClick}
						total={totalPage}
					/>
				)}
			</Fragment>
		);
	}
}