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

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Ethan Bustad
 */
public class FilterElement {

	public Object getItems() {
		return _items;
	}

	public String getLabel() {
		return _label;
	}

	public Object getLabelHelper() {
		return _labelHelper;
	}

	public BaseModel<?> getModel(long classPK) throws Exception {
		if (Validator.isNull(_modelClassName)) {
			return null;
		}

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			_modelClassName);

		return alloyServiceInvoker.fetchModel(classPK);
	}

	public String getModelClassName() {
		return _modelClassName;
	}

	public String getName() {
		return _name;
	}

	public String getNullableLabel() {
		return _nullableLabel;
	}

	public String getOptionLabelProperty() {
		return _optionLabelProperty;
	}

	public String getOptionValueProperty() {
		return _optionValueProperty;
	}

	public String getSelectedLabel() {
		return _selectedLabel;
	}

	public Object getSelectedValue() {
		return _selectedValue;
	}

	public String getType() {
		return _type;
	}

	public boolean isNullable() {
		return _nullable;
	}

	public boolean isShowEmptyOption() {
		return _showEmptyOption;
	}

	protected FilterElement(
		Object items, String label, Object labelHelper, String modelClassName,
		String name, boolean nullable, String nullableLabel,
		String optionLabelProperty, String optionValueProperty,
		String selectedLabel, Object selectedValue, boolean showEmptyOption,
		String type) {

		_items = items;
		_label = label;
		_labelHelper = labelHelper;
		_modelClassName = modelClassName;
		_name = name;
		_nullable = nullable;
		_nullableLabel = nullableLabel;
		_optionLabelProperty = optionLabelProperty;
		_optionValueProperty = optionValueProperty;
		_selectedLabel = selectedLabel;
		_selectedValue = selectedValue;
		_showEmptyOption = showEmptyOption;
		_type = type;
	}

	private final Object _items;
	private final String _label;
	private final Object _labelHelper;
	private final String _modelClassName;
	private final String _name;
	private final boolean _nullable;
	private final String _nullableLabel;
	private final String _optionLabelProperty;
	private final String _optionValueProperty;
	private final String _selectedLabel;
	private final Object _selectedValue;
	private final boolean _showEmptyOption;
	private final String _type;

}