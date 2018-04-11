import {OrderedMap} from 'immutable';

export default (data = new OrderedMap(), model, sortBy = 'watsonIncidentId') => {
	let sorted = new OrderedMap();

	if (sortBy === 'startDate') {
		sorted = data.sort(
			(a, b) => b.get(sortBy).localeCompare(a.get(sortBy))
		);
	}
	else if (sortBy === 'typeWatsonListTypeId' || sortBy === 'watsonChildId' || sortBy === 'watsonIncidentId') {
		sorted = data.sort(
			(a, b) => b.get(sortBy).localeCompare(
				a.get(sortBy),
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
			(a, b) => a.get(sortBy).localeCompare(b.get(sortBy))
		);
	}
	else if (sortBy === 'name') {
		sorted = data.sort(
			(a, b) => (b.get(sortBy) || '').localeCompare(
				a.get(sortBy),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}
	else if (sortBy === 'incidentName_String_sortable') {
		sorted = data.sort(
			(a, b) => (b.get('incidentName') || '').localeCompare(
				a.get('incidentName'),
				{},
				{ignorePunctuation: true, numeric: true}
			)
		);
	}
	else if (sortBy === 'reportDate') {
		sorted = data.sort(
			(a, b) => new Date(b.get(sortBy)) - new Date((a.get(sortBy)))
		);
	}
	else if (sortBy === 'incidentStatus') {
		sorted = data.sort(
			(a, b) => {
				let sortVal = b.get(sortBy).localeCompare(a.get(sortBy));

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