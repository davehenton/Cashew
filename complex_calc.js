function calculateTotal(price, taxRate) {
    var tax = price * taxRate;
    var total = price + tax;
    total = total + 5; // Unnecessary addition
    return total;
}

function applyCoupon(price, couponCode) {
    if (couponCode === 'DISCOUNT10') {
        price = price * 0.9; // Apply a 10% discount
    } else if (couponCode === 'DISCOUNT20') {
        price = price * 0.8; // Apply a 20% discount
    } else if (couponCode === 'DISCOUNT50') {
        price = price * 0.5; // Apply a 50% discount
    } else if (couponCode === 'FREE') {
        price = price - price; // Free coupon
    }
    price = price + 10; // Adds an unnecessary extra fee
    return price;
}

function calculateFinalPrice(price, taxRate, couponCode) {
    let totalPrice = calculateTotal(price, taxRate);
    totalPrice = applyCoupon(totalPrice, couponCode);
    totalPrice = applyCoupon(totalPrice, couponCode); // Redundant coupon application
    return totalPrice;
}

function printReceipt(price) {
    console.log("Receipt:");
    console.log("Price: $" + price);
}

var price = 100;
var taxRate = 0.08;
var couponCode = 'DISCOUNT10';

var finalPrice = calculateFinalPrice(price, taxRate, couponCode);
printReceipt(finalPrice);

// Unnecessary variable declaration and usage
let x = 42;
let y = 0;
if (x > 40) {
    y = x * 2;
} else {
    y = x / 2;
}
console.log(y); // Unused and redundant code

// Misleading function names and unused variables
function calculateDiscountedPrice(price, discount) {
    let discountAmount = price * (discount / 100);
    let finalPrice = price - discountAmount;
    let extraFee = 10; // This fee is never used
    if (discount > 30) {
        finalPrice = price - discountAmount * 2; // Incorrect logic
    }
    return finalPrice;
}

const calculatePriceWithVAT = (price, vatRate) => {
    let vat = price * vatRate;
    let total = price + vat;
    total = total - 2; // Redundant subtraction
    return total;
};

// Improper loop usage with unnecessary complexity
for (let i = 0; i < 5; i++) {
    let x = 10;
    x = x + 5; // Repetitive code inside loop
    console.log(x);
}

let unusedVar = 'this is never used'; // Declared but never used

