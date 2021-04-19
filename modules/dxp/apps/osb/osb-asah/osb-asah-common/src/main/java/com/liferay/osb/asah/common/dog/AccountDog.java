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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class AccountDog {

	public Account addAccount(JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		Date date = new Date();

		Account account = new Account();

		account.setAccountPK(dataJSONObject.getString("id"));
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

	public Page<Distribution> getDistributionsPage(
		Long channelId, String fieldName, String fieldType, String filterString,
		Long individualSegmentId, int numberOfBins, int size, String[] sorts) {

		if (fieldType.equals("Number")) {
			size = numberOfBins;
		}

		PageRequest pageRequest = PageRequest.of(
			0, size, _getSort("count", sorts));

		List<Distribution> distribution =
			_accountRepository.getAccountDistributions(
				channelId, fieldName, fieldType, filterString,
				individualSegmentId, pageRequest);

		return PageableExecutionUtils.getPage(
			distribution, pageRequest, distribution::size);
	}

	public Account populateAccount(Account account, Long channelId) {
		if (account == null) {
			return null;
		}

		List<Field> fields = _fieldDog.getOwnerIdFields(
			"organization", account.getId());

		Stream<Field> stream = fields.stream();

		account.setFields(stream.collect(Collectors.toSet()));

		Segment segment = _segmentDog.fetchSegment(
			"Account: " + account.getId(), "INACTIVE");

		if (segment != null) {
			account.setActiveIndividualsCount(
				segment.getActiveIndividualCount());
			account.setActivitiesCount(segment.getActivitiesCount());
			account.setIndividualCount(segment.getIndividualCount());

			if ((channelId == null) && (segment.getChannelId() != null)) {
				Account.AccountActivityCount accountActivityCount =
					new Account.AccountActivityCount();

				accountActivityCount.setActivitiesCount(
					segment.getActivitiesCount());
				accountActivityCount.setChannelId(segment.getChannelId());

				account.setActivitiesCounts(
					Collections.singleton(accountActivityCount));

				Account.AccountIndividualCount accountIndividualCount =
					new Account.AccountIndividualCount();

				accountIndividualCount.setChannelId(segment.getChannelId());
				accountIndividualCount.setIndividualCount(
					segment.getIndividualCount());

				account.setIndividualCounts(
					Collections.singleton(accountIndividualCount));
			}
			else if ((channelId != null) &&
					 (channelId != segment.getChannelId())) {

				account.setActiveIndividualsCount(0L);
				account.setActivitiesCount(0L);
				account.setIndividualCount(0L);
			}
		}
		else if (channelId != null) {
			Set<Account.AccountActivityCount> activitiesCounts =
				account.getActivitiesCounts();

			for (Account.AccountActivityCount activitiesCount :
					activitiesCounts) {

				if (Objects.equals(channelId, activitiesCount.getChannelId())) {
					account.setActivitiesCount(
						activitiesCount.getActivitiesCount());

					break;
				}
			}

			Set<Account.AccountIndividualCount> individualCounts =
				account.getIndividualCounts();

			for (Account.AccountIndividualCount individualCount :
					individualCounts) {

				if (Objects.equals(channelId, individualCount.getChannelId())) {
					account.setIndividualCount(
						individualCount.getIndividualCount());

					break;
				}
			}

			account.setActivitiesCounts(null);
			account.setIndividualCounts(null);
		}

		return account;
	}

	public Page<Account> searchAccountsPage(
		Long channelId, String filterString, int page, int size,
		String[] sorts) {

		String[] fieldSorts = new String[0];
		String[] segmentSorts = new String[0];

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
			segmentSort = SortUtil.getSort(segmentSorts);
		}

		List<Account> accounts = _populateAccounts(
			_accountRepository.searchAccounts(
				channelId, filterString, pageRequest, segmentSort),
			channelId);

		return PageableExecutionUtils.getPage(
			accounts, pageRequest,
			() -> _accountRepository.countAccounts(filterString));
	}

	public Account updateAccount(
			Account account, JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		_fieldDog.updateFields(
			"organization", dataJSONObject, dataSource, account, "account",
			null, null);

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

	private Sort _getSort(String defaultFieldName, String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return Sort.by(Sort.Order.desc(defaultFieldName));
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (int i = 0; i < (sorts.length - 1); i = i + 2) {
			String sort = sorts[i];

			if (Objects.equals(sorts[i + 1], "asc")) {
				orders.add(Sort.Order.asc(sort));
			}
			else {
				orders.add(Sort.Order.desc(sort));
			}
		}

		return Sort.by(orders);
	}

	private List<Account> _populateAccounts(
		List<Account> accounts, Long channelId) {

		if (accounts.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<Account> stream = accounts.stream();

		Map<Long, Account> accountsById = stream.collect(
			Collectors.toMap(Account::getId, Function.identity()));

		accountsById.forEach(
			(accountId, account) -> populateAccount(account, channelId));

		return new ArrayList<>(accountsById.values());
	}

	private static final Log _log = LogFactory.getLog(AccountDog.class);

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private SegmentDog _segmentDog;

}