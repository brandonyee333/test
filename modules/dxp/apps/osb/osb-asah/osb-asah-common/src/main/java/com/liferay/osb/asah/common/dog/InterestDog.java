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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class InterestDog {

	public void addInterests(List<Interest> interests) {
		_interestRepository.saveAll(interests);
	}

	public void deleteInterests(Long ownerId, String ownerType) {
		_interestRepository.deleteByOwnerIdAndOwnerType(ownerId, ownerType);
	}

	public void deleteInterests(String ownerType, Date recordedDate) {
		_interestRepository.deleteByOwnerTypeAndRecordedDateLessThanEqual(
			ownerType, recordedDate);
	}

	public Interest fetchInterest(
		String name, Long ownerId, String ownerType, Date recordedDate) {

		return _interestRepository.
			getByNameAndOwnerIdAndOwnerTypeAndRecordedDate(
				name, ownerId, ownerType, recordedDate);
	}

	public Interest getInterest(Long id) {
		Optional<Interest> interestOptional = _interestRepository.findById(id);

		return interestOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST, "There is no interest with ID " + id));
	}

	public Page<Interest> getInterestPage(
		Long ownerId, String ownerType, int size, int start) {

		return PageableExecutionUtils.getPage(
			_interestRepository.findByOwnerIdAndOwnerType(
				ownerId, ownerType, PageRequest.of(start / size, size)),
			PageRequest.of(start / size, size),
			() -> _interestRepository.countByOwnerIdAndOwnerType(
				ownerId, ownerType));
	}

	public Page<Interest> getInterestPage(
		String filterString, Double score, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		FilterHelper filterHelper = new FilterHelper(filterString);

		return PageableExecutionUtils.getPage(
			_interestRepository.findByFilterStringAndScoreGreaterThanEqual(
				filterHelper, score, pageRequest),
			pageRequest,
			() ->
				_interestRepository.countByFilterStringAndScoreGreaterThanEqual(
					filterHelper, score));
	}

	public List<Interest> getInterests(
		Long interestId, String ownerType, Date recordedDate, int size) {

		return _interestRepository.findByOwnerTypeAndRecordedDate(
			interestId, ownerType, recordedDate, size);
	}

	public List<Interest> getInterests(
		String name, Long ownerId, String ownerType, Date fromRecordedDate,
		Date toRecordedDate) {

		return _interestRepository.
			findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
				name, ownerId, ownerType, fromRecordedDate, toRecordedDate);
	}

	public List<String> getTopNames(Long ownerId, String ownerType, int size) {
		return _interestRepository.getTopNamesByOwnerIdAndOwnerType(
			ownerId, ownerType, size);
	}

	public JSONArray getTransformations(
		String apply, String filterString, int page, int size) {

		String period = "day";

		if (apply != null) {
			Matcher matcher = _periodPattern.matcher(apply);

			if (!matcher.find()) {
				throw new IllegalArgumentException("Invalid apply: " + apply);
			}

			String fieldName = matcher.group("fieldName");

			if (!fieldName.equals("dateRecorded")) {
				throw new IllegalArgumentException(
					"Compute function not supported for " + fieldName);
			}

			period = matcher.group("period");
		}

		Date toDate = DateUtil.addDays(DateUtil.newDayDate(), -(page * size));

		Date fromDate = DateUtil.addDays(toDate, 1 - size);

		return new JSONArray(
			_interestRepository.getTransformations(
				fromDate, new FilterHelper(filterString), period, toDate));
	}

	private static final Pattern _periodPattern = Pattern.compile(
		"compute\\((?<period>\\w+)\\((?<fieldname>\\w+)\\)\\)");

	@Autowired
	private InterestRepository _interestRepository;

}