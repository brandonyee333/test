<div class="kb-banner" style="background-image: url('${banner_header.banner_image.getData()}')">
	<div class="clearfix kb-banner-overlay">
		<div class="content">
			<#if banner_header.getData() != "">
				<h1 class="header">${banner_header.getData()}</h1>
			</#if>

			<div class="banner-text">
				${banner_header.banner_content.getData()}
			</div>
		</div>
	</div>
</div>