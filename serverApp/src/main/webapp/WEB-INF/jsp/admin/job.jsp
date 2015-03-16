<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
  .child-jobs {
    padding-left: 20px;
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
    <ul>Дочерние специализации
      <c:forEach var="child" items="${job.children}" varStatus="loop">
        <li>${child.caption}</li>
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