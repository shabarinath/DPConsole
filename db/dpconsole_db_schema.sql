USE dpconsole;


DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  class_code VARCHAR(30) NOT NULL,
  version BIGINT(20) DEFAULT 0,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) DEFAULT NULL,
  active BOOLEAN NOT NULL DEFAULT 1,
  account_locked BOOLEAN NOT NULL DEFAULT 0,
  account_expired BOOLEAN NOT NULL DEFAULT 0,
  credentials_expired BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY uk_username (username)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  version BIGINT(20) DEFAULT 0,  
  authority VARCHAR(255) DEFAULT NULL,
  user_id BIGINT(20) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  KEY FKBC16F46AB5DA4D87 (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS catalogue_categories;
CREATE TABLE catalogue_categories (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  name VARCHAR(255) NOT NULL,
  precedence INT(10) NOT NULL DEFAULT 0,
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE KEY name_uk(name)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS catalogue_sub_categories;
CREATE TABLE catalogue_sub_categories (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  name VARCHAR(255) NOT NULL,
  category_id BIGINT(20) NOT NULL,    
  precedence INT(10) NOT NULL,    
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE KEY name_uk(category_id, name)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS catalogue_items;
CREATE TABLE catalogue_items (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  name VARCHAR(255) NOT NULL,
  description TEXT DEFAULT NULL,
  sub_category_id BIGINT(20) NOT NULL,  
  food_type VARCHAR(255) NOT NULL,
  precedence INT(10) NOT NULL,  
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE KEY name_uk(sub_category_id, name)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS catalogue_item_aliases;
CREATE TABLE catalogue_item_aliases (
  item_id BIGINT(20) DEFAULT NULL,
  alias VARCHAR(255) DEFAULT NULL,
  list_index INT(10) NOT NULL DEFAULT 0,
  UNIQUE KEY name_uk(item_id, alias)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS kitchens;
CREATE TABLE kitchens (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  name VARCHAR(255) NOT NULL,
  mailbox_username VARCHAR(255) NOT NULL,    
  mailbox_password VARCHAR(255) NOT NULL,       
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE KEY name_uk(name)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS kitchen_delivery_partners;
CREATE TABLE kitchen_delivery_partners (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  kitchen_id BIGINT(20) NOT NULL,
  delivery_partner VARCHAR(255) NOT NULL,
  email_ids VARCHAR(255) NOT NULL,
  commission_percentage DOUBLE(10, 4) NOT NULL,  
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE KEY name_uk(kitchen_id, delivery_partner)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS kitchen_discounts;
CREATE TABLE kitchen_discounts (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  kitchen_id BIGINT(20) NOT NULL,   
  delivery_partner VARCHAR(255) NOT NULL,
  start_time DATETIME DEFAULT NULL, 
  end_time DATETIME DEFAULT NULL, 
  discount DOUBLE(10, 4) NOT NULL, 
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS kitchen_items;
CREATE TABLE kitchen_items (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  kitchen_id BIGINT(20) NOT NULL,   
  item_id BIGINT(20) NOT NULL,
  manufacturing_price DOUBLE(10, 4) NOT NULL,
  market_price DOUBLE(10, 4) NOT NULL,  
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE KEY name_uk(kitchen_id, item_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  kitchen_id BIGINT(20) NOT NULL,   
  delivery_partner VARCHAR(255) NOT NULL,
  dp_order_id VARCHAR(255) NOT NULL,
  parsed_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
  ordered_time DATETIME DEFAULT NULL,
  status VARCHAR(255) NOT NULL,
  total_cost DOUBLE(10, 4) NOT NULL,  
  notes TEXT DEFAULT NULL,
  manual_review BOOLEAN NOT NULL DEFAULT 0,
  manual_review_comments TEXT DEFAULT NULL,
  payment_type VARCHAR(255) DEFAULT NULL,
  active BOOLEAN NOT NULL DEFAULT 1,  
  PRIMARY KEY (id),
  UNIQUE KEY dp_order_id_uk(dp_order_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,  
  version BIGINT(20) NOT NULL DEFAULT 0,
  order_id BIGINT(20) NOT NULL,
  kitchen_item_id BIGINT(20) NOT NULL,
  quantity INT(10) NOT NULL,   
  manufacturing_price DOUBLE(10, 4) NOT NULL,
  market_price DOUBLE(10, 4) NOT NULL,
  dp_received_price DOUBLE(10, 4) NOT NULL,
  list_index INT(10) NOT NULL DEFAULT 0,
  active BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  UNIQUE KEY name_uk(order_id, kitchen_item_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;