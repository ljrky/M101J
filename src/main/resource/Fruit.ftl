<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>
        Fruite Picker
    </title>
</head>
<body>
<form action="/favorite_fruit", method="post">
    <p>
        What is your favorite fruit
    </p>
    <#list fruits as fruit>
        <p>
            <input type="radio" name="fruit" value="${fruit}">${fruit}</input>
        </p>
    </#list>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>