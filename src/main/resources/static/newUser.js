function newUser() {
    let form = window.formNewUser.newRoles;
    let new_Roles = [];
    let rolesList = document.createElement('ul');

    for (var i = 0; i < form.length; i++) {
        var option = form.options[i];
        let role = document.createElement('li');
        if (option.selected) {
            new_Roles.push(option.value);

            role.textContent = option.value + " ";
            rolesList.appendChild(role);
        }
    }

    fetch('http://localhost:8080/admin/add', {
        method: 'POST',
        body: JSON.stringify({
            firstName: window.formNewUser.newFirstName.value,
            lastName: window.formNewUser.newLastName.value,
            age: window.formNewUser.newAge.value,
            job: window.formNewUser.newJob.value,
            userName: window.formNewUser.newUserName.value,
            password: window.formNewUser.newPassword.value,
            roles: new_Roles
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => response.json())
        .then(user => {
            $('#tBody tr:last').after('<tr id=' + user.id + '>' +
                '<td>' + user.id + '</td>' +
                '<td>' + window.formNewUser.newFirstName.value + '</td>' +
                '<td>' + window.formNewUser.newLastName.value + '</td>' +
                '<td>' + window.formNewUser.newAge.value + '</td>' +
                '<td>' + window.formNewUser.newJob.value + '</td>' +
                '<td>' + window.formNewUser.newUserName.value + '</td>' +
                '<td>' + rolesList.textContent + '</td>' +
                '<td> <button type="button" onclick="getModalEdit(' + user.id + ')" class="btn btn-primary btn-sm">Edit</button> </td>' +
                '<td> <button type="button" onclick="getModalDelete(' + user.id + ')" class="btn btn-danger btn-sm">Delete</button> </td>' +
                '</tr>');

            window.formNewUser.newFirstName.value = "";
            window.formNewUser.newLastName.value = "";
            window.formNewUser.newAge.value = "";
            window.formNewUser.newJob.value = "";
            window.formNewUser.newUserName.value = "";
            window.formNewUser.newPassword.value = "";
            window.formNewUser.newRoles.value = "";

            $('#NewUserCreated').modal();
        });
}