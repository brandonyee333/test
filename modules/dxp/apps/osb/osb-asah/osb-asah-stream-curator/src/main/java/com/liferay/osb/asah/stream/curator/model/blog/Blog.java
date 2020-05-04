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

package com.liferay.osb.asah.stream.curator.model.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;

import java.util.Date;

/**
 * @author Inácio Nery
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = Blog.class, include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "messageFormat", use = JsonTypeInfo.Id.NAME, visible = true
)
public class Blog extends BaseAssetModel {

	public void addClicks(long clicks) {
		_clicks += clicks;
	}

	public void addComments(long comments) {
		_comments += comments;
	}

	public void addViews(long views) {
		_views += views;
	}

	public long getClicks() {
		return _clicks;
	}

	public long getComments() {
		return _comments;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getFirstEventDate() {
		if (_firstEventDate == null) {
			return null;
		}

		return new Date(_firstEventDate.getTime());
	}

	public long getRatings() {
		return _ratings;
	}

	public float getRatingsScore() {
		return _ratingsScore;
	}

	public int getReadPercentile() {
		return _readPercentile;
	}

	public long getReadTime() {
		return _readTime;
	}

	public long getViews() {
		return _views;
	}

	public void setClicks(long clicks) {
		_clicks = clicks;
	}

	public void setComments(long comments) {
		_comments = comments;
	}

	public void setFirstEventDate(Date firstEventDate) {
		if (firstEventDate != null) {
			_firstEventDate = new Date(firstEventDate.getTime());
		}
	}

	public void setRatings(long ratings) {
		_ratings = ratings;
	}

	public void setRatingsScore(float ratingsScore) {
		_ratingsScore = ratingsScore;
	}

	public void setReadPercentile(int readPercentile) {
		_readPercentile = readPercentile;
	}

	public void setReadTime(long readTime) {
		_readTime = readTime;
	}

	public void setViews(long views) {
		_views = views;
	}

	private long _clicks;
	private long _comments;
	private Date _firstEventDate;
	private long _ratings;
	private float _ratingsScore;
	private int _readPercentile;
	private long _readTime;
	private long _views;

}