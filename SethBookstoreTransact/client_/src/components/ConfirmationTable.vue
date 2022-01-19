<template>
  <div class="table">
    <ul>
      <li class="table-heading">
        <div class="heading-book">Book Title</div>
        <div class="heading-price">Quantity</div>
        <div class="heading-quantity">Book Price</div>
        <div class="subtotal-heading">Book Subtotal</div>
      </li>
      <template
          v-for="(item, index) in this.$store.state.orderDetail.lineItems"
      >
        <li :key="item.productId">
          <div class="book-title">
            {{ $store.state.orderDetail.books[index].title }}
          </div>
          <div class="quantity">{{ item.quantity }}</div>
          <div class="price">{{ $store.state.orderDetail.books[index].price | asDollarsAndCents }}</div>
          <div class="subtotal">{{
              ($store.state.orderDetail.books[index].price * item.quantity) | asDollarsAndCents
            }}
          </div>
        </li>
        <li class="line-sep" :key="item.productId"></li>

      </template>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ConfirmationTable"
}
</script>

<style scoped>
.table {
  display: grid;
  grid-template-columns: minmax(10em, 20em) repeat(3, 7.5em);
  background-color: white;
  border-left: solid 2px var(--primary-color);
  border-right: solid 2px var(--primary-color);
  row-gap: 1em;
  min-width: 40em;
  margin: 0 auto;
  box-shadow: 4px 4px 8px 4px rgba(0, 0, 0, 0.25);
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
  grid-column: 1 / 2;
  padding-left: 2em;
}

.heading-price {
  grid-column: 2/ 3;
  text-align: center;
}

.heading-quantity {
  grid-column: 3 / 4;
  text-align: center;
}

.subtotal-heading {
  grid-column: 4 / 5;
  text-align: center;
}

.book-title {
  padding-left: 2em;
  margin-bottom: .5em;
}

.quantity {
  text-align: center;
}

.price {
  text-align: center;
}

.subtotal {
  text-align: center;
}

.line-sep {
  display: block;
  height: 2px;
  background-color: var(--primary-color);
  grid-column: 1 / -1;
}

</style>