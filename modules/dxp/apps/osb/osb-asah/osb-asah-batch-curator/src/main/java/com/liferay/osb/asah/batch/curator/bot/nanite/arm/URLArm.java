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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vishal Reddy
 */
@Component
public class URLArm {

	public long getTotalKeywordViews(String dayDateString, String keyword) {
		return _faroInfoActivityDog.getTotalKeywordViews(
			dayDateString, getURLs(keyword));
	}

	public List<String> getURLs(String keyword) {
		List<Asset> assets = _assetDog.getAssets("Page", keyword);

		Stream<Asset> stream = assets.stream();

		return stream.map(
			Asset::getDataSourceAssetPK
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

}