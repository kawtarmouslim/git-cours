package web;


import dao.ProductDaoImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Product;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")

public class ProductServlet extends HttpServlet {

        private ProductDaoImp productDAO;


        public void init() {
            productDAO= new ProductDaoImp();
        }
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String action = request.getServletPath();

            try {
                switch (action) {
                    case "/new":
                        showNewForm(request, response);
                        break;
                    case "/insert":
                        insertUser(request, response);
                        break;
                    case "/delete":
                        deleteUser(request, response);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/update":
                        updateUser(request, response);
                        break;
                    default:
                        listUser(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
        private void listUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
            List<Product> listProduct = productDAO.selectAllProduct();
            request.setAttribute("listProduct", listProduct);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
            dispatcher.forward(request, response);
        }

        private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
            dispatcher.forward(request, response);
        }

        private void showEditForm(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            Product existingProduct = productDAO.selectProduct(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
            request.setAttribute("product", existingProduct);
            dispatcher.forward(request, response);

        }

        private void insertUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            String designation = request.getParameter("designation");
            double prix = Double.parseDouble(request.getParameter("prix"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            Product newProduct = new Product(designation, prix, quantite);
            productDAO.save(newProduct);
            response.sendRedirect("list");
        }

        private void updateUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String designation = request.getParameter("name");

            double prix = Double.parseDouble(request.getParameter("prix"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            Product book = new Product(id, designation, prix, quantite);
           productDAO.updateProduct(book);
            response.sendRedirect("list");
        }

        private void deleteUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            productDAO.deleteProduct(id);
            response.sendRedirect("list");

        }
    }


