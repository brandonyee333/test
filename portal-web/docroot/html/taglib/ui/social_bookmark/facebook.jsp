<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String facebookDisplayStyle = "button_count";

if (displayStyle.equals("simple")) {
	facebookDisplayStyle = "standard";
}
else if (displayStyle.equals("vertical")) {
	facebookDisplayStyle = "box_count";
}
%>

<liferay-util:html-bottom outputKey="taglib_ui_social_bookmark_facebook">
	<% System.out.println("protocol: " + HttpUtil.getProtocol(request)); %>
	<!-- http -->
	<script src="<%= HttpUtil.getProtocol(request) %>://connect.facebook.net/<%= locale.getLanguage() %>_<%= locale.getCountry() %>/all.js#xfbml=1"></script>
</liferay-util:html-bottom>

<div id="fb-root"></div>

<script>
/*
  window._fbAsyncInit = function() {
    // init the FB JS SDK
    FB.init({
      appId      : 'YOUR_APP_ID',                        // App ID from the app dashboard
      channelUrl : '//WWW.YOUR_DOMAIN.COM/channel.html', // Channel file for x-domain comms
      status     : true,                                 // Check Facebook Login status
      xfbml      : true                                  // Look for social plugins on the page
    });

    // Additional initialization code such as adding Event Listeners goes here
  };

  // Load the SDK asynchronously
  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/all.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  */
</script>

<div class="fb-like"
	data-font=""
	data-height="<%= (facebookDisplayStyle.equals("standard") || facebookDisplayStyle.equals("button_count")) ? 20 : StringPool.BLANK %>"
	data-href="<%= url %>"
	data-layout="<%= facebookDisplayStyle %>"
	data-send="false"
	data-show_faces="true"
>
</div>

<div id="share">

	<iframe src="//www.facebook.com/plugins/like.php?href=<%= url %>&amp;send=false&amp;layout=standard&amp;width=450&amp;show_faces=false&amp;font=trebuchet+ms&amp;colorscheme=light&amp;action=like&amp;height=35" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:450px; height:35px;" allowTransparency="true"></iframe>
	<button class="btn" type="button">
		Facebook Share
		<i class="icon-share"></i>
	</button>
	<liferay-ui:icon-menu cssClass="icon-share" id="facebookMenuShare" message="facebook">
		<liferay-ui:icon
			message="like"
			src="http://www.facebook.com/plugins/like.php?href=<%= url %>&send=false&layout=standard&width=450&show_faces=false&font=trebuchet+ms&colorscheme=light&action=like&height=35"
			url='#'
		/>
		<liferay-ui:icon
			message="share"
			src=""
			url='#'
		/>
	</liferay-ui:icon-menu>

	<aui:nav>
		<aui:nav-item>
			<a rel="nofollow" href="http://www.facebook.com/share.php?u=<;url>" onclick="return fbs_click()" target="_blank">Share on Facebook</a>
		</aui:nav-item>
		<aui:nav-item>
			<a rel="nofollow" href="http://www.facebook.com/share.php?u=<;url>" onclick="return fbs_click()" target="_blank">Like on Facebook</a>
		</aui:nav-item>
	</aui:nav>
</div>

<script>
	function fbs_click() {
		window.open('http://www.facebook.com/sharer.php?u=' + encodeURIComponent("<%= url %>"),'sharer','toolbar=0,status=0,width=626,height=436');
		return false;
	}
</script>
<a rel="nofollow" href="http://www.facebook.com/share.php?u=<;url>" onclick="return fbs_click()" target="_blank">Share on Facebook</a>