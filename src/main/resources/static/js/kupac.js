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

$(document).on("submit","#kupacProfil",function (event) {
    event.preventDefault();
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/kupac/profil",
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
                var row = "<tr>";
                row += "<td>" + data[i]['username'] + "</td>";
                row += "<td>" + data[i]['password'] + "</td>";
                row += "<td>" + data[i]['ime'] + "</td>";
                row += "<td>" + data[i]['prezime'] + "</td>";
                row += "<td>" + data[i]['pol'] + "</td>";
                row += "<td>" + data[i]['datumRodjenja'] + "</td>";
                row += "<td>" + data[i]['uloga'] + "</td>";
                row += "<td>" + data[i]['porudzbine'] + "</td>";
                $('#kupac').append(row);

        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
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