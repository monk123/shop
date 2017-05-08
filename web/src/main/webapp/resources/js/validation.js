$(document).ready(function () {
    var pattern= /^[a-z0-9_-]+@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$/i;
    var email = $('#email');
    
    mail.blur(function () {
        if (email.val() !== '') {
            if (email.val().search(pattern) === 0) {
                $('#valid').text('Email введен правильно');
                $('#submit').attr('disabled', false);
            } else {
                $('#valid').text('Email введен неправильно');
                $('#submit').attr('disabled', true);
                email.addClass('ok')
            }
        } else {
            $('#valid').text('Поле e-mail не должно быть пустым');
            email.addClass('error');
        }
    });
});