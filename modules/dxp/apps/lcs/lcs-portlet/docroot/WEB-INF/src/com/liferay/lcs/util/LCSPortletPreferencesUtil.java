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

package com.liferay.lcs.util;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.List;

import javax.portlet.ReadOnlyException;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
public class LCSPortletPreferencesUtil {

	public static synchronized javax.portlet.PortletPreferences
		fetchReadOnlyJxPortletPreferences() {

		PortletPreferences portletPreferences = _fetchPortletPreferences();

		try {
			return new ImmutablePortletPreferences(
				PortletPreferencesFactoryUtil.fromXML(
					CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
					PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0,
					PortletKeys.MONITORING,
					portletPreferences.getPreferences()));
		}
		catch (SystemException se) {
			_log.error(se, se);
		}

		return null;
	}

	public static synchronized void store(String key, String value)
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

		PortletPreferencesLocalServiceUtil.updatePortletPreferences(
			portletPreferences);

		PortletPreferencesPersistence portletPreferencesPersistence =
			_getPersistence();

		portletPreferencesPersistence.clearCache(portletPreferences);
	}

	private static PortletPreferences _fetchPortletPreferences() {
		PortletPreferences portletPreferences = null;

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
				PortletPreferencesLocalServiceUtil.dynamicQuery(dynamicQuery);

			if (!portletPreferencesList.isEmpty()) {
				if (portletPreferencesList.size() == 1) {
					portletPreferences = portletPreferencesList.get(0);
				}
				else {
					_log.error(
						"Unable to determine unique portlet preferences");

					return null;
				}
			}

			if (portletPreferences == null) {
				portletPreferences =
					PortletPreferencesLocalServiceUtil.addPortletPreferences(
						CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
						PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0,
						PortletKeys.MONITORING, null, null);
			}
		}
		catch (SystemException se) {
			_log.error("Unable to get portlet preferences", se);
		}

		return portletPreferences;
	}

	private static PortletPreferencesPersistence _getPersistence() {
		return (PortletPreferencesPersistence)PortalBeanLocatorUtil.locate(
			PortletPreferencesPersistence.class.getName());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSPortletPreferencesUtil.class);

}