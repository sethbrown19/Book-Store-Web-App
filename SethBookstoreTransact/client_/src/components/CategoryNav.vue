<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <template v-for="category in $store.state.categories">
        <li v-if="category.name === $store.state.selectedCategoryName" :key="category.categoryId"
            class="button selected-category-button">
          {{ category.name }}
        </li>
        <li v-else
            :key="category.categoryId"
            class="button unselected-category-button"
        >
          <router-link :to="'/category/' + category.name">
            {{ category.name }}
          </router-link>
        </li>
      </template>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "CategoryNav",
};
</script>

<style scoped>
.category-buttons {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  background-color: var(--primary-color);
  color: white;
  font-size: 20px;
  text-align: center;
  font-weight: bold;
}

.selected-category-button {
  background-color: white;
  color: var(--primary-color);
}

.unselected-category-button,
.unselected-category-button:visited {
  color: white;
  text-decoration: none;
}

.unselected-category-button:hover,
.unselected-category-button:active {
  background-color: white;
  color: var(--cta-button-color);
  text-decoration: none;
  cursor: pointer;
}

nav a {
  color: white;
  text-decoration: none;
}

nav a:hover {
  color: var(--cta-button-color);
  text-decoration: none;
}
</style>
