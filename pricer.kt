class InvoiceProcessor {

    fun calculatePrice(item: Item): Double {
        val basePrice = item.price
        val discount = item.discount ?: 0.0
        val tax = item.tax ?: 0.0
        return basePrice - discount + tax
    }

    fun printInvoice(item: Item) {
        println("Item: ${item.name}")
        println("Base Price: \$${item.price}")
        println("Discount: \$${item.discount ?: 0.0}")
        println("Tax: \$${item.tax ?: 0.0}")
        println("Total: \$${calculatePrice(item)}")
    }

    fun printInvoiceWithTotal(item: Item) {
        println("Item: ${item.name}")
        println("Base Price: \$${item.price}")
        println("Discount: \$${item.discount ?: 0.0}")
        println("Tax: \$${item.tax ?: 0.0}")
        println("Total Price: \$${item.price - (item.discount ?: 0.0) + (item.tax ?: 0.0)}") // Duplicated logic
    }

    fun calculateTotalForItems(items: List<Item>): Double {
        var total = 0.0
        for (item in items) {
            total += calculatePrice(item)
        }
        return total
    }

    fun printAllInvoices(items: List<Item>) {
        for (item in items) {
            printInvoice(item)
            println("-------------------")
        }
    }

    fun printInvoiceSummary(items: List<Item>) {
        println("Total: \$${calculateTotalForItems(items)}")
    }

}

data class Item(
    val name: String,
    val price: Double,
    val discount: Double?,
    val tax: Double?
)

fun main() {
    val items = listOf(
        Item("Item 1", 100.0, 10.0, 8.0),
        Item("Item 2", 200.0, 20.0, 16.0),
        Item("Item 3", 300.0, 30.0, 24.0)
    )

    val processor = InvoiceProcessor()
    processor.printAllInvoices(items)
    processor.printInvoiceSummary(items)
}
