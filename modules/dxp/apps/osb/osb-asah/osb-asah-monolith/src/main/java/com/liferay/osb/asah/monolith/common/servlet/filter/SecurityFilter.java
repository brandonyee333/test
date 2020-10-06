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

package com.liferay.osb.asah.monolith.common.servlet.filter;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.servlet.filter.BaseSecurityFilter;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@ConditionalOnProperty("osb.asah.security.enabled")
public class SecurityFilter extends BaseSecurityFilter {

	@Override
	protected boolean isInvalidRequest(HttpServletRequest httpServletRequest) {
		String faroBackendSecuritySignature = httpServletRequest.getHeader(
			"OSB-Asah-Faro-Backend-Security-Signature");

		if (faroBackendSecuritySignature == null) {
			logInvalidRequest(null, httpServletRequest);

			return true;
		}

		if (StringUtils.startsWith(
				httpServletRequest.getRequestURI(), "/api/recommendations") ||
			StringUtils.startsWith(
				httpServletRequest.getRequestURI(), "/api/reports")) {

			return super.isInvalidRequest(httpServletRequest);
		}

		if (StringUtils.contains(httpServletRequest.getRequestURI(), "/api/") ||
			StringUtils.contains(
				httpServletRequest.getRequestURI(), "/dxp-batch-entities") ||
			StringUtils.equals(
				httpServletRequest.getRequestURI(), "/dxp-entities")) {

			if (!_elasticsearchInvoker.exists(
					"data-sources",
					QueryBuilders.termQuery(
						"faroBackendSecuritySignature",
						faroBackendSecuritySignature))) {

				logInvalidRequest(
					faroBackendSecuritySignature, httpServletRequest);

				return true;
			}

			return false;
		}

		return super.isInvalidRequest(httpServletRequest);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) {
		String requestURI = httpServletRequest.getRequestURI();

		if (requestURI.equals("/") || requestURI.equals("/identity")) {
			return true;
		}

		return super.shouldNotFilter(httpServletRequest);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}