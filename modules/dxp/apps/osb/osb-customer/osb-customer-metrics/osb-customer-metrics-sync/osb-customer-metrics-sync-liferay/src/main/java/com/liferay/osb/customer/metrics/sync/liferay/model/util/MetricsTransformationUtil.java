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

package com.liferay.osb.customer.metrics.sync.liferay.model.util;

import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsTransformationUtil.class)
public class MetricsTransformationUtil {

	public Map<String, Object> transformSharedAttributes(
		Map<String, Object> attributes) {

		for (String dxpAttributeKeys : _OSB_DXP_ATTRIBUTES) {
			attributes.remove(dxpAttributeKeys);
		}

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String lowerCaseKey = StringUtil.lowerCase(entry.getKey());

			if (lowerCaseKey.endsWith("classnameid")) {
				Long classNameId = (Long)entry.getValue();

				ClassName className = _classNameLocalService.fetchClassName(
					classNameId);

				if (className != null) {
					entry.setValue(className);

					String classNameValue = className.getValue();

					if (classNameValue.equals(User.class.getName())) {
						int pos = lowerCaseKey.lastIndexOf("class");

						String entryKey = entry.getKey();

						String classPKKey =
							entryKey.substring(0, pos + 1) + "lassPK";

						Long classPK = (Long)attributes.get(classPKKey);

						if (classPK != null) {
							User user = _userLocalService.fetchUser(classPK);

							if (user != null) {
								attributes.put(classPKKey, user);
							}
						}
					}
				}
			}
			else if (lowerCaseKey.endsWith("userid")) {
				Long userId = (Long)entry.getValue();

				User user = _userLocalService.fetchUser(userId);

				if (user != null) {
					entry.setValue(user);
				}
			}
		}

		return attributes;
	}

	private static final String[] _OSB_DXP_ATTRIBUTES = {
		"entityCacheEnabled", "finderCacheEnabled", "mvccVersion"
	};

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private UserLocalService _userLocalService;

}