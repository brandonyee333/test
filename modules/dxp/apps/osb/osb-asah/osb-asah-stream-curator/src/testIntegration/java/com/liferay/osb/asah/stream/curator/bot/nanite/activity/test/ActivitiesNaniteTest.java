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

package com.liferay.osb.asah.stream.curator.bot.nanite.activity.test;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.activity.ActivitiesNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.test.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.messaging.MessageBusTestHelper;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

/**
 * @author Vishal Reddy
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class ActivitiesNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(1L);

		_dataSourceRepository.save(dataSource);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testCanonicalUrlSet() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_1.json", this));

		runNanite();

		Assert.assertEquals(
			1,
			_faroInfoElasticsearchInvoker.count(
				"activities",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("eventId", "pageViewed")
				).filter(
					QueryBuilders.termQuery(
						"object.canonicalUrl",
						"https://customer.liferay.com/documentation/search")
				).filter(
					QueryBuilders.termQuery(
						"userId", "34209dc0-a1b7-11e8-bf1b-f987e25a7caa")
				)));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testCommentPostedActivityIsAdded() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_1.json", this));

		runNanite();

		Assert.assertEquals(28, _assetRepository.count());

		List<Asset> assets =
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				"Page", FilterHelper.EMPTY,
				"8 Best Practices for Your Omnichannel Strategy | Digital " +
					"Strategy | Liferay Blogs",
				PageRequest.of(0, 5));

		Assert.assertFalse(assets.isEmpty());

		Asset asset = assets.get(0);

		Assert.assertEquals(
			"https://www.liferay.com/blog?regionCategoryName=en-us&" +
				"blogCategoryName=digital-strategy&" +
					"title=8-best-practices-for-your-omnichannel-strategy",
			asset.getDataSourceAssetPK());

		Assert.assertTrue(
			_faroInfoElasticsearchInvoker.exists(
				"activities",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("applicationId", "Blog")
				).filter(
					QueryBuilders.termQuery("eventId", "commentPosted")
				)));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testFormSubmittedPropertiesAreAdded() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_3.json", this));

		runNanite();

		JSONObject activityJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("eventId", "formSubmitted")));

		JSONObject eventPropertiesJSONObject = activityJSONObject.getJSONObject(
			"eventProperties");

		Assert.assertEquals(
			"24000", eventPropertiesJSONObject.getString("duration"));
		Assert.assertEquals(
			"GL Contact: Sales - MKTG FRM KK",
			eventPropertiesJSONObject.getString("title"));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testKeywordsLowercase() throws Exception {
		MessageBusTestHelper messageBusTestHelper = new MessageBusTestHelper(
			_messageBus);

		messageBusTestHelper.prepareMessageBusChannel(
			Channel.ANALYTICS_EVENTS_ACTIVITY,
			ResourceUtil.readResourceToJSONArray(
				"dependencies/analytics_events_1.json", this));

		runNanite();

		List<Asset> assets =
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				"Page", FilterHelper.EMPTY,
				"Felix Gogo Shell - reference - Knowledge | \"Liferay",
				PageRequest.of(0, 5));

		Assert.assertFalse(assets.isEmpty());

		Asset asset = assets.get(0);

		Set<AssetKeyword> assetKeywords = asset.getAssetKeywords();

		Assert.assertFalse(assetKeywords.isEmpty());

		Stream<AssetKeyword> stream = assetKeywords.stream();

		String[] keywords = stream.map(
			AssetKeyword::getKeyword
		).toArray(
			String[]::new
		);

		Arrays.sort(keywords);

		Assert.assertArrayEquals(
			new String[] {"felix gogo shell", "knowledge", "reference"},
			keywords);
	}

	@Override
	protected Nanite getNanite() {
		return _activitiesNanite;
	}

	@Autowired
	private ActivitiesNanite _activitiesNanite;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private MessageBus _messageBus;

}