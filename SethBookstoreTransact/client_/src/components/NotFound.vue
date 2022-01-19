<template>
  <div class="not-found">
    <h1 class="cart-message">404 Error. We're sorry that page cannot be found on this site.</h1>
    <router-link to="../home" class="cart-button continue-shopping">
      Home
    </router-link>

  </div>
</template>

<script>
export default {
  name: "not found"
}
</script>

<style scoped>
.not-found {
  text-align: center;
  margin-top: 1.9em;
  min-height: 600px;
}

.not-found a button {
  margin-top: 1.9em;
  margin-bottom: 1.9em;
}

.continue-shopping {
  background-color: white;
  color: var(--primary-color);
  display: inline-block;
  padding: 8px 20px;
  cursor: pointer;
  border: solid var(--primary-color) 2px;
  margin-top: 2em;
  font-size: 25px;
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