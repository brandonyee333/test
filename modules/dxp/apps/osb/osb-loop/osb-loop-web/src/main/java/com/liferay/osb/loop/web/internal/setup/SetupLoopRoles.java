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

package com.liferay.osb.loop.web.internal.setup;

import com.liferay.osb.loop.asset.entry.set.constants.AssetEntrySetPortletKeys;
import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.token.auth.constants.TokenAuthPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReader;
import com.liferay.push.notifications.constants.PushNotificationsPortletKeys;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Timothy Bell
 */
@Component(immediate = true, service = SetupLoopRoles.class)
public class SetupLoopRoles {

	@Activate
	public void activate(BundleContext bundleContext) throws Exception {
		_bundle = bundleContext.getBundle();

		deleteLoopResourcePermissions();

		importRoles();
	}

	protected void addRole(Element roleElement) throws Exception {
		boolean organizationRole = GetterUtil.getBoolean(
			roleElement.elementText("organization-role"));

		long companyId = _portal.getDefaultCompanyId();

		String roleName = roleElement.elementText("name");

		Role role = _roleLocalService.fetchRole(companyId, roleName);

		if (role == null) {
			int type = RoleConstants.TYPE_REGULAR;

			if (organizationRole) {
				type = RoleConstants.TYPE_ORGANIZATION;
			}

			role = _roleLocalService.addRole(
				_userLocalService.getDefaultUserId(companyId), null, 0,
				roleName, null, null, type, null, null);
		}

		int scope = ResourceConstants.SCOPE_COMPANY;

		String primKey = String.valueOf(companyId);

		if (organizationRole) {
			scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;

			primKey = String.valueOf(0);
		}

		List<Element> defaultActionKeysElements = roleElement.elements(
			"default-action-keys");

		for (Element defaultActionKeysElement : defaultActionKeysElements) {
			String name = defaultActionKeysElement.attributeValue(
				"resourceName");

			List<String> actionIds = new ArrayList<>();

			List<Element> actionKeyElements = defaultActionKeysElement.elements(
				"action-key");

			for (Element actionKeyElement : actionKeyElements) {
				actionIds.add(actionKeyElement.getText());
			}

			_resourcePermissionLocalService.setResourcePermissions(
				companyId, name, scope, primKey, role.getRoleId(),
				actionIds.toArray(new String[0]));
		}
	}

	protected void deleteLoopResourcePermissions() {
		DynamicQuery resourcePermissionDynamicQuery =
			DynamicQueryFactoryUtil.forClass(ResourcePermission.class);

		Property roleIdProperty = PropertyFactoryUtil.forName("roleId");

		DynamicQuery roleDynamicQuery = DynamicQueryFactoryUtil.forClass(
			Role.class);

		roleDynamicQuery.add(RestrictionsFactoryUtil.like("name", "Loop %"));

		Projection roleIdProjection = ProjectionFactoryUtil.property("roleId");

		roleDynamicQuery.setProjection(roleIdProjection);

		resourcePermissionDynamicQuery.add(roleIdProperty.in(roleDynamicQuery));

		resourcePermissionDynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"scope", ResourceConstants.SCOPE_COMPANY));

		List<ResourcePermission> resourcePermissions =
			_resourcePermissionLocalService.dynamicQuery(
				resourcePermissionDynamicQuery);

		for (ResourcePermission resourcePermission : resourcePermissions) {
			_resourcePermissionLocalService.deleteResourcePermission(
				resourcePermission);
		}
	}

	protected void importRoles() throws Exception {
		URL url = _bundle.getResource(
			"com/liferay/osb/loop/web/internal/setup/roles.xml");

		String xml = new String(_file.getBytes(url.openStream()));

		Document document = _saxReader.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> roleElements = rootElement.elements("role");

		for (Element roleElement : roleElements) {
			addRole(roleElement);
		}
	}

	@Reference(
		target = "(javax.portlet.name=" + AssetEntrySetPortletKeys.ASSET_ENTRY_SET + StringPool.CLOSE_PARENTHESIS
	)
	private Portlet _assetEntrySetPortlet;

	private Bundle _bundle;

	@Reference
	private File _file;

	@Reference(
		target = "(javax.portlet.name=" + LoopPortletKeys.LOOP + StringPool.CLOSE_PARENTHESIS
	)
	private Portlet _loopPortlet;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(javax.portlet.name=" + PushNotificationsPortletKeys.PUSH_NOTIFICATIONS + StringPool.CLOSE_PARENTHESIS
	)
	private Portlet _pushNotificationsPortlet;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private SAXReader _saxReader;

	@Reference(
		target = "(javax.portlet.name=" + TokenAuthPortletKeys.TOKEN_AUTH_MANAGER + StringPool.CLOSE_PARENTHESIS
	)
	private Portlet _tokenAuthManagerPortlet;

	@Reference
	private UserLocalService _userLocalService;

}