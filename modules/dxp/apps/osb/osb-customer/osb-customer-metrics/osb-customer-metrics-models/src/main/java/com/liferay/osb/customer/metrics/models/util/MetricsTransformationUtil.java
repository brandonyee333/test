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

package com.liferay.osb.customer.metrics.models.util;

import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsTransformationUtil.class)
public class MetricsTransformationUtil {

	public Map<String, Object> transformSharedAttributes(
		Map<String, Object> attributes) {

		Set<Map.Entry<String, Object>> entrySet = attributes.entrySet();

		Iterator iterator = entrySet.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry =
				(Map.Entry<String, Object>)iterator.next();

			if (ArrayUtil.contains(_OSB_DXP_ATTRIBUTES, entry.getKey())) {
				iterator.remove();
			}

			String lowerCaseKey = StringUtil.lowerCase(entry.getKey());

			if (lowerCaseKey.endsWith(StringUtil.lowerCase("userId"))) {
				Long userId = (Long)attributes.get(entry.getKey());

				User user = _userLocalService.fetchUser(userId);

				if (user != null) {
					attributes.put(entry.getKey(), user.getUuid());
				}
			}

			if (lowerCaseKey.endsWith(StringUtil.lowerCase("classNameId"))) {
				Long classNameId = (Long)attributes.get(entry.getKey());

				ClassName className = _classNameLocalService.fetchClassName(
					classNameId);

				if (className != null) {
					String classNameValue = className.getValue();

					attributes.put(entry.getKey(), classNameValue);

					int pos = lowerCaseKey.lastIndexOf("class");

					StringBundler sb = new StringBundler(2);

					String entryKey = entry.getKey();

					sb.append(entryKey.substring(0, pos + 1));

					sb.append("lassPK");

					if (classNameValue.equals(User.class.getName())) {
						Long classPK = (Long)attributes.get(sb.toString());

						if (classPK != null) {
							User classPKUser = _userLocalService.fetchUser(
								classPK);

							if (classPKUser != null) {
								attributes.put(
									sb.toString(), classPKUser.getUuid());
							}
						}
					}
				}
			}
		}

		return attributes;
	}

	private static final String[] _OSB_DXP_ATTRIBUTES = {
		"finderCacheEnabled", "entityCacheEnabled", "mvccVersion"
	};

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private UserLocalService _userLocalService;

}