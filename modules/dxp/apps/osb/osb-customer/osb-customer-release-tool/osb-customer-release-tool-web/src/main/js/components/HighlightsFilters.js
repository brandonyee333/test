import React, {Fragment} from 'react';
import PropTypes from 'prop-types';

import RefinementCheckbox from './RefinementCheckbox';

export const refinementFilters = (filtersArray, refineByCheckbox) => (
	<Fragment>
		<div className="refinement-filters">
			<h3 className="refine-by">
				{Liferay.Language.get('refine-by')}
			</h3>

			{!!filtersArray && filtersArray.map(
				checkbox => (
					<RefinementCheckbox
						checkboxLabel={checkbox.label}
						checkboxValue={checkbox.value}
						refineByCheckbox={refineByCheckbox}
					/>
				)
			)}
		</div>
	</Fragment>
);