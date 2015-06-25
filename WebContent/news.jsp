<%@ page import="java.util.Queue" %>
<%@ page import="sas.data.storage.objects.News" %>
<%@ page import="sas.data.storage.objects.User" %>
<%@ page import="sas.database.NewsDatabase" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
<head>
   
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- <title>New Page</title> -->
    <meta name="description" content="Description" />
    <meta name="keywords" content="Keywords" />


	<%
		if (session == null ||  session.getAttribute("user") == null) 
			response.sendRedirect("index.jsp");
	%>

    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="script.js"></script>
	
	<style type="text/css">
		ul#news-feed li h6 a{
			font-size: 20px;
		}
		ul#news-feed li p{
			font-size: 15px;
		}
		ul#news-feed li {
		  padding: 5px;
		}
	</style>
</head>
<body>
<div id="art-page-background-glare">
    <div id="art-page-background-glare-image"> </div>
</div>
<div id="art-main">
<div class="art-nav">
	<div class="art-nav-l"></div>
	<div class="art-nav-r"></div>
<div class="art-nav-outer">
<div class="art-nav-wrapper">
<div class="art-nav-inner">

	<ul class="art-hmenu">
		<li>
			<a href="./homePage.jsp"><span class="l"></span><span class="r"></span><span class="t">Home</span></a>
		</li>
		<li>
			<a href="./ebook.jsp"><span class="l"></span><span class="r"></span><span class="t">Ebook</span></a>
		</li>	
		<li>
			<a href="./video.jsp"><span class="l"></span><span class="r"></span><span class="t">Videos</span></a>
		</li>
		<li>
			<a href="./job.jsp"><span class="l"></span><span class="r"></span><span class="t">Jobs</span></a>
		</li>
		<li>
			<a href="NewsService"><span class="l"></span><span class="r"></span><span class="t">News</span></a>
		</li>
		<li>
			<a href="DiscussionForumService"><span class="l"></span><span class="r"></span><span class="t">Forums</span></a>
		</li>
		<li>
			<a href="QAForumService"><span class="l"></span><span class="r"></span><span class="t">QA</span></a>
		</li>		
		<li>
			<a href="LogoutService"><span class="l"></span><span class="r"></span><span class="t">Logout</span></a>
		</li>	
	</ul>
	
</div>
</div>
</div>
</div>
<div class="cleared reset-box"></div>
<div class="art-header">
        <div class="art-header-clip">
        <div class="art-header-center">
            <div class="art-header-png"></div>
            <div class="art-header-jpeg"></div>
        </div>
        </div>
    <div class="art-header-wrapper">
    <div class="art-header-inner">
        <div class="art-headerobject"></div>
        <div class="art-logo">
                 <h1 class="art-logo-name"><a href="./index.jsp">Student Assistance</a></h1>
				 <h1 class="art-logo-name"><a href="./index.jsp">System</a></h1>
                        <!-- <h2 class="art-logo-text">Enter Site Slogan</h2>-->
                </div>
    </div>
    </div>
    </div>
    <div class="cleared reset-box"></div>
    <div class="art-sheet">
        <div class="art-sheet-tl"></div>
        <div class="art-sheet-tr"></div>
        <div class="art-sheet-bl"></div>
        <div class="art-sheet-br"></div>
        <div class="art-sheet-tc"></div>
        <div class="art-sheet-bc"></div>
        <div class="art-sheet-cl"></div>
        <div class="art-sheet-cr"></div>
        <div class="art-sheet-cc">
        
        	<%! User user; %>
			<%
	

 				user = (User) session.getAttribute("user"); 
  	
        
				if (user.getCategory() == 2 || user.getCategory() == 3) { 
			%>
				
					<p><a href="newsUpload.jsp">upload news</a></p>
					
			<% 	} %>
			
			<br/>
			<h5 style="text-align: center;">Most Recent News</h5> <br/>
			
			<ul id="news-feed">
				
				
		<%! Queue<News> queue; %>
		<%! News news; %>
		<%! @SuppressWarnings("unchecked") %>
		
		<% 
			queue = (Queue<News>) request.getAttribute("results");
			
			if (queue == null || queue.isEmpty()) {

		%>
		
				<li>
					<label>None Found</label>
					<br/><br/>
					<a href="homePage.jsp">Return to home</a>
				</li>
		
		<% } else {
			
				while (queue.isEmpty() == false)
				{
					
					news  = queue.poll();
		%>
		
					<li>
						<h6><a href="<%="NewsDetailService?newsid="+news.getNewsId() %>" target="_blank"><%=news.getNewsTitle() %></a></h6>
						<label><%="Category :" + news.getCategory() %></label> <br/>
						<label> <%="Posted  on:" + news.getDateTime() %></label><br/>
						<label> <%="Link:" %>  <a href="<%=news.getNewsLink() %>"><%=news.getNewsLink() %></a></label><br/>
					</li>
			
		<%		}  %>		
		<%	}  %>
			</ul>
		</div>
        <div class="art-sheet-body">
            <div class="art-content-layout">
                <div class="art-content-layout-row">
                    <div class="art-layout-cell art-content">

    
		<div class="cleared"></div>
    </div>
</div>

                      <div class="cleared"></div>
                    </div>
                </div>
            </div>
            <div class="cleared"></div>
            <div class="art-footer">
                <div class="art-footer-t"></div>
                <div class="art-footer-l"></div>
                <div class="art-footer-b"></div>
                <div class="art-footer-r"></div>
                <div class="art-footer-body">
                    <a href="#" class="art-rss-tag-icon" title="RSS"></a>
                            <div class="art-footer-text">
                                <p><a href="#">Link1</a> | <a href="#">Link2</a> | <a href="#">Link3</a></p><p>Copyright © 2011. All Rights Reserved.</p>
                                                            </div>
                    <div class="cleared"></div>
                </div>
            </div>
    		<div class="cleared"></div>
        </div>
    <div class="cleared"></div>
    <p class="art-page-footer"><a href="http://www.2createawebsite.com/artisteer">Website Template created with Artisteer</a>.</p>

</body>
</html>
