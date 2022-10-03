package org.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.database.ProductDao;
import org.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "catalog", urlPatterns = {"/catalog", "/add"})
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var session = request.getSession();
        getProducts(session);
        if("/add".equals(request.getServletPath())) {
            addProductToCart(session, request.getParameter("productId"));
        }
        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }

    private void getProducts(HttpSession session) {
        if (Objects.isNull(session.getAttribute("products"))) {
            ProductDao.getAll().ifPresent(list -> session.setAttribute("products", list));
        }
    }

    private void addProductToCart(HttpSession session, String id) {
        List<Product> cart = getCart(session);
        var product = getProductFromCatalog((List<Product>) session.getAttribute("products"), id);
        cart.add(product);
        session.setAttribute("cart", cart);
    }

    private List<Product> getCart(HttpSession session) {
        return Objects.nonNull(session.getAttribute("cart"))
                ? (List<Product>) session.getAttribute("cart")
                : new ArrayList<>();
    }

    private Product getProductFromCatalog(List<Product> products, String id) {
        return products.stream()
                .filter(product -> id.equals(product.getId())).findAny().get();
    }
}
