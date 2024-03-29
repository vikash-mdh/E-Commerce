package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class UserInterface {

    GridPane loginPage;

    HBox headerBar;

    HBox footerBar;

    Button signInButton;

    Label welcomeLable;

    VBox body;

    Customer loggedInCustomer;

    ProductList productList = new ProductList();

    VBox productPage;

    Button placeOrderButton = new Button("Place order");

    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();

    ObservableList<Product> myOrders = FXCollections.observableArrayList();

    public BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 600);
        root.setTop(headerBar);
//        root.setCenter(loginPage);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage = productList.getAllProducts();
        body.getChildren().add(productPage);
        root.setBottom(footerBar);
        return root;
    }

    public UserInterface() throws FileNotFoundException {
        createLogicPage();
        createHeaderBar();
        createFooterBar();
    }

    private void createLogicPage(){
        Text userNameText = new Text("Email/username");
        Text passwordText = new Text("Password");

        userNameText.setStyle(
                "-fx-text-fill: #393838;"
        );

        passwordText.setStyle(
                "-fx-text-fill: #393838;"
        );

        TextField userName = new TextField();
        userName.setPromptText("Type your user name here");
        userName.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 6;" + "-fx-pref-width: 270;"
        );
        PasswordField password = new PasswordField();
        password.setPromptText("Type your password here");
        password.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 6;" + "-fx-pref-width: 270;"
        );
        Label messageLable = new Label();

        Button loginButton = new Button("Login");
        loginButton.setStyle(
                "-fx-background-color: #EB681C;" + "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;" + "-fx-background-radius: 6;"
                        + "-fx-font-size: 13;" // Set the radius for rounded edges
        );

        Button logoutButton = new Button("Log out");
        logoutButton.setStyle(
                "-fx-background-color: white;" + "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" // Set the radius for rounded edges
        );

        welcomeLable = new Label();
        welcomeLable.setStyle("-fx-text-fill: #0cdde6;" + "-fx-font-weight: bold;" + "-fx-font-size: 14;");

        loginPage = new GridPane();
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.setAlignment(Pos.CENTER);
        loginPage.add(userNameText, 0, 0);
        loginPage.add(userName, 1, 0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password, 1, 1);
        loginPage.add(loginButton, 1, 2);
        loginPage.add(messageLable, 1, 3);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(name, pass);

                if(name.isEmpty() || pass.isEmpty()){
                    messageLable.setText("Username or password can't be blank.");
                }else if(loggedInCustomer!=null){
                    welcomeLable.setText("  Hi! " + loggedInCustomer.getName());
                    headerBar.getChildren().addAll(logoutButton, welcomeLable);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                    footerBar.setVisible(true);

                    userName.clear();
                    password.clear();
                    messageLable.setText("");
                }else{
                    messageLable.setText("Incorrect Username or password. Please try again\nwith valid credentials.");
                }
            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                productPage = productList.getAllProducts(); // Update productPage
                body.getChildren().add(productPage);
                loggedInCustomer = null;
                headerBar.getChildren().removeAll(logoutButton, welcomeLable, messageLable);

                myOrders.clear(); // Clear my orders after logout

                body.getChildren().clear();

                Label titleLabel = new Label("Log In / Sign In");
                titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

                // Add an empty label for spacing
                Label spaceLabel = new Label(" ");
                spaceLabel.setMinHeight(20);  // Set the desired vertical spacing

                body.getChildren().addAll(titleLabel, spaceLabel, loginPage);

                footerBar.setVisible(false);
            }
        });

    }

    private void createHeaderBar() throws FileNotFoundException {
        Button homeButton = new Button();
        Image image = new Image(new FileInputStream("/Users/vikashkumar/Desktop/Ecommerce_Java/main/src/main/java/com/example/ecommerce/e-commlogo.png"));

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(88);
        imageView.setFitHeight(24);
        homeButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-radius: 10;" // Set the radius for rounded edges
        );
        homeButton.setGraphic(imageView);

        TextField searchBar = new TextField();
        searchBar.setPromptText("What you want to buy today?");
        searchBar.setPrefWidth(280);
        searchBar.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 6;" // Set the radius for rounded edges
        );

        Button searchButton = new Button();


        Image imageSearch = new Image(new FileInputStream("/Users/vikashkumar/Desktop/Ecommerce_Java/main/src/main/java/com/example/ecommerce/searchlogo.jpg"));

        ImageView imageSearchView = new ImageView();
        imageSearchView.setImage(imageSearch);
        imageSearchView.setFitWidth(17);
        imageSearchView.setFitHeight(17);
        searchButton.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 6;"
        );
        searchButton.setGraphic(imageSearchView);

        Button cartButtom = new Button(" Cart");
      //Image imageCart = new Image(Objects.requireNonNull(getClass().getResourceAsStream("cart-icon.png")));
        Image imageCart = new Image(new FileInputStream("/Users/vikashkumar/Desktop/Ecommerce_Java/main/src/main/java/com/example/ecommerce/cart-icon.png"));

        ImageView imageCartView = new ImageView();
        imageCartView.setImage(imageCart);
        imageCartView.setFitWidth(13);
        imageCartView.setFitHeight(11);
        cartButtom.setStyle(
                "-fx-background-color: white;" + "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" // Set the radius for rounded edges
        );
        cartButtom.setGraphic(imageCartView);

        Button orderButton = new Button("My Orders");
        orderButton.setStyle(
                "-fx-background-color: white;" + "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" // Set the radius for rounded edges
        );

        signInButton = new Button("Sign in");
        signInButton.setStyle(
                "-fx-background-color: white;" + "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" // Set the radius for rounded edges
        );

        headerBar = new HBox();
        headerBar.setPadding(new Insets(10));
        headerBar.setSpacing(10);
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setStyle("-fx-background-color: #F3782D;");
        headerBar.getChildren().addAll(homeButton, searchBar, searchButton, cartButtom, orderButton, signInButton);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                Label titleLabel = new Label("Log In / Sign In");
                titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

                // Add an empty label for spacing
                Label spaceLabel = new Label(" ");
                spaceLabel.setMinHeight(20);  // Set the desired vertical spacing

                body.getChildren().addAll(titleLabel, spaceLabel, loginPage);
                headerBar.getChildren().remove(signInButton);
                searchBar.clear();
                footerBar.setVisible(false);
            }
        });

        cartButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                // Create a label for the title
                Label titleLabel = new Label("My Cart");
                titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

                VBox prodPage = productList.getProductsInCart(itemsInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                placeOrderButton.setStyle(
                        "-fx-background-color: #EB681C;" + "-fx-font-weight: bold;" +
                                "-fx-text-fill: white;" + "-fx-background-radius: 6;"
                                + "-fx-font-size: 13;" // Set the radius for rounded edges
                );
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().addAll(titleLabel, prodPage);
                searchBar.clear();
                footerBar.setVisible(false);
            }
        });

        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                // Create a label for the title
                Label titleLabel = new Label("My Orders");
                titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

                VBox orderPage = productList.getMyOrders(myOrders);
                orderPage.setAlignment(Pos.CENTER);
                orderPage.setSpacing(10);
                body.getChildren().addAll(titleLabel, orderPage);
                searchBar.clear();
                footerBar.setVisible(false);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemsInCart==null){
                    showDialog("Please add a product in the cart to place order!");
                    return;
                }

                if(loggedInCustomer==null){
                    showDialog("Please login first to place order!");
                    return;
                }

                int count = Order.placeMultipleOrder(loggedInCustomer, itemsInCart);
                if(count!=0){
                    showDialog("Order for "+count+" products placed successfully!!");
                    // Add the ordered products to the myOrders list
                    myOrders.addAll(itemsInCart);
                    itemsInCart.clear(); // Clear the cart after placing the order
                    body.getChildren().clear();
//                    // Create a label for the title
                    Label titleLabel = new Label("My Cart");
                    titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

                    VBox prodPage = productList.getProductsInCart(itemsInCart);
                    prodPage.setAlignment(Pos.CENTER);
                    prodPage.setSpacing(10);
                    placeOrderButton.setStyle(
                            "-fx-background-color: #EB681C;" + "-fx-font-weight: bold;" +
                                    "-fx-text-fill: white;" + "-fx-background-radius: 6;"
                                    + "-fx-font-size: 13;" // Set the radius for rounded edges
                    );
                    prodPage.getChildren().add(placeOrderButton);
                    body.getChildren().addAll(titleLabel, prodPage);
                    footerBar.setVisible(false);
                }else{
                    showDialog("Order failed!!");
                }
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                productPage = productList.getAllProducts(); // Update productPage
                body.getChildren().add(productPage);
                searchBar.clear();
                footerBar.setVisible(true);
                if(loggedInCustomer==null && headerBar.getChildren().indexOf(signInButton)==-1){
                    headerBar.getChildren().add(signInButton);
                }
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchText = searchBar.getText().toLowerCase();

                // Call a method to update the product list based on the search criteria
                updateProductList(searchText);
            }
        });

    }

    private void createFooterBar() {
        Button buyNowButton = new Button("Buy Now");
        Button addToCartButton = new Button("Add to Cart");

        buyNowButton.setStyle(
                "-fx-background-color: white;" + "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" // Set the radius for rounded edges
        );

        addToCartButton.setStyle(
                "-fx-background-color: white;" + "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" // Set the radius for rounded edges
        );

        footerBar = new HBox();
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.setStyle("-fx-background-color: #393838;");
        footerBar.getChildren().addAll(buyNowButton, addToCartButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product==null){
                    showDialog("Please select a product first to place order!");
                    return;
                }

                if(loggedInCustomer==null){
                    showDialog("Please login first to place order!");
                    return;
                }

                boolean status = Order.placeOrder(loggedInCustomer, product);
                if(status==true){
                    showDialog("Order placed successfully!!");
                }else{
                    showDialog("Order failed!!");
                }

                myOrders.add(product);
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product==null){
                    showDialog("Please select a product first to add it to cart!");
                    return;
                }
                itemsInCart.add(product);
                showDialog("Selected item has been added to cart successfully!");
            }
        });
    }

    private void showDialog(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }

    private void updateProductList(String searchText) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

        for (Product product : Product.getAllProducts()) {
            // Adjust the condition based on your search criteria
            if (product.getName().toLowerCase().contains(searchText)) {
                filteredProducts.add(product);
            }
        }

        VBox updatedProductPage = productList.createTable(filteredProducts);
        body.getChildren().clear();
        body.getChildren().add(updatedProductPage);
    }
}