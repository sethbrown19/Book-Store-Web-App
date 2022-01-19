<template>
  <div class="cart-table">
    <ul>
      <li class="table-heading">
        <div class="heading-book">Your Cart <span v-if="$store.state.cart.numberOfItems === 1">({{ $store.state.cart.numberOfItems }} item)</span> <span v-else>({{ $store.state.cart.numberOfItems }} items)</span> </div>
        <div class="heading-price">Book Price </div>
        <div class="heading-quantity">Quantity</div>
        <div class="heading-subtotal">Subtotal</div>
      </li>

      <template v-for="item in $store.state.cart.items">
        <li :key="item.book.bookId">
          <div class="cart-book-image">
            <img
              :src="
                require('@/assets/images/books/' + bookImageFileName(item.book))
              "
              :alt="item.book.title"
              width="100px"
              height="auto"
            />
          </div>
          <div class="cart-book-title">{{ item.book.title }}</div>
          <div class="cart-book-price">
            ${{ (item.book.price / 100).toFixed(2) }}
          </div>
          <div class="cart-book-quantity">
            <span class="quantity">{{ item.quantity }}</span
            >&nbsp;
            <button
              class="icon-button inc-button"
              @click="updateCart(item.book, item.quantity + 1)"
            >
              <i class="fas fa-plus-circle"></i>
            </button>
            <button
              class="icon-button dec-button"
              @click="updateCart(item.book, item.quantity - 1)"
            >
              <i class="fas fa-minus-circle"></i>
            </button>
          </div>
          <div class="cart-book-subtotal">${{  ((item.book.price / 100) * item.quantity).toFixed(2)  }}</div>
        </li>
        <li class="line-sep" :key="item.book.bookId"></li>
      </template>
    </ul>
  </div>
</template>

<script>
export default {
  name: "CartTable",

  methods: {
    bookImageFileName: function (book) {
      let name = book.title.toLowerCase();
      name = name.replace(/ /g, "-")
      console.log("Book image file name: " + name);
      return `${name}.gif`
    },
    addToCart(book) {
      this.$store.dispatch("addToCart", book);
    },
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", { book, quantity });
    },
  },
};
</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(5em, 15em) repeat(3, max-content);
  row-gap: 1em;
  margin: 0 auto;
  background-color: lightcyan;
  box-shadow: 8px 8px 16px 8px rgba(0, 0, 0, 0.25);
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  background-color: var(--primary-color);
  color: white;
}

.table-heading > * {
  background-color: var(--primary-color);
  padding: 1.5em .5em;
}

.heading-book {
  grid-column: 1 / 3;
}

.heading-price {
  grid-column: 3 / 4;
  text-align: right;
}
.heading-quantity {
  grid-column: 4 / 5;
  text-align: center;
}

.heading-subtotal {
  text-align: center;
  grid-column: -2 / -1;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

img {
  display: block;
  width: 100px;
  height: auto;
}
.cart-book-price {
  padding-left: 1em;
  text-align: center;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
}

.cart-book-subtotal {
  text-align: center;
  padding-left: 1.5em;
  padding-right: 1.5em;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: var(--primary-color);
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
}

.inc-button {
  font-size: 1.125rem;
  color: var(--primary-color);
  margin-right: 0.25em;
  background-color: inherit;
}

.inc-button:hover {
  color: var(--primary-background-color);
}

.dec-button {
  font-size: 1.125rem;
  color: var(--primary-background-color);
  background-color: inherit;
}

.dec-button:hover {
  color: #aaa;
}

input[type="number"] {
  width: 4em;
}

select {
  background-color: var(--primary-color);
  color: white;
  border: 2px solid var(--primary-color);
  border-radius: 3px;
}
</style>
