    package com.yc.SecurePro.dto.req;

    import jakarta.validation.constraints.Min;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;

    public class ProductRequestDto {
        
        @NotBlank(message = "Designation is required")
        private String designation;

        @NotNull(message = "Prix is required")
        @Min(value = 0, message = "Prix must be greater than or equal to 0")
        private double prix;

        @NotNull(message = "Quantity is required")
        @Min(value = 0, message = "Quantity must be greater than or equal to 0")
        private int quantity;

        @NotNull(message = "Category is required")
        private Long categoryId;

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public double getPrix() {
            return prix;
        }

        public void setPrix(double prix) {
            this.prix = prix;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }
    }
