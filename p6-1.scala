object InventorySystem extends App {

  case class Product(name: String, quantity: Int, price: Double)

  val inventory1: Map[Int, Product] = Map(
    101 -> Product("ProductA", 50, 10.0),
    102 -> Product("ProductB", 30, 20.0),
    103 -> Product("ProductC", 20, 30.0)
  )

  val inventory2: Map[Int, Product] = Map(
    102 -> Product("ProductB", 25, 25.0),
    104 -> Product("ProductD", 15, 40.0)
  )

  val productNames: List[String] = inventory1.values.toList.map(_.name)
  println(s"Product names in inventory1: $productNames")

  val totalValue: Double = inventory1.values.map(p => p.quantity * p.price).sum
  println(s"Total value of products in inventory1: $$${totalValue}")

  val isEmpty: Boolean = inventory1.isEmpty
  println(s"Is inventory1 empty? $isEmpty")

  val mergedInventory: Map[Int, Product] = (inventory1.toSeq ++ inventory2.toSeq)
    .groupBy(_._1)
    .map { case (id, products) =>
      val mergedProduct = products.map(_._2).reduce { (p1, p2) =>
        Product(p1.name, p1.quantity + p2.quantity, math.max(p1.price, p2.price))
      }
      id -> mergedProduct
    }

  println("Merged Inventory:")
  mergedInventory.foreach { case (id, product) =>
    println(s"ID: $id, Name: ${product.name}, Quantity: ${product.quantity}, Price: ${product.price}")
  }

  val productIdToCheck = 102
  inventory1.get(productIdToCheck) match {
    case Some(product) => println(s"Product with ID $productIdToCheck: Name: ${product.name}, Quantity: ${product.quantity}, Price: ${product.price}")
    case None => println(s"Product with ID $productIdToCheck does not exist in inventory1")
  }

}
