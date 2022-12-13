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

package com.liferay.osb.asah.backend.servlet.filter;

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.servlet.filter.BaseSecurityOncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@ConditionalOnProperty("osb.asah.security.enabled")
public class SecurityOncePerRequestFilter
	extends BaseSecurityOncePerRequestFilter {

	@Override
	protected boolean isInvalidRequest(HttpServletRequest httpServletRequest) {
		String faroBackendSecuritySignature = httpServletRequest.getHeader(
			HeaderConstants.FARO_BACKEND_SECURITY_SIGNATURE);

		if (faroBackendSecuritySignature == null) {
			logInvalidRequest(null, httpServletRequest);

			return true;
		}

		// DXP Data Source status probe request

		if (StringUtils.equals(httpServletRequest.getMethod(), "GET") &&
			StringUtils.contains(
				httpServletRequest.getRequestURI(), "/api/1.0/data-sources")) {

			return false;
		}

		if (StringUtils.contains(httpServletRequest.getRequestURI(), "/api/") &&
			!StringUtils.contains(
				httpServletRequest.getRequestURI(), "/recommendations") &&
			!StringUtils.contains(
				httpServletRequest.getRequestURI(), "/reports")) {

			if (!_dataSourceDog.existsDataSource(
					faroBackendSecuritySignature)) {

				logInvalidRequest(
					faroBackendSecuritySignature, httpServletRequest);

				return true;
			}

			return false;
		}

		return super.isInvalidRequest(httpServletRequest);
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

}