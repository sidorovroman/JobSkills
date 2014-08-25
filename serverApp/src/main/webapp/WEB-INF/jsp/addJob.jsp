<%@ page import="com.onedeveloperstudio.jobskills.common.dto.JobDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="utf-8">
  <title>Skill &middot; Youself</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <%--<script type="text/javascript">--%>
  <%--//<![CDATA[--%>
  <%--try{if (!window.CloudFlare) {var CloudFlare=[{verbose:0,p:0,byc:0,owlid:"cf",bag2:1,mirage2:0,oracle:0,paths:{cloudflare:"/cdn-cgi/nexp/dokv=88e434a982/"},atok:"05aa490aaef2a17aa0797d3fc055d391",petok:"13f87ac05e707adaf70919dbf9144629e388b6fe-1408287134-1800",zone:"bootstrap-ru.com",rocket:"a",apps:{"ga_key":{"ua":"UA-12784007-23","ga_bs":"2"}}}];document.write('<script type="text/javascript" src="//ajax.cloudflare.com/cdn-cgi/nexp/dokv=97fb4d042e/cloudflare.min.js"><'+'\/script>');}}catch(e){};--%>
  <%--//]]>--%>
  <%--</script>--%>
  <link rel="stylesheet" type="text/css" href="<c:url value="/js/resources/styles/bootstrap.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/js/resources/styles/bootstrap-responsive.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/js/resources/styles/main.css" />">

  <!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
  <script src="//ulogin.ru/js/ulogin.js"></script>
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="../assets/ico/favicon.png">
  <script type="text/javascript">
    /* <![CDATA[ */
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-12784007-23']);
    _gaq.push(['_trackPageview']);

    (function() {
      var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
      ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
      var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
    })();

    (function(b){(function(a){"__CF"in b&&"DJS"in b.__CF?b.__CF.DJS.push(a):"addEventListener"in b?b.addEventListener("load",a,!1):b.attachEvent("onload",a)})(function(){"FB"in b&&"Event"in FB&&"subscribe"in FB.Event&&(FB.Event.subscribe("edge.create",function(a){_gaq.push(["_trackSocial","facebook","like",a])}),FB.Event.subscribe("edge.remove",function(a){_gaq.push(["_trackSocial","facebook","unlike",a])}),FB.Event.subscribe("message.send",function(a){_gaq.push(["_trackSocial","facebook","send",a])}));"twttr"in b&&"events"in twttr&&"bind"in twttr.events&&twttr.events.bind("tweet",function(a){if(a){var b;if(a.target&&a.target.nodeName=="IFRAME")a:{if(a=a.target.src){a=a.split("#")[0].match(/[^?=&]+=([^&]*)?/g);b=0;for(var c;c=a[b];++b)if(c.indexOf("url")===0){b=unescape(c.split("=")[1]);break a}}b=void 0}_gaq.push(["_trackSocial","twitter","tweet",b])}})})})(window);
    /* ]]> */
  </script>
</head>
<body>
<h2>Add Job</h2>
<div id="wrap">
  <%!
    public String printAllJobs(List<JobDto> jobDtos, int countTab) {
      StringBuilder builder = new StringBuilder();
      for (JobDto dto : jobDtos) {
        builder.append("<option value='").append(dto.getId()).append("'>").append(getTabs(countTab)).append(dto.getCaption()).append("</option>\n");
        builder.append(printAllJobs(dto.getChildren(), countTab+=1));
        countTab-=1;
      }
      return builder.toString();
    }

    public String getTabs(int count){
      StringBuilder buider = new StringBuilder();
      for(int i = 0; i < count; i++){
        buider.append("&nbsp;&nbsp;&nbsp;&nbsp;");
      }
      return buider.toString();
    }
  %>
<form method="POST" action="/addJob">
  <label>Наименование
    <input name="caption" type="text"/>
  </label>
  <label>Родительский элемент
    <select name="parent">
      <% out.println(printAllJobs((List<JobDto>) request.getAttribute("jobs"),0));%>
    </select>
  </label>
  <input type="submit" value="Сохранить"/>
</form>
</div>

<div id="footer">
  <div class="container">
    <p class="muted licensed">Создано <a href="http://vk.com/id12579619">Юрой</a> и <a href="http://vk.com/id20890267">Романом</a>.</p>
  </div>
</div>


<script src="<c:url value="/js/resources/bower_components/angular/angular.js" />"></script>
<script src="<c:url value="/js/resources/bower_components/angular-route/angular-route.js" />"></script>
<script src="<c:url value="/js/resources/js/app.js" />"></script>
<script src="<c:url value="/js/resources/js/services.js" />"></script>
<script src="<c:url value="/js/resources/js/controllers.js" />"></script>
</body>
</html>
