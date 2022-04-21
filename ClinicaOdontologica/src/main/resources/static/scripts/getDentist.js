window.onload = function () {
    console.log("Cargó");

    async function getDentists() {
        let url = '/dentists/list';
        try {
            let res = await fetch(url);
            return await res.json();
        } catch (error) {
            console.log(error);
        }
    }

    async function renderDentists() {
        let dentists = await getDentists();
        let html = '';
        dentists.forEach(dentist => {
            let htmlSegment = `<div class="dentist">
                                <h3>${dentist.name} ${dentist.lastname}</h3>
                                <p>${dentist.license}</p>
                            </div>`;

            html += htmlSegment;
        });

        let container = document.querySelector('#element');
        container.innerHTML = html;
    }

    renderDentists();
}