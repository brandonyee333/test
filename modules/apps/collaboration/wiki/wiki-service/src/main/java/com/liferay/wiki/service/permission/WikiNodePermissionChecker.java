/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.service.permission;

import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.WikiNodeLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.wiki.model.WikiNode"
)
public class WikiNodePermissionChecker implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long nodeId, String actionId)
		throws PortalException {

		WikiNode node = _wikiNodeLocalService.getNode(nodeId);

		check(permissionChecker, node, actionId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static void check(
			PermissionChecker permissionChecker, long groupId, String name,
			String actionId)
		throws PortalException {

		WikiNode node = _wikiNodeLocalService.getNode(groupId, name);

		check(permissionChecker, node, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, WikiNode node, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, node, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, WikiNode.class.getName(), node.getNodeId(),
				actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long nodeId, String actionId)
		throws PortalException {

		WikiNode node = _wikiNodeLocalService.getNode(nodeId);

		return contains(permissionChecker, node, actionId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String name,
			String actionId)
		throws PortalException {

		WikiNode node = _wikiNodeLocalService.getNode(groupId, name);

		return contains(permissionChecker, node, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, WikiNode node, String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, node.getGroupId(), WikiNode.class.getName(),
			node.getNodeId(), WikiPortletKeys.WIKI_ADMIN, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				node.getCompanyId(), WikiNode.class.getName(), node.getNodeId(),
				node.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			node.getGroupId(), WikiNode.class.getName(), node.getNodeId(),
			actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setWikiNodeLocalService(
		WikiNodeLocalService wikiNodeLocalService) {

		_wikiNodeLocalService = wikiNodeLocalService;
	}

	private static WikiNodeLocalService _wikiNodeLocalService;

}