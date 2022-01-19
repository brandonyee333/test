/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {DefaultEventHandler, openSelectionModal} from 'frontend-js-web';

class UserDropdownDefaultEventHandler extends DefaultEventHandler {
	deleteGroupUsers(itemData) {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			submitForm(document.hrefFm, itemData.deleteGroupUsersURL);
		}
	}

	assignRoles(itemData) {
		openSelectionModal({
			buttonAddLabel: Liferay.Language.get('done'),
			getSelectedItemsOnly: false,
			multiple: true,
			onSelect: (items) => {
				if (items.length) {
					const editUserGroupRoleFm = this.one(
						'#editUserGroupRoleFm'
					);

					if (!editUserGroupRoleFm) {
						return;
					}

					const allItems = document.createElement('input');

					allItems.name = this.ns('availableRowIds');
					allItems.value = items.map((item) => item.value);

					editUserGroupRoleFm.appendChild(allItems);

					const selectedItems = document.createElement('input');

					selectedItems.name = this.ns('rowIds');
					selectedItems.value = items
						.filter((item) => item.checked)
						.map((item) => item.value);

					editUserGroupRoleFm.appendChild(selectedItems);

					submitForm(
						editUserGroupRoleFm,
						itemData.editUserGroupRoleURL
					);
				}
			},
			title: Liferay.Language.get('assign-roles'),
			url: itemData.assignRolesURL,
		});
	}
}

export default UserDropdownDefaultEventHandler;
