$(document).ready(function () {
    //add events onclick
    $("#showFriendsBtn").on("click", showFriends);
});

function showFriends() {
    $.get(window.location.origin + "/ShowFriends", function (data) {
        $("#areaForRenderedInfo").html(data);
        $("#showFriendsTab").on("click", showFriends);
        $("#showFriendsTab").addClass("active");
        $("#showTakenRequests").on("click", showTakenRequests);
        $("#showTakenRequests").removeClass("active");
        $("#showSentRequests").on("click", showSentRequests);
        $("#showSentRequests").removeClass("active");
    });
};

function showTakenRequests() {
    $.get(window.location.origin + "/ShowTakenRequests", function (data) {
        $("#areaForRenderedInfo").html(data);
        $("#showFriendsTab").on("click", showFriends);
        $("#showFriendsTab").removeClass("active");
        $("#showTakenRequests").on("click", showTakenRequests);
        $("#showTakenRequests").addClass("active");
        $("#showSentRequests").on("click", showSentRequests);
        $("#showSentRequests").removeClass("active");
    });
};

function showSentRequests() {
    $.get(window.location.origin + "/ShowSentRequests", function (data) {
        $("#areaForRenderedInfo").html(data);
        $("#showFriendsTab").on("click", showFriends);
        $("#showFriendsTab").removeClass("active");
        $("#showTakenRequests").on("click", showTakenRequests);
        $("#showTakenRequests").removeClass("active");
        $("#showSentRequests").on("click", showSentRequests);
        $("#showSentRequests").addClass("active");
    });
};
