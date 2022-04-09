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

package com.liferay.osb.asah.upgrade.rest.controller;

import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.v3_2_0.DXPEntitiesUpgradeStep;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcos Martins
 */
@RequestMapping("/upgrades")
@RestController
public class UpgradeRestController {

	@PostMapping("/dxp-entities-upgrades")
	public void startDXPEntitiesUpgrades() {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		stream.map(
			Project::getId
		).forEach(
			this::_startDXPEntitiesUpgrade
		);
	}

	private void _startDXPEntitiesUpgrade(String projectId) {
		CompletableFuture<Void> completableFuture =
			_dxpEntitiesCompletableFutures.get(projectId);

		if (completableFuture != null) {
			return;
		}

		_dxpEntitiesCompletableFutures.put(
			projectId,
			CompletableFuture.runAsync(
				() -> ProjectIdThreadLocal.forProject(
					projectId,
					() -> {
						try {
							_dxpEntitiesUpgradeStep.upgrade("3.2.0");
						}
						catch (Exception exception) {
							_log.error(
								"Unable to upgrade dxp entities for " +
									projectId,
								exception);
						}
					}),
				_executorService));
	}

	private static final Log _log = LogFactory.getLog(
		UpgradeRestController.class);

	private final Map<String, CompletableFuture<Void>>
		_dxpEntitiesCompletableFutures = new ConcurrentHashMap();

	@Autowired(required = false)
	private DXPEntitiesUpgradeStep _dxpEntitiesUpgradeStep;

	private final ExecutorService _executorService =
		Executors.newSingleThreadExecutor();

	@Autowired
	private ProjectDog _projectDog;

}