import {OrderedMap} from 'immutable';

export default (data = new OrderedMap(), model, sortBy = 'watsonIncidentId') => {
	let sorted = new OrderedMap();

	if (sortBy === 'startDate') {
		sorted = data.sort(
			(a, b) => b.get('startDate').localeCompare(a.get('startDate'))
		);
	}
	else if (sortBy === 'watsonIncidentId') {
		sorted = data.sort(
			(a, b) => b.get('watsonIncidentId').localeCompare(
				a.get('watsonIncidentId'),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}
	else if (sortBy === 'watsonChildId') {
		sorted = data.sort(
			(a, b) => b.get('watsonChildId').localeCompare(
				a.get('watsonChildId'),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}
	else if (sortBy === 'modifiedDate') {
		sorted = data.sort(
			(a, b) => (b.get('modifiedDateTimeStamp')).localeCompare(
				a.get('modifiedDateTimeStamp'),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}
	else if (sortBy === 'makeWatsonListTypeId') {
		sorted = data.sort(
			(a, b) => a.get('makeWatsonListTypeId').localeCompare(b.get('makeWatsonListTypeId'))
		);
	}
	else if (sortBy === 'name') {
		sorted = data.sort(
			(a, b) => (b.get('name') || '').localeCompare(
				a.get('name'),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}
	else if (sortBy === 'reportDate') {
		sorted = data.sort(
			(a, b) => new Date(b.get('reportDate')) - new Date((a.get('reportDate')))
		);
	}
	else if (sortBy === 'incidentStatus') {
		sorted = data.sort(
			(a, b) => {
				let sortVal = b.get('incidentStatus').localeCompare(a.get('incidentStatus'));

				if (sortVal === 0) {
					sortVal = b.get('name').localeCompare(
						a.get('name'),
						{},
						{ignorePunctuation: true, numeric: true}
					);
				}

				return sortVal;
			}
		);
	}
	else if (sortBy === 'typeWatsonListTypeId') {
		sorted = data.sort(
			(a, b) => b.get('typeWatsonListTypeId').localeCompare(
				a.get('typeWatsonListTypeId'),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}
	else {
		sorted = data.sort(
			(a, b) => b.get('id').localeCompare(
				a.get('id'),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}

	return sorted;
};