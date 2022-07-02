$(document).on("submit","#prijavaAdmina",function (event){
    event.preventDefault();

    var username = $("#username").val();
    var password = $("#password").val();

    var noviKorisnik = formToJson1(username,password);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/admin/prijava",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(username + " se uspesno prijvaio");
                window.location.href="menadzer.html";
            },
            error:function (data)
            {
                alert("Greska prilikom registracije!");
            }
        }
    );
});

function formToJson1(user,pass)
{
    return JSON.stringify(
        {
            "username":user,
            "password":pass,
        }
    );
}