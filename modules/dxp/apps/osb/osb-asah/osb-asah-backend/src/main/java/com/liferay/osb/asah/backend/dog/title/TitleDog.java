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

package com.liferay.osb.asah.backend.dog.title;

import com.liferay.osb.asah.backend.model.AssetType;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lino Alves
 */
@Component
public class TitleDog {

	@Autowired
	public TitleDog() {
	}

	public Map<String, String> getTitle(
		AssetType assetType, Set<String> assetIds) {

		return getTitle(assetIds, assetType, null);
	}

	public Map<String, String> getTitle(
		Set<String> assetIds, AssetType assetType, String keywords) {

		return Collections.emptyMap();
	}

}