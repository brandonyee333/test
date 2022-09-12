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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.model.Field;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.hadoop.util.StringUtils;

import org.json.JSONArray;

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
		_bqIndividualRepository.saveAll(bqIndividuals);
	}

	private List<BQIndividual> _fetchBQUsersBQIndividuals() {
		List<BQUser> bqUsers = IterableUtils.toList(
			_bqUserRepository.findAll());

		Stream<BQUser> stream = bqUsers.stream();

		return stream.map(
			this::_toBQIndividual
		).sorted(
			Comparator.comparing(BQIndividual::getModifiedDate)
		).collect(
			Collectors.toList()
		);
	}

	private List<Field> _fetchCustomFields(BQUser bqUser) {
		List<BQExpandoValue> bqExpandoValues =
			_bqExpandoValueRepository.findByClassPKAndClassTypeAndDataSourceId(
				bqUser.getDXPUserId(), DXPEntity.Type.CLASS_NAME_USER,
				bqUser.getDataSourceId());

		Stream<BQExpandoValue> stream = bqExpandoValues.stream();

		return stream.map(
			bqExpandoValue -> {
				Field field = new Field();

				field.setDataSourceId(bqExpandoValue.getDataSourceId());
				field.setModifiedDate(bqExpandoValue.getModifiedDate());
				field.setName(_resolveFieldName(bqExpandoValue));
				field.setValue(bqExpandoValue.getValue());

				return field;
			}
		).collect(
			Collectors.toList()
		);
	}

	private BQIndividual _mergeBQIndividual(
		BQIndividual bqIndividual1, BQIndividual bqIndividual2) {

		BQIndividual mergedBQIndividual = new BQIndividual();

		mergedBQIndividual.setEmailAddress(bqIndividual2.getEmailAddress());
		mergedBQIndividual.setEmailAddressHashed(
			bqIndividual2.getEmailAddressHashed());

		Collection<Field> mergedBQIndividualFields = _mergeBQIndividualFields(
			bqIndividual1, bqIndividual2);

		mergedBQIndividual.setFieldsJSONArray(
			_objectMapper.convertValue(
				mergedBQIndividualFields, JSONArray.class));

		mergedBQIndividual.setFirstName(bqIndividual2.getFirstName());
		mergedBQIndividual.setIsNew(Boolean.TRUE);
		mergedBQIndividual.setLastName(bqIndividual2.getLastName());
		mergedBQIndividual.setMiddleName(bqIndividual2.getMiddleName());
		mergedBQIndividual.setModifiedDate(bqIndividual2.getModifiedDate());
		mergedBQIndividual.setScreenName(bqIndividual2.getScreenName());

		return mergedBQIndividual;
	}

	private Collection<Field> _mergeBQIndividualFields(
		BQIndividual bqIndividual1, BQIndividual bqIndividual2) {

		List<Field> fields = new ArrayList<>();

		fields.addAll(
			_objectMapper.convertValue(
				bqIndividual1.getFieldsJSONArray(),
				new TypeReference<List<Field>>() {
				}));
		fields.addAll(
			_objectMapper.convertValue(
				bqIndividual2.getFieldsJSONArray(),
				new TypeReference<List<Field>>() {
				}));

		Stream<Field> stream1 = fields.stream();

		Comparator<Field> fieldModifiedDateComparator = Comparator.comparing(
			Field::getModifiedDate);

		Map<String, Field> mergedFieldsMap = stream1.collect(
			Collectors.groupingBy(
				Field::getName,
				Collectors.collectingAndThen(
					Collectors.maxBy(fieldModifiedDateComparator),
					Optional::get)));

		return mergedFieldsMap.values();
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
		BQExpandoColumn bqExpandoColumn =
			_bqExpandoColumnRepository.findByColumnIdAndDataSourceId(
				bqExpandoValue.getColumnId(), bqExpandoValue.getDataSourceId());

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

		bqIndividual.setEmailAddress(bqUser.getEmailAddress());
		bqIndividual.setEmailAddressHashed(
			DigestUtils.sha256Hex(
				StringUtils.toLowerCase(bqUser.getEmailAddress())));

		List<Field> defaultFields = _toFields(
			bqUser.getDataSourceId(), bqUser.getFieldsJSONArray(),
			bqUser.getModifiedDate());

		List<Field> customFields = _fetchCustomFields(bqUser);

		bqIndividual.setFieldsJSONArray(
			_objectMapper.convertValue(
				CollectionUtils.union(defaultFields, customFields),
				JSONArray.class));

		bqIndividual.setFirstName(bqUser.getFirstName());
		bqIndividual.setLastName(bqUser.getLastName());
		bqIndividual.setMiddleName(bqUser.getMiddleName());
		bqIndividual.setScreenName(bqUser.getScreenName());
		bqIndividual.setIsNew(Boolean.TRUE);
		bqIndividual.setModifiedDate(bqUser.getModifiedDate());

		return bqIndividual;
	}

	private List<Field> _toFields(
		Long dataSourceId, JSONArray fieldsJSONArray, Date modifiedDate) {

		List<Field> fields = _objectMapper.convertValue(
			fieldsJSONArray,
			new TypeReference<List<Field>>() {
			});

		if (fields == null) {
			return Collections.emptyList();
		}

		for (Field field : fields) {
			field.setDataSourceId(dataSourceId);
			field.setModifiedDate(modifiedDate);
		}

		return fields;
	}

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private BQExpandoValueRepository _bqExpandoValueRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}