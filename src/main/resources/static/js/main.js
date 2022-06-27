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