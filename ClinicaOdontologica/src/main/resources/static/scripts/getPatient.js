window.addEventListener('load', function () {
    console.log("Cargó");

/*     (function () {
        const url = '/patients/list';
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
            .then(data => {
                let element = document.getElementById("element");
                element.innerHTML = `<p>${data.name}</p>`

                console.log(data);
            });
    }); */

    async function getPatients() {
        let url = '/patients/list';
        try {
            let res = await fetch(url);
            return await res.json();
        } catch (error) {
            console.log(error);
        }
    }

    async function renderPatients() {
        let patients = await getPatients();
        let html = '';
        patients.forEach(patient => {
            let htmlSegment = `<div class="patient">
                                <h3>${patient.name} ${patient.lastname}</h3>
                                <div class="email">${patient.email}</div>
                            </div>`;
    
            html += htmlSegment;
        });
    
        let container = document.querySelector('#element');
        container.innerHTML = html;
    }
    
    renderPatients();
})