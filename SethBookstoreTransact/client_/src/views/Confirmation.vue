<template>
  <div class="confirmation-view">
    <section v-if="$store.state.orderDetail == null" class="empty-cart-page">
      <h1 class="cart-message">Your purchase has not been completed. Please continue shopping.</h1>
      <router-link to="../category/Best Sellers" class="cart-button continue-shopping" >
        Continue Shopping
      </router-link>
    </section>
    <section v-else>
      <h1 class="order-confirmation">Order Confirmation Details</h1>
    <ul class="confirmation-information">
      <li><b>Confirmation #:</b> {{$store.state.orderDetail.order.confirmationNumber}}</li>
      <li><b>Date:</b> {{ new Date($store.state.orderDetail.order.dateCreated) }}</li>
      <li><b>Name:</b> {{ $store.state.orderDetail.customer.customerName }}</li>
      <li><b>Address:</b> {{ $store.state.orderDetail.customer.address }}</li>
      <li><b>Email:</b> {{ $store.state.orderDetail.customer.email }}</li>
      <li><b>Phone:</b> {{ ($store.state.orderDetail.customer.phone) }}</li>
      <li><b>Credit Card:</b> {{ (($store.state.orderDetail.customer.ccNumber).replace(/.(?=.{4})/g, 'x')).replace(/(.{4})/g, '$& ') }}
        ({{ new Date($store.state.orderDetail.customer.ccExpDate).getMonth() + 1}}, {{ new Date($store.state.orderDetail.customer.ccExpDate).getFullYear() }})</li>
    </ul>
    <confirmation-table> </confirmation-table>
    </section>
  </div>
</template>

<script>
import ConfirmationTable from "@/components/ConfirmationTable";
import {mapGetters} from "vuex";

export default {
  name: "Confirmation",
  components: { ConfirmationTable },
  computed: {
    ...mapGetters(['categoryName']),
  },
};
</script>

<style scoped>
.confirmation-view {
  display: flex;
  padding: 2em 2.75em;
  min-height: 600px;
  justify-content: center;
}
.confirmation-information {
  background-color: lightcyan;
  border: solid 2px #602656;
  font-size: 18px;
  min-width: 25em;
  padding: .5em 1.5em;
  margin-bottom: .75em;
  box-shadow: 4px 4px 8px 4px rgba(0, 0, 0, 0.25);
  text-align: left;
}
ul {
  text-align: center;
}
ul > li {
  margin: 0.5em;
}
.order-confirmation{
  text-align: center;
  margin-top: .25em;
  margin-bottom: .75em;
  background-color: lightcyan;
  border: solid 2px #602656;
  font-size: 30px;
  min-width: 22em;
  padding: .25em;
  box-shadow: 4px 4px 8px 4px rgba(0, 0, 0, 0.25);

}
.cart-message {
  display: flex;
  justify-content: center;
  padding-bottom: 20px;
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
.continue-shopping,
.continue-shopping a {
  text-decoration: none;
}

</style>