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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.repository.ProjectRepository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author André Miranda
 */
@Repository
public class ElasticsearchProjectRepositoryImpl
	extends BaseElasticsearchRepository<Project, String>
	implements ProjectRepository {

	@PostConstruct
	public void init() {
		_elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();
	}

	@Override
	protected String getCollectionName() {
		return "projects";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _elasticsearchInvoker;
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

}