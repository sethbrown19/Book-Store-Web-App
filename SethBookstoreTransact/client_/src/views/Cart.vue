<template>
  <div class="cart-page">
    <section v-if="$store.state.cart.numberOfItems === 0" class="empty-cart-page">
      <h1 class="cart-message">Your shopping cart is empty. Please add an item to your cart to continue.</h1>
      <router-link :to="{ name: 'category', params: { name: 'Best Sellers'} }" class="cart-button continue-shopping">
        Continue Shopping
      </router-link>
    </section>
    <section v-else class="non-empty-cart-page">
      <div class="cart-table-location">
        <cart-table></cart-table>
        <router-link :to="{ name: 'category', params: { name: categoryName } }" class="cart-button continue-shopping">
          Continue Shopping
        </router-link>
      </div>
      <div class="summary-table-location">
        <SummaryTable></SummaryTable>
      </div>
    </section>
  </div>

</template>

<script>
import {mapGetters} from 'vuex'
import CartTable from "@/components/CartTable";
import Vue from "vue";
import SummaryTable from "@/components/SummaryTable";

const PriceFormatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  minimumFractionDigits: 2
})


Vue.filter('asDollarsAndCents', function (cents) {
  return PriceFormatter.format(cents / 100.0)
})

export default {
  name: "Cart",
  components: {CartTable, SummaryTable},
  computed: {
    ...mapGetters(['categoryName']),
  },
  methods: {
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", {book, quantity});
    },

  }
};
</script>

<style scoped>

.cart-page {
  padding: 2em 2.75em;
  min-height: 600px;
}

.cart-message {
  display: flex;
  justify-content: center;
  padding-bottom: 20px;
}

.non-empty-cart-page {
  display: flex;
  flex-wrap: wrap;
  column-gap: 2em;
  justify-content: center;
}


.cart-button {
  display: flex;
  justify-content: space-between;
}

.continue-shopping {
  background-color: white;
  color: var(--primary-color);
  display: inline-block;
  padding: 8px 20px;
  cursor: pointer;
  border: solid var(--primary-color) 2px;
  margin-top: 2em;
}

.continue-shopping:hover {
  cursor: pointer;
  background-color: inherit;
  color: var(--primary-color);
}

.cart-button,
.cart-button a {
  text-decoration: none;
}

</style>