import React, {Component} from 'react';
import PropTypes from 'prop-types';

import axios from 'axios';

import SortableTable from './SortableTable';

export default class Highlights extends Component {
	static propTypes = {
		fixPacksResultsURL: PropTypes.string.isRequired,
		fixPackJSONObject: PropTypes.shape(
			{
				total: PropTypes.number,
				results: PropTypes.arrayOf(
					PropTypes.shape(
						{
						content: PropTypes.string,
						releaseDate: PropTypes.string,
						resourcePrimKey: PropTypes.string,
						title: PropTypes.string
					}
					)
				)
			}
		)
	};

	render() {
		const {fixPackJSONObject} = this.props;

		return (
			<SortableTable fixPackJSONObject={fixPackJSONObject} />
		)
	}
}