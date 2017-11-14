<header class="doc-header on-screen-helper" id="banner" role="banner">
	<div class="banner-content">
		<#if layout.getFriendlyURL() == "/support">
			<#assign
				logo = "lesa_logo.png"
				logo_redirect_link = "/group/customer/support/-/support/ticket"
			/>
		<#else>
			<#assign
				logo = "liferay_logo.png"
				logo_redirect_link = "/"
			/>
		</#if>

		<a class="logo" href="${logo_redirect_link}">
			<img alt="Liferay" src="${images_folder}/custom/${logo}" />
		</a>

		<#if has_navigation>
			<div class="responsive-content-height" id="navigationContainer">
				<div class="class-toggle-off-click" id="mainSearch">
					<div id="mainSearchContent">
						<span class="class-toggle-off-click-content">
							<#assign
								portlet_id = "2_WAR_osbportlet"
								namespace = "_${portlet_id}_"

								portlet_url = portletURLFactory.create(request, portlet_id, themeDisplay.getPlid(), "ACTION_PHASE")

								void = portlet_url.setParameter("javax.portlet.action", "search")
								void = portlet_url.setWindowState("normal")
							/>

							<form action="${portlet_url}" class="doc-search" method="post" name="${namespace}themeFm" onSubmit="${namespace}themeSearch();">
								<#assign keywords = paramUtil.getString(request, "${namespace}keywords") />

								<input class="doc-search-input" name="${namespace}keywords" value="${htmlUtil.escapeAttribute(keywords)}" />
							</form>

							<script type="text/javascript">
								function ${namespace}themeSearch() {
									var keywords = document.${namespace}themeFm.${namespace}keywords.value;

									if (keywords != '') {
										document.${namespace}themeFm.submit();
									}
								}
							</script>
						</span>
					</div>
				</div>

				<#assign avatar_style = "" />

				<#if is_signed_in>
					<#assign avatar_style = "style='background-image: url(/image/user_male_portrait?img_id=${user.portraitId}&height=60&width=60);'" />
				</#if>

				<div class="class-toggle-off-click" id="mainMenu">
					<div id="userAvatar" ${avatar_style}></div>

					<ul class="class-toggle-off-click-content drop-down-menu responsive-content-height">
						<#if is_signed_in && show_my_places>
							<li class="my-places">
								<ul>
									<#assign
										organization_local_service = serviceLocator.findService("com.liferay.portal.kernel.service.OrganizationLocalService")
										liferay_organization_user = organization_local_service.hasUserOrganization(user.getUserId(), 21)
									/>

									<#if liferay_organization_user>
										<li class="control-panel">
											<a href="${control_panel_url}">${control_panel_text}</a>
										</li>
									</#if>

									<#assign
										my_places = user.getMySiteGroups()
										places_size = my_places?size
									/>

									<#list my_places as place>
										<#if place.hasPrivateLayouts() || place.hasPublicLayouts()>
											<#assign
												layout_private_name = place.getDescriptiveName()
												layout_public_name = place.getDescriptiveName()
											/>

											<#if place.hasPrivateLayouts() && place.hasPublicLayouts()>
												<#assign
													layout_private_name = layout_private_name + " " + languageUtil.get(locale, "private")
													layout_public_name = layout_public_name + " " + languageUtil.get(locale, "public")
												/>
											</#if>

											<#if place?counter == 1>
												<#assign user_profile_class = "user-profile" />

												<#if places_size gt 2>
													<#assign user_profile_class = user_profile_class + " first" />
												</#if>

												<li>
													<#assign account_url = place.getPathFriendlyURL(false, themeDisplay) + place.getFriendlyURL() />

													<a href="${account_url}">
														<@liferay.language key="account-home" />
													</a>
												</li>
												<li class="$user_profile_class">
													<#assign profile_url = account_url + '/profile' />

													<a href="$profile_url">
														<@liferay.language key="profile" />
													</a>
												</li>

											<#elseif places_size gt 2>
												<#assign site_css_class = "site" />

												<#if place.getGroupId() == group_id>
													<#assign site_css_class = site_css_class + "selected" />
												</#if>

												<#if place.hasPublicLayouts()>
													<#assign
														layout_name = layout_public_name
														site_css_class = "public" + site_css_class
														url = place.getPathFriendlyURL(false, themeDisplay) + place.getFriendlyURL()
													/>
												</#if>

												<#if place.hasPrivateLayouts()>
													<#assign
														layout_name = layout_private_name
														site_css_class = "private" + site_css_class
														url = place.getPathFriendlyURL(true, themeDisplay) + place.getFriendlyURL()
													/>
												</#if>

												<li class="${site_css_class}">
													<a href="${url}">${htmlUtil.escape(layout_name)}</a>
												</li>
											</#if>
										</#if>
									</#list>
								</ul>
							</li>
						</#if>

						<li class="class-toggle language-toggle">
							<a href="javascript:;">
								<@liferay.language key="language" /><span class="language-toggle-caret"></span>
							</a>

							<@liferay.languages />
						</li>

						<#if is_signed_in>
							<li class="sign-in-button" id="signOutBtn">
								<a href="${sign_out_url}?referer=${themeDisplay.getURLCurrent()}">${sign_out_text}</a>
							</li>
						<#else>
							<li id="registerBtn">
								<a href="${portalUtil.getCreateAccountURL(request, themeDisplay)}">
									<@liferay.language key="register" />
								</a>
							</li>
							<li class="sign-in-button" id="signInBtn">
								<a href="${sign_in_url}">${sign_in_text}</a>
							</li>
						</#if>
					</ul>
				</div>
			</div>
		</#if>
	</div>
</header>