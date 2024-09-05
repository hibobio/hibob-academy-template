/**
 * Iterate through the list of products.
 * Use the ?.let function to safely access the name and price of each product.
 * Print the product details only if both name and price are non-null. Format the output as "Product: [name] - $[price]".
 * If either name or price is null, do not print anything for that product.
 *
 */

data class Product(val name: String?, val price: Double?)

fun main() {
    val products = listOf(
        Product("Laptop", 999.99),
        Product(null, 299.99),
        Product("Smartphone", null),
        Product(null, null)
    )

    // Task: Print the details of products only if both name and price are not null.
    products.map { product: Product ->  product?.name?.let{product?.price?.let { println("[${product.name}] - [${product.price}}") }}}
}
