window.onload = function () {
    document.querySelector("#form-post").addEventListener("submit", function (event) {
        event.preventDefault();
        
        let name = document.querySelector("#name-post").value;
        let lastname = document.querySelector("#lastname-post").value;
        let email = document.querySelector("#email-post").value;
        let dni = document.querySelector("#dni-post").value;

        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        myHeaders.append("Cookie", "JSESSIONID=EC0B3047C96E5E99C3BC091EA419ED37");

        let raw = JSON.stringify({
            lastname: lastname,
            name: name,
            email: email,
            dni: dni
        });

        let requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("http://localhost:8080/patients", requestOptions)
            .then(response => response.text())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
    })
}