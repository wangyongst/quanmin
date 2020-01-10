$(function () {
    $("#alert").hide();

    $("#loginButton").click(function () {
        $.post("/shiro/login",
            {
                username: $("#username").val(),
                password: $("#password").val()
            },
            function (result) {
                if (result.status == 1) {
                    window.location.href = "/";
                } else {
                    $("#alert").text(result.message);
                    $("#alert").show();
                }
            });
    });
});

