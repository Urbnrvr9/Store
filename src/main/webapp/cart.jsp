<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Cart</title>
    </head>
    <body>
    <div id="container">
        <div id="content">
            <table>
                <h4>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </h4>
                <c:forEach var="product" items="${cart}">
                    <c:url var="delete" value="/delete">
                        <c:param name="productId" value="${product.id}"/>
                    </c:url>
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td><a href="${delete}">Delete</a>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <p>
        <a href="catalog">Cart</a>
    </p>
    </body>
</html>
