import React, {Component} from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

import {error} from '../types/generic';
import {fixPackJSONObject} from '../types/highlights';

import TableResults from './TableResults';

export default class Highlights extends Component {
	static propTypes = {
		fixPackJSONObject: PropTypes.oneOfType(
			[error, fixPackJSONObject]
		).isRequired,
		fixPackResultsURL: PropTypes.string.isRequired
	};

	render() {
		const {fixPackJSONObject} = this.props;

		return (
			<TableResults jsonObject={fixPackJSONObject} />
		);
	}
}