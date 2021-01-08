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

package com.liferay.osb.asah.stream.curator.bot.nanite.form;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseStreamNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;
import com.liferay.osb.asah.stream.curator.model.form.Form;
import com.liferay.osb.asah.stream.curator.model.form.FormField;
import com.liferay.osb.asah.stream.curator.model.form.FormPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class FormNanite extends BaseStreamNanite<Form> {

	@Override
	public String getCollectionName() {
		return "forms";
	}

	@Override
	protected BinaryOperator<Form> getBinaryOperator() {
		return (Form oldForm, Form newForm) -> {
			mergeModels(oldForm, newForm);

			NavigableSet<Date> submissionDates = newForm.getSubmissionDates();

			oldForm.addSubmissionDates(submissionDates);

			submissionDates = oldForm.getSubmissionDates();

			NavigableSet<Date> viewDates = newForm.getViewDates();

			oldForm.addViewDates(viewDates);

			viewDates = oldForm.getViewDates();

			oldForm.setSubmissions(submissionDates.size());
			oldForm.setSubmissionsTime(
				NaniteUtil.getSubmissionsTime(
					oldForm.getEventDate(), submissionDates, viewDates));
			oldForm.setViews(viewDates.size());

			oldForm.setAbandonments(
				Math.max(0, oldForm.getViews() - oldForm.getSubmissions()));

			_computeFormPages(oldForm, newForm);

			_equalizerForms(oldForm);

			return oldForm;
		};
	}

	@Override
	protected Predicate<Form> getFilterPredicate() {
		return form -> StringUtils.isNotBlank(form.getAssetId());
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected MessageSubscriber getMessageSubscriber() {
		return _messageSubscriber;
	}

	@Override
	protected Supplier<Form> getModelSupplier() {
		return Form::new;
	}

	@Override
	protected Function<Form, String> getPrimaryKeyGeneratorFunction() {
		return form -> digest(
			form.getAssetId(), form.getChannelId(), form.getEventDate(),
			form.getUserId(), form.getVariantId());
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, Form form) {

		if (Objects.equals(analyticsEvent.getEventId(), "formSubmitted")) {
			form.addSubmissionDate(analyticsEvent.getEventDate());
			form.setSubmissions(1);
		}
		else if (Objects.equals(analyticsEvent.getEventId(), "formViewed")) {
			form.addViewDate(analyticsEvent.getEventDate());
			form.setAbandonments(1);
		}
		else {
			form.setAbandonments(1);
			form.setTitle("");
		}

		form.setViews(1);

		_setFormFieldProperties(analyticsEvent, form);
		_setFormPageProperties(analyticsEvent, form);

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		form.setAssetId(eventProperties.get("formId"));
	}

	private Map<String, Long> _computeFormFieldAbandonments(
		LinkedList<FormEventComparator> formEventComparators) {

		Map<String, Long> formFieldAbandonments = new HashMap<>();

		while (!formEventComparators.isEmpty()) {
			FormEventComparator formEventComparator =
				formEventComparators.pollLast();

			if (Objects.equals(formEventComparator.getValue(), "submission")) {
				_pollFormEventComparators(formEventComparators);
			}
			else if (!Objects.equals(formEventComparator.getValue(), "view")) {
				String name = (String)formEventComparator.getValue();

				formFieldAbandonments.put(
					name, formFieldAbandonments.getOrDefault(name, 0L) + 1);

				_pollFormEventComparators(formEventComparators);
			}
		}

		return formFieldAbandonments;
	}

	private void _computeFormFieldAbandonments(
		List<FormField> formFields, NavigableSet<Date> submissionDates,
		NavigableSet<Date> viewDates) {

		LinkedList<FormEventComparator> formEventComparators =
			new LinkedList<>();

		for (FormField formField : formFields) {
			for (Date viewDate : formField.getViewDates()) {
				formEventComparators.add(
					new FormEventComparator(viewDate, formField.getName()));
			}
		}

		submissionDates.forEach(
			submissionDate -> formEventComparators.add(
				new FormEventComparator(submissionDate, "submission")));
		viewDates.forEach(
			viewDate -> formEventComparators.add(
				new FormEventComparator(viewDate, "view")));

		Collections.sort(formEventComparators);

		Map<String, Long> formFieldAbandonments = _computeFormFieldAbandonments(
			formEventComparators);

		for (FormField formField : formFields) {
			Long abandonments = formFieldAbandonments.getOrDefault(
				formField.getName(), 0L);

			formField.setAbandonments(abandonments);
		}
	}

	private void _computeFormFields(
		FormPage oldFormPage, FormPage newFormPage, Form form) {

		List<FormField> oldFormFields = oldFormPage.getFormFields();
		List<FormField> newFormFields = newFormPage.getFormFields();

		Map<String, FormField> map = Stream.concat(
			oldFormFields.stream(), newFormFields.stream()
		).collect(
			Collectors.toMap(
				FormField::getName, Function.identity(),
				_getFormFieldBinaryOperator())
		);

		List<FormField> formFields = new ArrayList<>(map.values());

		_computeFormFieldAbandonments(
			formFields, form.getSubmissionDates(), form.getViewDates());

		oldFormPage.setFormFields(formFields);
	}

	private Map<Integer, Long> _computeFormPageAbandonments(
		LinkedList<FormEventComparator> formEventComparators) {

		Map<Integer, Long> formPageAbandonments = new HashMap<>();

		while (!formEventComparators.isEmpty()) {
			FormEventComparator formEventComparator =
				formEventComparators.pollLast();

			if (Objects.equals(formEventComparator.getValue(), "submission")) {
				_pollFormEventComparators(formEventComparators);
			}
			else if (!Objects.equals(formEventComparator.getValue(), "view")) {
				Integer pageIndex = (Integer)formEventComparator.getValue();

				formPageAbandonments.put(
					pageIndex,
					formPageAbandonments.getOrDefault(pageIndex, 0L) + 1);

				_pollFormEventComparators(formEventComparators);
			}
		}

		return formPageAbandonments;
	}

	private void _computeFormPageAbandonments(
		NavigableSet<Date> submissionDates, NavigableSet<Date> viewDates,
		List<FormPage> formPages) {

		LinkedList<FormEventComparator> formEventComparators =
			new LinkedList<>();

		for (FormPage formPage : formPages) {
			for (Date viewDate : formPage.getViewDates()) {
				formEventComparators.add(
					new FormEventComparator(viewDate, formPage.getPageIndex()));
			}
		}

		submissionDates.forEach(
			submissionDate -> formEventComparators.add(
				new FormEventComparator(submissionDate, "submission")));
		viewDates.forEach(
			viewDate -> formEventComparators.add(
				new FormEventComparator(viewDate, "view")));

		Collections.sort(formEventComparators);

		Map<Integer, Long> formPageAbandonments = _computeFormPageAbandonments(
			formEventComparators);

		for (FormPage formPage : formPages) {
			Long abandonments = formPageAbandonments.getOrDefault(
				formPage.getPageIndex(), 0L);

			formPage.setAbandonments(abandonments);
		}
	}

	private void _computeFormPages(Form oldForm, Form newForm) {
		List<FormPage> oldFormPages = oldForm.getFormPages();
		List<FormPage> newFormPages = newForm.getFormPages();

		Map<Integer, FormPage> map = Stream.concat(
			oldFormPages.stream(), newFormPages.stream()
		).collect(
			Collectors.toMap(
				FormPage::getPageIndex, Function.identity(),
				_getFormPageBinaryOperator(oldForm))
		);

		List<FormPage> formPages = new ArrayList<>(map.values());

		_computeFormPageAbandonments(
			oldForm.getSubmissionDates(), oldForm.getViewDates(), formPages);

		oldForm.setFormPages(formPages);
	}

	private void _equalizerFormFields(FormPage formPage) {
		List<FormField> formFields = formPage.getFormFields();

		if (formFields.isEmpty()) {
			return;
		}

		long abandonments = 0;

		for (FormField formField : formFields) {
			abandonments += formField.getAbandonments();
		}

		if (abandonments > formPage.getAbandonments()) {
			formPage.setAbandonments(abandonments);
		}
	}

	private void _equalizerFormPages(Form form) {
		List<FormPage> formPages = form.getFormPages();

		if (formPages.isEmpty()) {
			form.addFormPage(new FormPage());
		}

		long abandonments = 0;
		long views = 0;

		for (FormPage formPage : formPages) {
			_equalizerFormFields(formPage);

			abandonments += formPage.getAbandonments();
			views += formPage.getViews();
		}

		FormPage formPage = formPages.get(0);

		if (views < form.getViews()) {
			formPage.setViews(formPage.getViews() + form.getViews() - views);
		}
		else if (form.getViews() == 0) {
			form.setViews(views);
		}

		if (abandonments < form.getAbandonments()) {
			formPage.setAbandonments(
				formPage.getAbandonments() + form.getAbandonments() -
					abandonments);
		}
		else if (abandonments > form.getAbandonments()) {
			form.setAbandonments(abandonments);
		}
	}

	private void _equalizerForms(Form form) {
		_equalizerFormPages(form);

		if (form.getViews() < form.getSubmissions()) {
			form.setAbandonments(0);
			form.setViews(form.getSubmissions());
		}
	}

	private BinaryOperator<FormField> _getFormFieldBinaryOperator() {
		return (FormField oldFormField, FormField newFormField) -> {
			oldFormField.addInteractionsDuration(
				newFormField.getInteractionsDuration());

			NavigableSet<Date> viewDates = newFormField.getViewDates();

			oldFormField.addViewDates(viewDates);

			if (StringUtils.isNotEmpty(oldFormField.getName())) {
				oldFormField.setName(oldFormField.getName());
			}

			viewDates = oldFormField.getViewDates();

			if (!viewDates.isEmpty()) {
				oldFormField.setInteractions(viewDates.size());
				oldFormField.setRefilled(viewDates.size() - 1);
			}

			return oldFormField;
		};
	}

	private BinaryOperator<FormPage> _getFormPageBinaryOperator(Form form) {
		return (FormPage oldFormPage, FormPage newFormPage) -> {
			NavigableSet<Date> viewDates = newFormPage.getViewDates();

			oldFormPage.addViewDates(viewDates);

			if (StringUtils.isNotEmpty(oldFormPage.getName())) {
				oldFormPage.setName(oldFormPage.getName());
			}

			viewDates = oldFormPage.getViewDates();

			oldFormPage.setViews(viewDates.size());

			_computeFormFields(oldFormPage, newFormPage, form);

			return oldFormPage;
		};
	}

	private int _getPageIndex(Map<String, String> eventProperties) {
		return Optional.ofNullable(
			eventProperties.get("page")
		).map(
			Integer::valueOf
		).orElse(
			0
		);
	}

	private void _pollFormEventComparators(
		LinkedList<FormEventComparator> formEventComparators) {

		while (!formEventComparators.isEmpty()) {
			FormEventComparator formEventComparator =
				formEventComparators.peekLast();

			if (Objects.equals(formEventComparator.getValue(), "submission") ||
				Objects.equals(formEventComparator.getValue(), "view")) {

				break;
			}

			formEventComparators.pollLast();
		}
	}

	private void _setFormFieldProperties(
		AnalyticsEvent analyticsEvent, Form form) {

		if (!Objects.equals(analyticsEvent.getEventId(), "fieldBlurred") &&
			!Objects.equals(analyticsEvent.getEventId(), "fieldFocused")) {

			return;
		}

		FormPage formPage = new FormPage();

		FormField formField = new FormField();

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (Objects.equals(analyticsEvent.getEventId(), "fieldBlurred")) {
			formField.addInteractionsDuration(
				(long)Double.parseDouble(eventProperties.get("focusDuration")));
		}
		else {
			formField.addViewsDate(analyticsEvent.getEventDate());
		}

		formField.setAbandonments(1);
		formField.setInteractions(1);
		formField.setName(eventProperties.get("fieldName"));

		formPage.addFormField(formField);
		formPage.setAbandonments(1);

		int pageIndex = _getPageIndex(eventProperties);

		formPage.setPageIndex(pageIndex);

		formPage.setViews(1);

		form.addFormPage(formPage);
	}

	private void _setFormPageProperties(
		AnalyticsEvent analyticsEvent, Form form) {

		if (Objects.equals(analyticsEvent.getEventId(), "formViewed")) {
			FormPage formPage = new FormPage();

			formPage.addViewsDate(analyticsEvent.getEventDate());
			formPage.setAbandonments(1);
			formPage.setPageIndex(0);
			formPage.setViews(1);

			form.addFormPage(formPage);
		}
		else if (Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {
			FormPage formPage = new FormPage();

			Map<String, String> eventProperties =
				analyticsEvent.getEventProperties();

			formPage.setName(eventProperties.get("title"));

			int pageIndex = _getPageIndex(eventProperties);

			formPage.setPageIndex(pageIndex);

			if (pageIndex > 0) {
				formPage.addViewsDate(analyticsEvent.getEventDate());
				formPage.setAbandonments(1);
				formPage.setViews(1);
			}

			form.addFormPage(formPage);
		}
	}

	private static final Log _log = LogFactory.getLog(FormNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_FORM)
	private MessageSubscriber _messageSubscriber;

	private static class FormEventComparator
		implements Comparable<FormEventComparator> {

		public FormEventComparator(Date date, Object value) {
			_date = date;
			_value = value;
		}

		@Override
		public int compareTo(FormEventComparator formEventComparator) {
			int value = _date.compareTo(formEventComparator.getDate());

			if (value == 0) {
				if (Objects.equals(_value, "submission") ||
					Objects.equals(_value, "view")) {

					return -1;
				}

				return 1;
			}

			return value;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof FormEventComparator)) {
				return false;
			}

			FormEventComparator formEventComparator = (FormEventComparator)obj;

			if (Objects.equals(_date, formEventComparator._date) &&
				Objects.equals(_value, formEventComparator._value)) {

				return true;
			}

			return false;
		}

		public Date getDate() {
			return _date;
		}

		public Object getValue() {
			return _value;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_date, _value);
		}

		private final Date _date;
		private final Object _value;

	}

}