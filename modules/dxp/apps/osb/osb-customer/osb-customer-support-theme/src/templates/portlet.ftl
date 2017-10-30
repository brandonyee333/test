<#assign
	group = layout.getGroup()

	portlet_back_url = htmlUtil.escapeHREF(portletDisplay.getURLBack())
	portlet_id = htmlUtil.escapeAttribute(portletDisplay.getId())
	portlet_title = portletDisplay.getTitle()
/>

<section class="portlet" id="portlet_$portlet_id">
	<header class="portlet-topper">
		<h1 class="portlet-title">
			<span class="portlet-title-text">${portlet_title}</span>
		</h1>

		<menu class="portlet-topper-toolbar" id="portlet-topper-toolbar_${portlet_id}" type="toolbar">
			<#if portletDisplay.isShowBackIcon()>
				<a class="portlet-icon-back" href="${portlet_back_url}">
					<@liferay.language key="return-to-full-page" />
				</a>
			<#elseif !group.isUser() || permissionChecker.getUserId() == group.getClassPK() || permissionChecker.isGroupAdmin(layout.getGroupId())>
				theme.portletIconOptions()
			</#if>
		</menu>
	</header>

	<div class="portlet-content">
		${portletDisplay.writeContent(writer)}
	</div>
</section>