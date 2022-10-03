package org.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "cart", urlPatterns = {"/cart", "/delete"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/delete")) {
            List<Product> cart = (List<Product>) req.getSession().getAttribute("cart");
            var id = req.getParameter("productId");
            cart.removeIf(product -> id.equals(product.getId()));
            req.getSession().setAttribute("cart", cart);
        }
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
