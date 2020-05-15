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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.servlet.filter.BaseSecurityFilter;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import javax.annotation.PostConstruct;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@ConditionalOnProperty("osb.asah.security.enabled")
@MonolithExclude
public class SecurityFilter extends BaseSecurityFilter {

	@PostConstruct
	public void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	protected boolean isInvalidRequest(HttpServletRequest httpServletRequest) {
		String faroBackendSecuritySignature = httpServletRequest.getHeader(
			"OSB-Asah-Faro-Backend-Security-Signature");

		if (faroBackendSecuritySignature == null) {
			logInvalidRequest(null, httpServletRequest);

			return true;
		}

		if (!_elasticsearchInvoker.exists(
				"data-sources",
				QueryBuilders.termQuery(
					"faroBackendSecuritySignature",
					faroBackendSecuritySignature))) {

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

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}