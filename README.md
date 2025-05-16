**🚀 RV GameStore**
RV GameStore is a full-stack e-commerce web application built for browsing and managing gaming accessories. This project was developed as a major personal initiative using Java Spring Boot on the backend and a responsive HTML/CSS/JavaScript frontend.

The platform is designed to simulate an online game store experience with key features such as user registration, game catalog browsing, cart management, and admin-level product control.
-----
**📌 Key Features**
🔐 User Authentication – Separate login for Admin and User with session handling

🛍️ User Portal – Browse games, view details, add to cart

🧑‍💼 Admin Dashboard – Manage games, categories, user data, and orders

📩 Email – SMTP Service Provided

🔄 AJAX Integration – Smooth, dynamic updates without page reloads

🔧 MySQL Database – Efficient data management for users, products, and orders
----
**🧱 Project Structure**
The project is divided into two independent modules:

User Module
Accessible to regular users for browsing, registration, and cart operations

Runs on port 8080

Admin Module
Accessible only to admins for product and user management

Runs on port 9090

----

**🛠️ Tech Stack**
Frontend:
HTML5, CSS3, JavaScript
Bootstrap, Tailwind CSS
jQuery, AJAX

Backend:
Java (Spring Boot)
Hibernate (ORM)
Thymeleaf (Template Engine)
MySQL (Database)

----

📂 Folder Structure Overview

/RV-gamestore
├── /user-portal
├───java
│   └───com
│       └───gamestore
│           │   GamestoreApplication.java
│           │   
│           ├───config
│           │       MailConfig.java
│           │       SecurityConfig.java
│           │       
│           ├───controller
│           │       CartController.java
│           │       CheckoutController.java
│           │       HomeController.java
│           │
│           ├───domain
│           │   │   BillAddress.java
│           │   │   CartItems.java
│           │   │   Contact.java
│           │   │   Game.java
│           │   │   GameCartItem.java
│           │   │   Order.java
│           │   │   Payment.java
│           │   │   ShippingAddress.java
│           │   │   ShoppingCart.java
│           │   │   User.java
│           │   │   UserBilling.java
│           │   │   UserPayment.java
│           │   │   UserShipping.java
│           │   │
│           │   └───security
│           │           Authority.java
│           │           passwordResetToken.java
│           │           Role.java
│           │           UserRole.java
│           │
│           ├───repository
│           │       CartItemRepository.java
│           │       ContactServiceRepository.java
│           │       GameCartItemRepository.java
│           │       GameRepository.java
│           │       OrderRepository.java
│           │       PasswordResetTokenRepository.java
│           │       RoleRepository.java
│           │       ShoppingCartRepository.java
│           │       UserPaymentRepository.java
│           │       UserRepository.java
│           │       UserRoleRepository.java
│           │       UserShippingRepository.java
│           │
│           ├───services
│           │   │   BillingAddressService.java
│           │   │   CartItemService.java
│           │   │   ContactService.java
│           │   │   GameService.java
│           │   │   OrderService.java
│           │   │   PasswordResetTokenService.java
│           │   │   PaymentService.java
│           │   │   ShoppingAddressService.java
│           │   │   ShoppingCartService.java
│           │   │   UserPaymentService.java
│           │   │   UserService.java
│           │   │   UserShippingService.java
│           │   │
│           │   └───impl
│           │           BillingAddressServiceImpl.java
│           │           CartItemServiceImpl.java
│           │           ContactServiceImpl.java
│           │           GameServiceImpl.java
│           │           OrderServiceImpl.java
│           │           PaymentServiceImpl.java
│           │           ShoppingAddressServiceImpl.java
│           │           ShoppingCartServiceImpl.java
│           │           UserPaymentServiceImpl.java
│           │           UserSecurityService.java
│           │           UserServiceImpl.java
│           │           UserShippingServiceImpl.java
│           │
│           └───utility
│                   INDConst.java
│                   MailConstructor.java
│                   SecurityUtility.java
│
└───resources
    │   application.properties
    │   
    ├───static
    │   ├───css
    │   │       bootstrap-theme.css
    │   │       bootstrap-theme.css.map
    │   │       bootstrap-theme.min.css
    │   │       bootstrap-theme.min.css.map
    │   │       bootstrap.css
    │   │       bootstrap.css.map
    │   │       bootstrap.min.css
    │   │       bootstrap.min.css.map
    │   │       legal.css
    │   │       shop.css
    │   │       style.css
    │   │
    │   ├───fonts
    │   │       glyphicons-halflings-regular.eot
    │   │       glyphicons-halflings-regular.svg
    │   │       glyphicons-halflings-regular.ttf
    │   │       glyphicons-halflings-regular.woff
    │   │       glyphicons-halflings-regular.woff2
    │   │
    │   ├───images
    │   │   │   Activision-logo.png
    │   │   │   bag-1.png
    │   │   │   bag.png
    │   │   │   banner.png
    │   │   │   bg1.jpg
    │   │   │   bg2.jpg
    │   │   │   EA-logo.png
    │   │   │   epic-games.png
    │   │   │   faqbg.jpg
    │   │   │   g1.jpg
    │   │   │   game-store-logo.png
    │   │   │   gaming-purpose.jpg
    │   │   │   index.png
    │   │   │   mud.png
    │   │   │   ok.png
    │   │   │   payment.jpg
    │   │   │   payment.png
    │   │   │   posterVideo-60fps.mp4
    │   │   │   posterVideo1.mp4
    │   │   │   privacypolicy.jpg
    │   │   │   refund.png
    │   │   │   Roshan.png
    │   │   │   sample1.png
    │   │   │   sample2.png
    │   │   │   spiderman-2-video.mp4
    │   │   │   spiderman.jpeg
    │   │   │   ssl_certificate.png
    │   │   │   steam.png
    │   │   │   tnq.jpg
    │   │   │   Ubisoft.png
    │   │   │   Welcome-to-GameStore.png
    │   │   │   WWE2k23.jpg
    │   │   │   xbox-games.png
    │   │   │
    │   │   ├───CreditCards
    │   │   │       cc1.png
    │   │   │       cc2.png
    │   │   │       cc4.png
    │   │   │       cc6.png
    │   │   │       cc7.png
    │   │   │       cc8.png
    │   │   │
    │   │   ├───gateway
    │   │   │       apple-pay.png
    │   │   │       bank-card-back-side.png
    │   │   │       mastercard.png
    │   │   │       visa.png
    │   │   │
    │   │   └───Icon
    │   │           logo.ico
    │   │
    │   └───js
    │           bootstrap.js
    │           bootstrap.min.js
    │           jquery-3.3.1.min.js
    │           main.js
    │           npm.js
    │           popper.min.js
    │
    └───templates
        │   account.html
        │   checkout.html
        │   contact.html
        │   forget.html
        │   header.html
        │   index.html
        │   login.html
        │   MyCart.html
        │   OrderConfirmation.html
        │   OrderSummerySendGmail.html
        │   Products.html
        │   register.html
        │   Services.html
        │   shop.html
        │   Singlepage.html
        │   Trust.html
        │
        ├───legal
        │       AboutUs.html
        │       FAQ.html
        │       Payments.html
        │       Privacy_Policy.html
        │       Shopping_Policy.html
        │       Term_Condition.html
        │
        └───Profile
                AddBilling.html
                Billing.html
                EditProfile.html
                EditShipping.html
                MyProfile.html
                NavigatDrawer.html
                Order.html
                OrderDetails.html
                Shipping.html


├── /admin
├───java
│   └───com
│       └───adminportal
│           │   AdminPortalApplication.java
│           │   
│           ├───config
│           │       SecurityConfig.java
│           │       
│           ├───Controller
│           │       GameController.java
│           │       MainController.java
│           │
│           ├───domain
│           │   │   BillAddress.java
│           │   │   CartItems.java
│           │   │   Game.java
│           │   │   GameCartItem.java
│           │   │   Order.java
│           │   │   Payment.java
│           │   │   ShippingAddress.java
│           │   │   shoppingCart.java
│           │   │   User.java
│           │   │   UserBilling.java
│           │   │   UserPayment.java
│           │   │   UserShipping.java
│           │   │
│           │   └───security
│           │           Authority.java
│           │           passwordResetToken.java
│           │           Role.java
│           │           UserRole.java
│           │
│           ├───Repository
│           │       CartItemRepository.java
│           │       GameRepository.java
│           │       OrderServiceRepository.java
│           │       RoleRepository.java
│           │       UserBillingRepository.java
│           │       UserPaymentRepository.java
│           │       UserRepository.java
│           │       UserServiceRepository.java
│           │
│           ├───sevices
│           │   │   GameService.java
│           │   │   OrderService.java
│           │   │   UserBillingService.java
│           │   │   UserPaymentService.java
│           │   │   UserServices.java
│           │   │   UserShippingService.java
│           │   │
│           │   └───impl
│           │           GameServiceImpl.java
│           │           OrderServiceImpl.java
│           │           UserBillingServiceImpl.java
│           │           UserPaymentServiceImp.java
│           │           UserSecurityService.java
│           │           UserServiceImpl.java
│           │           UserShippingServiceImpl.java
│           │
│           └───utility
│                   SecurityUtility.java
│
└───resources
    │   application.properties
    │
    ├───META-INF
    │       additional-spring-configuration-metadata.json
    │
    ├───static
    │   ├───css
    │   │       AddProduct.css
    │   │       bootstrap-theme.css
    │   │       bootstrap-theme.css.map
    │   │       bootstrap-theme.min.css
    │   │       bootstrap-theme.min.css.map
    │   │       bootstrap.css
    │   │       bootstrap.css.map
    │   │       bootstrap.min.css
    │   │       bootstrap.min.css.map
    │   │       global.css
    │   │       style.css
    │   │       
    │   ├───fonts
    │   │       glyphicons-halflings-regular.eot
    │   │       glyphicons-halflings-regular.svg
    │   │       glyphicons-halflings-regular.ttf
    │   │       glyphicons-halflings-regular.woff
    │   │       glyphicons-halflings-regular.woff2
    │   │
    │   ├───images
    │   │   │   cardset.jpg
    │   │   │   controller.jpg
    │   │   │   g3.jpg
    │   │   │   gtav_1.jpg
    │   │   │   gtav_2.jpg
    │   │   │   gtav_3.jpg
    │   │   │   hitman.jpg
    │   │   │   logo.ico
    │   │   │   spiderman.jpeg
    │   │   │   
    │   │   └───Games
    │   │           1.jpg
    │   │           2.jpg
    │   │           3.jpg
    │   │           52.jpg
    │   │
    │   └───js
    │           bootstrap.js
    │           bootstrap.min.js
    │           npm.js
    │
    └───templates
        │   addProducts.html
        │   gameData.html
        │   login.html
        │   main.html
        │   Singlepage.html
        │   updateProduct.html
        │
        └───common
                header.html



The project is organized into two folders: one for users and one for admins, each running on different ports. 
<details>
<summary>📸 Screenshots</summary>
Pages
![checkout](https://github.com/user-attachments/assets/4eef8c49-2603-42af-a0b1-2f92399eb493)
![Screenshot 2024-07-20 222117](https://github.com/user-attachments/assets/383a3987-da28-48ab-bfa0-b1d1e68209db)
![Screenshot 2024-07-20 222050](https://github.com/user-attachments/assets/40462ca3-2df9-471d-a324-cf8fdcae7218)
![Screenshot 2024-07-20 222039](https://github.com/user-attachments/assets/63782700-9809-49bb-be30-03d168c270cd)
: ![Screenshot 2024-07-20 222028](https://github.com/user-attachments/assets/34540fb0-b4a3-4818-815a-553bf0541dff)
![Screenshot 2024-07-20 222016](https://github.com/user-attachments/assets/dceda8d9-58e5-4267-8f52-f710e38531b0)
![Screenshot 2024-07-20 222006](https://github.com/user-attachments/assets/1e00d7ed-455f-45c2-b671-cc4b18fdeb5b)
![Screenshot 2024-07-20 221909](https://github.com/user-attachments/assets/92a9ac4d-3348-4e37-97b0-b600e792ef61)
![Screenshot 2024-07-20 215758](https://github.com/user-attachments/assets/75c1fff5-9ba3-4419-ae4d-67f7b8141712)
![Screenshot 2024-07-20 215720](https://github.com/user-attachments/assets/962edff4-b799-4b35-be79-abed39578191)
![Screenshot 2024-07-20 215647](https://github.com/user-attachments/assets/a8f50f85-7df8-46ac-a08d-32826b3c68cb)
![Screenshot 2024-07-20 215634](https://github.com/user-attachments/assets/ba13d9d3-4aef-48cf-9743-785cb3880dba)
![Screenshot 2024-07-20 215624](https://github.com/user-attachments/assets/91f97c38-2b1f-4a59-bd0b-72ff5acfeba8)
![Screenshot 2024-07-20 215338](https://github.com/user-attachments/assets/ce415c67-f9f2-433c-ae22-43f36dad6da9)


Admin pages:
![Screenshot 2024-07-20 222541](https://github.com/user-attachments/assets/29ac7702-dde2-4a53-958a-34ed0152907a)
![Screenshot 2024-07-20 222531](https://github.com/user-attachments/assets/6385385f-4ac2-46d3-9597-fcf2a384574f)
![Screenshot 2024-07-20 222513](https://github.com/user-attachments/assets/86eab57f-b925-4722-a3f6-ff49dcb6949e)
![Screenshot 2024-07-20 222503](https://github.com/user-attachments/assets/4f29993f-61e1-4c76-94e7-07e05396a9bc)
![Screenshot 2024-07-20 222441](https://github.com/user-attachments/assets/4a1f383c-f24d-4afc-ba69-f1a9e6562e88)

</details>
