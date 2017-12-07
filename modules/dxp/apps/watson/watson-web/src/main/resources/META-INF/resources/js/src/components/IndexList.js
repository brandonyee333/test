import {includes} from 'lodash';
import {OrderedMap} from 'immutable';
import sub from 'string-sub';

import List from './List';
import LoadingCard from './LoadingCard';
import Sort from './Sort';

import {convertHtmlToText, getOptionsLabelFromWatsonConstants} from '../lib/util';

function fetchIncidentData(incidents, watsonIncidentId) {
	return incidents.get(watsonIncidentId);
}

function formatActivityData(watsonActivities, watsonIncidents, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleActivityData(watsonActivities, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonActivities.map(
			(watsonActivity, key) => {
				const watsonIncidentId = watsonActivity.get('watsonIncidentId');

				const watsonIncident = fetchIncidentData(watsonIncidents, watsonIncidentId);

				let incidentName = '';
				let statusCssClass = '';
				let statusLabel = '';

				if (watsonIncident) {
					incidentName = watsonIncident.get('name');
					statusCssClass = watsonIncident.get('incidentStatusLabel');
					statusLabel = statusCssClass;

					const statusId = watsonIncident.get('incidentStatus');

					if (statusId) {
						statusLabel = getOptionsLabelFromWatsonConstants('incidents', 'incidentStatus', statusId);
					}
				}

				return {
					header: watsonActivity.get('name'),
					id: key,
					lastEdited: watsonActivity.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/${key}/edit`,
					onClick,
					reportedBy: watsonActivity.get('reportedBy'),
					reportedDate: watsonActivity.get('createDate'),
					rowContent: convertHtmlToText(watsonActivity.get('narrative')),
					smallIncidentName: incidentName || watsonActivity.get('incidentName'),
					status: statusLabel,
					statusCssClass,
					subHeader: getOptionsLabelFromWatsonConstants('activities', 'typeWatsonListTypeId', watsonActivity.get('typeWatsonListTypeId'))

				};
			}
		);
	}

	return formattedData;
}

function formatAddressData(watsonAddresses, watsonIncidents, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleAddressData(watsonAddresses, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonAddresses.map(
			(watsonAddress, key) => {
				const watsonIncidentId = watsonAddress.get('watsonIncidentId');

				const watsonIncident = fetchIncidentData(watsonIncidents, watsonIncidentId);

				let incidentName = '';
				let statusCssClass = '';
				let statusLabel = '';

				if (watsonIncident) {
					incidentName = watsonIncident.get('name');
					statusCssClass = watsonIncident.get('incidentStatusLabel');
					statusLabel = statusCssClass;

					const statusId = watsonIncident.get('incidentStatus');

					if (statusId) {
						statusLabel = getOptionsLabelFromWatsonConstants('incidents', 'incidentStatus', statusId);
					}
				}

				return {
					file: watsonAddress.get('imagePayload'),
					header: watsonAddress.get('name'),
					id: key,
					lastEdited: watsonAddress.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/${key}/edit`,
					onClick,
					reportedBy: watsonAddress.get('reportedBy'),
					reportedDate: watsonAddress.get('createDate'),
					rowContent: formatAddressRowContent(watsonAddress),
					smallIncidentName: incidentName || watsonAddress.get('incidentName'),
					status: statusLabel,
					statusCssClass,
					subHeader: getOptionsLabelFromWatsonConstants('addresses', 'typeWatsonListTypeId', watsonAddress.get('typeWatsonListTypeId'))

				};
			}
		);
	}

	return formattedData;
}

function formatAddressRowContent(watsonAddress) {
	const locationLabels = [];

	const province = watsonAddress.get('province');

	if (province) {
		locationLabels.push(sub(Liferay.Language.get('subdistrict-x'), watsonAddress.get('subdistrict')));
		locationLabels.push(sub(Liferay.Language.get('province-x'), province));
	}
	else {
		const countryLabel = getOptionsLabelFromWatsonConstants('addresses', 'countryId', watsonAddress.get('countryId'));

		locationLabels.push(sub(Liferay.Language.get('country-x'), countryLabel));
		locationLabels.push(sub(Liferay.Language.get('region-x'), watsonAddress.get('region')));
	}

	return locationLabels;
}

function formatChildData(watsonChildren, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleChildData(watsonChildren, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonChildren.map(
			(watsonChild, key) => {

				return {
					header: watsonChild.get('name'),
					id: key,
					lastEdited: watsonChild.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/children/${key}/edit/`,
					onClick,
					reportedBy: watsonChild.get('reportedBy'),
					reportedDate: watsonChild.get('reportDate'),
					rowContent: formatPersonRowContent(watsonChild),
					subHeader: getOptionsLabelFromWatsonConstants('children', 'typeWatsonListTypeId', watsonChild.get('typeWatsonListTypeId'))
				};
			}
		);
	}

	return formattedData;
}

function formatDocumentsData(watsonDocuments, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleDocumentsData(watsonDocuments, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonDocuments.map(
			(watsonDocument, key) => {
				const watsonChildId = watsonDocument.get('watsonChildId');

				return {
					header: watsonDocument.get('name'),
					id: key,
					lastEdited: watsonDocument.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/${key}/edit`,
					onClick,
					reportedBy: watsonDocument.get('reportedBy'),
					reportedDate: watsonDocument.get('createDate'),
					smallIncidentName: watsonDocument.get('childName'),
					subHeader: getOptionsLabelFromWatsonConstants('documents', 'typeWatsonListTypeId', watsonDocument.get('typeWatsonListTypeId'))

				};
			}
		);
	}

	return formattedData;
}

function formatIncidentData(watsonIncidents, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleIncidentData(watsonIncidents, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonIncidents.map(
			(watsonIncident, key) => {
				const statusCssClass = watsonIncident.get('incidentStatusLabel');
				let statusLabel = statusCssClass;

				const statusId = watsonIncident.get('incidentStatus');

				if (statusId) {
					statusLabel = getOptionsLabelFromWatsonConstants('incidents', 'incidentStatus', statusId);
				}

				return {
					header: watsonIncident.get('name'),
					id: key,
					lastEdited: watsonIncident.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/incidents/${key}/edit/`,
					onClick,
					reportedBy: watsonIncident.get('reportedBy'),
					reportedDate: watsonIncident.get('reportDate'),
					rowContent: formatIncidentRowContent(watsonIncident),
					status: statusLabel,
					statusCssClass,
					subHeader: getOptionsLabelFromWatsonConstants('incidents', 'typeWatsonListTypeId', watsonIncident.get('typeWatsonListTypeId'))
				};
			}
		);
	}

	return formattedData;
}

function formatReportsData(watsonReports, model, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleReportsData(watsonReports, model, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonReports.map(
			(watsonReport, key) => {
				const watsonChildId = watsonReport.get('watsonChildId');

				return {
					header: watsonReport.get('name'),
					id: key,
					lastEdited: watsonReport.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/${key}/edit`,
					onClick,
					reportedBy: watsonReport.get('reportedBy'),
					reportedDate: watsonReport.get('createDate'),
					smallIncidentName: watsonReport.get('childName')

				};
			}
		);
	}

	return formattedData;
}

function formatIncidentRowContent(watsonIncident) {
	const natures = watsonIncident.get('natureWatsonListType');

	const natureLabels = [];

	if (natures) {
		natures.forEach(
			natureId => {
				if (natureId > 0) {
					natureLabels.push(getOptionsLabelFromWatsonConstants('incidents', 'natureWatsonListType', natureId));
				}
			}
		);
	}

	return natureLabels;
}

function formatPeopleData(watsonPeople, watsonIncidents, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimplePersonData(watsonPeople, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonPeople.map(
			(watsonPerson, key) => {
				const watsonIncidentId = watsonPerson.get('watsonIncidentId');

				const watsonIncident = fetchIncidentData(watsonIncidents, watsonIncidentId);

				let incidentName = '';
				let statusCssClass = '';
				let statusLabel = '';

				if (watsonIncident) {
					incidentName = watsonIncident.get('name');
					statusCssClass = watsonIncident.get('incidentStatusLabel');
					statusLabel = statusCssClass;

					const statusId = watsonIncident.get('incidentStatus');

					if (statusId) {
						statusLabel = getOptionsLabelFromWatsonConstants('incidents', 'incidentStatus', statusId);
					}
				}

				return {
					file: watsonPerson.get('imagePayload'),
					header: watsonPerson.get('name'),
					id: key,
					lastEdited: watsonPerson.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/${key}/edit`,
					onClick,
					reportedBy: watsonPerson.get('reportedBy'),
					reportedDate: watsonPerson.get('createDate'),
					rowContent: formatPersonRowContent(watsonPerson),
					smallIncidentName: incidentName || watsonPerson.get('incidentName'),
					status: statusLabel,
					statusCssClass,
					subHeader: getOptionsLabelFromWatsonConstants('people', 'typeWatsonListTypeId', watsonPerson.get('typeWatsonListTypeId'))

				};
			}
		);
	}

	return formattedData;
}

function formatPersonRowContent(watsonPerson) {
	const personDetails = [];

	const unknownLabel = Liferay.Language.get('unknown');

	personDetails.push(sub(Liferay.Language.get('country-of-ethnicity-x'), watsonPerson.get('ethnicity') || unknownLabel));
	personDetails.push(sub(Liferay.Language.get('birth-date-x'), watsonPerson.get('birthDate') || unknownLabel));

	return personDetails;
}

function formatResourcesData(watsonResources, watsonIncidents, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleResourcesData(watsonResources, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonResources.map(
			(watsonResource, key) => {
				const watsonIncidentId = watsonResource.get('watsonIncidentId');

				const watsonIncident = fetchIncidentData(watsonIncidents, watsonIncidentId);

				let incidentName = '';
				let statusCssClass = '';
				let statusLabel = '';

				if (watsonIncident) {
					incidentName = watsonIncident.get('name');
					statusCssClass = watsonIncident.get('incidentStatusLabel');
					statusLabel = statusCssClass;

					const statusId = watsonIncident.get('incidentStatus');

					if (statusId) {
						statusLabel = getOptionsLabelFromWatsonConstants('incidents', 'incidentStatus', statusId);
					}
				}

				return {
					header: watsonResource.get('name'),
					id: key,
					lastEdited: watsonResource.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${key}/edit`,
					onClick,
					reportedBy: watsonResource.get('reportedBy'),
					reportedDate: watsonResource.get('createDate'),
					smallIncidentName: incidentName || watsonResource.get('incidentName'),
					status: statusLabel,
					statusCssClass,
					subHeader: getOptionsLabelFromWatsonConstants('resources', 'typeWatsonListTypeId', watsonResource.get('typeWatsonListTypeId'))

				};
			}
		);
	}

	return formattedData;
}

function formatSimpleActivityData(watsonActivities, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonActivities.map(
		(watsonActivity, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const watsonIncidentId = watsonActivity.get('watsonIncidentId');

			return {
				disabled: disabled ? 'disabled' : '',
				header: watsonActivity.get('reportDate'),
				id: key,
				link: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/activities/${key}/edit`,
				onClick,
				rowContent: convertHtmlToText(watsonActivity.get('narrative')),
				selected,
				subHeader: getOptionsLabelFromWatsonConstants('activities', 'typeWatsonListTypeId', watsonActivity.get('typeWatsonListTypeId'))
			};
		}
	);

	return formattedData;
}

function formatSimpleAddressData(watsonAddresses, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonAddresses.map(
		(watsonAddress, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			let childRegionLabel = '';
			let regionLabel = '';

			const province = watsonAddress.get('province');

			if (province) {
				childRegionLabel = sub(Liferay.Language.get('subdistrict-x'), watsonAddress.get('subdistrict'));
				regionLabel = sub(Liferay.Language.get('province-x'), province);
			}
			else {
				const countryLabel = getOptionsLabelFromWatsonConstants('addresses', 'countryId', watsonAddress.get('countryId'));

				childRegionLabel = sub(Liferay.Language.get('country-x'), countryLabel);
				regionLabel = sub(Liferay.Language.get('region-x'), watsonAddress.get('region'));
			}

			const watsonIncidentId = watsonAddress.get('watsonIncidentId');

			return {
				disabled: disabled ? 'disabled' : '',
				file: watsonAddress.get('imagePayload'),
				header: watsonAddress.get('name'),
				id: key,
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/addresses/${key}/edit`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonAddress.get('reportedBy'),
				reportedDate: watsonAddress.get('createDate'),
				selected,
				smallIncidentName: childRegionLabel,
				status: regionLabel,
				statusCssClass: 'row-small-incident-name'
			};
		}
	);

	return formattedData;
}

function formatSimpleChildData(watsonChildren, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonChildren.map(
		(watsonChild, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const unknownLabel = Liferay.Language.get('unknown');

			const birthDate = sub(Liferay.Language.get('birth-date-x'), watsonChild.get('birthDate') || unknownLabel);
			const ethnicity = sub(Liferay.Language.get('country-of-ethnicity-x'), watsonChild.get('ethnicity') || unknownLabel);

			return {
				disabled: disabled ? 'disabled' : '',
				header: watsonChild.get('name'),
				id: key,
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/children/${key}/edit/`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonChild.get('reportedBy'),
				reportedDate: watsonChild.get('createDate'),
				selected,
				smallIncidentName: ethnicity,
				status: birthDate,
				statusCssClass: 'row-small-incident-name',
				subHeader: getOptionsLabelFromWatsonConstants('children', 'typeWatsonListTypeId', watsonChild.get('typeWatsonListTypeId'))
			};
		}
	);

	return formattedData;
}

function formatSimpleDocumentsData(watsonDocuments, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonDocuments.map(
		(watsonDocument, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const watsonChildId = watsonDocument.get('watsonChildId');

			return {
				disabled: disabled ? 'disabled' : '',
				header: watsonDocument.get('name'),
				id: key,
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/documents/${key}/edit`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonDocument.get('reportedBy'),
				reportedDate: watsonDocument.get('createDate'),
				rowContent: watsonDocument.get('description'),
				selected,
				subHeader: getOptionsLabelFromWatsonConstants('documents', 'parentTypeWatsonListTypeId', watsonDocument.get('parentTypeWatsonListTypeId'))
			};
		}
	);

	return formattedData;
}

function formatSimpleIncidentData(watsonIncidents, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonIncidents.map(
		(watsonIncident, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const statusCssClass = watsonIncident.get('incidentStatusLabel');
			let statusLabel = statusCssClass;

			const statusId = watsonIncident.get('incidentStatus');

			if (statusId) {
				statusLabel = getOptionsLabelFromWatsonConstants('incidents', 'incidentStatus', statusId);
			}

			return {
				disabled: disabled ? 'disabled' : '',
				header: watsonIncident.get('name'),
				id: key,
				lastEdited: watsonIncident.get('modifiedDate'),
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${key}/edit`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonIncident.get('reportedBy'),
				reportedDate: watsonIncident.get('reportDate'),
				selected,
				status: statusLabel,
				statusCssClass,
				subHeader: getOptionsLabelFromWatsonConstants('incidents', 'typeWatsonListTypeId', watsonIncident.get('typeWatsonListTypeId'))
			};
		}
	);

	return formattedData;
}

function formatSimpleReportsData(watsonReports, model, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonReports.map(
		(watsonReport, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const watsonChildId = watsonReport.get('watsonChildId');

			return {
				disabled: disabled ? 'disabled' : '',
				header: watsonReport.get('name'),
				id: key,
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit/${model}/${key}/edit`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonReport.get('reportedBy'),
				reportedDate: watsonReport.get('createDate'),
				rowContent: watsonReport.get('description'),
				selected
			};
		}
	);

	return formattedData;
}

function formatSimplePersonData(watsonPeople, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonPeople.map(
		(watsonPerson, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const unknownLabel = Liferay.Language.get('unknown');

			const birthDate = sub(Liferay.Language.get('birth-date-x'), watsonPerson.get('birthDate') || unknownLabel);
			const ethnicity = sub(Liferay.Language.get('country-of-ethnicity-x'), watsonPerson.get('ethnicity') || unknownLabel);

			const watsonIncidentId = watsonPerson.get('watsonIncidentId');

			return {
				disabled: disabled ? 'disabled' : '',
				file: watsonPerson.get('imagePayload'),
				header: watsonPerson.get('name'),
				id: key,
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/people/${key}/edit`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonPerson.get('reportedBy'),
				reportedDate: watsonPerson.get('createDate'),
				selected,
				smallIncidentName: ethnicity,
				status: birthDate,
				statusCssClass: 'row-small-incident-name',
				subHeader: getOptionsLabelFromWatsonConstants('people', 'typeWatsonListTypeId', watsonPerson.get('typeWatsonListTypeId'))
			};
		}
	);

	return formattedData;
}

function formatSimpleResourcesData(watsonResources, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonResources.map(
		(watsonResource, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const watsonIncidentId = watsonResource.get('watsonIncidentId');

			return {
				disabled: disabled ? 'disabled' : '',
				header: watsonResource.get('name'),
				id: key,
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/resources/${key}/edit`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonResource.get('reportedBy'),
				reportedDate: watsonResource.get('createDate'),
				rowContent: watsonResource.get('description'),
				selected,
				subHeader: getOptionsLabelFromWatsonConstants('resources', 'typeWatsonListTypeId', watsonResource.get('typeWatsonListTypeId'))
			};
		}
	);

	return formattedData;
}

function formatSimpleVehicleData(watsonVehicles, keysToOmit, onClick, selectedIds) {
	const formattedData = watsonVehicles.map(
		(watsonVehicle, key) => {
			const disabled = includes(keysToOmit, key, 0);

			const selected = includes(selectedIds, key, 0);

			const licensePlate = sub(Liferay.Language.get('license-plate-x'), watsonVehicle.get('licensePlate') || Liferay.Language.get('unknown'));
			const typeLabel = sub(Liferay.Language.get('type-x'), getOptionsLabelFromWatsonConstants('vehicles', 'typeWatsonListTypeId', watsonVehicle.get('typeWatsonListTypeId')));

			const watsonIncidentId = watsonVehicle.get('watsonIncidentId');

			return {
				disabled: disabled ? 'disabled' : '',
				file: watsonVehicle.get('imagePayload'),
				header: watsonVehicle.get('name'),
				id: key,
				link: onClick ? undefined : `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${key}/edit`,
				onClick: disabled ? undefined : onClick,
				reportedBy: watsonVehicle.get('reportedBy'),
				reportedDate: watsonVehicle.get('createDate'),
				selected,
				smallIncidentName: typeLabel,
				status: licensePlate,
				statusCssClass: 'row-small-incident-name',
				subHeader: getOptionsLabelFromWatsonConstants('vehicles', 'colorWatsonListTypeId', watsonVehicle.get('colorWatsonListTypeId'))
			};
		}
	);

	return formattedData;
}

function formatVehicleRowContent(watsonVehicle) {
	const vehicleLabels = [];

	const typeWatsonListTypeLabel = getOptionsLabelFromWatsonConstants('vehicles', 'typeWatsonListTypeId', watsonVehicle.get('typeWatsonListTypeId'));

	vehicleLabels.push(sub(Liferay.Language.get('type-x'), typeWatsonListTypeLabel));
	vehicleLabels.push(sub(Liferay.Language.get('license-plate-x'), watsonVehicle.get('licensePlate') || Liferay.Language.get('unknown')));

	return vehicleLabels;
}

function formatVehicleData(watsonVehicles, watsonIncidents, keysToOmit, onClick, selectedIds, simple) {
	let formattedData;

	if (simple) {
		formattedData = formatSimpleVehicleData(watsonVehicles, keysToOmit, onClick, selectedIds);
	}
	else {
		formattedData = watsonVehicles.map(
			(watsonVehicle, key) => {
				const watsonIncidentId = watsonVehicle.get('watsonIncidentId');

				const watsonIncident = fetchIncidentData(watsonIncidents, watsonIncidentId);

				let incidentName = '';
				let statusCssClass = '';
				let statusLabel = '';

				if (watsonIncident) {
					incidentName = watsonIncident.get('name');
					statusCssClass = watsonIncident.get('incidentStatusLabel');
					statusLabel = statusCssClass;

					const statusId = watsonIncident.get('incidentStatus');

					if (statusId) {
						statusLabel = getOptionsLabelFromWatsonConstants('incidents', 'incidentStatus', statusId);
					}
				}

				return {
					file: watsonVehicle.get('imagePayload'),
					header: watsonVehicle.get('name'),
					id: key,
					lastEdited: watsonVehicle.get('modifiedDate'),
					link: `${WatsonConstants.urls.baseURL}/incidents/${watsonIncidentId}/edit/vehicles/${key}/edit`,
					onClick,
					reportedBy: watsonVehicle.get('reportedBy'),
					reportedDate: watsonVehicle.get('createDate'),
					rowContent: formatVehicleRowContent(watsonVehicle),
					smallIncidentName: incidentName || watsonVehicle.get('incidentName'),
					status: statusLabel,
					statusCssClass,
					subHeader: getOptionsLabelFromWatsonConstants('vehicles', 'colorWatsonListTypeId', watsonVehicle.get('colorWatsonListTypeId'))

				};
			}
		);
	}

	return formattedData;
}

function IndexList({data = OrderedMap(), hasMoreResults, incidentsData = OrderedMap(), keysToOmit, loadMoreMethod, model, loading, onClick, selectedIds = [], simple, sortBy}) {
	data = Sort(data, model, sortBy, false);

	if (model === 'addresses') {
		data = formatAddressData(data, incidentsData, keysToOmit, onClick, selectedIds, simple);
	}
	else if (model === 'activities') {
		data = formatActivityData(data, incidentsData, keysToOmit, onClick, selectedIds, simple);
	}
	else if (model === 'children') {
		data = formatChildData(data, keysToOmit, onClick, selectedIds, simple);
	}
	else if (model === 'documents') {
		data = formatDocumentsData(data, keysToOmit, onClick, selectedIds, simple);
	}
	else if (model === 'legals' || model === 'illnesses') {
		data = formatReportsData(data, model, keysToOmit, onClick, selectedIds, simple);
	}
	else if (model === 'people') {
		data = formatPeopleData(data, incidentsData, keysToOmit, onClick, selectedIds, simple);
	}
	else if (model === 'resources') {
		data = formatResourcesData(data, incidentsData, keysToOmit, onClick, selectedIds, simple);
	}
	else if (model === 'vehicles') {
		data = formatVehicleData(data, incidentsData, keysToOmit, onClick, selectedIds, simple);
	}
	else {
		data = formatIncidentData(data, keysToOmit, onClick, selectedIds, simple);
	}

	return (
		<div class="incidents-list">
			<List data={data} />

			<LoadingCard
				hasMoreResults={hasMoreResults}
				loading={loading}
				loadMoreMethod={loadMoreMethod}
			/>
		</div>
	);
}

export default IndexList;