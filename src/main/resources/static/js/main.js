let pageable = {
    page: 0,
    size: 10
}

$(function () {

    changePage(pageable);

    $('ul.pagination li a').on('click', function (e) {
        e.preventDefault();

        let pageable = {
            page: e.target.innerHTML - 1,
            size: 10
        }

        changePage(pageable);
        $(".page-item").removeClass('active')
        $(e.target).parent().addClass('active')

    });

    $("#add-form").submit(function (event) {
        event.preventDefault();
        let $form = $(this);
        let data = getFormData($form);
        $.ajax({
            type: 'post',
            url: '/api/v1/users',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (response) {
                console.log(response)
                alert("Thêm thành công");
                changePage(pageable);
                $('#exampleModal').modal('hide');
            }
        });
    });

    $("#update-form").submit(function (event) {
        event.preventDefault();
        let $form = $(this);
        let data = getFormData($form);
        $.ajax({
            type: 'put',
            url: '/api/v1/users/' + data.userId ,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            success: function (response) {
                console.log(response)
                alert("Update thành công");
                changePage(pageable);
                $('#updateModal').modal('hide');
            }
        });
    });
})

function deleteUser(userId) {
    let isOk = confirm("Bạn có chắc chắn xoá user này không?");
    if (isOk) {
        $.ajax({
            url: '/api/v1/users/' + userId,
            type: 'DELETE',
            success: function (result) {
                alert("Đã xoá thành công")
                changePage(pageable);
            }
        });
    }
}

function renderListUser(data) {
    let html = '';
    data.content.forEach(item => {
        html += `<tr>
            <td>${item.id}</td>
            <td>${item.email}</td>
            <td>${item.birthDay}</td>
            <td>${item.createdDate}</td>
            <td>
                <button class="btn btn-sm btn-success" onclick='updateUser(${JSON.stringify(item)})'>Update</button> 
                <button class="btn btn-sm btn-danger" onclick="deleteUser(${item.id})">Xoá</button> 
            </td>
        </tr>`;
    })
    $("#user-data").html(html);
}

function changePage(pageable) {
    $.get('/api/v1/users', pageable, function (data) {
        renderListUser(data)
    });
}

function searchUsers() {
    let searchValue = $("#txt-find-email").val();
    if (searchValue) {
        $.get('/api/v1/users/email/' + searchValue, pageable, function (data) {
            renderListUser(data)
        })
    }
}

function getFormData($form) {
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function updateUser(user) {

    $('#updateModal').modal('show');

    $('#update-form input[name="fullName"]').val(user.fullName);
    $('#update-form input[name="email"]').val(user.email);
    $('#update-form input[name="password"]').val(user.password);
    $('#update-form input[name="birthDay"]').val(user.birthDay);
    $('#update-form input[name="userId"]').val(user.id);

}