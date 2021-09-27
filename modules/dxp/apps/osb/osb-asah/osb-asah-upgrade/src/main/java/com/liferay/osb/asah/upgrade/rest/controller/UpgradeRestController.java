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

import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.v3_0_0.CustomEventUpgradeStep;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping("/upgrades")
@RestController
public class UpgradeRestController {

	@GetMapping("/custom-event-upgrades")
	public JSONArray getCustomEventUpgrades() {
		JSONArray jsonArray = new JSONArray();

		for (Map.Entry<String, CompletableFuture<Void>> entry :
				_completableFutures.entrySet()) {

			jsonArray.put(_toJSONObject(entry.getValue(), entry.getKey()));
		}

		return jsonArray;
	}

	@PostMapping("/custom-event-upgrade/{projectId}")
	public void startCustomEventUpgrade(@PathVariable String projectId) {
		CompletableFuture<Void> completableFuture = _completableFutures.get(
			projectId);

		if (completableFuture != null) {
			return;
		}

		Optional<Project> projectOptional = _fetchProject(projectId);

		if (!projectOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid project ID");
		}

		_completableFutures.put(
			projectId,
			CompletableFuture.runAsync(
				() -> ProjectIdThreadLocal.forProject(
					projectId, () -> _customEventUpgradeStep.upgrade(null)),
				_executorService));
	}

	private Optional<Project> _fetchProject(String projectId) {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		return stream.filter(
			project -> Objects.equals(project.getId(), projectId)
		).findAny();
	}

	private JSONObject _toJSONObject(
		CompletableFuture<Void> completableFuture, String projectId) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("projectId", projectId);

		if (completableFuture.isDone()) {
			jsonObject.put("status", "COMPLETED");
		}
		else {
			jsonObject.put("status", "PENDING");
		}

		return jsonObject;
	}

	private final Map<String, CompletableFuture<Void>> _completableFutures =
		new ConcurrentHashMap<>();

	@Autowired
	private CustomEventUpgradeStep _customEventUpgradeStep;

	private final ExecutorService _executorService =
		Executors.newSingleThreadExecutor();

	@Autowired
	private ProjectDog _projectDog;

}