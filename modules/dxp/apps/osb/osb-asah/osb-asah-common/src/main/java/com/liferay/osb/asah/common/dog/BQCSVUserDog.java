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

import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQCSVUserRepository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Marcellus Tavares
 */
@Component
public class BQCSVUserDog {

	public void addBQCSVUsers(List<BQCSVUser> bqCSVUsers) {
		if (bqCSVUsers.isEmpty()) {
			return;
		}

		_bqCSVUserRepository.saveAll(bqCSVUsers);

		BQCSVUser bqCSVUser = bqCSVUsers.get(0);

		_asahTaskDog.scheduleAsahTask(
			"CSVUsersNanite",
			JSONUtil.put(
				"dataSourceId", String.valueOf(bqCSVUser.getDataSourceId())
			).put(
				"type", "reprocess"
			));
	}

	public void deleteBQCSVUsers(Long dataSourceId) {
		_bqCSVUserRepository.deleteByDataSourceId(dataSourceId);
	}

	public void deleteBQCSVUsers(
		Long dataSourceId, List<String> dataSourceUserPKs) {

		_bqCSVUserRepository.deleteByDataSourceIdAndDataSourceUserPKIn(
			dataSourceId, dataSourceUserPKs);
	}

	public BQCSVUser fetchBQCSVUser(
		Long dataSourceId, String fieldKey, String fieldValue) {

		List<BQCSVUser> bqCSVUsers =
			_bqCSVUserRepository.findByDataSourceIdAndFieldKeyEquals(
				dataSourceId, fieldKey, fieldValue);

		if (bqCSVUsers.isEmpty()) {
			return null;
		}

		if ((bqCSVUsers.size() > 1) && _log.isWarnEnabled()) {
			_log.warn("Multiple CSV Individuals were fetched");
		}

		return bqCSVUsers.get(0);
	}

	public List<BQCSVUser> getBQCSVUsers(
		Long dataSourceId, int page, int size, Sort sort) {

		return _bqCSVUserRepository.findByDataSourceId(
			dataSourceId, PageRequest.of(page, size, sort));
	}

	public long getBQCSVUsersCount(Long dataSourceId) {
		return _bqCSVUserRepository.countByDataSourceId(dataSourceId);
	}

	public BQCSVUser updateBQCSVUser(BQCSVUser bqCSVUser) {
		return _bqCSVUserRepository.save(bqCSVUser);
	}

	private static final Log _log = LogFactory.getLog(BQCSVUserDog.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private BQCSVUserRepository _bqCSVUserRepository;

}