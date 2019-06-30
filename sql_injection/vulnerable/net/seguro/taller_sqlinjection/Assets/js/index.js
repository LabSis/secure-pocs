(function () {
    $(document).ready(function () {
        function processProjectsResponse(response) {
            if (response.status === "ok") {
                if (response.data.projects.length === 0) {
                    $("#table-projects").addClass("hidden");
                    $("#search-result-message").text("No results.");
                    $("#search-result-message").removeClass("hidden");
                } else {
                    $("#table-projects").removeClass("hidden");
                    $("#search-result-message").addClass("hidden")
                }
                loadTable(response.data.projects);
            } else {
                $("#search-result-message").text("Could not filter projects. Try again later.");
                $("#search-result-message").removeClass("hidden");
            }
        }
        function loadTable(projects) {
            $("#table-projects tbody").empty();
            for (var i = 0; i < projects.length; i++) {
                var project = projects[i];
                var $tr = $("<tr></tr>");
                var $tds = $("<td>" + project.code + "</td>" + "<td>" + project.name + "</td>" + "<td>" + project.type + "</td>" + "<td>" + project.level + "</td>" + "<td>" + project.owner + "</td>");
                $tr.append($tds);
                $("#table-projects tbody").append($tr);
            }
        }
        $.ajax({
            url: location.origin + "/Index.aspx/GetProjects",
            type: "get",
            data: {},
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            var response = JSON.parse(response.d);
            processProjectsResponse(response);
        });
        $("#btn-filter-search").click(function () {
            if ($("#slc-project-type").val().trim() === "-1") {
                return;
            }
            $("#search-result-message").addClass("hidden");
            $.ajax({
                url: location.origin + "/Index.aspx/FilterProjects",
                type: "get",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: {
                    type: "\"" + $("#slc-project-type").val().trim() +  "\""
                }
            }).done(function (response) {
                console.log(response);
                var response = JSON.parse(response.d);
                processProjectsResponse(response);
            });
        });
    });
})();
