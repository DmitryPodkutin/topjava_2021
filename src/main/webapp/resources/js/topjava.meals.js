const userAjaxUrl = "ui/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});

// https://coderoad.ru/3786694/Как-сбросить-очистить-форму-через-JavaScript
function clearFilter() {
    $("#filter")[0].reset()
}

function applyFilter() {
    $.ajax({
        type: "GET",
        url: ctx.ajaxUrl+"filter",
        data: $("#filter").serialize()
    }).done(updateTableWithFilter)

    function updateTableWithFilter(data) {
        ctx.datatableApi.clear().rows.add(data).draw();}
}