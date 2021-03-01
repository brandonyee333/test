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

import com.liferay.osb.asah.common.dto.BlockedKeywordDTO;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.model.BlockedKeyword;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class BlockedKeywordDog extends BaseFaroInfoDog {

	public BlockedKeywordDTO addBlockedKeywords(Set<String> keywords) {
		Stream<String> stream = keywords.stream();

		keywords = stream.map(
			String::trim
		).map(
			String::toLowerCase
		).filter(
			StringUtils::isNotEmpty
		).collect(
			Collectors.toSet()
		);

		if (keywords.isEmpty()) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Empty keywords");
		}

		List<BlockedKeywordDTO> duplicateBlockedKeywordDTOs = ListUtil.map(
			getBlockedKeywords(keywords),
			blockedKeyword -> new BlockedKeywordDTO(blockedKeyword, true));

		List<BlockedKeyword> blockedKeywords = _createBlockedKeywords(
			duplicateBlockedKeywordDTOs, keywords);

		if (blockedKeywords.isEmpty()) {
			return new BlockedKeywordDTO(duplicateBlockedKeywordDTOs, false);
		}

		blockedKeywords = IterableUtils.toList(
			_blockedKeywordRepository.saveAll(blockedKeywords));

		return new BlockedKeywordDTO(
			duplicateBlockedKeywordDTOs, blockedKeywords);
	}

	public void deleteBlockedKeywords(List<Long> blockedKeywordIds) {
		_blockedKeywordRepository.deleteByIdIn(
			new HashSet<>(blockedKeywordIds));
	}

	public BlockedKeyword getBlockedKeyword(Long blockedKeywordId) {
		Optional<BlockedKeyword> blockedKeywordOptional =
			_blockedKeywordRepository.findById(blockedKeywordId);

		return blockedKeywordOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no blocked keyword with ID " + blockedKeywordId));
	}

	public List<BlockedKeyword> getBlockedKeywords() {
		return IterableUtils.toList(_blockedKeywordRepository.findAll());
	}

	public List<BlockedKeyword> getBlockedKeywords(Set<String> keywords) {
		return _blockedKeywordRepository.findByKeywordIn(keywords);
	}

	public Page<BlockedKeyword> getBlockedKeywords(
		String keyword, int page, int size, String[] sorts) {

		Sort sort = _getSort(sorts);

		if (keyword != null) {
			return PageableExecutionUtils.getPage(
				_blockedKeywordRepository.findByKeywordContainingIgnoreCase(
					keyword, PageRequest.of(page, size, sort)),
				PageRequest.of(page, size, sort),
				() ->
					_blockedKeywordRepository.
						countByKeywordContainingIgnoreCase(keyword));
		}

		return PageableExecutionUtils.getPage(
			_blockedKeywordRepository.findAll(PageRequest.of(page, size, sort)),
			PageRequest.of(page, size, sort), _blockedKeywordRepository::count);
	}

	private List<BlockedKeyword> _createBlockedKeywords(
		List<BlockedKeywordDTO> duplicateBlockedKeywordDTOs,
		Set<String> keywords) {

		Date date = new Date();

		Set<String> duplicateKeywords = SetUtil.map(
			duplicateBlockedKeywordDTOs, BlockedKeywordDTO::getKeyword);

		Stream<String> keywordsStream = keywords.stream();

		return keywordsStream.filter(
			keyword -> !duplicateKeywords.contains(keyword)
		).map(
			keyword -> new BlockedKeyword(date, keyword)
		).collect(
			Collectors.toList()
		);
	}

	private Sort _getSort(String[] sorts) {

		// TODO: make the frontend pass "keyword" without ".raw" and move this
		// method to ElasticsearchBlockedKeywordRepositoryImpl

		if (_isPostgreSQLEnabled && (sorts != null)) {
			for (int i = 0; i < sorts.length; i++) {
				sorts[i] = sorts[i].replace(".raw", "");
			}
		}

		List<Sort.Order> orders = SortBuilderUtil.getOrders(sorts);

		if (orders.isEmpty()) {
			if (_isPostgreSQLEnabled) {
				return Sort.by(Sort.Order.asc("keyword"));
			}

			return Sort.by(Sort.Order.asc("keyword.raw"));
		}

		return Sort.by(orders);
	}

	@Autowired
	private BlockedKeywordRepository _blockedKeywordRepository;

	@Value("${osb.asah.postgresql.enabled:false}")
	private boolean _isPostgreSQLEnabled;

}