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
 * @author Jenny Chen
 */
public class ZendeskAttachment {

	public ZendeskAttachment() {
	}

	public String getFileName() {
		return _fileName;
	}

	public long getZendeskAttachmentId() {
		return _zendeskAttachmentId;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public void setZendeskAttachmentId(long zendeskAttachmentId) {
		_zendeskAttachmentId = zendeskAttachmentId;
	}

	private String _fileName;
	private long _zendeskAttachmentId;

}