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

package com.liferay.osb.asah.common.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.helper.AccountsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class AccountDog {

	public Account addAccount(Account account) {
		account = _accountRepository.save(account);

		return populateAccount(account, null);
	}

	public Account addAccount(
			String accountPK, JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		Date date = new Date();

		Account account = new Account();

		account.setAccountPK(accountPK);
		account.setCreateDate(date);
		account.setDataSourceId(dataSource.getId());
		account.setModifiedDate(date);

		account = _accountRepository.save(account);

		List<Field> fields = _fieldDog.addFields(
			"organization", dataJSONObject, dataSource, account.getId(),
			"account");

		account.setFields(new HashSet<>(fields));

		_accountRepository.save(account);

		_segmentDog.addSegment(
			0L, date,
			"((dataSourceAccountPKs/accountPKs eq '" + account.getAccountPK() +
				"'))",
			date, "Account: " + account.getId(), "PROJECT", "DYNAMIC",
			"INACTIVE");

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			).put(
				"dateModified", DateUtil.toUTCString(date)
			).put(
				"removeFilter", "contains(filter, 'accounts.filterByCount(')"
			));

		return populateAccount(account, null);
	}

	public void deleteAccount(Account account) {
		Segment segment = _segmentDog.fetchSegment(
			"Account: " + account.getId(), "INACTIVE");

		if (segment != null) {
			Long segmentId = segment.getId();

			if (segmentId != null) {
				_segmentDog.deleteSegment(segmentId);
			}
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Unable to get individual segment associated with account " +
					account.getId());
		}

		_accountRepository.delete(account);

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter", "contains(filter, 'accounts.filterByCount(')"
			).put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			));
	}

	public Account fetchByAccountPKAndDataSourceId(
		String accountPK, Long dataSourceId) {

		Optional<Account> accountOptional =
			_accountRepository.findByAccountPKAndDataSourceId(
				accountPK, dataSourceId);

		return populateAccount(accountOptional.orElse(null), null);
	}

	public Account getAccount(Long accountId, Long channelId) {
		Optional<Account> accountOptional = _accountRepository.findById(
			accountId);

		Account account = accountOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no account with ID " + accountId));

		return populateAccount(account, channelId);
	}

	public Account getAccount(String accountPK) {
		Optional<Account> accountOptional = _accountRepository.findByAccountPK(
			accountPK);

		Account account = accountOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no account with primary key " + accountPK));

		return populateAccount(account, null);
	}

	public String getAccountName(String accountPK) {
		Account account = getAccount(accountPK);

		Set<Field> fields = account.getFields();

		Stream<Field> stream = fields.stream();

		Field nameField = stream.filter(
			field -> Objects.equals(field.getName(), "accountName")
		).findFirst(
		).orElse(
			null
		);

		if (nameField == null) {
			return null;
		}

		return String.valueOf(nameField.getValue());
	}

	public Map<Long, JSONObject> getAccountNamesJSONObjects(
		List<Individual> individuals) {

		Map<Long, JSONObject> accountNamesJSONObjects = new HashMap<>();

		for (Individual individual : individuals) {
			Set<Individual.DataSourceAccountPK> dataSourceAccountPKs =
				individual.getDataSourceAccountPKs();

			JSONArray accountNamesJSONArray = new JSONArray();

			for (Individual.DataSourceAccountPK dataSourceAccountPK :
					dataSourceAccountPKs) {

				Set<String> accountPKs = dataSourceAccountPK.getAccountPKs();

				for (String accountPK : accountPKs) {
					accountNamesJSONArray.put(getAccountName(accountPK));
				}
			}

			accountNamesJSONObjects.put(
				individual.getId(),
				JSONUtil.put("account-names", accountNamesJSONArray));
		}

		return accountNamesJSONObjects;
	}

	public Page<Account> getAccountPage(
		Date fromCreateDate, Long accountId, int size, Sort sort,
		Date toCreateDate) {

		return PageableExecutionUtils.getPage(
			_accountRepository.findByCreateDateBetweenAndIdAfter(
				fromCreateDate, toCreateDate, accountId,
				PageRequest.of(0, size, sort)),
			PageRequest.of(0, size, sort),
			() -> _accountRepository.countByCreateDateBetweenAndIdAfter(
				fromCreateDate, toCreateDate, accountId));
	}

	public List<Account> getAccounts(int size, int start) {
		return searchAccounts(null, start / size, size);
	}

	public Map<Long, JSONObject> getAccountsJSONObjects(
		List<Individual> individuals) {

		Map<Long, JSONObject> accountsJSONObjects = new HashMap<>();

		for (Individual individual : individuals) {
			Set<Individual.DataSourceAccountPK> dataSourceAccountPKs =
				individual.getDataSourceAccountPKs();

			JSONArray accountsJSONArray = new JSONArray();

			for (Individual.DataSourceAccountPK dataSourceAccountPK :
					dataSourceAccountPKs) {

				Set<String> accountPKs = dataSourceAccountPK.getAccountPKs();

				for (String accountPK : accountPKs) {
					accountsJSONArray.put(
						_objectMapper.convertValue(
							getAccount(accountPK), JSONObject.class));
				}
			}

			accountsJSONObjects.put(
				individual.getId(),
				JSONUtil.put("accounts", accountsJSONArray));
		}

		return accountsJSONObjects;
	}

	public Page<Distribution> getDistributionPage(
		@Nullable Long channelId, String fieldName, String fieldType,
		@Nullable String filterString, @Nullable Long individualSegmentId,
		int numberOfBins, int size, @Nullable String[] sorts) {

		if (fieldType.equals("Number")) {
			size = numberOfBins;
		}

		PageRequest pageRequest = PageRequest.of(
			0, size,
			SortUtil.getSort(Sort.by(Sort.Order.desc("count")), sorts));

		List<Distribution> distributions =
			_accountRepository.getAccountDistributions(
				_individualDog.getAccountPKs(channelId, individualSegmentId),
				fieldName, fieldType,
				new FilterHelper(
					_defaultFilterStringConverterHelper, filterString,
					_accountsFilterStringConverterHelper),
				pageRequest);

		return PageableExecutionUtils.getPage(
			distributions, pageRequest, distributions::size);
	}

	public Page<Transformation> getTransformationPage(
		String apply, @Nullable Long channelId, @Nullable String filterString,
		int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_accountRepository.getAccountTransformations(
				apply, channelId,
				new FilterHelper(
					_defaultFilterStringConverterHelper, filterString,
					_accountsFilterStringConverterHelper),
				pageRequest);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public Account populateAccount(Account account, Long channelId) {
		if (account == null) {
			return null;
		}

		List<Field> fields = _fieldDog.getOwnerIdFields(
			"organization", account.getId());

		account.setFields(new HashSet<>(fields));

		Segment segment = _segmentDog.fetchSegment(
			"Account: " + account.getId(), "INACTIVE");

		if (segment != null) {
			account.setActiveIndividualsCount(
				segment.getActiveIndividualsCount());
			account.setActivitiesCount(segment.getActivitiesCount());
			account.setIndividualsCount(segment.getIndividualsCount());

			List<Individual.ActivitiesCount> individualActivitiesCounts =
				_individualDog.getActivitiesCounts(
					BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
					segment.getId());

			if (!individualActivitiesCounts.isEmpty()) {
				Set<Account.AccountActivityCount> activitiesCounts =
					new HashSet<>();

				for (Individual.ActivitiesCount individualActivitiesCount :
						individualActivitiesCounts) {

					activitiesCounts.add(
						new Account.AccountActivityCount(
							individualActivitiesCount.getActivitiesCount(),
							individualActivitiesCount.getChannelId()));
				}

				account.setActivitiesCounts(activitiesCounts);
			}

			Map<Long, Long> channelIndividualCounts =
				_individualDog.getIndividualCounts(
					BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
					segment.getId());

			if (!channelIndividualCounts.isEmpty()) {
				Set<Account.AccountIndividualCount> individualsCounts =
					new HashSet<>();

				for (Map.Entry<Long, Long> entry :
						channelIndividualCounts.entrySet()) {

					individualsCounts.add(
						new Account.AccountIndividualCount(
							entry.getKey(), entry.getValue()));
				}

				account.setIndividualsCounts(individualsCounts);
			}
		}

		if (channelId != null) {
			Set<Account.AccountActivityCount> activitiesCounts =
				account.getActivitiesCounts();

			if (activitiesCounts.isEmpty()) {
				account.setActivitiesCount(0L);
			}

			for (Account.AccountActivityCount activitiesCount :
					activitiesCounts) {

				if (Objects.equals(channelId, activitiesCount.getChannelId())) {
					account.setActivitiesCount(
						activitiesCount.getActivitiesCount());

					break;
				}

				account.setActivitiesCount(0L);
			}

			Set<Account.AccountIndividualCount> individualsCounts =
				account.getIndividualsCounts();

			if (individualsCounts.isEmpty()) {
				account.setIndividualsCount(0L);
			}

			for (Account.AccountIndividualCount individualsCount :
					individualsCounts) {

				if (Objects.equals(
						channelId, individualsCount.getChannelId())) {

					account.setIndividualsCount(
						individualsCount.getIndividualsCount());

					break;
				}

				account.setIndividualsCount(0L);
			}

			account.setActivitiesCounts(null);
			account.setIndividualsCounts(null);
		}

		return account;
	}

	public Page<Account> searchAccountPage(
		@Nullable Long channelId, @Nullable String filterString, int page,
		@Nullable Long segmentId, int size, @Nullable String[] sorts) {

		String[] fieldSorts = {};
		String[] segmentSorts = {};

		if (ArrayUtils.isNotEmpty(sorts)) {
			for (int i = 0; i < (sorts.length - 1); i = i + 2) {
				String sort = sorts[i];

				if (sort.equalsIgnoreCase("activitiesCount") ||
					sort.equalsIgnoreCase("individualCount")) {

					segmentSorts = ArrayUtils.addAll(
						segmentSorts, sort, sorts[i + 1]);
				}
				else if (sort.startsWith("organization/")) {
					fieldSorts = ArrayUtils.addAll(
						fieldSorts, sort, sorts[i + 1]);
				}
			}
		}

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(fieldSorts));

		Sort segmentSort = null;

		if (ArrayUtils.isNotEmpty(segmentSorts)) {
			segmentSort = SortUtil.getSort(null, segmentSorts);
		}

		FilterHelper filterHelper = new FilterHelper(
			_defaultFilterStringConverterHelper, filterString,
			_accountsFilterStringConverterHelper);

		List<Account> accounts = _populateAccounts(
			_accountRepository.searchAccounts(
				_getAccountPKs(segmentId), channelId, filterHelper, pageRequest,
				segmentSort),
			channelId);

		return PageableExecutionUtils.getPage(
			accounts, pageRequest,
			() -> _accountRepository.countAccounts(
				_getAccountPKs(segmentId), filterHelper));
	}

	public List<Account> searchAccounts(
		String filterString, int page, int size) {

		return ListUtil.map(
			_accountRepository.searchAccounts(
				new FilterHelper(
					_defaultFilterStringConverterHelper, filterString,
					_accountsFilterStringConverterHelper),
				PageRequest.of(page, size)),
			account -> populateAccount(account, null));
	}

	public Account updateAccount(Account account) {
		return populateAccount(_accountRepository.save(account), null);
	}

	public Account updateAccount(
			Account account, JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		_fieldDog.updateFields(
			"organization", dataJSONObject, dataSource,
			Collections.singletonList(account.getId()), "account");

		List<Field> fields =
			_fieldRepository.findByContextAndOwnerIdAndOwnerType(
				"organization", account.getId(), "account");

		Stream<Field> stream = fields.stream();

		account.setFields(stream.collect(Collectors.toSet()));

		account.setModifiedDate(new Date());

		account = _accountRepository.save(account);

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			).put(
				"dateModified", DateUtil.toUTCString(account.getModifiedDate())
			).put(
				"removeFilter",
				"contains(filter, 'accounts.filter(') or contains(filter, " +
					"'accounts.filterByCount(')"
			));

		return populateAccount(account, null);
	}

	private Set<String> _getAccountPKs(Long segmentId) {
		if (segmentId == null) {
			return null;
		}

		Set<String> accountPKs = new HashSet<>();

		List<Object> individualDataSourceAccountPKs = JSONUtil.toObjectList(
			_elasticsearchInvoker.get(
				"individuals",
				QueryBuilders.termQuery("individualSegmentIds", segmentId)),
			"dataSourceAccountPKs");

		for (Object jsonArrayObject : individualDataSourceAccountPKs) {
			JSONArray jsonArray = (JSONArray)jsonArrayObject;

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				accountPKs.addAll(
					JSONUtil.toStringList(
						jsonObject.getJSONArray("accountPKs")));
			}
		}

		return accountPKs;
	}

	private List<Account> _populateAccounts(
		List<Account> accounts, Long channelId) {

		if (accounts.isEmpty()) {
			return Collections.emptyList();
		}

		for (Account account : accounts) {
			populateAccount(account, channelId);
		}

		return accounts;
	}

	private static final Log _log = LogFactory.getLog(AccountDog.class);

	@Autowired
	private AccountRepository _accountRepository;

	private final AccountsFilterStringConverterHelper
		_accountsFilterStringConverterHelper =
			new AccountsFilterStringConverterHelper();

	@Autowired
	private AsahTaskDog _asahTaskDog;

	private final DefaultFilterStringConverterHelper
		_defaultFilterStringConverterHelper =
			new DefaultFilterStringConverterHelper();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}