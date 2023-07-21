/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.legacy.web.service.internal.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.Serializable;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class LegacyExpandoBridgeImpl implements ExpandoBridge {

	public LegacyExpandoBridgeImpl() {
	}

	@Override
	public void addAttribute(String name) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addAttribute(String name, boolean secure)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addAttribute(String name, int type) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addAttribute(String name, int type, boolean secure)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addAttribute(String name, int type, Serializable defaultValue)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addAttribute(
			String name, int type, Serializable defaultValue, boolean secure)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Serializable getAttribute(String name) {
		return _attributes.get(name);
	}

	@Override
	public Serializable getAttribute(String name, boolean secure) {
		return getAttribute(name);
	}

	@Override
	public Serializable getAttributeDefault(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(_attributes.keySet());
	}

	@Override
	public UnicodeProperties getAttributeProperties(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		return _attributes;
	}

	@Override
	public Map<String, Serializable> getAttributes(boolean secure) {
		return getAttributes();
	}

	@Override
	public Map<String, Serializable> getAttributes(Collection<String> names) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Serializable> getAttributes(
		Collection<String> names, boolean secure) {

		throw new UnsupportedOperationException();
	}

	@Override
	public int getAttributeType(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getClassName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getClassPK() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getCompanyId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasAttribute(String name) {
		return _attributes.containsKey(name);
	}

	@Override
	public boolean isIndexEnabled() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttribute(String name, Serializable value) {
		_attributes.put(name, value);
	}

	@Override
	public void setAttribute(String name, Serializable value, boolean secure) {
		setAttribute(name, value);
	}

	@Override
	public void setAttributeDefault(String name, Serializable defaultValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttributeProperties(
		String name, UnicodeProperties properties) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttributeProperties(
		String name, UnicodeProperties properties, boolean secure) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttributes(Map<String, Serializable> attributes) {
		_attributes = attributes;
	}

	@Override
	public void setAttributes(
		Map<String, Serializable> attributes, boolean secure) {

		setAttributes(attributes);
	}

	@Override
	public void setAttributes(ServiceContext serviceContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttributes(ServiceContext serviceContext, boolean secure) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setClassName(String className) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setClassPK(long classPK) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCompanyId(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setIndexEnabled(boolean indexEnabled) {
		throw new UnsupportedOperationException();
	}

	private Map<String, Serializable> _attributes = new HashMap<>();

}