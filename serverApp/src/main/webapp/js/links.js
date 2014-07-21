$(function () {
  var buttons = $('.link'), boxs = $('.link-block'), menuLinks = $('.menulink');
  menuLinks.click(function () {
    var j = menuLinks.index(this);
    buttons.eq(j).click();
  });
  buttons.click(function () {
    var i = buttons.index(this),
        box = boxs.eq(i);
    button = buttons.eq(i);
    buttons.not(button).removeClass('active');
    button.addClass('active');
    boxs.not(box).css({display: 'none'});
    box.css({display: 'block'});
  });
  var current_link = document.location.toString().substr(document.location.toString().indexOf('#'));
  if (current_link == 'l' || current_link == "link1") {
    $("a[href^=#link1]").click();
  } else {
    if (current_link != "/") {
      $("a[href^=" + current_link + "]").click();
    }
  }
});