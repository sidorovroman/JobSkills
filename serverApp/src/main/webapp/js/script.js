$(function () {

//Меню прилипающее к верху
  var obj = $('#head_menu');
  var offset = obj.offset();
  var topOffset = offset.top;

  $(window).scroll(function () {

    var scrollTop = $(window).scrollTop();
    var basHeight = obj.width();

    if (scrollTop >= topOffset + 73) {
      obj.css({
        top: -73,
        position: 'fixed',
        width: basHeight + 'px'
      });
    }

    if (scrollTop < topOffset + 73) {
      obj.css({
        top: 0,
        position: 'absolute',
        width: 100 + '%'
      });
    }
  });

//Слайдер главной страницы
  var bas_temp = $('.slider .slide').eq(0);

  $(bas_temp).css('opacity', 1).addClass('active');
  $(bas_temp).children('div.info').css('z-index', 3);

  $('header.top div.kletki a:first-child').addClass('active');

  function next_slide() {

    $('.slider').find(':animated').stop(false, true);

    var bas_temp1 = $('.slider .active');
    var bas_temp2 = $(bas_temp1).next('.slide');

    if ($(bas_temp2).size() == 0) bas_temp2 = $('.slider .slide').eq(0);

    $(bas_temp1).animate({opacity: 0}, 1000);
    $(bas_temp2).animate({opacity: 1}, 1000);
    $(bas_temp1).removeClass('active');
    $(bas_temp2).addClass('active');
    $(bas_temp1).children('div.info').css('z-index', 2);
    $(bas_temp2).children('div.info').css('z-index', 3);
    $('header.top div.kletki a.active').removeClass('active');
    $('header.top div.kletki a.' + $(bas_temp2).attr('id')).addClass('active');
  }

  var bas_interval = setInterval(next_slide, 5000);

  $('header.top div.kletki a').click(function () {

    var bas_temp1 = $('.slider .active');
    var bas_temp2 = $('.slider #' + $(this).attr('class'));

    if (!$(this).hasClass('active')) {

      clearInterval(bas_interval);

      $('.slider').find(':animated').stop(false, true);

      $(bas_temp1).animate({opacity: 0}, 1000);
      $(bas_temp2).animate({opacity: 1}, 1000);
      $(bas_temp1).removeClass('active');
      $(bas_temp2).addClass('active');
      $(bas_temp1).children('div.info').css('z-index', 2);
      $(bas_temp2).children('div.info').css('z-index', 3);
      $('header.top div.kletki a.active').removeClass('active');
      $(this).addClass('active');

      bas_interval = setInterval(next_slide, 5000);
    }
  });

  //Поле поиска в шапке
  $('header.top input.search').focus(function () {

    $(this).addClass('active').animate({
      width: 90,
      marginLeft: 15
    }, 200);
  });

  $('header.top input.search').focusout(function () {

    $(this).removeClass('active').val('').animate({
      width: 21,
      marginLeft: 85
    }, 200);
  });

  $('header.top input.search').keypress(function (e) {
    if (e.which == 13) {
      var value = $(this).val();
      value = value.replace(/\s+/g, "+");
      window.location.href = $(this).attr('page') + "?q=" + value;
    }
  });

  //Серые плашки
  $('.pict_slider li').hover(function () {

    $(this).find(':animated').stop(false, true);

    $(this).children('span.bg').animate({
      width: 230,
      height: 230,
      left: -10,
      top: -10
    }, 300);

    $(this).find('span.text').css('display', 'block');

    $(this).find('img').animate({marginTop: -105}, 300);

  }, function () {

    $(this).find(':animated').stop(false, true);

    $(this).children('span.bg').animate({
      width: 210,
      height: 210,
      left: 0,
      top: 0
    }, 300);

    $(this).find('img').animate({marginTop: 15}, 300, function () {
      $(this).nextAll('span.text').hide();
    });
  });

  // открытие детализированной информации
  $('a.ys_open').click(function () {
    $(this).nextAll('div').first().slideToggle();
  });
});