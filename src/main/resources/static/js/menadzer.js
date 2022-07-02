$(document).on("click","#menadzerProfil",function (){

    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/menadzer/profil",
        dataType:"json",
        success:function (data){

            var row = "<tr>";
            row+="<td>" + data['username'] + "</td>";
            row+="<td>" + data['password'] + "</td>";
            row+="<td>" + data['ime'] + "</td>";
            row+="<td>" + data['prezime'] + "</td>";
            row+="<td>" + data['pol'] + "</td>";
            row+="<td>" + data['datumRodjenja'] + "</td>";
            row+="<td>" + data['uloga'] + "</td>";

            $('#menadzer').append(row);

        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});
$(document).on("submit","#izmenaPodatakaZaMenadzera",function (event){
    event.preventDefault();

    var username = $("#username").val();
    var password = $("#password").val();
    var ime = $("#ime").val();
    var prezime = $("#prezime").val();

    var izmeniProfil = formToJson1(username,password,ime,prezime);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/menadzer/izmeni",
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

$(document).on("submit","#reg",function (event){
    event.preventDefault();

    var nazivArtikla = $("#nazivArtikla").val();
    var cena = $("#cena").val();
    var ime = $("#ime").val();
    var prezime = $("#prezime").val();

    var noviKorisnik = formToJson2(username,password,ime,prezime);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac/registracija",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(username + " se uspesno registrovao/la!");
                window.location.href="kupac.html";
            },
            error:function (data)
            {
                alert("Greska prilikom registracije!");
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