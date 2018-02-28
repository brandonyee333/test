import {Map} from 'immutable';
import sub from 'string-sub';

import {getPluralMessage} from '../lib/util';

function PersonChildLink({affiliationData = 0, model}) {
	let retVal = {};

	if (Map.isMap(affiliationData) && affiliationData.size > 0) {
		if (model === 'people') {
			const watsonChildName = affiliationData.get('name');
			const watsonIncidentId = affiliationData.get('watsonIncidentId');
			const watsonPersonId = affiliationData.get('watsonPersonId');

			retVal = (
				<a class="sub-header-text" href={`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${watsonPersonId}/edit`} title={Liferay.Language.get('hyperlink')}>
					{sub(Liferay.Language.get('imported-affiliated-with-x'), watsonChildName, getPluralMessage(Liferay.Language.get('incident'), Liferay.Language.get('incidents'), affiliationData))}
				</a>
			);
		}
		else {
			const watsonChildId = affiliationData.get('watsonChildId');
			const watsonPersonName = affiliationData.get('name');

			retVal = (
				<a class="sub-header-text" href={`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/`} title={Liferay.Language.get('hyperlink')}>
					{sub(Liferay.Language.get('imported-affiliated-with-x'), watsonPersonName)}
				</a>
			);
		}
	}

	return (
		<div class="sub-header">
			{retVal}
		</div>
	);
}

export default PersonChildLink;