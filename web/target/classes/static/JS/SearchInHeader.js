$(document).ready(function () {
    $("#accountSearchInput").autocomplete({
        select: function (event, ui) {
            window.location.href = window.location.origin + "/PageFromSearch?id=" + ui.item.id;
        },
        source: function (request, response) {
            $.post(window.location.origin + "/searchAutocomplete", {pattern: request.term}, function (data) {
                response($.map(data, function (account, i) {
                    return {value: account.surname, id: account.id, label: account.name + ' ' + account.surname}
                }));
            });
        },
        minLength: 2
    });
});
