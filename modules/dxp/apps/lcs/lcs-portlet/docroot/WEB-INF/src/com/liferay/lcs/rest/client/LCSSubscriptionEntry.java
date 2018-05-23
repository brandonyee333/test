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

package com.liferay.lcs.rest.client;

import java.util.Date;

/**
 * @author Igor Beslic
 */
public class LCSSubscriptionEntry {

	public int getInstanceSize() {
		return _instanceSize;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public long getLcsSubscriptionEntryId() {
		return _lcsSubscriptionEntryId;
	}

	public String getPlatform() {
		return _platform;
	}

	public int getPlatformVersion() {
		return _platformVersion;
	}

	public int getProcessorCoresAllowed() {
		return _processorCoresAllowed;
	}

	public String getProduct() {
		return _product;
	}

	public int getProductVersion() {
		return _productVersion;
	}

	public int getServersAllowed() {
		return _serversAllowed;
	}

	public int getServersUsed() {
		return _serversUsed;
	}

	public String getType() {
		return _type;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public void setInstanceSize(int instanceSize) {
		_instanceSize = instanceSize;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
	}

	public void setLcsSubscriptionEntryId(long lcsSubscriptionEntryId) {
		_lcsSubscriptionEntryId = lcsSubscriptionEntryId;
	}

	public void setPlatform(String platform) {
		_platform = platform;
	}

	public void setPlatformVersion(int platformVersion) {
		_platformVersion = platformVersion;
	}

	public void setProcessorCoresAllowed(int processorCoresAllowed) {
		_processorCoresAllowed = processorCoresAllowed;
	}

	public void setProduct(String product) {
		_product = product;
	}

	public void setProductVersion(int productVersion) {
		_productVersion = productVersion;
	}

	public void setServersAllowed(int serversAllowed) {
		_serversAllowed = serversAllowed;
	}

	public void setServersUsed(int serversUsed) {
		_serversUsed = serversUsed;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public void setSupportEndDate(Date supportEndDate) {
		_supportEndDate = supportEndDate;
	}

	public void setSupportStartDate(Date supportStartDate) {
		_supportStartDate = supportStartDate;
	}

	public void setType(String type) {
		_type = type;
	}

	private Date _endDate;
	private int _instanceSize;
	private long _lcsProjectId;
	private long _lcsSubscriptionEntryId;
	private String _platform;
	private int _platformVersion;
	private int _processorCoresAllowed;
	private String _product;
	private int _productVersion;
	private int _serversAllowed;
	private int _serversUsed;
	private Date _startDate;
	private Date _supportEndDate;
	private Date _supportStartDate;
	private String _type;

}