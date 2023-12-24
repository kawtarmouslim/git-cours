package dao;


public  class StockService {
            private IProductDao productDao; // Assurez-vous d'avoir une interface pour le DAO

            public StockService() {
                this.productDao = productDao;
            }

            public int getAvailableQuantity(int productId) {
                // Logique pour obtenir la quantité disponible d'un produit
                // Utilisez le DAO pour récupérer les informations de stock depuis la base de données
                OrderProduct product = productDao.getProductById(productId);
                return (product != null) ? product.getQuantityInStock() : 0;
            }

            public boolean updateStock(int productId, int quantityChange) throws InsufficientStockException {
                boolean result = false;
                // Logique pour mettre à jour la quantité en stock d'un produit
                // Utilisez le DAO pour mettre à jour la base de données avec la nouvelle quantité
                OrderProduct product = productDao.getProductById(productId);
                if (product != null) {
                    int newQuantity = product.getQuantityInStock() + quantityChange;

                    if (newQuantity >= 0) {
                        product.setQuantityInStock(newQuantity);
                        productDao.updateProduct(product); // Assurez-vous que cette méthode prend un objet Product en paramètre
                        result = true; // La mise à jour a réussi
                    } else {
                        throw new InsufficientStockException("Stock insuffisant pour le produit avec l'ID : " + productId);
                    }
                }

                // La mise à jour a échoué (par exemple, produit non trouvé)
                return result;
            }
        }

