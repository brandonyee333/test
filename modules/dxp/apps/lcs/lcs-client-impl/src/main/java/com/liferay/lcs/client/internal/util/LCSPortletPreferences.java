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

package com.liferay.lcs.client.internal.util;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.List;

import javax.portlet.ReadOnlyException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
@Component(service = LCSPortletPreferences.class)
public class LCSPortletPreferences {

	public synchronized javax.portlet.PortletPreferences
		fetchReadOnlyJxPortletPreferences() {

		PortletPreferences portletPreferences = _fetchPortletPreferences();

		if (portletPreferences == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("LCS client portlet properties missing");
			}

			return null;
		}

		try {
			return new ImmutablePortletPreferences(
				PortletPreferencesFactoryUtil.fromXML(
					CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
					PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0,
					PortletKeys.MONITORING,
					portletPreferences.getPreferences()));
		}
		catch (Exception e) {
			_log.error("Unable to create LCS client's portlet preferences", e);
		}

		return null;
	}

	public synchronized void store(String key, String value)
		throws ReadOnlyException {

		PortletPreferences portletPreferences = _fetchPortletPreferences();

		javax.portlet.PortletPreferences jxPortletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
				PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0, PortletKeys.MONITORING,
				portletPreferences.getPreferences());

		jxPortletPreferences.setValue(key, value);

		portletPreferences.setPreferences(
			PortletPreferencesFactoryUtil.toXML(jxPortletPreferences));

		_portletPreferencesLocalService.updatePortletPreferences(
			portletPreferences);

		PortletPreferencesPersistence portletPreferencesPersistence =
			_getPersistence();

		portletPreferencesPersistence.clearCache(portletPreferences);
	}

	private PortletPreferences _fetchPortletPreferences() {
		try {
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
				PortletPreferences.class,
				PortalClassLoaderUtil.getClassLoader());

			dynamicQuery.add(
				RestrictionsFactoryUtil.eq("ownerId", CompanyConstants.SYSTEM));
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq(
					"ownerType", PortletKeys.PREFS_OWNER_TYPE_COMPANY));
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq("plid", Long.valueOf(0)));
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq(
					"portletId", PortletKeys.MONITORING));

			List<PortletPreferences> portletPreferencesList =
				_portletPreferencesLocalService.dynamicQuery(dynamicQuery);

			if (portletPreferencesList.isEmpty()) {
				return _portletPreferencesLocalService.addPortletPreferences(
					CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
					PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0,
					PortletKeys.MONITORING, null, null);
			}

			if (portletPreferencesList.size() == 1) {
				return portletPreferencesList.get(0);
			}

			_log.error(
				"Multiple entries for the LCS client's portlet preferences");
		}
		catch (Exception e) {
			_log.error("Unable to get LCS client's portlet preferences", e);
		}

		return null;
	}

	private PortletPreferencesPersistence _getPersistence() {
		return (PortletPreferencesPersistence)PortalBeanLocatorUtil.locate(
			PortletPreferencesPersistence.class.getName());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortletPreferences.class);

	@Reference(target = ModuleServiceLifecycle.DATABASE_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}