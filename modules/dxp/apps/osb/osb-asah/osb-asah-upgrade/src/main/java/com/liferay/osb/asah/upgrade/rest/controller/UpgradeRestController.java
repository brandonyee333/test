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
import com.liferay.osb.asah.upgrade.v3_0_5.EventIndexUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_0_5.IndividualEventUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_0.EventsUpgradeStep;
import com.liferay.osb.asah.upgrade.v3_1_0.PagesUpgradeStep;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	@GetMapping("/event-index-upgrades")
	public JSONArray getEventIndexUpgrades() {
		return _getUpgradesJSONArray(_eventIndexCompletableFutures);
	}

	@GetMapping("/events-upgrades")
	public JSONArray getEventsUpgrades() {
		return _getUpgradesJSONArray(_eventsCompletableFutures);
	}

	@GetMapping("/individual-event-upgrades")
	public JSONArray getIndividualEventUpgrades() {
		return _getUpgradesJSONArray(_individualEventsCompletableFutures);
	}

	@GetMapping("/pages-upgrades")
	public JSONArray getPagesUpgrades() {
		return _getUpgradesJSONArray(_pagesCompletableFutures);
	}

	@PostMapping("/event-index-upgrade/{projectId}")
	public void startEventIndexUpgrade(@PathVariable String projectId) {
		Optional<Project> projectOptional = _fetchProject(projectId);

		if (!projectOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid project ID");
		}

		_startEventIndexUpgrade(projectId);
	}

	@PostMapping("/event-index-upgrades")
	public void startEventIndexUpgrades() {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		stream.map(
			Project::getId
		).forEach(
			this::_startEventIndexUpgrade
		);
	}

	@PostMapping("/events-upgrade/{projectId}")
	public void startEventsUpgrade(@PathVariable String projectId) {
		Optional<Project> projectOptional = _fetchProject(projectId);

		if (!projectOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid project ID");
		}

		_startEventsUpgrade(projectId);
	}

	@PostMapping("/events-upgrades")
	public void startEventsUpgrades() {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		stream.map(
			Project::getId
		).forEach(
			this::_startEventsUpgrade
		);
	}

	@PostMapping("/individual-event-upgrade/{projectId}")
	public void startIndividualEventUpgrade(@PathVariable String projectId) {
		Optional<Project> projectOptional = _fetchProject(projectId);

		if (!projectOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid project ID");
		}

		_startIndividualEventUpgrade(projectId);
	}

	@PostMapping("/individual-event-upgrades")
	public void startIndividualEventUpgrades() {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		stream.map(
			Project::getId
		).forEach(
			this::_startIndividualEventUpgrade
		);
	}

	@PostMapping("/pages-upgrade/{projectId}")
	public void startPagesUpgrade(@PathVariable String projectId) {
		Optional<Project> projectOptional = _fetchProject(projectId);

		if (!projectOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid project ID");
		}

		_startPagesUpgrade(projectId);
	}

	@PostMapping("/pages-upgrades")
	public void startPagesUpgrades() {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		stream.map(
			Project::getId
		).forEach(
			this::_startPagesUpgrade
		);
	}

	private Optional<Project> _fetchProject(String projectId) {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		return stream.filter(
			project -> Objects.equals(project.getId(), projectId)
		).findAny();
	}

	private JSONArray _getUpgradesJSONArray(
		Map<String, CompletableFuture<Void>> completableFutures) {

		JSONArray jsonArray = new JSONArray();

		for (Map.Entry<String, CompletableFuture<Void>> entry :
				completableFutures.entrySet()) {

			jsonArray.put(_toJSONObject(entry.getValue(), entry.getKey()));
		}

		return jsonArray;
	}

	private void _startEventIndexUpgrade(String projectId) {
		CompletableFuture<Void> completableFuture =
			_eventIndexCompletableFutures.get(projectId);

		if (completableFuture != null) {
			return;
		}

		_eventIndexCompletableFutures.put(
			projectId,
			CompletableFuture.runAsync(
				() -> ProjectIdThreadLocal.forProject(
					projectId,
					() -> {
						try {
							_eventIndexUpgradeStep.upgrade(null);
						}
						catch (Exception exception) {
							_log.error(
								"Unable to upgrade event index for " +
									projectId,
								exception);
						}
					}),
				_executorService));
	}

	private void _startEventsUpgrade(String projectId) {
		CompletableFuture<Void> completableFuture =
			_eventsCompletableFutures.get(projectId);

		if (completableFuture != null) {
			return;
		}

		_eventsCompletableFutures.put(
			projectId,
			CompletableFuture.runAsync(
				() -> ProjectIdThreadLocal.forProject(
					projectId,
					() -> {
						try {
							_eventsUpgradeStep.upgrade("3.1.0");
						}
						catch (Exception exception) {
							_log.error(
								"Unable to upgrade events for " + projectId,
								exception);
						}
					}),
				_executorService));
	}

	private void _startIndividualEventUpgrade(String projectId) {
		CompletableFuture<Void> completableFuture =
			_individualEventsCompletableFutures.get(projectId);

		if (completableFuture != null) {
			return;
		}

		_individualEventsCompletableFutures.put(
			projectId,
			CompletableFuture.runAsync(
				() -> ProjectIdThreadLocal.forProject(
					projectId, () -> _individualEventUpgradeStep.upgrade(null)),
				_executorService));
	}

	private void _startPagesUpgrade(String projectId) {
		CompletableFuture<Void> completableFuture =
			_pagesCompletableFutures.get(projectId);

		if (completableFuture != null) {
			return;
		}

		_pagesCompletableFutures.put(
			projectId,
			CompletableFuture.runAsync(
				() -> ProjectIdThreadLocal.forProject(
					projectId,
					() -> {
						try {
							_pagesUpgradeStep.upgrade("3.1.0");
						}
						catch (Exception exception) {
							_log.error(
								"Unable to upgrade pages for " + projectId,
								exception);
						}
					}),
				_executorService));
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

	private static final Log _log = LogFactory.getLog(
		UpgradeRestController.class);

	private final Map<String, CompletableFuture<Void>>
		_eventIndexCompletableFutures = new ConcurrentHashMap<>();

	@Autowired
	private EventIndexUpgradeStep _eventIndexUpgradeStep;

	private final Map<String, CompletableFuture<Void>>
		_eventsCompletableFutures = new ConcurrentHashMap<>();

	@Autowired
	private EventsUpgradeStep _eventsUpgradeStep;

	private final ExecutorService _executorService =
		Executors.newSingleThreadExecutor();
	private final Map<String, CompletableFuture<Void>>
		_individualEventsCompletableFutures = new ConcurrentHashMap<>();

	@Autowired
	private IndividualEventUpgradeStep _individualEventUpgradeStep;

	private final Map<String, CompletableFuture<Void>>
		_pagesCompletableFutures = new ConcurrentHashMap<>();

	@Autowired
	private PagesUpgradeStep _pagesUpgradeStep;

	@Autowired
	private ProjectDog _projectDog;

}