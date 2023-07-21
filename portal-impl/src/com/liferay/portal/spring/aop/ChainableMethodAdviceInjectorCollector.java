/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.aop;

import com.liferay.portal.kernel.spring.util.SpringFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.TypedStringValue;

/**
 * @author Shuyang Zhou
 */
public class ChainableMethodAdviceInjectorCollector {

	public static final String BEAN_NAME =
		ChainableMethodAdviceInjectorCollector.class.getName();

	public static void collect(
		ConfigurableListableBeanFactory configurableListableBeanFactory) {

		ChainableMethodAdviceInjectorCollector
			chainableMethodAdviceInjectorCollector =
				new ChainableMethodAdviceInjectorCollector();

		String[] beanNames =
			configurableListableBeanFactory.getBeanDefinitionNames();

		for (String beanName : beanNames) {
			if (!beanName.contains(SpringFactoryUtil.class.getName())) {
				continue;
			}

			BeanDefinition beanDefinition =
				configurableListableBeanFactory.getBeanDefinition(beanName);

			ConstructorArgumentValues constructorArgumentValues =
				beanDefinition.getConstructorArgumentValues();

			List<ConstructorArgumentValues.ValueHolder> valueHolders =
				constructorArgumentValues.getGenericArgumentValues();

			if (valueHolders.isEmpty()) {
				continue;
			}

			ConstructorArgumentValues.ValueHolder valueHolder =
				valueHolders.get(0);

			TypedStringValue typedStringValue =
				(TypedStringValue)valueHolder.getValue();

			String className = typedStringValue.getValue();

			if (className.contains(
					ChainableMethodAdviceInjector.class.getSimpleName())) {

				chainableMethodAdviceInjectorCollector.addBeanName(beanName);
			}
		}

		if (!chainableMethodAdviceInjectorCollector.hasBeanNames()) {
			configurableListableBeanFactory.registerSingleton(
				BEAN_NAME, chainableMethodAdviceInjectorCollector);
		}
	}

	public List<String> getBeanNames() {
		return _beanNames;
	}

	protected void addBeanName(String beanName) {
		_beanNames.add(beanName);
	}

	protected boolean hasBeanNames() {
		return _beanNames.isEmpty();
	}

	private ChainableMethodAdviceInjectorCollector() {
	}

	private final List<String> _beanNames = new ArrayList<>();

}