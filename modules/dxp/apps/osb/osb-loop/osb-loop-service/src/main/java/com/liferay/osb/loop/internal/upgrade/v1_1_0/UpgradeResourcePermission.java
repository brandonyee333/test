/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.internal.upgrade.v1_1_0;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Iterator;

/**
 * @author Calvin Keum
 * @author Timothy Bell
 */
public class UpgradeResourcePermission extends BaseUpgradeClassName {

	public UpgradeResourcePermission(
		CounterLocalService counterLocalService,
		ResourceActionLocalService resourceActionLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService) {

		_counterLocalService = counterLocalService;
		_resourceActionLocalService = resourceActionLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
	}

	protected void addResourcePermission(JSONObject payloadJSONObject)
		throws Exception {

		JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
			"imageData");

		if (imageDataJSONArray == null) {
			return;
		}

		for (int i = 0; i < imageDataJSONArray.length(); i++) {
			JSONObject imageDataJSONObject = imageDataJSONArray.getJSONObject(
				i);

			JSONObject fileEntryIdJSONObject =
				imageDataJSONObject.getJSONObject("fileEntryIds");

			if (fileEntryIdJSONObject == null) {
				return;
			}

			Iterator<String> iterator = fileEntryIdJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				long fileEntryId = fileEntryIdJSONObject.getLong(key);

				addResourcePermission(
					PortalUtil.getDefaultCompanyId(), fileEntryId,
					_LOOP_PERSON);
			}
		}
	}

	protected void addResourcePermission(
			long companyId, long fileEntryId, String roleName)
		throws Exception {

		Role role = _roleLocalService.getRole(companyId, roleName);

		if (_resourcePermissionLocalService.hasResourcePermission(
				companyId, DLFileEntry.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntryId),
				role.getRoleId(), ActionKeys.VIEW)) {

			return;
		}

		long resourcePermissionId = _counterLocalService.increment();

		ResourcePermission resourcePermission =
			_resourcePermissionLocalService.createResourcePermission(
				resourcePermissionId);

		resourcePermission.setCompanyId(companyId);
		resourcePermission.setName(DLFileEntry.class.getName());
		resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
		resourcePermission.setPrimKey(String.valueOf(fileEntryId));
		resourcePermission.setRoleId(role.getRoleId());

		ResourceAction resourceAction =
			_resourceActionLocalService.getResourceAction(
				DLFileEntry.class.getName(), ActionKeys.VIEW);

		resourcePermission.setActionIds(resourceAction.getBitwiseValue());

		_resourcePermissionLocalService.addResourcePermission(
			resourcePermission);
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeClassNameColumn();
		upgradeImagePermissions();
	}

	protected void upgradeClassNameColumn() throws Exception {
		for (String className : CLASS_NAMES) {
			StringBundler sb = new StringBundler(5);

			sb.append("update ResourcePermission set name = '");
			sb.append(className);
			sb.append("' where name = '");
			sb.append(getStaleClassName(className));
			sb.append(StringPool.APOSTROPHE);

			runSQL(sb.toString());
		}
	}

	protected void upgradeImagePermissions() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("select * from AssetEntrySet");

			rs = ps.executeQuery();

			while (rs.next()) {
				String payload = rs.getString("payload");

				addResourcePermission(
					JSONFactoryUtil.createJSONObject(payload));
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final String _LOOP_PERSON = "Loop Person";

	private final CounterLocalService _counterLocalService;
	private final ResourceActionLocalService _resourceActionLocalService;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;

}