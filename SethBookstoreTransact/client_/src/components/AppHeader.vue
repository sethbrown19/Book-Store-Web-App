<template>
  <header class="container">
    <section class="booklogo">
      <router-link to="/">
        <img
            src="@/assets/images/site/Real Book Logo.png"
            alt="All Booked Up Logo"
        />
        <h1 class="text-logo">All Booked Up</h1>
      </router-link>
    </section>
    <section class="search-bar">
      <router-link to="../category/Best Sellers" class="search-icon">
        <i class="fas fa-search fa-md"></i>
      </router-link>
      <input class="search" placeholder="Find Your Book" type="text"/>
    </section>
    <section class="header-dropdown-and-cart">
      <div class="cart-sign-in-position">
        <router-link to="/"
        ><i class="fas fa-user-circle fa-2x">&nbsp;&nbsp; </i
        ></router-link>
        <router-link to="/cart">
          <i class="fas fa-shopping-cart fa-2x"></i>
          <span class="cart-sign-in-position badge">{{ $store.state.cart.numberOfItems }}</span>
        </router-link>
        <header-dropdown-menu></header-dropdown-menu>
      </div>
    </section>
  </header>
</template>

<script>
import HeaderDropdownMenu from "@/components/HeaderDropdown";

export default {
  name: "AppHeader",
  components: {HeaderDropdownMenu},
  component: {
    HeaderDropdownMenu,
  },
};
</script>

<style scoped>
header {
  display: grid;
  background: var(--header-background-color);
  grid-template-columns: 1fr 1fr 1fr;
  gap: 1em;
}

.text-logo {
  font-size: 20px;
}


.booklogo {
  display: flex;
  position: relative;
  justify-content: flex-start;
  padding-left: 30px;
}

.booklogo a {
  text-decoration: none;
}

.header-dropdown-and-cart {
  display: flex;
  justify-content: flex-end;
  padding-right: 10px;
  padding-top: 30px;
}

.header-dropdown-and-cart a {
  text-decoration: none;
}

@media (min-width: 600px) {
  header {
    flex-direction: column;
  }
}

.search-bar {
  display: flex;
  position: relative;
  padding-top: 35px;
  max-width: 440px;
  justify-content: center;
}

.search-icon,
.search-icon:visited {
  position: absolute;
  top: 45px;
  left: 8px;
  color: var(--primary-color);
  background-color: inherit;
  border-radius: 50%;
}

.search-icon:hover,
.search-icon:active {
  background: inherit;
  color: var(--primary-color);
  cursor: pointer;
}

.search {
  border: 2px solid var(--primary-color);
  border-radius: 20px;
  height: 40px;
  width: 100%;
  padding: 3px 23px 2px 30px;
  background-color: #f5f5f5;
}

.search:hover,
.search:focus {
  border: solid var(--primary-color) 2px;
  background-color: #f5f5f5;
}

.badge {
  position: absolute;
  text-align: center;
  top: -5px;
  content: attr(value);
  font-size: 15px;
  background: var(--primary-background-color);
  border-radius: 20%;
  padding: 3px;
  left: 65px;
  z-index: 50;
}
</style>
