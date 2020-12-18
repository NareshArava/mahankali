# Book App

## Milestone 1

-   Create REST API to fetch data
-   Create a static view. This view should have angular route path as /my-recommendations.
-   It should contain two sections:
    -   Search section
    -   Recommendation section
-   Create a search bar with a search button to search book by title/author.
-   Search section will have text field with ID #search-text-field
-   A button to submit search text field content with ID #search-button
-   On submission of search text should query the results from openlibrary.org API and display search results.
-   Get the results displayed in search results section.
-   Give an id #search-results to search result section.
-   Search results should show a series of card like components and a card should have the following attributes.
-   Assign .book-card class to each book card and all the book cards displayed in all sections should have the below details with attributes.
    -   assign .book-name class to describe name.
    -   assign .book-author class to describe author.
    -   assign .book-image class to show image.
    -   toggle .recommend & .unrecommend classes to recommend and unrecommend buttons.
-   Create a recommend button attached with every book card. Give recommend button a class .recommend
-   Click on Recommend button and the button should change to Unrecommend.
-   The Unrecommend button should have a class .unrecommend and Recommend button should have class .recommend.
-   View recommended books under my-recommendations section
-   Display all recommended books in this section. Recommended books should be displayed under my-recommendations section.
-   Unrecommend button, button should change to Recommend again, books should disappear from my-recommendations section

## Milestone 2

-   Create a Dashboard view (Angular Route /dashboard) with three sections Display Favorite, author, recommendations for a book from openlibrary.org one under the other.
-   This Dashboard is the default view to be shown.
-   The 3 sections are:
    -   Favorite books with Id #favorite.
    -   Author with ID #author.
    -   Recommended books with ID #recommended
-   View all Favorite book cards under Favorite section
-   Display all Author under Author section
-   View all book recommendations from 3rd party books service provider (openlibrary.org) under recommendations section

## Milestone 3 - Functional Requirements

### Pagination

-   In case of multiple records page should have pagination option to display data in card layout
    
    ### Implement Authentication
    
-   User should not be able to add anything in favorites & recommendation list until logged in.
-   Create Login Page
-   Create Register Page (User’s email address should be a userid)
-   Create Edit Profile / Change Password page (Email address cannot be changed)
-   Upload profile image while register & displaying the same in toolbar after login
-   Encrypt password using bcrypt while storing in database during registration

## Milestone 4

-   Implement Test Automation – write Unit Tests for Backend and FrontEnd.
-   Add E2E Test Cases, Dockerize and Implement CI using Gitlab Runner.
-   Create the  **README.md**  file with Steps to Execute.

## Acceptance Criteria

The final evaluation of submissions if the following is achieved:

-   Meeting the Core functional requirements as stated
    
-   Write Test Cases with each functionality with all the features
    
-   Follow the MVC model
    
-   Evaluation Rubrics (_On a Rating from 1 to 10 – wherein_  _**1**_  _is_  _**Non Existent**_  _to_  _**10**_  _being_  _**Exceeded Expectations**_)
    
    1.  Functional Requirements
        
    2.  Non-Functional Requirements
        
    3.  Code Quality
        
    4.  Standards, Styles and Guidelines
        
    5.  Aesthetics and Accessibility
