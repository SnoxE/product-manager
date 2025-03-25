db = db.getSiblingDB('product-manager');

default_users = [{
  username: "admin",
  password: "$2a$10$7aB9cH2GhbBQwYP8T0CHeuWxhWShyUdqfC/.3Hzlf0q2TEJpBZbYq", // Hashed "admin123"
  role: "ADMIN"
},
{
  username: "john_doe",
  password: "$2a$10$yCk0Lbs8hTm8CqSRj8oymef8Cm7F3Qg1T.srG9D6p2e.YqQZW0Paq", // Hashed "password123"
  role: "USER"
}]

default_products = [
  {
    name: "Product 1",
    description: "Description for Product 1",
    price: 29.99,
    category: "Category1"
  },
  {
    name: "Product 2",
    description: "Description for Product 2",
    price: 49.99,
    category: "Category2"
  },
  {
    name: "Product 3",
    description: "Description for Product 3",
    price: 19.99,
    category: "Category1"
  },
  {
    name: "Product 4",
    description: "Description for Product 4",
    price: 99.99,
    category: "Category3"
  },
  {
    name: "Product 5",
    description: "Description for Product 5",
    price: 79.99,
    category: "Category2"
  }
]


if (db.users.countDocuments({}) === 0) {
  db.createCollection('users');
  db.users.insertMany(default_users);
  print("Initialized 'users' collection with " + default_users.length + " users")
} else {
  print("'users' collection already exists. Skipping initialization.");
}

if (db.products.countDocuments({}) === 0) {
  db.createCollection('products');
  db.products.insertMany(default_products);
  print("Initialized 'products' collection with " + default_products.length + " products")
} else {
  print("'products' collection already exists. Skipping initialization.");
}