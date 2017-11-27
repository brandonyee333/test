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
			(a, b) => (b.get('watsonIncidentId') - (a.get('watsonIncidentId')))
		);
	}
	else if (sortBy === 'watsonChildId') {
		sorted = data.sort(
			(a, b) => (b.get('watsonChildId') - (a.get('watsonChildId')))
		);
	}
	else if (sortBy === 'modifiedDate') {
		sorted = data.sort(
			(a, b) => new Date(b.get('modifiedDate')) - new Date((a.get('modifiedDate')))
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
			(a, b) => a.get('typeWatsonListTypeId').localeCompare(b.get('typeWatsonListTypeId'))
		);
	}
	else {
		sorted = data.sort(
			(a, b) => (b.get('id') - (a.get('id')))
		);
	}

	return sorted;
};