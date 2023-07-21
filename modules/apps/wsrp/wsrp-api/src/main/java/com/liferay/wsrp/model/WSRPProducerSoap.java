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
public class WSRPProducerSoap implements Serializable {

	public static WSRPProducerSoap toSoapModel(WSRPProducer model) {
		WSRPProducerSoap soapModel = new WSRPProducerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWsrpProducerId(model.getWsrpProducerId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setVersion(model.getVersion());
		soapModel.setPortletIds(model.getPortletIds());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static WSRPProducerSoap[] toSoapModels(WSRPProducer[] models) {
		WSRPProducerSoap[] soapModels = new WSRPProducerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WSRPProducerSoap[][] toSoapModels(WSRPProducer[][] models) {
		WSRPProducerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WSRPProducerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WSRPProducerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WSRPProducerSoap[] toSoapModels(List<WSRPProducer> models) {
		List<WSRPProducerSoap> soapModels = new ArrayList<WSRPProducerSoap>(
			models.size());

		for (WSRPProducer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPProducerSoap[soapModels.size()]);
	}

	public WSRPProducerSoap() {
	}

	public long getPrimaryKey() {
		return _wsrpProducerId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpProducerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getWsrpProducerId() {
		return _wsrpProducerId;
	}

	public void setWsrpProducerId(long wsrpProducerId) {
		_wsrpProducerId = wsrpProducerId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getPortletIds() {
		return _portletIds;
	}

	public void setPortletIds(String portletIds) {
		_portletIds = portletIds;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private String _uuid;
	private long _wsrpProducerId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _version;
	private String _portletIds;
	private Date _lastPublishDate;

}