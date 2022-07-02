$(document).on("click","#kupacProfil",function (){

    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac/profil",
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

            $('#kupac').append(row);

        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});


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

$(document).on("click","#pregledPorudzbina",function (){

    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac/pregled-porudzbina",
        dataType:"json",
        success:function (data){

            var row = "<tr>";
            row+="<td>" + data['vremePorudzbine'] + "</td>";
            row+="<td>" + data['cena'] + "</td>";
            row+="<td>" + data['trenutnoStanjePorudzbine'] + "</td>";

            $('#porudzbine-kupac').append(row);

        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});

$(document).on("submit","#dodajUKorpu",function (event){
    event.preventDefault();

    var nazivArtikla = $("#nazivArtikla").val();
    var kolicina = $("#kolicina").val();
    var nazivRestorana = $("#nazivRestorana").val();

    var noviKorisnik = formToJson2(nazivArtikla,kolicina,nazivRestorana);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac/restoran/dodaj-u-korpu",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(nazivArtikla + " je uspesno dodato u kropu");
            },
            error:function (data)
            {
                alert("Greska prilikom dodavanja u korpu");
            }
        }
    );
});

function formToJson2(naza,kol,nazr)
{
    return JSON.stringify(
        {
            "naizvArtikla":naza,
            "kolicina":kol,
            "nazivRestorana":nazr,
        }
    );
}

$(document).on("click","#pregledKorpe",function (){

    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac/pregled-korpe",
        dataType:"json",
        success:function (data){

            var row = "<tr>";
            row+="<td>" + data['idPorudzbine'] + "</td>";
            row+="<td>" + data['kupac'] + "</td>";
            row+="<td>" + data['poruceniartikli'] + "</td>";

            $('#pregled_korpe').append(row);

        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});


$(document).on("submit","#poruci",function (event){
    event.preventDefault();

    var nazivRestorana = $("#nazivArtikla").val();

    var noviKorisnik = formToJson3(nazivRestorana);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac/poruci",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert("Uspesno kreirana porudzbina iz "+nazivRestorana);
            },
            error:function (data)
            {
                alert("Greska prilikom dodavanja u korpu");
            }
        }
    );
});

function formToJson3(nazr)
{
    return JSON.stringify(
        {
            "nazivRestorana":nazr
                }
    );
}


