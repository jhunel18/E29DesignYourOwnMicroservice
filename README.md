# E29DesignYourOwnMicroservice
This Activity has 3 Web Services
The User Service is composed of CRUD Operation wherein there are endpoints to add,view all, update and delete users.
The Product Service has Add Product where the User Service is used to check first it the user exists and has a role of a selle before allowed to add product.
The Product Service has also viewProduct for each user and delete product.
The Cart Service has AddtoCart functionality where the UserService and Product Service is used to check first if it exist before adding to Cart.
The Cart Service also has the viewAll functionality that shows all products added to Cart.
