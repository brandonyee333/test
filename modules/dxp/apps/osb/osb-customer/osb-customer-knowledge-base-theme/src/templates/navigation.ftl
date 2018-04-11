<div class="responsive-only site-navigation-menu-toggle toggle-menu" data-target-node="#navigationContainer" id="navigationToggle"></div>

<div class="responsive-content-height site-navigation" id="navigationContainer">
	<div class="nav">
		<#if stringUtil.equals(nav_options, "static")>
			<nav class="${nav_css_class}">
				<@print_navigation layout_friendly_url=main_nav_friendly_url />
			</nav>
		<#else>
			<nav class="${nav_css_class}">
				<ul class="nav-menu">
					<#list nav_items as nav_item>
						<#assign nav_item_css_class = "nav-item root-item" />

						<#if nav_item.isSelected()>
							<#assign nav_item_css_class = nav_item_css_class + " selected" />
						</#if>

						<#if nav_item?is_first>
							<#assign nav_item_css_class = nav_item_css_class + " first" />
						<#elseif nav_item?is_last>
							<#assign nav_item_css_class = nav_item_css_class + " last" />
						</#if>

						<li class="${nav_item_css_class}">
							<a href="${nav_item.getURL()}" onClick="_gaq.push(['_trackEvent', 'Navigation Clicks', 'Community Navigation', '${nav_item.getName()}']);" ${nav_item.getTarget()}>
								<span>${nav_item.getName()}</span>
							</a>
						</li>
					</#list>
				</ul>
			</nav>
		</#if>
	</div>

	<div class="nav-search">
		<div class="nav-search-content toggle-menu-content" id="navigationSearchContent">
			<form action="/documentation/search" class="nav-search-form" method="get" name="searchFm">
				<input name="p_p_id" type="hidden" value="1_WAR_osbknowledgebaseportlet" />
				<input name="p_p_state" type="hidden" value="normal" />

				<input class="lfr-search-input" name="_1_WAR_osbknowledgebaseportlet_keywords" size="30" title="<@liferay.language key="search" />" type="search" value="" />
			</form>
		</div>

		<div class="nav-search-toggle toggle-menu" data-target-node="#navigationSearchContent"></div>
	</div>

	<#assign main_menu_style = "" />

	<#if is_signed_in>
		<#assign main_menu_style = "style='background-image: url(" + user.getPortraitURL(theme_display) + "); background-size: cover;'" />
	</#if>

	<div class="nav-user toggle-menu" ${main_menu_style}>
		<ul class="drop-down-menu responsive-content-height toggle-menu-content">
			<#if is_signed_in>
				<li class="my-places">
					<ul>
						<li>
							<a href="https://www.liferay.com/web/${user.getScreenName()}"><@liferay.language key="account-home" /></a>
						</li>
						<li class="user-profile">
							<a href="https://www.liferay.com/web/${user.getScreenName()}/profile"><@liferay.language key="profile" /></a>
						</li>

						<#if customer || liferay_employee || partner>
							<li>
								<a href="https://www.liferay.com/group/customer"><@liferay.language key="customer-portal" /></a>
							</li>
						</#if>

						<#if liferay_employee || partner>
							<li>
								<a href="https://www.liferay.com/group/partner"><@liferay.language key="partner-portal" /></a>
							</li>
						</#if>

						<#if show_my_places>
							<#assign my_places = user.getMySiteGroups() />

							<#list my_places as place>
								<#if place.hasPublicLayouts() || place.hasPrivateLayouts()>
									<#assign
										layout_private_name = place.getDescriptiveName()
										layout_public_name = place.getDescriptiveName()

										url = place.getPathFriendlyURL(false, theme_display) + place.getFriendlyURL()
									/>

									<#if place.hasPrivateLayouts() && place.hasPublicLayouts()>
										<#assign
											layout_private_name = layout_private_name + languageUtil.get(locale, "private")
											layout_public_name = layout_public_name + languageUtil.get(locale, "public")
										/>
									</#if>

									<#if my_places?size gt 2>
										<#assign site_css_class = "site" />

										<#if place.getGroupId() == group_id>
											<#assign site_css_class = site_css_class + " selected" />
										</#if>

										<#if place.hasPublicLayouts()>
											<li class="public ${site_css_class}">
												<a href="${url}">${layout_public_name}</a>
											</li>
										</#if>

										<#if place.hasPrivateLayouts()>
											<#assign url = place.getPathFriendlyURL(true, theme_display) + place.getFriendlyURL() />

											<li class="private ${site_css_class}">
												<a href="${url}">${layout_private_name}</a>
											</li>
										</#if>
									</#if>
								</#if>
							</#list>
						</#if>
					</ul>
				</li>
			</#if>

			<li class="language-toggle parent-item toggle-menu">
				<a href="javascript:;">
					<@liferay.language key="language" />
				</a>

				<span class="children-marker"></span>

				<@liferay.languages />
			</li>

			<#if is_signed_in>
				<li class="sign-in-button" id="signOutBtn">
					<a href="${sign_out_url}?referer=${theme_display.getURLCurrent()}">${sign_out_text}</a>
				</li>
			<#else>
				<li class="sign-in-button" id="signInBtn">
					<a href="${sign_in_url}">${sign_in_text}</a>
				</li>
			</#if>
		</ul>
	</div>
</div>

<div class="documentation-search">
	<#assign redirect = paramUtil.getString(request, "_3_WAR_osbknowledgebaseportlet_redirect", "")! />

	<#if redirect?? && redirect?has_content>
		<a class="results" href="${redirect}">
			<svg class="icon-monospaced lexicon-icon lexicon-icon-angle-left" role="img" viewBox="0 0 512 512">
				<path class="lexicon-icon-outline" d="M114.106 254.607c0.22 6.936 2.972 13.811 8.272 19.11l227.222 227.221c11.026 11.058 28.94 11.058 39.999 0 11.058-11.026 11.058-28.94 0-39.999l-206.333-206.333c0 0 206.333-206.333 206.333-206.333 11.058-11.059 11.058-28.973 0-39.999-11.058-11.059-28.973-11.059-39.999 0l-227.221 227.221c-5.3 5.3-8.052 12.174-8.273 19.111z"></path>
			</svg>

			<span class="responsive-hidden">
				<@liferay.language key="results" />
			</span>
		</a>
	</#if>

	<form action="/documentation/search" class="doc-search-form" method="get" name="docSearchFm">
		<input name="p_p_id" type="hidden" value="1_WAR_osbknowledgebaseportlet" />
		<input name="p_p_state" type="hidden" value="normal" />

		<#assign keywords = paramUtil.getString(request, "_1_WAR_osbknowledgebaseportlet_keywords")! />
		<#assign keyword_search_value = "" />

		<#if keywords?? && keywords?has_content>
			<#assign keyword_search_value = htmlUtil.escapeAttribute(keywords) />
		</#if>

		<input class="keyword-search-input" id="_1_WAR_osbknowledgebaseportlet_keywords" name="_1_WAR_osbknowledgebaseportlet_keywords" placeholder="<@liferay.language key="search-documentation-by-keyword" />" type="search" value="${keyword_search_value}" />

		<svg class="doc-search-icon lexicon-icon lexicon-icon-search" viewBox="0 0 512 512">
			<path class="lexicon-icon-outline" d="M503.254 467.861l-133.645-133.645c27.671-35.13 44.344-79.327 44.344-127.415 0-113.784-92.578-206.362-206.362-206.362s-206.362 92.578-206.362 206.362 92.578 206.362 206.362 206.362c47.268 0 90.735-16.146 125.572-42.969l133.851 133.851c5.002 5.002 11.554 7.488 18.106 7.488s13.104-2.486 18.106-7.488c10.004-10.003 10.004-26.209 0.029-36.183zM52.446 206.801c0-85.558 69.616-155.173 155.173-155.173s155.174 69.616 155.174 155.173-69.616 155.173-155.173 155.173-155.173-69.616-155.173-155.173z"></path>
		</svg>
	</form>
</div>