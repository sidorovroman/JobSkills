$(function () {
  // удаление вакансии
  $(".yellow_btn.delete").click(function () {
    var deleted_id = $(this).attr("id");
    if (!deleted_id) {
      alert("Не выбраная вакансия для удаления");
    }
    var result = confirm("Вы уверены, что хотите удалить вакансию?");
    if (result) {
      $.ajax({
        type: "POST",
        url: requestContext + '/admin/deleteVacancy',
        data: {id: deleted_id},
        success: function () {
          $(".vakansi" + deleted_id).remove();
          alert("Вакансия успешно удалена");
        }
      });
    }
    return false;
  });

  // заполнение формы
  $(".yellow_btn.update").click(function () {
    var id = $(this).attr("id");
    var vakansi = $(".vakansi" + id);


    var job = $(vakansi).children('.ysloviya');

    var data = {
      id: id,
      name: $(vakansi).children("h3").children(".jobName").html(),
      nameEn: $(vakansi).children("h3").children(".jobNameEn").html(),
      office: $(vakansi).children("h3").children(".office").html(),
      info: $(vakansi).children(".jobInfo").html(),
      infoEn: $(vakansi).children(".jobInfoEn").html(),
      requirement: job.children(".jobRequirement").html(),
      requirementEn: job.children(".jobRequirementEn").html(),
      responsibility: job.children(".jobResponsibility").html(),
      responsibilityEn: job.children(".jobResponsibilityEn").html(),
      term: job.children(".jobTerm").html(),
      termEn: job.children(".jobTermEn").html()
    };
    showVacancyForm(data);
  });

  $(".yellow_btn.create").click(function () {
    showVacancyForm({});
  });

  function showVacancyForm(data) {
    var offices = {
      "Казань": "KAZAN",
      "Уфа": "UFA"
    };

    $("input[name='id']").val(data.id);
    $("input[name='name']").val(data.name);
    $("input[name='nameEn']").val(data.nameEn);

    var office = offices[data.office] || "KAZAN";
    $("select[name='office']").val(office);

    CKEDITOR.instances['info'].setData(data.info);
    CKEDITOR.instances['infoEn'].setData(data.infoEn);
    CKEDITOR.instances['requirement'].setData(data.requirement);
    CKEDITOR.instances['requirementEn'].setData(data.requirementEn);
    CKEDITOR.instances['responsibility'].setData(data.responsibility);
    CKEDITOR.instances['responsibilityEn'].setData(data.responsibilityEn);
    CKEDITOR.instances['term'].setData(data.term);
    CKEDITOR.instances['termEn'].setData(data.termEn);

    if (data.id) {
      $("#editVacancy h1 .title").html('Редактировать <span class="yellow"> вакансию</span>');
    } else {
      $("#editVacancy h1 .title").html('Добавить <span class="yellow"> вакансию</span>');
    }

//    $.fancybox.open({
//      href: '#editVacancy',
//      closeBtn: false
//    });
    $("#editVacancy").css("display", "block");
    $('body').animate({
      scrollTop: $("#editVacancy").offset().top
    }, 500);
  };

// сохранение вакансии
  $(".yellow_btn.send").click(function () {
    if ($("form")[0].checkValidity()) {
      var id = $("input[name='id']").val();
      $.ajax({
        type: "POST",
        url: requestContext + '/admin/editVacancy',
        data: {
          id: id,
          name: $("input[name='name']").val(),
          nameEn: $("input[name='nameEn']").val(),
          office: $("select[name='office']").val(),
          info: CKEDITOR.instances['info'].getData(),
          infoEn: CKEDITOR.instances['infoEn'].getData(),
          requirement: CKEDITOR.instances['responsibility'].getData(),
          requirementEn: CKEDITOR.instances['responsibilityEn'].getData(),
          responsibility: CKEDITOR.instances['requirement'].getData(),
          responsibilityEn: CKEDITOR.instances['requirementEn'].getData(),
          term: CKEDITOR.instances['term'].getData(),
          termEn: CKEDITOR.instances['termEn'].getData()
        },
        success: function () {
          if (!id) {
            alert('Вакансия успешно добавлена');
          } else {
            alert('Вакансия успешно обновлена');
          }
          // перезагрузка страницы
          window.location = window.location.href;
        }, error: function (data) {
          console.log(data);
          alert("Произошла ошибка при сохранении");
        }
      });
    } else {
      alert("Не все поля заполнены");
    }
    return false;
  });
});