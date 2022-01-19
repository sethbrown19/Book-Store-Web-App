<template>
  <section class="summary-table">
    <div class="table-heading">
      Summary
    </div>
    <div class="line-sep"></div>
    <div class="total-heading">
      <div class="grand-total">
        Cart Total&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${{
          ($store.state.cart.total / 100).toFixed(2)
        }}
      </div>
    </div>
    <div class="line-sep"></div>
    <div class="total-heading">
      <router-link to="/Checkout" class="proceed-checkout">
        Checkout&nbsp;
      </router-link>
    </div>

    <div class="or-position">OR</div>
    <div class="link-like-button-position">
      <button class="link-like-button" @click="updateCart($store.state.cart.clear(), 0)">
        Clear Cart
      </button>
    </div>

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
  name: "SummaryTable",

  methods: {
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", {book, quantity});
    },
  },
}
</script>

<style scoped>

.summary-table {
  display: grid;
  grid-template-rows: 4em 2px 4em 2px 3em  3em 3em;
  row-gap: .25em;
  grid-template-columns: minmax(5em, 15em);
  margin: 0 auto;
  background-color: var(--primary-color);
  min-width: 15em;
  box-shadow: 8px 8px 16px 8px rgba(0, 0, 0, 0.25);

}

.table-heading {
  color: white;
  padding: 20px 10px;
  font-size: 24px;

}

.total-heading {
  color: white;
  padding: 10px 10px;
  font-size: 20px;
}

.line-sep {
  display: block;
  height: 2px;
  background-color: lightcyan;
}

.or-position {
  text-align: center;
  font-size: 18px;
  padding-top: 15px;
  color: white;
  margin-top: .5em;
}

.link-like-button-position {
  text-align: center;
  font-size: 18px;
  padding-top: 10px;
}

.link-like-button {
  color: white;
  background-color: inherit;
  border: none;
}

.link-like-button:hover {
  cursor: pointer;
  color: var(--cta-button-color);
}

.proceed-checkout {
  display: flex;
  background-color: var(--cta-button-color);
  color: white;
  border: none;
  padding: 10px 22px;
  align-items: center;
  justify-content: center;
}

.proceed-checkout:hover {
  background-color: var(--primary-background-color);
  cursor: pointer;
  border: none;
}

.proceed-checkout,
.proceed-checkout a {
  text-decoration: none;
}

.grand-total {
  padding-top: 10px;
}

</style>