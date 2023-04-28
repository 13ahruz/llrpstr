A  Spring application has been developed to manage magazines and articles.
The system comprises three entities - Users, Magazines, and Articles.

Two distinct roles have been defined within the application - ADMIN and USER.
Upon the creation of a new user, the USER role is automatically allocated.

To showcase the system's functionality, two sample users have been included - one with an ADMIN role and the other with a USER role.
The Admin user can log in using the email address admin@example.com and the password "password",
while the User user can access the system with the email address user@example.com and the password "password."

The application has integrated DTO-based validation to ensure that all inputs
adhere to specific requirements such as character count, email format, mandatory fields, etc.

The security configuration of the system is well established,
with specific links and features only accessible to the ADMIN role.
Conversely, other aspects of the system are available to all users.

The Admin user has complete control over the system and can create, edit, update, and delete users, magazines, and articles.
In contrast, the User role has limited access and can only view their personal profile page,
which is exclusively accessible to the Admin user.