<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
  .child-list {
    padding-left: 20px;
  }

  .child-spec {
    list-style-type: none;
    padding: 0px;
  }

  .child-spec span {
    font-weight: bold;
  }

  .child-spec li {
    padding-left: 10px;
    padding-top: 5px;
    height: 50px;
    border: 3px solid #A7A7A7;
    margin: 2px 0px 2px -2px;
    -webkit-box-shadow: 2px 2px 0px #A7A7A7;
    -moz-box-shadow: 2px 2px 0px #A7A7A7;
    -o-box-shadow: 2px 2px 0px #A7A7A7;
    box-shadow: 2px 2px 0px #A7A7A7;
    font-size: 11px;
    color: black;
    font-weight: normal;
    line-height: 1.333;
    border-radius: 2px;
    overflow: hidden;
    text-overflow: ellipsis;
    -o-text-overflow: ellipsis;
    white-space: nowrap;
    width: 100%;
  }

  .caption {
    font-size: 16px;
  }
</style>
<div id="job-container">
  <span style="float: right;">Закрыть</span>
  <div class="title">
    <img src="/resources/img/job-icon-0.png">

    <div class="info">

      <h1>${job.caption}</h1><br/>
      <span class="job-description"> <b>Описание:</b> ${job.description}</span>

    </div>
  </div>
  <div class="child-list">
    <ul class="child-spec"><span>Дочерние специализации</span>
      <c:forEach var="child" items="${job.children}" varStatus="loop">
        <li>
          <strong class="caption">${child.caption}</strong><br/>
          При клике сюда -  в этом же блоке откроется работа, оставив только название. Хз как сделать но будет круто${child.description}
        </li>
      </c:forEach>
    </ul>
  </div>
  <div class="child-list">
    <ul class="child-spec"><span>Необходимые навыки</span>
      <c:forEach var="skill" items="${job.skills}" varStatus="loop">
        <li>
          <strong class="caption">${skill.caption}</strong><br/>
            Навыки, как и последующие скилы, будут открываться в новом окно, типа записи вк.${skill.description}
        </li>
      </c:forEach>
    </ul>
  </div>
</div>