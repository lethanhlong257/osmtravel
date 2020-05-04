$('.slider-carousel').owlCarousel({
  loop: true,
  autoplay: true,
  autoplayTimeout: 5000,
  autoplayHoverPause: true,
  dots: true,
  nav: false,
  items: 1,
  autoHeight: true,
  autoplaySpeed: 1000,
});

$('.sale-carousel').owlCarousel({
  loop: true,
  autoplay: true,
  autoplayTimeout: 5000,
  autoplayHoverPause: true,
  dots: false,
  nav: true,
  autoplaySpeed: 1000,
  margin: 30,
  navText: [
    "<i class='mdi mdi-chevron-left'></i>",
    "<i class='mdi mdi-chevron-right'></i>" 
  ],
  responsive: {
    0: {
        items:1,
    },
    575: {
      items:2,
      margin: 15
    },
    768: {
      items:2
    },
    992: {
        items:3
    },
    1200: {
        items:3
    }
  }
});

var swiper = new Swiper('.swiper-suggest', {
  slidesPerView: 3,
  slidesPerColumn: 2,
  spaceBetween: 30,
  autoplay: {
    delay: 4000,
  },
  grabCursor: true,
  disableOnInteraction: true,
  speed: 1000,
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
  breakpoints: {
    1199: {
      slidesPerView: 3,
      slidesPerColumn: 2,
    },
    991: {
      slidesPerView: 2,
      slidesPerColumn: 2,
    },
    767: {
      slidesPerView: 2,
      slidesPerColumn: 2,
      spaceBetween: 15,
    },
    575: {
      slidesPerView: 1,
      slidesPerColumn: 2,
    },
  },
});

$(document).ready(() => {
  const pageUrl = window.location.href;
  const windowWidth = document.body.clientWidth;

  // GO TOP
  $(window).scroll(function () {
    if ($(this).scrollTop() > 10) {
      $('.go-top').fadeIn().css('transform','scale(1)');
      $('.menu').addClass('down')
    } else {
      $('.go-top').fadeOut().css('transform','scale(0)');

      $('.menu').removeClass('down')
    }
  });

  $('.go-top').click(() => {
    $("html, body").animate({
      scrollTop: 0
    }, 600);
    return false;
  });

  let iScrollPos = 0; 
  $(window).scroll(function () {
    if ($(this).scrollTop() > 400) { 
      $('.search').addClass('active');
      let iCurScrollPos = $(this).scrollTop();
      if (iCurScrollPos < iScrollPos) {
        $('.search').addClass('down');
      } else {
        $('.search').removeClass('down');
      }
      iScrollPos = iCurScrollPos;
    } else {
      $('.search').removeClass('active');
    }
  });

  $(".menu a").each( function () {
    if (pageUrl == (this.href)) {
      $(this).closest("a").addClass("active");
    }
  });
  
  $('.toggleMenu').click(() => {
    $('.nav').toggleClass('out');
    $('.overlay-menu').toggleClass('overlay-in');
  });

  $('.overlay-menu, .nav-close').click(function() {
    $('.overlay-menu').removeClass('overlay-in');
    $('.nav').removeClass('out');
  });

  $('.footer h4').click(function() {
    $(this).parent().find('ul').toggleClass('active');
  });

  $('.search-btn i').click(function() {
    $('.search').toggleClass('mobile');
    $(this).toggleClass('mdi-magnify mdi-close');
  });

  if (windowWidth < 1200) {
    $('.menu .nav-link').parent().find('ul').filter(function() {
      $(this).parent().find('.nav-link').removeAttr('href');
    });
  }

  $(function() {
    $(' .project-zone > .box-project').each( function() { $(this).hoverdir(); } );
  });

  for (let item = 0; item < 10; item++) {
    $('.project .box-project').eq(item).addClass(`box-project-${item+1}`)
  }

  $('.page-project a[href*="#"]').on('click', function (e) {
    e.preventDefault();

    $('html, body').animate({
      scrollTop: $($(this).attr('href')).offset().top + -150
    }, 300, 'linear');
  });

  $(window).scroll( function () {
    if ($(this).scrollTop() > 400) {
      $('.page-project .nav-tabs').addClass('active');
    } else {
      $('.page-project .nav-tabs').removeClass('active');
    }
  });
  
  $(window).scroll( function () {
    var scrollPosition = $(document).scrollTop();
    $('.page-project .nav-tabs li a').each(function () {
        var currentLink = $(this);
        var refElement = $(currentLink.attr("href"));
        if (refElement.position().top + -200 <= scrollPosition && refElement.position().top + refElement.height() > scrollPosition) {
            $('.page-project .nav-tabs li a.active').removeClass('active');
            $(this).addClass('active');
        }
        else{
            currentLink.removeClass("active");
        }
    });
  })

});
