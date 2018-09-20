${product.data}

<br />

<div class="links">
	<#if link.siblings?has_content>
		<#list link.siblings as curLink>
			<a href="${curLink.linkUrl.data}">${curLink.data}</a>

			<#if curLink?has_next>
				|
			</#if>
		</#list>
	</#if>
</div>

${alertMessage.data}

<br />

${additionalNotes.data}

<br />

<select>
	<#list downloadGroup.siblings as curDownloadGroup>
		<optgroup label="${curDownloadGroup.data}">
			<#list curDownloadGroup.download.siblings as curDownload>
				<option value="${curDownload.downloadUrl.data}">${curDownload.data}</option>
			</#list>
		</optgroup>
	</#list>
</select>

${requiredAgreement.data}