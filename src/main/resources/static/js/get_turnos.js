// Helper functions to fetch Paciente and Odontologo by ID
function fetchPacienteById(id) {
    return fetch('/pacientes/' + id)
        .then(response => response.json());
}

function fetchOdontologoById(id) {
    return fetch('/odontologos/' + id)
        .then(response => response.json());
}

window.addEventListener('load', function () {
    (function () {
        const url = '/turnos';
        const settings = {
            method: 'GET'
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                // Recorrer la colección de turnos del JSON
                const promises = data.map(turno => {
                    return Promise.all([
                        fetchOdontologoById(turno.odontologoId),
                        fetchPacienteById(turno.pacienteId)
                    ]).then(([odontologo, paciente]) => {
                        return { turno, odontologo, paciente };
                    });
                });

                Promise.all(promises).then(results => {
                    results.forEach(({ turno, odontologo, paciente }) => {
                        var table = document.getElementById("turnoTable");
                        var turnoRow = table.insertRow();
                        let tr_id = 'tr_' + turno.id;
                        turnoRow.id = tr_id;

                        let deleteButton = '<button' +
                            ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                            ' type="button" onclick="deleteBy(' + turno.id + ')" class="btn btn-danger btn_delete">' +
                            '&times' +
                            '</button>';

                        let updateButton = '<button' +
                            ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                            ' type="button" onclick="findBy(' + turno.id + ')" class="btn btn-info btn_id">' +
                            turno.id +
                            '</button>';

                        turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                            '<td>' + odontologo.nombre + " " + odontologo.apellido + '</td>' +
                            '<td>' + paciente.nombre + " " + paciente.apellido + '</td>' +
                            '<td>' + turno.fechaHora + '</td>' +
                            '<td>' + deleteButton + '</td>';
                    });
                }).catch(error => {
                    console.error('Error fetching details:', error);
                });
            }).catch(error => {
                console.error('Error fetching turnos:', error);
            });
    })();

    (function () {
        let pathname = window.location.pathname;
        if (pathname == "/get_turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});

