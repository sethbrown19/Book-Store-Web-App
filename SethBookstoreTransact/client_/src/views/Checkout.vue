<template>
  <div class="checkout-page">
    <section class="empty-checkout-page" v-if="cart.empty">
      <h1 class="cart-message">Your shopping cart is empty. Please add an item to your cart to continue.</h1>
      <router-link to="../category/Best Sellers" class="cart-button continue-shopping" >
        Continue Shopping
      </router-link>
    </section>

    <section class="checkout-page-body checkout-table" v-else>
      <form @submit.prevent="submitOrder">
        <section v-show="checkoutStatus != ''" class="checkoutStatusBox">
          <div v-if="checkoutStatus == 'ERROR'">
            Error: Please fix the problems and try again.
          </div>

          <div v-else-if="checkoutStatus == 'PENDING'">Processing...</div>

          <div v-else-if="checkoutStatus == 'OK'">Order placed...</div>

          <div v-else>
            An unexpected error occurred, please try again.
          </div>
        </section>
        <div>
          <label for="name">Full Name</label>
          <input
            type="text"
            size="34"
            id="name"
            name="name"
            v-model.lazy="$v.name.$model"
          />
        </div>
        <template v-if="$v.name.$error">
          <span class="error" v-if="!$v.name.required">Name is required.</span>
          <span class="error" v-else-if="!$v.name.minLength">
            Name must have at least
            {{ $v.name.$params.minLength.min }} letters.
          </span>
          <span class="error" v-else-if="!$v.name.maxLength">
            Name can have at most
            {{ $v.name.$params.maxLength.max }} letters.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>
        <div>
          <label for="address">Address</label>
          <input
              type="text"
              size="34"
              id="address"
              name="address"
              v-model.lazy="$v.address.$model"
          />
        </div>
        <template v-if="$v.address.$error">
          <span class="error" v-if="!$v.address.required">Address is required.</span>
          <span class="error" v-else-if="!$v.address.minLength">
            Address must have at least
            {{ $v.address.$params.minLength.min }} letters.
          </span>
          <span class="error" v-else-if="!$v.address.maxLength">
            Address can have at most
            {{ $v.address.$params.maxLength.max }} characters.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>
        <div>
          <label for="phone">Phone</label>
          <input
            class="textField"
            type="text"
            size="34"
            id="phone"
            name="phone"
            v-model.lazy="$v.phone.$model"
          />
        </div>
        <template v-if="$v.phone.$error">
          <span class="error" v-if="!$v.phone.required">Phone is required.</span>
          <span class="error" v-else-if="!$v.phone.phone">Please enter a valid phone number.</span>
          <span class="error" v-else>An unexpected error occurred.</span>
        </template>

        <div>
          <label for="email">Email</label>
          <input type="text" size="34" id="email" name="email" v-model.lazy="$v.email.$model" />
        </div>
        <template v-if="$v.email.$error">
          <span class="error" v-if="!$v.email.required">Email is required.</span>
          <span class="error" v-else-if="!$v.email.email">Please enter a valid email.</span>
          <span class="error" v-else>An unexpected error occurred.</span>
        </template>

        <div>
          <label for="ccNumber">Credit Card</label>
          <input type="text" size="34" id="ccNumber" name="ccNumber" v-model.lazy="$v.ccNumber.$model" />
        </div>
        <template v-if="$v.ccNumber.$error">
          <span class="error" v-if="!$v.ccNumber.required">CC number is required.</span>
          <span class="error" v-else-if="!$v.ccNumber.creditCard">Please enter a valid CC number.</span>
          <span class="error" v-else>An unexpected error occurred.</span>
        </template>

        <div>
          <label>Exp Month / Year</label>
          <select v-model="ccExpiryMonth" >
            <option
              v-for="(month, index) in months"
              :key="month"
              :value="index + 1"
            >
              {{ month }}
            </option>
          </select>
          <select v-model="ccExpiryYear" >
            <option v-for="(year, index) in 10" :key="year">
              {{ yearFrom(index) }}
            </option>
          </select>
        </div>
        <div class="button-position">
                <input
          type="submit"
          name="submit"
          class="button"
          :disabled="checkoutStatus == 'PENDING'"
          value="Complete Purchase"
        />
        </div>
         <div class="total">
          Your credit card will be charged ${{ ($store.state.cart.total / 100).toFixed(2) }}
          <br>(${{ (($store.state.cart.total / 100).toFixed(2)) - 5 }} + $5.00 shipping)
        </div>

      </form>

    </section>

  </div>
</template>

<script>
import {
  required,
  email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

import isCreditCard from "validator/lib/isCreditCard";
import isMobilePhone from "validator/lib/isMobilePhone";

const phone = value => isMobilePhone(value, "en-US");
const creditCard = value => isCreditCard(value);


  export default {
  data() {
    return {
      name: "",
      address: "",
      phone: "",
      email: "",
      ccNumber: "",
      ccExpiryMonth: new Date().getMonth() + 1,
      ccExpiryYear: new Date().getFullYear(),
      checkoutStatus: "",
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    phone: {
      required,
      phone,
    },
    email: {
      required,
      email,
    },
    ccNumber: {
      required,
      creditCard,
    },
    address: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(60),
    },
  },

  computed: {
    cart() {
      return this.$store.state.cart;
    },
    months() {
      return [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec",
      ];
    },
  },

  methods: {
    submitOrder() {
      console.log("Submit order");
      this.$v.$touch(); // Ensures validators always run
      if (this.$v.$error) {
        this.checkoutStatus = "ERROR";
      } else {
        this.checkoutStatus = "PENDING";
        setTimeout(() => {
          this.$store
              .dispatch('placeOrder', {
                name: this.name,
                address: this.address,
                phone: this.phone,
                email: this.email,
                ccNumber: this.ccNumber,
                ccExpiryMonth: this.ccExpiryMonth,
                ccExpiryYear: this.ccExpiryYear
              })
              .then(() => {
                this.checkoutStatus = 'OK'
                this.$router.push({ name: 'confirmation' })
              })
              .catch(reason => {
                this.checkoutStatus = 'SERVER_ERROR'
                console.log('Error placing order', reason)
              })

        }, 1000);
      }
    },

    yearFrom(index) {
      return new Date().getFullYear() + index;
    },
  },
};
</script>

<style scoped>

.cart-message {
  display: flex;
  justify-content: center;
  padding-bottom: 20px;
}

.button {
  justify-self: right;
  background-color: #E74A5D;
  border: none;
  color: white;
  max-width: 400px;
}
.button:hover {
  background-color: #71C9D9;
  cursor: pointer;
}
.total {
  text-align: right;
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

.checkout-page {
  background: var(--primary-background-color);
  color: var(--primary-color);
  padding: 2em 2.75em;
  min-height: 600px;
}
.checkout-page-body {
  display: flex;
  padding: 1em;
}
.checkout-table {
  display: flex;
  row-gap: 1em;
  justify-content: center;
  margin: 0 auto;
  background-color: lightcyan;
  min-width: 25em;
  max-width: 35em;
  border: solid 3px var(--primary-color);

  box-shadow: 8px 8px 16px 8px rgba(0, 0, 0, 0.25);
}

form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: .3em;
  margin-top: 1.4em;
  min-width: 10em;
  margin-right: 5em;
}

form > div > input
 {
  background-color: lightcyan;
  margin-left: 0.5em;
  width: 15em;
  border: solid 2px var(--primary-color);
  padding: .5em;
  margin-top: -.6em;
}
form > div > select {
  background-color: lightcyan;
  margin-left: 0.5em;
  width: 7.25em;
  padding: .45em;
  margin-top: -.4em;
  margin-bottom: .8em;
  border: solid 2px var(--primary-color);
}

.error {
  color: red;
  text-align: center;
  font-size: 15px;
  margin-bottom: .125em;
  margin-left: 3em;
}

.checkoutStatusBox {
  margin: 1em;
  text-align: center;
  color: #E74A5D;
}
.continue-shopping,
.continue-shopping a {
  text-decoration: none;
}

</style>
