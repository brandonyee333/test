import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

import Alert from './Alert';
import SortableTable from './SortableTable';

TableResults.propTypes = {
	jsonObject: PropTypes.object.isRequired
};

export default function TableResults({jsonObject}) {
	return (
		<Fragment>
			{!!jsonObject.error && (
				<Alert type="danger">{jsonObject.error.message}</Alert>
			)}

			{!!jsonObject.results && (
				<SortableTable jsonObject={jsonObject} />
			)}
		</Fragment>
	);
}