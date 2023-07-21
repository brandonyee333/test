/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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