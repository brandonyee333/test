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

package com.liferay.osb.asah.stream.curator.model.document.library;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;

/**
 * @author Marcellus Tavares
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = DocumentLibrary.class,
	include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "messageFormat",
	use = JsonTypeInfo.Id.NAME, visible = true
)
public class DocumentLibrary extends BaseAssetModel {

	public void addComments(long comments) {
		_comments += comments;
	}

	public void addDownloads(long downloads) {
		_downloads += downloads;
	}

	public void addPreviews(long previews) {
		_previews += previews;
	}

	public long getComments() {
		return _comments;
	}

	public long getDownloads() {
		return _downloads;
	}

	public long getPreviews() {
		return _previews;
	}

	public long getRatings() {
		return _ratings;
	}

	public float getRatingsScore() {
		return _ratingsScore;
	}

	public void setComments(long comments) {
		_comments = comments;
	}

	public void setDownloads(long downloads) {
		_downloads = downloads;
	}

	public void setPreviews(long previews) {
		_previews = previews;
	}

	public void setRatings(long ratings) {
		_ratings = ratings;
	}

	public void setRatingsScore(float ratingsScore) {
		_ratingsScore = ratingsScore;
	}

	private long _comments;
	private long _downloads;
	private long _previews;
	private long _ratings;
	private float _ratingsScore;

}