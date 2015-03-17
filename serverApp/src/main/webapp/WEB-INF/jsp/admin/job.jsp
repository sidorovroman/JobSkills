<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
  .child-jobs {
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
    border: 3px solid #ffffff;
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
  .caption{
    font-size: 16px;
  }
</style>
<div id="job-container" xmlns="http://www.w3.org/1999/html">
  <div class="title">
    <img src="/resources/img/job-icon-0.png">

    <div class="info">

      <h1>${job.caption}</h1><br/>
      <span class="job-description"> <b>Описание:</b> ${job.description}</span>

    </div>
  </div>
  <div class="child-jobs">
    <ul class="child-spec"><span>Дочерние специализации</span>
      <c:forEach var="child" items="${job.children}" varStatus="loop">
        <li>
          <strong class="caption">${child.caption}</strong><br/>
          ${child.description}
        </li>
      </c:forEach>
    </ul>
  </div>
  <div>
  <h1 class="skills-title">Необходимые навыки <a ng-click="add()" class="btn" style="float:right">Добавить навык</a></h1>

    <div id="skills-list" xmlns="http://www.w3.org/1999/html">
      <table>
        <tbody>
        <c:forEach var="skill" items="${job.skills}" varStatus="loop">
          <tr ng-click="select(skill)">
            <td ng-click="select(skill)">
              <div ng-click="edit(skill)">
                <span>${skill.caption}</span>

                <div class="rating">
                  <div class="vote-up"></div>
                  <span class="rating-value"> 5</span>

                  <div class="vote-down"></div>
                </div>
                <i class="btn-edit"></i>
              </div>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>