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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoSessionsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.BQSession;
import com.liferay.osb.asah.common.repository.BQSessionRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.annotation.VisibleForTestingOnly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Component
public class UserSessionDog {

	@VisibleForTestingOnly
	public BQSession addBQSession(
		long channelId, String id, Date sessionEndDate, Date sessionStartDate) {

		BQSession bqSession = new BQSession();

		bqSession.setChannelId(channelId);
		bqSession.setId(id);
		bqSession.setSessionEnd(sessionEndDate);
		bqSession.setSessionStart(sessionStartDate);

		return _bqSessionRepository.save(bqSession);
	}

	public List<BQSession> findByIds(Collection<String> ids) {
		return _bqSessionRepository.findAllById(ids);
	}

	public List<Long> getIndividualIds(String filterString) {
		List<Long> individualIds = new ArrayList<>();

		// TODO Search for individual ids

		return individualIds;
	}

	public Page<String> searchBQSessionsFieldValues(
		String fieldName, String filterString, int page, int size,
		String value) {

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(page, size);

		return PageableExecutionUtils.getPage(
			_bqSessionRepository.searchSessionFieldValues(
				fieldName, filterHelper, pageRequest, value),
			pageRequest,
			() -> _bqSessionRepository.countSessionFieldValues(
				fieldName, filterHelper, value));
	}

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	private FaroInfoSessionsFilterStringConverterHelper
		_faroInfoSessionsFilterStringConverterHelper;

	@Autowired
	private ObjectMapper _objectMapper;

}