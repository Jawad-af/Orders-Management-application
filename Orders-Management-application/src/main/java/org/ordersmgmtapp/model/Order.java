package org.ordersmgmtapp.model;

import java.time.LocalDateTime;

    public class Order {
        private static long orderId = 0;
        private long id;
        private long clientId;
        private String productId;
        private int quantity;
        private LocalDateTime orderDate;

        // Constructor
        public Order(long clientId, String productId, int quantity) {
            this.clientId = clientId;
            this.productId = productId;
            this.quantity = quantity;
            this.orderDate = LocalDateTime.now(); // Set the order date to the current date and time
        }

        // Getters and setters
        public long getId() {
            return id;
        }

        public long getClientId() {
            return clientId;
        }

        public void setClientId(long clientId) {
            this.clientId = clientId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public LocalDateTime getOrderDate() {
            return orderDate;
        }

        @Override
        public String toString() {
            return "id:  " + id +
                    "  |  clientId=" + clientId +
                    "  |  productId=" + productId +
                    "  |  quantity=" + quantity +
                    "  |  Date=" + orderDate;
        }
}
