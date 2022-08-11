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

import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQUserRepository;
import com.liferay.osb.asah.dataflow.emulator.model.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;

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
			bqUser -> _toBQIndividual(
				bqUser.getDataSourceId(), bqUser.getEmailAddress(),
				bqUser.getFieldsJSONArray(), bqUser.getModifiedDate())
		).collect(
			Collectors.toList()
		);
	}

	private Date _getFieldsLastModifiedDate(Collection<Field> fields) {
		Stream<Field> stream = fields.stream();

		Comparator<Field> fieldModifiedDateComparator = Comparator.comparing(
			Field::getModifiedDate);

		Optional<Field> lastModifiedFieldOptional = stream.sorted(
			fieldModifiedDateComparator.reversed()
		).findFirst();

		return lastModifiedFieldOptional.map(
			Field::getModifiedDate
		).orElse(
			null
		);
	}

	private BQIndividual _mergeBQIndividual(
		BQIndividual bqIndividual1, BQIndividual bqIndividual2) {

		BQIndividual mergedBQIndividual = new BQIndividual();

		mergedBQIndividual.setEmailAddress(bqIndividual1.getEmailAddress());

		String emailAddressHashed = DigestUtils.sha256Hex(
			StringUtils.lowerCase(bqIndividual1.getEmailAddress()));

		mergedBQIndividual.setEmailAddressHashed(emailAddressHashed);

		Collection<Field> mergedBQIndividualFields = _mergeBQIndividualFields(
			bqIndividual1, bqIndividual2);

		mergedBQIndividual.setFieldsJSONArray(
			_objectMapper.convertValue(
				mergedBQIndividualFields, JSONArray.class));

		mergedBQIndividual.setId(emailAddressHashed);
		mergedBQIndividual.setIsNew(Boolean.TRUE);
		mergedBQIndividual.setModifiedDate(
			_getFieldsLastModifiedDate(mergedBQIndividualFields));

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

	private BQIndividual _toBQIndividual(
		Long dataSourceId, String emailAddress, JSONArray fieldJSONArray,
		Date modifiedDate) {

		BQIndividual bqIndividual = new BQIndividual();

		bqIndividual.setEmailAddress(emailAddress);
		bqIndividual.setFieldsJSONArray(
			_objectMapper.convertValue(
				_toFields(dataSourceId, fieldJSONArray, modifiedDate),
				JSONArray.class));
		bqIndividual.setModifiedDate(modifiedDate);

		return bqIndividual;
	}

	private Field[] _toFields(
		Long dataSourceId, JSONArray fieldsJSONArray, Date modifiedDate) {

		Field[] fields = _objectMapper.convertValue(
			fieldsJSONArray, Field[].class);

		for (Field field : fields) {
			field.setDataSourceId(dataSourceId);
			field.setModifiedDate(modifiedDate);
		}

		return fields;
	}

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}