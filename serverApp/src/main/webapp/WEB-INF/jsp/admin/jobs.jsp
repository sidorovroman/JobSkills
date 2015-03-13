<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru" ng-app="App">
<head>
  <meta charset="utf-8">
  <title>Skill &middot; Youself</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/reset.css" />">--%>
  <link rel="stylesheet" type="text/css" href="<c:url value="../../../resources/styles/bootstrap.min.css" />">

  <%--<link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/bootswatch.less" />">--%>
  <%--<link rel="stylesheet" type="text/less" href="<c:url value="/resources/styles/variables.less" />">--%>
  <link rel="stylesheet" href="<c:url value="../../../resources/styles/bootstrap-multiselect.css" />">
  <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/datepicker.css" />">--%>
  <link rel="stylesheet" type="text/css" href="<c:url value="../../../resources/styles/main.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="../../../resources/styles/admin.css" />">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="<c:url value="../../../resources/js/admin.js" />"></script>
</head>
<body>
<style>
  body {
    background: #EFEFEF;
    overflow-y: scroll;
  }

  #logo {
    display: block;
    width: 71px;
    height: 71px;
  }

  #main_layout {
    max-width: 1440px;
    min-width: 1024px;
    min-height: 100%;
    height: 100%;
    text-align: left;
    margin: 0 auto 0;
    padding: 0;
    border: 0;
    position: relative;
  }

  #main_layout .inner {
    background: #fff;
    min-height: 100%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    padding-top: 12px;
    padding-left: 96px;
    padding-right: 24px;
    background: #fff;
  }

  #nav_panel {
    position: absolute;
    top: 0;
    bottom: 0px;
    width: 71px;
    z-index: 99991;
    background-color: #008e9e;
  }

  #main_content {
    float: left;
    padding-right: 320px;
    width: 100%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
  }

  #page_head {
    padding-top: 1px;
    padding-bottom: 6px;
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    font-style: normal;
    vertical-align: baseline;
    outline: 0;
  }

  .nav_row {
    display: block;
    color: white;
    font-size: 14px;
    font-weight: bold;
    padding: 10px 25px;
    text-decoration: none;
    position: relative;
    cursor: pointer;
  }

  .nav_row:hover {
    background: #07c507;
  }

  #nav_bar {
    background: #00BAD3;
  }

  #right_block {
    position: relative;
    padding-top: 7px;
    float: left;
    width: 300px;
    margin-left: -300px;
  }

  .statistic {
    margin-top: 15px;
    width: 100%;
    border: 1px solid #A7A7A7;
    border-radius: 3px;
    -webkit-box-shadow: 10px 10px 25px #A7A7A7;
    -moz-box-shadow: 10px 10px 25px #A7A7A7;
    -o-box-shadow: 10px 10px 25px #A7A7A7;
    box-shadow: 10px 10px 25px #A7A7A7;
    background: #00BAD3;
    padding-bottom: 10px;
  }

  .job-block {
    float: left;
    width: 280px;
    height: 300px;
    border: 1px solid #e9e9e9;
    border-radius: 3px;
    overflow: hidden;
    padding: 8px;
    margin: 10px;
    background: #fafafa;
    -webkit-box-shadow: 10px 10px 25px #A7A7A7;
    -moz-box-shadow: 10px 10px 25px #A7A7A7;
    -o-box-shadow: 10px 10px 25px #A7A7A7;
    box-shadow: 10px 10px 25px #A7A7A7;
    cursor: pointer;
  }

  .job-block:hover {
    background: #f5f5f5;
  }

  .job-block img {
    width: 70px;
    margin: 5px;
  }

  .job-block h1 {
    display: inline-block;
    margin-top: 13px;
    font-size: 24px;
    margin-bottom: 10px;
    color: #575050;
  }

  .job-block .job-info {
    float: left;
    margin: 10px;
  }
</style>
<div id="main_layout">
  <div id="nav_panel">
    <div id="logo" class="text-center" style="border: 1px solid green;">
      <h2 style="margin-top: 20px; color: white;">S &middot; Y</h2>
    </div>
    <div class="nav_button text-center" style="cursor: pointer;">
      <h3 style="color:white;">
        ===<br>===
      </h3>
    </div>
  </div>
  <div id="nav_bar"
       style="display: none; position: absolute; float: left; width: 300px; height: 100%; border: 1px solid black; margin-left: 70px;">
    <div style="text-align: center; margin-top: 60px;"><h2>Навигация</h2></div>
    <a class="nav_row" href="/admin/index">Пользователи</a>
    <a class="nav_row" href="/admin/news">Новости</a>
    <a class="nav_row" href="/admin/jobs">Работы</a>
  </div>
  <div class="inner">
    <div id="main_content">
      <div id="page_head" style="margin-top: 0px; text-align: center;">Работы</div>

      <div id="content_block">
        <c:forEach var="job" items="${jobs}" varStatus="loop">
          <div class="job-block">
            <div>
              <img src="/resources/img/job-icon-1.png">

              <h1>${job.caption}</h1>
            </div>
            <p class="job-info">${job.description}</p>
          </div>
        </c:forEach>
      </div>
    </div>
    <div id="right_block">
      <div class="statistic">
        <h3 class="text-center">Общая статистика</h3>

        <div class="nav_row"> Новостей: ${newsCount} </div>
        <div class="nav_row"> Пользователей: ${usersCount} </div>
        <div class="nav_row"> Работ: ${jobsCount} </div>
      </div>

      <div class="statistic">
        <h3 class="text-center">Cтатистика за 30 дней</h3>

        <div class="nav_row"> Новостей: ${newNewss} </div>
        <div class="nav_row"> Пользователей: ${newUsers} </div>
        <div class="nav_row"> Работ: ${newJobs} </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  $('.nav_button').click(function () {
    $('#nav_bar').animate({width: 'toggle'});
  });
  $('.job-block').click(function (event) {
    var targerElem = event.currentTarget;
    //$('.job-block').not(event.currentTarget).remove();
    $(event.currentTarget).animate({
      width: "100%",
      "margin-top": 0,
      "margin-left": 0
    }, 500 );
  });
</script>
</body>
</html>