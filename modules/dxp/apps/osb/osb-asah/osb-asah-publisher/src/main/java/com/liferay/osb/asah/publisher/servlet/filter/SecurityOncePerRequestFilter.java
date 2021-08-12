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

package com.liferay.osb.asah.publisher.servlet.filter;

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.servlet.filter.BaseSecurityOncePerRequestFilter;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import javax.servlet.http.HttpServletRequest;

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

		if (!_dataSourceDog.existsDataSource(faroBackendSecuritySignature)) {
			logInvalidRequest(faroBackendSecuritySignature, httpServletRequest);

			return true;
		}

		return false;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) {
		String requestURI = httpServletRequest.getRequestURI();

		if (requestURI.equals("/") || requestURI.equals("/identity")) {
			return true;
		}

		return super.shouldNotFilter(httpServletRequest);
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}