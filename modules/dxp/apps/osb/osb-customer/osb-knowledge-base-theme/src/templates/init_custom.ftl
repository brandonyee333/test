<#assign
	group_local_service = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")

	layout_local_service = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")

	portal_permission_util = serviceLocator.findService("com.liferay.portal.kernel.service.permission.PortalPermission")

	ancestor_layout = layout_local_service.getLayout(layout.getAncestorPlid())
/>

<#if !show_add_content??>
	<#assign css_class = stringUtil.replace(css_class, "controls-visible", "controls-hidden") />
</#if>

<#assign side_nav_visible = getterUtil.getBoolean(sessionClicks.get(request, "_2_WAR_osbknowledgebaseportlet_side_nav_toggle_value", "true"), false) />

<#if !side_nav_visible??>
	<#assign css_class = css_class + " side-nav-hidden" />
</#if>

<#assign
	guest_group = group_local_service.fetchGroup(company_id, "Guest")
	guest_group_id = guest_group.getGroupId()
/>

<#assign footer_nav_friendly_url = "/footer-navigation" />
<#assign main_nav_friendly_url = "/main-navigation" />
<#assign mobile_footer_nav_friendly_url = "/mobile-footer-navigation" />

<#assign nav_options = theme_display.getThemeSetting("nav-options") />

<#if theme.getSetting("open-graph-image") != "">
	<#assign open_graph_image = theme.getSetting("open-graph-image") />
<#else>
	<#assign open_graph_image = images_folder + "/custom/open_graph_image.png" />
</#if>

<#if theme.getSetting("open-graph-title") != "">
	<#assign open_graph_title = theme.getSetting("open-graph-title") />
<#else>
	<#assign open_graph_title = the_title + " - " + company_name />
</#if>

<#if theme.getSetting("open-graph-url") != "">
	<#assign open_graph_url = theme.getSetting("open-graph-url") />
<#else>
	<#assign open_graph_url = htmlUtil.escape(portal.getCurrentCompleteURL(request)) />
</#if>

<#assign can_view_control_panel = false />

<#if is_signed_in && portal_permission_util.contains(themeDisplay.getPermissionChecker(), "VIEW_CONTROL_PANEL")>
	<#assign can_view_control_panel = true />
<#else>
	<#assign css_class = stringUtil.replace(css_class, "has-control-menu", "") />
</#if>

<#assign
	userGroupIds = user.getUserGroupIds()

	customer = false
/>

<#if arrayUtil.contains(userGroupIds, 84013)>
	<#assign customer = true />
</#if>

<#assign liferayEmployee = false />

<#if arrayUtil.contains(userGroupIds, 80690)>
	<#assign liferayEmployee = true />
</#if>

<#assign partner = false />

<#if arrayUtil.contains(userGroupIds, 84039)>
	<#assign partner = true />
</#if>

<#macro print_navigation layout_friendly_url>
	<#assign
		layoutGroup = layout.getGroup()
		navigation_layout = layout_local_service.fetchLayoutByFriendlyURL(guest_group_id, false, layout_friendly_url)!""
	/>

	<#if getterUtil.getBoolean(theme_display.getThemeSetting("custom-navigation"))>
		<#assign navigation_layout = layout_local_service.fetchLayoutByFriendlyURL(group_id, layout.isPrivateLayout(), layout_friendly_url)!"" />
	</#if>

	<#if navigation_layout?? && validator.isNotNull(navigation_layout)>
		<#assign root_layout_name = navigation_layout.getName(locale) />

		<ul class="nav-menu">
			<#list navigation_layout.getChildren() as cur_layout>
				<#assign
					cur_layout_name = cur_layout.getName(locale)

					cur_layout_nav_item_selected_css_class = ""

					nav_index = cur_layout?index + 1
				/>

				<#if getterUtil.getInteger(cur_layout.getTypeSettingsProperty("linkToLayoutId")) == layout.getLayoutId()>
					<#assign cur_layout_nav_item_selected_css_class = "selected" />
				</#if>

				<#if validator.isNull(cur_layout_nav_item_selected_css_class) && cur_layout.hasChildren()>
					<#list cur_layout.getChildren() as child_layout>
						<#if getterUtil.getInteger(child_layout.getTypeSettingsProperty("linkToLayoutId")) == layout.getLayoutId()>
							<#assign cur_layout_nav_item_selected_css_class = "selected" />

							<#break>
						</#if>
					</#list>
				</#if>

				<#if cur_layout.hasChildren()>
					<li class="nav-item nav-item-${nav_index} parent-item root-item toggle-menu">
						<a class="${cur_layout_nav_item_selected_css_class}" href="javascript:;" ${cur_layout.getTarget()}>${cur_layout_name}</a>

						<span class="children-marker responsive-only"></span>
				<#else>
					<li class="nav-item nav-item-${nav_index} root-item">
						<#assign cur_layout_href = cur_layout.getFriendlyURL() />

						<#if cur_layout.isTypeURL()>
							<#assign cur_layout_href = cur_layout.getRegularURL(request) />
						</#if>

						<a class="${cur_layout_nav_item_selected_css_class}" href="${cur_layout_href}" onClick="_gaq.push(['_trackEvent', 'Navigation Clicks', '${root_layout_name}', '${cur_layout_name}']);" ${cur_layout.getTarget()}>${cur_layout_name}</a>
				</#if>

				<#if cur_layout.hasChildren()>
					<ul class="child-menu drop-down-menu toggle-menu-content">
						<#list cur_layout.getChildren() as child_layout>
							<#assign
								child_layout_name = child_layout.getName(locale)

								child_nav_index = child_layout?index + 1
							/>

							<#if child_layout.hasChildren()>
								<li class="child-item nav-item nav-item-${child_nav_index} parent-item" id="childItem${child_nav_index}">

								<span class="children-marker responsive-only toggle-menu" data-target-node="#childItem${child_nav_index}"></span>
							<#else>
								<li class="child-item nav-item" id="childItem${child_nav_index}">
							</#if>

							<#assign child_layout_href = child_layout.getFriendlyURL() />

							<#if child_layout.isTypeURL()>
								<#assign child_layout_href = child_layout.getRegularURL(request) />
							</#if>

							<#assign child_layout_nav_item_selected_css_class = "" />

							<#if getterUtil.getInteger(child_layout.getTypeSettingsProperty("linkToLayoutId")) == layout.getLayoutId()>
								<#assign child_layout_nav_item_selected_css_class = "selected" />
							</#if>

							<a class="${child_layout_nav_item_selected_css_class}" href="${child_layout_href}" onClick="_gaq.push(['_trackEvent', 'Navigation Clicks', '${root_layout_name}', '${cur_layout_name} - ${child_layout_name}']);" ${child_layout.getTarget()}>${child_layout_name}</a>

							<#if child_layout.hasChildren()>
								<ul class="grand-child-menu">
									<#list child_layout.getChildren() as grand_child_layout>
										<#assign
											grand_child_layout_name = grand_child_layout.getName(locale)
											grand_child_layout_css_class = "grand-child-item nav-item"

											grand_child_layout_href = child_layout.getFriendlyURL()
										/>

										<#if grand_child_layout.isTypeURL()>
											<#assign grand_child_layout_href = grand_child_layout.getRegularURL(request) />
										</#if>

										<#if grand_child_layout?is_last>
											<#assign grand_child_layout_css_class = grand_child_layout_css_class + " last" />
										</#if>

										<li class="${grand_child_layout_css_class}">
											<a href="${grand_child_layout_href}" onClick="_gaq.push(['_trackEvent', 'Navigation Clicks', '${root_layout_name}', '${child_layout_name} - ${grand_child_layout_name}']);" ${grand_child_layout.getTarget()}>${grand_child_layout_name}</a>
										</li>
									</#list>
								</ul>
							</#if>

							</li>
						</#list>
					</ul>
				</#if>

				</li>
			</#list>
		</ul>
	</#if>
</#macro>