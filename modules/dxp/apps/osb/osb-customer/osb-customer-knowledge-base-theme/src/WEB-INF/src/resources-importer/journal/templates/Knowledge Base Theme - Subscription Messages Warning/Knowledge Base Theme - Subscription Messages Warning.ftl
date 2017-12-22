<style type="text/css">
	.lfr-collapsed .lfr-panel-title:after {
		-moz-transform: rotate(-90deg);
		-ms-transform: rotate(-90deg);
		-o-transform: rotate(-90deg);
		-webkit-transform: rotate(-90deg);
		transform: rotate(-90deg);
	}

	.lfr-panel.lfr-collapsible .lfr-panel-titlebar {
		background: none;
		padding: 0 1em;
		position: relative;
	}

	.lfr-panel-container {
		border-width: 0;
	}

	.lfr-panel-container .lfr-panel.info {
		background-color: #A4A4A4;
		border-bottom: 1px solid #565656;
		color: #FFF;
	}

	.lfr-panel-container .lfr-panel.urgent {
		background-color: #FF5454;
		border-bottom: 1px solid #980000;
		color: #FFF;
	}

	.lfr-panel-container .lfr-panel.warning {
		background-color: #FFC;
		border-bottom: 1px solid #FC0;
	}

	.lfr-panel-content {
		padding: 0.5em 1.5em;
	}

	.lfr-panel-title {
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		width: calc(100% - 2.5em);
	}

	.lfr-panel-title:after {
		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg%20xmlns='http://www.w3.org/2000/svg'%20width='512'%20height='512'%20viewBox='0%200%20512%20512'%3E%3Cpath%20d='M256%20384a28.207%2028.207%200%200%200%2019.111-8.272l227.221-227.221c11.058-11.026%2011.058-28.941%200-39.999-11.026-11.058-28.94-11.058-39.999%200L256%20314.841%2049.667%20108.508c-11.059-11.058-28.973-11.058-39.999%200-11.059%2011.058-11.059%2028.972%200%2039.999l227.221%20227.221A28.194%2028.194%200%200%200%20256%20384z'%20fill='currentColor'/%3E%3C/svg%3E");
		background-size: contain;
		content: '';
		height: 8px;
		position: absolute;
		right: 1em;
		top: 50%;
		-moz-transform: rotate(0);
		-ms-transform: rotate(0);
		-o-transform: rotate(0);
		-webkit-transform: rotate(0);
		transform: rotate(0);
		width: 8px;
	}

	.lfr-panel-title .project-name {
		font-weight: bold;
	}

	.lfr-panel-title .project-content {
		padding-left: 5px;
	}

	.small-padding-vertical {
		padding: 0.5em 0;
	}

	.standard-padding-vertical {
		padding: 1em 0;
	}
</style>

<script type="text/javascript">
	AUI().ready(
		'liferay-panel',
		function(A) {
			new Liferay.Panel(
				{
					persistState: true
				}
			);
		}
	);
</script>

<#assign
	portlet_bean_locator = objectUtil("com.liferay.portal.kernel.bean.PortletBeanLocatorUtil")

	corp_project_message_local_service = portlet_bean_locator.locate("osb-portlet", "com.liferay.osb.service.CorpProjectMessageLocalService")

	service_context = objectUtil("com.liferay.portal.service.ServiceContextThreadLocal").getServiceContext()
	http_servlet_request = service_context.getRequest()

	user_id = permissionChecker.getUserId()!
/>

<#if user_id?has_content>
	<#assign corp_project_messages_map = corp_project_message_local_service.getCorpProjectMessagesMap(user_id, true, false)! />

	<h1 class="standard-padding-vertical">${heading.data}</h1>

	<#if corp_project_messages_map?has_content>
		<#list corp_project_messages_map.entrySet() as set>
			<#assign
				corp_project_messages = set.getValue()

				severity_level_label = corp_project_messages.get(0).getSeverityLevelLabel()
			/>

			<div class="lfr-panel-container standard-padding-vertical" id="${severity_level_label}">
				<h2 class="small-padding-vertical">
					<#assign pattern = severity_level_label + "-messages-x" />

					${languageUtil.format(locale, pattern, corp_project_messages.size())}
				</h2>

				<#list corp_project_messages as message>
					<#assign
						panel_id = severity_level_label + message.getCorpProjectMessageId()

						panel_state = sessionClicks.get(http_servlet_request, panel_id, "closed")

						panel_state_class = ""
					/>

					<#if stringUtil.equals(panel_state, "closed")>
						<#assign panel_state_class = "lfr-collapsed" />
					</#if>

					<div class="lfr-collapsible lfr-panel ${panel_state_class} ${severity_level_label} small-padding-vertical" id="${panel_id}">
						<#assign
							content = message.getContent()
							title = message.getTitle()

							message_entry = message.getAccountEntry()
							project_name = message_entry.getName()
						/>

						<div class="lfr-panel-titlebar">
							<div class="lfr-panel-title">
								<span class="project-name">${project_name}</span>:

								<span class="project-content">
									<#if title?has_content>
										${title}
									<#else>
										${content}
									</#if>
								</span>
							</div>
						</div>

						<div class="lfr-panel-content">
							${content}
						</div>
					</div>
				</#list>
			</div>
		</#list>
	<#else>
		<div class="portlet-msg-info">
			<@liferay.language key="there-are-no-messages-regarding-your-liferay-subscription-at-this-time" />
		</div>
	</#if>
</#if>