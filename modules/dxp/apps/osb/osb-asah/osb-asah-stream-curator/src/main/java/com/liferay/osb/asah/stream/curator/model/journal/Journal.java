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

package com.liferay.osb.asah.stream.curator.model.journal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;

/**
 * @author Inácio Nery
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = Journal.class, include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "messageFormat", use = JsonTypeInfo.Id.NAME, visible = true
)
public class Journal extends BaseAssetModel {

	public void addViews(long views) {
		_views += views;
	}

	public long getRatings() {
		return _ratings;
	}

	public float getRatingsScore() {
		return _ratingsScore;
	}

	public long getViews() {
		return _views;
	}

	public void setRatings(long ratings) {
		_ratings = ratings;
	}

	public void setRatingsScore(float ratingsScore) {
		_ratingsScore = ratingsScore;
	}

	public void setViews(long views) {
		_views = views;
	}

	private long _ratings;
	private float _ratingsScore;
	private long _views;

}