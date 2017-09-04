$(document).ready(function () {
    var currentPage = 1;
    var countOfPages = $("#CountPagesWithResultSearch").html();
    var countAccountPerPage = $("#CountAccountsPerPage").html();
    var pattern = $("#Pattern").html();
    var startIndex = 0;

    if (countOfPages > 1) {
        $("#nextPageBtn").show();
    }

    //next page button event
    $(function () {
        $("#nextPageBtn").on("click", function () {
            //prepare display of buttons and current page
            currentPage++;
            if (currentPage == countOfPages) {
                $("#nextPageBtn").hide();
            }
            $("#previousPageBtn").show();
            $("#displayPage").html("Page " + currentPage + " of " + countOfPages);

            //ajax
            startIndex = startIndex + countAccountPerPage;
            $.get(window.location.origin + "/nextResultOfSearch",
                {pattern: pattern, start: startIndex, maxResult: countAccountPerPage},
                function (data) {
                    $.each(data, function (i, account) {
                        var avatar = "#AccImg" + i;
                        var accLinkToPage = "#AccLinkToPage" + i;
                        $(avatar).attr("src", account.avatarStringRepresentation);
                        $(accLinkToPage).attr("href", "PageFromSearch?id=" + account.id);
                        $(accLinkToPage).html(account.name + " " + account.surname);
                    });
                }, "json");
        });
    });

    //previous button event
    $(function () {
        $("#previousPageBtn").on("click", function () {
            //prepare display of buttons and current page
            currentPage--;
            if (currentPage == 1) {
                $("#previousPageBtn").hide();
            }
            $("#nextPageBtn").show();
            $("#displayPage").html("Page " + currentPage + " of " + countOfPages);

            //ajax
            startIndex = startIndex - countAccountPerPage;
            $.get(window.location.origin + "/nextResultOfSearch",
                {pattern: pattern, start: startIndex, maxResult: countAccountPerPage},
                function (data) {
                    $.each(data, function (i, account) {
                        var avatar = "#AccImg" + i;
                        var accLinkToPage = "#AccLinkToPage" + i;
                        $(avatar).attr("src", account.avatarStringRepresentation);
                        $(accLinkToPage).attr("href", "PageFromSearch?id=" + account.id);
                        $(accLinkToPage).html(account.name + " " + account.surname);
                    });
                }, "json");
        });
    });
});
