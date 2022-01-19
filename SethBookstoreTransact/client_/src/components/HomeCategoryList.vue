<template>
  <div class="home-page">
    <section class="welcome-text">
      <div class="shop-now-button-position">
        <router-link to="../category/Best Sellers" class="shop-now-button">
          Shop for Books
        </router-link>
      </div>
      <p>Check out our best sellers this past month.</p>
    </section>
    <section class="category-images container">
      <div class="book-boxes">
        <router-link to="../category/Best Sellers" class="book-box">
          <img
              src="@/assets/images/books/the-secrets-of-cutting-grass.gif"
              alt="Cutting Grass"
              width="150px"
          />
        </router-link>
        <router-link to="../category/Best Sellers" class="book-box">
          <img
              src="@/assets/images/books/gulag.gif"
              alt="Gulag"
              width="150px"
          />
        </router-link>
        <router-link to="../category/Best Sellers" class="book-box">
          <img src="@/assets/images/books/heavier-than-heaven.gif" alt="Kurt" width="150px"/>
        </router-link>
        <router-link to="../category/Best Sellers" class="book-box">
          <img
              src="@/assets/images/books/becoming.gif"
              alt="Becoming"
              width="150px"
          />
        </router-link>
      </div>
    </section>

  </div>
</template>

<script>
export default {
  name: "HomeCategoryList",
};
</script>

<style scoped>
.home-page {
  display: grid;
}

.welcome-text {
  display: inline-block;
  text-align: center;
  max-height: 150px;
  padding-top: 20px;
}

.book-boxes {
  display: flex;
  position: relative;
  flex-wrap: wrap;
  gap: 4em;
  padding-bottom: 20px;
  justify-content: center;
}

.book-box {
  display: grid;
  background-color: var(--primary-color);
  padding: 1em;
  box-shadow: 8px 8px 16px 8px rgba(0, 0, 0, 0.25);
  border: solid white 3px;

}

.book-box a {
  text-align: center;
  cursor: pointer;
}

.book-box div {
  padding-bottom: 1em;
  color: white;
  margin-bottom: -2em;
  text-align: center;
  font-size: 18px;
}

.shop-now-button-position {
  text-align: center;
}

.shop-now-button-position a {
  text-decoration: none;
  font-size: 20px;
}

.shop-now-button,
.shop-now-button:visited {
  background-color: var(--cta-button-color);
  color: var(--header-background-color);
  display: inline-block;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 1.5rem;
  border: solid var(--cta-button-color) 2px;
}

.shop-now-button:hover,
.shop-now-button:active {
  background: var(--primary-color);
  border: solid var(--primary-color) 2px;
  color: white;
}
</style>
