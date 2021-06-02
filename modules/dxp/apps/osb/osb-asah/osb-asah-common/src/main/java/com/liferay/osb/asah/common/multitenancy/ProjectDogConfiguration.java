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

package com.liferay.osb.asah.common.multitenancy;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchSnapshotManager;
import com.liferay.osb.asah.common.elasticsearch.repository.impl.ElasticsearchProjectRepositoryImpl;
import com.liferay.osb.asah.common.multitenancy.impl.MultiTenantProjectDogImpl;
import com.liferay.osb.asah.common.multitenancy.impl.SingleTenantProjectDogImpl;
import com.liferay.osb.asah.common.repository.ProjectRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author André Miranda
 */
@Configuration
public class ProjectDogConfiguration {

	@Bean
	public ProjectDog projectDog(ProjectRepository projectRepository) {
		if (!ServiceConstants.OSB_ASAH_MULTITENANCY_ENABLED) {
			return new SingleTenantProjectDogImpl();
		}

		if (projectRepository instanceof ElasticsearchProjectRepositoryImpl) {
			return new MultiTenantProjectDogImpl(
				this::_createSnapshots, projectRepository);
		}

		return new MultiTenantProjectDogImpl(null, projectRepository);
	}

	private void _createSnapshots(String projectId) {
		try {
			_elasticsearchSnapshotManager.createSnapshotLifecyclePolicy(
				projectId);
		}
		catch (Exception exception) {
			_log.error(
				"Unable to create snapshot lifecycle policy for project " +
					projectId,
				exception);
		}
	}

	private static final Log _log = LogFactory.getLog(
		MultiTenantProjectDogImpl.class);

	@Autowired
	private ElasticsearchSnapshotManager _elasticsearchSnapshotManager;

}