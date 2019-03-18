import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import {error} from '../types/generic';
import {jiraIssueJSONObject} from '../types/changelog';

import TableResults from './TableResults';

export default class Changelog extends Component {
	static propTypes = {
		jiraIssueJSONObject: PropTypes.oneOfType(
			[error, jiraIssueJSONObject]
		).isRequired
	};

	render() {
		const {jiraIssueJSONObject} = this.props;

		return (
			<TableResults jsonObject={jiraIssueJSONObject} />
		);
	}
}