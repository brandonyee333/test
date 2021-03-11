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

import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.repository.AsahTaskRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class AsahTaskDog extends BaseFaroInfoDog {

	public AsahTask addAsahTask(AsahTask asahTask) {
		return _asahTaskRepository.save(asahTask);
	}

	public void deleteAllAsahTask() {
		_asahTaskRepository.deleteAll();
	}

	public void deleteAsahTask(AsahTask asahTask) {
		_asahTaskRepository.delete(asahTask);
	}

	public void deleteAsahTaskById(Long asahTaskId) {
		_asahTaskRepository.deleteById(asahTaskId);
	}

	public AsahTask getAsahTask(Long asahTaskId) {
		Optional<AsahTask> asahTaskOptional = _asahTaskRepository.findById(
			asahTaskId);

		return asahTaskOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no Asah Task with ID " + asahTaskId));
	}

	public List<AsahTask> getImmediateAsahTasks() {
		return _asahTaskRepository.findByCronExpressionIsNull();
	}

	public List<AsahTask> getScheduledAsahTasks() {
		return _asahTaskRepository.findByCronExpressionIsNotNull();
	}

	public AsahTask scheduleAsahTask(AsahTask asahTask) {
		asahTask = addAsahTask(asahTask);

		if (asahTask.getCronExpression() == null) {
			_nanitesHttp.executeOSBAsahTask(asahTask);
		}
		else {
			_nanitesHttp.scheduleOSBAsahTask(asahTask);
		}

		return asahTask;
	}

	public void unscheduleAsahTask(AsahTask asahTask) {
		_nanitesHttp.unscheduleOSBAsahTask(asahTask);

		deleteAsahTask(asahTask);
	}

	@Autowired
	private AsahTaskRepository _asahTaskRepository;

	@Autowired
	private NanitesHttp _nanitesHttp;

}