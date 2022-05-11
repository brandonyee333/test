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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcos Martins
 */
public abstract class BaseBQDXPEntityDogTestCase
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	public void setUp() {
		Channel channel = new Channel("channel");

		channel.setId(11L);
		channel.setIsNew(true);

		dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");
		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(123L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		dataSource = _dataSourceRepository.save(dataSource);

		channel.addChannelDataSource(
			new ChannelDataSource(dataSource.getId(), null));

		_channelRepository.save(channel);

		bqExpandoColumn = new BQExpandoColumn();

		bqExpandoColumn.setColumnId(1L);
		bqExpandoColumn.setDataSourceId(dataSource.getId());
		bqExpandoColumn.setDataType("STRING");
		bqExpandoColumn.setId("1");
		bqExpandoColumn.setIsNew(true);
		bqExpandoColumn.setName("column");

		bqExpandoColumn = _bqExpandoColumnRepository.save(bqExpandoColumn);

		bqExpandoValue = new BQExpandoValue();

		bqExpandoValue.setColumnId(bqExpandoColumn.getColumnId());
		bqExpandoValue.setId("1");
		bqExpandoValue.setIsNew(true);
		bqExpandoValue.setValue("test");

		bqExpandoValue = _bqExpandoValueRepository.save(bqExpandoValue);
	}

	protected BQExpandoColumn bqExpandoColumn;
	protected BQExpandoValue bqExpandoValue;
	protected DataSource dataSource;

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private BQExpandoValueRepository _bqExpandoValueRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}