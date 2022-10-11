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

package com.liferay.osb.asah.dataflow.emulator.bot.nanite.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQUserRepository;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.IndividualNanite;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.OSBAsahDataflowEmulatorSpringTestContext;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 */
public class IndividualNaniteTest
	implements OSBAsahDataflowEmulatorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testMergeCustomFieldNameDifferentType() {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setId(RandomTestUtil.randomUUID());
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("joe@liferay.com"));
		bqIdentity.setIsNew(Boolean.TRUE);

		_bqIdentityRepository.save(bqIdentity);

		// BQUser 1

		BQUser bqUser1 = new BQUser();

		bqUser1.setDXPUserId(RandomTestUtil.randomNumber());
		bqUser1.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser1.setEmailAddress("joe@liferay.com");
		bqUser1.setEmailAddressHashed(
			DigestUtils.sha256Hex(bqUser1.getEmailAddress()));
		bqUser1.setId(RandomTestUtil.randomString());
		bqUser1.setIsNew(Boolean.TRUE);
		bqUser1.setModifiedDate(DateUtil.toUTCDate("2022-08-04T12:00:00.000Z"));

		_bqUserRepository.save(bqUser1);

		BQExpandoColumn bqExpandoColumn1 = new BQExpandoColumn();

		bqExpandoColumn1.setColumnId(RandomTestUtil.randomString());
		bqExpandoColumn1.setClassName(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoColumn1.setDataSourceId(bqUser1.getDataSourceId());
		bqExpandoColumn1.setDataType("string");
		bqExpandoColumn1.setDisplayType("selection-list");
		bqExpandoColumn1.setId(RandomTestUtil.randomString());
		bqExpandoColumn1.setIsNew(Boolean.TRUE);
		bqExpandoColumn1.setName("age");

		_bqExpandoColumnRepository.save(bqExpandoColumn1);

		BQExpandoValue bqExpandoValue1 = new BQExpandoValue();

		bqExpandoValue1.setClassPK(bqUser1.getDXPUserId());
		bqExpandoValue1.setClassType(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoValue1.setColumnId(bqExpandoColumn1.getColumnId());
		bqExpandoValue1.setDataSourceId(bqUser1.getDataSourceId());
		bqExpandoValue1.setId(RandomTestUtil.randomString());
		bqExpandoValue1.setIsNew(Boolean.TRUE);
		bqExpandoValue1.setValue("[18]");
		bqExpandoValue1.setModifiedDate(bqUser1.getModifiedDate());

		_bqExpandoValueRepository.save(bqExpandoValue1);

		// BQUser 2

		BQUser bqUser2 = new BQUser();

		bqUser2.setDXPUserId(RandomTestUtil.randomNumber());
		bqUser2.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser2.setEmailAddress("joe@liferay.com");
		bqUser2.setEmailAddressHashed(
			DigestUtils.sha256Hex(bqUser2.getEmailAddress()));
		bqUser2.setId(RandomTestUtil.randomString());
		bqUser2.setIsNew(Boolean.TRUE);
		bqUser2.setModifiedDate(DateUtil.toUTCDate("2022-08-05T12:00:00.000Z"));

		_bqUserRepository.save(bqUser2);

		BQExpandoColumn bqExpandoColumn2 = new BQExpandoColumn();

		bqExpandoColumn2.setColumnId(RandomTestUtil.randomString());
		bqExpandoColumn2.setClassName(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoColumn2.setDataSourceId(bqUser2.getDataSourceId());
		bqExpandoColumn2.setDataType("string");
		bqExpandoColumn2.setDisplayType("input-field");
		bqExpandoColumn2.setId(RandomTestUtil.randomString());
		bqExpandoColumn2.setIsNew(Boolean.TRUE);
		bqExpandoColumn2.setName("age");

		_bqExpandoColumnRepository.save(bqExpandoColumn2);

		BQExpandoValue bqExpandoValue2 = new BQExpandoValue();

		bqExpandoValue2.setClassPK(bqUser2.getDXPUserId());
		bqExpandoValue2.setClassType(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoValue2.setColumnId(bqExpandoColumn2.getColumnId());
		bqExpandoValue2.setDataSourceId(bqUser2.getDataSourceId());
		bqExpandoValue2.setId(RandomTestUtil.randomString());
		bqExpandoValue2.setIsNew(Boolean.TRUE);
		bqExpandoValue2.setValue("20");
		bqExpandoValue2.setModifiedDate(bqUser2.getModifiedDate());

		_bqExpandoValueRepository.save(bqExpandoValue2);

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());
		Assertions.assertEquals(
			bqUser2.getModifiedDate(), bqIndividual.getModifiedDate());

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser1.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser1.getModifiedDate())
				).put(
					"name", "age_string_array"
				).put(
					"value", "[18]"
				),
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser2.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser2.getModifiedDate())
				).put(
					"name", "age_string"
				).put(
					"value", "20"
				)),
			bqIndividual.getFieldsJSONArray(), false);
	}

	@Test
	public void testMergeCustomFieldNameSameType() {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setId(RandomTestUtil.randomUUID());
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("joe@liferay.com"));
		bqIdentity.setIsNew(Boolean.TRUE);

		_bqIdentityRepository.save(bqIdentity);

		// BQUser 1

		BQUser bqUser1 = new BQUser();

		bqUser1.setDXPUserId(RandomTestUtil.randomNumber());
		bqUser1.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser1.setEmailAddress("joe@liferay.com");
		bqUser1.setEmailAddressHashed(
			DigestUtils.sha256Hex(bqUser1.getEmailAddress()));
		bqUser1.setId(RandomTestUtil.randomString());
		bqUser1.setIsNew(Boolean.TRUE);
		bqUser1.setModifiedDate(DateUtil.toUTCDate("2022-08-04T12:00:00.000Z"));

		_bqUserRepository.save(bqUser1);

		BQExpandoColumn bqExpandoColumn1 = new BQExpandoColumn();

		bqExpandoColumn1.setColumnId(RandomTestUtil.randomString());
		bqExpandoColumn1.setClassName(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoColumn1.setDataSourceId(bqUser1.getDataSourceId());
		bqExpandoColumn1.setDataType("string");
		bqExpandoColumn1.setDisplayType("input-field");
		bqExpandoColumn1.setId(RandomTestUtil.randomString());
		bqExpandoColumn1.setIsNew(Boolean.TRUE);
		bqExpandoColumn1.setName("age");

		_bqExpandoColumnRepository.save(bqExpandoColumn1);

		BQExpandoValue bqExpandoValue1 = new BQExpandoValue();

		bqExpandoValue1.setClassPK(bqUser1.getDXPUserId());
		bqExpandoValue1.setClassType(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoValue1.setColumnId(bqExpandoColumn1.getColumnId());
		bqExpandoValue1.setDataSourceId(bqUser1.getDataSourceId());
		bqExpandoValue1.setId(RandomTestUtil.randomString());
		bqExpandoValue1.setIsNew(Boolean.TRUE);
		bqExpandoValue1.setValue("18");
		bqExpandoValue1.setModifiedDate(bqUser1.getModifiedDate());

		_bqExpandoValueRepository.save(bqExpandoValue1);

		// BQUser 2

		BQUser bqUser2 = new BQUser();

		bqUser2.setDXPUserId(RandomTestUtil.randomNumber());
		bqUser2.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser2.setEmailAddress("joe@liferay.com");
		bqUser2.setEmailAddressHashed(
			DigestUtils.sha256Hex(bqUser2.getEmailAddress()));
		bqUser2.setId(RandomTestUtil.randomString());
		bqUser2.setIsNew(Boolean.TRUE);
		bqUser2.setModifiedDate(DateUtil.toUTCDate("2022-08-05T12:00:00.000Z"));

		_bqUserRepository.save(bqUser2);

		BQExpandoColumn bqExpandoColumn2 = new BQExpandoColumn();

		bqExpandoColumn2.setColumnId(RandomTestUtil.randomString());
		bqExpandoColumn2.setClassName(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoColumn2.setDataSourceId(bqUser2.getDataSourceId());
		bqExpandoColumn2.setDataType("string");
		bqExpandoColumn2.setDisplayType("input-field");
		bqExpandoColumn2.setId(RandomTestUtil.randomString());
		bqExpandoColumn2.setIsNew(Boolean.TRUE);
		bqExpandoColumn2.setName("age");

		_bqExpandoColumnRepository.save(bqExpandoColumn2);

		BQExpandoValue bqExpandoValue2 = new BQExpandoValue();

		bqExpandoValue2.setClassPK(bqUser2.getDXPUserId());
		bqExpandoValue2.setClassType(DXPEntity.Type.CLASS_NAME_USER);
		bqExpandoValue2.setColumnId(bqExpandoColumn2.getColumnId());
		bqExpandoValue2.setDataSourceId(bqUser2.getDataSourceId());
		bqExpandoValue2.setId(RandomTestUtil.randomString());
		bqExpandoValue2.setIsNew(Boolean.TRUE);
		bqExpandoValue2.setValue("20");
		bqExpandoValue2.setModifiedDate(bqUser2.getModifiedDate());

		_bqExpandoValueRepository.save(bqExpandoValue2);

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());
		Assertions.assertEquals(
			bqUser2.getModifiedDate(), bqIndividual.getModifiedDate());

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser2.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser2.getModifiedDate())
				).put(
					"name", "age_string"
				).put(
					"value", "20"
				)),
			bqIndividual.getFieldsJSONArray(), false);
	}

	@Test
	public void testMergeDefaultField() {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setId(RandomTestUtil.randomUUID());
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("joe@liferay.com"));
		bqIdentity.setIsNew(Boolean.TRUE);

		_bqIdentityRepository.save(bqIdentity);

		BQUser bqUser1 = new BQUser();

		bqUser1.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser1.setEmailAddress("joe@liferay.com");
		bqUser1.setEmailAddressHashed(
			DigestUtils.sha256Hex(bqUser1.getEmailAddress()));
		bqUser1.setFieldsJSONArray(
			JSONUtil.putAll(
				JSONUtil.put(
					"name", "country"
				).put(
					"value", "Brazil"
				)));
		bqUser1.setFirstName("Joe");
		bqUser1.setId(RandomTestUtil.randomString());
		bqUser1.setIsNew(Boolean.TRUE);
		bqUser1.setModifiedDate(DateUtil.toUTCDate("2022-08-04T12:00:00.000Z"));

		_bqUserRepository.save(bqUser1);

		BQUser bqUser2 = new BQUser();

		bqUser2.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser2.setEmailAddress("joe@liferay.com");
		bqUser2.setEmailAddressHashed(
			DigestUtils.sha256Hex(bqUser2.getEmailAddress()));
		bqUser2.setFirstName("Joseph");
		bqUser2.setFieldsJSONArray(
			JSONUtil.putAll(
				JSONUtil.put(
					"name", "country"
				).put(
					"value", "Argentina"
				),
				JSONUtil.put(
					"name", "company"
				).put(
					"value", "Liferay"
				)));
		bqUser2.setId(RandomTestUtil.randomString());
		bqUser2.setIsNew(Boolean.TRUE);
		bqUser2.setModifiedDate(DateUtil.toUTCDate("2022-08-03T12:00:00.000Z"));

		_bqUserRepository.save(bqUser2);

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());
		Assertions.assertEquals("Joe", bqIndividual.getFirstName());
		Assertions.assertEquals(
			bqUser1.getModifiedDate(), bqIndividual.getModifiedDate());

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser1.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser1.getModifiedDate())
				).put(
					"name", "country"
				).put(
					"value", "Brazil"
				),
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser2.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser2.getModifiedDate())
				).put(
					"name", "company"
				).put(
					"value", "Liferay"
				)),
			bqIndividual.getFieldsJSONArray(), false);
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
	private IndividualNanite _individualNanite;

}