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

package com.liferay.osb.asah.common.bigquery.impl;

import com.liferay.osb.asah.common.bigquery.BigQuerySchemaManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
public class NoOpBigQuerySchemaManagerImpl implements BigQuerySchemaManager {

	@Override
	public void createSchema(Project project) {
		if (_log.isInfoEnabled()) {
			_log.info("Skipping create schema operation on local environments");
		}
	}

	@Override
	public void deleteSchema(String projectId) {
		if (_log.isInfoEnabled()) {
			_log.info("Skipping delete schema operation on local environments");
		}
	}

	private static final Log _log = LogFactory.getLog(
		NoOpBigQuerySchemaManagerImpl.class);

}