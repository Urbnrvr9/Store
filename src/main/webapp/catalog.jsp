<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Catalog</title>
    </head>
    <body>
        <h1>Каталог продукции</h1>
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
                    <c:forEach var="product" items="${products}">
                        <c:url var="add" value="/add">
                            <c:param name="productId" value="${product.id}"/>
                        </c:url>
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td><a href="${add}">Add to cart</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <p>
            <a href="cart">Catalog</a>
        </p>
    </body>
</html>
