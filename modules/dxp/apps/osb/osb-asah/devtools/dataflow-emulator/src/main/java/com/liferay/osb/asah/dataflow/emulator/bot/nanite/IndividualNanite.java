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

package com.liferay.osb.asah.dataflow.emulator.bot.nanite;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQUserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.ListUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class IndividualNanite {

	public void run() {
		List<BQIndividual> bqIndividuals = _mergeBQIndividuals(
			_fetchBQUsersBQIndividuals());

		if (bqIndividuals.isEmpty()) {
			return;
		}

		_bqIndividualRepository.deleteAll();

		bqIndividuals.forEach(_bqIndividualRepository::insert);
	}

	private List<BQIndividual> _fetchBQUsersBQIndividuals() {
		List<BQIdentity> bqIdentities = _bqIdentityRepository.findAll();
		List<BQUser> bqUsers = _bqUserRepository.findAll();

		Stream<BQIdentity> bqIdentityStream = bqIdentities.stream();

		Set<String> individualIdsSet = bqIdentityStream.map(
			BQIdentity::getIndividualId
		).filter(
			Objects::nonNull
		).collect(
			Collectors.toSet()
		);

		Stream<BQUser> stream = bqUsers.stream();

		return stream.filter(
			bqUser -> individualIdsSet.contains(bqUser.getIndividualId())
		).map(
			this::_toBQIndividual
		).sorted(
			Comparator.comparing(BQIndividual::getModifiedDate)
		).collect(
			Collectors.toList()
		);
	}

	private List<BQIndividual.Field> _fetchCustomFields(BQUser bqUser) {
		List<BQExpandoValue> bqExpandoValues =
			_bqExpandoValueRepository.findByClassPKAndClassTypeAndDataSourceId(
				bqUser.getDXPUserId(), DXPEntity.Type.CLASS_NAME_USER,
				bqUser.getDataSourceId());

		Stream<BQExpandoValue> stream = bqExpandoValues.stream();

		return stream.map(
			bqExpandoValue -> {
				BQIndividual.Field field = new BQIndividual.Field(
					bqExpandoValue.getDataSourceId(),
					_resolveFieldName(bqExpandoValue),
					bqExpandoValue.getValue());

				field.setModifiedDate(bqUser.getModifiedDate());

				return field;
			}
		).collect(
			Collectors.toList()
		);
	}

	private Object _getFieldValueByName(
		List<BQIndividual.Field> fields, String name) {

		for (BQIndividual.Field field : fields) {
			if (Objects.equals(field.getName(), name)) {
				return field.getValue();
			}
		}

		return null;
	}

	private Date _getFieldValueDateByName(
		List<BQIndividual.Field> fields, String name) {

		String valueString = _getFieldValueStringByName(fields, name);

		if (valueString == null) {
			return null;
		}

		return new Date(Long.parseLong(valueString));
	}

	private String _getFieldValueStringByName(
		List<BQIndividual.Field> fields, String name) {

		Object value = _getFieldValueByName(fields, name);

		if (value == null) {
			return null;
		}

		return String.valueOf(value);
	}

	private BQIndividual _mergeBQIndividual(
		BQIndividual bqIndividual1, BQIndividual bqIndividual2) {

		BQIndividual mergedBQIndividual = new BQIndividual();

		mergedBQIndividual.setAddresses(bqIndividual2.getAddresses());
		mergedBQIndividual.setBirthday(bqIndividual2.getBirthday());
		mergedBQIndividual.setCreateDate(bqIndividual2.getCreateDate());
		mergedBQIndividual.setEmailAddress(bqIndividual2.getEmailAddress());
		mergedBQIndividual.setFields(
			_mergeBQIndividualFields(bqIndividual1, bqIndividual2));
		mergedBQIndividual.setFirstName(bqIndividual2.getFirstName());
		mergedBQIndividual.setGender(bqIndividual2.getGender());
		mergedBQIndividual.setId(bqIndividual2.getId());
		mergedBQIndividual.setLanguageId(bqIndividual2.getLanguageId());
		mergedBQIndividual.setLastName(bqIndividual2.getLastName());
		mergedBQIndividual.setMiddleName(bqIndividual2.getMiddleName());
		mergedBQIndividual.setModifiedDate(bqIndividual2.getModifiedDate());
		mergedBQIndividual.setScreenName(bqIndividual2.getScreenName());
		mergedBQIndividual.setTimeZoneId(bqIndividual2.getTimeZoneId());

		return mergedBQIndividual;
	}

	private List<BQIndividual.Field> _mergeBQIndividualFields(
		BQIndividual bqIndividual1, BQIndividual bqIndividual2) {

		List<BQIndividual.Field> fields = ListUtils.union(
			bqIndividual1.getFields(), bqIndividual2.getFields());

		Stream<BQIndividual.Field> stream = fields.stream();

		Comparator<BQIndividual.Field> fieldModifiedDateComparator =
			Comparator.comparing(BQIndividual.Field::getModifiedDate);

		Map<String, BQIndividual.Field> mergedFieldsMap = stream.collect(
			Collectors.groupingBy(
				BQIndividual.Field::getName,
				Collectors.collectingAndThen(
					Collectors.maxBy(fieldModifiedDateComparator),
					Optional::get)));

		return new ArrayList<>(mergedFieldsMap.values());
	}

	private List<BQIndividual> _mergeBQIndividuals(
		List<BQIndividual> bqIndividuals) {

		if (bqIndividuals.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<BQIndividual> stream1 = bqIndividuals.stream();

		Map<String, Optional<BQIndividual>> map = stream1.collect(
			Collectors.groupingBy(
				BQIndividual::getEmailAddress,
				Collectors.reducing(this::_mergeBQIndividual)));

		Collection<Optional<BQIndividual>> collection = map.values();

		Stream<Optional<BQIndividual>> stream2 = collection.stream();

		return stream2.filter(
			Optional::isPresent
		).map(
			Optional::get
		).collect(
			Collectors.toList()
		);
	}

	private String _resolveFieldName(BQExpandoValue bqExpandoValue) {
		Optional<BQExpandoColumn> bqExpandoColumnOptional =
			_bqExpandoColumnRepository.findByColumnIdAndDataSourceId(
				bqExpandoValue.getColumnId(), bqExpandoValue.getDataSourceId());

		BQExpandoColumn bqExpandoColumn = bqExpandoColumnOptional.get();

		String fieldName =
			bqExpandoColumn.getName() + "_" + bqExpandoColumn.getDataType();

		String displayType = bqExpandoColumn.getDisplayType();

		if (Objects.equals(displayType, "checkbox") ||
			Objects.equals(displayType, "radio") ||
			Objects.equals(displayType, "selection-list")) {

			return fieldName + "_array";
		}

		return fieldName;
	}

	private BQIndividual _toBQIndividual(BQUser bqUser) {
		BQIndividual bqIndividual = new BQIndividual();

		List<BQIndividual.Field> defaultFields = _toFields(
			bqUser.getDataSourceId(), bqUser.getFields(),
			bqUser.getModifiedDate());

		bqIndividual.setAddresses(
			_getFieldValueStringByName(defaultFields, "addresses"));
		bqIndividual.setBirthday(
			_getFieldValueDateByName(defaultFields, "birthday"));

		bqIndividual.setCreateDate(new Date());
		bqIndividual.setEmailAddress(bqUser.getEmailAddress());
		bqIndividual.setFields(
			ListUtils.union(defaultFields, _fetchCustomFields(bqUser)));
		bqIndividual.setFirstName(bqUser.getFirstName());
		bqIndividual.setGender(
			_getFieldValueStringByName(defaultFields, "gender"));
		bqIndividual.setId(bqUser.getIndividualId());
		bqIndividual.setJobTitle(bqUser.getJobTitle());
		bqIndividual.setLanguageId(
			_getFieldValueStringByName(defaultFields, "languageId"));
		bqIndividual.setLastName(bqUser.getLastName());
		bqIndividual.setMiddleName(bqUser.getMiddleName());
		bqIndividual.setModifiedDate(bqUser.getModifiedDate());
		bqIndividual.setScreenName(bqUser.getScreenName());
		bqIndividual.setTimeZoneId(
			_getFieldValueStringByName(defaultFields, "timeZoneId"));

		return bqIndividual;
	}

	private List<BQIndividual.Field> _toFields(
		Long dataSourceId, List<BQUser.Field> bqUserFields, Date modifiedDate) {

		if (bqUserFields == null) {
			return Collections.emptyList();
		}

		List<BQIndividual.Field> fields = new ArrayList<>();

		for (BQUser.Field bqUserField : bqUserFields) {
			BQIndividual.Field field = new BQIndividual.Field(
				dataSourceId, bqUserField.getName(), bqUserField.getValue());

			field.setModifiedDate(modifiedDate);

			fields.add(field);
		}

		return fields;
	}

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private BQExpandoValueRepository _bqExpandoValueRepository;

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}