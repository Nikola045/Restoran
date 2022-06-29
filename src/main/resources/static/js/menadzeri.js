$(document).on("submit", "form", function (event) {           // kada je submitovana forma za kreiranje novog zaposlenog
    event.preventDefault();

    var username = $("#username").val();
    var password = $("#password").val();
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();


    var newEmployeeJSON = formToJSON(username,password,firstName, lastName);  // pozivamo pomoćnu metodu da kreira JSON

    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8080/api/admin/DodajMenadzera/",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: newMenadzerJSON,                                      // Šaljemo novog zaposlenog
        success: function () {
            alert(firstName + " " + lastName + " je uspešno kreiran sa username: " + username+" i password: "+password);
            window.location.href = "korisnici.html";
        },
        error: function (data) {
            alert("Greška!");
        }
    });
});