<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Contacts</title>

    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>

</head>
<body>
<div class="container">
    <div>
        <%@ include file="/WEB-INF/views/title.jspf" %>
    </div>
    <h3 class="h3">Contacts</h3>
    <h4 align="middle">tel, address, e-mail...</h4>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2541.264762527585!2d30.616378815730823!3d50.436168879473364!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c567e8d1d35d%3A0x9313056d32b49b46!2z0KHQtdC60YbQuNGPIDExLCDQstGD0LvQuNGG0Y8g0KDQtdCz0LXQvdC10YDQsNGC0L7RgNC90LAsIDQsINCa0LjRl9CyLCAwMjAwMA!5e0!3m2!1sru!2sua!4v1484951605603"
                    width="600" height="450" frameborder="0" style="border:0" allowfullscreen>
                your browser does not support iframes
                <img src="/images/map.png" alt="map" align="middle">
            </iframe>
        </div>
    </div>
</div>
</body>
</html>
