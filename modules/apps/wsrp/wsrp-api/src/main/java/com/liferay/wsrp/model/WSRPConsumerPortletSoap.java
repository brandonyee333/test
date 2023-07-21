/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WSRPConsumerPortletSoap implements Serializable {

	public static WSRPConsumerPortletSoap toSoapModel(
		WSRPConsumerPortlet model) {

		WSRPConsumerPortletSoap soapModel = new WSRPConsumerPortletSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWsrpConsumerPortletId(model.getWsrpConsumerPortletId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWsrpConsumerId(model.getWsrpConsumerId());
		soapModel.setName(model.getName());
		soapModel.setPortletHandle(model.getPortletHandle());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static WSRPConsumerPortletSoap[] toSoapModels(
		WSRPConsumerPortlet[] models) {

		WSRPConsumerPortletSoap[] soapModels =
			new WSRPConsumerPortletSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WSRPConsumerPortletSoap[][] toSoapModels(
		WSRPConsumerPortlet[][] models) {

		WSRPConsumerPortletSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new WSRPConsumerPortletSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WSRPConsumerPortletSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WSRPConsumerPortletSoap[] toSoapModels(
		List<WSRPConsumerPortlet> models) {

		List<WSRPConsumerPortletSoap> soapModels =
			new ArrayList<WSRPConsumerPortletSoap>(models.size());

		for (WSRPConsumerPortlet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new WSRPConsumerPortletSoap[soapModels.size()]);
	}

	public WSRPConsumerPortletSoap() {
	}

	public long getPrimaryKey() {
		return _wsrpConsumerPortletId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpConsumerPortletId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortletId;
	}

	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortletId = wsrpConsumerPortletId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getWsrpConsumerId() {
		return _wsrpConsumerId;
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerId = wsrpConsumerId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPortletHandle() {
		return _portletHandle;
	}

	public void setPortletHandle(String portletHandle) {
		_portletHandle = portletHandle;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private String _uuid;
	private long _wsrpConsumerPortletId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _wsrpConsumerId;
	private String _name;
	private String _portletHandle;
	private Date _lastPublishDate;

}