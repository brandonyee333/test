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

package com.liferay.osb.testray.taglib.servlet.taglib;

import com.liferay.osb.testray.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Ethan Bustad
 */
public class FilterElementTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		FilterTag filterTag = (FilterTag)findAncestorWithClass(
			this, FilterTag.class);

		if (filterTag != null) {
			FilterElement filterElement = new FilterElement(
				_items, _label, _labelHelper, _modelClassName, _name, _nullable,
				_nullableLabel, _optionLabelProperty, _optionValueProperty,
				_selectedLabel, _selectedValue, _showEmptyOption, _type);

			filterTag.addFilterElement(filterElement);
		}

		return super.doStartTag();
	}

	public void setItems(Object items) {
		_items = items;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setLabelHelper(Object labelHelper) {
		_labelHelper = labelHelper;
	}

	public void setModelClassName(String modelClassName) {
		_modelClassName = modelClassName;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setNullable(boolean nullable) {
		_nullable = nullable;
	}

	public void setNullableLabel(String nullableLabel) {
		_nullableLabel = nullableLabel;
	}

	public void setOptionLabelProperty(String optionLabelProperty) {
		_optionLabelProperty = optionLabelProperty;
	}

	public void setOptionValueProperty(String optionValueProperty) {
		_optionValueProperty = optionValueProperty;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSelectedLabel(String selectedLabel) {
		_selectedLabel = selectedLabel;
	}

	public void setSelectedValue(Object selectedValue) {
		_selectedValue = selectedValue;
	}

	public void setShowEmptyOption(boolean showEmptyOption) {
		_showEmptyOption = showEmptyOption;
	}

	public void setType(String type) {
		_type = type;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_items = null;
		_label = null;
		_labelHelper = null;
		_modelClassName = null;
		_name = null;
		_nullable = false;
		_nullableLabel = null;
		_optionLabelProperty = "name";
		_optionValueProperty = "primaryKey";
		_selectedLabel = null;
		_selectedValue = null;
		_showEmptyOption = true;
		_type = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		if (_label == null) {
			_label = TextFormatter.format(_name, TextFormatter.K);
		}

		request.setAttribute("testray:filter-element:items", _items);
		request.setAttribute("testray:filter-element:label", _label);
		request.setAttribute(
			"testray:filter-element:labelHelper", _labelHelper);
		request.setAttribute("testray:filter-element:name", _name);
		request.setAttribute("testray:filter-element:nullable", _nullable);
		request.setAttribute(
			"testray:filter-element:nullableLabel", _nullableLabel);
		request.setAttribute(
			"testray:filter-element:optionLabelProperty", _optionLabelProperty);
		request.setAttribute(
			"testray:filter-element:optionValueProperty", _optionValueProperty);
		request.setAttribute(
			"testray:filter-element:selectedValue", _selectedValue);
		request.setAttribute(
			"testray:filter-element:showEmptyOption", _showEmptyOption);
		request.setAttribute("testray:filter-element:type", _type);
	}

	private static final String _END_PAGE = "/filter-element/end.jsp";

	private static final String _START_PAGE = "/filter-element/start.jsp";

	private Object _items;
	private String _label;
	private Object _labelHelper;
	private String _modelClassName;
	private String _name;
	private boolean _nullable;
	private String _nullableLabel;
	private String _optionLabelProperty = "name";
	private String _optionValueProperty = "primaryKey";
	private String _selectedLabel;
	private Object _selectedValue;
	private boolean _showEmptyOption = true;
	private String _type;

}