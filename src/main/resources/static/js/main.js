$(function () {
    let pageable = {
        page: 0,
        size: 10
    }
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

function changePage(pageable) {
    $.get('/api/v1/users', pageable, function (data) {
        let html = '';
        data.content.forEach(item => {
            html += `<tr>
            <td>${item.id}</td>
            <td>${item.email}</td>
            <td>${item.birthDay}</td>
            <td>${item.createdDate}</td>
        </tr>`;
        })
        $("#user-data").html(html);
    });
}