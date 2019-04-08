import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/includes';

import {errorType} from '../types/generic';
import {
	artifactVersionFiltersType,
	artifactVersionJSONObjectType
} from '../types/module-changes';

import * as moduleChangesTable from './ModuleChangesTable';

import TableResults from './TableResults';

export default class ModuleChanges extends Component {
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

	render() {
		const {
			description,
			filters,
			fromFixPackVersion,
			jsonObject,
			toFixPackVersion
		} = this.props;

		return (
			<Fragment>
				<div className="col-md-3">
					{!!filters && (
						<div className="refine-by-filters">
							<div className="filter-header">
								<h3>{Liferay.Language.get('refine-by')}</h3>
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