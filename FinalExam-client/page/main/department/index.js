var KEY_ENTER = 13;
var sort = null;

$(function() {
    $('#form-modal-container').load('/page/main/department/form-modal.html');
    $('#delete-modal-container').load('/common/modal/delete-modal.html', null, function() {
        $('#delete-modal-btn-remove').on('click', function(event) {
            $.ajax({
                method: 'DELETE',
                url: 'http://localhost:8080/api/v1/departments',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify($('.selected .id').toArray().map(id => id.innerText)),
                beforeSend: () => showLoading(),
                success: data => loadDepartments(),
                complete: () => hideLoading()
            });
            bootstrap.Modal.getOrCreateInstance($('#delete-modal')).hide();
        });
    });

    if (storage.getItem('key_role') != 'ADMIN') {
        $('#btn-add').hide();
        $('#btn-edit').hide();
        $('#btn-delete').hide();
    }

    addListeners();
    loadDepartments();
});

function addListeners() {
    $('#btn-search, #btn-refresh').on('click', event => loadDepartments());

    // Khi người dùng thay đổi page size
    $('#page-size').on('change', event => loadDepartments());

    // Khi người dùng thay đổi page number và nhấn ENTER
    $('#page-number').on('keypress', event => {
        if (event.which == KEY_ENTER) {
            loadDepartments();
        }
    });

    $('#department-tbody').on('click', 'tr', function(event) {
        if (event.ctrlKey) {
            $(this).toggleClass('selected');
        } else {
            $(this).addClass('selected').siblings().removeClass('selected');
        }
        updateStatus();
    });

    $('#department-thead').on('click', 'th', function(event) {
        $(this).siblings().find('i').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');

        const i = $(this).find('i');
        if (i.hasClass('fa-sort')) {
            i.removeClass('fa-sort').addClass('fa-sort-up');
        } else {
            i.toggleClass('fa-sort-up fa-sort-down');
        }

        let type = i.hasClass('fa-sort-up') ? 'asc' : 'desc';
        sort = `${$(this).attr('key')},${type}`
        loadDepartments();
    });

    $('#btn-add').on('click', event => {
        $('#department-form').trigger('reset');
        $('#form-id-container').hide();
        $('#form-modal-btn-update').hide();
        $('#form-modal-btn-create').show();
        $('#form-modal-title').text('Thêm danh mục sản phẩm');
    });

    $('#btn-edit').on('click', event => {
        $('#department-form').trigger('reset');
        $('#form-modal-btn-create').hide();
        $('#form-modal-btn-update').show();
        $('#form-id-container').show();
        $('#form-modal-title').text('Cập nhật danh mục sản phẩm');

        const row = $('.selected');
        $('#form-id').val(row.find('.id').attr('value'));
        $('#form-name').val(row.find('.name').attr('value'));
    });

    $('#btn-delete').on('click', function(event) {
        $('#delete-modal-title').text('Xóa danh mục sản phẩm');
        const message = `Bạn chắc chắn muốn xóa ${$('.selected').length} danh mục sản phẩm?`;
        $('#delete-modal-body').text(message);
    });
}

function updateStatus() {
    const length = $('.selected').length;
    if (length == 0) {
        $('#btn-edit').attr('disabled', 'disabled');
        $('#btn-delete').attr('disabled', 'disabled');
    } else if (length == 1) {
        $('#btn-edit').removeAttr('disabled');
        $('#btn-delete').removeAttr('disabled');
    } else {
        $('#btn-edit').attr('disabled', 'disabled');
        $('#btn-delete').removeAttr('disabled');
    }
}

function loadDepartments() {
    const searchParams = new URLSearchParams();

    let type = $('#type').val();
    if (!type) type = null;

    const params = {
        page: $('#page-number').val(),
        size: $('#page-size').val(),
        sort: sort,
        search: $('#search').val(),
        type: type,
        minCreatedDate: $('#min-created-date').val(),
        maxCreatedDate: $('#max-created-date').val()
    }
    for (const key in params) {
        if (params[key]) {
            searchParams.set(key, params[key]);
        }
    }

    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/api/v1/departments?' + searchParams,
        beforeSend: () => showLoading(),
        success: function(data) {
            showPageInfo(data);
            showDepartments(data.content);
            updateStatus();
        },
        complete: () => hideLoading(),
    });
}

function showPageInfo(data) {
    const start = data.pageable.offset;
    $('#page-info').text(`Showing ${start + 1} to ${start + data.numberOfElements} of ${data.totalElements} rows.`);
    $('#page-number').attr('max', data.totalPages);
    if (data.last) {
        $('#next').addClass('disabled');
    } else {
        $('#next').removeClass('disabled');
    }
    if (data.first) {
        $('#previous').addClass('disabled');
    } else {
        $('#previous').removeClass('disabled');
    }
}

function showDepartments(departments) {
    const tbody = $('#department-tbody');
    tbody.empty();
    for (const department of departments) {
        tbody.append(`
            <tr>
                <th class='id' value='${department.id}' scope="row">${department.id}</th>
                <td class='name' value='${department.name}'>${department.name}</td>
                <td class='type' value='${department.type}'>${department.type}</td>
                <td class='createdDate'>${department.createdDate}</td>
            </tr>
        `);
    }
}

function showLoading() {
    $('#loading').show();
}

function hideLoading() {
    $('#loading').hide();
}

function changePageNumberBy(value) {
    const page = $('#page-number');
    page.val(+page.val() + value);
    loadDepartments();
}