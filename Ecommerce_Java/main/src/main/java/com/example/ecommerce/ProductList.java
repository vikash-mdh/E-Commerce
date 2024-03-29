package com.example.ecommerce;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {
    private TableView<Product> productTable;

    public VBox createTable(ObservableList<Product> data) {
        TableColumn id = new TableColumn("PRODUCT ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setPrefWidth(100);

        TableColumn name = new TableColumn("PRODUCT NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(550);

        TableColumn price = new TableColumn("PRICE (in ₹)");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setPrefWidth(100);

        productTable = new TableView<>();
        productTable.getColumns().addAll(id, name, price);
        productTable.setItems(data);
        // To remove extra column from productlist table
//        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set styles for the TableView
        productTable.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #ccc;" +
                        "-fx-border-width: 1px;" + "-fx-text-fill: #F4F1F1;"
                        + "-fx-font-weight: bold;" + "-fx-font-size: 12;"
        );

        // Set styles for the columns (you can customize based on your needs)
        id.setStyle("-fx-alignment: CENTER;");
        name.setStyle("-fx-alignment: CENTER;");
        price.setStyle("-fx-alignment: CENTER;");

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(productTable);
        return vBox;
    }

    public VBox getAllProducts() {
        ObservableList<Product> data = Product.getAllProducts();
        return createTable(data);
    }

    public Product getSelectedProduct() {
        return productTable.getSelectionModel().getSelectedItem();
    }

    public VBox getProductsInCart(ObservableList<Product> data) {
        return createTable(data);
    }

    public VBox getMyOrders(ObservableList<Product> data) {
        return createTable(data);
    }
}