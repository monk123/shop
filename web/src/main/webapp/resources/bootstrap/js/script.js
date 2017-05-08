(function ($) {

    $(document).ready(function () {

        $('.navigation-menu').each(function () {
            var height = $('.level1 li').height();
            $('.navigation-menu').height(height);
            
            $('.toLevel2').mouseover(function () {
                $('.level2').show();
                $('.icon').removeClass().addClass('icon fa fa-angle-up');
            });

            $('.toLevel2, .level2').mouseout(function () {
                $('.level2').hide();
                $('.icon').removeClass().addClass('icon fa fa-angle-down');
            });
            
        });

    })

})(jQuery);