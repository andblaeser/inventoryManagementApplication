# inventoryManagementApplication
Simple inventory management application for Teksystems case study.

## User Stories:
- As a stakeholder I want to limit access to the site to only allow users with an authenticated log in to reach the landing, inventory, and add item pages.

- As a user I want to register an account so I can access the entirety of the site.

- As a user I want to add an item to the inventory so the stock of the item can be tracked.

- As a user I want to view all inventory items so I can view the stock of all items in real-time.

- As a user I want to view all items that are out of stock so I can make sure we refill stock quickly.

- As a user I want to add or reduce the stock of an item so I can keep the database up to date at all times.

- As a user I want to delete an item from the inventory so it is no longer tracked or shown as available.

## Challenges
- I knew that I wanted to greet the current user by their first name on the landing page after logging in successfully, but the first name field was not included in the default UserDetails interface. So I created a custom class (MyUserPrincipal) that implements the UserDetails interface and also adds the other fields from my User entity, allowing me to access the firstName field.

- I needed to test the repository layer of my application, but was struggling to do so without adding test data into my working database. I overcame this by using an EntityManager to persist my test data, run the test, and then remove it using the EntityManager.

- I wanted a user to be able to quickly and easily update the database with the click of a button and immediately see the change reflected on screen. I accomplished this by using jQuery and Ajax for on click event handling and to automatically reload the inventory page on a successful update.
