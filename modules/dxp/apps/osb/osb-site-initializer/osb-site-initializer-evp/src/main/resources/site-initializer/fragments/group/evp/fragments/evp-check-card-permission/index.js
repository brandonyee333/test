/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */
const ROLE = {
	ADMINISTRATOR: 'Administrator',
	EVP_MANAGER: 'EVP Manager',
	FINANCE_USER: 'Finance User',
	POWER_USER: 'Power User',
};

const fieldEVPRequestsListing = document.querySelector('.EVPRequestsListing');
const fieldEVPOrganizationsListing = document.querySelector(
	'.EVPOrganizationsListing'
);

Liferay.Service(
	'/role/get-user-roles',
	{
		userId: Liferay.ThemeDisplay.getUserId(),
	},
	(response) => {
		if (response) {
			const userRoles = response.array.forEach(({name}) => name);

			/** Card: EVP Requests */
			if (
				![
					ROLE.EVP_MANAGER,
					ROLE.FINANCE_USER,
					ROLE.ADMINISTRATOR,
					ROLE.POWER_USER,
				].includes(userRoles)
			) {
				fieldEVPRequestsListing.hidden = true;
			}

			/** Card: EVP Organization */
			if (
				![
					ROLE.FINANCE_USER,
					ROLE.ADMINISTRATOR,
					ROLE.POWER_USER,
				].includes(userRoles)
			) {
				fieldEVPOrganizationsListing.hidden = true;
			}
		}
	}
);
