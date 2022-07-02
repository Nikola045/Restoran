$(document).on("submit","#izmenaPodatakaZaKupca",function (event){
    event.preventDefault();

    var username = $("#username").val();
    var password = $("#password").val();
    var ime = $("#ime").val();
    var prezime = $("#prezime").val();

    var izmeniProfil = formToJson1(username,password,ime,prezime);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac/izmeni",
            dataType:"json",
            contentType:"application/json",
            data:izmeniProfil,
            success:function()
            {
                alert("Uspesno izmenjeni podaci");
            },
            error:function (data)
            {
                alert("Neuspesno!");
            }
        }
    );
});

function formToJson1(user,pass,im,prez)
{
    return JSON.stringify(
        {
            "username":user,
            "password":pass,
            "ime":im,
            "prezime":prez
        }
    );
}