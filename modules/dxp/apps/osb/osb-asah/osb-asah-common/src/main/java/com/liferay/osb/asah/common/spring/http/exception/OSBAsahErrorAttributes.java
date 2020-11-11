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

package com.liferay.osb.asah.common.spring.http.exception;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Leslie Wong
 */
@Component
public class OSBAsahErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(
		WebRequest webRequest, ErrorAttributeOptions options) {

		_log.error("Unable to process request", getError(webRequest));

		OSBAsahError osbAsahError = new OSBAsahError(
			_environment.getActiveProfiles());

		osbAsahError.setErrorAttributes(
			super.getErrorAttributes(webRequest, options));

		return osbAsahError.getErrorAttributes();
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahErrorAttributes.class);

	@Autowired
	private Environment _environment;

}