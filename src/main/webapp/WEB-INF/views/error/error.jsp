<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
    <h3>Exception:</h3>
    ${exception }

    <h3>Stack trace:</h3>
    <pre>
        ${exception.cause }
    </pre>
</body>
</html>