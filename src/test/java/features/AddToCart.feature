Feature: Magento Automation - Fixed Category Selection

  Scenario: User logs in and adds Phoebe Zipper Sweatshirt (XL, Gray) to the cart
    Given User logs in with valid credentials
    When User selects Women -> Tops > Antonia Racer Tank -> XL -> Purple
    Then Item should be added to cart successfully
