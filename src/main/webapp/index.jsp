<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<button type="button" onclick="initializeDatabase()">Initialize Database</button>
<br>
<a href="employeeServlet">Employee Servlet</a><br />
<a href="index2.html">web client</a><br />
</body>

<script>

    function initializeDatabase()
    {
        var url = "api/employee/initializeDatabase";
        jQuery.ajax({url:url,
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            dataType: "json",
            success:
                function (resultData) {
                    //alert(resultData);
                    //console.log(resultData.name);
                    //$("textarea#theData").text(resultData.name);
                    $("textarea#theData").append("&#13;&#10;GET data: "+ JSON.stringify(resultData) );
                },
            error :
                function(jqXHR, textStatus, errorThrown) {
                },
            timeout: 120000,
        });
    }
</script>
</html>