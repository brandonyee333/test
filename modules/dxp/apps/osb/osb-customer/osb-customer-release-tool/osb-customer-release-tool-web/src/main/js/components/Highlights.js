import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

import {error, fixPackJSONObject} from '../types/highlights'

import Alert from './Alert';
import SortableTable from './SortableTable';

export default class Highlights extends Component {
	static propTypes = {
		fixPacksResultsURL: PropTypes.string.isRequired,
		fixPackJSONObject: PropTypes.oneOfType(
			[error, fixPackJSONObject]
		)
	};

	render() {
		const {fixPackJSONObject} = this.props;

		return (
			<Fragment>
				{!!fixPackJSONObject.error && (
					<Alert type="danger">{fixPackJSONObject.error.message}</Alert>
				)}

				{!!fixPackJSONObject.results && (
					<SortableTable fixPackJSONObject={fixPackJSONObject} />
				)}
			</Fragment>
		);
	}
}