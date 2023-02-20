# E29DesignYourOwnMicroservice

The program has three Web Services, it is composed of the User Web Service, Product Web Service and Cart Web Service.
Each service is connected to a database. They interact using RESTful endpoints.

#The User Service is composed of Create, Read, Update, Delete Operation. The endpoints are:

  Add User - allows the add user to the database.
  View All Users - allows to display all current users.
  Update User - this endpoint allows you edit or update the information of a user.
  Delete User - this endpoint allows you delete specific user by their id.
  
#The Product Service is composed of Add Product, ViewProductPerUser and Delete Product Operation. The endpoints are:

  Add Product - this endpoint allows you to add product. It connected to User Service wherein this checks if the userId exists and its role is seller, otherwise it will return 404 Not Found it the userId does not exists and UnAuthorized if exists but the role is buyer.

  ViewProductPerUser - this endpoint allows you view the products added by specific seller. The current "userId" is checked first and return 404 Found if not found, then it also checks if the role is "seller", if not, it will display UnAuthorized.
  
  DeleteProduct - Allows the user to delete specifi products in the database..
    

#The Cart Service is composed of AddToCart and ViewCart Operation. The endpoints are:

  AddToCart - This endpoints allows the user to add to the Cart. This is connected to the User Service and Product Service. This feature checks if the "userId" is existing in the UserService and it role is "buyer" if not found, it will return 404 Not Found or UnAuthorized if found but the role is "seller". This also checks if the product is existing in the Product Service, If the userId exists and role is "buyer" and the product exists in Product Service, it will add to database.
  
  ViewCart - This endpoint allows the "buyer" to see all the products added to the Cart.
