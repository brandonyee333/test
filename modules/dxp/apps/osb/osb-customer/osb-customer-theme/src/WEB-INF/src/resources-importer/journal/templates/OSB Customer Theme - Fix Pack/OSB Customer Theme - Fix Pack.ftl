<#assign releaseDate_Data = getterUtil.getString(releaseDate.getData())>

<tr class="journal-article-row">
	<td class="lfr-released-column">
		<#if validator.isNotNull(releaseDate_Data)>
			<#assign releaseDate_DateObj = dateUtil.parseDate("yyyy-MM-dd", releaseDate_Data, locale)>

			${dateUtil.getDate(releaseDate_DateObj, "MMM d, yyyy", locale)}
		</#if>
	</td>
	<td class="lfr-details-column">
		<div class="fix-pack-details">
			<h3 class="fix-pack-version">
				${.vars['reserved-article-title'].getData()}
			</h3>

			<#-- Liferay version goes under here -->
			<span class="secondary-text-color">ADD JAVA CODE FOR LIFERAY VERSION HERE</span>
		</div>

		<#if validator.isNull(highlights)>
			<@liferay.language key="no-highlights-for-this-release" />
		<#else>
			<#if validator.isNotNull(highlights.keyHighlights.getData())>
				<div class="key-highlights">
					<h4 class="highlights-section-title">
						<@liferay.language key="key-highlights" />
					</h4>

					<p class="highlights-section">
						${highlights.keyHighlights.getData()}
					</p>
				</div>
			</#if>

			<#if validator.isNotNull(highlights.importantChanges.getData())>
				<div class="important-changes">
					<h4 class="highlights-section-title">
						<@liferay.language key="important-changes" />
					</h4>

					<p class="highlights-section">
						${highlights.importantChanges.getData()}
					</p>
				</div>
			</#if>

			<#if validator.isNotNull(highlights.knownIssues.getData())>
				<div class="known-issues">
					<h4 class="highlights-section-title">
						<@liferay.language key="known-issues" />
					</h4>

					<p class="highlights-section">
						${highlights.knownIssues.getData()}
					</p>
				</div>
			</#if>

			<#if validator.isNotNull(highlights.security.getData())>
				<div class="security">
					<h4 class="highlights-section-title">
						<@liferay.language key="security" />
					</h4>

					<p class="highlights-section">
						${highlights.security.getData()}
					</p>
				</div>
			</#if>
		</#if>
	</td>
</tr>