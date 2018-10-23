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

package com.liferay.osb.customer.zendesk.model;

/**
 * @author Amos Fong
 */
public class ZendeskTranslation {

	public ZendeskTranslation() {
	}

	public String getBody() {
		return _body;
	}

	public String getLocale() {
		return _locale;
	}

	public long getSourceId() {
		return _sourceId;
	}

	public String getSourceType() {
		return _sourceType;
	}

	public String getTitle() {
		return _title;
	}

	public long getZendeskTranslationId() {
		return _zendeskTranslationId;
	}

	public void setBody(String body) {
		_body = body;
	}

	public void setLocale(String locale) {
		_locale = locale;
	}

	public void setSourceId(long sourceId) {
		_sourceId = sourceId;
	}

	public void setSourceType(String sourceType) {
		_sourceType = sourceType;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setZendeskTranslationId(long zendeskTranslationId) {
		_zendeskTranslationId = zendeskTranslationId;
	}

	private String _body;
	private String _locale;
	private long _sourceId;
	private String _sourceType;
	private String _title;
	private long _zendeskTranslationId;

}