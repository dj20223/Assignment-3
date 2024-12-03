# CS 2341
## Assignment 3

* **@author: Dani Jerez 49272827**
* **@author: Yash Shah 49309058**
* **@duedate: November 17, 2024**

------

### Objective:
You are developing a data management system for a large e-commerce platform that
needs to handle thousands of product records. Each product has a unique identifier, a
name, a category, and a price. The system must support efficient insertion and search
to manage and retrieve product information.

### Requirements:
1. **Balanced Search Tree Implementation**
   * Implement a balanced search tree to store the product records. You can choose from the following types of balanced search trees:
     * Red-Black Tree
     * B-Tree
   * Your tree should support the following operations:
     * Insert: Read product information from amazon-product-data.csv and insert each product into the tree.
     * Search: Retrieve a product record by its product-id. Show product-id, name, category and price for the retrieved product.
2. **Performance and Scalability Considerations**
    * Analyze the time complexity of each operation (insert, search) and ensure that they meet the expected performance characteristics of the chosen balanced search tree.
    * Implement and analyze the performance of your tree when dealing with a large dataset.
    * Discuss how the tree remains balanced after multiple operations and the impact of balancing on performance.


### Deliverables
* Provide results for three different search queries and two insertions (one edge case: inserting duplicate product-id -- You will give an error and tell the user product already exists)


### Runtime Analysis
The time complexity of insertions and searches in a Red-Black Tree is O(log n) as the tree is self-balancing, which means it maintains a height proportional to Log n through its specific rules.  It basically means that we can efficiently traverse it. During a search/insertion, the maximum steps required equals the tree’s height. After an insertion, rebalancing and recoloring is necessary to maintain the rules/balance of the red-black tree.  Recolouring and rebalancing are O(1)/constant time operations but because they have to span the length of the path, which is an O(log n) operation,  therefore the insertions take O(log n) time.

------

## Outut
**Search Results:**
- ID: bc178f33a04dbccefa95b165f8b56830, Name: Hoffmaster 120813 Double-Tipped Triangular Crayon, 88 mm Length, Wrapped (500 Packs of 2), Category: Toys & Games | Arts & Crafts | Drawing & Painting Supplies | Crayons, Price: 97.68
- ID: 18018b6bc416dab347b1b7db79994afa, Name: Guillow Airplane Design Studio with Travel Case Building Kit, Category: Toys & Games | Hobbies | Models & Model Kits | Model Kits | Airplane & Jet Kits, Price: 28.91
- ID: 84fb43b933850dc05e57a162c5ba1702, Name: Big Party Pack Orange Peel Plastic Plates | 10.25" | Pack of 50 | Party Supply, Category: Toys & Games | Party Supplies | Party Tableware | Plates, Price: 12.63

**Insertion Results:**
- Product successfully inserted!
- Error: Product with ID 18018b6bc416dab347b1b7db79994afa already exists.