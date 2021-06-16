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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AsahTaskRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.IterableUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class AsahTaskDog extends BaseFaroInfoDog {

	public void deleteAsahTask(Long asahTaskId) {
		_asahTaskRepository.deleteById(asahTaskId);
	}

	public void deleteAsahTasks() {
		_asahTaskRepository.deleteAll();
	}

	public AsahTask getAsahTask(Long asahTaskId) {
		Optional<AsahTask> asahTaskOptional = _asahTaskRepository.findById(
			asahTaskId);

		return asahTaskOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no Asah Task with ID " + asahTaskId));
	}

	public List<AsahTask> getAsahTasks() {
		return IterableUtils.toList(_asahTaskRepository.findAll());
	}

	public List<AsahTask> getAsahTasks(String className) {
		return _asahTaskRepository.findByClassName(className);
	}

	public List<AsahTask> getImmediateAsahTasks() {
		return _asahTaskRepository.findByCronExpressionIsNull();
	}

	public List<AsahTask> getScheduledAsahTasks() {
		return _asahTaskRepository.findByCronExpressionIsNotNull();
	}

	public AsahTask scheduleAsahTask(AsahTask asahTask) {
		asahTask = _asahTaskRepository.save(asahTask);

		if (asahTask.getCronExpression() == null) {
			_nanitesHttp.executeAsahTask(asahTask.getId());
		}
		else {
			_nanitesHttp.scheduleAsahTask(asahTask.getId());
		}

		return asahTask;
	}

	public void scheduleAsahTask(String className, JSONArray contextJSONArray) {
		try {
			Iterable<AsahTask> asahTasks = _asahTaskRepository.saveAll(
				JSONUtil.toList(
					contextJSONArray,
					contextJSONObject -> new AsahTask(
						className, contextJSONObject,
						ProjectIdThreadLocal.getProjectId())));

			Stream<AsahTask> stream = StreamSupport.stream(
				asahTasks.spliterator(), true);

			stream.map(
				AsahTask::getId
			).forEach(
				_nanitesHttp::executeAsahTask
			);
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Unable to schedule Asah Task", exception);
		}
	}

	public AsahTask scheduleAsahTask(
		String className, JSONObject contextJSONObject) {

		return scheduleAsahTask(
			new AsahTask(
				className, contextJSONObject,
				ProjectIdThreadLocal.getProjectId()));
	}

	public AsahTask scheduleAsahTask(
		String className, JSONObject contextJSONObject, String cronExpression) {

		return scheduleAsahTask(
			new AsahTask(
				className, contextJSONObject, cronExpression,
				ProjectIdThreadLocal.getProjectId()));
	}

	public void unscheduleAsahTask(Long asahTaskId) {
		_nanitesHttp.unscheduleAsahTask(asahTaskId);

		deleteAsahTask(asahTaskId);
	}

	@Autowired
	private AsahTaskRepository _asahTaskRepository;

	@Autowired
	private NanitesHttp _nanitesHttp;

}