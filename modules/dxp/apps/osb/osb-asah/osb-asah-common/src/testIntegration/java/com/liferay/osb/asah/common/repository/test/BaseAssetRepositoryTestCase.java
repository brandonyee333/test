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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoAssetFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.Repository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseAssetRepositoryTestCase
	extends BaseRepositoryTestCase<Asset, Long> {

	@Before
	public void setUp() {
		DataSource dataSource = _addDataSource();

		_dataSourceId = Optional.ofNullable(
			dataSource
		).map(
			DataSource::getId
		).orElse(
			0L
		);

		Asset asset1 = new Asset();

		asset1.setAssetType("Page");
		asset1.setCanonicalURL("https://www.terrance-lueilwitz.biz");
		asset1.setChannelIds(SetUtil.of(1L));
		asset1.setDataSourceAssetPK("https://www.terrance-lueilwitz.biz");
		asset1.setDataSourceId(dataSource.getId());
		asset1.setAssetKeywords(
			SetUtil.of(new AssetKeyword("holistic ROI", "title")));
		asset1.setTitle("empower holistic ROI");
		asset1.setURL("https://www.terrance-lueilwitz.biz");

		Asset asset2 = new Asset();

		asset2.setAssetType("Page");
		asset2.setCanonicalURL("https://www.jamey-shields.info");
		asset2.setChannelIds(SetUtil.of(1L));
		asset2.setDataSourceAssetPK("https://www.jamey-shields.info");
		asset2.setDataSourceId(dataSource.getId());
		asset2.setAssetKeywords(
			SetUtil.of(
				new AssetKeyword("engineer", "title"),
				new AssetKeyword("intuitive", "title"),
				new AssetKeyword("models", "title")));
		asset2.setTitle("engineer intuitive models");
		asset2.setURL("https://www.jamey-shields.info");

		Asset asset3 = new Asset();

		asset3.setAssetType("Page");
		asset3.setCanonicalURL("https://www.iva-tillman.com");
		asset3.setChannelIds(SetUtil.of(1L));
		asset3.setDataSourceAssetPK("https://www.iva-tillman.com");
		asset3.setDataSourceId(dataSource.getId());
		asset3.setAssetKeywords(
			SetUtil.of(
				new AssetKeyword("compelling action-items", "title"),
				new AssetKeyword("models", "title")));
		asset3.setTitle("seize compelling action-items");
		asset3.setURL("https://www.iva-tillman.com");

		setUpRepository(asset1, asset2, asset3);
	}

	@Test
	public void testCountAssetKeywords() {
		Assert.assertEquals(5, _assetRepository.countAssetKeywords(null));

		Assert.assertEquals(1, _assetRepository.countAssetKeywords("ROI"));
	}

	@Test
	public void testCountByAssetTypeAndCanonicalUrlIn() {
		Assert.assertEquals(
			2,
			_assetRepository.countByAssetTypeAndCanonicalURLIn(
				"Page",
				Arrays.asList(
					"https://www.terrance-lueilwitz.biz",
					"https://www.iva-tillman.com")));
	}

	@Test
	public void testFindByAssetType() {
		List<Asset> assets =
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				"Page", FilterHelper.EMPTY, null,
				PageRequest.of(0, 20, Sort.desc("id")));

		Assert.assertEquals(assets.toString(), 3, assets.size());
		Assert.assertEquals(
			Arrays.asList(
				"seize compelling action-items", "engineer intuitive models",
				"empower holistic ROI"),
			_getPageAssetTitles(assets));
	}

	@Test
	public void testFindByAssetTypeAndCanonicalUrlIn() {
		List<Asset> assets = _assetRepository.findByAssetTypeAndCanonicalURLIn(
			"Page",
			Arrays.asList(
				"https://www.terrance-lueilwitz.biz",
				"https://www.iva-tillman.com"),
			PageRequest.of(0, 10));

		Assert.assertEquals(assets.toString(), 2, assets.size());
	}

	@Test
	public void testFindByAssetTypeAndFilterString1() {
		List<Asset> assets =
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				"Page",
				new FilterHelper(
					_faroInfoAssetFilterStringConverterHelper,
					"similarTo(title, 'seize.*')",
					_defaultFilterStringConverterHelper),
				null, PageRequest.of(0, 20, Sort.desc("id")));

		Assert.assertEquals(assets.toString(), 1, assets.size());
		Assert.assertEquals(
			Arrays.asList("seize compelling action-items"),
			_getPageAssetTitles(assets));
	}

	@Test
	public void testFindByAssetTypeAndFilterString2() {
		List<Asset> assets =
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				"Page",
				new FilterHelper(
					_faroInfoAssetFilterStringConverterHelper,
					"title eq 'engineer intuitive models'",
					_defaultFilterStringConverterHelper),
				null, PageRequest.of(0, 20, Sort.desc("id")));

		Assert.assertEquals(assets.toString(), 1, assets.size());
		Assert.assertEquals(
			Arrays.asList("engineer intuitive models"),
			_getPageAssetTitles(assets));
	}

	@Test
	public void testFindByAssetTypeAndFilterString3() {
		List<Asset> assets =
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				"Page",
				new FilterHelper(
					_faroInfoAssetFilterStringConverterHelper,
					"url eq 'https://www.terrance-lueilwitz.biz'",
					_defaultFilterStringConverterHelper),
				null, PageRequest.of(0, 20, Sort.desc("id")));

		Assert.assertEquals(assets.toString(), 1, assets.size());
		Assert.assertEquals(
			Arrays.asList("empower holistic ROI"), _getPageAssetTitles(assets));
	}

	@Test
	public void testFindByAssetTypeAndKeyword2() {
		List<Asset> assets =
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				"Page", FilterHelper.EMPTY, "seize",
				PageRequest.of(0, 20, Sort.desc("id")));

		Assert.assertEquals(assets.toString(), 1, assets.size());
		Assert.assertEquals(
			Arrays.asList("seize compelling action-items"),
			_getPageAssetTitles(assets));
	}

	@Test
	public void testFindByAssetTypeAndKeywordNotNull() {
		Asset asset = new Asset();

		asset.setAssetType("Page");
		asset.setCanonicalURL("https://www.no-keywords.com");
		asset.setChannelIds(SetUtil.of(1L));
		asset.setDataSourceAssetPK("https://www.no-keywords.com");
		asset.setDataSourceId(1L);
		asset.setTitle("no keywords");
		asset.setURL("https://www.no-keywords.com");

		_assetRepository.save(asset);

		List<Asset> assets =
			_assetRepository.findByAssetTypeAndAssetKeywordNotNull(
				0L, "Page", 50);

		Assert.assertEquals(assets.toString(), 3, assets.size());
	}

	@Test
	public void testFindByChannelIds() {
		List<Asset> assets = _assetRepository.findByChannelIds(
			Arrays.asList(1L, 2L), PageRequest.of(0, 10));

		Assert.assertEquals(assets.toString(), 3, assets.size());

		assets = _assetRepository.findByChannelIds(
			Arrays.asList(2L), PageRequest.of(0, 10));

		Assert.assertEquals(assets.toString(), 0, assets.size());

		assets = _assetRepository.findByChannelIds(
			Arrays.asList(1L), PageRequest.of(0, 10));

		Assert.assertEquals(assets.toString(), 3, assets.size());
	}

	@Test
	public void testFindByFilterString() {
		List<Asset> assets = _assetRepository.findByFilterString(
			new FilterHelper(
				_faroInfoAssetFilterStringConverterHelper,
				"keywords/keyword eq 'engineer' and keywords/type eq 'title'",
				_defaultFilterStringConverterHelper),
			PageRequest.of(0, 20, Sort.desc("id")));

		Assert.assertEquals(assets.toString(), 1, assets.size());
		Assert.assertEquals(
			Arrays.asList("engineer intuitive models"),
			_getPageAssetTitles(assets));
	}

	@Test
	public void testFindKeywordByAssetType() {
		List<String> keywords = _assetRepository.findKeywordByAssetType("Page");

		Stream<String> stream = keywords.stream();

		keywords = stream.map(
			String::toLowerCase
		).collect(
			Collectors.toList()
		);

		Assert.assertEquals(keywords.toString(), 5, keywords.size());

		Assert.assertEquals(
			Arrays.asList(
				"compelling action-items", "engineer", "holistic roi",
				"intuitive", "models"),
			keywords);
	}

	public void testGetByAssetTypeAndChannelIdAndDatasourceId() {
		Map<String, Set<String>> assets =
			_assetRepository.getByAssetTypeAndChannelIdAndDatasourceId(
				"Page", 1L, _dataSourceId);

		Assert.assertEquals(assets.toString(), assets.size(), 5);

		Set<String> keySet = assets.keySet();

		Stream<String> keyStream = keySet.stream();

		Assert.assertThat(
			new String[] {
				"holistic roi", "engineer", "intuitive", "models",
				"compelling action-items"
			},
			Matchers.arrayContainingInAnyOrder(
				keyStream.map(
					String::toLowerCase
				).toArray(
					String[]::new
				)));

		Collection<Set<String>> values = assets.values();

		values.forEach(value -> Assert.assertFalse(value.isEmpty()));
	}

	@Override
	protected Repository<Asset, Long> getRepository() {
		return _assetRepository;
	}

	private DataSource _addDataSource() {
		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");

		Channel channel = new Channel("channel1");

		channel.setId(11L);
		channel.setIsNew(true);

		_channelRepository.save(channel);

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(1L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		return _dataSourceRepository.save(dataSource);
	}

	private List<String> _getPageAssetTitles(List<Asset> assets) {
		return ListUtil.map(assets, Asset::getTitle);
	}

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	private long _dataSourceId;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private final DefaultFilterStringConverterHelper
		_defaultFilterStringConverterHelper =
			new DefaultFilterStringConverterHelper();
	private final FaroInfoAssetFilterStringConverterHelper
		_faroInfoAssetFilterStringConverterHelper =
			new FaroInfoAssetFilterStringConverterHelper();

}