<template>
  <section class="book-boxes">
    <li class="book-box">
      <div class="img">
        <img
            :src="require('@/assets/images/books/' + bookImageFileName(book))"
            :alt="book.title"
            width="160px"
        />
      </div>
      <div class="book-title" :book="book">{{ book.title }}</div>
      <div class="book-author" :book="book">{{ book.author }}</div>
      <div class="book-price" :book="book">{{ (book.price / 100).toFixed(2) }}</div>
      <button class="buy-now-button" @click="addToCart(book)">Add to Cart</button>
      <button class="read-now-button">Peek Inside</button>
    </li>
  </section>
</template>

<script>
import Vue from "vue";

const PriceFormatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  minimumFractionDigits: 2
})

Vue.filter('asDollarsAndCents', function (cents) {
  return PriceFormatter.format(cents / 100.0)
})
export default {
  name: "CategoryBookListItem",
  props: {
    book: {
      type: Object,
      required: true,
    },
  },
  methods: {
    addToCart(book) {
      this.$store.dispatch("addToCart", book);
    },
    bookImageFileName: function (book) {
      let name = book.title.toLowerCase()
      name = name.replace(/ /g, "-")
      return `${name}.gif`
    }
  }
};
</script>
<style scoped>
.book-boxes {
  display: flex;
  flex-wrap: wrap;
  gap: 5em;
  padding: 1em;
  color: var(--primary-color);
}

.book-box {
  display: grid;
  grid-template-columns: 1fr minmax(8em, 10em);
  grid-template-rows: min-content min-content max-content 1fr min-content;
  background-color: white;
  column-gap: 0.5em;
  row-gap: 0.25em;
  padding: .65em;
  border: solid var(--primary-color) 3px;
  box-shadow: 8px 8px 16px 8px rgba(0, 0, 0, 0.25);
}

.img {
  grid-column: 1 / 2;
  grid-row: 1 / -1;
}

.read-now-button {
  grid-row: -2 / -1;
  background-color: var(--primary-color);
  border: solid var(--primary-color) 2px;
  color: white;
  height: 35px;
  width: 125px;
  font-size: 0.8rem;
  font-weight: bold;
  align-self: end;
  justify-self: end;

}

.read-now-button:hover {
  cursor: pointer;
  background-color: var(--primary-background-color);
  border: solid var(--primary-background-color) 2px;
  color: white;
}

.book-title {
  font-weight: bold;
  font-size: 1.3em;
  color: var(--primary-color);
}

.book-price {
  font-style: italic;
  color: var(--primary-color);
}

.buy-now-button {
  grid-row: -3 / -2;
  align-self: end;
  justify-self: end;
  right: 100%;
  background-color: var(--cta-button-color);
  border: solid var(--cta-button-color) 2px;
  color: white;
  white-space: nowrap;
  font-weight: bold;
  font-size: 0.8rem;
  text-transform: uppercase;
  height: 35px;
  width: 125px;
}

.buy-now-button:hover {
  cursor: pointer;
  background-color: var(--primary-background-color);
  border: solid var(--primary-background-color) 2px;
}
</style>
