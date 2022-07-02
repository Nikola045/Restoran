$(document).on("click","#dostavljacProfil",function (){

    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/dostavljac/profil",
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

            $('#dostavljac').append(row);

        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});
$(document).on("submit","#izmenaPodatakaZaDostavljaca",function (event){
    event.preventDefault();

    var username = $("#username").val();
    var password = $("#password").val();
    var ime = $("#ime").val();
    var prezime = $("#prezime").val();

    var izmeniProfil = formToJson1(username,password,ime,prezime);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/dostavljac/izmeni",
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