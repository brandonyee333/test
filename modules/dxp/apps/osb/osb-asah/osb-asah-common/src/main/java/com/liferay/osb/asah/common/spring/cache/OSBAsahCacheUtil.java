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

package com.liferay.osb.asah.common.spring.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;

/**
 * @author Alejo Ceballos
 */
public class OSBAsahCacheUtil {

	public static Class<?> extractTargetClass(Object target) {
		try {
			Class<?>[] proxiedUserInterfacesClasses =
				AopProxyUtils.proxiedUserInterfaces(target);

			Class<?> candidateTargetClass = proxiedUserInterfacesClasses[0];

			String candidateTargetClassName = candidateTargetClass.getName();

			if (candidateTargetClassName.contains("com.liferay.osb.asah")) {
				return candidateTargetClass;
			}
		}
		catch (IllegalArgumentException illegalArgumentException) {
			if (_log.isDebugEnabled()) {
				Class<?> targetClass = target.getClass();

				_log.debug(
					targetClass.getName() + ": " +
						illegalArgumentException.getMessage());
			}
		}

		return AopUtils.getTargetClass(target);
	}

	private static final Log _log = LogFactory.getLog(OSBAsahCacheUtil.class);

}