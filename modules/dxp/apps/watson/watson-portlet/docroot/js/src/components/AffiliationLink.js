import sub from 'string-sub';

import {getPluralMessage} from '../lib/util';

function AffiliationLink({affiliationData = 0, entryId = 0, model, watsonIncidentId}) {
	return (
		<div class="sub-header">
			{affiliationData > 0 &&
				<a class="sub-header-text" href={`${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/${model}/${entryId}/view`} title={Liferay.Language.get('hyperlink')}>
					{sub(Liferay.Language.get('imported-affiliated-with-x-other-x'), affiliationData, getPluralMessage(Liferay.Language.get('incident'), Liferay.Language.get('incidents'), affiliationData))}
				</a>
			}
		</div>
	);
}

export default AffiliationLink;